# 👤 Citizen Services - Complete Implementation Guide

## 📋 Service Overview
**URL**: `https://kzmh4iyhuejbjas7db3k.lite.vusercontent.net/`
**Primary Users**: CITIZEN (General Public)
**Core Function**: Address registration and tracking, government service access

## 🎯 Real-World Flow
```
Citizen Download App → Register & KYC → Address Registration 
→ Location Tracking → Government Services → Emergency Reporting 
→ Digital ID Management → Service Application Tracking
```

## 📁 Complete File Structure & Implementation Checklist

### ✅ Root Application
```
citizen-service/
└── src/main/java/com/egov/citizen/
    ├── CitizenApplication.java ✓
```

**CitizenApplication.java** - Public-facing mobile application entry point
```java
// Main Spring Boot application for citizen services
// Enables: JPA, Security, REST, Cache, Mobile optimization
// Configures: GPS tracking, Address registration, Digital ID
```

### ✅ Controllers (Citizen Web Layer)
```
├── controller/
│   ├── AddressRegistrationController.java ✓
│   ├── AddressTrackingController.java ✓
│   ├── CitizenDashboardController.java ✓
│   ├── DocumentManagementController.java ✓
│   ├── VehicleRegistrationController.java ✓
│   ├── LicenseApplicationController.java ✓
│   ├── EmergencyReportController.java ✓
│   ├── DigitalIdController.java ✓
│   └── ServiceTrackingController.java ✓
```

**AddressRegistrationController.java** - Core address registration functionality
```java
// API Endpoints:
// POST /api/citizen/addresses/register - Register new address
// GET /api/citizen/addresses/my-addresses - Get user's addresses
// POST /api/citizen/addresses/submit-location - Submit location data
// GET /api/citizen/addresses/tracking-status/{verificationId} - Get tracking status
// Real-world: Citizen registers home address with GPS coordinates
```

**AddressTrackingController.java** - Address verification tracking
```java
// API Endpoints:
// GET /address-verification/tracking - Get tracking data
// POST /address-verification/track - Track address verification
// POST /api/citizen/addresses/resume-tracking/{verificationId} - Resume tracking
// Real-world: Citizen tracks verification agent visit status
```

**EmergencyReportController.java** - Emergency reporting system
```java
// API Endpoints:
// POST /api/citizen/emergency/report - Report emergency
// GET /api/citizen/emergency/my-reports - Get my emergency reports
// GET /api/citizen/emergency/track/{incidentId} - Track emergency response
// POST /api/citizen/emergency/cancel/{incidentId} - Cancel emergency
// Real-world: Citizen reports accident with GPS auto-capture
```

**DigitalIdController.java** - Digital identification management
```java
// API Endpoints:
// GET /api/citizen/digital-id/my-licenses - Get my digital licenses
// POST /api/citizen/digital-id/apply-license - Apply for license
// GET /api/citizen/digital-id/license/{licenseId}/qr - Get license QR code
// GET /api/citizen/digital-id/applications/status - Check application status
// Real-world: Citizen views driver's license with QR code for police verification
```

### ✅ Services (Citizen Business Logic)
```
├── service/
│   ├── interface/
│   │   ├── AddressRegistrationService.java ✓
│   │   ├── AddressTrackingService.java ✓
│   │   ├── CitizenDashboardService.java ✓
│   │   ├── DocumentManagementService.java ✓
│   │   ├── VehicleRegistrationService.java ✓
│   │   ├── LicenseApplicationService.java ✓
│   │   ├── EmergencyReportService.java ✓
│   │   ├── DigitalIdService.java ✓
│   │   └── ServiceTrackingService.java ✓
│   ├── impl/
│   │   ├── AddressRegistrationServiceImpl.java ✓
│   │   ├── AddressTrackingServiceImpl.java ✓
│   │   ├── CitizenDashboardServiceImpl.java ✓
│   │   ├── DocumentManagementServiceImpl.java ✓
│   │   ├── VehicleRegistrationServiceImpl.java ✓
│   │   ├── LicenseApplicationServiceImpl.java ✓
│   │   ├── EmergencyReportServiceImpl.java ✓
│   │   ├── DigitalIdServiceImpl.java ✓
│   │   └── ServiceTrackingServiceImpl.java ✓
│   └── validation/
│       ├── AddressValidationService.java ✓
│       ├── DocumentValidationService.java ✓
│       └── CitizenValidationService.java ✓
```

