package com.igor.pancake.models;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Ingredient {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private BigDecimal price;

    @Enumerated(jakarta.persistence.EnumType.STRING)
    private Category category;
}
