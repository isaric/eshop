package com.isaric.eshop.eshop.service;

import java.util.List;

import com.isaric.eshop.eshop.model.Product;

public interface ProductService {

    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product createNewProduct(Product product);

    Product updateProduct(Product product);
}