**AddressRegistrationService.java** - Core address registration logic
```java
// Business Rules:
// 1. GPS coordinate validation and accuracy check
// 2. Address format standardization
// 3. Duplicate address detection
// 4. Verification workflow initiation
```

**AddressTrackingService.java** - Address verification tracking
```java
// Features:
// 1. Real-time verification status updates
// 2. Agent assignment notifications
// 3. Estimated completion time calculation
// 4. Verification certificate generation
```

**EmergencyReportService.java** - Emergency reporting logic
```java
// Features:
// 1. GPS-based location capture
// 2. Emergency type classification
// 3. Automatic dispatch coordination
// 4. Real-time response tracking
```

### ✅ Repository (Citizen Data Layer)
```
├── repository/
│   ├── CitizenRepository.java ✓
│   ├── AddressRegistrationRepository.java ✓
│   ├── AddressTrackingRepository.java ✓
│   ├── CitizenDocumentRepository.java ✓
│   ├── VehicleRepository.java ✓
│   ├── LicenseApplicationRepository.java ✓
│   ├── EmergencyReportRepository.java ✓
│   ├── DigitalIdRepository.java ✓
│   └── ServiceApplicationRepository.java ✓
```

**AddressRegistrationRepository.java** - Address data operations
```java
// JPA Methods:
// findByUserIdAndStatus()
// findAddressesWithinRadius()
// countPendingVerifications()
// findByGpsCoordinatesNear()
```

**EmergencyReportRepository.java** - Emergency data operations
```java
// JPA Methods:
// findActiveEmergenciesByUser()
// findEmergenciesByLocationAndTime()
// countEmergenciesByType()
// findEmergenciesRequiringUpdate()
```

### ✅ Entities (Citizen Data Models)
```
├── entity/
│   ├── Citizen.java ✓
│   ├── AddressRegistration.java ✓
│   ├── AddressTracking.java ✓
│   ├── CitizenDocument.java ✓
│   ├── Vehicle.java ✓
│   ├── LicenseApplication.java ✓
│   ├── EmergencyReport.java ✓
│   ├── DigitalId.java ✓
│   └── ServiceApplication.java ✓
```

**AddressRegistration.java** - Core address entity
```java
// Fields: addressId, userId, fullAddress, gpsLat, gpsLng, status
// Relations: OneToMany with AddressTracking
// Status: SUBMITTED, UNDER_VERIFICATION, VERIFIED, REJECTED
// Features: GPS coordinates, verification workflow
```

**EmergencyReport.java** - Emergency incident entity
```java
// Fields: reportId, userId, emergencyType, location, status, priority
// Relations: ManyToOne with Citizen
// Types: ACCIDENT, FIRE, MEDICAL, CRIME, OTHER
// Features: GPS auto-capture, response tracking
```

**DigitalId.java** - Digital identification entity
```java
// Fields: digitalIdId, userId, idType, idNumber, qrCode, expiryDate
// Relations: ManyToOne with Citizen
// Types: DRIVERS_LICENSE, NATIONAL_ID, PASSPORT, VOTER_ID
// Features: QR code generation, expiry tracking
```

