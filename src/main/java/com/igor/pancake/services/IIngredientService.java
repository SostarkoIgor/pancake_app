package com.igor.pancake.services;

import com.igor.pancake.dtos.IngredientRequestDto;
import com.igor.pancake.models.Ingredient;

import java.util.List;

public interface IIngredientService {
    public Ingredient save(IngredientRequestDto ingredient);
    public Ingredient update(IngredientRequestDto ingredient, Long id);
    public boolean delete(Long id);
    public List<Ingredient> getAll();
    public Ingredient findMostUsedHealthyLastMonth();
    public Ingredient findMostUsedLastMonth();
}
