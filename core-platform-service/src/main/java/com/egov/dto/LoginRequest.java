/**
 * FILE LOCATION: core-platform-service/src/main/java/com/egov/core/dto/LoginRequest.java
 * PURPOSE: Login request DTO for user authentication
 * MODULE: Core Platform Service (Foundation Service)
 * CREATED BY: Elite Java Developer
 */

package com.egov.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Login Request DTO - Core Platform Service
 * Data Transfer Object for user authentication requests
 */
public class LoginRequest {
    
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;
    
    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    private String password;
    
    private String deviceId;
    private String tenantId;
    private String loginType; // EMAIL, PHONE, USERNAME
    private String twoFactorCode;
    private boolean rememberMe;
    private String ipAddress;
    private String userAgent;

    // Default Constructor
    public LoginRequest() {}

    // Constructor with required fields
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Constructor with all fields
    public LoginRequest(String username, String password, String deviceId, String tenantId, 
                       String loginType, String twoFactorCode, boolean rememberMe) {
        this.username = username;
        this.password = password;
        this.deviceId = deviceId;
        this.tenantId = tenantId;
        this.loginType = loginType;
        this.twoFactorCode = twoFactorCode;
        this.rememberMe = rememberMe;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getTwoFactorCode() {
        return twoFactorCode;
    }

    public void setTwoFactorCode(String twoFactorCode) {
        this.twoFactorCode = twoFactorCode;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    // Security method to clear sensitive data
    public void clearSensitiveData() {
        this.password = null;
        this.twoFactorCode = null;
    }

    // toString method (excluding sensitive data)
    @Override
    public String toString() {
        return "LoginRequest{" +
                "username='" + username + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", tenantId='" + tenantId + '\'' +
                ", loginType='" + loginType + '\'' +
                ", rememberMe=" + rememberMe +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }

    // Builder Pattern for easy object creation
    public static class Builder {
        private String username;
        private String password;
        private String deviceId;
        private String tenantId;
        private String loginType;
        private String twoFactorCode;
        private boolean rememberMe;
        private String ipAddress;
        private String userAgent;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder deviceId(String deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        public Builder tenantId(String tenantId) {
            this.tenantId = tenantId;
            return this;
        }

        public Builder loginType(String loginType) {
            this.loginType = loginType;
            return this;
        }

        public Builder twoFactorCode(String twoFactorCode) {
            this.twoFactorCode = twoFactorCode;
            return this;
        }

        public Builder rememberMe(boolean rememberMe) {
            this.rememberMe = rememberMe;
            return this;
        }

        public Builder ipAddress(String ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }

        public Builder userAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        public LoginRequest build() {
            LoginRequest request = new LoginRequest();
            request.username = this.username;
            request.password = this.password;
            request.deviceId = this.deviceId;
            request.tenantId = this.tenantId;
            request.loginType = this.loginType;
            request.twoFactorCode = this.twoFactorCode;
            request.rememberMe = this.rememberMe;
            request.ipAddress = this.ipAddress;
            request.userAgent = this.userAgent;
            return request;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
