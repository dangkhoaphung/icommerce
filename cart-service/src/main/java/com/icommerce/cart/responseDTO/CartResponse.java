package com.icommerce.cart.responseDTO;

import lombok.Data;

import java.util.List;

/**
 * Created on 6/13/20
 *
 * @author dangkhoa.phung
 */
@Data
public class CartResponse {
    private List<ProductResponse> productList;
}
