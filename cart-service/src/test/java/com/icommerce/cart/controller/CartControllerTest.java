package com.icommerce.cart.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icommerce.cart.entity.Cart;
import com.icommerce.cart.entity.ProductCart;
import com.icommerce.cart.repository.CartRepository;
import com.icommerce.cart.repository.ProductCartRepository;
import com.icommerce.cart.requestDTO.AddProductRequest;
import com.icommerce.cart.service.internal.ProductServiceProxy;
import com.icommerce.common.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created on 6/15/20
 *
 * @author dangkhoa.phung
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductServiceProxy productServiceProxy;

    @MockBean
    private ProductCartRepository productCartRepository;

    @MockBean
    private CartRepository cartRepository;

    @Test
    public void addMoreExistedProductSuccessTest() throws Exception {
        ProductCart productCart = new ProductCart();
        productCart.setQuantity(3);
        productCart.setProductId(1);
        productCart.setCartId(1);
        Product product = new Product();
        product.setId(1);
        product.setName("Product Name");
        Cart cart = new Cart();
        cart.setId(1);
        cart.setAccountId(1);
        cart.setUpdatedDate(new Date(System.currentTimeMillis()));
        Mockito.when(cartRepository.findByAccountId(anyInt())).thenReturn(cart);
        Mockito.when(productCartRepository.findByCartIdAndProductId(productCart.getCartId(), productCart.getProductId())).thenReturn(productCart);
        Mockito.when(productServiceProxy.getDetail(productCart.getProductId())).thenReturn(product);
        AddProductRequest addProductRequest = new AddProductRequest();
        addProductRequest.setProductId(1);
        addProductRequest.setQuantity(2);
        MockHttpServletRequestBuilder addRequest = MockMvcRequestBuilders.post("/add")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(addProductRequest));
        mvc.perform(addRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    public void addNewProductToCartSuccessTest() throws Exception {
        AddProductRequest addProductRequest = new AddProductRequest();
        addProductRequest.setProductId(1);
        addProductRequest.setQuantity(2);
        Product product = new Product();
        product.setId(1);
        product.setName("Product Name 1");
        product.setPrice(3000);
        Mockito.when(productCartRepository.findByCartIdAndProductId(1, 1)).thenReturn(null);
        Mockito.when(productServiceProxy.getDetail(anyInt())).thenReturn(product);
        MockHttpServletRequestBuilder addRequest = MockMvcRequestBuilders.post("/add")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(addProductRequest));
        mvc.perform(addRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    public void addProductServiceUnavailableTest() throws Exception {
        AddProductRequest addProductRequest = new AddProductRequest();
        addProductRequest.setProductId(1);
        addProductRequest.setQuantity(2);
        Mockito.when(productCartRepository.findByCartIdAndProductId(1, 1)).thenReturn(null);
        Mockito.when(productServiceProxy.getDetail(anyInt())).thenReturn(new Product());
        MockHttpServletRequestBuilder addRequest = MockMvcRequestBuilders.post("/add")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(addProductRequest));
        mvc.perform(addRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addProductBadRequestTest() throws Exception {
        AddProductRequest addProductRequest = new AddProductRequest();
        addProductRequest.setProductId(1);
        addProductRequest.setQuantity(2);
        Mockito.when(productServiceProxy.getDetail(anyInt())).thenReturn(null);
        MockHttpServletRequestBuilder addRequest = MockMvcRequestBuilders.post("/add")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(addProductRequest));
        mvc.perform(addRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getProductsSuccessTest() throws Exception {
        Product product = new Product();
        product.setId(1);
        product.setName("Product Name 1");
        product.setPrice(3000);
        Product product2 = new Product();
        product2.setId(2);
        product2.setName("Product Name 2");
        product2.setPrice(4000);
        ProductCart productCart = new ProductCart();
        productCart.setCartId(1);
        productCart.setProductId(1);
        productCart.setQuantity(1);
        ProductCart productCart2 = new ProductCart();
        productCart2.setCartId(1);
        productCart2.setProductId(2);
        productCart2.setQuantity(2);
        Cart cart = new Cart();
        cart.setId(1);
        cart.setAccountId(1);
        cart.setUpdatedDate(new Date(System.currentTimeMillis()));
        Mockito.when(cartRepository.findByAccountId(anyInt())).thenReturn(cart);
        List<ProductCart> productCartList = Arrays.asList(productCart, productCart2);
        Mockito.when(productServiceProxy.getDetail(1)).thenReturn(product);
        Mockito.when(productServiceProxy.getDetail(2)).thenReturn(product2);
        Mockito.when(productCartRepository.findByCartId(anyInt())).thenReturn(productCartList);
        MockHttpServletRequestBuilder addRequest = MockMvcRequestBuilders.get("/get")
                                        .contentType(MediaType.APPLICATION_JSON);
        mvc.perform(addRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
}
