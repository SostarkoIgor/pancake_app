package com.igor.pancake.models;

import com.igor.pancake.dtos.OrderDto;
import com.igor.pancake.dtos.PancakeDto;
import com.igor.pancake.mappers.OrderMapper;
import com.igor.pancake.mappers.PancakeMapper;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String description;
    private LocalDateTime orderTime;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL
    )
    private List<Pancake> pancakes = new ArrayList<>();

    public BigDecimal getPrice(){
        return pancakes.stream().map(Pancake::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public OrderDto getOrderDtoWithDiscount(){
        BigDecimal price_healthy = BigDecimal.ZERO;
        for (Pancake p:pancakes){
            if (p.isHealthy()){
                price_healthy=price_healthy.add(p.getPrice().multiply(BigDecimal.valueOf(0.85)));
            }
            else price_healthy=price_healthy.add(p.getPrice());
        }
        BigDecimal price=getPrice();
        OrderDto dto=OrderMapper.toDto(this);
        List<PancakeDto> pancakes_= new ArrayList<>();
        if (price.compareTo(BigDecimal.valueOf(20)) < 1 ||
            price.compareTo(BigDecimal.valueOf(50)) < 1 && price.multiply(BigDecimal.valueOf(0.95)).compareTo(price_healthy) > 0 ||
                price.multiply(BigDecimal.valueOf(0.9)).compareTo(price_healthy) > 0
        ){

            for (Pancake p:pancakes){
                if (p.isHealthy()){
                    pancakes_.add(PancakeMapper.toDTO(p,0.85));
                }
                else pancakes_.add(PancakeMapper.toDTO(p));
            }
            dto.setPancakes(pancakes_);
            dto.setPrice(price_healthy);
        }else{
            double discount=1;
            if (price.compareTo(BigDecimal.valueOf(20)) > 0) discount=0.95;
            if (price.compareTo(BigDecimal.valueOf(50)) > 0) discount=0.9;
            for (Pancake p:pancakes){
                pancakes_.add(PancakeMapper.toDTO(p, discount));
            }
            dto.setPancakes(pancakes_);
            dto.setPrice(price.multiply(BigDecimal.valueOf(discount)));
        }
        return dto;
    }
}
