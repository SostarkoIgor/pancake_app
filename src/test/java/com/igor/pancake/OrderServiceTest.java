package com.igor.pancake;
import com.igor.pancake.dtos.OrderRequestDto;
import com.igor.pancake.exceptions.InvalidOrderException;
import com.igor.pancake.models.Category;
import com.igor.pancake.models.Ingredient;
import com.igor.pancake.models.Order;
import com.igor.pancake.models.Pancake;
import com.igor.pancake.repository.OrderRepository;
import com.igor.pancake.repository.PancakeRepository;
import com.igor.pancake.services.implementation.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private PancakeRepository pancakeRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void shouldThrowWhenEmptyPancakes() {
        OrderRequestDto dto = new OrderRequestDto();
        dto.setPancakeIds(List.of());

        assertThrows(InvalidOrderException.class,
                () -> orderService.createOrder(dto));
    }

    @Test
    void shouldThrowWhenPancakeAlreadyInOrder() {
        Pancake p = new Pancake();
        p.setOrder(new Order());

        when(pancakeRepository.findAllById(List.of(1L)))
                .thenReturn(List.of(p));

        OrderRequestDto dto = new OrderRequestDto();
        dto.setPancakeIds(List.of(1L));

        assertThrows(InvalidOrderException.class,
                () -> orderService.createOrder(dto));
    }

    @Test
    void shouldCreateOrderSuccessfully() {
        Pancake p = new Pancake();
        p.setOrder(null);
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        ingredient1.setCategory(Category.baza);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(1L);
        ingredient2.setCategory(Category.nadjev);
        p.setIngredients(List.of(ingredient1,ingredient2));

        when(pancakeRepository.findAllById(List.of(1L)))
                .thenReturn(List.of(p));

        when(orderRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        OrderRequestDto dto = new OrderRequestDto();
        dto.setPancakeIds(List.of(1L));

        Order result = orderService.createOrder(dto);

        assertNotNull(result);
        verify(orderRepository).save(any());
    }
}
