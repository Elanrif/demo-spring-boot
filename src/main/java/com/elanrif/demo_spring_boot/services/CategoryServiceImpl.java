package com.elanrif.demo_spring_boot.services;

import com.elanrif.demo_spring_boot.entities.Category;

import java.util.List;

public interface CategoryServiceImpl {
    Category createCategory(Category category);
    Category updateCategory(Integer id, Category category);
    void deleteCategory(Integer id);
    List<Category> getAllCategories();
    Category getCategoryById(Integer id);
}
