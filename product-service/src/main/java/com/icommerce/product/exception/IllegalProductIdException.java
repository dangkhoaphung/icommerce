package com.icommerce.product.exception;

import java.util.function.Supplier;

/**
 * Created on 6/14/20
 *
 * @author dangkhoa.phung
 */
public class IllegalProductIdException extends RuntimeException {
    public IllegalProductIdException(String message) {
        super(message);
    }
}
