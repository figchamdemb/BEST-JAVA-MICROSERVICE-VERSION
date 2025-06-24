package com.egov.core.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Audit Log Entity for Security and Compliance Tracking
 * Records all significant system activities for security monitoring and compliance
 */
@Entity
@Table(name = "audit_logs", 
       indexes = {
           @Index(name = "idx_audit_user_id", columnList = "user_id"),
           @Index(name = "idx_audit_tenant_id", columnList = "tenant_id"),
           @Index(name = "idx_audit_action", columnList = "action"),
           @Index(name = "idx_audit_entity_type", columnList = "entity_type"),
           @Index(name = "idx_audit_entity_id", columnList = "entity_id"),
           @Index(name = "idx_audit_timestamp", columnList = "timestamp"),
           @Index(name = "idx_audit_risk_level", columnList = "risk_level"),
           @Index(name = "idx_audit_ip_address", columnList = "ip_address"),
           @Index(name = "idx_audit_session_id", columnList = "session_id"),
           @Index(name = "idx_audit_source", columnList = "source"),
           @Index(name = "idx_audit_success", columnList = "success")
       })
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "audit_id")
    private Long auditId;

    // User and tenant information
    @Column(name = "user_id")
    private Long userId;

    @Size(max = 100, message = "Username must not exceed 100 characters")
    @Column(name = "username", length = 100)
    private String username;

    @NotBlank(message = "Tenant ID is required")
    @Size(max = 50, message = "Tenant ID must not exceed 50 characters")
    @Column(name = "tenant_id", nullable = false, length = 50)
    private String tenantId;

    @Size(max = 100, message = "User role must not exceed 100 characters")
    @Column(name = "user_role", length = 100)
    private String userRole;

    // Action details
    @NotBlank(message = "Action is required")
    @Size(max = 100, message = "Action must not exceed 100 characters")
    @Column(name = "action", nullable = false, length = 100)
    private String action;

    @Enumerated(EnumType.STRING)
    @Column(name = "action_type", nullable = false)
    private ActionType actionType;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    @Column(name = "description", length = 500)
    private String description;

    // Entity information (what was affected)
    @Size(max = 100, message = "Entity type must not exceed 100 characters")
    @Column(name = "entity_type", length = 100)
    private String entityType;

    @Size(max = 50, message = "Entity ID must not exceed 50 characters")
    @Column(name = "entity_id", length = 50)
    private String entityId;

    @Size(max = 200, message = "Entity name must not exceed 200 characters")
    @Column(name = "entity_name", length = 200)
    private String entityName;

    // Operation result
    @NotNull(message = "Success status is required")
    @Column(name = "success", nullable = false)
    private Boolean success;

    @Size(max = 500, message = "Error message must not exceed 500 characters")
    @Column(name = "error_message", length = 500)
    private String errorMessage;

    @Size(max = 50, message = "Error code must not exceed 50 characters")
    @Column(name = "error_code", length = 50)
    private String errorCode;

    // Risk and security classification
    @Enumerated(EnumType.STRING)
    @Column(name = "risk_level", nullable = false)
    private RiskLevel riskLevel = RiskLevel.LOW;

    @Enumerated(EnumType.STRING)
    @Column(name = "security_level")
    private SecurityLevel securityLevel = SecurityLevel.NORMAL;

    @Column(name = "sensitive_data_accessed")
    private Boolean sensitiveDataAccessed = false;

    @Column(name = "admin_action")
    private Boolean adminAction = false;

    @Column(name = "privileged_operation")
    private Boolean privilegedOperation = false;

    // Session and technical details
    @Size(max = 100, message = "Session ID must not exceed 100 characters")
    @Column(name = "session_id", length = 100)
    private String sessionId;

    @Size(max = 45, message = "IP address must not exceed 45 characters")
    @Column(name = "ip_address", length = 45)
    private String ipAddress;

    @Size(max = 500, message = "User agent must not exceed 500 characters")
    @Column(name = "user_agent", length = 500)
    private String userAgent;

    @Size(max = 200, message = "Request URL must not exceed 200 characters")
    @Column(name = "request_url", length = 200)
    private String requestUrl;

    @Size(max = 10, message = "HTTP method must not exceed 10 characters")
    @Column(name = "http_method", length = 10)
    private String httpMethod;

    @Size(max = 50, message = "Request ID must not exceed 50 characters")
    @Column(name = "request_id", length = 50)
    private String requestId;

    // Source and location
    @Enumerated(EnumType.STRING)
    @Column(name = "source", nullable = false)
    private AuditSource source = AuditSource.WEB_APPLICATION;

    @Size(max = 100, message = "Source application must not exceed 100 characters")
    @Column(name = "source_application", length = 100)
    private String sourceApplication;

    @Size(max = 100, message = "Module must not exceed 100 characters")
    @Column(name = "module", length = 100)
    private String module;

    @Size(max = 100, message = "Function must not exceed 100 characters")
    @Column(name = "function", length = 100)
    private String function;

    // Geographic and device information
    @Size(max = 100, message = "Location must not exceed 100 characters")
    @Column(name = "location", length = 100)
    private String location;

    @Size(max = 100, message = "Device type must not exceed 100 characters")
    @Column(name = "device_type", length = 100)
    private String deviceType;

    @Size(max = 100, message = "Device ID must not exceed 100 characters")
    @Column(name = "device_id", length = 100)
    private String deviceId;

    @Size(max = 50, message = "Operating system must not exceed 50 characters")
    @Column(name = "operating_system", length = 50)
    private String operatingSystem;

    @Size(max = 50, message = "Browser must not exceed 50 characters")
    @Column(name = "browser", length = 50)
    private String browser;

    // Data changes (for update operations)
    @Column(name = "old_values", columnDefinition = "TEXT")
    private String oldValues; // JSON representation of old values

    @Column(name = "new_values", columnDefinition = "TEXT")
    private String newValues; // JSON representation of new values

    @Column(name = "changed_fields", length = 1000)
    private String changedFields; // Comma-separated list of changed fields

    // Additional metadata
    @Column(name = "additional_data", columnDefinition = "TEXT")
    private String additionalData; // JSON object with additional context

    @Column(name = "tags", length = 500)
    private String tags; // Comma-separated tags for categorization

    @Column(name = "correlation_id", length = 100)
    private String correlationId; // For tracking related operations

    @Column(name = "parent_audit_id")
    private Long parentAuditId; // For hierarchical audit trails

    // Performance metrics
    @Column(name = "execution_time_ms")
    private Long executionTimeMs;

    @Column(name = "memory_usage_mb")
    private Double memoryUsageMb;

    @Column(name = "cpu_usage_percent")
    private Double cpuUsagePercent;

    // Compliance and regulatory
    @Column(name = "compliance_flags", length = 200)
    private String complianceFlags; // JSON array of applicable compliance requirements

    @Column(name = "retention_period_days")
    private Integer retentionPeriodDays = 2555; // Default 7 years

    @Column(name = "archived")
    private Boolean archived = false;

    @Column(name = "archived_at")
    private LocalDateTime archivedAt;

    // Notification and alerting
    @Column(name = "alert_triggered")
    private Boolean alertTriggered = false;

    @Column(name = "alert_level")
    @Enumerated(EnumType.STRING)
    private AlertLevel alertLevel;

    @Column(name = "alert_message", length = 500)
    private String alertMessage;

    @Column(name = "notification_sent")
    private Boolean notificationSent = false;

    // Timestamp (immutable)
    @NotNull(message = "Timestamp is required")
    @Column(name = "timestamp", nullable = false, updatable = false)
    private LocalDateTime timestamp;

    @Column(name = "created_by_system")
    private Boolean createdBySystem = true;

    // Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User user;

    // JPA lifecycle methods
    @PrePersist
    protected void onCreate() {
        if (timestamp == null) {
            timestamp = LocalDateTime.now();
        }
        if (success == null) {
            success = true;
        }
        if (riskLevel == null) {
            riskLevel = RiskLevel.LOW;
        }
        if (securityLevel == null) {
            securityLevel = SecurityLevel.NORMAL;
        }
        if (source == null) {
            source = AuditSource.WEB_APPLICATION;
        }
        if (createdBySystem == null) {
            createdBySystem = true;
        }
    }

    // Business methods
    public boolean isHighRisk() {
        return riskLevel == RiskLevel.HIGH || riskLevel == RiskLevel.CRITICAL;
    }

    public boolean isSecurityRelevant() {
        return securityLevel == SecurityLevel.HIGH || 
               securityLevel == SecurityLevel.CRITICAL ||
               Boolean.TRUE.equals(sensitiveDataAccessed) ||
               Boolean.TRUE.equals(privilegedOperation);
    }

    public boolean isSystemAdminAction() {
        return Boolean.TRUE.equals(adminAction) && "SUPER_ADMIN".equals(userRole);
    }

    public boolean requiresImediateAttention() {
        return !Boolean.TRUE.equals(success) && isHighRisk();
    }

    public boolean shouldTriggerAlert() {
        return isHighRisk() || 
               (!Boolean.TRUE.equals(success) && isSecurityRelevant()) ||
               Boolean.TRUE.equals(privilegedOperation);
    }

    public String getActionSummary() {
        return String.format("%s performed %s on %s", 
                            username != null ? username : "System", 
                            action, 
                            entityType != null ? entityType : "Unknown");
    }

    public boolean isWithinRetentionPeriod() {
        if (retentionPeriodDays == null) return true;
        LocalDateTime cutoffDate = LocalDateTime.now().minusDays(retentionPeriodDays);
        return timestamp.isAfter(cutoffDate);
    }

    public void markForArchival() {
        this.archived = true;
        this.archivedAt = LocalDateTime.now();
    }

    // Enums
    public enum ActionType {
        CREATE,
        READ,
        UPDATE,
        DELETE,
        LOGIN,
        LOGOUT,
        ACCESS,
        DOWNLOAD,
        UPLOAD,
        EXPORT,
        IMPORT,
        APPROVE,
        REJECT,
        SUSPEND,
        ACTIVATE,
        DEACTIVATE,
        ASSIGN,
        UNASSIGN,
        GRANT,
        REVOKE,
        SEARCH,
        VIEW,
        PRINT,
        BACKUP,
        RESTORE,
        CONFIGURE,
        EXECUTE,
        ADMIN_ACTION,
        SECURITY_ACTION,
        SYSTEM_ACTION,
        ERROR,
        WARNING,
        ALERT
    }

    public enum RiskLevel {
        LOW,        // Normal operations, low security impact
        MEDIUM,     // Operations that could affect data integrity
        HIGH,       // Operations that could compromise security
        CRITICAL    // Operations that could severely impact system security
    }

    public enum SecurityLevel {
        NORMAL,     // Normal security operations
        ELEVATED,   // Operations requiring elevated permissions
        HIGH,       // High security operations
        CRITICAL    // Critical security operations
    }

    public enum AuditSource {
        WEB_APPLICATION,
        MOBILE_APPLICATION,
        API,
        SYSTEM,
        BACKGROUND_JOB,
        SCHEDULED_TASK,
        WEBHOOK,
        INTEGRATION,
        COMMAND_LINE,
        ADMIN_CONSOLE,
        EXTERNAL_SYSTEM,
        UNKNOWN
    }

    public enum AlertLevel {
        INFO,
        WARNING,
        ERROR,
        CRITICAL
    }

    // Constructors
    public AuditLog() {}

    public AuditLog(String tenantId, String action, ActionType actionType, Boolean success) {
        this.tenantId = tenantId;
        this.action = action;
        this.actionType = actionType;
        this.success = success;
        this.timestamp = LocalDateTime.now();
        this.riskLevel = RiskLevel.LOW;
        this.securityLevel = SecurityLevel.NORMAL;
        this.source = AuditSource.WEB_APPLICATION;
    }

    public AuditLog(Long userId, String username, String tenantId, String action, 
                   ActionType actionType, String entityType, String entityId, Boolean success) {
        this.userId = userId;
        this.username = username;
        this.tenantId = tenantId;
        this.action = action;
        this.actionType = actionType;
        this.entityType = entityType;
        this.entityId = entityId;
        this.success = success;
        this.timestamp = LocalDateTime.now();
        this.riskLevel = RiskLevel.LOW;
        this.securityLevel = SecurityLevel.NORMAL;
        this.source = AuditSource.WEB_APPLICATION;
    }

    // Getters and Setters
    public Long getAuditId() {
        return auditId;
    }

    public void setAuditId(Long auditId) {
        this.auditId = auditId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }

    public SecurityLevel getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(SecurityLevel securityLevel) {
        this.securityLevel = securityLevel;
    }

    public Boolean getSensitiveDataAccessed() {
        return sensitiveDataAccessed;
    }

    public void setSensitiveDataAccessed(Boolean sensitiveDataAccessed) {
        this.sensitiveDataAccessed = sensitiveDataAccessed;
    }

    public Boolean getAdminAction() {
        return adminAction;
    }

    public void setAdminAction(Boolean adminAction) {
        this.adminAction = adminAction;
    }

    public Boolean getPrivilegedOperation() {
        return privilegedOperation;
    }

    public void setPrivilegedOperation(Boolean privilegedOperation) {
        this.privilegedOperation = privilegedOperation;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public AuditSource getSource() {
        return source;
    }

    public void setSource(AuditSource source) {
        this.source = source;
    }

    public String getSourceApplication() {
        return sourceApplication;
    }

    public void setSourceApplication(String sourceApplication) {
        this.sourceApplication = sourceApplication;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getOldValues() {
        return oldValues;
    }

    public void setOldValues(String oldValues) {
        this.oldValues = oldValues;
    }

    public String getNewValues() {
        return newValues;
    }

    public void setNewValues(String newValues) {
        this.newValues = newValues;
    }

    public String getChangedFields() {
        return changedFields;
    }

    public void setChangedFields(String changedFields) {
        this.changedFields = changedFields;
    }

    public String getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getCreatedBySystem() {
        return createdBySystem;
    }

    public void setCreatedBySystem(Boolean createdBySystem) {
        this.createdBySystem = createdBySystem;
    }

    // Additional getters and setters for all remaining fields...

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuditLog auditLog = (AuditLog) o;
        return Objects.equals(auditId, auditLog.auditId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(auditId);
    }

    @Override
    public String toString() {
        return "AuditLog{" +
                "auditId=" + auditId +
                ", username='" + username + '\'' +
                ", action='" + action + '\'' +
                ", entityType='" + entityType + '\'' +
                ", success=" + success +
                ", timestamp=" + timestamp +
                '}';
    }
}