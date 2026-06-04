package com.igor.pancake.controllers;

import com.igor.pancake.dtos.PancakeDto;
import com.igor.pancake.dtos.PancakeRequestDto;
import com.igor.pancake.exceptions.PancakeEditException;
import com.igor.pancake.mappers.PancakeMapper;
import com.igor.pancake.models.Pancake;
import com.igor.pancake.services.IPancakeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pancakes")
public class PancakeController {
    private final IPancakeService pancakeService;

    public PancakeController(IPancakeService _pancakeService) {
        pancakeService = _pancakeService;
    }

    @PostMapping
    public PancakeDto save(@RequestBody PancakeRequestDto requestDto) {
        return PancakeMapper.toDTO(pancakeService.save(requestDto));
    }

    @DeleteMapping ("/{id:\\d+}")
    public boolean delete(@PathVariable Long id) {
        return  pancakeService.delete(id);
    }

    @PutMapping ("/{id:\\d+}")
    public PancakeDto update(@RequestBody PancakeRequestDto pancakeRequestDto, @PathVariable Long id) {
        if (pancakeService.isPancakeInOrder(id)) throw new PancakeEditException("Pancake can't be edited due to being in an order.");
        Pancake updatedPancake = pancakeService.update(pancakeRequestDto, id);
        return PancakeMapper.toDTO(updatedPancake);
    }

    @GetMapping
    public List<Long> getAll() {
        return pancakeService.getAllPancakeIds();
    }

    @GetMapping("/{id:\\d+}")
    public PancakeDto getPancake(@PathVariable Long id){
        return PancakeMapper.toDTO(pancakeService.getById(id));
    }

    @DeleteMapping("/{id:\\d+}/order")
    public PancakeDto removeFromOrder(@PathVariable Long id){
        return PancakeMapper.toDTO(pancakeService.removeFromOrder(id));
    }

    @PostMapping("/{pancakeId:\\d+}/order/{orderId:\\d+}")
    public PancakeDto moveToOrder(@PathVariable Long pancakeId, @PathVariable Long orderId){
        return PancakeMapper.toDTO(pancakeService.addToOrder(pancakeId, orderId));
    }

    @GetMapping("/unassigned")
    public List<PancakeDto> getUnassignedPancakes(){
        return pancakeService.getPancakesNotInOrder().stream().map(PancakeMapper::toDTO).toList();
    }
}
