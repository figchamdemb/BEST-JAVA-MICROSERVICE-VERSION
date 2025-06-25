package com.egov.citizen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Citizen Service Application - Public-facing mobile application entry point
 * 
 * This microservice provides:
 * - Emergency reporting (Police, Fire, Ambulance)
 * - Address registration and verification tracking
 * - Digital ID management and QR code generation
 * - Government service applications
 * - Real-time emergency response tracking
 * 
 * Key Features:
 * - Mobile-optimized API responses
 * - GPS location tracking
 * - Real-time WebSocket updates
 * - Offline capability support
 * - Multi-language support
 * 
 * Integration Points:
 * - Police Service: Emergency dispatch and response
 * - Emergency Dispatch: Multi-agency coordination
 * - Government Services: Service applications and tracking
 * - Payment Service: Fee processing
 * - Communication Service: SMS/Push notifications
 */
@SpringBootApplication(scanBasePackages = "com.egov")
@EnableAsync
@EnableScheduling
@EnableTransactionManagement
public class CitizenApplication {

    public static void main(String[] args) {
        SpringApplication.run(CitizenApplication.class, args);
        System.out.println("🏛️ Citizen Service Started Successfully!");
        System.out.println("📱 Mobile App URL: https://kzmpxmtnzru8u4h3du9h.lite.vusercontent.net/");
        System.out.println("🚨 Emergency Services Ready");
        System.out.println("🏠 Address Registration Active");
        System.out.println("🆔 Digital ID Services Online");
    }
}
