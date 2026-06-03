package com.igor.pancake.dtos;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PancakeDto {
    private Long id;
    private BigDecimal price;
    private List<IngredientDto> ingredients;
}