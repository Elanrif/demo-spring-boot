package com.elanrif.demo_spring_boot.services;

import com.elanrif.demo_spring_boot.entities.Product;
import com.elanrif.demo_spring_boot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements ProductServiceImpl {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
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
        updateProduct.setCreatedAt(product.getCreatedAt());
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
    public Product getProductById(Integer id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
       return product;
    }
}
