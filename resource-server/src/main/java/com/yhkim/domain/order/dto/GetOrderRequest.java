package com.yhkim.domain.order.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GetOrderRequest {
    
    // TODO: gRPC 이용하여 변경
    @NotNull
    @Min(value = 1, message = "User Id must be 1 and greater.")
    private Integer userId;
}
