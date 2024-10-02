package com.yhkim.domain.order.repository;

import com.yhkim.domain.order.entity.Order;
import com.yhkim.domain.order.entity.OrderType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Optional<Order> findTopByOrderByIdDesc();
    
    
    @Query("SELECT o FROM Order o WHERE o.orderUserId = :orderUserId and :orderType is null or o.orderType = :orderType ORDER BY o.id DESC")
    Page<Order> findByOrderUserIdAndOrderType(Integer orderUserId, OrderType orderType, Pageable pageable);
}
