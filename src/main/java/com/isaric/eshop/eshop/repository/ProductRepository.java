package com.isaric.eshop.eshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.isaric.eshop.eshop.model.Product;

@Component
public interface ProductRepository extends JpaRepository<Product, Long> {
}
