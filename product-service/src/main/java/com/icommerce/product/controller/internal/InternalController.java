package com.icommerce.product.controller.internal;

import com.icommerce.common.entity.Product;
import com.icommerce.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 6/13/20
 *
 * @author dangkhoa.phung
 */
@RestController
@RequestMapping("internal")
public class InternalController {
    private static final Logger LOGGER = LoggerFactory.getLogger(InternalController.class);

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(value = "get", produces = "application/json")
    public Product getDetail(int productId) {
        LOGGER.info("Get detail productId: {}", productId);
        return productRepository.findById(productId).orElse(null);
    }
}
