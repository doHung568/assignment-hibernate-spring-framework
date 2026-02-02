package com.hsf.service.impl;

import com.hsf.dto.OrderDTO;
import com.hsf.model.Order;
import com.hsf.repository.OrderRepository;
import com.hsf.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) throws Exception {
        // check exist by email
        if (orderRepository.existsByEmail(orderDTO.getEmail())) {
            throw new Exception("Email already exist " + orderDTO.getEmail());
        }

        Order orderSave = modelMapper.map(orderDTO, Order.class);
        orderSave.setOrderId(generateOrderId());
        Order order = orderRepository.save(orderSave);
        return modelMapper.map(order, OrderDTO.class);
    }

    private String generateOrderId() {
        String lastOrderId = orderRepository.findLastOrderId();
        if (lastOrderId == null || lastOrderId.isEmpty()) {
            return "ORD001";
        }

        String numberPart = lastOrderId.substring(3);
        int nextId = Integer.parseInt(numberPart) + 1;
        return String.format("ORD%03d", nextId);
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
        Order order = orderRepository.findOrderByOrderId(id);
        return modelMapper.map(order, OrderDTO.class);
    }

}
