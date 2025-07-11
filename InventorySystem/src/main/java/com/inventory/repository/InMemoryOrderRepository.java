package com.inventory.repository;

import com.inventory.models.Order;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryOrderRepository implements OrderRepository {
    private final Map<String, Order> orders = new ConcurrentHashMap<>();
    private final Object orderStatusLock = new Object();

    @Override
    public Order save(Order order) {
        orders.put(order.getId(), order);
        return order;
    }

    @Override
    public Optional<Order> findById(String id) {
        return Optional.ofNullable(orders.get(id));
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public List<Order> findByCustomerId(String customerId) {
        return orders.values().stream()
                .filter(order -> order.getCustomer().getId().equals(customerId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findByStatus(Order.OrderStatus status) {
        return orders.values().stream()
                .filter(order -> order.getStatus() == status)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteById(String id) {
        return orders.remove(id) != null;
    }

    @Override
    public boolean updateStatus(String orderId, Order.OrderStatus status) {
        synchronized (orderStatusLock) {
            Order order = orders.get(orderId);
            if (order != null) {
                order.setStatus(status);
                return true;
            }
            return false;
        }
    }
} 