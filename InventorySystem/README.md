# Inventory Management System

A comprehensive inventory and order management system built in Java without any external frameworks. The system uses in-memory storage and follows a clean architecture pattern with Models, Services, and Repository layers. **The system is designed to handle concurrent users with full thread safety.**

## Project Structure

```
src/main/java/com/inventory/
├── models/                 # Data models
│   ├── Product.java       # Product entity
│   ├── Customer.java      # Customer entity
│   ├── Order.java         # Order entity
│   └── OrderItem.java     # Order item entity
├── repository/            # Data access layer (Thread-safe)
│   ├── ProductRepository.java
│   ├── InMemoryProductRepository.java
│   ├── CustomerRepository.java
│   ├── InMemoryCustomerRepository.java
│   ├── OrderRepository.java
│   └── InMemoryOrderRepository.java
├── service/              # Business logic layer (Thread-safe)
│   ├── ProductService.java
│   ├── CustomerService.java
│   └── OrderService.java
├── InventoryManagementSystem.java  # Main driver class
├── TestSystem.java                 # Basic functionality test
└── ConcurrentTestSystem.java       # Concurrent user test
```

## Thread Safety & Concurrent User Handling

### Repository Layer Thread Safety
- **ConcurrentHashMap**: All in-memory repositories use `ConcurrentHashMap` for thread-safe data storage
- **Atomic Operations**: Stock updates use synchronized blocks to prevent race conditions
- **Lock Granularity**: Separate locks for different operations (stock updates, order status changes)

### Service Layer Thread Safety
- **Synchronized Business Operations**: Critical operations like adding items to orders are synchronized
- **Atomic Stock Management**: Stock reservation and restoration are atomic operations
- **Order Processing**: Order creation and modification are thread-safe

### Key Thread-Safe Features
- **Stock Reservation**: Multiple users can't oversell the same product
- **Order Management**: Concurrent order creation and modification
- **Data Consistency**: No data corruption under concurrent access
- **Deadlock Prevention**: Careful lock ordering and minimal lock scope

## Features

### Product Management
- Add new products with name, description, price, stock quantity, category, and SKU
- View all products
- Search products by name
- Update product stock levels (thread-safe)
- Delete products
- Automatic stock reservation when items are added to orders

### Customer Management
- Add new customers with name, email, phone, and address
- View all customers
- Search customers by name
- Update customer information
- Delete customers
- Email uniqueness validation

### Order Management
- Create new orders for customers (thread-safe)
- Add items to orders with quantity validation and atomic stock reservation
- View all orders and order details
- Update order status (PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED)
- Cancel orders with automatic stock restoration
- Calculate order totals

### Reporting
- Inventory reports with stock levels and values
- Customer reports
- Order summary reports with status breakdowns and revenue calculations

## Architecture

### Models Layer
- **Product**: Represents inventory items with stock management
- **Customer**: Represents customer information
- **Order**: Represents customer orders with status tracking
- **OrderItem**: Represents individual items within an order

### Repository Layer (Thread-Safe)
- Interface-based design for data access
- In-memory implementations using `ConcurrentHashMap` storage
- CRUD operations for all entities
- Search and filtering capabilities
- Atomic stock operations with proper synchronization

### Service Layer (Thread-Safe)
- Business logic implementation with synchronization
- Validation and error handling
- Thread-safe stock management and reservation
- Order processing logic with atomic operations

### Driver Class
- Console-based user interface
- Menu-driven navigation
- Sample data initialization
- Input validation and error handling

## How to Run

1. **Compile the project:**
   ```bash
   javac -d bin src/main/java/com/inventory/*.java src/main/java/com/inventory/*/*.java
   ```

2. **Run options:**
   ```bash
   # Main application
   java -cp bin com.inventory.InventoryManagementSystem
   
   # Basic functionality test
   java -cp bin com.inventory.TestSystem
   
   # Concurrent user test (10 threads, 50 operations each)
   java -cp bin com.inventory.ConcurrentTestSystem
   ```

3. **Using the build script:**
   ```bash
   ./build.sh run          # Run main application
   ./build.sh test         # Run basic test
   ./build.sh concurrent   # Run concurrent test
   ```

## Concurrent User Testing

The system includes a comprehensive concurrent test that:
- **Simulates 10 concurrent users** performing 50 operations each
- **Tests stock reservation** under high concurrency
- **Verifies data consistency** after concurrent operations
- **Checks for race conditions** in order processing
- **Validates final state** to ensure no data corruption

### Test Operations
1. **Order Creation**: Multiple users creating orders simultaneously
2. **Stock Updates**: Concurrent stock modifications
3. **Item Addition**: Adding items to orders with stock validation
4. **Order Status Updates**: Concurrent status changes
5. **Product Searches**: Read operations under load

## Usage Examples

### Sample Workflow

1. **Start the application** - The system will initialize with sample data
2. **Manage Products** - Add, view, or update product information
3. **Manage Customers** - Add customer information
4. **Create Orders** - Create orders for customers and add items
5. **View Reports** - Generate various business reports

### Sample Data

The system comes pre-loaded with sample data:
- **Products**: Laptop, Wireless Mouse, Mechanical Keyboard, Java Programming Book
- **Customers**: John Doe, Jane Smith

## Key Features

### Stock Management (Thread-Safe)
- Automatic stock reservation when items are added to orders
- Stock restoration when orders are cancelled
- Low stock alerts in inventory reports
- Real-time stock validation under concurrent access
- **No overselling**: Multiple users cannot purchase more than available stock

### Order Processing (Thread-Safe)
- Multi-step order status workflow
- Automatic total calculation
- Item-level order management
- Customer order history
- **Atomic operations**: Order modifications are thread-safe

### Data Validation
- Email uniqueness for customers
- Stock availability checks under concurrent access
- Input validation for all user inputs
- Error handling with meaningful messages

## Technical Details

- **Language**: Java 8+
- **Storage**: In-memory (ConcurrentHashMap-based)
- **Architecture**: Layered (Models-Service-Repository)
- **Thread Safety**: Full concurrent user support
- **UI**: Console-based with menu navigation
- **Dependencies**: None (pure Java)

## Thread Safety Implementation Details

### Repository Thread Safety
```java
// ConcurrentHashMap for thread-safe storage
private final Map<String, Product> products = new ConcurrentHashMap<>();

// Synchronized stock operations
public boolean reserveStock(String productId, int quantity) {
    synchronized (stockUpdateLock) {
        // Atomic stock check and update
    }
}
```

### Service Layer Thread Safety
```java
// Synchronized business operations
public boolean addItemToOrder(String orderId, String productId, int quantity) {
    synchronized (orderOperationLock) {
        // Atomic order modification with stock reservation
    }
}
```

## Extensibility

The system is designed to be easily extensible:
- Repository interfaces allow for different storage implementations (database, file-based, etc.)
- Service layer can be extended with additional business logic
- New models can be added following the existing pattern
- UI can be replaced with web interface or GUI
- **Thread safety patterns** can be applied to new components

## Future Enhancements

- Database persistence with connection pooling
- Web-based user interface with session management
- REST API endpoints with proper concurrency control
- Advanced reporting and analytics
- User authentication and authorization
- Email notifications
- Barcode/QR code integration
- Multi-warehouse support
- **Distributed locking** for multi-instance deployments 