package com.egov.citizen.service;

import com.egov.citizen.dto.request.EmergencyReportRequest;
import com.egov.citizen.dto.response.EmergencyStatusResponse;
import java.time.LocalDateTime;
import java.util.List;

public interface EmergencyReportService {
    EmergencyStatusResponse reportEmergency(EmergencyReportRequest request);
    EmergencyStatusResponse getEmergencyStatus(Long reportId);
    boolean cancelEmergency(Long reportId);
    void updateEmergencyStatus(Long reportId, String status);
    List<EmergencyStatusResponse> getCitizenEmergencies(Long citizenId);
    
    void enableGpsTracking(Long reportId);
    void updateWellnessCheck(Long reportId, LocalDateTime checkTime);
}
