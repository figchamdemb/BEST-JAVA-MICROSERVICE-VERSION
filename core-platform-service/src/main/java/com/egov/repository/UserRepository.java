package com.egov.repository;

import com.egov.entity.User;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);

    @Query("SELECT u FROM User u WHERE " +
           "(:status IS NULL OR u.status = :status) AND " +
           "(:userType IS NULL OR u.userType = :userType) AND " +
           "(:tenantId IS NULL OR u.tenant.tenantCode = :tenantId)")
    Page<User> findByFilters(
        @Param("status") String status,
        @Param("userType") String userType,
        @Param("tenantId") String tenantId,
        Pageable pageable);

    @Query("SELECT u FROM User u WHERE " +
           "LOWER(u.username) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(u.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(u.lastName) LIKE LOWER(CONCAT('%', :keyword, '%')) AND " +
           "(:tenantId IS NULL OR u.tenant.tenantCode = :tenantId) " +
           "ORDER BY u.createdAt DESC")
    List<User> searchByKeyword(
        @Param("keyword") String keyword,
        @Param("tenantId") String tenantId,
        @Param("limit") int limit);

    @Query("SELECT COUNT(u) FROM User u WHERE " +
           "(:tenantId IS NULL OR u.tenant.tenantCode = :tenantId)")
    long countByTenant(@Param("tenantId") String tenantId);

    @Query("SELECT COUNT(u) FROM User u WHERE " +
           "u.status = :status AND " +
           "(:tenantId IS NULL OR u.tenant.tenantCode = :tenantId)")
    long countByStatusAndTenant(
        @Param("status") String status,
        @Param("tenantId") String tenantId);

    @Query("SELECT u.userType, COUNT(u) FROM User u WHERE " +
           "(:tenantId IS NULL OR u.tenant.tenantCode = :tenantId) " +
           "GROUP BY u.userType")
    Map<String, Long> countByUserTypeAndTenant(@Param("tenantId") String tenantId);

    @Query("SELECT DATE(u.createdAt) as date, COUNT(u) as count FROM User u WHERE " +
           "(:tenantId IS NULL OR u.tenant.tenantCode = :tenantId) AND " +
           "u.createdAt >= CURRENT_DATE - :days " +
           "GROUP BY DATE(u.createdAt) " +
           "ORDER BY date DESC")
    Map<String, Long> countRecentRegistrations(
        @Param("tenantId") String tenantId,
        @Param("days") int days);
}
