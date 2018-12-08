package com.isaric.eshop.eshop;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.isaric.eshop.eshop.exception.MalformedProductException;
import com.isaric.eshop.eshop.exception.ProductNotFoundException;
import com.isaric.eshop.eshop.model.Product;
import com.isaric.eshop.eshop.repository.ProductRepository;
import com.isaric.eshop.eshop.service.ProductService;
import com.isaric.eshop.eshop.service.impl.ProductServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService = new ProductServiceImpl();

    @Mock
    private ProductRepository productRepository;

    @Test(expected = MalformedProductException.class)
    public void createNewProductWithId() {
        Product product = new Product();
        product.setId(215L);
        productService.createNewProduct(product);
    }

    @Test(expected = ProductNotFoundException.class)
    public void updateNonexistentProduct() {
        Optional<Product> productOptional= Optional.empty();
        Mockito.when(productRepository.findById(1L)).thenReturn(productOptional);
        productService.getProductById(1L);
    }

}
