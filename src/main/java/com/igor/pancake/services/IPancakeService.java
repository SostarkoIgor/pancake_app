package com.igor.pancake.services;

import java.util.List;

import com.igor.pancake.dtos.PancakeRequestDto;
import com.igor.pancake.models.Pancake;

public interface IPancakeService {
    public Pancake save(PancakeRequestDto pancake);
    public Pancake update(PancakeRequestDto pancake, Long id);
    public boolean delete(Long id);
    public List<Long> getAllPancakeIds();
    public Pancake getById(Long id);
    public boolean isPancakeInOrder(Long id);
    public Pancake removeFromOrder(Long pancakeId);
    public Pancake addToOrder(Long pancakeId, Long orderId);
    public List<Pancake> getPancakesNotInOrder();
}
