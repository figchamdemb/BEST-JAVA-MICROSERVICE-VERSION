package com.egov.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
@Data
@Builder(toBuilder = true)
public class UserDto {
    public UserDto() {
    }

    public UserDto(Long userId, String username, String email, String password, String firstName, 
                  String lastName, String middleName, String phoneNumber, String profilePicture, 
                  String status, String userType, List<String> roles, List<String> permissions, 
                  boolean enabled, boolean accountNonExpired, boolean accountNonLocked, 
                  boolean credentialsNonExpired, LocalDateTime lastLoginAt, LocalDateTime createdAt, 
                  LocalDateTime updatedAt, LocalDateTime passwordChangedAt, boolean twoFactorEnabled, 
                  boolean emailVerified, boolean phoneVerified, String tenantId, String tenantCode, 
                  String tenantName, String address, String city, String region, String country, 
                  String postalCode) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.phoneNumber = phoneNumber;
        this.profilePicture = profilePicture;
        this.status = status;
        this.userType = userType;
        this.roles = roles;
        this.permissions = permissions;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.lastLoginAt = lastLoginAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.passwordChangedAt = passwordChangedAt;
        this.twoFactorEnabled = twoFactorEnabled;
        this.emailVerified = emailVerified;
        this.phoneVerified = phoneVerified;
        this.tenantId = tenantId;
        this.tenantCode = tenantCode;
        this.tenantName = tenantName;
        this.address = address;
        this.city = city;
        this.region = region;
        this.country = country;
        this.postalCode = postalCode;
    }
    private Long userId;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private String phoneNumber;
    private String profilePicture;
    private String status;
    private String userType;
    private List<String> roles;
    private List<String> permissions;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private LocalDateTime lastLoginAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime passwordChangedAt;
    private boolean twoFactorEnabled;
    private boolean emailVerified;
    private boolean phoneVerified;
    private String tenantId;
    private String tenantCode;
    private String tenantName;
    private String address;
    private String city;
    private String region;
    private String country;
    private String postalCode;
}
