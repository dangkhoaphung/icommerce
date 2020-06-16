package com.icommerce.product.controller;

import com.icommerce.common.entity.Product;
import com.icommerce.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created on 6/13/20
 *
 * @author dangkhoa.phung
 */
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "hello")
    public ResponseEntity<String> getCliched() {
        return new ResponseEntity<>("Product Service", HttpStatus.OK);
    }

    @GetMapping(value = "get-all", produces = "application/json")
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }
}
