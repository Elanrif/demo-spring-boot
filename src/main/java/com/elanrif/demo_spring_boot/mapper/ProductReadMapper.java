package com.elanrif.demo_spring_boot.mapper;

import com.elanrif.demo_spring_boot.dto.ProductDto;
import com.elanrif.demo_spring_boot.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductReadMapper {
    ProductDto toDto(Product product);
    Product toEntity(ProductDto productDto);
}
