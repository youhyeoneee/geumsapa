package com.yhkim.domain.auth.service;

import com.yhkim.domain.user.entity.User;
import com.yhkim.domain.user.service.UserService;
import com.yhkim.exception.CustomException;
import com.yhkim.exception.ErrorCode;
import com.yhkim.grpc.auth.AuthProto;
import com.yhkim.grpc.auth.AuthServiceGrpc;
import com.yhkim.grpc.dto.ValidateTokenRequest;
import com.yhkim.grpc.dto.ValidateTokenResponse;
import com.yhkim.grpc.mapper.AuthMapper;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;


@Slf4j
@RequiredArgsConstructor
@GrpcService
public class AuthServiceGrpcImpl extends AuthServiceGrpc.AuthServiceImplBase {
    private final AuthService authService;
    private final UserService userService;
    private final AuthMapper authMapper;
    
    @Override
    public void validateToken(AuthProto.ValidateTokenRequest request,
                              StreamObserver<AuthProto.ValidateTokenResponse> responseStreamObserver) {
        // 1. 클라이언트로부터 전달받은 request 데이터를 DTO로 변환한다.
        ValidateTokenRequest validateTokenRequest = authMapper.requestProtoToDto(request);
        
        // 2. 서비스 레이어에서 토큰을 사용해서 검증하는 로직을 수행하고 결과를 받는다.
        String username = "";
        try {
            username = authService.validateToken(validateTokenRequest.getToken());
            log.info("username: {}", username);
        } catch (Exception e) {
            log.error("Error on validateToken : {}", e.getMessage());
        }
        
        boolean isValid = false;
        Integer userId = null;
        
        try {
            User user = userService.findByUsername(username);
            if (user != null) {
                isValid = true;
                userId = user.getId();
            }
        } catch (CustomException e) {
            if (e.errorCode.equals(ErrorCode.USERNAME_NOT_FOUND)) {
                log.error("username not found. (username : {})", username);
            } else {
                throw e;
            }
        }
        
        ValidateTokenResponse validateTokenResponse = ValidateTokenResponse.builder().isValid(isValid).userId(userId).build();
        
        // 3. 엔티티를 gRPC response 데이터로 변환한다.
        AuthProto.ValidateTokenResponse response = authMapper.dtoToResponseProto(validateTokenResponse);
        
        // 4. 응답을 클라이언트에게 전달한다.
        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();
    }
}
