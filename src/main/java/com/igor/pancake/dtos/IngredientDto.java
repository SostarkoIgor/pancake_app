package com.igor.pancake.dtos;

import com.igor.pancake.models.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class IngredientDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private boolean healthy;
    private Category category;

}
