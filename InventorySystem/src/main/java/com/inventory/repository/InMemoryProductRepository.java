package com.inventory.repository;

import com.inventory.models.Product;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryProductRepository implements ProductRepository {
    private final Map<String, Product> products = new ConcurrentHashMap<>();
    private final Object stockUpdateLock = new Object();

    @Override
    public Product save(Product product) {
        products.put(product.getId(), product);
        return product;
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public List<Product> findByCategory(String category) {
        return products.values().stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findByNameContaining(String name) {
        return products.values().stream()
                .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteById(String id) {
        return products.remove(id) != null;
    }

    @Override
    public boolean updateStock(String productId, int newQuantity) {
        synchronized (stockUpdateLock) {
            Product product = products.get(productId);
            if (product != null) {
                product.setQuantityInStock(newQuantity);
                return true;
            }
            return false;
        }
    }

    // Thread-safe method to reserve stock atomically
    public boolean reserveStock(String productId, int quantity) {
        synchronized (stockUpdateLock) {
            Product product = products.get(productId);
            if (product != null && product.getQuantityInStock() >= quantity) {
                product.setQuantityInStock(product.getQuantityInStock() - quantity);
                return true;
            }
            return false;
        }
    }

    // Thread-safe method to restore stock atomically
    public boolean restoreStock(String productId, int quantity) {
        synchronized (stockUpdateLock) {
            Product product = products.get(productId);
            if (product != null) {
                product.setQuantityInStock(product.getQuantityInStock() + quantity);
                return true;
            }
            return false;
        }
    }
} 