package com.inventory.service;

import com.inventory.models.Customer;
import com.inventory.repository.CustomerRepository;
import java.util.List;
import java.util.Optional;

public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(String name, String email, String phone, String address) {
        // Check if customer with same email already exists
        Optional<Customer> existingCustomer = customerRepository.findByEmail(email);
        if (existingCustomer.isPresent()) {
            throw new IllegalArgumentException("Customer with email " + email + " already exists");
        }

        Customer customer = new Customer(name, email, phone, address);
        return customerRepository.save(customer);
    }

    public Optional<Customer> getCustomerById(String id) {
        return customerRepository.findById(id);
    }

    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public List<Customer> searchCustomersByName(String name) {
        return customerRepository.findByNameContaining(name);
    }

    public boolean updateCustomer(String id, String name, String email, String phone, String address) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
            Customer customer = existingCustomer.get();
            customer.setName(name);
            customer.setEmail(email);
            customer.setPhone(phone);
            customer.setAddress(address);
            customerRepository.save(customer);
            return true;
        }
        return false;
    }

    public boolean deleteCustomer(String id) {
        return customerRepository.deleteById(id);
    }
} 