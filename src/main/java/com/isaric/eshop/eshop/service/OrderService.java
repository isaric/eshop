package com.isaric.eshop.eshop.service;

import java.util.Date;
import java.util.List;

import com.isaric.eshop.eshop.model.Order;

public interface OrderService {

    Order createOrder(Order order);

    List<Order> findOrderByDateCreated(Date begin, Date end);
}
