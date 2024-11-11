package com.yhkim.domain.auth.entity;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "refresh-token", timeToLive = 30 * 24 * 60 * 60 * 1000L)
public class RefreshToken {
    
    @Id
    @Column(unique = true, nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String token;
    
    public void setToken(String token) {
        this.token = token;
    }
}
