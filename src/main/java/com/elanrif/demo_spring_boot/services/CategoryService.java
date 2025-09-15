package com.elanrif.demo_spring_boot.services;

import com.elanrif.demo_spring_boot.entities.Category;
import com.elanrif.demo_spring_boot.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements CategoryServiceImpl {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory( Category  category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Integer id,  Category category) {
        Category updateCategory =  categoryRepository.findById(id).orElse(null);
        if (updateCategory == null) {
            throw new RuntimeException(" Category not found");
        }
        updateCategory.setName( category.getName());
        updateCategory.setDescription(category.getDescription());
        updateCategory.setCreatedAt(category.getCreatedAt());
        updateCategory.setCreatedAt(category.getCreatedAt());
        return categoryRepository.save(updateCategory);

    }

    @Override
    public void deleteCategory(Integer id) {
        Category updateCategory = categoryRepository.findById(id).orElse(null);
        if (updateCategory == null) {
            throw new RuntimeException("Category not found");
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Integer id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category== null) {
            throw new RuntimeException("Category not found");
        }
        return category;
    }
}
