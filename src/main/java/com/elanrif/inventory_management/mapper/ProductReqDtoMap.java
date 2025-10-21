package com.elanrif.inventory_management.mapper;

import com.elanrif.inventory_management.dto.ProductReqDto;
import com.elanrif.inventory_management.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductReqDtoMap {
    @Mapping(target = "categoryId", ignore = true)
    ProductReqDto toDto(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Product toEntity(ProductReqDto productReqDto);
}
