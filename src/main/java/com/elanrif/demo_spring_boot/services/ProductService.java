package com.elanrif.demo_spring_boot.services;

import com.elanrif.demo_spring_boot.dto.ProductCreateDto;
import com.elanrif.demo_spring_boot.entities.Category;
import com.elanrif.demo_spring_boot.entities.Product;
import com.elanrif.demo_spring_boot.repository.CategoryRepository;
import com.elanrif.demo_spring_boot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements ProductServiceImpl {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product createProduct(ProductCreateDto productCreateDto) {
        Category category = categoryRepository.findById(productCreateDto.getCategoryId()).orElse(null);
        if (category == null) {
            throw new RuntimeException("Category not found");
        }
        Product p = new Product();
        p.setName(productCreateDto.getName());
        p.setPrice(productCreateDto.getPrice());
        p.setDescription(productCreateDto.getDescription());
        p.setStock(productCreateDto.getStock());
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
    public Product getProductById(Integer id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
       return product;
    }

    @Override
    public List<Product> findByProductName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> findByPriceIsGreaterThanEqual(Double price) {
        return productRepository.findByPriceIsGreaterThanEqual(price);
    }
}
