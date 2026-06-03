package com.igor.pancake.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderDto {
    private Long Id;
    private String description;
    private LocalDateTime orderTime;
    private BigDecimal price;
    private List<PancakeDto> pancakes;
}
