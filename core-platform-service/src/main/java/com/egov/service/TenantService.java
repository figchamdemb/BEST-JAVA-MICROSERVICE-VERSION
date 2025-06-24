// ====================================================================
// FILE: TenantService.java (COMPLETE IMPLEMENTATION)
// ====================================================================

package com.egov.service;

import com.egov.dto.TenantDto;
import com.egov.dto.CreateTenantRequest;
import com.egov.dto.UpdateTenantRequest;
import com.egov.entity.Tenant;
import com.egov.entity.TenantConfiguration;
import com.egov.repository.TenantRepository;
import com.egov.repository.TenantConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Tenant Management Service - Complete Implementation
 * Handles multi-tenant organization management with full configuration
 */
@Service
@Transactional
public class TenantService {

    @Autowired
    private TenantRepository tenantRepository;
    
    @Autowired
    private TenantConfigurationRepository tenantConfigRepository;

    /**
     * Create new tenant organization with comprehensive setup
     */
    public TenantDto createTenant(CreateTenantRequest request) {
        try {
            // Validate tenant creation request
            validateTenantCreationRequest(request);
            
            // Check tenant code uniqueness
            if (tenantRepository.existsByTenantCode(request.getTenantCode())) {
                throw new RuntimeException("Tenant code already exists");
            }
            
            // Check tenant name uniqueness
            if (tenantRepository.existsByTenantName(request.getTenantName())) {
                throw new RuntimeException("Tenant name already exists");
            }
            
            // Create tenant entity
            Tenant tenant = new Tenant();
            tenant.setTenantCode(request.getTenantCode().toUpperCase());
            tenant.setTenantName(request.getTenantName());
            tenant.setTenantType(request.getTenantType());
            tenant.setDescription(request.getDescription());
            tenant.setContactEmail(request.getContactEmail());
            tenant.setContactPhone(request.getContactPhone());
            tenant.setAddress(request.getAddress());
            tenant.setStatus("ACTIVE");
            tenant.setMaxUsers(request.getMaxUsers() != null ? request.getMaxUsers() : 1000);
            tenant.setMaxStorage(request.getMaxStorage() != null ? request.getMaxStorage() : 10737418240L); // 10GB
            tenant.setCreatedAt(LocalDateTime.now());
            tenant.setUpdatedAt(LocalDateTime.now());
            
            // Set subscription details
            tenant.setSubscriptionType(request.getSubscriptionType() != null ? request.getSubscriptionType() : "BASIC");
            tenant.setSubscriptionStartDate(LocalDateTime.now());
            tenant.setSubscriptionEndDate(LocalDateTime.now().plusYears(1)); // Default 1 year
            
            // Save tenant
            Tenant savedTenant = tenantRepository.save(tenant);
            
            // Create default configurations
            createDefaultTenantConfigurations(savedTenant);
            
            // Log tenant creation
            auditTenantEvent(savedTenant.getTenantId(), "TENANT_CREATED", true);
            
            return convertToDto(savedTenant);
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to create tenant: " + e.getMessage());
        }
    }

    /**
     * Get tenant by ID with configuration details
     */
    public TenantDto getTenantById(Long tenantId) {
        Optional<Tenant> tenantOpt = tenantRepository.findById(tenantId);
        if (!tenantOpt.isPresent()) {
            throw new RuntimeException("Tenant not found");
        }
        
        return convertToDto(tenantOpt.get());
    }

    /**
     * Get tenant by code
     */
    public TenantDto getTenantByCode(String tenantCode) {
        Optional<Tenant> tenantOpt = tenantRepository.findByTenantCode(tenantCode.toUpperCase());
        if (!tenantOpt.isPresent()) {
            throw new RuntimeException("Tenant not found");
        }
        
        return convertToDto(tenantOpt.get());
    }

    /**
     * Get all tenants with filtering and pagination
     */
    public Page<TenantDto> getAllTenants(Pageable pageable, String status, String tenantType) {
        try {
            Page<Tenant> tenants;
            
            if (status != null && tenantType != null) {
                tenants = tenantRepository.findByStatusAndTenantType(status, tenantType, pageable);
            } else if (status != null) {
                tenants = tenantRepository.findByStatus(status, pageable);
            } else if (tenantType != null) {
                tenants = tenantRepository.findByTenantType(tenantType, pageable);
            } else {
                tenants = tenantRepository.findAll(pageable);
            }
            
            return tenants.map(this::convertToDto);
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve tenants: " + e.getMessage());
        }
    }

