package com.hsf.service.impl;

import com.hsf.repository.CustomerRepository;
import com.hsf.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public boolean checkExistByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    @Override
    public boolean checkExistByPhoneNumber(String phoneNumber) {
        return customerRepository.existsByPhoneNumber(phoneNumber);
    }
}
