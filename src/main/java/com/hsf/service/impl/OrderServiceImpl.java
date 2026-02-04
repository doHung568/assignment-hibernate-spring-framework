package com.hsf.service.impl;

import com.hsf.dto.OrderDTO;
import com.hsf.model.Customer;
import com.hsf.model.Order;
import com.hsf.repository.CustomerRepository;
import com.hsf.repository.OrderRepository;
import com.hsf.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.customerRepository = customerRepository;
    }


    @Override
    public String createOrder(OrderDTO orderDTO){
        Order orderSave = modelMapper.map(orderDTO, Order.class);
        orderSave.setId(generateOrderId());
        orderSave.setCustomer(getCustomerByEmail(orderDTO.getEmail()));
        orderRepository.save(orderSave);
        return null;
    }

    private String generateOrderId() {
        String lastOrderId = orderRepository.findLastId();
        if (lastOrderId == null || lastOrderId.isEmpty()) {
            return "ORD001";
        }

        String numberPart = lastOrderId.substring(3);
        int nextId = Integer.parseInt(numberPart) + 1;
        return String.format("ORD%03d", nextId);
    }

    private Customer getCustomerByEmail(String email){
        return customerRepository.findCustomerByEmail(email);
    }

    @Override
    public List<OrderDTO> getAll() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDTO> orderDTOs = new ArrayList<>();
        for (Order order : orders) {
            orderDTOs.add(modelMapper.map(order, OrderDTO.class));
        }
        return orderDTOs;
    }

    @Override
    public OrderDTO getOrderDetails(String id) {
        Order order = orderRepository.findOrderById(id);
        return modelMapper.map(order, OrderDTO.class);
    }
}
