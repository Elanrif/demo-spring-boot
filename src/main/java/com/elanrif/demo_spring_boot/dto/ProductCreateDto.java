package com.elanrif.demo_spring_boot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductCreateDto {
    private Integer categoryId;
    private String name;
    private Double price;
    private String description;
    private Integer stock;
}
