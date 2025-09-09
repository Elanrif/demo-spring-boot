package com.elanrif.demo_spring_boot.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity // JPA,This will create a table named "product" in the database
public class Product {
    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatic Increment
    private Integer id;
    private String name;
    private Double price;
    private String description;
    private Integer stock;
    private LocalDateTime createdAt;
}
