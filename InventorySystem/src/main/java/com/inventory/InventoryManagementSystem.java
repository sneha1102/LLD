package com.inventory;

import com.inventory.models.*;
import com.inventory.repository.*;
import com.inventory.service.*;
import java.util.List;
import java.util.Scanner;

public class InventoryManagementSystem {
    private ProductService productService;
    private CustomerService customerService;
    private OrderService orderService;
    private Scanner scanner;

    public InventoryManagementSystem() {
        // Initialize repositories
        ProductRepository productRepository = new InMemoryProductRepository();
        CustomerRepository customerRepository = new InMemoryCustomerRepository();
        OrderRepository orderRepository = new InMemoryOrderRepository();

        // Initialize services
        this.productService = new ProductService(productRepository);
        this.customerService = new CustomerService(customerRepository);
        this.orderService = new OrderService(orderRepository, productService, customerService);
        
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("=== Inventory Management System ===");
        
        // Initialize with sample data
        initializeSampleData();
        
        while (true) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    manageProducts();
                    break;
                case 2:
                    manageCustomers();
                    break;
                case 3:
                    manageOrders();
                    break;
                case 4:
                    viewReports();
                    break;
                case 5:
                    System.out.println("Thank you for using the Inventory Management System!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Manage Products");
        System.out.println("2. Manage Customers");
        System.out.println("3. Manage Orders");
        System.out.println("4. View Reports");
        System.out.println("5. Exit");
    }

