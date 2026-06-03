package com.igor.pancake.controllers;

import com.igor.pancake.dtos.OrderDto;
import com.igor.pancake.dtos.OrderRequestDto;
import com.igor.pancake.services.IOrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("orders")
public class OrderController {
    private final IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderDto save(@RequestBody OrderRequestDto dto){
        return orderService.createOrder(dto).getOrderDtoWithDiscount();
    }

    @GetMapping("{id}")
    public OrderDto get(@PathVariable Long id){
        return orderService.getOrder(id).getOrderDtoWithDiscount();
    }
}
