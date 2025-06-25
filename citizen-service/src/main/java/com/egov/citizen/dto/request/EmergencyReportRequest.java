package com.egov.citizen.dto.request;

import com.egov.citizen.entity.UrgencyLevel;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class EmergencyReportRequest {
    @NotNull
    private UrgencyLevel.EmergencyType emergencyType;
    
    @NotBlank
    private String description;
    
    @NotNull
    private UrgencyLevel urgencyLevel;
    
    private String location;
    private Double latitude;
    private Double longitude;
    private Integer accuracy;
    private String address;
    
    private Boolean notifyEmergencyContacts;
    private Boolean broadcastToNearby;
    private Boolean gpsTrackingEnabled;
    
    private List<MultipartFile> mediaAttachments;
    private List<String> languagePreferences;
    
    // For medical emergencies
    private String medicalCondition;
    private Integer patientAge;
    private String patientGender;
    
    // For police emergencies
    private String incidentLocationType;
    private Boolean hasWitnesses;
    private String witnessContact;
    
    // Wellness check
    private LocalDateTime wellnessCheckTime;
}
