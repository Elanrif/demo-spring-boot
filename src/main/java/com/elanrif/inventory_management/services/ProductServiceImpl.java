package com.elanrif.inventory_management.services;

import com.elanrif.inventory_management.dto.ProductReqDto;
import com.elanrif.inventory_management.entities.Product;

import java.util.List;

public interface ProductServiceImpl {
    Product createProduct(ProductReqDto productReqDto);
    Product updateProduct(Integer id, Product product);
    void deleteProduct(Integer id);
    List<Product> getAllProducts();
    Product getProductById(Integer id);
}
