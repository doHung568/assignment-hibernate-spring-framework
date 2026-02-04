package com.hsf.service;

import com.hsf.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    String createOrder(OrderDTO orderDTO);

    List<OrderDTO> getAll();

    OrderDTO getOrderDetails(String id);
}
