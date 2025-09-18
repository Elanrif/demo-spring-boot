package com.elanrif.inventory_management.dto;

import lombok.Data;

@Data
public class ProductReqDto {
    private Integer categoryId;
    private String name;
    private Double price;
    private String description;
    private Integer stock;
}

