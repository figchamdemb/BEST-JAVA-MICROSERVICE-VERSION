package com.egov.citizen.integration;

import com.egov.citizen.model.GpsData;
import com.egov.citizen.entity.EmergencyReport;
import com.egov.citizen.repository.EmergencyReportRepository;
import com.egov.citizen.entity.EmergencyStatus;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GpsTrackingServiceImpl implements GpsTrackingService {
    
    private final Map<Long, List<GpsData>> trackingData = new ConcurrentHashMap<>();
    private final WebClient webClient;
    private final EmergencyReportRepository emergencyReportRepository;

    @Autowired
    public GpsTrackingServiceImpl(WebClient.Builder webClientBuilder,
                                 EmergencyReportRepository emergencyReportRepository) {
        this.webClient = webClientBuilder.baseUrl("https://gps-tracking-service.example.com").build();
        this.emergencyReportRepository = emergencyReportRepository;
    }

    @Override
    public List<GpsData> getDailyData(Long addressId) {
        return trackingData.getOrDefault(addressId, Collections.emptyList());
    }

    @Override
    public void startTracking(Long emergencyId, Double latitude, Double longitude) {
        EmergencyReport emergency = emergencyReportRepository.findById(emergencyId)
            .orElseThrow(() -> new IllegalArgumentException("Emergency not found"));
        
        GpsData initialData = new GpsData(latitude, longitude, new Date());
        trackingData.put(emergencyId, new ArrayList<>(List.of(initialData)));
        
        // Send initial location to emergency dispatch
        sendLocationUpdate(emergencyId, latitude, longitude);
    }

    @Override
    public void stopTracking(Long emergencyId) {
        trackingData.remove(emergencyId);
    }

    @Scheduled(fixedRate = 30000) // Update every 30 seconds
    public void updateActiveEmergencies() {
        List<EmergencyReport> activeEmergencies = emergencyReportRepository.findByStatus(EmergencyStatus.DISPATCHED);
        
        activeEmergencies.forEach(emergency -> {
            // In a real implementation, this would get actual device location
            GpsData latestData = getSimulatedMovement(emergency.getId());
            trackingData.computeIfAbsent(emergency.getId(), k -> new ArrayList<>())
                       .add(latestData);
            
            sendLocationUpdate(emergency.getId(), latestData.getLatitude(), latestData.getLongitude());
        });
    }

    private void sendLocationUpdate(Long emergencyId, Double latitude, Double longitude) {
        webClient.post()
            .uri("/api/emergency/{id}/location", emergencyId)
            .bodyValue(new GpsData(latitude, longitude, new Date()))
            .retrieve()
            .bodyToMono(Void.class)
            .onErrorResume(e -> {
                System.err.println("Failed to send location update: " + e.getMessage());
                return Mono.empty();
            })
            .subscribe();
    }

    private GpsData getSimulatedMovement(Long emergencyId) {
        // Simulate small movement for demo purposes
        List<GpsData> history = trackingData.getOrDefault(emergencyId, Collections.emptyList());
        if (history.isEmpty()) {
            return new GpsData(0.0, 0.0, new Date());
        }
        
        GpsData last = history.get(history.size() - 1);
        double latChange = (Math.random() - 0.5) * 0.0001;
        double lonChange = (Math.random() - 0.5) * 0.0001;
        
        return new GpsData(
            last.getLatitude() + latChange,
            last.getLongitude() + lonChange,
            new Date()
        );
    }
}
