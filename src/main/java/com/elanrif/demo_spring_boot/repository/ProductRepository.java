package com.elanrif.demo_spring_boot.repository;

import com.elanrif.demo_spring_boot.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByName(String name);
    /* price >= ?*/
    List<Product> findByPriceIsGreaterThanEqual(Double price);
    /*TODOLIST
    *About name: ignoreCase, like, notLike, contain* and isNotContain* (all contains), endsWith*
    *About price:   lowerthanEqual, Equal,
    * */
}
