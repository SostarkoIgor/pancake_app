package com.igor.pancake.controllers;

import com.igor.pancake.dtos.ReportDto;
import com.igor.pancake.mappers.IngredientMapper;
import com.igor.pancake.services.IIngredientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reports")
public class ReportController {
    private final IIngredientService ingredientService;

    public ReportController(IIngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public ReportDto get(){
        ReportDto dto=new ReportDto();
        dto.setMostUsed(IngredientMapper.toDTO(ingredientService.findMostUsedLastMonth()));
        dto.setMostUsedHealthy(IngredientMapper.toDTO(ingredientService.findMostUsedHealthyLastMonth()));
        return dto;
    }
}
