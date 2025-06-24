/**
 * FILE LOCATION: core-platform-service/src/main/java/com/egov/core/dto/TenantDto.java
 * PURPOSE: Tenant/Organization data transfer object for multi-tenant operations
 * MODULE: Core Platform Service (Foundation Service)
 * CREATED BY: Elite Java Developer
 */

package com.egov.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Tenant DTO - Core Platform Service
 * Data Transfer Object for tenant/organization information
 */
public class TenantDto {
    
    private Long tenantId;
    
    @NotBlank(message = "Tenant name is required")
    @Size(min = 2, max = 100, message = "Tenant name must be between 2 and 100 characters")
    private String tenantName;
    
    @NotBlank(message = "Tenant code is required")
    @Size(min = 2, max = 20, message = "Tenant code must be between 2 and 20 characters")
    private String tenantCode;
    
    private String tenantType; // BANK, POLICE, COURT, TELCO, INSURANCE, HOSPITAL, SCHOOL, etc.
    private String description;
    private String domain; // Subdomain for tenant access
    private String logoUrl;
    private String websiteUrl;
    private String status; // ACTIVE, INACTIVE, SUSPENDED, PENDING
    private String contactEmail;
    private String contactPhone;
    private String licenseNumber;
    private String registrationNumber;
    
    // Resource Limits
    private Integer maxUsers;
    private Integer maxAgents;
    private Integer maxBranches;
    private Integer maxTransactions;
    private Long storageLimit; // in MB
    private String subscriptionType;
    private LocalDateTime subscriptionStartDate;
    private LocalDateTime subscriptionEndDate;
    private LocalDateTime deletedAt;
    
    // Current Usage
    private Integer currentUsers;
    private Integer currentAgents;
    private Integer currentBranches;
    private Integer currentTransactions;
    private Long currentStorage;
    
    // Address Information
    private String address;
    private String city;
    private String region;
    private String country;
    private String postalCode;
    
    // Configuration
    private Map<String, Object> configuration;
    private Map<String, Object> branding;
    private List<String> features;
    private List<String> services;
    
    // Contact Person
    private String contactPersonName;
    private String contactPersonTitle;
    private String contactPersonEmail;
    private String contactPersonPhone;
    
    // Admin User
    private String adminUsername;
    private String adminEmail;
    private String adminPhone;
    private String adminFirstName;
    private String adminLastName;
    
    // Billing Information
    private String billingPlan;
    private Double monthlyFee;
    private String paymentMethod;
    private LocalDateTime nextBillingDate;
    
    // Timestamps
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime activatedAt;
    private LocalDateTime suspendedAt;
    private String createdBy;
    private String updatedBy;
    
    // Default Constructor
    public TenantDto() {}

    // Constructor with basic fields
    public TenantDto(String tenantName, String tenantCode, String tenantType) {
        this.tenantName = tenantName;
        this.tenantCode = tenantCode;
        this.tenantType = tenantType;
    }

    // Getters and Setters
    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public String getTenantType() {
        return tenantType;
    }

