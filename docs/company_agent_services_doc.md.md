# 🏦 Company Agent Services - Complete Implementation Guide

## 📋 Service Overview
**Platform**: Android App with Bluetooth Fingerprint SDK
**Primary Users**: BANK_AGENT, TELCO_AGENT, COURT_AGENT, REAL_ESTATE_AGENT, INSURANCE_AGENT
**Core Function**: Limited permissions for partner company field operations

## 🎯 Real-World Flow
```
AGENT Login → Citizen Lookup → Biometric Verification (Bluetooth Scanner) 
→ Service Creation → Consent Management → Limited Permissions
```

## 📁 Complete File Structure & Implementation Checklist

### ✅ Root Application
```
company-agent-service/
└── src/main/java/com/egov/companyagent/
    ├── CompanyAgentApplication.java ✓
```

**CompanyAgentApplication.java** - Partner ecosystem entry point
```java
// Main Spring Boot application for partner companies
// Enables: JPA, Security, REST, Cache
// Configures: Biometric SDK integration, Limited permissions
```

### ✅ Controllers (Partner Agent Web Layer)
```
├── controller/
│   ├── bank/
│   │   ├── BankAgentController.java ✓
│   │   ├── AccountOpeningController.java ✓
│   │   └── BankKycController.java ✓
│   ├── telco/
│   │   ├── TelcoAgentController.java ✓
│   │   ├── SimRegistrationController.java ✓
│   │   └── LineVerificationController.java ✓
│   ├── court/
│   │   ├── CourtAgentController.java ✓
│   │   ├── LegalDocumentController.java ✓
│   │   └── CaseManagementController.java ✓
│   ├── realestate/
│   │   ├── RealEstateAgentController.java ✓
│   │   ├── PropertyVerificationController.java ✓
│   │   └── PropertyRegistrationController.java ✓
│   ├── insurance/
│   │   ├── InsuranceAgentController.java ✓
│   │   ├── PolicyVerificationController.java ✓
│   │   └── ClaimProcessingController.java ✓
│   └── shared/
│       ├── AgentAuthController.java ✓
│       ├── FingerprintController.java ✓
│       └── CitizenLookupController.java ✓
```

**BankAgentController.java** - Bank agent operations
```java
// API Endpoints:
// POST /api/agent/login - Agent login
// POST /api/agent/citizen/lookup - Citizen lookup
// POST /api/agent/biometric/verify - Biometric verification
// POST /api/agent/services/add - Add service
// Real-world: Bank agent opens account for customer at market
```

**FingerprintController.java** - Bluetooth fingerprint operations
```java
// API Endpoints:
// POST /api/agent/biometric/enroll - Enroll fingerprint
// POST /api/agent/biometric/verify - Verify fingerprint  
// GET /api/agent/biometric/status/{userId} - Get biometric status
// Real-world: Agent uses Bluetooth scanner to verify customer identity
```

**SimRegistrationController.java** - Telco SIM registration
```java
// API Endpoints:
// POST /api/agent/telco/sim-register - Register SIM card
// GET /api/sim-registration/{simNumber} - Get SIM details
// PUT /api/sim-registration/{id}/status - Update SIM status
// Real-world: Agent registers new SIM with biometric verification
```

