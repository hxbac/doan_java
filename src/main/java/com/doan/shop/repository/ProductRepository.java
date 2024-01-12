package com.doan.shop.repository;

import com.doan.shop.model.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p " +
           "WHERE (:ram IS NULL OR p.ram = :ram) " +
           "AND (:memory IS NULL OR p.memory = :memory) " +
           "AND (:price IS NULL OR (CASE WHEN :price = 1 THEN p.price < 5000000 " +
           "                             WHEN :price = 2 THEN p.price BETWEEN 5000000 AND 10000000 " +
           "                             WHEN :price = 3 THEN p.price BETWEEN 10000000 AND 15000000 " +
           "                             WHEN :price = 4 THEN p.price BETWEEN 15000000 AND 20000000 " +
           "                             WHEN :price = 5 THEN p.price >= 20000000 END)) " +
           "AND (:search IS NULL OR p.name LIKE %:search%) " +
           "AND (:category IS NULL OR p.category.id = :category)")
    List<Product> findProductsByQuery(
            @Param("ram") String ram,
            @Param("memory") String memory,
            @Param("price") String price,
            @Param("search") String search,
            @Param("category") Long category);
    
    List<Product> findTop8ByOrderByIdDesc();

    @Query("SELECT p FROM Product p WHERE p.category.id = :category")
    List<Product> findProductsByCategory(@Param("category") Long category);
}