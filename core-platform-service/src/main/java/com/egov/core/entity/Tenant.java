package com.egov.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Tenant Entity for Multi-Tenant Architecture
 * Represents organizations, departments, and companies in the E-Government system
 */
@Entity
@Table(name = "tenants", 
       uniqueConstraints = {
           @UniqueConstraint(columnNames = "tenant_id"),
           @UniqueConstraint(columnNames = "registration_number"),
           @UniqueConstraint(columnNames = "subdomain")
       },
       indexes = {
           @Index(name = "idx_tenant_id", columnList = "tenant_id"),
           @Index(name = "idx_tenant_type", columnList = "tenant_type"),
           @Index(name = "idx_tenant_status", columnList = "status"),
           @Index(name = "idx_tenant_name", columnList = "name"),
           @Index(name = "idx_tenant_created_at", columnList = "created_at")
       })
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Tenant ID is required")
    @Size(min = 3, max = 50, message = "Tenant ID must be between 3 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = "Tenant ID can only contain letters, numbers, underscores, and hyphens")
    @Column(name = "tenant_id", nullable = false, unique = true, length = 50)
    private String tenantId;

    @NotBlank(message = "Organization name is required")
    @Size(min = 2, max = 200, message = "Organization name must be between 2 and 200 characters")
    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Size(max = 100, message = "Display name must not exceed 100 characters")
    @Column(name = "display_name", length = 100)
    private String displayName;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    @Column(name = "description", length = 500)
    private String description;

    // Organization type and classification
    @Enumerated(EnumType.STRING)
    @Column(name = "tenant_type", nullable = false)
    private TenantType tenantType;

    @Enumerated(EnumType.STRING)
    @Column(name = "organization_category")
    private OrganizationCategory organizationCategory;

    @Enumerated(EnumType.STRING)
    @Column(name = "government_level")
    private GovernmentLevel governmentLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TenantStatus status = TenantStatus.PENDING;

    // Registration and legal information
    @Size(max = 50, message = "Registration number must not exceed 50 characters")
    @Column(name = "registration_number", unique = true, length = 50)
    private String registrationNumber;

    @Size(max = 50, message = "Tax number must not exceed 50 characters")
    @Column(name = "tax_number", length = 50)
    private String taxNumber;

    @Size(max = 50, message = "License number must not exceed 50 characters")
    @Column(name = "license_number", length = 50)
    private String licenseNumber;

    @Column(name = "establishment_date")
    private LocalDateTime establishmentDate;

    // Web and digital presence
    @Pattern(regexp = "^[a-z0-9-]+$", message = "Subdomain can only contain lowercase letters, numbers, and hyphens")
    @Size(max = 50, message = "Subdomain must not exceed 50 characters")
    @Column(name = "subdomain", unique = true, length = 50)
    private String subdomain;

    @Size(max = 200, message = "Website URL must not exceed 200 characters")
    @Column(name = "website_url", length = 200)
    private String websiteUrl;

    @Size(max = 200, message = "Logo URL must not exceed 200 characters")
    @Column(name = "logo_url", length = 200)
    private String logoUrl;

    // Contact information
    @NotBlank(message = "Primary email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Primary email must not exceed 100 characters")
    @Column(name = "primary_email", nullable = false, length = 100)
    private String primaryEmail;

    @Pattern(regexp = "^\\+233[0-9]{9}$", message = "Primary phone must be in format +233XXXXXXXXX")
    @Column(name = "primary_phone", length = 15)
    private String primaryPhone;

    @Size(max = 100, message = "Secondary email must not exceed 100 characters")
    @Email(message = "Invalid secondary email format")
    @Column(name = "secondary_email", length = 100)
    private String secondaryEmail;

    @Pattern(regexp = "^\\+233[0-9]{9}$", message = "Secondary phone must be in format +233XXXXXXXXX")
    @Column(name = "secondary_phone", length = 15)
    private String secondaryPhone;

    // Address information
    @Size(max = 200, message = "Address line 1 must not exceed 200 characters")
    @Column(name = "address_line_1", length = 200)
    private String addressLine1;

    @Size(max = 200, message = "Address line 2 must not exceed 200 characters")
    @Column(name = "address_line_2", length = 200)
    private String addressLine2;

    @Size(max = 50, message = "City must not exceed 50 characters")
    @Column(name = "city", length = 50)
    private String city;

    @Size(max = 50, message = "Region must not exceed 50 characters")
    @Column(name = "region", length = 50)
    private String region;

    @Size(max = 20, message = "Postal code must not exceed 20 characters")
    @Column(name = "postal_code", length = 20)
    private String postalCode;

    @Size(max = 50, message = "Country must not exceed 50 characters")
    @Column(name = "country", length = 50)
    private String country = "Ghana";

    @Size(max = 20, message = "Digital address must not exceed 20 characters")
    @Column(name = "digital_address", length = 20)
    private String digitalAddress;

    // Geographic coordinates
    @DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90")
    @DecimalMax(value = "90.0", message = "Latitude must be between -90 and 90")
    @Column(name = "latitude", precision = 10, scale = 8)
    private BigDecimal latitude;

    @DecimalMin(value = "-180.0", message = "Longitude must be between -180 and 180")
    @DecimalMax(value = "180.0", message = "Longitude must be between -180 and 180")
    @Column(name = "longitude", precision = 11, scale = 8)
    private BigDecimal longitude;

    // Hierarchy and relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_tenant_id")
    private Tenant parentTenant;

    @OneToMany(mappedBy = "parentTenant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Tenant> childTenants = new HashSet<>();

    @Column(name = "tenant_level")
    private Integer tenantLevel = 1;

    @Size(max = 500, message = "Tenant path must not exceed 500 characters")
    @Column(name = "tenant_path", length = 500)
    private String tenantPath; // Hierarchical path like "/government/ministry/department"

    // Operational details
    @Column(name = "max_users")
    private Integer maxUsers = 100;

    @Column(name = "max_storage_gb")
    private Integer maxStorageGb = 10;

    @Column(name = "max_api_calls_per_day")
    private Integer maxApiCallsPerDay = 10000;

    @Column(name = "operating_hours", length = 100)
    private String operatingHours; // JSON object with operating hours

    @Column(name = "time_zone", length = 50)
    private String timeZone = "Africa/Accra";

    @Column(name = "language_code", length = 10)
    private String languageCode = "en";

    @Column(name = "currency_code", length = 3)
    private String currencyCode = "GHS";

    // Features and capabilities
    @Column(name = "features_enabled", length = 1000)
    private String featuresEnabled; // JSON array of enabled features

    @Column(name = "integration_endpoints", length = 1000)
    private String integrationEndpoints; // JSON object with external integration endpoints

    @Column(name = "custom_settings", length = 2000)
    private String customSettings; // JSON object with custom tenant settings

    // Billing and subscription
    @Enumerated(EnumType.STRING)
    @Column(name = "subscription_plan")
    private SubscriptionPlan subscriptionPlan = SubscriptionPlan.BASIC;

    @Column(name = "monthly_fee", precision = 10, scale = 2)
    private BigDecimal monthlyFee = BigDecimal.ZERO;

    @Column(name = "billing_cycle_start")
    private LocalDateTime billingCycleStart;

    @Column(name = "subscription_expires_at")
    private LocalDateTime subscriptionExpiresAt;

    @Column(name = "trial_ends_at")
    private LocalDateTime trialEndsAt;

    @Column(name = "is_trial")
    private Boolean isTrial = false;

    // Security and compliance
    @Enumerated(EnumType.STRING)
    @Column(name = "security_level")
    private SecurityLevel securityLevel = SecurityLevel.STANDARD;

    @Column(name = "requires_2fa")
    private Boolean requires2fa = false;

    @Column(name = "password_policy", length = 500)
    private String passwordPolicy; // JSON object with password requirements

    @Column(name = "session_timeout_minutes")
    private Integer sessionTimeoutMinutes = 30;

    @Column(name = "ip_whitelist", length = 500)
    private String ipWhitelist; // JSON array of allowed IP addresses

    @Column(name = "compliance_requirements", length = 500)
    private String complianceRequirements; // JSON array of compliance standards

    // Notifications and alerts
    @Column(name = "notification_settings", length = 1000)
    private String notificationSettings; // JSON object with notification preferences

    @Column(name = "alert_email", length = 100)
    @Email(message = "Invalid alert email format")
    private String alertEmail;

    @Column(name = "alert_phone", length = 15)
    @Pattern(regexp = "^\\+233[0-9]{9}$", message = "Alert phone must be in format +233XXXXXXXXX")
    private String alertPhone;

    // Primary contact person
    @Size(max = 100, message = "Contact person name must not exceed 100 characters")
    @Column(name = "contact_person_name", length = 100)
    private String contactPersonName;

    @Size(max = 100, message = "Contact person title must not exceed 100 characters")
    @Column(name = "contact_person_title", length = 100)
    private String contactPersonTitle;

    @Email(message = "Invalid contact person email format")
    @Size(max = 100, message = "Contact person email must not exceed 100 characters")
    @Column(name = "contact_person_email", length = 100)
    private String contactPersonEmail;

    @Pattern(regexp = "^\\+233[0-9]{9}$", message = "Contact person phone must be in format +233XXXXXXXXX")
    @Column(name = "contact_person_phone", length = 15)
    private String contactPersonPhone;

    // API and integration keys
    @Size(max = 64, message = "API key must not exceed 64 characters")
    @Column(name = "api_key", length = 64)
    private String apiKey;

    @Size(max = 128, message = "API secret must not exceed 128 characters")
    @Column(name = "api_secret", length = 128)
    private String apiSecret;

    @Size(max = 200, message = "Webhook URL must not exceed 200 characters")
    @Column(name = "webhook_url", length = 200)
    private String webhookUrl;

    // Audit fields
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "updated_by", length = 50)
    private String updatedBy;

    @Column(name = "activated_at")
    private LocalDateTime activatedAt;

    @Column(name = "deactivated_at")
    private LocalDateTime deactivatedAt;

    // Relationships
    @OneToMany(mappedBy = "tenantId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "tenantId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "tenantId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Permission> permissions = new ArrayList<>();

    // JPA lifecycle methods
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) {
            status = TenantStatus.PENDING;
        }
        if (subscriptionPlan == null) {
            subscriptionPlan = SubscriptionPlan.BASIC;
        }
        if (securityLevel == null) {
            securityLevel = SecurityLevel.STANDARD;
        }
        if (tenantLevel == null) {
            tenantLevel = 1;
        }
        // Generate tenant path based on hierarchy
        updateTenantPath();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
        updateTenantPath();
    }

    // Business methods
    private void updateTenantPath() {
        if (parentTenant != null) {
            tenantPath = parentTenant.getTenantPath() + "/" + tenantId;
            tenantLevel = parentTenant.getTenantLevel() + 1;
        } else {
            tenantPath = "/" + tenantId;
            tenantLevel = 1;
        }
    }

    public boolean isActive() {
        return status == TenantStatus.ACTIVE;
    }

    public boolean isTrialActive() {
        return Boolean.TRUE.equals(isTrial) && 
               trialEndsAt != null && 
               trialEndsAt.isAfter(LocalDateTime.now());
    }

    public boolean isSubscriptionActive() {
        return subscriptionExpiresAt == null || 
               subscriptionExpiresAt.isAfter(LocalDateTime.now());
    }

    public boolean hasFeature(String featureName) {
        // Implementation to check if a feature is enabled
        // This would parse the featuresEnabled JSON array
        return true; // Simplified for now
    }

    public void activate() {
        this.status = TenantStatus.ACTIVE;
        this.activatedAt = LocalDateTime.now();
    }

    public void deactivate() {
        this.status = TenantStatus.INACTIVE;
        this.deactivatedAt = LocalDateTime.now();
    }

    public void suspend() {
        this.status = TenantStatus.SUSPENDED;
    }

    public int getCurrentUserCount() {
        return users != null ? users.size() : 0;
    }

    public boolean hasReachedUserLimit() {
        return maxUsers != null && getCurrentUserCount() >= maxUsers;
    }

    public String getFullAddress() {
        StringBuilder address = new StringBuilder();
        if (addressLine1 != null) address.append(addressLine1);
        if (addressLine2 != null) address.append(", ").append(addressLine2);
        if (city != null) address.append(", ").append(city);
        if (region != null) address.append(", ").append(region);
        if (country != null) address.append(", ").append(country);
        return address.toString();
    }

    public void addChildTenant(Tenant childTenant) {
        childTenants.add(childTenant);
        childTenant.setParentTenant(this);
    }

    public void removeChildTenant(Tenant childTenant) {
        childTenants.remove(childTenant);
        childTenant.setParentTenant(null);
    }

    // Enums
    public enum TenantType {
        GOVERNMENT_MINISTRY,
        GOVERNMENT_DEPARTMENT,
        GOVERNMENT_AGENCY,
        GOVERNMENT_COMMISSION,
        LOCAL_GOVERNMENT,
        BANK,
        MICROFINANCE,
        INSURANCE_COMPANY,
        TELECOMMUNICATIONS,
        HOSPITAL,
        CLINIC,
        UNIVERSITY,
        SCHOOL,
        COURT,
        POLICE_STATION,
        FIRE_STATION,
        IMMIGRATION_OFFICE,
        PRIVATE_COMPANY,
        NGO,
        INTERNATIONAL_ORGANIZATION
    }

    public enum OrganizationCategory {
        PUBLIC_SECTOR,
        PRIVATE_SECTOR,
        NON_PROFIT,
        INTERNATIONAL,
        HYBRID
    }

    public enum GovernmentLevel {
        FEDERAL,
        REGIONAL,
        DISTRICT,
        MUNICIPAL,
        LOCAL
    }

    public enum TenantStatus {
        PENDING,       // Tenant application is pending approval
        ACTIVE,        // Tenant is active and operational
        INACTIVE,      // Tenant is inactive but can be reactivated
        SUSPENDED,     // Tenant is temporarily suspended
        DEACTIVATED,   // Tenant has been deactivated
        ARCHIVED,      // Tenant is archived for historical purposes
        TRIAL          // Tenant is in trial period
    }

    public enum SubscriptionPlan {
        BASIC,
        STANDARD,
        PREMIUM,
        ENTERPRISE,
        GOVERNMENT,
        CUSTOM
    }

    public enum SecurityLevel {
        BASIC,
        STANDARD,
        HIGH,
        CRITICAL
    }

    // Constructors
    public Tenant() {}

    public Tenant(String tenantId, String name, TenantType tenantType, String primaryEmail) {
        this.tenantId = tenantId;
        this.name = name;
        this.tenantType = tenantType;
        this.primaryEmail = primaryEmail;
        this.status = TenantStatus.PENDING;
        this.subscriptionPlan = SubscriptionPlan.BASIC;
        this.securityLevel = SecurityLevel.STANDARD;
    }

    // Getters and Setters (truncated for brevity - include all fields)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public TenantType getTenantType() { return tenantType; }
    public void setTenantType(TenantType tenantType) { this.tenantType = tenantType; }

    public OrganizationCategory getOrganizationCategory() { return organizationCategory; }
    public void setOrganizationCategory(OrganizationCategory organizationCategory) { this.organizationCategory = organizationCategory; }

    public GovernmentLevel getGovernmentLevel() { return governmentLevel; }
    public void setGovernmentLevel(GovernmentLevel governmentLevel) { this.governmentLevel = governmentLevel; }

    public TenantStatus getStatus() { return status; }
    public void setStatus(TenantStatus status) { this.status = status; }

    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

    public String getPrimaryEmail() { return primaryEmail; }
    public void setPrimaryEmail(String primaryEmail) { this.primaryEmail = primaryEmail; }

    public String getPrimaryPhone() { return primaryPhone; }
    public void setPrimaryPhone(String primaryPhone) { this.primaryPhone = primaryPhone; }

    public Tenant getParentTenant() { return parentTenant; }
    public void setParentTenant(Tenant parentTenant) { 
        this.parentTenant = parentTenant;
        updateTenantPath();
    }

    public Set<Tenant> getChildTenants() { return childTenants; }
    public void setChildTenants(Set<Tenant> childTenants) { this.childTenants = childTenants; }

    public String getTenantPath() { return tenantPath; }
    public void setTenantPath(String tenantPath) { this.tenantPath = tenantPath; }

    public Integer getTenantLevel() { return tenantLevel; }
    public void setTenantLevel(Integer tenantLevel) { this.tenantLevel = tenantLevel; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public List<User> getUsers() { return users; }
    public void setUsers(List<User> users) { this.users = users; }

    // Additional getters and setters for all other fields...
    // (Include all remaining getter/setter methods for completeness)

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tenant tenant = (Tenant) o;
        return Objects.equals(id, tenant.id) &&
               Objects.equals(tenantId, tenant.tenantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tenantId);
    }

    @Override
    public String toString() {
        return "Tenant{" +
                "id=" + id +
                ", tenantId='" + tenantId + '\'' +
                ", name='" + name + '\'' +
                ", tenantType=" + tenantType +
                ", status=" + status +
                ", primaryEmail='" + primaryEmail + '\'' +
                '}';
    }
}