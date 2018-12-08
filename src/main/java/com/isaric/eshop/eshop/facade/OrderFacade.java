package com.isaric.eshop.eshop.facade;

import java.util.Date;
import java.util.List;

import com.isaric.eshop.eshop.facade.data.OrderData;

public interface OrderFacade {

    OrderData createNewOrder(OrderData orderData);

    List<OrderData> findOderByDateCreated(Date begin, Date end);
}
