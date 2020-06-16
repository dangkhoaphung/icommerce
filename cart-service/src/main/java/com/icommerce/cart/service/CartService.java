package com.icommerce.cart.service;


import com.icommerce.cart.exception.IllegalProductException;
import com.icommerce.cart.responseDTO.CartResponse;

import javax.naming.ServiceUnavailableException;

/**
 * Created on 6/13/20
 *
 * @author dangkhoa.phung
 */
public interface CartService {
    CartResponse getProductList(int accountId);

    void add(int accountId, int productId, int quantity) throws IllegalProductException, ServiceUnavailableException;
}
