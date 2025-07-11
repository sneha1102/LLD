package com.inventory.service;

import com.inventory.models.*;
import com.inventory.repository.OrderRepository;
import java.util.List;
import java.util.Optional;

public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final CustomerService customerService;
    private final Object orderOperationLock = new Object();

    public OrderService(OrderRepository orderRepository, ProductService productService, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.customerService = customerService;
    }

    public Order createOrder(String customerId) {
        Optional<Customer> customer = customerService.getCustomerById(customerId);
        if (customer.isEmpty()) {
            throw new IllegalArgumentException("Customer not found with ID: " + customerId);
        }
        
        Order order = new Order(customer.get());
        return orderRepository.save(order);
    }

    public boolean addItemToOrder(String orderId, String productId, int quantity) {
        synchronized (orderOperationLock) {
            Optional<Order> orderOpt = orderRepository.findById(orderId);
            Optional<Product> productOpt = productService.getProductById(productId);
            
            if (orderOpt.isEmpty() || productOpt.isEmpty()) {
                return false;
            }

            Order order = orderOpt.get();
            Product product = productOpt.get();

            // Check if product is in stock
            if (!productService.isProductInStock(productId, quantity)) {
                return false;
            }

            // Reserve stock first
            if (!productService.reserveStock(productId, quantity)) {
                return false;
            }

            // Create order item
            OrderItem orderItem = new OrderItem(product, quantity);
            order.addOrderItem(orderItem);

            // Update order
            orderRepository.save(order);
            return true;
        }
    }

    public boolean removeItemFromOrder(String orderId, String itemId) {
        synchronized (orderOperationLock) {
            Optional<Order> orderOpt = orderRepository.findById(orderId);
            if (orderOpt.isEmpty()) {
                return false;
            }

            Order order = orderOpt.get();
            
            // Find the item to remove and restore stock
            Optional<OrderItem> itemToRemove = order.getOrderItems().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst();
            
            if (itemToRemove.isPresent()) {
                OrderItem item = itemToRemove.get();
                // Restore stock
                productService.restoreStock(item.getProduct().getId(), item.getQuantity());
                // Remove item from order
                order.removeOrderItem(itemId);
                orderRepository.save(order);
                return true;
            }
            
            return false;
        }
    }

    public Optional<Order> getOrderById(String id) {
        return orderRepository.findById(id);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByCustomer(String customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public List<Order> getOrdersByStatus(Order.OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    public boolean updateOrderStatus(String orderId, Order.OrderStatus status) {
        return orderRepository.updateStatus(orderId, status);
    }

    public boolean cancelOrder(String orderId) {
        synchronized (orderOperationLock) {
            Optional<Order> orderOpt = orderRepository.findById(orderId);
            if (orderOpt.isEmpty()) {
                return false;
            }

            Order order = orderOpt.get();
            
            // Restore stock for cancelled orders
            if (order.getStatus() == Order.OrderStatus.PENDING || order.getStatus() == Order.OrderStatus.CONFIRMED) {
                for (OrderItem item : order.getOrderItems()) {
                    Product product = item.getProduct();
                    productService.restoreStock(product.getId(), item.getQuantity());
                }
            }

            order.setStatus(Order.OrderStatus.CANCELLED);
            orderRepository.save(order);
            return true;
        }
    }

    public double getOrderTotal(String orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        return order.map(Order::getTotalAmount).orElse(0.0);
    }
} 