### ✅ DTOs (Citizen Data Transfer)
```
├── dto/
│   ├── request/
│   │   ├── AddressRegistrationRequest.java ✓
│   │   ├── LocationUpdateRequest.java ✓
│   │   ├── DocumentUploadRequest.java ✓
│   │   ├── VehicleRegistrationRequest.java ✓
│   │   ├── LicenseApplicationRequest.java ✓
│   │   ├── EmergencyReportRequest.java ✓
│   │   ├── DigitalIdRequest.java ✓
│   │   └── ServiceApplicationRequest.java ✓
│   ├── response/
│   │   ├── AddressRegistrationResponse.java ✓
│   │   ├── TrackingStatusResponse.java ✓
│   │   ├── CitizenDashboardResponse.java ✓
│   │   ├── DocumentStatusResponse.java ✓
│   │   ├── VehicleStatusResponse.java ✓
│   │   ├── LicenseStatusResponse.java ✓
│   │   ├── EmergencyStatusResponse.java ✓
│   │   ├── DigitalIdResponse.java ✓
│   │   └── ServiceStatusResponse.java ✓
│   └── mapper/
│       ├── CitizenMapper.java ✓
│       ├── AddressMapper.java ✓
│       ├── DocumentMapper.java ✓
│       └── VehicleMapper.java ✓
```

**AddressRegistrationRequest.java** - Address registration request DTO
```java
// Fields: fullAddress, gpsLatitude, gpsLongitude, addressType
// Validation: GPS coordinate format, address completeness
// Features: Auto-location detection, manual override
```

### ✅ Configuration (Citizen System Config)
```
├── config/
│   ├── CitizenSecurityConfig.java ✓
│   ├── MobileAppConfig.java ✓
│   └── LocationTrackingConfig.java ✓
```

**MobileAppConfig.java** - Mobile application configuration
```java
// Features:
// 1. Mobile-optimized response formatting
// 2. Offline synchronization settings
// 3. Push notification configuration
// 4. Location accuracy settings
```

**LocationTrackingConfig.java** - GPS tracking configuration
```java
// Features:
// 1. GPS accuracy requirements
// 2. Location update intervals
// 3. Geofencing parameters
// 4. Privacy protection settings
```

### ✅ Exception Handling
```
├── exception/
│   ├── CitizenNotFoundException.java ✓
│   ├── AddressRegistrationException.java ✓
│   ├── DocumentUploadException.java ✓
│   ├── LocationTrackingException.java ✓
│   └── CitizenExceptionHandler.java ✓
```

### ✅ Security (Public Access)
```
├── security/
│   ├── CitizenAuthProvider.java ✓
│   ├── MobileTokenProvider.java ✓
│   └── CitizenSecurityUtils.java ✓
```

**CitizenAuthProvider.java** - Citizen authentication
```java
// Authentication Methods:
// 1. Phone number + OTP verification
// 2. Biometric authentication (fingerprint)
// 3. Social login integration
// 4. Guest mode for emergency reporting
```

### ✅ Utilities
```
├── util/
│   ├── LocationTrackingUtil.java ✓
│   ├── AddressValidationUtil.java ✓
│   ├── DocumentProcessingUtil.java ✓
│   └── EmergencyPriorityUtil.java ✓
```

**LocationTrackingUtil.java** - GPS and location utilities
```java
// Utilities:
// 1. GPS coordinate validation
// 2. Address geocoding and reverse geocoding
// 3. Distance calculation between points
// 4. Location accuracy assessment
```

### ✅ Integration (External Services)
```
└── integration/
    ├── LocationServiceClient.java ✓
    ├── GovernmentServiceClient.java ✓
    ├── VerificationServiceClient.java ✓
    └── EmergencyServiceClient.java ✓
```

**LocationServiceClient.java** - External location services
```java
// Integrations:
// 1. Google Maps API for geocoding
// 2. GPS coordinate validation
// 3. Address standardization
// 4. Location-based service discovery
```

## 🔗 Key API Endpoints

### Address Registration (Primary Focus)
- `POST /api/citizen/addresses/register` - Register New Address
- `GET /api/citizen/addresses/my-addresses` - Get User's Addresses
- `POST /api/citizen/addresses/submit-location` - Submit Location Data
- `GET /api/citizen/addresses/tracking-status/{verificationId}` - Get Tracking Status

