package com.isaric.eshop.eshop.exception;

import com.isaric.eshop.eshop.model.Product;

public class MalformedProductException extends RuntimeException {

    private Product product;

    public MalformedProductException(Product product) {
        this.product = product;
    }
}
