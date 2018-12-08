package com.isaric.eshop.eshop.facade.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.isaric.eshop.eshop.facade.OrderFacade;
import com.isaric.eshop.eshop.facade.data.OrderData;
import com.isaric.eshop.eshop.model.Order;
import com.isaric.eshop.eshop.model.Product;
import com.isaric.eshop.eshop.service.OrderService;
import com.isaric.eshop.eshop.service.ProductService;

@Component
public class OrderFacadeImpl implements OrderFacade {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Override
    public OrderData createNewOrder(OrderData orderData) {
        Order order = convertDataToModel(orderData);
        return convertModelToData(orderService.createOrder(order));
    }

    @Override
    public List<OrderData> findOderByDateCreated(Date begin, Date end) {
        return orderService.findOrderByDateCreated(begin, end)
                           .stream()
                           .map(this::convertModelToData)
                           .collect(Collectors.toList());
    }

    private Order convertDataToModel(OrderData orderData) {
        Order target = new Order();
        List<Long> productIds = orderData.getProductIds();
        List<Product> products = null;
        if (productIds != null) {
            products = productIds.stream()
                                 .map(i -> productService.getProductById(i))
                                 .collect(Collectors.toList());
        }
        target.setProducts(products);
        target.setEmail(orderData.getEmail());
        return target;
    }

    private OrderData convertModelToData(Order order) {
        OrderData target = new OrderData();
        target.setEmail(order.getEmail());
        target.setId(order.getId());
        target.setProductIds(order.getProducts().stream()
                                  .map(Product::getId)
                                  .collect(Collectors.toList()));
        target.setDateCreated(order.getDateCreated());
        target.setTotalPrice(order.getTotalPrice());
        return target;
    }

}
