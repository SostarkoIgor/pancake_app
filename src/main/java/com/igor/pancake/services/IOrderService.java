package com.igor.pancake.services;

import com.igor.pancake.dtos.OrderRequestDto;
import com.igor.pancake.models.Order;

import java.util.List;

public interface IOrderService {
    public Order createOrder(OrderRequestDto dto);
    public Order getOrder(Long id);
    public List<Order> getOrders();
}
