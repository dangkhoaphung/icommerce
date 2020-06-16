package com.icommerce.cart.requestDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created on 6/14/20
 *
 * @author dangkhoa.phung
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AddProductRequest {
    private int productId;
    private int quantity;
}
