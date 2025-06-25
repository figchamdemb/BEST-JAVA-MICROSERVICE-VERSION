package com.egov.citizen.service;

public interface GpsTrackingService {
    void startTracking(Long reportId, Double latitude, Double longitude);
    void updateLocation(Long reportId, Double latitude, Double longitude);
    void stopTracking(Long reportId);
}
