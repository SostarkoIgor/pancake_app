package com.igor.pancake.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequestDto {
    private String description;
    private List<Long> pancakeIds;

}
