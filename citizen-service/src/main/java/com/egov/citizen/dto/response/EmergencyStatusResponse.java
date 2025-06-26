package com.egov.citizen.dto.response;

import com.egov.citizen.entity.UrgencyLevel;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class EmergencyStatusResponse {
    private Long reportId;
    private UrgencyLevel.EmergencyType emergencyType;
    private String status;
    private String assignedUnit;
    private LocalDateTime estimatedArrival;
    private LocalDateTime reportedAt;
    private Double latitude;
    private Double longitude;
    private String address;
    private List<String> mediaUrls;
    private String trackingUrl;
}
