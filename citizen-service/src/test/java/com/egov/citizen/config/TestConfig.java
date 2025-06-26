package com.egov.citizen.config;

import com.egov.citizen.service.GpsTrackingService;
import com.egov.citizen.service.NotificationService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan(basePackages = "com.egov.citizen")
public class TestConfig {

    @Bean
    public GpsTrackingService gpsTrackingService() {
        return new GpsTrackingService() {
            @Override
            public void startTracking(Long reportId, Double latitude, Double longitude) {}
            
            @Override
            public void updateLocation(Long reportId, Double latitude, Double longitude) {}
            
            @Override
            public void stopTracking(Long reportId) {}
        };
    }

    @Bean 
    public NotificationService notificationService() {
        return new NotificationService() {
            @Override
            public void sendEmergencyAlert(Long reportId) {}
            
            @Override
            public void sendStatusUpdate(Long reportId, String status) {}
            
            @Override
            public void sendSosNotification(Long reportId) {}
        };
    }
}
