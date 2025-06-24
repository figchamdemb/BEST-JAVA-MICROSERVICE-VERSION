package com.egov.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Map;

public class CreateTenantRequest {
    public static Builder builder() {
        return new Builder();
    }

    @NotBlank(message = "Tenant name is required")
    @Size(min = 2, max = 100, message = "Tenant name must be between 2 and 100 characters")
    private String tenantName;

    @NotBlank(message = "Tenant code is required")
    @Size(min = 2, max = 20, message = "Tenant code must be between 2 and 20 characters")
    private String tenantCode;

    private String tenantType;
    private String description;
    private String domain;
    private String contactEmail;
    private String contactPhone;
    private String address;
    private String city;
    private String region;
    private String country;
    private String postalCode;
    private Integer maxUsers;
    private Long storageLimit;
    private String subscriptionType;
    private LocalDateTime subscriptionStartDate;
    private LocalDateTime subscriptionEndDate;
    private Map<String, Object> configuration;

    // Getters and Setters
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

    public Integer getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(Integer maxUsers) {
        this.maxUsers = maxUsers;
    }

    public Long getStorageLimit() {
        return storageLimit;
    }

    public Long getMaxStorage() {
        return getStorageLimit();
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

    public Map<String, Object> getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Map<String, Object> configuration) {
        this.configuration = configuration;
    }

    public static class Builder {
        private CreateTenantRequest request = new CreateTenantRequest();

        public Builder tenantName(String tenantName) {
            request.setTenantName(tenantName);
            return this;
        }

        public Builder tenantCode(String tenantCode) {
            request.setTenantCode(tenantCode);
            return this;
        }

        public Builder tenantType(String tenantType) {
            request.setTenantType(tenantType);
            return this;
        }

        public Builder description(String description) {
            request.setDescription(description);
            return this;
        }

        public Builder domain(String domain) {
            request.setDomain(domain);
            return this;
        }

        public Builder contactEmail(String contactEmail) {
            request.setContactEmail(contactEmail);
            return this;
        }

        public Builder contactPhone(String contactPhone) {
            request.setContactPhone(contactPhone);
            return this;
        }

        public Builder address(String address) {
            request.setAddress(address);
            return this;
        }

        public Builder city(String city) {
            request.setCity(city);
            return this;
        }

        public Builder region(String region) {
            request.setRegion(region);
            return this;
        }

        public Builder country(String country) {
            request.setCountry(country);
            return this;
        }

        public Builder postalCode(String postalCode) {
            request.setPostalCode(postalCode);
            return this;
        }

        public Builder maxUsers(Integer maxUsers) {
            request.setMaxUsers(maxUsers);
            return this;
        }

        public Builder storageLimit(Long storageLimit) {
            request.setStorageLimit(storageLimit);
            return this;
        }

        public Builder subscriptionType(String subscriptionType) {
            request.setSubscriptionType(subscriptionType);
            return this;
        }

        public Builder subscriptionStartDate(LocalDateTime subscriptionStartDate) {
            request.setSubscriptionStartDate(subscriptionStartDate);
            return this;
        }

        public Builder subscriptionEndDate(LocalDateTime subscriptionEndDate) {
            request.setSubscriptionEndDate(subscriptionEndDate);
            return this;
        }

        public Builder configuration(Map<String, Object> configuration) {
            request.setConfiguration(configuration);
            return this;
        }

        public CreateTenantRequest build() {
            return request;
        }
    }
}
