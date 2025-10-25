package com.elanrif.inventory_management.services;

import com.elanrif.inventory_management.dto.ProductReqDto;
import com.elanrif.inventory_management.entities.Category;
import com.elanrif.inventory_management.entities.Product;
import com.elanrif.inventory_management.mapper.ProductReqDtoMap;
import com.elanrif.inventory_management.repository.CategoryRepository;
import com.elanrif.inventory_management.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements ProductServiceImpl {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductReqDtoMap productReqDtoMap;

    @Override
    public Product createProduct(ProductReqDto productReqDto) {
        Category category = categoryRepository.findById(productReqDto.getCategoryId()).orElse(null);
        Product p = productReqDtoMap.toEntity(productReqDto);
        if (category == null) {
           return productRepository.save(p);
        }
        p.setCategory(category);
        return productRepository.save(p);
    }

    @Override
    public Product updateProduct(Integer id, Product product) {
        Product updateProduct = productRepository.findById(id).orElse(null);
        if (updateProduct == null) {
            throw new RuntimeException("Product not found");
        }
        updateProduct.setName(product.getName());
        updateProduct.setPrice(product.getPrice());
        updateProduct.setDescription(product.getDescription());
        updateProduct.setStock(product.getStock());
        return productRepository.save(updateProduct);
    }

    @Override
    public void deleteProduct(Integer id) {
        Product updateProduct = productRepository.findById(id).orElse(null);
        if (updateProduct == null) {
           throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsDesc() {
        return productRepository.findByOrderByIdDesc();
    }
    @Override
    public Product getProductById(Integer id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
       return product;
    }
}
