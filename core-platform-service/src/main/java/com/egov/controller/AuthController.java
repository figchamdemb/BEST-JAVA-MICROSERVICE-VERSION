/**
 * FILE LOCATION: core-platform-service/src/main/java/com/egov/core/controller/AuthController.java
 * PURPOSE: Authentication controller for login, logout, token refresh, and user registration
 * MODULE: Core Platform Service (Foundation Service)
 * CREATED BY: Elite Java Developer
 */

 package com.egov.controller;

import com.egov.dto.AuthResponse;
import com.egov.dto.LoginRequest;
import com.egov.dto.UserDto;
import com.egov.service.AuthService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.*;
 
 import javax.validation.Valid;
 
 /**
  * Authentication Controller - Core Platform Service
  * Handles all authentication-related operations including login, logout, and token management
  */
 @RestController
 @RequestMapping("/api/auth")
 @CrossOrigin(origins = "*")
 public class AuthController {
 
     @Autowired
     private AuthService authService;
 
     /**
      * User Login Endpoint
      * Authenticates user credentials and returns JWT token
      */
     @PostMapping("/login")
     public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
         try {
             AuthResponse response = authService.authenticateUser(loginRequest);
             return ResponseEntity.ok(response);
         } catch (Exception e) {
             return ResponseEntity.badRequest()
                 .body(AuthResponse.builder()
                     .success(false)
                     .message("Authentication failed: " + e.getMessage())
                     .build());
         }
     }
 
     /**
      * User Registration Endpoint
      * Creates new user account with basic information
      */
     @PostMapping("/register")
     public ResponseEntity<AuthResponse> register(@Valid @RequestBody UserDto userDto) {
         try {
             AuthResponse response = authService.registerUser(userDto);
             return ResponseEntity.ok(response);
         } catch (Exception e) {
             return ResponseEntity.badRequest()
                 .body(AuthResponse.builder()
                     .success(false)
                     .message("Registration failed: " + e.getMessage())
                     .build());
         }
     }
 
     /**
      * Token Refresh Endpoint
      * Refreshes expired JWT tokens using refresh token
      */
     @PostMapping("/refresh-token")
     public ResponseEntity<AuthResponse> refreshToken(@RequestParam String refreshToken) {
         try {
             AuthResponse response = authService.refreshToken(refreshToken);
             return ResponseEntity.ok(response);
         } catch (Exception e) {
             return ResponseEntity.badRequest()
                 .body(AuthResponse.builder()
                     .success(false)
                     .message("Token refresh failed: " + e.getMessage())
                     .build());
         }
     }
 
     /**
      * User Logout Endpoint
      * Invalidates user session and JWT token
      */
     @PostMapping("/logout")
     public ResponseEntity<AuthResponse> logout(@RequestHeader("Authorization") String token) {
         try {
             authService.logoutUser(token);
             return ResponseEntity.ok(AuthResponse.builder()
                 .success(true)
                 .message("Logout successful")
                 .build());
         } catch (Exception e) {
             return ResponseEntity.badRequest()
                 .body(AuthResponse.builder()
                     .success(false)
                     .message("Logout failed: " + e.getMessage())
                     .build());
         }
     }
 
     /**
      * Verify Phone Number Endpoint
      * Sends OTP to phone number for verification
      */
     @PostMapping("/verify-phone")
     public ResponseEntity<AuthResponse> verifyPhone(@RequestParam String phoneNumber) {
         try {
             AuthResponse response = authService.initiatePhoneVerification(phoneNumber);
             return ResponseEntity.ok(response);
         } catch (Exception e) {
             return ResponseEntity.badRequest()
                 .body(AuthResponse.builder()
                     .success(false)
                     .message("Phone verification failed: " + e.getMessage())
                     .build());
         }
     }
 
     /**
      * Verify Token Endpoint
      * Validates JWT token and returns user information
      */
     @GetMapping("/verify-token")
     public ResponseEntity<AuthResponse> verifyToken(@RequestHeader("Authorization") String token) {
         try {
             AuthResponse response = authService.verifyToken(token);
             return ResponseEntity.ok(response);
         } catch (Exception e) {
             return ResponseEntity.badRequest()
                 .body(AuthResponse.builder()
                     .success(false)
                     .message("Token verification failed: " + e.getMessage())
                     .build());
         }
     }
 
     /**
      * Get Current User Information
      * Returns authenticated user's profile information
      */
     @GetMapping("/me")
     public ResponseEntity<UserDto> getCurrentUser(@RequestHeader("Authorization") String token) {
         try {
             UserDto userDto = authService.getCurrentUser(token);
             return ResponseEntity.ok(userDto);
         } catch (Exception e) {
             return ResponseEntity.badRequest().build();
         }
     }
 
     /**
      * Change Password Endpoint
      * Allows authenticated users to change their password
      */
     @PutMapping("/change-password")
     public ResponseEntity<AuthResponse> changePassword(
             @RequestHeader("Authorization") String token,
             @RequestParam String currentPassword,
             @RequestParam String newPassword) {
         try {
             AuthResponse response = authService.changePassword(token, currentPassword, newPassword);
             return ResponseEntity.ok(response);
         } catch (Exception e) {
             return ResponseEntity.badRequest()
                 .body(AuthResponse.builder()
                     .success(false)
                     .message("Password change failed: " + e.getMessage())
                     .build());
         }
     }
 
     /**
      * Forgot Password Endpoint
      * Initiates password reset process via email/SMS
      */
     @PostMapping("/forgot-password")
     public ResponseEntity<AuthResponse> forgotPassword(@RequestParam String identifier) {
         try {
             AuthResponse response = authService.initiateForgotPassword(identifier);
             return ResponseEntity.ok(response);
         } catch (Exception e) {
             return ResponseEntity.badRequest()
                 .body(AuthResponse.builder()
                     .success(false)
                     .message("Password reset initiation failed: " + e.getMessage())
                     .build());
         }
     }
 
     /**
      * Reset Password Endpoint
      * Completes password reset using reset token
      */
     @PostMapping("/reset-password")
     public ResponseEntity<AuthResponse> resetPassword(
             @RequestParam String resetToken,
             @RequestParam String newPassword) {
         try {
             AuthResponse response = authService.resetPassword(resetToken, newPassword);
             return ResponseEntity.ok(response);
         } catch (Exception e) {
             return ResponseEntity.badRequest()
                 .body(AuthResponse.builder()
                     .success(false)
                     .message("Password reset failed: " + e.getMessage())
                     .build());
         }
     }
 }
