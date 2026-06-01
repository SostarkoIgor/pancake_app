package com.igor.pancake.repository;

import com.igor.pancake.models.Pancake;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PancakeRepository  extends JpaRepository<Pancake, Long> {
}
