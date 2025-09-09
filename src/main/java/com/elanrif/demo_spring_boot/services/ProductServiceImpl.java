package com.elanrif.demo_spring_boot.services;

import com.elanrif.demo_spring_boot.entities.Product;

import java.util.List;

public interface ProductServiceImpl {
    Product createProduct(Product product);
    Product updateProduct(Integer id, Product product);
    void deleteProduct(Integer id);
    List<Product> getAllProducts();
}
