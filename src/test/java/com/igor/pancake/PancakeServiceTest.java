package com.igor.pancake;

import com.igor.pancake.dtos.PancakeRequestDto;
import com.igor.pancake.models.Category;
import com.igor.pancake.models.Ingredient;
import com.igor.pancake.models.Pancake;
import com.igor.pancake.repository.IngredientRepository;
import com.igor.pancake.repository.PancakeRepository;
import com.igor.pancake.services.implementation.PancakeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PancakeServiceTest {

    @Mock
    private PancakeRepository pancakeRepository;

    @Mock
    private IngredientRepository ingredientRepository;

    @InjectMocks
    private PancakeService pancakeService;

    @Test
    void shouldThrowWhenNoIngredients() {
        PancakeRequestDto dto = new PancakeRequestDto();
        dto.setIngredients(List.of());

        assertThrows(RuntimeException.class,
                () -> pancakeService.save(dto));
    }

    @Test
    void shouldCreatePancake() {

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        ingredient1.setCategory(Category.baza);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);
        ingredient2.setCategory(Category.nadjev);

        when(ingredientRepository.findAllById(any()))
                .thenReturn(List.of(ingredient1, ingredient2));

        when(pancakeRepository.save(any()))
                .thenAnswer(i -> i.getArgument(0));

        PancakeRequestDto dto = new PancakeRequestDto();
        dto.setIngredients(List.of(1L,2L));

        Pancake result = pancakeService.save(dto);

        assertNotNull(result);
    }
}
