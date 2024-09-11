package com.yhkim.domain.order.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderType {
    BUY("BUY"),
    SELL("SELL");
    
    private final String type;
    
    // String 값으로부터 Enum을 찾는 메서드
    public static OrderType fromString(String text) {
        for (OrderType t : OrderType.values()) {
            if (t.type.equalsIgnoreCase(text)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Unknown trade type: " + text);
    }
}
