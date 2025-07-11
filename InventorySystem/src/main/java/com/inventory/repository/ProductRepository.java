package com.inventory.repository;

import com.inventory.models.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(String id);
    List<Product> findAll();
    List<Product> findByCategory(String category);
    List<Product> findByNameContaining(String name);
    boolean deleteById(String id);
    boolean updateStock(String productId, int newQuantity);
} 