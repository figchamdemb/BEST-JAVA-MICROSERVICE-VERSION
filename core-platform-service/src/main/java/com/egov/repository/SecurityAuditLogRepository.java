package com.egov.repository;

import com.egov.entity.SecurityAuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SecurityAuditLogRepository extends JpaRepository<SecurityAuditLog, Long> {

    List<SecurityAuditLog> findByUserIdOrderByTimestampDesc(Long userId, int limit);

    long countByEventTypeAndTenantIdAndTimestampAfter(String eventType, String tenantId, LocalDateTime timestamp);

    long countByUserIdAndEventTypeAndTimestampAfter(Long userId, String eventType, LocalDateTime timestamp);

    List<SecurityAuditLog> findByUserIdAndTimestampAfterOrderByTimestampDesc(Long userId, LocalDateTime timestamp, int limit);
}
