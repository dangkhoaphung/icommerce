package com.icommerce.cart.responseDTO;

import com.icommerce.common.entity.Product;
import lombok.Data;

/**
 * Created on 6/14/20
 *
 * @author dangkhoa.phung
 */
@Data
public class ProductResponse {
    private Product product;
    private int quantity;

    public ProductResponse() {

    }

    public ProductResponse(final Product product, final int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

}
