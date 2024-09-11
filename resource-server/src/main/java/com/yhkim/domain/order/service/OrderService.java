package com.yhkim.domain.order.service;

import com.yhkim.domain.order.dto.CreateOrderRequest;
import com.yhkim.domain.order.dto.OrderDetailResponse;
import com.yhkim.domain.order.entity.OrderStatus;

public interface OrderService {
    
    OrderDetailResponse createOrder(CreateOrderRequest createOrderRequest);
    
    OrderDetailResponse cancelOrder(Integer orderId);
    
    OrderDetailResponse updateOrder(Integer orderId, OrderStatus orderStatus);
    
    // TODO: getOrder
}
