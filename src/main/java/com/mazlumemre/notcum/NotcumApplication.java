package com.mazlumemre.notcum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.mazlumemre.notcum")
public class NotcumApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotcumApplication.class, args);
	}

}
