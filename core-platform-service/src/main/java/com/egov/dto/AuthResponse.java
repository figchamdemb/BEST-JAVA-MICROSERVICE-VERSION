package com.egov.dto;

import lombok.Builder;
import lombok.Data;
import java.util.Map;

@Data
@Builder
public class AuthResponse {
    private boolean success;
    private String message;
    private String token;
    private String refreshToken;
    private UserDto user;
    private Map<String, Object> data;
    private String tokenType;
    private long expiresIn;
    private boolean twoFactorRequired;
    private String twoFactorType;
    private boolean emailVerified;
    private boolean phoneVerified;

    public static AuthResponse error(String message) {
        return AuthResponse.builder()
                .success(false)
                .message(message)
                .build();
    }

    public static AuthResponse success(String message) {
        return AuthResponse.builder()
                .success(true)
                .message(message)
                .build();
    }
}