    private void manageProducts() {
        while (true) {
            System.out.println("\n=== Product Management ===");
            System.out.println("1. Add Product");
            System.out.println("2. View All Products");
            System.out.println("3. Search Products");
            System.out.println("4. Update Stock");
            System.out.println("5. Delete Product");
            System.out.println("6. Back to Main Menu");
            
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    viewAllProducts();
                    break;
                case 3:
                    searchProducts();
                    break;
                case 4:
                    updateProductStock();
                    break;
                case 5:
                    deleteProduct();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void manageCustomers() {
        while (true) {
            System.out.println("\n=== Customer Management ===");
            System.out.println("1. Add Customer");
            System.out.println("2. View All Customers");
            System.out.println("3. Search Customers");
            System.out.println("4. Update Customer");
            System.out.println("5. Delete Customer");
            System.out.println("6. Back to Main Menu");
            
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    viewAllCustomers();
                    break;
                case 3:
                    searchCustomers();
                    break;
                case 4:
                    updateCustomer();
                    break;
                case 5:
                    deleteCustomer();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void manageOrders() {
        while (true) {
            System.out.println("\n=== Order Management ===");
            System.out.println("1. Create Order");
            System.out.println("2. Add Item to Order");
            System.out.println("3. View All Orders");
            System.out.println("4. View Order Details");
            System.out.println("5. Update Order Status");
            System.out.println("6. Cancel Order");
            System.out.println("7. Back to Main Menu");
            
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    createOrder();
                    break;
                case 2:
                    addItemToOrder();
                    break;
                case 3:
                    viewAllOrders();
                    break;
                case 4:
                    viewOrderDetails();
                    break;
                case 5:
                    updateOrderStatus();
                    break;
                case 6:
                    cancelOrder();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewReports() {
        System.out.println("\n=== Reports ===");
        System.out.println("1. Product Inventory Report");
        System.out.println("2. Customer List Report");
        System.out.println("3. Order Summary Report");
        System.out.println("4. Back to Main Menu");
        
        int choice = getIntInput("Enter your choice: ");
        
        switch (choice) {
            case 1:
                generateInventoryReport();
                break;
            case 2:
                generateCustomerReport();
                break;
            case 3:
                generateOrderReport();
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    // Product management methods
    private void addProduct() {
        System.out.println("\n=== Add Product ===");
        String name = getStringInput("Enter product name: ");
        String description = getStringInput("Enter product description: ");
        double price = getDoubleInput("Enter product price: ");
        int quantity = getIntInput("Enter initial stock quantity: ");
        String category = getStringInput("Enter product category: ");
        String sku = getStringInput("Enter product SKU: ");
        
        try {
            Product product = productService.createProduct(name, description, price, quantity, category, sku);
            System.out.println("Product created successfully: " + product.getId());
        } catch (Exception e) {
            System.out.println("Error creating product: " + e.getMessage());
        }
    }

    private void viewAllProducts() {
        System.out.println("\n=== All Products ===");
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No products found.");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    private void searchProducts() {
        System.out.println("\n=== Search Products ===");
        String searchTerm = getStringInput("Enter search term: ");
        List<Product> products = productService.searchProductsByName(searchTerm);
        if (products.isEmpty()) {
            System.out.println("No products found matching: " + searchTerm);
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    private void updateProductStock() {
        System.out.println("\n=== Update Product Stock ===");
        String productId = getStringInput("Enter product ID: ");
        int newQuantity = getIntInput("Enter new stock quantity: ");
        
        boolean success = productService.updateProductStock(productId, newQuantity);
        if (success) {
            System.out.println("Stock updated successfully.");
        } else {
            System.out.println("Failed to update stock. Product not found.");
        }
    }

    private void deleteProduct() {
        System.out.println("\n=== Delete Product ===");
        String productId = getStringInput("Enter product ID: ");
        
        boolean success = productService.deleteProduct(productId);
        if (success) {
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Failed to delete product. Product not found.");
        }
    }

    // Customer management methods
    private void addCustomer() {
        System.out.println("\n=== Add Customer ===");
        String name = getStringInput("Enter customer name: ");
        String email = getStringInput("Enter customer email: ");
        String phone = getStringInput("Enter customer phone: ");
        String address = getStringInput("Enter customer address: ");
        
        try {
            Customer customer = customerService.createCustomer(name, email, phone, address);
            System.out.println("Customer created successfully: " + customer.getId());
        } catch (Exception e) {
            System.out.println("Error creating customer: " + e.getMessage());
        }
    }

    private void viewAllCustomers() {
        System.out.println("\n=== All Customers ===");
        List<Customer> customers = customerService.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        }
    }

    private void searchCustomers() {
        System.out.println("\n=== Search Customers ===");
        String searchTerm = getStringInput("Enter search term: ");
        List<Customer> customers = customerService.searchCustomersByName(searchTerm);
        if (customers.isEmpty()) {
            System.out.println("No customers found matching: " + searchTerm);
        } else {
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        }
    }

    private void updateCustomer() {
        System.out.println("\n=== Update Customer ===");
        String customerId = getStringInput("Enter customer ID: ");
        String name = getStringInput("Enter new name: ");
        String email = getStringInput("Enter new email: ");
        String phone = getStringInput("Enter new phone: ");
        String address = getStringInput("Enter new address: ");
        
        boolean success = customerService.updateCustomer(customerId, name, email, phone, address);
        if (success) {
            System.out.println("Customer updated successfully.");
        } else {
            System.out.println("Failed to update customer. Customer not found.");
        }
    }

    private void deleteCustomer() {
        System.out.println("\n=== Delete Customer ===");
        String customerId = getStringInput("Enter customer ID: ");
        
        boolean success = customerService.deleteCustomer(customerId);
        if (success) {
            System.out.println("Customer deleted successfully.");
        } else {
            System.out.println("Failed to delete customer. Customer not found.");
        }
    }

    // Order management methods
    private void createOrder() {
        System.out.println("\n=== Create Order ===");
        String customerId = getStringInput("Enter customer ID: ");
        
        try {
            Order order = orderService.createOrder(customerId);
            System.out.println("Order created successfully: " + order.getId());
        } catch (Exception e) {
            System.out.println("Error creating order: " + e.getMessage());
        }
    }

    private void addItemToOrder() {
        System.out.println("\n=== Add Item to Order ===");
        String orderId = getStringInput("Enter order ID: ");
        String productId = getStringInput("Enter product ID: ");
        int quantity = getIntInput("Enter quantity: ");
        
        boolean success = orderService.addItemToOrder(orderId, productId, quantity);
        if (success) {
            System.out.println("Item added to order successfully.");
        } else {
            System.out.println("Failed to add item to order. Check order ID, product ID, and stock availability.");
        }
    }

    private void viewAllOrders() {
        System.out.println("\n=== All Orders ===");
        List<Order> orders = orderService.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            for (Order order : orders) {
                System.out.println(order);
            }
        }
    }

    private void viewOrderDetails() {
        System.out.println("\n=== Order Details ===");
        String orderId = getStringInput("Enter order ID: ");
        
        orderService.getOrderById(orderId).ifPresentOrElse(
            order -> {
                System.out.println("Order ID: " + order.getId());
                System.out.println("Customer: " + order.getCustomer().getName());
                System.out.println("Status: " + order.getStatus());
                System.out.println("Total Amount: $" + order.getTotalAmount());
                System.out.println("Order Items:");
                for (OrderItem item : order.getOrderItems()) {
                    System.out.println("  - " + item.getProduct().getName() + " x" + item.getQuantity() + " @ $" + item.getUnitPrice());
                }
            },
            () -> System.out.println("Order not found.")
        );
    }

    private void updateOrderStatus() {
        System.out.println("\n=== Update Order Status ===");
        String orderId = getStringInput("Enter order ID: ");
        System.out.println("Available statuses: PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED");
        String statusStr = getStringInput("Enter new status: ");
        
        try {
            Order.OrderStatus status = Order.OrderStatus.valueOf(statusStr.toUpperCase());
            boolean success = orderService.updateOrderStatus(orderId, status);
            if (success) {
                System.out.println("Order status updated successfully.");
            } else {
                System.out.println("Failed to update order status. Order not found.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid status. Please use one of the available statuses.");
        }
    }

    private void cancelOrder() {
        System.out.println("\n=== Cancel Order ===");
        String orderId = getStringInput("Enter order ID: ");
        
        boolean success = orderService.cancelOrder(orderId);
        if (success) {
            System.out.println("Order cancelled successfully.");
        } else {
            System.out.println("Failed to cancel order. Order not found.");
        }
    }

    // Report generation methods
    private void generateInventoryReport() {
        System.out.println("\n=== Inventory Report ===");
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No products in inventory.");
        } else {
            System.out.println("Product Inventory Summary:");
            System.out.println("Total Products: " + products.size());
            int totalItems = products.stream().mapToInt(Product::getQuantityInStock).sum();
            System.out.println("Total Items in Stock: " + totalItems);
            double totalValue = products.stream().mapToDouble(p -> p.getPrice() * p.getQuantityInStock()).sum();
            System.out.println("Total Inventory Value: $" + String.format("%.2f", totalValue));
            
            System.out.println("\nLow Stock Products (< 10 items):");
            products.stream()
                .filter(p -> p.getQuantityInStock() < 10)
                .forEach(p -> System.out.println("  - " + p.getName() + ": " + p.getQuantityInStock() + " items"));
        }
    }

    private void generateCustomerReport() {
        System.out.println("\n=== Customer Report ===");
        List<Customer> customers = customerService.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            System.out.println("Customer Summary:");
            System.out.println("Total Customers: " + customers.size());
            System.out.println("\nCustomer List:");
            for (Customer customer : customers) {
                System.out.println("  - " + customer.getName() + " (" + customer.getEmail() + ")");
            }
        }
    }

    private void generateOrderReport() {
        System.out.println("\n=== Order Report ===");
        List<Order> orders = orderService.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            System.out.println("Order Summary:");
            System.out.println("Total Orders: " + orders.size());
            
            long pendingOrders = orders.stream().filter(o -> o.getStatus() == Order.OrderStatus.PENDING).count();
            long confirmedOrders = orders.stream().filter(o -> o.getStatus() == Order.OrderStatus.CONFIRMED).count();
            long shippedOrders = orders.stream().filter(o -> o.getStatus() == Order.OrderStatus.SHIPPED).count();
            long deliveredOrders = orders.stream().filter(o -> o.getStatus() == Order.OrderStatus.DELIVERED).count();
            long cancelledOrders = orders.stream().filter(o -> o.getStatus() == Order.OrderStatus.CANCELLED).count();
            
            System.out.println("Pending: " + pendingOrders);
            System.out.println("Confirmed: " + confirmedOrders);
            System.out.println("Shipped: " + shippedOrders);
            System.out.println("Delivered: " + deliveredOrders);
            System.out.println("Cancelled: " + cancelledOrders);
            
            double totalRevenue = orders.stream()
                .filter(o -> o.getStatus() != Order.OrderStatus.CANCELLED)
                .mapToDouble(Order::getTotalAmount)
                .sum();
            System.out.println("Total Revenue: $" + String.format("%.2f", totalRevenue));
        }
    }

    // Helper methods
    private void initializeSampleData() {
        System.out.println("Initializing sample data...");
        
        // Add sample products
        Product laptop = productService.createProduct("Laptop", "High-performance laptop", 999.99, 50, "Electronics", "LAP001");
        Product mouse = productService.createProduct("Wireless Mouse", "Ergonomic wireless mouse", 29.99, 100, "Electronics", "MOU001");
        Product keyboard = productService.createProduct("Mechanical Keyboard", "RGB mechanical keyboard", 89.99, 75, "Electronics", "KEY001");
        Product book = productService.createProduct("Java Programming", "Learn Java programming", 49.99, 200, "Books", "BOK001");
        
        // Add sample customers
        Customer john = customerService.createCustomer("John Doe", "john@example.com", "555-0101", "123 Main St");
        Customer jane = customerService.createCustomer("Jane Smith", "jane@example.com", "555-0102", "456 Oak Ave");
        
        System.out.println("Sample data initialized successfully!");
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public static void main(String[] args) {
        InventoryManagementSystem system = new InventoryManagementSystem();
        system.run();
    }
} 