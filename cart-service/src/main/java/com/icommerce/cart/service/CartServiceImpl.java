package com.icommerce.cart.service;

import com.icommerce.cart.entity.Cart;
import com.icommerce.cart.entity.ProductCart;
import com.icommerce.cart.exception.IllegalProductException;
import com.icommerce.cart.repository.CartRepository;
import com.icommerce.cart.repository.ProductCartRepository;
import com.icommerce.cart.responseDTO.CartResponse;
import com.icommerce.cart.responseDTO.ProductResponse;
import com.icommerce.cart.service.internal.ProductServiceProxy;
import com.icommerce.common.entity.Product;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import javax.naming.ServiceUnavailableException;
import javax.transaction.Transactional;
import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 6/13/20
 *
 * @author dangkhoa.phung
 */
@Service
public class CartServiceImpl implements CartService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CartServiceImpl.class);

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductCartRepository productCartRepository;

    @Autowired
    private ProductServiceProxy productService;

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @Override
    public CartResponse getProductList(final int accountId) {
        LOGGER.info("Get product(s) in cart of account {}", accountId);
        Cart cart = cartRepository.findByAccountId(accountId);
        List<ProductCart> productCartList = Collections.emptyList();
        if (cart != null) {
            productCartList = productCartRepository.findByCartId(cart.getId()).stream()
                    .collect(Collectors.toList());
        }

        CircuitBreaker productDetailCB = circuitBreakerFactory.create("productDetailCB");
        CartResponse cartResponse = new CartResponse();
        List<ProductResponse> responses = productCartList.stream()
                .map(productCart -> {
                    Product product = productDetailCB.run(() ->
                            productService.getDetail(productCart.getProductId()), throwable -> emptyProduct());
                    return new ProductResponse(product, productCart.getQuantity());
                }).filter(productResponse -> StringUtils.isNotBlank(productResponse.getProduct().getName()))
                .collect(Collectors.toList());
        cartResponse.setProductList(responses);
        return cartResponse;
    }

    private Product emptyProduct() {
        LOGGER.info("Fallback empty product");
        return new Product();
    }

    @Transactional
    @Override
    public void add(int accountId, final int productId, final int quantity) throws IllegalProductException, ServiceUnavailableException {
        LOGGER.info("Add {} product {} into account {}", quantity, productId, accountId);
        Cart cart = cartRepository.findByAccountId(accountId);
        if (cart == null) {
            cart = new Cart();
            cart.setAccountId(accountId);
        }
        cart.setUpdatedDate(new Date(System.currentTimeMillis()));

        ProductCart productCart = productCartRepository.findByCartIdAndProductId(cart.getId(), productId);
        if (productCart == null) {//add new product to cart
            CircuitBreaker productDetailCB = circuitBreakerFactory.create("productDetailCB");
            Product product =  productDetailCB.run(() -> productService.getDetail(productId), throwable -> emptyProduct());
            if (product == null) {
                throw new IllegalProductException(String.format("Product %s is not available", productId));
            } else if (StringUtils.isBlank(product.getName())) {
                throw new ServiceUnavailableException("Product is temporary unavailable now. Please try again after a few minutes");
            }
            productCart = new ProductCart();
            productCart.setCartId(cart.getId());
            productCart.setProductId(productId);
            productCart.setQuantity(quantity);
        } else {//update quantity of product in cart
            productCart.setQuantity(productCart.getQuantity() + quantity);
        }

        cartRepository.save(cart);
        productCartRepository.save(productCart);
    }
}
