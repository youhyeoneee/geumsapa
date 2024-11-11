package com.yhkim.domain.auth.repository;

import com.yhkim.domain.auth.entity.RefreshToken;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataRedisTest
@TestPropertySource(properties = {
        "spring.data.redis.host=localhost",
        "spring.data.redis.port=6379"
})
class RefreshTokenRepositoryTest {
    
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    
    @Test
    @DisplayName("Redis 저장 테스트")
    void test() {
        RefreshToken refreshToken = RefreshToken.builder().username("test").token("testtoken").build();
        refreshTokenRepository.save(refreshToken);
        
        RefreshToken refreshToken2 = RefreshToken.builder().username("test2").token("testtoken2").build();
        refreshTokenRepository.save(refreshToken2);
        
        assertEquals("testtoken", refreshTokenRepository.findById("test").get().getToken());
        assertEquals("testtoken2", refreshTokenRepository.findById("test2").get().getToken());
    }
}
