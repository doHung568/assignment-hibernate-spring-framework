package com.hsf.service;

import com.hsf.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    OrderDTO createOrder(OrderDTO orderDTO) throws Exception;

    List<OrderDTO> getAll();

    OrderDTO getOrderDetails(String id);
}
