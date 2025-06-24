package com.egov.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Permission Entity for Fine-Grained Access Control
 * Supports hierarchical permissions and resource-level security
 */
@Entity
@Table(name = "permissions", 
       uniqueConstraints = {
           @UniqueConstraint(columnNames = {"name", "resource", "tenant_id"})
       },
       indexes = {
           @Index(name = "idx_permission_name", columnList = "name"),
           @Index(name = "idx_permission_resource", columnList = "resource"),
           @Index(name = "idx_permission_category", columnList = "category"),
           @Index(name = "idx_permission_tenant", columnList = "tenant_id"),
           @Index(name = "idx_permission_status", columnList = "status")
       })
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private Long permissionId;

    @NotBlank(message = "Permission name is required")
    @Size(min = 3, max = 100, message = "Permission name must be between 3 and 100 characters")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Size(max = 200, message = "Permission description must not exceed 200 characters")
    @Column(name = "description", length = 200)
    private String description;

    // Multi-tenant support
    @NotBlank(message = "Tenant ID is required")
    @Column(name = "tenant_id", nullable = false, length = 50)
    private String tenantId;

    // Permission classification
    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private PermissionCategory category = PermissionCategory.FUNCTIONAL;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private PermissionType type = PermissionType.CUSTOM;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PermissionStatus status = PermissionStatus.ACTIVE;

    // Resource and action specification
    @NotBlank(message = "Resource is required")
    @Size(max = 100, message = "Resource name must not exceed 100 characters")
    @Column(name = "resource", nullable = false, length = 100)
    private String resource;

    @NotBlank(message = "Action is required")
    @Size(max = 50, message = "Action must not exceed 50 characters")
    @Column(name = "action", nullable = false, length = 50)
    private String action;

    // Hierarchical permission support
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_permission_id")
    private Permission parentPermission;

    @OneToMany(mappedBy = "parentPermission", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Permission> childPermissions = new HashSet<>();

    @Column(name = "permission_level")
    private Integer permissionLevel = 1;

    // Permission properties
    @Enumerated(EnumType.STRING)
    @Column(name = "scope")
    private PermissionScope scope = PermissionScope.TENANT;

    @Column(name = "system_permission")
    private Boolean systemPermission = false;

    @Column(name = "deletable")
    private Boolean deletable = true;

    @Column(name = "inheritable")
    private Boolean inheritable = true;

    // Resource filters and conditions
    @Column(name = "resource_filter", length = 500)
    private String resourceFilter; // JSON object for complex filtering

    @Column(name = "conditions", length = 500)
    private String conditions; // JSON object for conditional access

    // Department/Module specific
    @Column(name = "module", length = 50)
    private String module;

    @Column(name = "department", length = 100)
    private String department;

    // Risk and priority
    @Enumerated(EnumType.STRING)
    @Column(name = "risk_level")
    private RiskLevel riskLevel = RiskLevel.LOW;

    @Column(name = "priority")
    private Integer priority = 0;

    // Time-based restrictions
    @Column(name = "time_restricted")
    private Boolean timeRestricted = false;

    @Column(name = "allowed_times", length = 200)
    private String allowedTimes; // JSON object with time restrictions

    // Location-based restrictions
    @Column(name = "location_restricted")
    private Boolean locationRestricted = false;

    @Column(name = "allowed_locations", length = 500)
    private String allowedLocations; // JSON array of location codes

    // IP-based restrictions
    @Column(name = "ip_restricted")
    private Boolean ipRestricted = false;

    @Column(name = "allowed_ips", length = 500)
    private String allowedIps; // JSON array of IP addresses/ranges

    // Data access restrictions
    @Column(name = "data_classification", length = 50)
    private String dataClassification; // PUBLIC, INTERNAL, CONFIDENTIAL, RESTRICTED

    @Column(name = "max_records")
    private Integer maxRecords; // Maximum records that can be accessed

    @Column(name = "field_restrictions", length = 500)
    private String fieldRestrictions; // JSON array of restricted fields

    // Audit and compliance
    @Column(name = "requires_audit")
    private Boolean requiresAudit = false;

    @Column(name = "requires_approval")
    private Boolean requiresApproval = false;

    @Column(name = "approval_workflow", length = 100)
    private String approvalWorkflow;

    // Validity period
    @Column(name = "valid_from")
    private LocalDateTime validFrom;

    @Column(name = "valid_until")
    private LocalDateTime validUntil;

    // Audit fields
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "updated_by", length = 50)
    private String updatedBy;

    // Relationships
    @ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();

    // JPA lifecycle methods
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) {
            status = PermissionStatus.ACTIVE;
        }
        if (category == null) {
            category = PermissionCategory.FUNCTIONAL;
        }
        if (type == null) {
            type = PermissionType.CUSTOM;
        }
        if (scope == null) {
            scope = PermissionScope.TENANT;
        }
        if (riskLevel == null) {
            riskLevel = RiskLevel.LOW;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Business methods
    public String getFullPermissionName() {
        return resource + ":" + action;
    }

    public boolean isActive() {
        LocalDateTime now = LocalDateTime.now();
        
        // Check status
        if (status != PermissionStatus.ACTIVE) {
            return false;
        }
        
        // Check validity period
        if (validFrom != null && now.isBefore(validFrom)) {
            return false;
        }
        
        if (validUntil != null && now.isAfter(validUntil)) {
            return false;
        }
        
        return true;
    }

    public boolean isSystemPermission() {
        return Boolean.TRUE.equals(systemPermission);
    }

    public boolean canBeDeleted() {
        return Boolean.TRUE.equals(deletable) && !Boolean.TRUE.equals(systemPermission);
    }

    public boolean isInheritable() {
        return Boolean.TRUE.equals(inheritable);
    }

    public boolean requiresAuditTrail() {
        return Boolean.TRUE.equals(requiresAudit);
    }

    public boolean requiresApprovalWorkflow() {
        return Boolean.TRUE.equals(requiresApproval);
    }

    public Set<Permission> getAllChildPermissions() {
        Set<Permission> allChildren = new HashSet<>(childPermissions);
        
        for (Permission child : childPermissions) {
            allChildren.addAll(child.getAllChildPermissions());
        }
        
        return allChildren;
    }

    public boolean impliesPermission(Permission otherPermission) {
        // Check if this permission implies another permission
        if (this.equals(otherPermission)) {
            return true;
        }
        
        // Check if other permission is a child of this permission
        return getAllChildPermissions().contains(otherPermission);
    }

    public boolean isHighRisk() {
        return riskLevel == RiskLevel.HIGH || riskLevel == RiskLevel.CRITICAL;
    }

    public void addRole(Role role) {
        roles.add(role);
        role.getPermissions().add(this);
    }

    public void removeRole(Role role) {
        roles.remove(role);
        role.getPermissions().remove(this);
    }

    // Enums
    public enum PermissionCategory {
        SYSTEM,          // System-level permissions
        FUNCTIONAL,      // Business function permissions
        DATA,            // Data access permissions
        ADMINISTRATIVE,  // Administrative permissions
        SECURITY,        // Security-related permissions
        AUDIT,           // Audit and compliance permissions
        EMERGENCY        // Emergency access permissions
    }

    public enum PermissionType {
        SYSTEM,    // System-defined permissions
        CUSTOM,    // Custom permissions
        INHERITED, // Inherited from parent
        DERIVED    // Derived from other permissions
    }

    public enum PermissionStatus {
        ACTIVE,     // Permission is active
        INACTIVE,   // Permission is inactive
        SUSPENDED,  // Permission is temporarily suspended
        ARCHIVED,   // Permission is archived
        PENDING     // Permission is pending approval
    }

    public enum PermissionScope {
        GLOBAL,     // Global scope (across all tenants)
        TENANT,     // Tenant scope
        DEPARTMENT, // Department scope
        TEAM,       // Team scope
        PERSONAL    // Personal scope
    }

    public enum RiskLevel {
        LOW,        // Low risk permission
        MEDIUM,     // Medium risk permission
        HIGH,       // High risk permission
        CRITICAL    // Critical permission requiring special handling
    }

    // Constructors
    public Permission() {}

    public Permission(String name, String description, String tenantId, String resource, String action) {
        this.name = name;
        this.description = description;
        this.tenantId = tenantId;
        this.resource = resource;
        this.action = action;
        this.status = PermissionStatus.ACTIVE;
        this.category = PermissionCategory.FUNCTIONAL;
        this.type = PermissionType.CUSTOM;
        this.scope = PermissionScope.TENANT;
    }

    public Permission(String name, String description, String tenantId, String resource, String action, 
                     PermissionCategory category, RiskLevel riskLevel) {
        this.name = name;
        this.description = description;
        this.tenantId = tenantId;
        this.resource = resource;
        this.action = action;
        this.category = category;
        this.riskLevel = riskLevel;
        this.status = PermissionStatus.ACTIVE;
        this.type = PermissionType.CUSTOM;
        this.scope = PermissionScope.TENANT;
    }

    // Getters and Setters
    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public PermissionCategory getCategory() {
        return category;
    }

    public void setCategory(PermissionCategory category) {
        this.category = category;
    }

    public PermissionType getType() {
        return type;
    }

    public void setType(PermissionType type) {
        this.type = type;
    }

    public PermissionStatus getStatus() {
        return status;
    }

    public void setStatus(PermissionStatus status) {
        this.status = status;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Permission getParentPermission() {
        return parentPermission;
    }

    public void setParentPermission(Permission parentPermission) {
        this.parentPermission = parentPermission;
        if (parentPermission != null) {
            this.permissionLevel = parentPermission.getPermissionLevel() + 1;
        }
    }

    public Set<Permission> getChildPermissions() {
        return childPermissions;
    }

    public void setChildPermissions(Set<Permission> childPermissions) {
        this.childPermissions = childPermissions;
    }

    public Integer getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(Integer permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public PermissionScope getScope() {
        return scope;
    }

    public void setScope(PermissionScope scope) {
        this.scope = scope;
    }

    public Boolean getSystemPermission() {
        return systemPermission;
    }

    public void setSystemPermission(Boolean systemPermission) {
        this.systemPermission = systemPermission;
    }

    public Boolean getDeletable() {
        return deletable;
    }

    public void setDeletable(Boolean deletable) {
        this.deletable = deletable;
    }

    public Boolean getInheritable() {
        return inheritable;
    }

    public void setInheritable(Boolean inheritable) {
        this.inheritable = inheritable;
    }

    public String getResourceFilter() {
        return resourceFilter;
    }

    public void setResourceFilter(String resourceFilter) {
        this.resourceFilter = resourceFilter;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Boolean getTimeRestricted() {
        return timeRestricted;
    }

    public void setTimeRestricted(Boolean timeRestricted) {
        this.timeRestricted = timeRestricted;
    }

    public String getAllowedTimes() {
        return allowedTimes;
    }

    public void setAllowedTimes(String allowedTimes) {
        this.allowedTimes = allowedTimes;
    }

    public Boolean getLocationRestricted() {
        return locationRestricted;
    }

    public void setLocationRestricted(Boolean locationRestricted) {
        this.locationRestricted = locationRestricted;
    }

    public String getAllowedLocations() {
        return allowedLocations;
    }

    public void setAllowedLocations(String allowedLocations) {
        this.allowedLocations = allowedLocations;
    }

    public Boolean getIpRestricted() {
        return ipRestricted;
    }

    public void setIpRestricted(Boolean ipRestricted) {
        this.ipRestricted = ipRestricted;
    }

    public String getAllowedIps() {
        return allowedIps;
    }

    public void setAllowedIps(String allowedIps) {
        this.allowedIps = allowedIps;
    }

    public String getDataClassification() {
        return dataClassification;
    }

    public void setDataClassification(String dataClassification) {
        this.dataClassification = dataClassification;
    }

    public Integer getMaxRecords() {
        return maxRecords;
    }

    public void setMaxRecords(Integer maxRecords) {
        this.maxRecords = maxRecords;
    }

    public String getFieldRestrictions() {
        return fieldRestrictions;
    }

    public void setFieldRestrictions(String fieldRestrictions) {
        this.fieldRestrictions = fieldRestrictions;
    }

    public Boolean getRequiresAudit() {
        return requiresAudit;
    }

    public void setRequiresAudit(Boolean requiresAudit) {
        this.requiresAudit = requiresAudit;
    }

    public Boolean getRequiresApproval() {
        return requiresApproval;
    }

    public void setRequiresApproval(Boolean requiresApproval) {
        this.requiresApproval = requiresApproval;
    }

    public String getApprovalWorkflow() {
        return approvalWorkflow;
    }

    public void setApprovalWorkflow(String approvalWorkflow) {
        this.approvalWorkflow = approvalWorkflow;
    }

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return Objects.equals(permissionId, that.permissionId) &&
               Objects.equals(name, that.name) &&
               Objects.equals(resource, that.resource) &&
               Objects.equals(action, that.action) &&
               Objects.equals(tenantId, that.tenantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permissionId, name, resource, action, tenantId);
    }

    @Override
    public String toString() {
        return "Permission{" +
                "permissionId=" + permissionId +
                ", name='" + name + '\'' +
                ", resource='" + resource + '\'' +
                ", action='" + action + '\'' +
                ", tenantId='" + tenantId + '\'' +
                ", category=" + category +
                ", riskLevel=" + riskLevel +
                ", status=" + status +
                '}';
    }
}