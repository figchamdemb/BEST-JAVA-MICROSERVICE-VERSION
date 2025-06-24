/**
 * FILE LOCATION: core-platform-service/src/main/java/com/egov/core/controller/SecurityController.java
 * PURPOSE: Security policies and permissions controller for access control and audit management
 * MODULE: Core Platform Service (Foundation Service)
 * CREATED BY: Elite Java Developer
 */

package com.egov.controller;

import com.egov.dto.AuthResponse;
import com.egov.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Security Management Controller - Core Platform Service
 * Handles system-wide security policies, permissions, audit trails, and access control
 */
@RestController
@RequestMapping("/api/security")
@CrossOrigin(origins = "*")
public class SecurityController {

    @Autowired
    private SecurityService securityService;

    /**
     * Get User Permissions
     * Retrieve all permissions for the authenticated user
     */
    @GetMapping("/permissions")
    public ResponseEntity<List<String>> getUserPermissions(@RequestHeader("Authorization") String token) {
        try {
            List<String> permissions = securityService.getUserPermissions(token);
            return ResponseEntity.ok(permissions);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Assign Role to User
     * Admin endpoint to assign roles to users
     */
    @PostMapping("/roles/assign")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<AuthResponse> assignRole(
            @RequestParam Long userId,
            @RequestParam String roleName) {
        try {
            securityService.assignRoleToUser(userId, roleName);
            return ResponseEntity.ok(AuthResponse.builder()
                .success(true)
                .message("Role assigned successfully")
                .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(AuthResponse.builder()
                    .success(false)
                    .message("Role assignment failed: " + e.getMessage())
                    .build());
        }
    }

    /**
     * Revoke Role from User
     * Admin endpoint to revoke roles from users
     */
    @PostMapping("/roles/revoke")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<AuthResponse> revokeRole(
            @RequestParam Long userId,
            @RequestParam String roleName) {
        try {
            securityService.revokeRoleFromUser(userId, roleName);
            return ResponseEntity.ok(AuthResponse.builder()
                .success(true)
                .message("Role revoked successfully")
                .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(AuthResponse.builder()
                    .success(false)
                    .message("Role revocation failed: " + e.getMessage())
                    .build());
        }
    }

    /**
     * Get Security Audit Trail
     * Retrieve security audit logs with filtering options
     */
    @GetMapping("/audit-trail")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('AUDITOR')")
    public ResponseEntity<Page<Object>> getAuditTrail(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String action,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            Pageable pageable) {
        try {
            Page<Object> auditLogs = securityService.getAuditTrail(userId, action, startDate, endDate, pageable);
            return ResponseEntity.ok(auditLogs);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Get Failed Login Attempts
     * Monitor failed authentication attempts for security analysis
     */
    @GetMapping("/failed-logins")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('SECURITY_OFFICER')")
    public ResponseEntity<List<Object>> getFailedLoginAttempts(
            @RequestParam(required = false) String timeRange) {
        try {
            List<Object> failedLogins = securityService.getFailedLoginAttempts(timeRange);
            return ResponseEntity.ok(failedLogins);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Enable Two-Factor Authentication
     * Enable 2FA for user account
     */
    @PostMapping("/2fa/enable")
    public ResponseEntity<AuthResponse> enableTwoFactorAuth(@RequestHeader("Authorization") String token) {
        try {
            Map<String, Object> qrData = securityService.enableTwoFactorAuth(token);
            return ResponseEntity.ok(AuthResponse.builder()
                .success(true)
                .message("Two-factor authentication enabled")
                .data(qrData)
                .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(AuthResponse.builder()
                    .success(false)
                    .message("2FA setup failed: " + e.getMessage())
                    .build());
        }
    }

    /**
     * Disable Two-Factor Authentication
     * Disable 2FA for user account
     */
    @PostMapping("/2fa/disable")
    public ResponseEntity<AuthResponse> disableTwoFactorAuth(
            @RequestHeader("Authorization") String token,
            @RequestParam String verificationCode) {
        try {
            securityService.disableTwoFactorAuth(token, verificationCode);
            return ResponseEntity.ok(AuthResponse.builder()
                .success(true)
                .message("Two-factor authentication disabled")
                .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(AuthResponse.builder()
                    .success(false)
                    .message("2FA disable failed: " + e.getMessage())
                    .build());
        }
    }

    /**
     * Verify Two-Factor Authentication Code
     * Verify 2FA code during login or sensitive operations
     */
    @PostMapping("/2fa/verify")
    public ResponseEntity<AuthResponse> verifyTwoFactorCode(
            @RequestHeader("Authorization") String token,
            @RequestParam String verificationCode) {
        try {
            boolean isValid = securityService.verifyTwoFactorCode(token, verificationCode);
            return ResponseEntity.ok(AuthResponse.builder()
                .success(isValid)
                .message(isValid ? "Code verified successfully" : "Invalid verification code")
                .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(AuthResponse.builder()
                    .success(false)
                    .message("Code verification failed: " + e.getMessage())
                    .build());
        }
    }

    /**
     * Lock User Account
     * Admin endpoint to lock user accounts for security reasons
     */
    @PostMapping("/users/{userId}/lock")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('SECURITY_OFFICER')")
    public ResponseEntity<AuthResponse> lockUserAccount(
            @PathVariable Long userId,
            @RequestParam String reason) {
        try {
            securityService.lockUserAccount(userId, reason);
            return ResponseEntity.ok(AuthResponse.builder()
                .success(true)
                .message("User account locked successfully")
                .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(AuthResponse.builder()
                    .success(false)
                    .message("Account lock failed: " + e.getMessage())
                    .build());
        }
    }

    /**
     * Unlock User Account
     * Admin endpoint to unlock previously locked user accounts
     */
    @PostMapping("/users/{userId}/unlock")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('SECURITY_OFFICER')")
    public ResponseEntity<AuthResponse> unlockUserAccount(@PathVariable Long userId) {
        try {
            securityService.unlockUserAccount(userId);
            return ResponseEntity.ok(AuthResponse.builder()
                .success(true)
                .message("User account unlocked successfully")
                .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(AuthResponse.builder()
                    .success(false)
                    .message("Account unlock failed: " + e.getMessage())
                    .build());
        }
    }

    /**
     * Get Security Dashboard
     * Get security overview and metrics for administrators
     */
    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('SECURITY_OFFICER')")
    public ResponseEntity<Map<String, Object>> getSecurityDashboard() {
        try {
            Map<String, Object> dashboard = securityService.getSecurityDashboard();
            return ResponseEntity.ok(dashboard);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Create Security Alert
     * Create security alerts for suspicious activities
     */
    @PostMapping("/alerts")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('SECURITY_OFFICER')")
    public ResponseEntity<AuthResponse> createSecurityAlert(
            @RequestParam String alertType,
            @RequestParam String description,
            @RequestParam(required = false) Long userId,
            @RequestParam String severity) {
        try {
            securityService.createSecurityAlert(alertType, description, userId, severity);
            return ResponseEntity.ok(AuthResponse.builder()
                .success(true)
                .message("Security alert created successfully")
                .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(AuthResponse.builder()
                    .success(false)
                    .message("Alert creation failed: " + e.getMessage())
                    .build());
        }
    }

    /**
     * Get Active Security Alerts
     * Retrieve all active security alerts
     */
    @GetMapping("/alerts/active")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('SECURITY_OFFICER')")
    public ResponseEntity<List<Object>> getActiveSecurityAlerts() {
        try {
            List<Object> alerts = securityService.getActiveSecurityAlerts();
            return ResponseEntity.ok(alerts);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Update Security Policy
     * Admin endpoint to update system security policies
     */
    @PutMapping("/policies/{policyId}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<AuthResponse> updateSecurityPolicy(
            @PathVariable Long policyId,
            @RequestBody Map<String, Object> policyData) {
        try {
            securityService.updateSecurityPolicy(policyId, policyData);
            return ResponseEntity.ok(AuthResponse.builder()
                .success(true)
                .message("Security policy updated successfully")
                .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(AuthResponse.builder()
                    .success(false)
                    .message("Policy update failed: " + e.getMessage())
                    .build());
        }
    }

    /**
     * Get User Activity Log
     * Retrieve detailed activity log for a specific user
     */
    @GetMapping("/users/{userId}/activity")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('AUDITOR')")
    public ResponseEntity<Page<Object>> getUserActivityLog(
            @PathVariable Long userId,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            Pageable pageable) {
        try {
            Page<Object> activityLog = securityService.getUserActivityLog(userId, startDate, endDate, pageable);
            return ResponseEntity.ok(activityLog);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Force Password Reset
     * Admin endpoint to force password reset for user accounts
     */
    @PostMapping("/users/{userId}/force-password-reset")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('SECURITY_OFFICER')")
    public ResponseEntity<AuthResponse> forcePasswordReset(@PathVariable Long userId) {
        try {
            securityService.forcePasswordReset(userId);
            return ResponseEntity.ok(AuthResponse.builder()
                .success(true)
                .message("Password reset forced successfully")
                .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(AuthResponse.builder()
                    .success(false)
                    .message("Force password reset failed: " + e.getMessage())
                    .build());
        }
    }

    /**
     * Validate IP Address
     * Check if an IP address is allowed for a user/tenant
     */
    @GetMapping("/validate-ip")
    public ResponseEntity<AuthResponse> validateIpAddress(
            @RequestParam String ipAddress,
            @RequestHeader("Authorization") String token) {
        try {
            boolean isValid = securityService.validateIpAddress(ipAddress, token);
            return ResponseEntity.ok(AuthResponse.builder()
                .success(isValid)
                .message(isValid ? "IP address is valid" : "IP address is not allowed")
                .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(AuthResponse.builder()
                    .success(false)
                    .message("IP validation failed: " + e.getMessage())
                    .build());
        }
    }
}
