package com.igor.pancake.mappers;

import com.igor.pancake.models.Ingredient;
import com.igor.pancake.dtos.*;

public class IngredientMapper {
    public static IngredientDto toDTO(Ingredient i) {
        if (i==null) throw new IllegalStateException("Ingredient was null");
        return new IngredientDto(
                i.getId(),
                i.getName(),
                i.getPrice(),
                i.isHealthy(),
                i.getCategory()
        );
    }
    public static Ingredient toIngredientFromRequestDto(IngredientRequestDto requestDto){
        if (requestDto==null) throw new IllegalStateException("Dto was null");
        Ingredient ingredient=new Ingredient();
        ingredient.setCategory(requestDto.getCategory());
        ingredient.setPrice(requestDto.getPrice());
        ingredient.setName(requestDto.getName());
        ingredient.setHealthy(requestDto.isHealthy());
        return ingredient;
    }
}
