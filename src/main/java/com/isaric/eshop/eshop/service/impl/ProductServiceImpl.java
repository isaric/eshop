package com.isaric.eshop.eshop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isaric.eshop.eshop.exception.MalformedProductException;
import com.isaric.eshop.eshop.exception.ProductNotFoundException;
import com.isaric.eshop.eshop.model.Product;
import com.isaric.eshop.eshop.repository.ProductRepository;
import com.isaric.eshop.eshop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product getProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (!productOptional.isPresent()) {
            throw new ProductNotFoundException(null);
        }
        return productOptional.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createNewProduct(Product product) {
        if (!isNewProductValid(product)) {
            throw new MalformedProductException(product);
        }
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        if (product.getId() == null) {
            throw new ProductNotFoundException(product);
        }

        Optional<Product> existingProductOptional = productRepository.findById(product.getId());

        if (!existingProductOptional.isPresent()) {
            throw new ProductNotFoundException(product);
        }

        if (!isExistingProductValid(product)) {
            throw new MalformedProductException(product);
        }

        productRepository.save(product);
        return product;
    }

    private boolean isNewProductValid(Product product) {
        return (product.getId() == null && product.getName() != null && product.getPrice() != null);
    }

    private boolean isExistingProductValid(Product product) {
        return (product.getName() != null && product.getPrice() != null);
    }
}
