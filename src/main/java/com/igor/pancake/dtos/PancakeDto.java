package com.igor.pancake.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PancakeDto {
    private Long id;
    private List<IngredientDto> ingredients;
}