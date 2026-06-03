package com.igor.pancake.repository;

import com.igor.pancake.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    @Query(value = """
    SELECT i.*
    FROM ingredient i
    JOIN pancake_ingredient pi ON pi.ingredient_id = i.id
    JOIN pancake p ON p.id = pi.pancake_id
    JOIN orders o ON o.id = p.order_id
    WHERE o.order_time >= :from AND i.healthy = true
    GROUP BY i.Id
    ORDER BY COUNT(*) DESC
    LIMIT 1
""", nativeQuery = true)
    Ingredient findMostUsedHealthyIngredient(LocalDateTime from);

    @Query(value = """
    SELECT i.*
    FROM ingredient i
    JOIN pancake_ingredient pi ON pi.ingredient_id = i.id
    JOIN pancake p ON p.id = pi.pancake_id
    JOIN orders o ON o.id = p.order_id
    WHERE o.order_time >= :from
    GROUP BY i.Id
    ORDER BY COUNT(*) DESC
    LIMIT 1
""", nativeQuery = true)
    Ingredient findMostUsedIngredient(LocalDateTime from);
}
