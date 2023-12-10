package com.doan.shop.repository;

import com.doan.shop.dto.OrderDetailDTO;
import com.doan.shop.model.OrderDetail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    @Query("SELECT NEW com.doan.shop.dto.OrderDetailDTO(od, p) FROM OrderDetail od JOIN FETCH od.product p JOIN FETCH od.order o WHERE o.id = :orderId")
    List<OrderDetailDTO> findAllByOrderIDWithProduct(@Param("orderId") Long orderId);
}