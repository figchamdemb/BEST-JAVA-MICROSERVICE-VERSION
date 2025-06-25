package com.egov.citizen.integration;

import com.egov.citizen.model.GpsData;
import java.util.List;

public interface GpsTrackingService {
    List<GpsData> getDailyData(Long addressId);
    void startTracking(Long addressId, Double latitude, Double longitude);
    void stopTracking(Long addressId);
}
