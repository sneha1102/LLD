package com.inventory.repository;

import com.inventory.models.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Customer save(Customer customer);
    Optional<Customer> findById(String id);
    List<Customer> findAll();
    Optional<Customer> findByEmail(String email);
    List<Customer> findByNameContaining(String name);
    boolean deleteById(String id);
} 