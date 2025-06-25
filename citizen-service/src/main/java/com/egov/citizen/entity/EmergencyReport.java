package com.egov.citizen.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "emergency_reports")
public class EmergencyReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "citizen_id", nullable = false)
    private Citizen citizen;

    @Enumerated(EnumType.STRING)
    private UrgencyLevel.EmergencyType emergencyType;

    @Enumerated(EnumType.STRING)
    private EmergencyStatus status;

    private String description;
    private String location;
    private Double latitude;
    private Double longitude;
    private Integer accuracy; // meters
    private String resolutionDetails;
    private String incidentNumber;
    private String assignedUnit;
    private String address;
    private LocalDateTime estimatedArrival;
    
    @Enumerated(EnumType.STRING)
    private UrgencyLevel urgencyLevel;

    @CreationTimestamp
    private LocalDateTime reportedAt;
    private LocalDateTime updatedAt;
    private LocalDateTime resolvedAt;
    private LocalDateTime dispatchedAt;

    @ElementCollection
    private List<String> mediaUrls; // Photos/videos/audio

    @ElementCollection
    private List<String> languagePreferences; // User's preferred languages

    @Column(name = "gps_tracking_enabled")
    private Boolean gpsTrackingEnabled;

    @Column(name = "last_gps_update")
    private LocalDateTime lastGpsUpdate;

    @Column(name = "wellness_check_time")
    private LocalDateTime wellnessCheckTime;

    @Column(name = "sos_broadcast_sent")
    private Boolean sosBroadcastSent;
}
