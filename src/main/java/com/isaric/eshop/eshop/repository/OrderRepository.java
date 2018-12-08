package com.isaric.eshop.eshop.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.isaric.eshop.eshop.model.Order;

@Component
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE dateCreated >= ?1 AND dateCreated <= ?2")
    List<Order> findOrderByDateCreatedInterval(Date begin, Date end);
}
