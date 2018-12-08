package com.isaric.eshop.eshop.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.isaric.eshop.eshop.exception.MalformedOrderException;
import com.isaric.eshop.eshop.model.Order;
import com.isaric.eshop.eshop.model.Product;
import com.isaric.eshop.eshop.repository.OrderRepository;
import com.isaric.eshop.eshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        if (!isValidNewOrder(order)) {
            throw new MalformedOrderException(order);
        }
        List<Product> products = order.getProducts();
        Double totalPrice = products.stream()
                                    .mapToDouble(Product::getPrice)
                                    .sum();
        order.setTotalPrice(totalPrice);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findOrderByDateCreated(Date begin, Date end) {
        return orderRepository.findOrderByDateCreatedInterval(begin, end);
    }

    private boolean isValidNewOrder(Order order) {
        return (order.getId() == null && order.getEmail() != null && !CollectionUtils.isEmpty(order.getProducts()));
    }
}
