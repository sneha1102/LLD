#!/bin/bash

echo "Compiling Java source files..."
javac -d out/production/GymChain src/main/java/DriverClass.java src/main/java/models/*.java src/main/java/service/*.java src/main/java/repository/*.java

if [ $? -eq 0 ]; then
    echo "Compilation successful! Running the program..."
    echo "================================================"
    java -cp out/production/GymChain main.java.DriverClass
    echo "================================================"
    echo "Program execution completed."
else
    echo "Compilation failed!"
    exit 1
fi

echo "Cleaning up .class files..."
find . -name "*.class" -delete
echo "Cleanup completed!" 