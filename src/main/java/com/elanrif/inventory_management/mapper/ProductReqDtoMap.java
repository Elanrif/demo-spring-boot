package com.elanrif.inventory_management.mapper;

import com.elanrif.inventory_management.dto.ProductReqDto;
import com.elanrif.inventory_management.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductReqDtoMap {
    ProductReqDto toDto(Product product);
    Product toEntity(ProductReqDto productReqDto);
}

