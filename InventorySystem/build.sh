#!/bin/bash

# Inventory Management System Build Script

echo "=== Inventory Management System Build Script ==="

# Create bin directory if it doesn't exist
mkdir -p bin

# Compile all Java files
echo "Compiling Java files..."
javac -d bin src/main/java/com/inventory/*.java src/main/java/com/inventory/*/*.java

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo ""
    echo "Available run options:"
    echo "  ./build.sh run          - Run the main application"
    echo "  ./build.sh test         - Run the basic test"
    echo "  ./build.sh concurrent   - Run the concurrent test"
    echo ""
    echo "Or run manually:"
    echo "  java -cp bin com.inventory.InventoryManagementSystem"
    echo "  java -cp bin com.inventory.TestSystem"
    echo "  java -cp bin com.inventory.ConcurrentTestSystem"
    
    # If run argument is provided, execute the appropriate application
    case "$1" in
        "run")
            echo ""
            echo "Starting Inventory Management System..."
            java -cp bin com.inventory.InventoryManagementSystem
            ;;
        "test")
            echo ""
            echo "Running basic test..."
            java -cp bin com.inventory.TestSystem
            ;;
        "concurrent")
            echo ""
            echo "Running concurrent test..."
            java -cp bin com.inventory.ConcurrentTestSystem
            ;;
    esac
else
    echo "Compilation failed!"
    exit 1
fi 