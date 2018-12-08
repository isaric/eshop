package com.isaric.eshop.eshop.exception;

import com.isaric.eshop.eshop.model.Product;

public class ProductNotFoundException extends RuntimeException {

    private Product product;

    public ProductNotFoundException(Product product) {
        this.product = product;
    }
}
