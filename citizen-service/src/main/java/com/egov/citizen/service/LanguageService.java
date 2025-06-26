package com.egov.citizen.service;

public interface LanguageService {
    String translateEmergencyType(String emergencyType, String targetLanguage);
    String getLocalizedMessage(String messageKey, String language);
    String detectLanguage(String text);
}
