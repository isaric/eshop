package com.isaric.eshop.eshop;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.isaric.eshop.eshop.exception.MalformedOrderException;
import com.isaric.eshop.eshop.model.Order;
import com.isaric.eshop.eshop.model.Product;
import com.isaric.eshop.eshop.service.OrderService;
import com.isaric.eshop.eshop.service.impl.OrderServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class OrderServiceTest {

    private OrderService orderService = new OrderServiceImpl();

    private static final String EMAIL = "test@test.com";

    private List<Product> products;

    @Before
    public void setUp() {
        Product product = new Product();
        product.setId(1L);
        products = new ArrayList<>();
        products.add(product);
    }

    @Test(expected = MalformedOrderException.class)
    public void createOrderWithId() {
        Order order = new Order();
        order.setId(25L);
        order.setProducts(products);
        order.setEmail(EMAIL);
        orderService.createOrder(order);
    }

    @Test(expected = MalformedOrderException.class)
    public void createOrderWithoutEmail() {
        Order order = new Order();
        order.setProducts(products);
        orderService.createOrder(order);
    }

    @Test(expected = MalformedOrderException.class)
    public void createOrderWithoutProducts() {
        Order order = new Order();
        order.setEmail(EMAIL);
        orderService.createOrder(order);
    }
}