### ✅ Services (Partner Business Logic)
```
├── service/
│   ├── interface/
│   │   ├── bank/
│   │   │   ├── BankAgentService.java ✓
│   │   │   ├── AccountOpeningService.java ✓
│   │   │   └── BankKycService.java ✓
│   │   ├── telco/
│   │   │   ├── TelcoAgentService.java ✓
│   │   │   ├── SimRegistrationService.java ✓
│   │   │   └── LineVerificationService.java ✓
│   │   ├── court/
│   │   │   ├── CourtAgentService.java ✓
│   │   │   ├── LegalDocumentService.java ✓
│   │   │   └── CaseManagementService.java ✓
│   │   ├── realestate/
│   │   │   ├── RealEstateAgentService.java ✓
│   │   │   ├── PropertyVerificationService.java ✓
│   │   │   └── PropertyRegistrationService.java ✓
│   │   ├── insurance/
│   │   │   ├── InsuranceAgentService.java ✓
│   │   │   ├── PolicyVerificationService.java ✓
│   │   │   └── ClaimProcessingService.java ✓
│   │   └── shared/
│   │       ├── AgentCoreService.java ✓
│   │       ├── FingerprintService.java ✓
│   │       └── CitizenLookupService.java ✓
│   ├── impl/
│   │   ├── bank/
│   │   │   ├── BankAgentServiceImpl.java ✓
│   │   │   ├── AccountOpeningServiceImpl.java ✓
│   │   │   └── BankKycServiceImpl.java ✓
│   │   ├── telco/
│   │   │   ├── TelcoAgentServiceImpl.java ✓
│   │   │   ├── SimRegistrationServiceImpl.java ✓
│   │   │   └── LineVerificationServiceImpl.java ✓
│   │   ├── court/
│   │   │   ├── CourtAgentServiceImpl.java ✓
│   │   │   ├── LegalDocumentServiceImpl.java ✓
│   │   │   └── CaseManagementServiceImpl.java ✓
│   │   ├── realestate/
│   │   │   ├── RealEstateAgentServiceImpl.java ✓
│   │   │   ├── PropertyVerificationServiceImpl.java ✓
│   │   │   └── PropertyRegistrationServiceImpl.java ✓
│   │   ├── insurance/
│   │   │   ├── InsuranceAgentServiceImpl.java ✓
│   │   │   ├── PolicyVerificationServiceImpl.java ✓
│   │   │   └── ClaimProcessingServiceImpl.java ✓
│   │   └── shared/
│   │       ├── AgentCoreServiceImpl.java ✓
│   │       ├── FingerprintServiceImpl.java ✓
│   │       └── CitizenLookupServiceImpl.java ✓
│   └── validation/
│       ├── BankValidationService.java ✓
│       ├── TelcoValidationService.java ✓
│       ├── CourtValidationService.java ✓
│       └── AgentPermissionService.java ✓
```

**FingerprintService.java** - Biometric operations service
```java
// Business Rules:
// 1. Bluetooth SDK integration for fingerprint capture
// 2. Template format conversion and validation
// 3. Biometric matching against citizen database
// 4. Audit trail for all biometric operations
```

**AgentPermissionService.java** - Limited permissions management
```java
// Features:
// 1. Role-based access control for agents
// 2. Service-specific permission validation
// 3. Data access restrictions
// 4. Consent-based operations only
```

### ✅ Repository (Partner Data Layer)
```
├── repository/
│   ├── bank/
│   │   ├── BankAgentRepository.java ✓
│   │   ├── BankAccountRepository.java ✓
│   │   └── BankKycRepository.java ✓
│   ├── telco/
│   │   ├── TelcoAgentRepository.java ✓
│   │   ├── SimCardRepository.java ✓
│   │   └── TelcoServiceRepository.java ✓
│   ├── court/
│   │   ├── CourtAgentRepository.java ✓
│   │   ├── LegalDocumentRepository.java ✓
│   │   └── CourtCaseRepository.java ✓
│   ├── realestate/
│   │   ├── RealEstateAgentRepository.java ✓
│   │   ├── PropertyRepository.java ✓
│   │   └── PropertyTransactionRepository.java ✓
│   ├── insurance/
│   │   ├── InsuranceAgentRepository.java ✓
│   │   ├── InsurancePolicyRepository.java ✓
│   │   └── InsuranceClaimRepository.java ✓
│   └── shared/
│       ├── AgentRepository.java ✓
│       ├── CompanyRepository.java ✓
│       └── ServiceTemplateRepository.java ✓
```

**AgentRepository.java** - Agent data operations
```java
// JPA Methods:
// findByEmployeeIdAndCompany()
// findActiveAgentsByLocation()
// findAgentsByPermissionLevel()
// countAgentActivitiesByDateRange()
```

### ✅ Entities (Partner Data Models)
```
├── entity/
│   ├── bank/
│   │   ├── BankAgent.java ✓
│   │   ├── BankAccount.java ✓
│   │   ├── BankCustomer.java ✓
│   │   └── BankTransaction.java ✓
│   ├── telco/
│   │   ├── TelcoAgent.java ✓
│   │   ├── SimCard.java ✓
│   │   ├── TelcoCustomer.java ✓
│   │   └── TelcoService.java ✓
│   ├── court/
│   │   ├── CourtAgent.java ✓
│   │   ├── LegalDocument.java ✓
│   │   ├── CourtCase.java ✓
│   │   └── LegalClient.java ✓
│   ├── realestate/
│   │   ├── RealEstateAgent.java ✓
│   │   ├── Property.java ✓
│   │   ├── PropertyTransaction.java ✓
│   │   └── PropertyOwner.java ✓
│   ├── insurance/
│   │   ├── InsuranceAgent.java ✓
│   │   ├── InsurancePolicy.java ✓
│   │   ├── InsuranceClaim.java ✓
│   │   └── InsuranceClient.java ✓
│   └── shared/
│       ├── Agent.java ✓
│       ├── Company.java ✓
│       ├── ServiceTemplate.java ✓
│       └── AgentSession.java ✓
```