    public void setTenantType(String tenantType) {
        this.tenantType = tenantType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Integer getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(Integer maxUsers) {
        this.maxUsers = maxUsers;
    }

    public Integer getMaxAgents() {
        return maxAgents;
    }

    public void setMaxAgents(Integer maxAgents) {
        this.maxAgents = maxAgents;
    }

    public Integer getMaxBranches() {
        return maxBranches;
    }

    public void setMaxBranches(Integer maxBranches) {
        this.maxBranches = maxBranches;
    }

    public Integer getMaxTransactions() {
        return maxTransactions;
    }

    public void setMaxTransactions(Integer maxTransactions) {
        this.maxTransactions = maxTransactions;
    }

    public Long getStorageLimit() {
        return storageLimit;
    }

    public void setStorageLimit(Long storageLimit) {
        this.storageLimit = storageLimit;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public LocalDateTime getSubscriptionStartDate() {
        return subscriptionStartDate;
    }

    public void setSubscriptionStartDate(LocalDateTime subscriptionStartDate) {
        this.subscriptionStartDate = subscriptionStartDate;
    }

    public LocalDateTime getSubscriptionEndDate() {
        return subscriptionEndDate;
    }

    public void setSubscriptionEndDate(LocalDateTime subscriptionEndDate) {
        this.subscriptionEndDate = subscriptionEndDate;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Integer getCurrentUsers() {
        return currentUsers;
    }

    public void setCurrentUsers(Integer currentUsers) {
        this.currentUsers = currentUsers;
    }

    public Integer getCurrentAgents() {
        return currentAgents;
    }

    public void setCurrentAgents(Integer currentAgents) {
        this.currentAgents = currentAgents;
    }

    public Integer getCurrentBranches() {
        return currentBranches;
    }

    public void setCurrentBranches(Integer currentBranches) {
        this.currentBranches = currentBranches;
    }

    public Integer getCurrentTransactions() {
        return currentTransactions;
    }

    public void setCurrentTransactions(Integer currentTransactions) {
        this.currentTransactions = currentTransactions;
    }

    public Long getCurrentStorage() {
        return currentStorage;
    }

    public void setCurrentStorage(Long currentStorage) {
        this.currentStorage = currentStorage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Map<String, Object> getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Map<String, Object> configuration) {
        this.configuration = configuration;
    }

    public Map<String, Object> getBranding() {
        return branding;
    }

    public void setBranding(Map<String, Object> branding) {
        this.branding = branding;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getContactPersonTitle() {
        return contactPersonTitle;
    }

    public void setContactPersonTitle(String contactPersonTitle) {
        this.contactPersonTitle = contactPersonTitle;
    }

    public String getContactPersonEmail() {
        return contactPersonEmail;
    }

    public void setContactPersonEmail(String contactPersonEmail) {
        this.contactPersonEmail = contactPersonEmail;
    }

    public String getContactPersonPhone() {
        return contactPersonPhone;
    }

    public void setContactPersonPhone(String contactPersonPhone) {
        this.contactPersonPhone = contactPersonPhone;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    public String getAdminFirstName() {
        return adminFirstName;
    }

    public void setAdminFirstName(String adminFirstName) {
        this.adminFirstName = adminFirstName;
    }

    public String getAdminLastName() {
        return adminLastName;
    }

    public void setAdminLastName(String adminLastName) {
        this.adminLastName = adminLastName;
    }

    public String getBillingPlan() {
        return billingPlan;
    }

    public void setBillingPlan(String billingPlan) {
        this.billingPlan = billingPlan;
    }

    public Double getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(Double monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getNextBillingDate() {
        return nextBillingDate;
    }

    public void setNextBillingDate(LocalDateTime nextBillingDate) {
        this.nextBillingDate = nextBillingDate;
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

    public LocalDateTime getActivatedAt() {
        return activatedAt;
    }

    public void setActivatedAt(LocalDateTime activatedAt) {
        this.activatedAt = activatedAt;
    }

    public LocalDateTime getSuspendedAt() {
        return suspendedAt;
    }

    public void setSuspendedAt(LocalDateTime suspendedAt) {
        this.suspendedAt = suspendedAt;
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

    // Utility methods
    public boolean isActive() {
        return "ACTIVE".equals(status);
    }

    public boolean hasReachedUserLimit() {
        return maxUsers != null && currentUsers != null && currentUsers >= maxUsers;
    }

    public boolean hasReachedAgentLimit() {
        return maxAgents != null && currentAgents != null && currentAgents >= maxAgents;
    }

    public double getUserUtilization() {
        if (maxUsers == null || maxUsers == 0) return 0.0;
        return currentUsers != null ? (double) currentUsers / maxUsers * 100 : 0.0;
    }

    public double getStorageUtilization() {
        if (storageLimit == null || storageLimit == 0) return 0.0;
        return currentStorage != null ? (double) currentStorage / storageLimit * 100 : 0.0;
    }

    // toString method
    @Override
    public String toString() {
        return "TenantDto{" +
                "tenantId=" + tenantId +
                ", tenantName='" + tenantName + '\'' +
                ", tenantCode='" + tenantCode + '\'' +
                ", tenantType='" + tenantType + '\'' +
                ", domain='" + domain + '\'' +
                ", status='" + status + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", maxUsers=" + maxUsers +
                ", currentUsers=" + currentUsers +
                ", createdAt=" + createdAt +
                '}';
    }
}
