package com.egov.repository;

import com.egov.entity.TenantConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenantConfigurationRepository extends JpaRepository<TenantConfiguration, Long> {
    List<TenantConfiguration> findByTenantId(Long tenantId);
    List<TenantConfiguration> findByConfigType(String configType);
    List<TenantConfiguration> findByTenantIdAndConfigType(Long tenantId, String configType);
    List<TenantConfiguration> findByTenantIdAndConfigKey(Long tenantId, String configKey);
}
