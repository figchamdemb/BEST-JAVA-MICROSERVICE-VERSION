package com.egov.citizen.service.interfaces;

import com.egov.citizen.entity.UrgencyLevel;
import java.util.List;

public interface EmergencyReportService {
    UrgencyLevel createEmergencyReport(UrgencyLevel report);
    List<UrgencyLevel> getEmergencyReportsByCitizen(Long citizenId);
    List<UrgencyLevel> getPendingEmergencyReports();
    UrgencyLevel updateEmergencyReportStatus(Long reportId, String status);
    UrgencyLevel assignOfficer(Long reportId, Long officerId);
    List<UrgencyLevel> getEmergencyReportsByOfficer(Long officerId);
    UrgencyLevel getEmergencyReportById(Long reportId);
    List<UrgencyLevel> getEmergencyReportsByType(String emergencyType);
    List<UrgencyLevel> getEmergencyReportsByPriority(String priority);
    UrgencyLevel addEmergencyResponse(Long reportId, String responseDetails);
    List<UrgencyLevel> getEmergencyReportsByLocation(Double latitude, Double longitude, Double radius);
    UrgencyLevel updateEmergencyReportLocation(Long reportId, Double latitude, Double longitude);
}
