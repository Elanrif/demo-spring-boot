package com.elanrif.demo_spring_boot.repository;

import com.elanrif.demo_spring_boot.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