    /**
     * Get all active tenants
     */
    public List<TenantDto> getAllActiveTenants() {
        List<Tenant> tenants = tenantRepository.findByStatus("ACTIVE");
        return tenants.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Update tenant information
     */
    public TenantDto updateTenant(Long tenantId, UpdateTenantRequest request) {
        try {
            Optional<Tenant> tenantOpt = tenantRepository.findById(tenantId);
            if (!tenantOpt.isPresent()) {
                throw new RuntimeException("Tenant not found");
            }
            
            Tenant tenant = tenantOpt.get();
            
            // Update fields if provided
            if (request.getTenantName() != null) {
                // Check name uniqueness if changed
                if (!request.getTenantName().equals(tenant.getTenantName()) &&
                    tenantRepository.existsByTenantName(request.getTenantName())) {
                    throw new RuntimeException("Tenant name already exists");
                }
                tenant.setTenantName(request.getTenantName());
            }
            
            if (request.getDescription() != null) tenant.setDescription(request.getDescription());
            if (request.getContactEmail() != null) tenant.setContactEmail(request.getContactEmail());
            if (request.getContactPhone() != null) tenant.setContactPhone(request.getContactPhone());
            if (request.getAddress() != null) tenant.setAddress(request.getAddress());
            if (request.getStatus() != null) tenant.setStatus(request.getStatus());
            if (request.getMaxUsers() != null) tenant.setMaxUsers(request.getMaxUsers());
            if (request.getStorageLimit() != null) tenant.setMaxStorage(request.getStorageLimit());
            if (request.getSubscriptionType() != null) tenant.setSubscriptionType(request.getSubscriptionType());
            
            tenant.setUpdatedAt(LocalDateTime.now());
            
            Tenant savedTenant = tenantRepository.save(tenant);
            
            // Log tenant update
            auditTenantEvent(tenantId, "TENANT_UPDATED", true);
            
            return convertToDto(savedTenant);
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to update tenant: " + e.getMessage());
        }
    }

    /**
     * Update tenant configuration
     */
    public void updateTenantConfiguration(Long tenantId, Map<String, Object> configUpdates) {
        try {
            Optional<Tenant> tenantOpt = tenantRepository.findById(tenantId);
            if (!tenantOpt.isPresent()) {
                throw new RuntimeException("Tenant not found");
            }
            
            for (Map.Entry<String, Object> entry : configUpdates.entrySet()) {
                String configKey = entry.getKey();
                Object configValue = entry.getValue();
                
                List<TenantConfiguration> configList = tenantConfigRepository
                    .findByTenantIdAndConfigKey(tenantId, configKey);
                
                TenantConfiguration config;
                if (!configList.isEmpty()) {
                    config = configList.get(0);
                } else {
                    config = new TenantConfiguration();
                    config.setTenantId(tenantId);
                    config.setConfigKey(configKey);
                    config.setCreatedAt(LocalDateTime.now());
                }
                
                config.setConfigValue(configValue.toString());
                config.setUpdatedAt(LocalDateTime.now());
                
                tenantConfigRepository.save(config);
            }
            
            // Log configuration update
            auditTenantEvent(tenantId, "TENANT_CONFIG_UPDATED", true);
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to update tenant configuration: " + e.getMessage());
        }
    }

    /**
     * Get tenant configuration
     */
    public Map<String, Object> getTenantConfiguration(Long tenantId) {
        List<TenantConfiguration> configs = tenantConfigRepository.findByTenantId(tenantId);
        
        return configs.stream()
            .collect(Collectors.toMap(
                TenantConfiguration::getConfigKey,
                config -> config.getConfigValue()  // Return raw value instead of string
            ));
    }

    /**
     * Activate tenant
     */
    public void activateTenant(Long tenantId) {
        updateTenantStatus(tenantId, "ACTIVE");
    }

    /**
     * Suspend tenant
     */
    public void suspendTenant(Long tenantId, String reason) {
        try {
            updateTenantStatus(tenantId, "SUSPENDED");
            
            // Add suspension reason to configuration
            if (reason != null) {
                Map<String, Object> config = Map.of(
                    "suspension_reason", reason,
                    "suspension_date", LocalDateTime.now().toString()
                );
                updateTenantConfiguration(tenantId, config);
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to suspend tenant: " + e.getMessage());
        }
    }

    /**
     * Delete tenant (soft delete)
     */
    public void deleteTenant(Long tenantId) {
        try {
            Optional<Tenant> tenantOpt = tenantRepository.findById(tenantId);
            if (!tenantOpt.isPresent()) {
                throw new RuntimeException("Tenant not found");
            }
            
            Tenant tenant = tenantOpt.get();
            tenant.setStatus("DELETED");
            tenant.setDeletedAt(LocalDateTime.now());
            tenant.setUpdatedAt(LocalDateTime.now());
            
            tenantRepository.save(tenant);
            
            // Log tenant deletion
            auditTenantEvent(tenantId, "TENANT_DELETED", true);
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete tenant: " + e.getMessage());
        }
    }

    /**
     * Get tenant configuration schema by type
     */
    public Map<String, Object> getTenantConfigSchema(String tenantType) {
        try {
            // Define base schema common to all tenant types
            Map<String, Object> baseSchema = new HashMap<>();
            baseSchema.put("timezone", Map.of(
                "type", "string",
                "default", "UTC",
                "required", true
            ));
            baseSchema.put("language", Map.of(
                "type", "string",
                "default", "en",
                "required", true
            ));
            baseSchema.put("max_login_attempts", Map.of(
                "type", "number",
                "default", 5,
                "min", 1,
                "max", 10,
                "required", true
            ));

            // Add type-specific configurations
            switch (tenantType.toUpperCase()) {
                case "BANK":
                    baseSchema.put("transaction_limit", Map.of(
                        "type", "number",
                        "default", 10000,
                        "min", 1000,
                        "required", true
                    ));
                    baseSchema.put("enable_fraud_detection", Map.of(
                        "type", "boolean",
                        "default", true,
                        "required", false
                    ));
                    break;
                    
                case "POLICE":
                    baseSchema.put("case_prefix", Map.of(
                        "type", "string",
                        "default", "CASE-",
                        "required", true
                    ));
                    baseSchema.put("enable_biometrics", Map.of(
                        "type", "boolean",
                        "default", false,
                        "required", false
                    ));
                    break;
                    
                case "COURT":
                    baseSchema.put("case_number_format", Map.of(
                        "type", "string",
                        "default", "YYYY-####",
                        "required", true
                    ));
                    baseSchema.put("enable_public_records", Map.of(
                        "type", "boolean",
                        "default", true,
                        "required", false
                    ));
                    break;
                    
                default:
                    throw new RuntimeException("Unsupported tenant type: " + tenantType);
            }
            
            return baseSchema;
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to get tenant configuration schema: " + e.getMessage());
        }
    }

    /**
     * Get tenant statistics
     */
    public Map<String, Object> getTenantStatistics(Long tenantId) {
        try {
            Optional<Tenant> tenantOpt = tenantRepository.findById(tenantId);
            if (!tenantOpt.isPresent()) {
                throw new RuntimeException("Tenant not found");
            }
            
            // Get user count for tenant
            long userCount = tenantRepository.countUsersByTenant(tenantId);
            long activeUserCount = tenantRepository.countActiveUsersByTenant(tenantId);
            
            // Get storage usage
            long storageUsed = tenantRepository.getStorageUsageByTenant(tenantId);
            
            // Get recent activity
            long recentLogins = tenantRepository.countRecentLoginsByTenant(tenantId, 30);
            
            Map<String, Object> stats = new HashMap<>();
            stats.put("userCount", userCount);
            stats.put("activeUserCount", activeUserCount);
            stats.put("storageUsed", storageUsed);
            stats.put("recentLogins", recentLogins);
            stats.put("lastUpdated", LocalDateTime.now());
            
            return stats;
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to get tenant statistics: " + e.getMessage());
        }
    }

    /**
     * Validate tenant resource limits
     */
    public boolean validateResourceLimits(Long tenantId, String resourceType, long requestedAmount) {
        try {
            Optional<Tenant> tenantOpt = tenantRepository.findById(tenantId);
            if (!tenantOpt.isPresent()) {
                return false;
            }
            
            Tenant tenant = tenantOpt.get();
            
            switch (resourceType.toLowerCase()) {
                case "users":
                    long currentUsers = tenantRepository.countUsersByTenant(tenantId);
                    return (currentUsers + requestedAmount) <= tenant.getMaxUsers();
                    
                case "storage":
                    long currentStorage = tenantRepository.getStorageUsageByTenant(tenantId);
                    return (currentStorage + requestedAmount) <= tenant.getMaxStorage();
                    
                default:
                    return true;
            }
            
        } catch (Exception e) {
            return false;
        }
    }

    // Private helper methods
    
    private void validateTenantCreationRequest(CreateTenantRequest request) {
        if (request.getTenantCode() == null || request.getTenantCode().trim().isEmpty()) {
            throw new RuntimeException("Tenant code is required");
        }
        
        if (request.getTenantCode().length() < 2 || request.getTenantCode().length() > 10) {
            throw new RuntimeException("Tenant code must be between 2 and 10 characters");
        }
        
        if (!request.getTenantCode().matches("^[A-Z0-9_]+$")) {
            throw new RuntimeException("Tenant code can only contain uppercase letters, numbers, and underscores");
        }
        
        if (request.getTenantName() == null || request.getTenantName().trim().isEmpty()) {
            throw new RuntimeException("Tenant name is required");
        }
        
        if (request.getTenantType() == null || request.getTenantType().trim().isEmpty()) {
            throw new RuntimeException("Tenant type is required");
        }
        
        if (request.getContactEmail() != null && !isValidEmail(request.getContactEmail())) {
            throw new RuntimeException("Invalid contact email format");
        }
    }
    
    private void createDefaultTenantConfigurations(Tenant tenant) {
        Map<String, String> defaultConfigs = Map.of(
            "timezone", "UTC",
            "date_format", "yyyy-MM-dd",
            "language", "en",
            "max_login_attempts", "5",
            "session_timeout", "30",
            "password_expiry_days", "90",
            "two_factor_required", "false",
            "audit_retention_days", "365"
        );
        
        for (Map.Entry<String, String> entry : defaultConfigs.entrySet()) {
            TenantConfiguration config = new TenantConfiguration();
            config.setTenantId(tenant.getTenantId());
            config.setConfigKey(entry.getKey());
            config.setConfigValue(entry.getValue());
            config.setCreatedAt(LocalDateTime.now());
            config.setUpdatedAt(LocalDateTime.now());
            
            tenantConfigRepository.save(config);
        }
    }
    
    private void updateTenantStatus(Long tenantId, String status) {
        Optional<Tenant> tenantOpt = tenantRepository.findById(tenantId);
        if (!tenantOpt.isPresent()) {
            throw new RuntimeException("Tenant not found");
        }
        
        Tenant tenant = tenantOpt.get();
        tenant.setStatus(status);
        tenant.setUpdatedAt(LocalDateTime.now());
        
        tenantRepository.save(tenant);
        
        auditTenantEvent(tenantId, "TENANT_STATUS_CHANGED", true);
    }
    
    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$");
    }
    
    private void auditTenantEvent(Long tenantId, String event, boolean success) {
        try {
            String message = String.format("Tenant ID: %d, Event: %s, Success: %s, Timestamp: %s", 
                tenantId, event, success, LocalDateTime.now());
            System.out.println("TENANT_AUDIT: " + message);
            
            // TODO: Integrate with audit logging system
        } catch (Exception e) {
            System.err.println("Failed to log tenant audit event: " + e.getMessage());
        }
    }
    
    private TenantDto convertToDto(Tenant tenant) {
        TenantDto dto = new TenantDto();
        dto.setTenantId(tenant.getTenantId());
        dto.setTenantCode(tenant.getTenantCode());
        dto.setTenantName(tenant.getTenantName());
        dto.setTenantType(tenant.getTenantType());
        dto.setDescription(tenant.getDescription());
        dto.setContactEmail(tenant.getContactEmail());
        dto.setContactPhone(tenant.getContactPhone());
        dto.setAddress(tenant.getAddress());
        dto.setStatus(tenant.getStatus());
        dto.setMaxUsers(tenant.getMaxUsers());
        dto.setStorageLimit(tenant.getMaxStorage());
        dto.setSubscriptionType(tenant.getSubscriptionType());
        dto.setSubscriptionStartDate(tenant.getSubscriptionStartDate());
        dto.setSubscriptionEndDate(tenant.getSubscriptionEndDate());
        dto.setCreatedAt(tenant.getCreatedAt());
        dto.setUpdatedAt(tenant.getUpdatedAt());
        
        // Get configuration
        dto.setConfiguration(getTenantConfiguration(tenant.getTenantId()));
        
        return dto;
    }
}
