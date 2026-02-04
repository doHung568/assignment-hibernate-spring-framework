package com.hsf.service.impl;

import com.hsf.dto.OrderDTO;
import com.hsf.model.Customer;
import com.hsf.repository.CustomerRepository;
import com.hsf.repository.OrderRepository;
import com.hsf.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository ,ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean checkExistByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    @Override
    public boolean checkExistByPhoneNumber(String phoneNumber) {
        return customerRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public void saveNewCustomer(OrderDTO orderDTO) {
        Customer customer = modelMapper.map(orderDTO, Customer.class);
        customerRepository.save(customer);
    }
}
