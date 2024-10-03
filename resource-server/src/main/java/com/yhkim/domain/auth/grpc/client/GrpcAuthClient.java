package com.yhkim.domain.auth.grpc.client;

import com.yhkim.grpc.auth.AuthProto;
import com.yhkim.grpc.auth.AuthServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * gRPC 클라이언트
 * GrpcClient 클래스는 gRPC 클라이언트를 구현한 것으로, 다른 서버 또는 같은 서버 내에서 gRPC 서버 메서드를 호출하는 데 사용됩니다.
 * 이 클래스는 애플리케이션 내에서 gRPC 서버에 요청을 보내는 역할을 합니다.
 */

@Component
@Slf4j
public class GrpcAuthClient {
    private final AuthServiceGrpc.AuthServiceBlockingStub blockingStub;
    
    // gRPC 서버에 연결 (생성자)
    public GrpcAuthClient() {
        ManagedChannel channel = NettyChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        blockingStub = AuthServiceGrpc.newBlockingStub(channel);
    }
    
    /**
     * 토큰 검증
     *
     * @param request
     * @return
     */
    public AuthProto.ValidateTokenResponse validate(String token) {
        AuthProto.ValidateTokenRequest request = AuthProto.ValidateTokenRequest.newBuilder().setToken(token).build();
        
        log.info("Created request: {}", request);
        try {
            AuthProto.ValidateTokenResponse response = blockingStub.validateToken(request);
            log.info("Received response: {}", response);
            return response;
        } catch (Exception e) {
            log.error("Error during gRPC call", e);
            throw e;
        }
    }
    
}
