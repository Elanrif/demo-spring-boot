package com.elanrif.inventory_management.repository;

import com.elanrif.inventory_management.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer>{
    List<Category> findAllByOrderByIdDesc();
}
