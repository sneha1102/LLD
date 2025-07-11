package com.inventory;

import com.inventory.models.*;
import com.inventory.repository.*;
import com.inventory.service.*;
import java.util.concurrent.*;
import java.util.List;
import java.util.Random;

public class ConcurrentTestSystem {
    private static final int NUM_THREADS = 10;
    private static final int OPERATIONS_PER_THREAD = 50;
    
    public static void main(String[] args) {
        System.out.println("=== Concurrent Inventory Management System Test ===");
        System.out.println("Testing with " + NUM_THREADS + " threads, " + OPERATIONS_PER_THREAD + " operations each");
        
        // Initialize repositories and services
        ProductRepository productRepository = new InMemoryProductRepository();
        CustomerRepository customerRepository = new InMemoryCustomerRepository();
        OrderRepository orderRepository = new InMemoryOrderRepository();
        
        ProductService productService = new ProductService(productRepository);
        CustomerService customerService = new CustomerService(customerRepository);
        OrderService orderService = new OrderService(orderRepository, productService, customerService);
        
        try {
            // Initialize sample data
            initializeSampleData(productService, customerService);
            
            // Create thread pool
            ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
            CountDownLatch latch = new CountDownLatch(NUM_THREADS);
            
            // Submit concurrent tasks
            for (int i = 0; i < NUM_THREADS; i++) {
                final int threadId = i;
                executor.submit(() -> {
                    try {
                        performConcurrentOperations(threadId, productService, customerService, orderService);
                    } finally {
                        latch.countDown();
                    }
                });
            }
            
            // Wait for all threads to complete
            latch.await();
            executor.shutdown();
            
            // Verify final state
            verifyFinalState(productService, customerService, orderService);
            
            System.out.println("\n=== Concurrent test completed successfully! ===");
            
        } catch (Exception e) {
            System.err.println("Concurrent test failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void initializeSampleData(ProductService productService, CustomerService customerService) {
        System.out.println("Initializing sample data...");
        
        // Create products with limited stock to test concurrency
        Product laptop = productService.createProduct("Laptop", "High-performance laptop", 999.99, 10, "Electronics", "LAP001");
        Product mouse = productService.createProduct("Wireless Mouse", "Ergonomic wireless mouse", 29.99, 20, "Electronics", "MOU001");
        Product keyboard = productService.createProduct("Mechanical Keyboard", "RGB mechanical keyboard", 89.99, 15, "Electronics", "KEY001");
        
        // Create customers
        Customer john = customerService.createCustomer("John Doe", "john@example.com", "555-0101", "123 Main St");
        Customer jane = customerService.createCustomer("Jane Smith", "jane@example.com", "555-0102", "456 Oak Ave");
        Customer bob = customerService.createCustomer("Bob Wilson", "bob@example.com", "555-0103", "789 Pine St");
        
        System.out.println("Sample data initialized: " + 
            productService.getAllProducts().size() + " products, " + 
            customerService.getAllCustomers().size() + " customers");
    }
    
    private static void performConcurrentOperations(int threadId, ProductService productService, 
                                                   CustomerService customerService, OrderService orderService) {
        Random random = new Random();
        List<Product> products = productService.getAllProducts();
        List<Customer> customers = customerService.getAllCustomers();
        
        for (int i = 0; i < OPERATIONS_PER_THREAD; i++) {
            try {
                int operation = random.nextInt(5);
                
                switch (operation) {
                    case 0:
                        // Create order
                        if (!customers.isEmpty()) {
                            Customer customer = customers.get(random.nextInt(customers.size()));
                            Order order = orderService.createOrder(customer.getId());
                            System.out.println("Thread " + threadId + ": Created order " + order.getId());
                        }
                        break;
                        
                    case 1:
                        // Add item to existing order
                        List<Order> orders = orderService.getAllOrders();
                        if (!orders.isEmpty() && !products.isEmpty()) {
                            Order order = orders.get(random.nextInt(orders.size()));
                            Product product = products.get(random.nextInt(products.size()));
                            int quantity = random.nextInt(3) + 1;
                            
                            boolean success = orderService.addItemToOrder(order.getId(), product.getId(), quantity);
                            if (success) {
                                System.out.println("Thread " + threadId + ": Added " + quantity + "x " + product.getName() + " to order " + order.getId());
                            } else {
                                System.out.println("Thread " + threadId + ": Failed to add item (likely out of stock)");
                            }
                        }
                        break;
                        
                    case 2:
                        // Update product stock
                        if (!products.isEmpty()) {
                            Product product = products.get(random.nextInt(products.size()));
                            int newStock = random.nextInt(50) + 10;
                            boolean success = productService.updateProductStock(product.getId(), newStock);
                            if (success) {
                                System.out.println("Thread " + threadId + ": Updated " + product.getName() + " stock to " + newStock);
                            }
                        }
                        break;
                        
                    case 3:
                        // Search products
                        if (!products.isEmpty()) {
                            Product product = products.get(random.nextInt(products.size()));
                            List<Product> searchResults = productService.searchProductsByName(product.getName().substring(0, 3));
                            System.out.println("Thread " + threadId + ": Found " + searchResults.size() + " products matching search");
                        }
                        break;
                        
                    case 4:
                        // Update order status
                        List<Order> allOrders = orderService.getAllOrders();
                        if (!allOrders.isEmpty()) {
                            Order order = allOrders.get(random.nextInt(allOrders.size()));
                            Order.OrderStatus[] statuses = Order.OrderStatus.values();
                            Order.OrderStatus newStatus = statuses[random.nextInt(statuses.length)];
                            
                            boolean success = orderService.updateOrderStatus(order.getId(), newStatus);
                            if (success) {
                                System.out.println("Thread " + threadId + ": Updated order " + order.getId() + " status to " + newStatus);
                            }
                        }
                        break;
                }
                
                // Small delay to simulate real-world conditions
                Thread.sleep(random.nextInt(10));
                
            } catch (Exception e) {
                System.err.println("Thread " + threadId + " encountered error: " + e.getMessage());
            }
        }
    }
    
    private static void verifyFinalState(ProductService productService, CustomerService customerService, OrderService orderService) {
        System.out.println("\n=== Final State Verification ===");
        
        // Check product stock levels
        List<Product> products = productService.getAllProducts();
        System.out.println("Final product stock levels:");
        for (Product product : products) {
            System.out.println("  " + product.getName() + ": " + product.getQuantityInStock() + " units");
        }
        
        // Check order statuses
        List<Order> orders = orderService.getAllOrders();
        System.out.println("\nFinal order summary:");
        System.out.println("  Total orders: " + orders.size());
        
        // Count orders by status
        for (Order.OrderStatus status : Order.OrderStatus.values()) {
            long count = orders.stream().filter(o -> o.getStatus() == status).count();
            System.out.println("  " + status + ": " + count);
        }
        
        // Verify no negative stock
        boolean hasNegativeStock = products.stream().anyMatch(p -> p.getQuantityInStock() < 0);
        if (hasNegativeStock) {
            System.err.println("ERROR: Found products with negative stock!");
        } else {
            System.out.println("✓ All products have non-negative stock levels");
        }
        
        // Verify order totals are calculated correctly
        boolean hasValidTotals = orders.stream().allMatch(order -> {
            double expectedTotal = order.getOrderItems().stream()
                .mapToDouble(item -> item.getQuantity() * item.getUnitPrice())
                .sum();
            return Math.abs(order.getTotalAmount() - expectedTotal) < 0.01;
        });
        
        if (hasValidTotals) {
            System.out.println("✓ All order totals are calculated correctly");
        } else {
            System.err.println("ERROR: Some order totals are incorrect!");
        }
    }
} 