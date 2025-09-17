package com.elanrif.demo_spring_boot.mapper;

import com.elanrif.demo_spring_boot.dto.ProductCreateDto;
import com.elanrif.demo_spring_boot.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductCreateDto toDto(Product product);
    Product toEntity(ProductCreateDto productCreateDto);
}
