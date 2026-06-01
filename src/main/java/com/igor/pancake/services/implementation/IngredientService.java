package com.igor.pancake.services.implementation;

import com.igor.pancake.dtos.IngredientRequestDto;
import com.igor.pancake.exceptions.ResourceNotFoundException;
import com.igor.pancake.mappers.IngredientMapper;
import com.igor.pancake.models.Ingredient;
import com.igor.pancake.repository.IngredientRepository;
import com.igor.pancake.services.IIngredientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService implements IIngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository _ingredientRepository) {
        ingredientRepository = _ingredientRepository;
    }


    @Override
    public Ingredient save(IngredientRequestDto ingredient) {
        return ingredientRepository.save(IngredientMapper.toIngredientFromRequestDto(ingredient));
    }

    @Override
    public Ingredient update(IngredientRequestDto ingredient, Long id) {
        if (ingredientRepository.existsById(id)) {
            Ingredient ingredient_=ingredientRepository.findById(id).get();
            if (ingredient.getName() != null) ingredient_.setName(ingredient.getName());
            if (ingredient.getPrice() != null) ingredient_.setPrice(ingredient.getPrice());
            if (ingredient.getCategory() != null) ingredient_.setCategory(ingredient.getCategory());
            return ingredientRepository.save(ingredient_);
        }
        throw new ResourceNotFoundException("Ingredient with given id not found.");
    }

    @Override
    public boolean delete(Long id) {
        if (ingredientRepository.existsById(id)) {
            ingredientRepository.deleteById(id);
            return true;
        }
        else throw new ResourceNotFoundException("Ingredient with given id not found.");
    }

    @Override
    public List<Ingredient> getAll() {
        return ingredientRepository.findAll().stream().toList();
    }
}
