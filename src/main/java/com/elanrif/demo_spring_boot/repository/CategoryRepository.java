package com.elanrif.demo_spring_boot.repository;

import com.elanrif.demo_spring_boot.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer>{
}
