package com.elanrif.inventory_management.services;

import com.elanrif.inventory_management.dto.UserDto;
import com.elanrif.inventory_management.dto.UserReqDto;
import com.elanrif.inventory_management.entities.Role;
import com.elanrif.inventory_management.entities.User;
import com.elanrif.inventory_management.enums.ERole;
import com.elanrif.inventory_management.mapper.UserDtoMap;
import com.elanrif.inventory_management.mapper.UserReqDtoMap;
import com.elanrif.inventory_management.payload.request.SignupRequest;
import com.elanrif.inventory_management.repository.RoleRepository;
import com.elanrif.inventory_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserServiceImpl {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserDtoMap userDtoMap;
    @Autowired
    private UserReqDtoMap userReqDtoMap;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public List<User> getAllUsers(String order) {
        if (order != null && order.equalsIgnoreCase("desc")) {
            return userRepository.findAllByOrderByIdDesc();
        }
       return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    @Override
    public User updateUser(UserReqDto userReqDto, Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        // Mise à jour des champs
        existingUser.setEmail(userReqDto.getEmail());
        existingUser.setUsername(userReqDto.getUsername());
        existingUser.setPhone(userReqDto.getPhone());
        existingUser.setAddress(userReqDto.getAddress());
        existingUser.setPassword(passwordEncoder.encode(userReqDto.getPassword()));
        return userRepository.save(existingUser);
    }

    @Override
    public UserDto login(UserReqDto userReqDto) {
        User user = userRepository.findByEmail(userReqDto.getEmail())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        if (!passwordEncoder.matches(userReqDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Mot de passe incorrect");
        }
        return userDtoMap.toDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        userRepository.deleteById(user.getId());
    }

    @Override
    public User register(SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            throw new RuntimeException("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new RuntimeException("Error: Email is already in use!");
        }

        User user = User.builder()
                .username(signupRequest.getUsername())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .email(signupRequest.getEmail())
                .phone(signupRequest.getPhone())
                .address(signupRequest.getAddress())
                .build();

        Set<String> strRoles = signupRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "moderator":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        break;
                    default:
                        Role defaultRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(defaultRole);
                }
            });
        }

        user.setRoles(roles);
        return userRepository.save(user);
    }

}
