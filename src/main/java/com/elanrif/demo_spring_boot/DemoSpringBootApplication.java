package com.elanrif.demo_spring_boot;

import com.elanrif.demo_spring_boot.entities.Product;
import com.elanrif.demo_spring_boot.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoSpringBootApplication {
    @Autowired
    ProductService productService;
	public static void main(String[] args) {
		SpringApplication.run(DemoSpringBootApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader() {
		return args -> {
			Product p = new Product(null, "Sample Product", 19.99, "This is a sample product", 100, java.time.LocalDateTime.now());

            productService.createProduct(p);
		};
	}
}
