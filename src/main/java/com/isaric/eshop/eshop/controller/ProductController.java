package com.isaric.eshop.eshop.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isaric.eshop.eshop.exception.MalformedProductException;
import com.isaric.eshop.eshop.exception.ProductNotFoundException;
import com.isaric.eshop.eshop.facade.ProductFacade;
import com.isaric.eshop.eshop.facade.data.ProductData;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductFacade productFacade;

    @GetMapping
    public List<ProductData> getAllProducts() {
        return productFacade.getAllProducts();
    }

    @PostMapping
    public ProductData createNewProduct(@RequestBody ProductData productData, HttpServletResponse response) {
        try {
            return productFacade.createNewProduct(productData);
        } catch (MalformedProductException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return null;
    }

    @PutMapping
    public ProductData updateProduct(@RequestBody ProductData productData, HttpServletResponse response) {
        try {
            return productFacade.updateProduct(productData);
        } catch (ProductNotFoundException ex) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return productData;
        }
    }
}
