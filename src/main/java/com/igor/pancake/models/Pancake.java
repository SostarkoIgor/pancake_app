package com.igor.pancake.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Pancake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "pancake_ingredient",
            joinColumns = @JoinColumn(name = "pancake_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public boolean isValid() {
        long baseCount = this.getIngredients().stream()
                .filter(i -> i.getCategory() == Category.baza)
                .count();
        long fillingCount = this.getIngredients().stream()
                .filter(i -> i.getCategory() == Category.nadjev)
                .count();
        return baseCount == 1 && fillingCount >= 1;
    }
}
