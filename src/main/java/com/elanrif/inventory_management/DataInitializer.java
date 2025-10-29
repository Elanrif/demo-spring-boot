package com.elanrif.inventory_management;

import com.elanrif.inventory_management.entities.Role;
import com.elanrif.inventory_management.enums.ERole;
import com.elanrif.inventory_management.payload.request.SignupRequest;
import com.elanrif.inventory_management.repository.RoleRepository;
import com.elanrif.inventory_management.repository.UserRepository;
import com.elanrif.inventory_management.security.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthService authService;

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
                .phone("+212612345678")
                .address("123 Admin St, City, Country")
                .roles(Set.of("admin", "moderator"))
                .build();
        if (userRepository.findByUsername("admin").isEmpty()) {
            authService.registerAndGenerateJwt(adminSignupRequest,false);
        }
    }
}
