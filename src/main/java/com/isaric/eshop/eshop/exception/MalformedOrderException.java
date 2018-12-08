package com.isaric.eshop.eshop.exception;

import com.isaric.eshop.eshop.model.Order;

public class MalformedOrderException extends RuntimeException {

    private Order order;

    public MalformedOrderException(Order order) {
        this.order = order;
    }
}
