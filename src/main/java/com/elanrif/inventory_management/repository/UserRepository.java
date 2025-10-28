package com.elanrif.inventory_management.repository;

import com.elanrif.inventory_management.entities.Category;
import com.elanrif.inventory_management.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findAllByOrderByIdDesc();

}
