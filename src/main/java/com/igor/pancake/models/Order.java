package com.igor.pancake.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
}
