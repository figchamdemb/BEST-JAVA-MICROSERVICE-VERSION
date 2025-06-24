/**
 * FILE LOCATION: core-platform-service/src/main/java/com/egov/core/controller/TenantController.java
 * PURPOSE: Multi-tenant organization management controller for tenant creation and configuration
 * MODULE: Core Platform Service (Foundation Service)
 * CREATED BY: Elite Java Developer
 */

package com.egov.controller;

import com.egov.dto.AuthResponse;
import com.egov.dto.CreateTenantRequest;
import com.egov.dto.TenantDto;
import com.egov.service.TenantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Tenant Management Controller - Core Platform Service
 * Handles multi-tenant organization management including creation, configuration, and administration
 */
@RestController
@RequestMapping("/api/tenants")
@CrossOrigin(origins = "*")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    /**
     * Create New Tenant Organization
     * Super Admin endpoint to create new tenant organizations (banks, police, courts, etc.)
     */
    @PostMapping("/create")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<AuthResponse> createTenant(@Valid @RequestBody TenantDto tenantDto) {
        try {
            CreateTenantRequest request = CreateTenantRequest.builder()
                .tenantName(tenantDto.getTenantName())
                .tenantCode(tenantDto.getTenantCode())
                .tenantType(tenantDto.getTenantType())
                .description(tenantDto.getDescription())
                .domain(tenantDto.getDomain())
                .contactEmail(tenantDto.getContactEmail())
                .contactPhone(tenantDto.getContactPhone())
                .address(tenantDto.getAddress())
                .city(tenantDto.getCity())
                .region(tenantDto.getRegion())
                .country(tenantDto.getCountry())
                .postalCode(tenantDto.getPostalCode())
                .maxUsers(tenantDto.getMaxUsers())
                .storageLimit(tenantDto.getStorageLimit())
                .subscriptionType(tenantDto.getSubscriptionType())
                .subscriptionStartDate(tenantDto.getSubscriptionStartDate())
                .subscriptionEndDate(tenantDto.getSubscriptionEndDate())
                .configuration(tenantDto.getConfiguration())
                .build();
            
            TenantDto createdTenant = tenantService.createTenant(request);
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> tenantMap = objectMapper.convertValue(createdTenant, new com.fasterxml.jackson.core.type.TypeReference<Map<String, Object>>() {});
            return ResponseEntity.ok(AuthResponse.builder()
                .success(true)
                .message("Tenant organization created successfully")
                .data(tenantMap)
                .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(AuthResponse.builder()
                    .success(false)
                    .message("Tenant creation failed: " + e.getMessage())
                    .build());
        }
    }

    /**
     * Get Tenant Details
     * Retrieve specific tenant information by tenant ID
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('TENANT_ADMIN')")
    public ResponseEntity<TenantDto> getTenantById(@PathVariable Long id) {
        try {
            TenantDto tenant = tenantService.getTenantById(id);
            return ResponseEntity.ok(tenant);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Update Tenant Configuration
     * Update tenant settings, configurations, and metadata
     */
    @PutMapping("/{id}/config")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('TENANT_ADMIN')")
    public ResponseEntity<AuthResponse> updateTenantConfig(
            @PathVariable Long id,
            @RequestBody Map<String, Object> configuration) {
        try {
            tenantService.updateTenantConfiguration(id, configuration);
            Map<String, Object> tenantMap = new ObjectMapper().convertValue(
                tenantService.getTenantById(id), 
                new com.fasterxml.jackson.core.type.TypeReference<Map<String, Object>>() {}
            );
            return ResponseEntity.ok(AuthResponse.builder()
                .success(true)
                .message("Tenant configuration updated successfully")
                .data(tenantMap)
                .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(AuthResponse.builder()
                    .success(false)
                    .message("Configuration update failed: " + e.getMessage())
                    .build());
        }
    }

    /**
     * Get All Tenants
     * Super Admin endpoint to retrieve all tenant organizations
     */
    @GetMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<Page<TenantDto>> getAllTenants(Pageable pageable) {
        try {
            Page<TenantDto> tenants = tenantService.getAllTenants(pageable, null, null);
            return ResponseEntity.ok(tenants);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Get Tenant Users
     * Get all users belonging to a specific tenant organization
     */
    @GetMapping("/{id}/users")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('TENANT_ADMIN')")
    public ResponseEntity<List<Object>> getTenantUsers(@PathVariable Long id) {
        try {
            @SuppressWarnings("unchecked")
            List<Object> users = (List<Object>) tenantService.getTenantStatistics(id).get("users");
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Get Tenant Statistics
     * Get usage statistics and metrics for a tenant
     */
    @GetMapping("/{id}/statistics")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('TENANT_ADMIN')")
    public ResponseEntity<Map<String, Object>> getTenantStatistics(@PathVariable Long id) {
        try {
            Map<String, Object> statistics = tenantService.getTenantStatistics(id);
            return ResponseEntity.ok(statistics);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Activate Tenant
     * Super Admin endpoint to activate tenant organizations
     */
    @PutMapping("/{id}/activate")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<AuthResponse> activateTenant(@PathVariable Long id) {
        try {
            tenantService.activateTenant(id);
            return ResponseEntity.ok(AuthResponse.builder()
                .success(true)
                .message("Tenant activated successfully")
                .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(AuthResponse.builder()
                    .success(false)
                    .message("Tenant activation failed: " + e.getMessage())
                    .build());
        }
    }

    /**
     * Suspend Tenant
     * Super Admin endpoint to suspend tenant organizations
     */
    @PutMapping("/{id}/suspend")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<AuthResponse> suspendTenant(@PathVariable Long id) {
        try {
            tenantService.suspendTenant(id, "Suspended via API");
            return ResponseEntity.ok(AuthResponse.builder()
                .success(true)
                .message("Tenant suspended successfully")
                .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(AuthResponse.builder()
                    .success(false)
                    .message("Tenant suspension failed: " + e.getMessage())
                    .build());
        }
    }

    /**
     * Get Tenants by Type
     * Get tenants filtered by organization type (BANK, POLICE, COURT, TELCO, etc.)
     */
    @GetMapping("/type/{tenantType}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<List<TenantDto>> getTenantsByType(@PathVariable String tenantType) {
        try {
            List<TenantDto> tenants = tenantService.getAllActiveTenants().stream()
                .filter(t -> t.getTenantType().equals(tenantType))
                .collect(Collectors.toList());
            return ResponseEntity.ok(tenants);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Update Tenant Limits
     * Super Admin endpoint to update resource limits for tenants
     */
    @PutMapping("/{id}/limits")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<AuthResponse> updateTenantLimits(
            @PathVariable Long id,
            @RequestBody Map<String, Integer> limits) {
        try {
            Map<String, Object> configUpdates = new HashMap<>();
            limits.forEach((k,v) -> configUpdates.put("limit." + k, v));
            tenantService.updateTenantConfiguration(id, configUpdates);
            return ResponseEntity.ok(AuthResponse.builder()
                .success(true)
                .message("Tenant limits updated successfully")
                .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(AuthResponse.builder()
                    .success(false)
                    .message("Limits update failed: " + e.getMessage())
                    .build());
        }
    }

    /**
     * Get Tenant Usage
     * Get current resource usage for a tenant
     */
    @GetMapping("/{id}/usage")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('TENANT_ADMIN')")
    public ResponseEntity<Map<String, Object>> getTenantUsage(@PathVariable Long id) {
        try {
            Map<String, Object> usage = tenantService.getTenantStatistics(id);
            return ResponseEntity.ok(usage);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Setup Tenant Database
     * Initialize database schema and data for new tenant
     */
    @PostMapping("/{id}/setup-database")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<AuthResponse> setupTenantDatabase(@PathVariable Long id) {
        try {
            // No direct equivalent - this would need to be implemented
            throw new UnsupportedOperationException("Database setup not implemented");
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(AuthResponse.builder()
                    .success(false)
                    .message("Database setup failed: " + e.getMessage())
                    .build());
        }
    }

    /**
     * Get Current Tenant Context
     * Get the current tenant information based on request context
     */
    @GetMapping("/current")
    public ResponseEntity<TenantDto> getCurrentTenant() {
        try {
            // No direct equivalent - this would need to be implemented
            throw new UnsupportedOperationException("Current tenant retrieval not implemented");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Validate Tenant Domain
     * Check if a tenant domain/subdomain is available
     */
    @GetMapping("/validate-domain")
    public ResponseEntity<AuthResponse> validateTenantDomain(@RequestParam String domain) {
        try {
            // No direct equivalent - this would need to be implemented
            throw new UnsupportedOperationException("Domain validation not implemented");
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(AuthResponse.builder()
                    .success(false)
                    .message("Domain validation failed: " + e.getMessage())
                    .build());
        }
    }

    /**
     * Get Tenant Configuration Schema
     * Get the configuration schema/template for tenant setup
     */
    @GetMapping("/config-schema/{tenantType}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<Map<String, Object>> getTenantConfigSchema(@PathVariable String tenantType) {
        try {
            Map<String, Object> schema = tenantService.getTenantConfigSchema(tenantType);
            return ResponseEntity.ok(schema);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
