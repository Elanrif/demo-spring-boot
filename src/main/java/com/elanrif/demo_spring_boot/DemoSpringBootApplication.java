package com.elanrif.demo_spring_boot;

import com.elanrif.demo_spring_boot.entities.Product;
import com.elanrif.demo_spring_boot.repository.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringBootApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(ProductRepository productRepository) {
		return args -> {
			for (int i = 1; i <= 5; i++) {
				Product p = new Product(
					null,
					"Produit " + i,
					10.0 * i,
					"Description du produit " + i,
					100 * i,
					java.time.LocalDateTime.now()
				);
                System.out.println("Product: " + i + " " + p);
                productRepository.save(p);
			}
		};
	}
}
