package com.yhkim.auth.service;

import com.yhkim.auth.dto.ReissueTokenResponse;

public interface AuthService {
    
    ReissueTokenResponse refresh(String refreshToken);
}
