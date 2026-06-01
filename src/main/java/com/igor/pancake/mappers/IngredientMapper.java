package com.igor.pancake.mappers;

import com.igor.pancake.models.Ingredient;
import com.igor.pancake.dtos.*;

public class IngredientMapper {
    public static IngredientDto toDTO(Ingredient i) {
        return new IngredientDto(
                i.getId(),
                i.getName(),
                i.getPrice(),
                i.getCategory()
        );
    }
    public static Ingredient toIngredientFromRequestDto(IngredientRequestDto requestDto){
        Ingredient ingredient=new Ingredient();
        ingredient.setCategory(requestDto.getCategory());
        ingredient.setPrice(requestDto.getPrice());
        ingredient.setName(requestDto.getName());
        return ingredient;
    }

    public static Ingredient toIngredientFromDto(IngredientDto ingredientDto){
        Ingredient ingredient=new Ingredient();
        ingredient.setId(ingredientDto.getId());
        ingredient.setCategory(ingredientDto.getCategory());
        ingredient.setPrice(ingredientDto.getPrice());
        ingredient.setName(ingredientDto.getName());
        return ingredient;
    }
}
