package com.yhkim.domain.order.controller;

import com.yhkim.domain.auth.CustomUserDetails;
import com.yhkim.domain.order.dto.CreateOrderRequest;
import com.yhkim.domain.order.dto.Links;
import com.yhkim.domain.order.dto.OrderDetailResponse;
import com.yhkim.domain.order.dto.UpdateOrderRequest;
import com.yhkim.domain.order.service.OrderService;
import com.yhkim.util.ApiUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.yhkim.util.ApiUtils.success;
import static com.yhkim.util.ApiUtils.successWithLinks;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    
    private final OrderService orderService;
    
    @PostMapping
    public ResponseEntity<ApiUtils.SuccessResponse<OrderDetailResponse>> createOrder(@AuthenticationPrincipal CustomUserDetails userDetails,
                                                                                     @Valid @RequestBody CreateOrderRequest request) {
        Integer userId = userDetails.getUserId();
        return success(HttpStatus.CREATED, "Success to create order.", orderService.createOrder(userId, request));
    }
    
    @GetMapping
    public ResponseEntity<ApiUtils.SuccessLinksResponse<List<OrderDetailResponse>, Links>> getOrders(@AuthenticationPrincipal CustomUserDetails userDetails,
                                                                                                     Pageable pageable,
                                                                                                     @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                                                                                     @RequestParam(required = false) String invoice,
                                                                                                     HttpServletRequest request) {
        Integer userId = userDetails.getUserId();
        Page<OrderDetailResponse> ordersPage = orderService.getAllOrders(pageable, userId, date, invoice);
        Links links = createLinks(ordersPage, request);
        
        return successWithLinks(HttpStatus.OK, "Success to get orders.", ordersPage.getContent(), links);
    }
    
    @DeleteMapping("/{orderId}")
    public ResponseEntity<ApiUtils.SuccessResponse<OrderDetailResponse>> cancelOrder(@AuthenticationPrincipal CustomUserDetails userDetails,
                                                                                     @PathVariable Integer orderId) {
        
        Integer userId = userDetails.getUserId();
        return success(HttpStatus.OK, "Success to cancel order.", orderService.cancelOrder(userId, orderId));
    }
    
    @PatchMapping("/{orderId}/status")
    public ResponseEntity<ApiUtils.SuccessResponse<OrderDetailResponse>> updateOrderStatus(@PathVariable Integer orderId, @RequestBody UpdateOrderRequest updateOrderRequest) {
        return success(HttpStatus.OK, "Success to update order status.", orderService.updateOrder(orderId, updateOrderRequest.getOrderStatus()));
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
