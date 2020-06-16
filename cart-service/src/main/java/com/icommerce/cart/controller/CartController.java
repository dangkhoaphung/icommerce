package com.icommerce.cart.controller;

import com.icommerce.cart.exception.IllegalProductException;
import com.icommerce.cart.requestDTO.AddProductRequest;
import com.icommerce.cart.responseDTO.CartResponse;
import com.icommerce.cart.responseDTO.Response;
import com.icommerce.cart.service.CartService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.ServiceUnavailableException;

/**
 * Created on 6/13/20
 *
 * @author dangkhoa.phung
 */
@RestController
public class CartController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);
    @Autowired
    private CartService cartService;

    @GetMapping(value = "hello", produces = "application/json")
    public ResponseEntity<String> getCliched() {
        return new ResponseEntity<>("Cart Service", HttpStatus.OK);
    }

    @GetMapping(value = "get", produces = "application/json")
    public ResponseEntity<CartResponse> get() {
        int accountId = 1;
        CartResponse cartResponse = cartService.getProductList(accountId);
        return new ResponseEntity<>(cartResponse, HttpStatus.OK);
    }

    @PostMapping(value = "add", produces = "application/json")
    public ResponseEntity<Response> add(@RequestBody AddProductRequest addProductRequest) throws IllegalProductException, ServiceUnavailableException {
        int accountId = 1;
        Response response = new Response();
        cartService.add(accountId, addProductRequest.getProductId(), addProductRequest.getQuantity());
        response.setStatus("SUCCESS");
        response.setMessage(StringUtils.EMPTY);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