**Agent.java** - Core agent entity
```java
// Fields: agentId, employeeId, company, permissions, status
// Relations: ManyToOne with Company, OneToMany with Sessions
// Features: Limited permission levels, activity tracking
// Security: Role-based access control
```

**ServiceTemplate.java** - Dynamic service template entity  
```java
// Fields: templateId, companyId, serviceName, dynamicFields
// Features: Company-specific service configurations
// Relations: ManyToOne with Company
// Usage: Different banks can have different account types
```

### ✅ DTOs (Partner Data Transfer)
```
├── dto/
│   ├── request/
│   │   ├── bank/
│   │   │   ├── AccountOpeningRequest.java ✓
│   │   │   ├── BankKycRequest.java ✓
│   │   │   └── BankTransactionRequest.java ✓
│   │   ├── telco/
│   │   │   ├── SimRegistrationRequest.java ✓
│   │   │   ├── LineActivationRequest.java ✓
│   │   │   └── TelcoServiceRequest.java ✓
│   │   ├── court/
│   │   │   ├── DocumentVerificationRequest.java ✓
│   │   │   ├── CaseRegistrationRequest.java ✓
│   │   │   └── LegalServiceRequest.java ✓
│   │   ├── realestate/
│   │   │   ├── PropertyVerificationRequest.java ✓
│   │   │   ├── PropertyRegistrationRequest.java ✓
│   │   │   └── PropertyTransferRequest.java ✓
│   │   ├── insurance/
│   │   │   ├── PolicyApplicationRequest.java ✓
│   │   │   ├── ClaimSubmissionRequest.java ✓
│   │   │   └── PolicyVerificationRequest.java ✓
│   │   └── shared/
│   │       ├── AgentLoginRequest.java ✓
│   │       ├── FingerprintScanRequest.java ✓
│   │       └── CitizenLookupRequest.java ✓
│   ├── response/
│   │   ├── bank/
│   │   │   ├── AccountResponse.java ✓
│   │   │   ├── BankKycResponse.java ✓
│   │   │   └── TransactionResponse.java ✓
│   │   ├── telco/
│   │   │   ├── SimRegistrationResponse.java ✓
│   │   │   ├── LineStatusResponse.java ✓
│   │   │   └── TelcoServiceResponse.java ✓
│   │   ├── court/
│   │   │   ├── DocumentStatusResponse.java ✓
│   │   │   ├── CaseStatusResponse.java ✓
│   │   │   └── LegalServiceResponse.java ✓
│   │   ├── realestate/
│   │   │   ├── PropertyStatusResponse.java ✓
│   │   │   ├── VerificationResponse.java ✓
│   │   │   └── TransactionResponse.java ✓
│   │   ├── insurance/
│   │   │   ├── PolicyResponse.java ✓
│   │   │   ├── ClaimStatusResponse.java ✓
│   │   │   └── VerificationResponse.java ✓
│   │   └── shared/
│   │       ├── AgentAuthResponse.java ✓
│   │       ├── FingerprintResponse.java ✓
│   │       └── CitizenLookupResponse.java ✓
│   └── mapper/
│       ├── BankMapper.java ✓
│       ├── TelcoMapper.java ✓
│       ├── CourtMapper.java ✓
│       ├── RealEstateMapper.java ✓
│       ├── InsuranceMapper.java ✓
│       └── AgentMapper.java ✓
```

### ✅ Configuration (Partner System Config)
```
├── config/
│   ├── CompanyAgentSecurityConfig.java ✓
│   ├── FingerprintSdkConfig.java ✓
│   └── AgentDatabaseConfig.java ✓
```

**FingerprintSdkConfig.java** - Bluetooth SDK configuration
```java
// Features:
// 1. Bluetooth fingerprint scanner integration
// 2. SDK initialization and management
// 3. Template format configurations
// 4. Error handling for hardware failures
```

### ✅ Exception Handling
```
├── exception/
│   ├── AgentNotFoundException.java ✓
│   ├── FingerprintScanException.java ✓
│   ├── CitizenLookupException.java ✓
│   ├── PermissionDeniedException.java ✓
│   └── CompanyAgentExceptionHandler.java ✓
```

### ✅ Security (Limited Access)
```
├── security/
│   ├── AgentAuthProvider.java ✓
│   ├── CompanyTokenProvider.java ✓
│   ├── AgentPermissionValidator.java ✓
│   └── LimitedAccessController.java ✓
```

