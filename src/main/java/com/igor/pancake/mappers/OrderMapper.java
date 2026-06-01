package com.igor.pancake.mappers;

import com.igor.pancake.dtos.OrderDto;
import com.igor.pancake.models.Order;

public class OrderMapper {
    public static OrderDto toDto(Order order){
        OrderDto dto= new OrderDto();
        dto.setDescription(order.getDescription());
        dto.setId(order.getId());
        dto.setOrderTime(order.getOrderTime());
        dto.setPancakes(order.getPancakes().stream().map(PancakeMapper::toDTO).toList());
        return dto;
    }
}
