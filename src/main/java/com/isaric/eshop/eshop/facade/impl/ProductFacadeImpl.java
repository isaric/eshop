package com.isaric.eshop.eshop.facade.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.isaric.eshop.eshop.facade.ProductFacade;
import com.isaric.eshop.eshop.facade.data.ProductData;
import com.isaric.eshop.eshop.model.Product;
import com.isaric.eshop.eshop.service.ProductService;

@Component
public class ProductFacadeImpl implements ProductFacade {

    @Autowired
    private ProductService productService;

    @Override
    public ProductData updateProduct(ProductData productData) {
        Product product = new Product();
        BeanUtils.copyProperties(productData, product);
        productService.updateProduct(product);
        return productData;
    }

    @Override
    public List<ProductData> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return products.stream().map(p -> {
           ProductData productData = new ProductData();
           BeanUtils.copyProperties(p, productData);
           return productData;
        }).collect(Collectors.toList());
    }

    @Override
    public ProductData createNewProduct(ProductData productData) {
        Product product = new Product();
        BeanUtils.copyProperties(productData, product);
        Product newProduct = productService.createNewProduct(product);
        BeanUtils.copyProperties(newProduct, productData);
        return productData;
    }
}
