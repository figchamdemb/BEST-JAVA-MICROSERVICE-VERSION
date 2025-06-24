package com.egov.entity;

import jakarta.persistence.*;
import com.egov.util.JsonConverter;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "tenant_configurations")
public class TenantConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tenant_id", nullable = false)
    private Long tenantId;

    @Column(name = "config_type", nullable = false)
    private String configType;

    @Column(name = "config_data", columnDefinition = "jsonb")
    @Convert(converter = JsonConverter.class)
    private Map<String, Object> configData;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public Map<String, Object> getConfigData() {
        return configData;
    }

    public void setConfigData(Map<String, Object> configData) {
        this.configData = configData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    // Additional methods needed by TenantService
    public void setConfigKey(String configKey) {
        this.configType = configKey;
    }

    public void setConfigValue(String configValue) {
        if (this.configData == null) {
            this.configData = Map.of("value", configValue);
        } else {
            this.configData.put("value", configValue);
        }
    }

    public String getConfigKey() {
        return this.configType;
    }

    public String getConfigValue() {
        return this.configData != null ? (String) this.configData.get("value") : null;
    }

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

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
}
