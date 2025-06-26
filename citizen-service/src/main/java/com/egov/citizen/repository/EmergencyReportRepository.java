package com.egov.citizen.repository;

import com.egov.citizen.entity.Citizen;
import com.egov.citizen.entity.EmergencyReport;
import com.egov.citizen.entity.UrgencyLevel;
import com.egov.citizen.entity.EmergencyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EmergencyReportRepository extends JpaRepository<EmergencyReport, Long> {
    List<EmergencyReport> findByCitizen(Citizen citizen);
    List<EmergencyReport> findByCitizen_CitizenId(Long citizenId);
    List<EmergencyReport> findByStatus(EmergencyStatus status);
    List<EmergencyReport> findByEmergencyType(UrgencyLevel.EmergencyType type);
    List<EmergencyReport> findByEmergencyTypeAndStatus(
        UrgencyLevel.EmergencyType type, 
        EmergencyStatus status
    );
    List<EmergencyReport> findByReportedAtBetween(
        LocalDateTime start, 
        LocalDateTime end
    );
    List<EmergencyReport> findByAssignedUnit(String unitId);
    
    @Query("SELECT e FROM EmergencyReport e WHERE " +
           "6371 * acos(cos(radians(:latitude)) * cos(radians(e.latitude)) * " +
           "cos(radians(e.longitude) - radians(:longitude)) + " +
           "sin(radians(:latitude)) * sin(radians(e.latitude))) <= :radiusKm")
    List<EmergencyReport> findByLocationWithinRadius(
        @Param("latitude") Double latitude,
        @Param("longitude") Double longitude,
        @Param("radiusKm") Double radiusKm
    );
}