### Mobile App Features
- `POST /api/citizen/app/onboarding/complete` - Complete Onboarding
- `GET /api/citizen/app/dashboard` - Get Dashboard
- `GET /api/citizen/app/services/nearby` - Get Nearby Services

### Emergency Services
- `POST /api/citizen/emergency/report` - Report Emergency
- `GET /api/citizen/emergency/my-reports` - Get My Emergency Reports
- `GET /api/citizen/emergency/track/{incidentId}` - Track Emergency Response

### Digital ID Management
- `GET /api/citizen/digital-id/my-licenses` - Get My Digital Licenses
- `POST /api/citizen/digital-id/apply-license` - Apply for License
- `GET /api/citizen/digital-id/license/{licenseId}/qr` - Get License QR Code

### Government Services
- `GET /api/government/services` - Get Government Services
- `POST /api/government/services/apply` - Apply for Service
- `GET /api/government/services/track/{applicationId}` - Track Application

## 🌟 Real-World Implementation Scenarios

### Scenario 1: First-Time Address Registration
```java
// 1. New citizen downloads mobile app
// 2. Completes phone verification with OTP
// 3. Uses GPS to capture home location
// 4. Fills in address details manually
// 5. Submits for verification
// 6. Receives tracking number
// 7. Gets notifications about verification progress
```

### Scenario 2: Emergency Reporting
```java
// 1. Citizen witnesses traffic accident
// 2. Opens app emergency section
// 3. Selects "Traffic Accident" type
// 4. GPS auto-captures location
// 5. Adds description and photos
// 6. Submits emergency report
// 7. Receives incident tracking number
// 8. Gets real-time updates on response
```

### Scenario 3: Digital License Management
```java
// 1. Citizen applies for driver's license renewal
// 2. Uploads required documents via app
// 3. Schedules appointment at DMV
// 4. Receives digital license after approval
// 5. QR code available for police verification
// 6. Push notification for expiry reminders
```

### Scenario 4: Government Service Application
```java
// 1. Citizen needs passport renewal
// 2. Views service requirements in app
// 3. Uploads photos and documents
// 4. Pays fees via mobile money
// 5. Tracks application progress
// 6. Receives approval notification
// 7. Downloads digital certificate
```

## 📝 Implementation Order

### Phase 1: Core Citizen Setup
1. ✅ Create CitizenApplication.java
2. ✅ Setup mobile-optimized configuration
3. ✅ Configure GPS and location services
4. ✅ Setup citizen database schema

### Phase 2: Address Registration (Primary Feature)
5. ✅ Create address registration entities
6. ✅ Implement address registration service
7. ✅ Create address tracking system
8. ✅ Test GPS capture and validation

### Phase 3: Citizen Services
9. ✅ Implement dashboard service
10. ✅ Create document management
11. ✅ Implement emergency reporting
12. ✅ Create digital ID management

### Phase 4: Government Integration
13. ✅ Create government service controllers
14. ✅ Implement service application tracking
15. ✅ Setup vehicle registration
16. ✅ Test citizen workflows

### Phase 5: Mobile Optimization
17. ✅ Configure mobile security
18. ✅ Setup push notifications
19. ✅ Optimize for offline usage
20. ✅ End-to-end citizen testing

## 🚀 Firebase Studio Development Steps

1. **Citizen Setup**: Create "citizen-service" project
2. **Mobile Config**: Configure mobile-optimized settings
3. **Address System**: Focus on address registration first
4. **GPS Integration**: Setup location tracking
5. **Testing**: Test address registration flow
6. **Clean Install**: `mvn clean install`

## 💡 Key Features
- **Address Registration**: Primary focus on address verification
- **GPS Tracking**: Real-time location capture and tracking
- **Emergency Reporting**: Quick emergency response system
- **Digital ID**: Secure digital identification management
- **Mobile Optimized**: Designed for smartphone usage
- **Offline Support**: Works with limited connectivity
- **Push Notifications**: Real-time updates and alerts

This microservice provides the foundation for citizen interaction with the government system, with primary emphasis on address registration and tracking functionality.