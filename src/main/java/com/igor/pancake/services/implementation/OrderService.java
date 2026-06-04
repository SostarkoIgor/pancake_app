package com.igor.pancake.services.implementation;

import com.igor.pancake.dtos.OrderRequestDto;
import com.igor.pancake.exceptions.InvalidOrderException;
import com.igor.pancake.exceptions.InvalidPancakeException;
import com.igor.pancake.exceptions.ResourceNotFoundException;
import com.igor.pancake.models.Order;
import com.igor.pancake.models.Pancake;
import com.igor.pancake.repository.OrderRepository;
import com.igor.pancake.repository.PancakeRepository;
import com.igor.pancake.services.IOrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final PancakeRepository pancakeRepository;

    public OrderService(OrderRepository orderRepository, PancakeRepository pancakeRepository) {
        this.orderRepository = orderRepository;
        this.pancakeRepository = pancakeRepository;
    }

    @Override
    public Order createOrder(OrderRequestDto dto) {
        if (dto.getPancakeIds().isEmpty()) {
            throw new InvalidOrderException("Order can not have 0 pancakes");
        }
        List<Pancake> pancakes = pancakeRepository.findAllById(dto.getPancakeIds());
        if (pancakes.isEmpty()) throw new InvalidOrderException("Order can not have 0 pancakes");
        boolean alreadyInOrder = pancakes.stream().anyMatch(p -> p.getOrder() != null);
        if (alreadyInOrder) {
            throw new InvalidOrderException("Pancake already belongs to an order.");
        }
        if (!arePancakesValid(dto.getPancakeIds())) {
            throw new InvalidPancakeException("Invalid pancakes.");
        }
        Order order = new Order();
        order.setOrderTime(LocalDateTime.now());
        order.setDescription(dto.getDescription());
        order.setPancakes(pancakes);
        pancakes.forEach(p -> p.setOrder(order));
        return orderRepository.save(order);
    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Order not found"));
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public boolean arePancakesValid(List<Long> pancakeIds){
        List<Pancake> pancakes = pancakeRepository.findAllById(pancakeIds);
        return pancakes.stream().allMatch(Pancake::isValid);
    }
}
