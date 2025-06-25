package com.egov.citizen.entity;

public enum UrgencyLevel {
    LOW, MEDIUM, HIGH, CRITICAL;

    public enum EmergencyType {
        POLICE, FIRE, AMBULANCE, SOS
    }
}
