package com.elanrif.inventory_management.mapper;

import com.elanrif.inventory_management.dto.ProductDto;
import com.elanrif.inventory_management.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductDtoMap {
    ProductDto toDto(Product product);
    Product toEntity(ProductDto productDto);
}
