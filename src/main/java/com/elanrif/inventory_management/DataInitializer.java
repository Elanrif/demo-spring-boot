package com.elanrif.inventory_management;

import com.elanrif.inventory_management.entities.Category;
import com.elanrif.inventory_management.entities.Product;
import com.elanrif.inventory_management.entities.Role;
import com.elanrif.inventory_management.enums.ERole;
import com.elanrif.inventory_management.enums.CategoryEnum;
import com.elanrif.inventory_management.payload.request.SignupRequest;
import com.elanrif.inventory_management.repository.CategoryRepository;
import com.elanrif.inventory_management.repository.ProductRepository;
import com.elanrif.inventory_management.repository.RoleRepository;
import com.elanrif.inventory_management.repository.UserRepository;
import com.elanrif.inventory_management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        /* Create Roles */
        if (roleRepository.findByName(ERole.ROLE_USER).isEmpty()) {
            roleRepository.save(new Role(ERole.ROLE_USER));
        }
        if (roleRepository.findByName(ERole.ROLE_ADMIN).isEmpty()) {
            roleRepository.save(new Role(ERole.ROLE_ADMIN));
        }
        if (roleRepository.findByName(ERole.ROLE_MODERATOR).isEmpty()) {
            roleRepository.save(new Role(ERole.ROLE_MODERATOR));
        }

        /* Create Admin */
        SignupRequest adminSignupRequest = SignupRequest.builder()
                .username("admin")
                .password("admin123")
                .email("admin@gmail.com")
                .role(Set.of("admin", "moderator"))
                .build();
        if (userRepository.findByUsername("admin").isEmpty()) {
            userService.signUp(adminSignupRequest);
        }

        /* Create Categories */
        if (categoryRepository.findAll().isEmpty()) {
            Category electronics = new Category(null, null, "Electronics", "Devices and gadgets", false, null, CategoryEnum.ACTIVE, null, null);
            Category clothing = new Category(null, null, "Clothing", "Apparel and accessories", false, null, CategoryEnum.ACTIVE, null, null);
            categoryRepository.save(electronics);
            categoryRepository.save(clothing);
        }

        /* Create Products */
        if (productRepository.findAll().isEmpty()) {
            Category electronics = categoryRepository.findAll().get(0); // Assuming Electronics is the first category
            Category clothing = categoryRepository.findAll().get(1); // Assuming Clothing is the second category

            Product phone = new Product(null, electronics, "Smartphone", 699.99, "Latest model smartphone", 50, null, null);
            Product tshirt = new Product(null, clothing, "T-Shirt", 19.99, "Comfortable cotton t-shirt", 200, null, null);

            productRepository.save(phone);
            productRepository.save(tshirt);
        }
    }
}
