package com.yhkim.grpc.mapper;

import com.yhkim.grpc.auth.AuthProto;
import com.yhkim.grpc.dto.ValidateTokenRequest;
import com.yhkim.grpc.dto.ValidateTokenResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


/**
 * componentModel="spring"을 통해서 spring container에 Bean으로 등록 해 준다. (외부에서 주입받아서 사용하면 된다.)
 * unmappedTargetPolicy IGNORE 만약, target class에 매핑되지 않는 필드가 있으면, null로 넣게 되고, 따로 report하지 않는다.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {
    // gRPC request를 DTO로 변환
    ValidateTokenRequest requestProtoToDto(AuthProto.ValidateTokenRequest validateTokenRequest);
    
    // DTO를 gRPC response로 변환
    AuthProto.ValidateTokenResponse dtoToResponseProto(ValidateTokenResponse validateTokenResponse);
}
