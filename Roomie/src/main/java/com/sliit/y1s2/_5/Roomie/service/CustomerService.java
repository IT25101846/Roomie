package com.sliit.y1s2._5.Roomie.service;

import com.sliit.y1s2._5.Roomie.model.Customer;
import com.sliit.y1s2._5.Roomie.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /** Register a new customer */
    public String register(Customer customer) {
        // Check duplicate email
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            return "ERROR: Email already registered.";
        }
        customer.setCustomerId(customerRepository.generateId());
        customer.setCreatedAt(LocalDate.now().toString());
        customerRepository.save(customer);
        return "SUCCESS";
    }

    /** Login - returns customer object if credentials match */
    public Optional<Customer> login(String email, String password) {
        return customerRepository.findByEmail(email)
                .filter(c -> c.getPassword().equals(password));
    }

    /** Get all customers */
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    /** Get customer by ID */
    public Optional<Customer> getCustomerById(String id) {
        return customerRepository.findById(id);
    }

    /** Update customer profile */
    public String updateProfile(Customer customer) {
        if (customerRepository.findById(customer.getCustomerId()).isEmpty()) {
            return "ERROR: Customer not found.";
        }
        customerRepository.update(customer);
        return "SUCCESS";
    }

    /** Delete a customer */
    public String deleteCustomer(String customerId) {
        if (customerRepository.findById(customerId).isEmpty()) {
            return "ERROR: Customer not found.";
        }
        customerRepository.delete(customerId);
        return "SUCCESS";
    }
}
