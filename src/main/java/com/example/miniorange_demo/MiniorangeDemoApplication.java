package com.example.miniorange_demo;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class MiniorangeDemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(MiniorangeDemoApplication.class, args);

	}

}
