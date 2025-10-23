package com.elanrif.inventory_management.controllers;

import com.elanrif.inventory_management.dto.ProductReqDto;
import com.elanrif.inventory_management.entities.Product;
import com.elanrif.inventory_management.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public Product createProduct(@RequestBody ProductReqDto productReqDto) {
        return productService.createProduct(productReqDto);
    }

    @PutMapping("/{ID}")
    public Product updateProduct(@PathVariable("ID") Integer id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Integer id) {
      productService.deleteProduct(id);
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/desc")
    public List<Product> getAllProductsDesc() {
        return productService.getAllProductsDesc();
    }


    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Integer id) {
        return productService.getProductById(id);
    }
}
