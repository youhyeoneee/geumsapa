package com.yhkim.domain.order.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    // 공통 상태
    ORDER_COMPLETED("주문 완료"),
    
    // 구매 주문 상태
    PAYMENT_COMPLETED("입금 완료"),
    SHIPMENT_COMPLETED("발송 완료"),
    
    // 판매 주문 상태
    REMITTANCE_COMPLETED("송금 완료"),
    RECEIPT_COMPLETED("수령 완료");
    
    private final String description;
}
