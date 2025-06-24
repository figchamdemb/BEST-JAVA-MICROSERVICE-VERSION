package com.egov.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.egov.entity.Permission;
import com.egov.entity.Role;
import com.egov.entity.User;
import java.util.Optional;
import com.egov.repository.PermissionRepository;
import com.egov.repository.RoleRepository;
import com.egov.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SecurityService implements SecurityOperations {

    // Existing fields and methods remain unchanged

    @Override
    public List<String> getUserPermissions(String token) {
        Optional<User> user = getCurrentUser();
        return user.map(u -> new ArrayList<>(getCurrentUserPermissions()))
                  .orElse(new ArrayList<>());
    }

    @Override
    public void assignRoleToUser(Long userId, String roleName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        Role role = roleRepository.findByRoleName(roleName);
        if (role == null) {
            throw new IllegalArgumentException("Role not found: " + roleName);
        }
        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Override
    public void revokeRoleFromUser(Long userId, String roleName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        Role role = roleRepository.findByRoleName(roleName);
        if (role == null) {
            throw new IllegalArgumentException("Role not found: " + roleName);
        }
        user.getRoles().remove(role);
        userRepository.save(user);
    }

    @Override
    public Page<Object> getAuditTrail(String userId, String action, String startDate, String endDate, Pageable pageable) {
        // TODO: Implement audit trail retrieval
        return Page.empty();
    }

    @Override
    public List<Object> getFailedLoginAttempts(String timeRange) {
        // TODO: Implement failed login attempts retrieval
        return Collections.emptyList();
    }

    @Override
    public Map<String, Object> enableTwoFactorAuth(String token) {
        // TODO: Implement 2FA enablement
        return Collections.emptyMap();
    }

    @Override
    public void disableTwoFactorAuth(String token, String verificationCode) {
        // TODO: Implement 2FA disablement
    }

    @Override
    public boolean verifyTwoFactorCode(String token, String verificationCode) {
        // TODO: Implement 2FA verification
        return false;
    }

    @Override
    public void lockUserAccount(Long userId, String reason) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        user.setLockedUntil(LocalDateTime.now().plusHours(1));
        userRepository.save(user);
    }

    @Override
    public void unlockUserAccount(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        user.setLockedUntil(null);
        userRepository.save(user);
    }

    @Override
    public Map<String, Object> getSecurityDashboard() {
        // TODO: Implement security dashboard
        return Collections.emptyMap();
    }

    @Override
    public void createSecurityAlert(String alertType, String description, Long userId, String severity) {
        // TODO: Implement security alert creation
    }

    @Override
    public List<Object> getActiveSecurityAlerts() {
        // TODO: Implement active alerts retrieval
        return Collections.emptyList();
    }

    @Override
    public void updateSecurityPolicy(Long policyId, Map<String, Object> policyData) {
        // TODO: Implement policy update
    }

    @Override
    public Page<Object> getUserActivityLog(Long userId, String startDate, String endDate, Pageable pageable) {
        // TODO: Implement activity log retrieval
        return Page.empty();
    }

    @Override
    public void forcePasswordReset(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        
        String tempPassword = UUID.randomUUID().toString().substring(0, 8);
        user.setPassword(passwordEncoder.encode(tempPassword));
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        
        log.info("Password forcefully reset for user ID: {}", userId);
    }

    @Override
    public boolean validateIpAddress(String ipAddress, String token) {
        // TODO: Implement IP validation
        return false;
    }

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Get current authenticated user
     */
    public Optional<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        String username = null;
        if (authentication.getPrincipal() instanceof UserDetails) {
            username = ((UserDetails) authentication.getPrincipal()).getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            username = (String) authentication.getPrincipal();
        }

        if (username != null) {
            return userRepository.findByUsername(username);
        }
        return Optional.empty();
    }

    /**
     * Check if current user has specific permission
     */
    public boolean hasPermission(String permissionName) {
        Optional<User> currentUserOpt = getCurrentUser();
        if (!currentUserOpt.isPresent()) {
            return false;
        }
        User currentUser = currentUserOpt.get();

        return currentUser.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .anyMatch(permission -> permission.getPermissionName().equals(permissionName));
    }

    /**
     * Check if current user has any of the specified permissions
     */
    public boolean hasAnyPermission(String... permissionNames) {
        Optional<User> currentUserOpt = getCurrentUser();
        if (!currentUserOpt.isPresent()) {
            return false;
        }
        User currentUser = currentUserOpt.get();

        Set<String> userPermissions = currentUser.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .map(Permission::getPermissionName)
                .collect(Collectors.toSet());

        return Arrays.stream(permissionNames)
                .anyMatch(userPermissions::contains);
    }

    /**
     * Check if current user has specific role
     */
    public boolean hasRole(String roleName) {
        Optional<User> currentUserOpt = getCurrentUser();
        if (!currentUserOpt.isPresent()) {
            return false;
        }
        User currentUser = currentUserOpt.get();

        return currentUser.getRoles().stream()
                .anyMatch(role -> role.getRoleName().equals(roleName));
    }

    /**
     * Check if current user has any of the specified roles
     */
    public boolean hasAnyRole(String... roleNames) {
        Optional<User> currentUserOpt = getCurrentUser();
        if (!currentUserOpt.isPresent()) {
            return false;
        }
        User currentUser = currentUserOpt.get();

        Set<String> userRoles = currentUser.getRoles().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toSet());

        return Arrays.stream(roleNames)
                .anyMatch(userRoles::contains);
    }

    /**
     * Get all permissions for current user
     */
    public Set<String> getCurrentUserPermissions() {
        Optional<User> currentUserOpt = getCurrentUser();
        if (!currentUserOpt.isPresent()) {
            return Collections.emptySet();
        }
        User currentUser = currentUserOpt.get();

        return currentUser.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .map(Permission::getPermissionName)
                .collect(Collectors.toSet());
    }

    /**
     * Get all roles for current user
     */
    public Set<String> getCurrentUserRoles() {
        Optional<User> currentUserOpt = getCurrentUser();
        if (!currentUserOpt.isPresent()) {
            return Collections.emptySet();
        }
        User currentUser = currentUserOpt.get();

        return currentUser.getRoles().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toSet());
    }

    /**
     * Create a new user with encrypted password
     */
    public Optional<User> createUser(String username, String email, String rawPassword, Set<String> roleNames) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username already exists: " + username);
        }

        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email already exists: " + email);
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setStatus("ACTIVE");
        user.setLockedUntil(null);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        // Assign roles
        Set<Role> roles = roleNames.stream()
                .map(roleName -> {
                    Role role = roleRepository.findByRoleName(roleName);
                    if (role == null) {
                        throw new IllegalArgumentException("Role not found: " + roleName);
                    }
                    return role;
                })
                .collect(Collectors.toSet());
        user.setRoles(roles);

        return Optional.of(userRepository.save(user));
    }

    /**
     * Update user password
     */
    public void updatePassword(String username, String newPassword) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (!userOpt.isPresent()) {
            throw new IllegalArgumentException("User not found: " + username);
        }
        User user = userOpt.get();
        
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        
        log.info("Password updated for user: {}", username);
    }

    /**
     * Assign role to user
     */
    public void assignRoleToUser(String username, String roleName) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (!userOpt.isPresent()) {
            throw new IllegalArgumentException("User not found: " + username);
        }
        User user = userOpt.get();
        
        Role role = roleRepository.findByRoleName(roleName);
        if (role == null) {
            throw new IllegalArgumentException("Role not found: " + roleName);
        }

        user.getRoles().add(role);
        userRepository.save(user);
        
        log.info("Role '{}' assigned to user '{}'", roleName, username);
    }

    /**
     * Remove role from user
     */
    public void removeRoleFromUser(String username, String roleName) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (!userOpt.isPresent()) {
            throw new IllegalArgumentException("User not found: " + username);
        }
        User user = userOpt.get();
        
        Role role = roleRepository.findByRoleName(roleName);
        if (role == null) {
            throw new IllegalArgumentException("Role not found: " + roleName);
        }

        user.getRoles().remove(role);
        userRepository.save(user);
        
        log.info("Role '{}' removed from user '{}'", roleName, username);
    }

    /**
     * Lock user account
     */
    public void lockUser(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (!userOpt.isPresent()) {
            throw new IllegalArgumentException("User not found: " + username);
        }
        User user = userOpt.get();
        
        user.setLockedUntil(LocalDateTime.now().plusHours(1));
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        
        log.info("User account locked: {}", username);
    }

    /**
     * Unlock user account
     */
    public void unlockUser(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (!userOpt.isPresent()) {
            throw new IllegalArgumentException("User not found: " + username);
        }
        User user = userOpt.get();
        
        user.setLockedUntil(null);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        
        log.info("User account unlocked: {}", username);
    }

    /**
     * Enable user account
     */
    public void enableUser(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (!userOpt.isPresent()) {
            throw new IllegalArgumentException("User not found: " + username);
        }
        User user = userOpt.get();
        
        user.setStatus("ACTIVE");
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        
        log.info("User account enabled: {}", username);
    }

    /**
     * Disable user account
     */
    public void disableUser(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (!userOpt.isPresent()) {
            throw new IllegalArgumentException("User not found: " + username);
        }
        User user = userOpt.get();
        
        user.setStatus("INACTIVE");
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        
        log.info("User account disabled: {}", username);
    }

    /**
     * Create a new role
     */
    public Optional<Role> createRole(String roleName, String description, Set<String> permissionNames) {
        if (roleRepository.findByRoleName(roleName) != null) {
            throw new IllegalArgumentException("Role already exists: " + roleName);
        }

        Role role = new Role();
        role.setRoleName(roleName);
        role.setDescription(description);
        // Role entity doesn't track timestamps

        // Assign permissions
        Set<Permission> permissions = permissionNames.stream()
                .map(permissionName -> {
                    Permission permission = permissionRepository.findByPermissionName(permissionName);
                    if (permission == null) {
                        throw new IllegalArgumentException("Permission not found: " + permissionName);
                    }
                    return permission;
                })
                .collect(Collectors.toSet());
        role.setPermissions(permissions);

        return Optional.of(roleRepository.save(role));
    }

    /**
     * Create a new permission
     */
    public Optional<Permission> createPermission(String permissionName, String description, String resourceType, String actionType) {
        if (permissionRepository.findByPermissionName(permissionName) != null) {
            throw new IllegalArgumentException("Permission already exists: " + permissionName);
        }

        Permission permission = new Permission();
        permission.setPermissionName(permissionName);
        permission.setDescription(description);
        // Permission entity doesn't have resourceType/actionType fields
        // Permission entity doesn't track timestamps

        return Optional.of(permissionRepository.save(permission));
    }

    /**
     * Check if user is super admin
     */
    public boolean isSuperAdmin() {
        return hasRole("SUPER_ADMIN");
    }

    /**
     * Check if user is admin
     */
    public boolean isAdmin() {
        return hasAnyRole("SUPER_ADMIN", "ADMIN");
    }

    /**
     * Get user by username
     */
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Get user by email
     */
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Check if current user owns the resource
     */
    public boolean ownsResource(Long resourceOwnerId) {
        Optional<User> currentUserOpt = getCurrentUser();
        return currentUserOpt.isPresent() && 
               currentUserOpt.get().getUserId().equals(resourceOwnerId);
    }

    /**
     * Check if current user can access resource
     */
    public boolean canAccessResource(String permission, Long resourceOwnerId) {
        return hasPermission(permission) || ownsResource(resourceOwnerId);
    }

    /**
     * Get security audit info for current user
     */
    public Map<String, Object> getSecurityAuditInfo() {
        Optional<User> currentUserOpt = getCurrentUser();
        if (!currentUserOpt.isPresent()) {
            return Collections.emptyMap();
        }
        User currentUser = currentUserOpt.get();

        Map<String, Object> auditInfo = new HashMap<>();
        auditInfo.put("userId", currentUser.getUserId());
        auditInfo.put("username", currentUser.getUsername());
        auditInfo.put("email", currentUser.getEmail());
        auditInfo.put("roles", getCurrentUserRoles());
        auditInfo.put("permissions", getCurrentUserPermissions());
        auditInfo.put("enabled", currentUser.isActive());
        auditInfo.put("accountNonLocked", !currentUser.isLocked());
        auditInfo.put("lastLogin", currentUser.getLastLoginAt());
        
        return auditInfo;
    }
}
