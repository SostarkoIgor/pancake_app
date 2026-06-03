package com.igor.pancake.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportDto {
    private IngredientDto mostUsedHealthy;
    private IngredientDto mostUsed;
}
