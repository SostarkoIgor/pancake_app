package com.igor.pancake.controllers;

import com.igor.pancake.dtos.IngredientRequestDto;
import com.igor.pancake.dtos.IngredientDto;
import com.igor.pancake.mappers.IngredientMapper;
import com.igor.pancake.models.Ingredient;
import com.igor.pancake.services.IIngredientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("ingredients")
public class IngredientController {
    private final IIngredientService ingredientService;

    public IngredientController(IIngredientService _ingredientService) {
        ingredientService = _ingredientService;
    }

    @PostMapping
    public IngredientDto save(@RequestBody IngredientRequestDto ingredientDto) {
        return IngredientMapper.toDTO(ingredientService.save(ingredientDto));
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return  ingredientService.delete(id);
    }

    @PostMapping("/{id}")
    public IngredientDto update(@RequestBody IngredientRequestDto ingredientDto, @PathVariable Long id) {
        Ingredient updatedIngredient = ingredientService.update(ingredientDto, id);
        return IngredientMapper.toDTO(updatedIngredient);
    }

    @GetMapping
    public List<IngredientDto> getAll() {
        return ingredientService.getAll().stream().map(IngredientMapper::toDTO).toList();
    }
}
