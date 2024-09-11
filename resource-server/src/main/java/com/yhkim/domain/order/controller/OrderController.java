package com.yhkim.domain.order.controller;

import com.yhkim.domain.order.dto.*;
import com.yhkim.domain.order.service.OrderService;
import com.yhkim.util.ApiUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.yhkim.util.ApiUtils.success;
import static com.yhkim.util.ApiUtils.successWithLinks;

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
    public ResponseEntity<ApiUtils.SuccessLinksResponse<List<OrderDetailResponse>, Links>> getOrders(Pageable pageable,
                                                                                                     @Valid @RequestBody GetOrderRequest getOrderRequest,
                                                                                                     HttpServletRequest request) {
        
        Page<OrderDetailResponse> ordersPage = orderService.getAllOrders(pageable, getOrderRequest.getUserId());
        Links links = createLinks(ordersPage, request);
        
        return successWithLinks(HttpStatus.CREATED, "Success to get orders.", ordersPage.getContent(), links);
    }
    
    @DeleteMapping("/{orderId}")
    public ResponseEntity<ApiUtils.SuccessResponse<OrderDetailResponse>> cancelOrder(@PathVariable Integer orderId) {
        return success(HttpStatus.CREATED, "Success to cancel order.", orderService.cancelOrder(orderId));
    }
    
    @PatchMapping("/{orderId}/status")
    public ResponseEntity<ApiUtils.SuccessResponse<OrderDetailResponse>> updateOrderStatus(@PathVariable Integer orderId, @RequestBody UpdateOrderRequest updateOrderRequest) {
        return success(HttpStatus.CREATED, "Success to update order status.", orderService.updateOrder(orderId, updateOrderRequest.getOrderStatus()));
    }
    
    private Links createLinks(Page<OrderDetailResponse> page, HttpServletRequest request) {
        String baseUrl = request.getRequestURL().toString() + "?" + request.getQueryString();
        
        return Links.builder()
                .self(baseUrl)
                .first(replacePageParam(baseUrl, 0))
                .last(replacePageParam(baseUrl, page.getTotalPages() - 1))
                .prev(page.hasPrevious() ? replacePageParam(baseUrl, page.getNumber() - 1) : null)
                .next(page.hasNext() ? replacePageParam(baseUrl, page.getNumber() + 1) : null)
                .build();
    }
    
    private String replacePageParam(String url, int page) {
        return url.replaceFirst("(page=)\\d+", "$1" + page);
    }
    
}
