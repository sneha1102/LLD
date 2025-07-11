package com.inventory.service;

import com.inventory.models.Product;
import com.inventory.repository.ProductRepository;
import com.inventory.repository.InMemoryProductRepository;
import java.util.List;
import java.util.Optional;

public class ProductService {
    private final ProductRepository productRepository;
    private final Object stockOperationLock = new Object();

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(String name, String description, double price, 
                               int quantityInStock, String category, String sku) {
        Product product = new Product(name, description, price, quantityInStock, category, sku);
        return productRepository.save(product);
    }

    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> searchProductsByName(String name) {
        return productRepository.findByNameContaining(name);
    }

    public boolean updateProductStock(String productId, int newQuantity) {
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        }
        return productRepository.updateStock(productId, newQuantity);
    }

    public boolean deleteProduct(String id) {
        return productRepository.deleteById(id);
    }

    public boolean isProductInStock(String productId, int requestedQuantity) {
        Optional<Product> product = productRepository.findById(productId);
        return product.isPresent() && product.get().getQuantityInStock() >= requestedQuantity;
    }

    public boolean reserveStock(String productId, int quantity) {
        synchronized (stockOperationLock) {
            if (productRepository instanceof InMemoryProductRepository) {
                return ((InMemoryProductRepository) productRepository).reserveStock(productId, quantity);
            } else {
                // Fallback for other repository implementations
                Optional<Product> product = productRepository.findById(productId);
                if (product.isPresent() && product.get().getQuantityInStock() >= quantity) {
                    int newStock = product.get().getQuantityInStock() - quantity;
                    return productRepository.updateStock(productId, newStock);
                }
                return false;
            }
        }
    }

    public boolean restoreStock(String productId, int quantity) {
        synchronized (stockOperationLock) {
            if (productRepository instanceof InMemoryProductRepository) {
                return ((InMemoryProductRepository) productRepository).restoreStock(productId, quantity);
            } else {
                // Fallback for other repository implementations
                Optional<Product> product = productRepository.findById(productId);
                if (product.isPresent()) {
                    int newStock = product.get().getQuantityInStock() + quantity;
                    return productRepository.updateStock(productId, newStock);
                }
                return false;
            }
        }
    }
} 