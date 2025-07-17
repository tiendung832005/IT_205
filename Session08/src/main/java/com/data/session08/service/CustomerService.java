package com.data.session08.service;

import com.data.session08.entity.Customer;
import com.data.session08.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer addCustomer(Customer customer) {
        customer.setCreatedAt(LocalDateTime.now());
        customer.setStatus(true);
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Customer not found with id: " + id));
        customer.setFullName(updatedCustomer.getFullName());
        customer.setPhone(updatedCustomer.getPhone());
        customer.setEmail(updatedCustomer.getEmail());
        customer.setNumberOfPayments(updatedCustomer.getNumberOfPayments());
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Customer not found with id: " + id));
        customer.setStatus(false); // Soft delete
        customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}