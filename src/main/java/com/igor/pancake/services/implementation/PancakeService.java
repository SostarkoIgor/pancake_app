package com.igor.pancake.services.implementation;

import java.util.List;

import com.igor.pancake.dtos.PancakeRequestDto;
import com.igor.pancake.exceptions.InvalidPancakeException;
import com.igor.pancake.exceptions.PancakeIngredientException;
import com.igor.pancake.exceptions.ResourceNotFoundException;
import com.igor.pancake.models.Ingredient;
import com.igor.pancake.models.Pancake;
import com.igor.pancake.repository.IngredientRepository;
import com.igor.pancake.repository.PancakeRepository;
import com.igor.pancake.services.IPancakeService;
import org.springframework.stereotype.Service;

@Service
public class PancakeService implements IPancakeService {

    private final PancakeRepository pancakeRepository;
    private final IngredientRepository ingredientRepository;
    public PancakeService(PancakeRepository _pancakeRepository, IngredientRepository _ingredientRepository) {
        pancakeRepository = _pancakeRepository;
        ingredientRepository = _ingredientRepository;
    }
    @Override
    public Pancake save(PancakeRequestDto pancake) {
        if (pancake.getIngredients()==null || pancake.getIngredients().isEmpty()) throw new PancakeIngredientException("Pancake can't have 0 ingredients.");

        List<Ingredient> ingredients =
                ingredientRepository.findAllById(pancake.getIngredients());

        if (ingredients.size() != pancake.getIngredients().size()) {
            throw new PancakeIngredientException("Some ingredients do not exist");
        }

        Pancake pancake_ = new Pancake();
        pancake_.setIngredients(ingredients);
        if (!pancake_.isValid()) throw new InvalidPancakeException("Invalid pancake.");

        return pancakeRepository.save(pancake_);
    }

    @Override
    public Pancake update(PancakeRequestDto pancake, Long id) {
        if (pancakeRepository.existsById(id)) {
            Pancake pancake__ = pancakeRepository.findById(id)
                    .orElseThrow();

            if (pancake__.getOrder() != null) {
                throw new IllegalStateException(
                        "Pancake belongs to an order and cannot be modified");
            }
            List<Ingredient> ingredients= ingredientRepository.findAllById(pancake.getIngredients());
            if (ingredients!=null && !ingredients.isEmpty()){

                if (pancake.getIngredients().size() != ingredients.size()) {
                    throw new PancakeIngredientException("Some ingredients do not exist");
                }
                Pancake pancake_=pancakeRepository.findById(id).orElseThrow();
                pancake_.setIngredients(ingredients);
                if (!pancake_.isValid()) throw new InvalidPancakeException("Invalid pancake");
                return pancakeRepository.save(pancake_);
            }
            else throw new PancakeIngredientException("Not enough ingredients.");
        }
        throw new ResourceNotFoundException("Pancake not found.");
    }

    @Override
    public boolean delete(Long id) {
        if (pancakeRepository.existsById(id)) {
            Pancake pancake=pancakeRepository.findById(id).orElseThrow();
            if (pancake.getOrder() != null) {
                throw new IllegalStateException(
                        "Pancake belongs to an order and cannot be deleted");
            }
            pancakeRepository.deleteById(id);
            return true;
        }
        throw new ResourceNotFoundException("Pancake with the given id not found.");
    }

    @Override
    public List<Long> getAllPancakeIds() {
        return pancakeRepository.findAll().stream().map(Pancake::getId).toList();
    }

    @Override
    public Pancake getById(Long id) {
        return pancakeRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Pancake with given id not found"));
    }

    @Override
    public boolean isPancakeInOrder(Long id) {
        Pancake pancake = getById(id);
        return pancake.getOrder()!=null;
    }

}
