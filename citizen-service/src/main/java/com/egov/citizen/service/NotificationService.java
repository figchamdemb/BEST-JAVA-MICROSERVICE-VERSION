package com.egov.citizen.service;

public interface NotificationService {
    void sendEmergencyAlert(Long reportId);
    void sendStatusUpdate(Long reportId, String status);
    void sendSosNotification(Long reportId);
}
