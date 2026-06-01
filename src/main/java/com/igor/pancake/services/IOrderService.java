package com.igor.pancake.services;

import com.igor.pancake.dtos.OrderRequestDto;
import com.igor.pancake.models.Order;

public interface IOrderService {
    public Order createOrder(OrderRequestDto dto);
    public Order getOrder(Long id);
}
