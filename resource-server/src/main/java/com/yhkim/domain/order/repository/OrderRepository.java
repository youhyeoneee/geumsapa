package com.yhkim.domain.order.repository;

import com.yhkim.domain.order.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Optional<Order> findTopByOrderByIdDesc();
    
    Page<Order> findByOrderUserIdOrderByIdDesc(Integer orderUserId, Pageable pageable);
}
