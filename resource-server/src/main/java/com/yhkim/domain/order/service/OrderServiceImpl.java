package com.yhkim.domain.order.service;

import com.yhkim.domain.order.dto.CreateOrderRequest;
import com.yhkim.domain.order.dto.OrderDetailResponse;
import com.yhkim.domain.order.entity.Order;
import com.yhkim.domain.order.entity.OrderStatus;
import com.yhkim.domain.order.repository.OrderRepository;
import com.yhkim.domain.order.util.OrderNumberGenerator;
import com.yhkim.domain.product.entity.Product;
import com.yhkim.domain.product.entity.ProductPrice;
import com.yhkim.domain.product.repository.ProductPriceRepository;
import com.yhkim.domain.product.repository.ProductRepository;
import com.yhkim.exception.CustomException;
import com.yhkim.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ProductPriceRepository productPriceRepository;
    
    @Override
    @Transactional
    public OrderDetailResponse createOrder(CreateOrderRequest createOrderRequest) {
        
        // 상품
        Product product = productRepository.findById(createOrderRequest.getProductId())
                .orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
        
        // 수량 & 가격
        ProductPrice productPrice = productPriceRepository.findTopByOrderTypeOrderByCreatedAtDesc(createOrderRequest.getOrderType())
                .orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_PRICE_NOT_FOUND));
        
        // quantity를 100으로 곱한 후 계산 (센티그램 단위 변환)
        // 0.01 -> 1 센티그램
        // getPricePerCentigramme = 1 센티그램별 가격
        Integer totalPrice = productPrice.getPricePerCentigramme() * createOrderRequest.getQuantity().multiply(new BigDecimal("100")).intValue();
        
        Order order = createOrderRequest.toEntity(product, totalPrice);
        Order savedOrder = orderRepository.save(order);
        
        // 저장된 주문의 ID를 이용하여 주문번호 생성
        String orderNumber = OrderNumberGenerator.generateOrderNumber(createOrderRequest.getOrderType(), savedOrder.getId());
        
        // 주문번호 설정
        savedOrder.setOrderNumber(orderNumber);
        
        return savedOrder.getOrderDetail();
    }
    
    @Override
    @Transactional
    public OrderDetailResponse cancelOrder(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new CustomException(ErrorCode.ORDER_NOT_FOUND));
        
        // 이미 취소된 경우
        if (order.getDeletedAt() != null) {
            throw new CustomException(ErrorCode.ORDER_ALREADY_CANCELLED);
        }
        
        // 수령 완료거나 발송 완료 시 주문 취소 못하도록
        if (order.getStatus().equals(OrderStatus.RECEIPT_COMPLETED) || order.getStatus().equals(OrderStatus.SHIPMENT_COMPLETED)) {
            throw new CustomException(ErrorCode.ORDER_CANCELLATION_NOT_ALLOWED);
        }
        
        order.delete();
        
        return order.getOrderDetail();
    }
    
    
}
