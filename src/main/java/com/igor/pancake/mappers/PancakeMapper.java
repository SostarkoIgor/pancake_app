package com.igor.pancake.mappers;

import com.igor.pancake.dtos.*;
import com.igor.pancake.models.Pancake;

import java.math.BigDecimal;

public class PancakeMapper {
    public static PancakeDto toDTO(Pancake i) {
        if (i==null) throw new IllegalStateException("Pancake was null");
        return new PancakeDto(
                i.getId(),
                i.getPrice(),
                i.getIngredients().stream().map(IngredientMapper::toDTO).toList(),
                i.isHealthy()
        );
    }

    public static PancakeDto toDTO(Pancake i, double discount) {
        if (i==null) throw new IllegalStateException("Pancake was null");
        return new PancakeDto(
                i.getId(),
                i.getPrice().multiply(BigDecimal.valueOf(discount)),
                i.getIngredients().stream().map(IngredientMapper::toDTO).toList(),
                i.isHealthy()
        );
    }

}
