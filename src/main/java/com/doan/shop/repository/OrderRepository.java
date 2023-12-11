package com.doan.shop.repository;

import com.doan.shop.dto.OrderUserDTO;
import com.doan.shop.model.Orders;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    @Query("SELECT o FROM Orders o JOIN o.user u WHERE u.id = :userId")
    List<Orders> findAllOrdersByUserId(@Param("userId") Long userId);

    @Query("SELECT NEW com.doan.shop.dto.OrderUserDTO(o, u) FROM Orders o JOIN FETCH o.user u")
    List<OrderUserDTO> findAllWithUser();
}