package com.egov.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;

public interface SecurityOperations {
    List<String> getUserPermissions(String token);
    void assignRoleToUser(Long userId, String roleName);
    void revokeRoleFromUser(Long userId, String roleName);
    Page<Object> getAuditTrail(String userId, String action, String startDate, String endDate, Pageable pageable);
    List<Object> getFailedLoginAttempts(String timeRange);
    Map<String, Object> enableTwoFactorAuth(String token);
    void disableTwoFactorAuth(String token, String verificationCode);
    boolean verifyTwoFactorCode(String token, String verificationCode);
    void lockUserAccount(Long userId, String reason);
    void unlockUserAccount(Long userId);
    Map<String, Object> getSecurityDashboard();
    void createSecurityAlert(String alertType, String description, Long userId, String severity);
    List<Object> getActiveSecurityAlerts();
    void updateSecurityPolicy(Long policyId, Map<String, Object> policyData);
    Page<Object> getUserActivityLog(Long userId, String startDate, String endDate, Pageable pageable);
    void forcePasswordReset(Long userId);
    boolean validateIpAddress(String ipAddress, String token);
}
