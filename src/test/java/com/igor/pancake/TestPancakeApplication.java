package com.igor.pancake;

import org.springframework.boot.SpringApplication;

public class TestPancakeApplication {

	public static void main(String[] args) {
		SpringApplication.from(PancakeApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