**LimitedAccessController.java** - Restricts agent permissions
```java
// Security Rules:
// 1. Agents can only access their company's data
// 2. Consent required for citizen data access
// 3. Limited to specific service operations
// 4. No access to sensitive government data
```

### ✅ Utilities
```
├── util/
│   ├── FingerprintUtil.java ✓
│   ├── CompanyTemplateUtil.java ✓
│   ├── AgentValidationUtil.java ✓
│   └── BluetoothSdkUtil.java ✓
```

**BluetoothSdkUtil.java** - Bluetooth fingerprint utilities
```java
// Utilities:
// 1. Bluetooth device discovery
// 2. Fingerprint template processing
// 3. Quality assessment
// 4. Error code translation
```

### ✅ Integration (External Partners)
```
└── integration/
    ├── FingerprintSdkIntegration.java ✓
    ├── BiometricVerificationClient.java ✓
    ├── CitizenServiceClient.java ✓
    └── CompanyNotificationClient.java ✓
```

## 🔗 Key API Endpoints

### Agent Authentication
- `POST /api/agent/login` - Agent Login
- `GET /api/agent/features` - Get Agent Features
- `POST /api/access-code/verify` - Verify Access Code

### Citizen Services
- `POST /api/agent/citizen/lookup` - Citizen Lookup
- `POST /api/agent/consent/request` - Request Consent
- `GET /api/agent/consent/status/{consentId}` - Check Consent Status

### Biometric Operations
- `POST /api/agent/biometric/enroll` - Enroll Fingerprint
- `POST /api/agent/biometric/verify` - Verify Fingerprint
- `GET /api/agent/biometric/status/{userId}` - Get Biometric Status

### Service Operations
- `GET /api/agent/services/templates` - Get Service Templates
- `POST /api/agent/services/add` - Add Service
- `POST /api/agent/telco/sim-register` - Telco SIM Registration

## 🌟 Real-World Implementation Scenarios

### Scenario 1: Bank Account Opening at Market
```java
// 1. Bank agent visits Osu Market with tablet
// 2. Customer wants to open business account
// 3. Agent uses Bluetooth scanner for fingerprints
// 4. Verifies customer identity via biometric
// 5. Creates account using dynamic template
// 6. Account number generated immediately
```

### Scenario 2: Telco SIM Registration Drive
```java
// 1. Telco agents setup at university campus
// 2. Students queue for SIM registration
// 3. Agent scans student ID and fingerprints
// 4. Verifies against national database
// 5. Registers SIM with biometric link
// 6. SIM activated immediately
```

### Scenario 3: Real Estate Property Verification
```java
// 1. Real estate agent meets client at property
// 2. Verifies client identity via biometric
// 3. Records property inspection details
// 4. Uploads property photos and documents
// 5. Submits verification to land registry
// 6. Client receives verification certificate
```

## 📝 Implementation Order

### Phase 1: Agent Core Setup
1. ✅ Create CompanyAgentApplication.java
2. ✅ Setup limited permission system
3. ✅ Configure Bluetooth SDK integration
4. ✅ Setup company database schema

### Phase 2: Biometric Integration
5. ✅ Create fingerprint entities and services
6. ✅ Implement Bluetooth SDK wrapper
7. ✅ Create biometric verification flow
8. ✅ Test fingerprint capture and matching

### Phase 3: Company Services
9. ✅ Implement bank agent services
10. ✅ Implement telco agent services
11. ✅ Implement court agent services
12. ✅ Implement real estate and insurance services

### Phase 4: Agent Controllers
13. ✅ Create agent authentication controllers
14. ✅ Create service-specific controllers
15. ✅ Setup consent management
16. ✅ Test agent workflows

### Phase 5: Security & Integration
17. ✅ Configure limited access security
18. ✅ Setup citizen service integration
19. ✅ Create company notification system
20. ✅ End-to-end agent testing

## 🚀 Firebase Studio Development Steps

1. **Agent Setup**: Create "company-agent-service" project
2. **Bluetooth Config**: Configure fingerprint SDK integration
3. **Company Structure**: Create bank, telco, court modules
4. **Permission System**: Implement limited access controls
5. **Testing**: Test agent scenarios with biometric
6. **Clean Install**: `mvn clean install`

## 💡 Key Features
- **Limited Permissions**: Restricted access compared to government
- **Bluetooth Integration**: Fingerprint scanner SDK integration
- **Multi-Company**: Support for banks, telcos, courts, etc.
- **Dynamic Templates**: Company-specific service configurations
- **Consent Management**: Citizen consent for data access
- **Audit Trail**: Complete activity logging for compliance

This microservice enables partner organizations to provide services while maintaining strict security and limited access controls.