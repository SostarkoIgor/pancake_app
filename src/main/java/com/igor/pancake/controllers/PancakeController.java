package com.igor.pancake.controllers;

import com.igor.pancake.dtos.IngredientRequestDto;
import com.igor.pancake.dtos.IngredientDto;
import com.igor.pancake.dtos.PancakeDto;
import com.igor.pancake.dtos.PancakeRequestDto;
import com.igor.pancake.mappers.IngredientMapper;
import com.igor.pancake.mappers.PancakeMapper;
import com.igor.pancake.models.Ingredient;
import com.igor.pancake.models.Pancake;
import com.igor.pancake.services.IIngredientService;
import com.igor.pancake.services.IPancakeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

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

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return  pancakeService.delete(id);
    }

    @PostMapping("/{id}")
    public PancakeDto update(@RequestBody PancakeRequestDto pancakeRequestDto, @PathVariable Long id) {
        Pancake updatedPancake = pancakeService.update(pancakeRequestDto, id);
        return PancakeMapper.toDTO(updatedPancake);
    }

    @GetMapping
    public List<Long> getAll() {
        return pancakeService.getAllPancakeIds();
    }

    @GetMapping("/{id}")
    public PancakeDto getPancake(@PathVariable Long id){
        return PancakeMapper.toDTO(pancakeService.getById(id));
    }
}
