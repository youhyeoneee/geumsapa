package com.yhkim.domain.order.service;

import com.yhkim.domain.order.dto.CreateOrderRequest;
import com.yhkim.domain.order.dto.OrderDetailResponse;

public interface OrderService {
    
    OrderDetailResponse createOrder(CreateOrderRequest createOrderRequest);
    
    OrderDetailResponse cancelOrder(Integer orderId);
    
    // TODO: updateOrderStatus
    
    // TODO: getOrder
}
