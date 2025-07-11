package com.inventory.repository;

import com.inventory.models.Order;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findById(String id);
    List<Order> findAll();
    List<Order> findByCustomerId(String customerId);
    List<Order> findByStatus(Order.OrderStatus status);
    boolean deleteById(String id);
    boolean updateStatus(String orderId, Order.OrderStatus status);
} 