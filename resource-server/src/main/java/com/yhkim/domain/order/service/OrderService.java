package com.yhkim.domain.order.service;

import com.yhkim.domain.order.dto.CreateOrderRequest;
import com.yhkim.domain.order.dto.OrderDetailResponse;
import com.yhkim.domain.order.entity.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface OrderService {
    
    OrderDetailResponse createOrder(Integer userId, CreateOrderRequest createOrderRequest);
    
    OrderDetailResponse cancelOrder(Integer userId, Integer orderId);
    
    OrderDetailResponse updateOrder(Integer userId, Integer orderId, OrderStatus orderStatus);
    
    Page<OrderDetailResponse> getAllOrders(Pageable pageable, Integer userId, LocalDate date, String invoice);
}
