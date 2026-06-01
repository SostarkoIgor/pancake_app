package com.igor.pancake.mappers;

import com.igor.pancake.dtos.*;
import com.igor.pancake.models.Pancake;

public class PancakeMapper {
    public static PancakeDto toDTO(Pancake i) {
        return new PancakeDto(
                i.getId(),
                i.getIngredients().stream().map(IngredientMapper::toDTO).toList()
        );
    }

}
