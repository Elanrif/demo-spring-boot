package com.elanrif.inventory_management.services;

import com.elanrif.inventory_management.entities.Category;

import java.util.List;

public interface CategoryServiceImpl {
    Category createCategory(Category category);
    Category updateCategory(Integer id, Category category);
    void deleteCategory(Integer id);
    List<Category> getAllCategories(String order);
    Category getCategoryById(Integer id);
}
