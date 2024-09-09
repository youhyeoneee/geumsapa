package com.yhkim.domain.auth.service;

import com.yhkim.domain.auth.dto.ReissueTokenResponse;

public interface AuthService {
    
    ReissueTokenResponse refresh(String refreshToken);
}
