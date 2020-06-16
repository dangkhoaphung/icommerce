package com.icommerce.cart.handler;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExceptionMessage {

    private final Throwable throwable;

    ExceptionMessage(Throwable throwable) {
        this.throwable = throwable;
    }

    @JsonProperty("message")
    public String getMessage() {
        return throwable.getMessage();
    }

    @JsonProperty("cause")
    public ExceptionMessage getCause() {
        return throwable.getCause() != null ? new ExceptionMessage(throwable.getCause()) : null;
    }
}
