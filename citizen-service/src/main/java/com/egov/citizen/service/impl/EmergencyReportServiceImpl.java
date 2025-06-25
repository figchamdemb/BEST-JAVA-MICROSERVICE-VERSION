package com.egov.citizen.service.impl;

import com.egov.citizen.dto.request.EmergencyReportRequest;
import com.egov.citizen.dto.response.EmergencyStatusResponse;
import com.egov.citizen.entity.EmergencyReport;
import com.egov.citizen.entity.EmergencyStatus;
import com.egov.citizen.repository.EmergencyReportRepository;
import com.egov.citizen.service.EmergencyReportService;
import com.egov.citizen.service.GpsTrackingService;
import com.egov.citizen.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmergencyReportServiceImpl implements EmergencyReportService {

    private final EmergencyReportRepository emergencyReportRepository;
    private final GpsTrackingService gpsTrackingService;
    private final NotificationService notificationService;

    @Override
    @Transactional
    public void enableGpsTracking(Long reportId) {
        EmergencyReport report = emergencyReportRepository.findById(reportId)
            .orElseThrow(() -> new RuntimeException("Emergency report not found"));
        report.setGpsTrackingEnabled(true);
        emergencyReportRepository.save(report);
        gpsTrackingService.startTracking(reportId, report.getLatitude(), report.getLongitude());
    }

    @Override
    @Transactional
    public void updateWellnessCheck(Long reportId, LocalDateTime checkTime) {
        EmergencyReport report = emergencyReportRepository.findById(reportId)
            .orElseThrow(() -> new RuntimeException("Emergency report not found"));
        report.setWellnessCheckTime(checkTime);
        emergencyReportRepository.save(report);
    }

    @Override
    @Transactional
    public EmergencyStatusResponse reportEmergency(EmergencyReportRequest request) {
        EmergencyReport report = new EmergencyReport();
        report.setEmergencyType(request.getEmergencyType());
        report.setDescription(request.getDescription());
        report.setUrgencyLevel(request.getUrgencyLevel());
        report.setLatitude(request.getLatitude());
        report.setLongitude(request.getLongitude());
        report.setAddress(request.getAddress());
        report.setStatus(EmergencyStatus.PENDING_DISPATCH);
        report.setReportedAt(LocalDateTime.now());

        EmergencyReport savedReport = emergencyReportRepository.save(report);
        notificationService.sendEmergencyAlert(savedReport.getId());
        return mapToResponse(savedReport);
    }

    @Override
    public EmergencyStatusResponse getEmergencyStatus(Long reportId) {
        EmergencyReport report = emergencyReportRepository.findById(reportId)
            .orElseThrow(() -> new RuntimeException("Emergency report not found"));
        return mapToResponse(report);
    }

    @Override
    @Transactional
    public boolean cancelEmergency(Long reportId) {
        EmergencyReport report = emergencyReportRepository.findById(reportId)
            .orElseThrow(() -> new RuntimeException("Emergency report not found"));
        
        if (report.getStatus() == EmergencyStatus.RESOLVED) {
            return false;
        }

        report.setStatus(EmergencyStatus.CANCELLED);
        emergencyReportRepository.save(report);
        notificationService.sendStatusUpdate(reportId, "CANCELLED");
        return true;
    }

    @Override
    @Transactional
    public void updateEmergencyStatus(Long reportId, String status) {
        EmergencyReport report = emergencyReportRepository.findById(reportId)
            .orElseThrow(() -> new RuntimeException("Emergency report not found"));
        
        report.setStatus(EmergencyStatus.valueOf(status));
        emergencyReportRepository.save(report);
        notificationService.sendStatusUpdate(reportId, status);
    }

    @Override
    public List<EmergencyStatusResponse> getCitizenEmergencies(Long citizenId) {
        return emergencyReportRepository.findByCitizen_CitizenId(citizenId).stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    private EmergencyStatusResponse mapToResponse(EmergencyReport report) {
        EmergencyStatusResponse response = new EmergencyStatusResponse();
        response.setReportId(report.getId());
        response.setEmergencyType(report.getEmergencyType());
        response.setStatus(report.getStatus().name());
        response.setAssignedUnit(report.getAssignedUnit());
        response.setEstimatedArrival(report.getEstimatedArrival());
        response.setReportedAt(report.getReportedAt());
        response.setLatitude(report.getLatitude());
        response.setLongitude(report.getLongitude());
        response.setAddress(report.getAddress());
        response.setTrackingUrl("/api/emergency/track/" + report.getId());
        return response;
    }
}
