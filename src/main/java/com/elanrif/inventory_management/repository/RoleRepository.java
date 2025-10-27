package com.elanrif.inventory_management.repository;

import com.elanrif.inventory_management.entities.Role;
import com.elanrif.inventory_management.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
Optional<Role> findByName(ERole name);
}
