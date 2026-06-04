package com.igor.pancake.repository;

import com.igor.pancake.models.Pancake;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PancakeRepository  extends JpaRepository<Pancake, Long> {
    boolean existsByIngredients_Id(Long ingredientId);
    List<Pancake> findByOrderIsNull();
}
