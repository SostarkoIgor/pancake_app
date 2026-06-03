package com.igor.pancake;

import com.igor.pancake.models.Ingredient;
import com.igor.pancake.repository.IngredientRepository;
import com.igor.pancake.repository.PancakeRepository;
import com.igor.pancake.services.implementation.IngredientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IngredientServiceTest {

    @Mock
    private IngredientRepository ingredientRepository;

    @Mock
    private PancakeRepository pancakeRepository;

    @InjectMocks
    private IngredientService ingredientService;

    @Test
    void shouldReturnAllIngredients() {
        when(ingredientRepository.findAll())
                .thenReturn(List.of(new Ingredient(), new Ingredient()));

        assertEquals(2, ingredientService.getAll().size());
    }

    @Test
    void shouldThrowWhenDeletingUsedIngredient() {
        when(pancakeRepository.existsByIngredients_Id(1L))
                .thenReturn(true);

        assertThrows(RuntimeException.class,
                () -> ingredientService.delete(1L));
    }

    @Test
    void shouldAllowDeleteWhenNotUsed() {

        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);

        when(ingredientRepository.existsById(1L))
                .thenReturn(true);

        when(pancakeRepository.existsByIngredients_Id(1L))
                .thenReturn(false);

        ingredientService.delete(1L);

        verify(ingredientRepository).deleteById(1L);
    }
}