package com.yhkim.domain.order.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    // 공통 상태
    ORDER_COMPLETED("주문 완료", 1),
    
    // BUY - 구매 주문 상태
    PAYMENT_COMPLETED("입금 완료", 2),
    SHIPMENT_COMPLETED("발송 완료", 2),
    
    // SELL - 판매 주문 상태
    REMITTANCE_COMPLETED("송금 완료", 3),
    RECEIPT_COMPLETED("수령 완료", 3);
    
    private final String description;
    private final int sequence;
    
    public boolean canTransitionTo(OrderStatus newStatus) {
        return this.sequence < newStatus.sequence;
    }
    
    @JsonCreator
    public static OrderStatus parsing(String inputValue) {
        return Stream.of(OrderStatus.values())
                .filter(orderStatus -> orderStatus.name().equals(inputValue.toUpperCase()))
                .findFirst()
                .orElse(null);
    }
}
