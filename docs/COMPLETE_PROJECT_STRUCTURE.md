# E-Government Unified Backend System - Complete Project Structure

## Project Overview
This document provides a comprehensive overview of the entire project structure, including all packages, classes, and their import paths.

## Root Structure
\`\`\`
egov-backend/
├── src/main/java/com/egov/
├── src/main/resources/
├── src/test/java/com/egov/
├── target/
├── docs/
├── scripts/
├── docker-compose.yml
├── Dockerfile
├── pom.xml
└── README.md
\`\`\`

## 1. Main Application Entry Point

### Application Main Class
\`\`\`java
// package com.egov;
EgovBackendApplication.java
\`\`\`

## 2. Common Package Structure

### 2.1 Common DTOs
\`\`\`java
// package com.egov.common.dto;
├── AddressDto.java
├── ApiResponse.java
├── IncidentDto.java
├── UserDto.java
└── VehicleDto.java
\`\`\`

### 2.2 Common Entities
\`\`\`java
// package com.egov.common.entity;
├── AuditLog.java
├── BaseEntity.java
└── Tenant.java
\`\`\`

### 2.3 Common Events
\`\`\`java
// package com.egov.common.event;
├── ApplicationEventPublisher.java
├── IncidentEventListener.java
├── NotificationEventListener.java
└── UserEventListener.java
\`\`\`

### 2.4 Common Exceptions
\`\`\`java
// package com.egov.common.exception;
├── BadRequestException.java
├── GlobalExceptionHandler.java
├── OtpServiceException.java
├── PaymentProcessingException.java
└── ResourceNotFoundException.java
\`\`\`

### 2.5 Common Mappers
\`\`\`java
// package com.egov.common.mapper;
├── AddressMapper.java
├── BaseMapper.java
├── IncidentMapper.java
├── UserMapper.java
└── VehicleMapper.java
\`\`\`

### 2.6 Common Repositories
\`\`\`java
// package com.egov.common.repository;
├── AuditLogRepository.java
└── TenantRepository.java
\`\`\`

### 2.7 Common Security
\`\`\`java
// package com.egov.common.security;
├── AuthenticationFailureHandler.java
├── AuthenticationSuccessHandler.java
├── CustomUserDetailsService.java
├── JwtAuthenticationFilter.java
├── JwtTokenProvider.java
├── SecurityAuditor.java
├── SecurityService.java
├── SecurityUtils.java
├── TenantContext.java
├── TenantInterceptor.java
└── UserPrincipal.java
\`\`\`

### 2.8 Common Validation
\`\`\`java
// package com.egov.common.validation;
├── DocumentValidator.java
├── EmailValidator.java
├── LocationValidator.java
├── PhoneNumberValidator.java
└── TenantValidator.java
\`\`\`

## 3. Configuration Package

### 3.1 Configuration Classes
\`\`\`java
// package com.egov.config;
├── ApplicationConfig.java
├── AsyncConfig.java
├── BiometricConfig.java
├── CacheConfig.java
├── DatabaseConfig.java
├── MultiTenantConfig.java
├── RedisConfig.java
├── SecurityConfig.java
├── SwaggerConfig.java
├── ValidationConfig.java
└── WebSocketConfig.java
\`\`\`

## 4. Core DTOs Package

### 4.1 Core DTOs
\`\`\`java
// package com.egov.dto;
├── ApiResponse.java
├── AuditLogDto.java
├── AuditSearchCriteria.java
├── DocumentDto.java
├── DocumentSearchCriteria.java
├── DocumentTypeDto.java
├── NotificationDto.java
├── NotificationSearchCriteria.java
└── NotificationTemplateDto.java
\`\`\`

### 4.2 Incident DTOs
\`\`\`java
// package com.egov.dto.incident;
├── CreateIncidentRequestDto.java
└── IncidentResponseDto.java
\`\`\`

### 4.3 Payment DTOs
\`\`\`java
// package com.egov.dto.payment;
├── PaymentDto.java
├── PaymentRequest.java
├── PaymentResponse.java
├── ProcessPaymentRequest.java
├── ProcessRefundRequest.java
└── RefundDto.java
\`\`\`

### 4.4 Vehicle DTOs
\`\`\`java
// package com.egov.dto.vehicle;
├── CalculateFeesRequest.java
├── RegisterVehicleRequest.java
├── RegistrationFeesDto.java
├── UpdateRegistrationRequest.java
├── VehicleRegistrationDto.java
└── VinVerificationDto.java
\`\`\`

## 5. Core Entities Package

### 5.1 Core Entities
\`\`\`java
// package com.egov.entity;
├── Document.java
├── DocumentType.java
├── Notification.java
├── NotificationTemplate.java
└── Payment.java
\`\`\`

## 6. Enums Package

### 6.1 Enums
\`\`\`java
// package com.egov.enums;
├── AddressStatus.java
├── AddressType.java
├── NotificationStatus.java
├── PriorityLevel.java
└── UserStatus.java
\`\`\`

## 7. Events Package

### 7.1 Events
\`\`\`java
// package com.egov.event;
├── IncidentCreatedEvent.java
└── IncidentStatusChangedEvent.java
\`\`\`

## 8. Integration Package

### 8.1 External Integrations
\`\`\`java
// package com.egov.integration.external;
├── AddressValidationResult.java
├── BiometricApiService.java
├── GoogleMapsService.java
├── SendGridEmailService.java
├── StripePaymentService.java
└── TwilioSmsService.java
\`\`\`

### 8.2 Webhook Handlers
\`\`\`java
// package com.egov.integration.webhook;
├── NotificationWebhookHandler.java
├── PaymentWebhookHandler.java
└── StripeWebhookHandler.java
\`\`\`

## 9. Modules Package Structure

### 9.1 Address Module
\`\`\`java
// Controllers
// package com.egov.modules.address.controller;
├── AddressApplicationController.java
├── AddressVerificationController.java
└── GooglePlacesController.java

// DTOs
// package com.egov.modules.address.dto;
├── AddressApplicationRequest.java
├── AddressApplicationResponse.java
├── AddressVerificationAssignmentRequest.java
└── AddressVerificationResultRequest.java

// Entities
// package com.egov.modules.address.entity;
└── AddressApplication.java

// Models
// package com.egov.modules.address.model;
└── AddressVerification.java

// Repositories
// package com.egov.modules.address.repository;
├── AddressApplicationRepository.java
└── AddressVerificationRepository.java

// Services
// package com.egov.modules.address.service;
├── AddressApplicationService.java
├── AddressService.java
└── LocationTrackingService.java
\`\`\`

### 9.2 Admin Module
\`\`\`java
// Controllers
// package com.egov.modules.admin.controller;
├── AdminDashboardController.java
├── SuperAdminController.java
└── SuperAdminProviderConfigController.java

// DTOs
// package com.egov.modules.admin.dto;
├── ActiveTrackingSessionDto.java
├── AddDynamicFieldRequest.java
├── AdminDashboardStatsDto.java
├── AgentAccountResponse.java
├── CreateAgentAccountRequest.java
├── CreateTemplateRequest.java
├── DynamicFieldRequest.java
├── DynamicFieldResponse.java
├── LocationCheckDto.java
├── LocationMonitoringDto.java
├── ManualVerificationRequest.java
├── ServiceTemplateResponse.java
├── ServiceTemplateWithFieldsDto.java
├── SystemStatsResponse.java
├── UpdateDynamicFieldRequest.java
└── VerificationRecordDto.java

// Entities
// package com.egov.modules.admin.entity;
├── AgentAccount.java
├── DynamicField.java
├── OtpProviderConfig.java
└── ServiceTemplate.java

// Repositories
// package com.egov.modules.admin.repository;
├── DynamicFieldRepository.java
├── OtpProviderConfigRepository.java
└── ServiceTemplateRepository.java

// Services
// package com.egov.modules.admin.service;
├── AdminDashboardService.java
└── SuperAdminService.java
\`\`\`

### 9.3 Agent Module
\`\`\`java
// Controllers
// package com.egov.modules.agent.controller;
└── UnifiedAgentController.java

// DTOs
// package com.egov.modules.agent.dto;
├── AgentAuthResponse.java
├── AgentFeaturesResponse.java
├── AgentLoginRequest.java
├── AgentLoginResponse.java
├── CitizenLookupRequest.java
└── CitizenLookupResponse.java

// Services
// package com.egov.modules.agent.service;
├── AgentAuthService.java
└── UnifiedAgentService.java
\`\`\`

### 9.4 Audit Module
\`\`\`java
// Controllers
// package com.egov.modules.audit.controller;
└── AuditController.java

// Services
// package com.egov.modules.audit.service;
└── AuditService.java
\`\`\`

### 9.5 Auth Module
\`\`\`java
// Controllers
// package com.egov.modules.auth.controller;
├── AccessCodeController.java
├── AuthController.java
└── OtpController.java

// DTOs
// package com.egov.modules.auth.dto;
├── AccessCodeRequest.java
├── AccessCodeResponse.java
├── AccessCodeVerificationRequest.java
├── AccessCodeVerificationResponse.java
├── ChangePasswordRequest.java
├── ForgotPasswordRequest.java
├── LoginRequest.java
├── LoginResponse.java
├── OtpRequest.java
├── OtpResponse.java
├── OtpVerificationRequest.java
├── OtpVerificationResponse.java
├── RefreshTokenRequest.java
├── RegisterRequest.java
├── RegisterResponse.java
├── ResetPasswordRequest.java
├── TokenResponse.java
├── UserInfo.java
├── VerifyEmailRequest.java
└── VerifyPhoneRequest.java

// Entities
// package com.egov.modules.auth.entity;
└── RefreshToken.java

// Repositories
// package com.egov.modules.auth.repository;
└── RefreshTokenRepository.java

// Services
// package com.egov.modules.auth.service;
├── AccessCodeService.java
├── AuthService.java
└── OtpService.java
\`\`\`

### 9.6 Biometric Module
\`\`\`java
// Cache
// package com.egov.modules.biometric.cache;
└── BiometricCacheService.java

// Controllers
// package com.egov.modules.biometric.controller;
├── BiometricAuditController.java
├── BiometricController.java
└── BiometricTemplateController.java

// DTOs
// package com.egov.modules.biometric.dto;
├── BiometricAuditRequest.java
├── BiometricBatchEnrollmentRequest.java
├── BiometricBatchEnrollmentResponse.java
├── BiometricEnrollmentRequest.java
├── BiometricEnrollmentRequestDto.java
├── BiometricEnrollmentResponse.java
├── BiometricEnrollmentStatus.java
├── BiometricTemplateDto.java
├── BiometricVerificationRequest.java
├── BiometricVerificationResponse.java
├── BiometricVerificationResultDto.java
├── EnrollmentStatistics.java
├── TemplateConversionRequest.java
├── TemplateConversionResponse.java
└── VerificationStatistics.java

// Entities
// package com.egov.modules.biometric.entity;
├── BiometricTemplate.java
└── BiometricVerificationLog.java

// Repositories
// package com.egov.modules.biometric.repository;
├── BiometricTemplateRepository.java
└── BiometricVerificationLogRepository.java

// Services
// package com.egov.modules.biometric.service;
├── BiometricAuditService.java
├── BiometricService.java
└── BiometricTemplateService.java

// Utils
// package com.egov.modules.biometric.util;
└── TemplateFormatUtil.java
\`\`\`

### 9.7 Citizen Module
\`\`\`java
// Controllers
// package com.egov.modules.citizen.controller;
├── CitizenAddressController.java
├── CitizenAppController.java
├── CitizenDigitalIdController.java
└── CitizenEmergencyController.java

// DTOs
// package com.egov.modules.citizen.dto;
├── AddressRegistrationRequest.java
├── CitizenAddressDto.java
├── DailyProgressDto.java
├── LocationSubmissionRequest.java
└── TrackingStatusResponse.java

// Services
// package com.egov.modules.citizen.service;
├── CitizenAddressService.java
└── CitizenAppService.java
\`\`\`

### 9.8 Consent Module
\`\`\`java
// Controllers
// package com.egov.modules.consent.controller;
└── ConsentController.java

// DTOs
// package com.egov.modules.consent.dto;
├── ConsentRequest.java
├── ConsentResponse.java
├── ConsentUpdateRequest.java
└── ServiceConsentRequestDto.java

// Entities
// package com.egov.modules.consent.entity;
├── Consent.java
└── ConsentType.java

// Repositories
// package com.egov.modules.consent.repository;
└── ConsentRepository.java

// Services
// package com.egov.modules.consent.service;
└── ConsentService.java
\`\`\`

### 9.9 Document Module
\`\`\`java
// Controllers
// package com.egov.modules.document.controller;
└── DocumentController.java

// Services
// package com.egov.modules.document.service;
└── DocumentService.java
\`\`\`

### 9.10 Emergency Module
\`\`\`java
// Controllers
// package com.egov.modules.emergency.controller;
├── CompleteEmergencyController.java
├── DepartmentController.java
├── EmergencyDispatchController.java
├── EmergencyManagementController.java
├── EquipmentController.java
├── IncidentController.java
├── PatrolController.java
└── TrafficController.java

// DTOs
// package com.egov.modules.emergency.dto;
├── EmergencyContactDto.java
├── EmergencyRequestDto.java
├── EmergencyResponseDto.java
├── FireEmergencyRequest.java
├── MedicalEmergencyRequest.java
├── PoliceEmergencyRequest.java
├── SOSRequest.java
├── SOSResponseDto.java
├── VolunteerResponseDto.java
├── WellnessCheckResponseDto.java
├── WellnessCheckResponseRequest.java
├── WellnessCheckSetupRequest.java
└── WellnessCheckSetupResponseDto.java

// Entities
// package com.egov.modules.emergency.entity;
├── Department.java
├── EmergencyResponse.java
├── Incident.java
└── Officer.java

// Repositories
// package com.egov.modules.emergency.repository;
├── DepartmentRepository.java
├── EmergencyResponseRepository.java
├── IncidentRepository.java
└── OfficerRepository.java

// Services
// package com.egov.modules.emergency.service;
├── DepartmentService.java
├── EmergencyContactService.java
├── IncidentService.java
├── LocationTrackingService.java
├── OfficerService.java
└── WellnessCheckService.java
\`\`\`

### 9.11 Government Module
\`\`\`java
// Controllers
// package com.egov.modules.government.controller;
├── ClientController.java
├── CompleteGovernmentController.java
├── CreditScoreController.java
├── DigitalAddressController.java
├── KycController.java
├── LicenseController.java
├── SimRegistrationController.java
└── VehicleController.java

// DTOs
// package com.egov.modules.government.dto;
├── ApplicationStatusDto.java
├── ApplyLicenseRequest.java
├── CreditScoreDto.java
├── DigitalAddressDto.java
├── IssueLicenseRequest.java
├── KycDto.java
├── LicenseApplicationDto.java
├── LicenseDto.java
├── LicenseRenewalDto.java
├── LicenseTypeDto.java
├── LicenseVerificationDto.java
├── PaymentDto.java
├── ProcessPaymentRequest.java
├── ScheduleTestRequest.java
├── SimRegistrationDto.java
├── SubmitTestResultsRequest.java
├── TestResultDto.java
└── TestScheduleDto.java

// Entities
// package com.egov.modules.government.entity;
├── Client.java
└── DigitalAddress.java

// Enums
// package com.egov.modules.government.enums;
├── AddressStatus.java
└── AddressType.java

// Repositories
// package com.egov.modules.government.repository;
├── CreditScoreRepository.java
├── DigitalAddressRepository.java
└── SimRegistrationRepository.java

// Services
// package com.egov.modules.government.service;
├── ClientService.java
├── CreditScoreService.java
├── DigitalAddressService.java
└── SimRegistrationService.java
\`\`\`

### 9.12 KYC Module
\`\`\`java
// Controllers
// package com.egov.modules.kyc.controller;
└── KycAdminController.java
\`\`\`

### 9.13 Notification Module
\`\`\`java
// Controllers
// package com.egov.modules.notification.controller;
└── NotificationController.java

// DTOs
// package com.egov.modules.notification.dto;
├── OtpProviderResponse.java
├── OtpRequest.java
├── OtpResponse.java
├── OtpUseCase.java
├── OtpVerificationRequest.java
└── OtpVerificationResponse.java

// Providers
// package com.egov.modules.notification.provider;
├── AwsSnsOtpProvider.java
├── D7NetworkOtpProvider.java
├── FirebaseOtpProvider.java
├── OtpProvider.java
├── OtpProviderType.java
└── TwilioOtpProvider.java

// Services
// package com.egov.modules.notification.service;
├── MultiProviderOtpService.java
├── NotificationService.java
└── RealTimeNotificationService.java
\`\`\`

### 9.14 Patrol Module
\`\`\`java
// Controllers
// package com.egov.modules.patrol.controller;
├── CompletePatrolController.java
└── PatrolResponseController.java

// DTOs
// package com.egov.modules.patrol.dto;
├── AddTeamMemberRequest.java
├── CheckInRequest.java
├── CheckInResponse.java
├── CreatePatrolRouteRequest.java
├── CreateTrainingRequest.java
├── EquipmentDto.java
├── EquipmentRequestDto.java
├── EquipmentRequestRequest.java
├── IncidentAcceptanceResponse.java
├── IncidentCompletionRequest.java
├── IncidentCompletionResponse.java
├── LicenseVerificationRequest.java
├── LicenseVerificationResponse.java
├── LocationUpdateRequest.java
├── NearbyIncidentDto.java
├── PatrolCheckpointDto.java
├── PatrolOfficerDto.java
├── PatrolRouteDto.java
├── PatrolScheduleDto.java
├── PatrolTeamDto.java
├── ShiftDto.java
├── TrafficStopRequest.java
├── TrafficStopResponse.java
├── TrainingDto.java
└── TrainingOverviewDto.java

// Entities
// package com.egov.modules.patrol.entity;
├── Equipment.java
├── PatrolOfficer.java
├── PatrolRoute.java
├── PatrolSchedule.java
├── PatrolTeam.java
└── Training.java

// Repositories
// package com.egov.modules.patrol.repository;
├── EquipmentRepository.java
├── PatrolOfficerRepository.java
├── PatrolRouteRepository.java
├── PatrolScheduleRepository.java
├── PatrolTeamRepository.java
└── TrainingRepository.java

// Services
// package com.egov.modules.patrol.service;
├── PatrolResponseService.java
└── PatrolTeamService.java
\`\`\`

### 9.15 Payment Module
\`\`\`java
// Controllers
// package com.egov.modules.payment.controller;
├── BillingController.java
└── PaymentController.java

// Services
// package com.egov.modules.payment.service;
├── MultiProviderPaymentService.java
└── PaymentService.java
\`\`\`

### 9.16 Police Module
\`\`\`java
// Controllers
// package com.egov.modules.police.controller;
├── PoliceAdminController.java
├── PolicePatrolController.java
├── PoliceSearchController.java
└── PoliceVehicleManagementController.java

// Services
// package com.egov.modules.police.service;
├── DigitalLicenseService.java
├── EvidenceService.java
├── InvestigationService.java
├── PatrolService.java
├── PoliceAdminService.java
├── PolicePatrolService.java
├── PoliceSearchService.java
└── TrafficService.java
\`\`\`

### 9.17 Reporting Module
\`\`\`java
// Controllers
// package com.egov.modules.reporting.controller;
├── AnalyticsController.java
├── DashboardController.java
└── ReportsController.java

// Services
// package com.egov.modules.reporting.service;
├── AnalyticsService.java
└── ReportingService.java
\`\`\`

### 9.18 Service Module
\`\`\`java
// Controllers
// package com.egov.modules.service.controller;
├── DynamicServiceController.java
└── ServiceTemplateController.java

// DTOs
// package com.egov.modules.service.dto;
├── AddServiceRequest.java
├── CitizenServiceDto.java
└── ServiceTemplateDto.java

// Entities
// package com.egov.modules.service.entity;
└── ServiceTemplate.java

// Repositories
// package com.egov.modules.service.repository;
└── ServiceTemplateRepository.java

// Services
// package com.egov.modules.service.service;
├── CitizenServiceService.java
├── DynamicServiceService.java
└── ServiceTemplateService.java
\`\`\`

### 9.19 Support Module
\`\`\`java
// Controllers
// package com.egov.modules.support.controller;
└── SupportController.java

// Services
// package com.egov.modules.support.service;
└── SupportService.java
\`\`\`

### 9.20 System Module
\`\`\`java
// Controllers
// package com.egov.modules.system.controller;
├── MonitoringController.java
└── SystemController.java

// DTOs
// package com.egov.modules.system.dto;
├── BackupDto.java
├── CreateBackupRequest.java
├── MaintenanceModeDto.java
├── SystemConfigDto.java
├── SystemHealthDto.java
├── SystemStatusDto.java
├── ToggleMaintenanceModeRequest.java
└── UpdateSystemConfigRequest.java

// Services
// package com.egov.modules.system.service;
└── SystemService.java
\`\`\`

### 9.21 Tenant Module
\`\`\`java
// Entities
// package com.egov.modules.tenant.entity;
└── Tenant.java

// Repositories
// package com.egov.modules.tenant.repository;
└── TenantRepository.java
\`\`\`

### 9.22 User Module
\`\`\`java
// Controllers
// package com.egov.modules.user.controller;
└── UserController.java

// DTOs
// package com.egov.modules.user.dto;
├── CreateUserRequest.java
├── RoleDto.java
├── UpdateProfileRequest.java
├── UpdateUserRequest.java
├── UserDto.java
├── UserInfo.java
└── UserProfileDto.java

// Entities
// package com.egov.modules.user.entity;
├── Permission.java
├── Role.java
└── User.java

// Repositories
// package com.egov.modules.user.repository;
├── PermissionRepository.java
├── RoleRepository.java
└── UserRepository.java

// Services
// package com.egov.modules.user.service;
└── UserService.java
\`\`\`

### 9.23 Vehicle Module
\`\`\`java
// Controllers
// package com.egov.modules.vehicle.controller;
└── VehicleRegistrationController.java

// DTOs
// package com.egov.modules.vehicle.dto;
├── AddDriverRequest.java
├── VehicleDriverResponse.java
├── VehiclePaymentResponse.java
├── VehicleRegistrationDetailsResponse.java
├── VehicleRegistrationRequest.java
├── VehicleRegistrationResponse.java
└── VehicleRegistrationSummary.java

// Entities
// package com.egov.modules.vehicle.entity;
├── DriverStatus.java
├── FuelType.java
├── RegistrationStatus.java
├── RegistrationType.java
├── Vehicle.java
├── VehicleDriver.java
├── VehiclePayment.java
├── VehicleRegistration.java
├── VehicleStatus.java
└── VehicleType.java

// Repositories
// package com.egov.modules.vehicle.repository;
├── VehicleDriverRepository.java
├── VehiclePaymentRepository.java
├── VehicleRegistrationRepository.java
└── VehicleRepository.java

// Services
// package com.egov.modules.vehicle.service;
└── VehicleRegistrationService.java
\`\`\`

## 10. Core Services Package

### 10.1 Core Services
\`\`\`java
// package com.egov.service;
├── DepartmentService.java
├── FileStorageService.java
├── LicenseService.java
├── PatrolService.java
├── PaymentService.java
└── VehicleService.java
\`\`\`

## 11. Core Repositories Package

### 11.1 Core Repositories
\`\`\`java
// package com.egov.repository;
└── PaymentRepository.java
\`\`\`

## 12. Resources Structure

### 12.1 Application Configuration
\`\`\`
src/main/resources/
├── application.yml
├── application-dev.yml
├── application-prod.yml
├── application-test.yml
├── banner.txt
└── messages.properties (+ localized versions)
\`\`\`

### 12.2 Database Structure
\`\`\`
src/main/resources/db/
├── migration/
│   ├── V1__Initial_Schema.sql
│   ├── V1__Initial_Complete_Schema.sql
│   ├── V2__Biometric_Integration_Schema.sql
│   ├── V2__Complete_Biometric_Integration.sql
│   ├── V3__Update_Biometric_Schema_For_FGTIT.sql
│   └── V4__Add_Missing_Tables.sql
├── data/
│   ├── demo-data.sql
│   └── test-data.sql
└── procedures/
    ├── incident_procedures.sql
    └── reporting_procedures.sql
\`\`\`

### 12.3 OpenAPI Documentation
\`\`\`
src/main/resources/openapi/
└── egov-openapi-spec.yaml
\`\`\`

## 13. Test Structure

### 13.1 Test Configuration
\`\`\`java
// package com.egov.config;
├── TestContainerConfig.java
├── TestDatabaseConfig.java
└── TestSecurityConfig.java
\`\`\`

### 13.2 Module Tests
\`\`\`java
// package com.egov.modules.biometric.integration;
└── BiometricIntegrationTest.java

// package com.egov.modules.biometric.service;
├── BiometricServiceTest.java
└── BiometricTemplateServiceTest.java

// package com.egov.modules.notification.service;
└── MultiProviderOtpServiceTest.java

// package com.egov.modules.vehicle.service;
└── VehicleRegistrationServiceTest.java
\`\`\`

## 14. Build and Deployment

### 14.1 Build Files
\`\`\`
├── pom.xml (Main Maven configuration)
├── pom-client-generation.xml (Client generation)
├── openapi-generator-config.yaml
└── target/ (Build output directory)
\`\`\`

### 14.2 Docker Configuration
\`\`\`
├── Dockerfile
├── Dockerfile.client-generation
├── docker-compose.yml
└── docker-compose.client-generation.yml
\`\`\`

### 14.3 Scripts
\`\`\`
scripts/
├── build-check.sh
├── final-validation.sh
├── fix-permissions.sh
├── generate-clients.sh
└── test-compile.sh
\`\`\`

## 15. Documentation

### 15.1 Documentation Structure
\`\`\`
docs/
├── api/
│   ├── API_DOCUMENTATION.md
│   ├── COMPLETE_API_ENDPOINTS_DOCUMENTATION.md
│   ├── DTO_DOCUMENTATION.md
│   └── egov-postman-collection.json
├── architecture/
│   ├── database-design.md
│   ├── security-architecture.md
│   └── system-architecture.md
├── client-generation/
│   └── CLIENT_GENERATION_GUIDE.md
├── development/
│   ├── coding-standards.md
│   ├── deployment-guide.md
│   └── testing-guidelines.md
├── user-guides/
│   ├── admin-guide.md
│   ├── citizen-guide.md
│   └── officer-guide.md
└── COMPLETE_SYSTEM_FLOW_DOCUMENTATION.md
\`\`\`

## 16. Key Import Path Examples

### 16.1 Controller Import Examples
\`\`\`java
// Main Application Controller
import com.egov.modules.auth.controller.AuthController;
import com.egov.modules.citizen.controller.CitizenAppController;
import com.egov.modules.emergency.controller.EmergencyManagementController;
import com.egov.modules.government.controller.LicenseController;
import com.egov.modules.patrol.controller.PatrolResponseController;
import com.egov.modules.vehicle.controller.VehicleRegistrationController;
\`\`\`

### 16.2 Service Import Examples
\`\`\`java
// Service Layer Imports
import com.egov.modules.auth.service.AuthService;
import com.egov.modules.biometric.service.BiometricService;
import com.egov.modules.notification.service.NotificationService;
import com.egov.modules.payment.service.PaymentService;
import com.egov.service.PatrolService;
import com.egov.service.VehicleService;
\`\`\`

### 16.3 Entity Import Examples
\`\`\`java
// Entity Imports
import com.egov.modules.user.entity.User;
import com.egov.modules.vehicle.entity.Vehicle;
import com.egov.modules.emergency.entity.Incident;
import com.egov.common.entity.BaseEntity;
import com.egov.entity.Payment;
\`\`\`

### 16.4 DTO Import Examples
\`\`\`java
// DTO Imports
import com.egov.common.dto.ApiResponse;
import com.egov.modules.auth.dto.LoginRequest;
import com.egov.modules.vehicle.dto.VehicleRegistrationRequest;
import com.egov.modules.emergency.dto.EmergencyRequestDto;
import com.egov.dto.payment.PaymentRequest;
\`\`\`

### 16.5 Configuration Import Examples
\`\`\`java
// Configuration Imports
import com.egov.config.SecurityConfig;
import com.egov.config.DatabaseConfig;
import com.egov.config.BiometricConfig;
import com.egov.common.security.JwtTokenProvider;
import com.egov.common.security.TenantContext;
\`\`\`

## 17. Module Dependencies and Relationships

### 17.1 Core Dependencies
- **Common Package**: Used by all modules for shared functionality
- **Config Package**: Provides configuration for all modules
- **Security Package**: Handles authentication and authorization across modules

### 17.2 Module Interactions
- **Auth Module** → **User Module** → **All Other Modules**
- **Payment Module** → **Government Module**, **Vehicle Module**
- **Notification Module** → **All Modules** (for notifications)
- **Biometric Module** → **Auth Module**, **Government Module**
- **Emergency Module** → **Patrol Module**, **Notification Module**

## 18. Key Features by Module

### 18.1 Authentication & Authorization
- JWT-based authentication
- Role-based access control
- Multi-factor authentication
- OTP verification

### 18.2 Multi-tenancy
- Tenant-based data isolation
- Tenant context management
- Tenant-specific configurations

### 18.3 Biometric Integration
- Fingerprint enrollment and verification
- Template format conversion
- Batch processing capabilities

### 18.4 Government Services
- License management
- Vehicle registration
- Digital address system
- KYC verification

### 18.5 Emergency Services
- Incident management
- Patrol coordination
- Emergency response
- Real-time tracking

This structure provides a comprehensive overview of the entire E-Government Unified Backend System, showing how all components are organized and interconnected.
