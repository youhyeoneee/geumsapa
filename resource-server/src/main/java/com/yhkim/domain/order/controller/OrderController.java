package com.yhkim.domain.order.controller;

import com.yhkim.domain.order.dto.CreateOrderRequest;
import com.yhkim.domain.order.dto.OrderDetailResponse;
import com.yhkim.domain.order.service.OrderService;
import com.yhkim.util.ApiUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.yhkim.util.ApiUtils.success;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    
    private final OrderService orderService;
    
    @PostMapping
    public ResponseEntity<ApiUtils.SuccessResponse<OrderDetailResponse>> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        return success(HttpStatus.CREATED, "Success to create order.", orderService.createOrder(request));
    }
    
    @DeleteMapping("/{orderId}")
    public ResponseEntity<ApiUtils.SuccessResponse<OrderDetailResponse>> cancelOrder(@PathVariable Integer orderId) {
        return success(HttpStatus.CREATED, "Success to cancel order.", orderService.cancelOrder(orderId));
    }
    
    // TODO: updateOrderStatus
    
    // TODO: getOrder
}
