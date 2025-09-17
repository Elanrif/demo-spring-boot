package com.elanrif.demo_spring_boot.controllers;

import com.elanrif.demo_spring_boot.dto.ProductCreateDto;
import com.elanrif.demo_spring_boot.dto.ProductDto;
import com.elanrif.demo_spring_boot.entities.Product;
import com.elanrif.demo_spring_boot.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public Product createProduct(@RequestBody ProductCreateDto productCreateDto) {
        return productService.createProduct(productCreateDto);
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

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Integer id) {
        return productService.getProductById(id);
    }

    @GetMapping("/byName")
    public List<Product> findByProductName(@RequestParam("name") String name) {
        return productService.findByProductName(name);
    }

    @GetMapping("/byPrice")
    public List<Product> findByPriceIsGreaterThanEqual(@RequestParam("price") Double price){
        return productService.findByPriceIsGreaterThanEqual(price);
    }

    @GetMapping("/dto")
    public List<ProductDto> getAllProductDtos() {
        return productService.getAllDtoProducts();
    }

    @GetMapping("/dto/{id}")
    public ProductDto getProductDtoById(@PathVariable("id") Integer id) {
        return productService.getProductDtoById(id);
    }
}
