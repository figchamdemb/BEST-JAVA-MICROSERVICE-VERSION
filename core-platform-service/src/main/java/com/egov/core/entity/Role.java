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
 * Role Entity for Role-Based Access Control (RBAC)
 * Supports hierarchical roles and multi-tenant architecture
 */
@Entity
@Table(name = "roles", 
       uniqueConstraints = {
           @UniqueConstraint(columnNames = {"name", "tenant_id"})
       },
       indexes = {
           @Index(name = "idx_role_name_tenant", columnList = "name, tenant_id"),
           @Index(name = "idx_role_type", columnList = "role_type"),
           @Index(name = "idx_role_status", columnList = "status"),
           @Index(name = "idx_role_created_at", columnList = "created_at")
       })
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @NotBlank(message = "Role name is required")
    @Size(min = 3, max = 50, message = "Role name must be between 3 and 50 characters")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Size(max = 200, message = "Role description must not exceed 200 characters")
    @Column(name = "description", length = 200)
    private String description;

    // Multi-tenant support
    @NotBlank(message = "Tenant ID is required")
    @Column(name = "tenant_id", nullable = false, length = 50)
    private String tenantId;

    // Role classification
    @Enumerated(EnumType.STRING)
    @Column(name = "role_type", nullable = false)
    private RoleType roleType = RoleType.CUSTOM;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_category", nullable = false)
    private RoleCategory roleCategory = RoleCategory.OPERATIONAL;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RoleStatus status = RoleStatus.ACTIVE;

    // Hierarchical role support
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_role_id")
    private Role parentRole;

    @OneToMany(mappedBy = "parentRole", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Role> childRoles = new HashSet<>();

    @Column(name = "role_level")
    private Integer roleLevel = 1;

    // Priority and restrictions
    @Column(name = "priority")
    private Integer priority = 0;

    @Column(name = "max_users")
    private Integer maxUsers;

    @Column(name = "auto_assign")
    private Boolean autoAssign = false;

    @Column(name = "system_role")
    private Boolean systemRole = false;

    @Column(name = "deletable")
    private Boolean deletable = true;

    // Role validity
    @Column(name = "valid_from")
    private LocalDateTime validFrom;

    @Column(name = "valid_until")
    private LocalDateTime validUntil;

    // Department/Organization specific
    @Column(name = "department", length = 100)
    private String department;

    @Column(name = "organization_unit", length = 100)
    private String organizationUnit;

    // Geographical restrictions
    @Column(name = "location_restricted")
    private Boolean locationRestricted = false;

    @Column(name = "allowed_locations", length = 500)
    private String allowedLocations; // JSON array of location codes

    // Time-based restrictions
    @Column(name = "time_restricted")
    private Boolean timeRestricted = false;

    @Column(name = "allowed_hours", length = 200)
    private String allowedHours; // JSON object with day/time restrictions

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
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "role_permissions",
        joinColumns = @JoinColumn(name = "role_id"),
        inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions = new HashSet<>();

    // JPA lifecycle methods
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) {
            status = RoleStatus.ACTIVE;
        }
        if (roleType == null) {
            roleType = RoleType.CUSTOM;
        }
        if (roleCategory == null) {
            roleCategory = RoleCategory.OPERATIONAL;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Business methods
    public boolean hasPermission(String permissionName) {
        return permissions.stream()
            .anyMatch(permission -> permission.getName().equals(permissionName));
    }

    public boolean hasPermission(Permission permission) {
        return permissions.contains(permission);
    }

    public void addPermission(Permission permission) {
        permissions.add(permission);
        permission.getRoles().add(this);
    }

    public void removePermission(Permission permission) {
        permissions.remove(permission);
        permission.getRoles().remove(this);
    }

    public void addUser(User user) {
        users.add(user);
        user.getRoles().add(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.getRoles().remove(this);
    }

    public boolean isActive() {
        LocalDateTime now = LocalDateTime.now();
        
        // Check status
        if (status != RoleStatus.ACTIVE) {
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

    public boolean isSystemRole() {
        return Boolean.TRUE.equals(systemRole);
    }

    public boolean canBeDeleted() {
        return Boolean.TRUE.equals(deletable) && !Boolean.TRUE.equals(systemRole);
    }

    public Set<Permission> getAllPermissions() {
        Set<Permission> allPermissions = new HashSet<>(permissions);
        
        // Include permissions from parent roles (inheritance)
        if (parentRole != null) {
            allPermissions.addAll(parentRole.getAllPermissions());
        }
        
        return allPermissions;
    }

    public int getUserCount() {
        return users != null ? users.size() : 0;
    }

    public boolean hasReachedMaxUsers() {
        return maxUsers != null && getUserCount() >= maxUsers;
    }

    // Enums
    public enum RoleType {
        SYSTEM,     // System-defined roles (cannot be modified)
        CUSTOM,     // Custom roles created by administrators
        INHERITED,  // Roles inherited from parent organizations
        TEMPORARY   // Temporary roles with expiration
    }

    public enum RoleCategory {
        SUPER_ADMIN,      // Super administrator roles
        ADMIN,            // Administrator roles
        OPERATIONAL,      // Day-to-day operational roles
        VIEWER,           // Read-only roles
        GUEST,            // Limited access roles
        SERVICE_SPECIFIC  // Specific service roles
    }

    public enum RoleStatus {
        ACTIVE,    // Role is active and can be assigned
        INACTIVE,  // Role is inactive but not deleted
        SUSPENDED, // Role is temporarily suspended
        ARCHIVED,  // Role is archived (historical purposes)
        PENDING    // Role is pending approval
    }

    // Constructors
    public Role() {}

    public Role(String name, String description, String tenantId) {
        this.name = name;
        this.description = description;
        this.tenantId = tenantId;
        this.status = RoleStatus.ACTIVE;
        this.roleType = RoleType.CUSTOM;
        this.roleCategory = RoleCategory.OPERATIONAL;
    }

    public Role(String name, String description, String tenantId, RoleType roleType, RoleCategory roleCategory) {
        this.name = name;
        this.description = description;
        this.tenantId = tenantId;
        this.roleType = roleType;
        this.roleCategory = roleCategory;
        this.status = RoleStatus.ACTIVE;
    }

    // Getters and Setters
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public RoleCategory getRoleCategory() {
        return roleCategory;
    }

    public void setRoleCategory(RoleCategory roleCategory) {
        this.roleCategory = roleCategory;
    }

    public RoleStatus getStatus() {
        return status;
    }

    public void setStatus(RoleStatus status) {
        this.status = status;
    }

    public Role getParentRole() {
        return parentRole;
    }

    public void setParentRole(Role parentRole) {
        this.parentRole = parentRole;
        if (parentRole != null) {
            this.roleLevel = parentRole.getRoleLevel() + 1;
        }
    }

    public Set<Role> getChildRoles() {
        return childRoles;
    }

    public void setChildRoles(Set<Role> childRoles) {
        this.childRoles = childRoles;
    }

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(Integer maxUsers) {
        this.maxUsers = maxUsers;
    }

    public Boolean getAutoAssign() {
        return autoAssign;
    }

    public void setAutoAssign(Boolean autoAssign) {
        this.autoAssign = autoAssign;
    }

    public Boolean getSystemRole() {
        return systemRole;
    }

    public void setSystemRole(Boolean systemRole) {
        this.systemRole = systemRole;
    }

    public Boolean getDeletable() {
        return deletable;
    }

    public void setDeletable(Boolean deletable) {
        this.deletable = deletable;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOrganizationUnit() {
        return organizationUnit;
    }

    public void setOrganizationUnit(String organizationUnit) {
        this.organizationUnit = organizationUnit;
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

    public Boolean getTimeRestricted() {
        return timeRestricted;
    }

    public void setTimeRestricted(Boolean timeRestricted) {
        this.timeRestricted = timeRestricted;
    }

    public String getAllowedHours() {
        return allowedHours;
    }

    public void setAllowedHours(String allowedHours) {
        this.allowedHours = allowedHours;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(roleId, role.roleId) &&
               Objects.equals(name, role.name) &&
               Objects.equals(tenantId, role.tenantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, name, tenantId);
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", tenantId='" + tenantId + '\'' +
                ", roleType=" + roleType +
                ", roleCategory=" + roleCategory +
                ", status=" + status +
                '}';
    }
}