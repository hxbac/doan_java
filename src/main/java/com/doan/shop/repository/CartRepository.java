package com.doan.shop.repository;

import com.doan.shop.model.Cart;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("SELECT c, p FROM Cart c JOIN FETCH c.product p WHERE c.userID = :userId")
    List<Object[]> findAllByUserIDWithProduct(@Param("userId") Long userId);

    @Query("SELECT c FROM Cart c WHERE c.userID = :userId AND c.product.id = :productId")
    List<Cart> findAllByUserIDAndProductID(@Param("userId") Long userId, @Param("productId") Long productId);
}