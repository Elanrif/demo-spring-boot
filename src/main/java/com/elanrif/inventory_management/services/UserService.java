package com.elanrif.inventory_management.services;

import com.elanrif.inventory_management.dto.UserDto;
import com.elanrif.inventory_management.dto.UserReqDto;
import com.elanrif.inventory_management.entities.Category;
import com.elanrif.inventory_management.entities.User;
import com.elanrif.inventory_management.mapper.UserDtoMap;
import com.elanrif.inventory_management.mapper.UserReqDtoMap;
import com.elanrif.inventory_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserServiceImpl {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDtoMap userDtoMap;
    @Autowired
    private UserReqDtoMap userReqDtoMap;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public List<UserDto> getAllUsers(String order) {
        List<User> users = order != null && order.equalsIgnoreCase("desc")
                ? userRepository.findAllByOrderByIdDesc()
                : userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(userDtoMap.toDto(user));
        }
        return userDtos;
    }

    @Override
    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(userDtoMap::toDto)
                .orElse(null);
    }

    @Override
    public User register(UserReqDto userReqDto) {
        if (userRepository.findByEmail(userReqDto.getEmail()).isPresent()) {
            throw new RuntimeException("Email déjà utilisé");
        }
        User user = userReqDtoMap.toEntity(userReqDto);
        user.setPassword(passwordEncoder.encode(userReqDto.getPassword()));
        return userRepository.save(user);
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
}
