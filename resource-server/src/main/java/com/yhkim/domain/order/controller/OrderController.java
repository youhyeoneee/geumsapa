package com.yhkim.domain.order.controller;

import com.yhkim.domain.order.dto.CreateOrderRequest;
import com.yhkim.domain.order.dto.GetOrderRequest;
import com.yhkim.domain.order.dto.OrderDetailResponse;
import com.yhkim.domain.order.dto.UpdateOrderRequest;
import com.yhkim.domain.order.service.OrderService;
import com.yhkim.util.ApiUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    
    @GetMapping
    public ResponseEntity<ApiUtils.SuccessResponse<List<OrderDetailResponse>>> getOrders(Pageable pageable,
                                                                                         @Valid @RequestBody GetOrderRequest getOrderRequest) {
        return success(HttpStatus.CREATED, "Success to get orders.", orderService.getAllOrders(pageable, getOrderRequest.getUserId()));
    }
    
    @DeleteMapping("/{orderId}")
    public ResponseEntity<ApiUtils.SuccessResponse<OrderDetailResponse>> cancelOrder(@PathVariable Integer orderId) {
        return success(HttpStatus.CREATED, "Success to cancel order.", orderService.cancelOrder(orderId));
    }
    
    @PatchMapping("/{orderId}/status")
    public ResponseEntity<ApiUtils.SuccessResponse<OrderDetailResponse>> updateOrderStatus(@PathVariable Integer orderId, @RequestBody UpdateOrderRequest updateOrderRequest) {
        return success(HttpStatus.CREATED, "Success to update order status.", orderService.updateOrder(orderId, updateOrderRequest.getOrderStatus()));
    }
    
    
}
