package com.doan.shop.repository;

import com.doan.shop.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByEmailAndPassword(String email, String password);
    List<User> findAllByEmail(String email);
}