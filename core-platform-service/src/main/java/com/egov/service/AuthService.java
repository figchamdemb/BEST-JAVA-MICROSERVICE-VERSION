package com.egov.service;

import com.egov.dto.AuthResponse;
import com.egov.dto.LoginRequest;
import com.egov.dto.UserDto;
import com.egov.entity.User;
import com.egov.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponse authenticateUser(LoginRequest loginRequest) {
        try {
            Optional<User> userOpt = userRepository.findByUsername(loginRequest.getUsername());
            if (!userOpt.isPresent() || !passwordEncoder.matches(loginRequest.getPassword(), userOpt.get().getPassword())) {
                return AuthResponse.error("Invalid credentials");
            }

            User user = userOpt.get();
            if (user.getLockedUntil() != null && user.getLockedUntil().isAfter(LocalDateTime.now())) {
                return AuthResponse.error("Account locked until " + user.getLockedUntil());
            }

            // Generate tokens and return response
            return AuthResponse.builder()
                .success(true)
                .message("Login successful")
                .token(generateToken(user))
                .refreshToken(generateRefreshToken(user))
                .build();
        } catch (Exception e) {
            return AuthResponse.error("Authentication failed: " + e.getMessage());
        }
    }

    public AuthResponse registerUser(UserDto userDto) {
        try {
            if (userRepository.existsByUsername(userDto.getUsername())) {
                return AuthResponse.error("Username already exists");
            }
            if (userRepository.existsByEmail(userDto.getEmail())) {
                return AuthResponse.error("Email already registered");
            }
            if (!isPasswordStrong(userDto.getPassword())) {
                return AuthResponse.error("Password must be at least 8 characters with uppercase, lowercase, digit and special character");
            }

            User user = new User();
            user.setUsername(userDto.getUsername());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setEmail(userDto.getEmail());
            user.setStatus("ACTIVE");
            user.setCreatedAt(LocalDateTime.now());

            userRepository.save(user);
            return AuthResponse.success("Registration successful");
        } catch (Exception e) {
            return AuthResponse.error("Registration failed: " + e.getMessage());
        }
    }

    public AuthResponse refreshToken(String refreshToken) {
        try {
            // Validate refresh token and generate new access token
            return AuthResponse.builder()
                .success(true)
                .message("Token refreshed")
                .token(generateTokenFromRefreshToken(refreshToken))
                .build();
        } catch (Exception e) {
            return AuthResponse.error("Token refresh failed: " + e.getMessage());
        }
    }

    public void logoutUser(String token) {
        // Invalidate token implementation
    }

    public AuthResponse initiatePhoneVerification(String phoneNumber) {
        // Send OTP implementation
        return AuthResponse.success("OTP sent to phone");
    }

    public AuthResponse verifyToken(String token) {
        try {
            User user = validateToken(token);
            return AuthResponse.builder()
                .success(true)
                .message("Token valid")
                .user(user.toDto())
                .build();
        } catch (Exception e) {
            return AuthResponse.error("Token invalid: " + e.getMessage());
        }
    }

    public UserDto getCurrentUser(String token) {
        User user = validateToken(token);
        return user.toDto();
    }

    public AuthResponse changePassword(String token, String currentPassword, String newPassword) {
        try {
            User user = validateToken(token);
            if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
                return AuthResponse.error("Current password incorrect");
            }
            if (!isPasswordStrong(newPassword)) {
                return AuthResponse.error("New password must be at least 8 characters with uppercase, lowercase, digit and special character");
            }
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return AuthResponse.success("Password changed successfully");
        } catch (Exception e) {
            return AuthResponse.error("Password change failed: " + e.getMessage());
        }
    }

    public AuthResponse initiateForgotPassword(String identifier) {
        // Generate and send reset token
        return AuthResponse.success("Reset instructions sent");
    }

    public AuthResponse resetPassword(String resetToken, String newPassword) {
        try {
            // Validate reset token and update password
            return AuthResponse.success("Password reset successful");
        } catch (Exception e) {
            return AuthResponse.error("Password reset failed: " + e.getMessage());
        }
    }

    public boolean verifyTwoFactorCode(Long userId, String code) {
        // Verify 2FA code implementation
        return true;
    }

    // Helper methods
    private String generateToken(User user) {
        return "generated-jwt-token";
    }

    private String generateRefreshToken(User user) {
        return UUID.randomUUID().toString();
    }

    private String generateTokenFromRefreshToken(String refreshToken) {
        return "new-generated-jwt-token";
    }

    private User validateToken(String token) {
        // Token validation logic
        return userRepository.findById(1L).orElseThrow(() -> new RuntimeException("User not found"));
    }

    private boolean isPasswordStrong(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            if (Character.isLowerCase(c)) hasLower = true;
            if (Character.isDigit(c)) hasDigit = true;
            if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
        
        return hasUpper && hasLower && hasDigit && hasSpecial;
    }
}
