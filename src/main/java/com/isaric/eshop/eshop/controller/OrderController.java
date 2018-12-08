package com.isaric.eshop.eshop.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isaric.eshop.eshop.exception.MalformedOrderException;
import com.isaric.eshop.eshop.exception.ProductNotFoundException;
import com.isaric.eshop.eshop.facade.OrderFacade;
import com.isaric.eshop.eshop.facade.data.OrderData;
import com.isaric.eshop.eshop.form.OrderSearchForm;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderFacade orderFacade;

    @PostMapping
    public OrderData createOrder(@RequestBody OrderData orderData, HttpServletResponse response) {
        try {
            return orderFacade.createNewOrder(orderData);
        } catch (ProductNotFoundException ex) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return orderData;
        } catch (MalformedOrderException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return orderData;
        }
    }

    @GetMapping
    public List<OrderData> findOrderByDateRange(@RequestBody @Valid  OrderSearchForm form, BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        return orderFacade.findOderByDateCreated(form.getBegin(), form.getEnd());
    }
}
