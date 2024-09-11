package com.yhkim.domain.order.util;

import com.yhkim.domain.order.entity.OrderType;

public class OrderNumberGenerator {
    private static final String ORDER_PREFIX = "ORDER";
    
    public static String generateOrderNumber(OrderType type, Integer id) {
        
        String idPart = String.format("%06d", id);
        return String.format("%s-%s-%s", ORDER_PREFIX, type.getCode(), idPart);
    }
}
