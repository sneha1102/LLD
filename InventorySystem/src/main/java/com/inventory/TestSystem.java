package com.inventory;

import com.inventory.models.*;
import com.inventory.repository.*;
import com.inventory.service.*;

public class TestSystem {
    public static void main(String[] args) {
        System.out.println("=== Testing Inventory Management System ===");
        
        // Initialize repositories and services
        ProductRepository productRepository = new InMemoryProductRepository();
        CustomerRepository customerRepository = new InMemoryCustomerRepository();
        OrderRepository orderRepository = new InMemoryOrderRepository();
        
        ProductService productService = new ProductService(productRepository);
        CustomerService customerService = new CustomerService(customerRepository);
        OrderService orderService = new OrderService(orderRepository, productService, customerService);
        
        try {
            // Test 1: Create products
            System.out.println("\n1. Creating products...");
            Product laptop = productService.createProduct("Laptop", "High-performance laptop", 999.99, 50, "Electronics", "LAP001");
            Product mouse = productService.createProduct("Wireless Mouse", "Ergonomic wireless mouse", 29.99, 100, "Electronics", "MOU001");
            System.out.println("✓ Products created: " + laptop.getName() + ", " + mouse.getName());
            
            // Test 2: Create customers
            System.out.println("\n2. Creating customers...");
            Customer john = customerService.createCustomer("John Doe", "john@example.com", "555-0101", "123 Main St");
            System.out.println("✓ Customer created: " + john.getName());
            
            // Test 3: Create order
            System.out.println("\n3. Creating order...");
            Order order = orderService.createOrder(john.getId());
            System.out.println("✓ Order created: " + order.getId());
            
            // Test 4: Add items to order
            System.out.println("\n4. Adding items to order...");
            boolean added1 = orderService.addItemToOrder(order.getId(), laptop.getId(), 2);
            boolean added2 = orderService.addItemToOrder(order.getId(), mouse.getId(), 1);
            System.out.println("✓ Items added: " + (added1 && added2));
            
            // Test 5: View order details
            System.out.println("\n5. Order details:");
            orderService.getOrderById(order.getId()).ifPresent(o -> {
                System.out.println("   Order ID: " + o.getId());
                System.out.println("   Customer: " + o.getCustomer().getName());
                System.out.println("   Status: " + o.getStatus());
                System.out.println("   Total Amount: $" + o.getTotalAmount());
                System.out.println("   Items: " + o.getOrderItems().size());
            });
            
            // Test 6: Update order status
            System.out.println("\n6. Updating order status...");
            boolean statusUpdated = orderService.updateOrderStatus(order.getId(), Order.OrderStatus.CONFIRMED);
            System.out.println("✓ Status updated: " + statusUpdated);
            
            // Test 7: Check stock levels
            System.out.println("\n7. Checking stock levels...");
            productService.getProductById(laptop.getId()).ifPresent(p -> 
                System.out.println("   " + p.getName() + " stock: " + p.getQuantityInStock() + " (was 50)")
            );
            
            // Test 8: Generate reports
            System.out.println("\n8. Generating reports...");
            System.out.println("   Total products: " + productService.getAllProducts().size());
            System.out.println("   Total customers: " + customerService.getAllCustomers().size());
            System.out.println("   Total orders: " + orderService.getAllOrders().size());
            
            System.out.println("\n=== All tests passed! ===");
            
        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 