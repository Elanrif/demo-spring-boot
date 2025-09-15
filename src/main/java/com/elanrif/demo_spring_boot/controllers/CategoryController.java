package com.elanrif.demo_spring_boot.controllers;

import com.elanrif.demo_spring_boot.entities.Category;
import com.elanrif.demo_spring_boot.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @PutMapping("/{ID}")
    public Category updateCategory(@PathVariable("ID") Integer id, @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") Integer id) {
        categoryService.deleteCategory(id);
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable("id") Integer id) {
        return categoryService.getCategoryById(id);
    }
}
