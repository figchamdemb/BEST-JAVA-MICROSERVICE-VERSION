package com.egov.citizen.exception;

public class EmergencyReportException extends RuntimeException {
    public EmergencyReportException(String message) {
        super(message);
    }

    public EmergencyReportException(String message, Throwable cause) {
        super(message, cause);
    }
}
