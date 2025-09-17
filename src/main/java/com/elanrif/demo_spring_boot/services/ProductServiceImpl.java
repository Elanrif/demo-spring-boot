package com.elanrif.demo_spring_boot.services;

import com.elanrif.demo_spring_boot.dto.ProductCreateDto;
import com.elanrif.demo_spring_boot.dto.ProductDto;
import com.elanrif.demo_spring_boot.entities.Product;

import java.util.List;

public interface ProductServiceImpl {
    Product createProduct(ProductCreateDto productCreateDto);
    Product updateProduct(Integer id, Product product);
    void deleteProduct(Integer id);
    List<Product> getAllProducts();
    Product getProductById(Integer id);
    List<ProductDto> getAllDtoProducts();
    ProductDto getProductDtoById(Integer id);
    List<Product> findByProductName(String name);
    List<Product> findByPriceIsGreaterThanEqual(Double price);
}
