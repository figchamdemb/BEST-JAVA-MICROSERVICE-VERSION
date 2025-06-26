package com.egov.citizen.model;

import java.util.Date;

public class GpsData {
    private final Double latitude;
    private final Double longitude; 
    private final Date timestamp;

    public GpsData(Double latitude, Double longitude, Date timestamp) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
