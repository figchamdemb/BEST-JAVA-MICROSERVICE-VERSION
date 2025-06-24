package com.egov.repository;

import com.egov.entity.Tenant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {
    Optional<Tenant> findByTenantCode(String tenantCode);
    Optional<Tenant> findByTenantName(String tenantName);
    boolean existsByTenantCode(String tenantCode);
    boolean existsByTenantName(String tenantName);
    
    Page<Tenant> findByStatus(String status, Pageable pageable);
    Page<Tenant> findByTenantType(String tenantType, Pageable pageable);
    Page<Tenant> findByStatusAndTenantType(String status, String tenantType, Pageable pageable);
    List<Tenant> findByStatus(String status);
    List<Tenant> findByTenantType(String tenantType);
    List<Tenant> findByStatusAndTenantType(String status, String tenantType);
    
    @Query("SELECT COUNT(u) FROM User u WHERE u.tenant.tenantId = :tenantId")
    long countUsersByTenant(Long tenantId);
    
    @Query("SELECT COUNT(u) FROM User u WHERE u.tenant.tenantId = :tenantId AND u.status = 'ACTIVE'")
    long countActiveUsersByTenant(Long tenantId);
    
    @Query("SELECT COALESCE(SUM(f.size), 0) FROM FileStorage f WHERE f.tenantId = :tenantId")
    long getStorageUsageByTenant(Long tenantId);
    
    @Query("SELECT COUNT(l) FROM LoginHistory l WHERE l.tenantId = :tenantId AND l.loginTime >= CURRENT_DATE - :days")
    long countRecentLoginsByTenant(Long tenantId, int days);
}
