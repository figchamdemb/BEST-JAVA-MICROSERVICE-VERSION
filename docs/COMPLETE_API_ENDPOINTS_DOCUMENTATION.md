# Complete API Endpoints Documentation

## E-Government Unified Backend System - All API Paths

This document lists all API endpoints available in the system, organized by module and controller.

---

## 1. ADDRESS MODULE

### AddressApplicationController
**Base Path:** `/api/address-applications`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| POST | `/api/address-applications/submit` | Submit address verification application | CITIZEN |
| GET | `/api/address-applications/pending` | Get pending applications | SUPER_ADMIN |
| POST | `/api/address-applications/assign-agent` | Assign verification agent | SUPER_ADMIN |
| POST | `/api/address-applications/submit-verification` | Submit verification results | VERIFICATION_AGENT |
| GET | `/api/address-applications/track/{applicationNumber}` | Track application status | Public |
| GET | `/api/address-applications/my-applications` | Get user's applications | CITIZEN |

### AddressVerificationController
**Base Path:** `/address-verification`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| GET | `/address-verification/tracking` | Get tracking data | SUPER_ADMIN, DEPARTMENT_ADMIN, VERIFICATION_AGENT |
| POST | `/address-verification/request` | Request address verification | SUPER_ADMIN, DEPARTMENT_ADMIN, CITIZEN |
| POST | `/address-verification/track` | Track address verification | SUPER_ADMIN, DEPARTMENT_ADMIN, VERIFICATION_AGENT |
| PUT | `/address-verification/{id}/complete` | Complete verification | SUPER_ADMIN, VERIFICATION_AGENT |
| GET | `/address-verification/pending` | Get pending verifications | SUPER_ADMIN, VERIFICATION_AGENT |
| GET | `/address-verification/completed` | Get completed verifications | SUPER_ADMIN, DEPARTMENT_ADMIN, VERIFICATION_AGENT |
| POST | `/address-verification/validate` | Validate address | SUPER_ADMIN, DEPARTMENT_ADMIN, VERIFICATION_AGENT |

### GooglePlacesController
**Base Path:** `/google-places`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| POST | `/google-places/register` | Register place with Google Places | SUPER_ADMIN, DEPARTMENT_ADMIN, CITIZEN |
| GET | `/google-places/status` | Get registration status | SUPER_ADMIN, DEPARTMENT_ADMIN |
| GET | `/google-places/pending` | Get pending registrations | SUPER_ADMIN, DEPARTMENT_ADMIN |
| GET | `/google-places/completed` | Get completed registrations | SUPER_ADMIN, DEPARTMENT_ADMIN |
| POST | `/google-places/search` | Search places | Public |
| GET | `/google-places/{id}/details` | Get place details | Public |

---

## 2. ADMIN MODULE

### AdminDashboardController
**Base Path:** `/api/admin/dashboard`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| GET | `/api/admin/dashboard/stats` | Get dashboard statistics | ADMIN, SUPER_ADMIN |
| GET | `/api/admin/dashboard/active-tracking-sessions` | Get active tracking sessions | ADMIN, SUPER_ADMIN |
| GET | `/api/admin/dashboard/location-monitoring/{userId}` | Get location monitoring | ADMIN, SUPER_ADMIN |
| POST | `/api/admin/dashboard/verify-address/{verificationId}` | Manually verify address | ADMIN, SUPER_ADMIN |
| POST | `/api/admin/dashboard/assign-verification/{verificationId}` | Assign for verification | ADMIN, SUPER_ADMIN |

### SuperAdminController
**Base Path:** `/api/v1/admin/super`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| GET | `/api/v1/admin/super/dashboard` | Super Admin Dashboard | SUPER_ADMIN |
| GET | `/api/v1/admin/super/system/health` | System Health Check | SUPER_ADMIN |
| POST | `/api/v1/admin/super/agents/create` | Create Agent Account | SUPER_ADMIN |
| GET | `/api/v1/admin/super/agents` | Get All Agents | SUPER_ADMIN |
| PUT | `/api/v1/admin/super/agents/{agentId}/status` | Update Agent Status | SUPER_ADMIN |
| POST | `/api/v1/admin/super/templates/create` | Create Service Template | SUPER_ADMIN |
| GET | `/api/v1/admin/super/templates` | Get All Templates | SUPER_ADMIN |
| POST | `/api/v1/admin/super/templates/{templateId}/fields/add` | Add Dynamic Field | SUPER_ADMIN |
| POST | `/api/v1/admin/super/departments/create` | Create Department | SUPER_ADMIN |
| GET | `/api/v1/admin/super/departments` | Get All Departments | SUPER_ADMIN |
| GET | `/api/v1/admin/super/users/analytics` | User Analytics | SUPER_ADMIN |
| POST | `/api/v1/admin/super/users/{userId}/impersonate` | Impersonate User | SUPER_ADMIN |
| GET | `/api/v1/admin/super/config` | Get System Configuration | SUPER_ADMIN |
| PUT | `/api/v1/admin/super/config` | Update System Configuration | SUPER_ADMIN |
| GET | `/api/v1/admin/super/audit/logs` | Get Audit Logs | SUPER_ADMIN |
| GET | `/api/v1/admin/super/monitoring/alerts` | Get System Alerts | SUPER_ADMIN |
| POST | `/api/v1/admin/super/backup/create` | Create System Backup | SUPER_ADMIN |
| POST | `/api/v1/admin/super/maintenance/mode` | Toggle Maintenance Mode | SUPER_ADMIN |

### SuperAdminProviderConfigController
**Base Path:** `/api/v1/admin/super/providers`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| GET | `/api/v1/admin/super/providers/otp` | Get OTP Providers | SUPER_ADMIN |
| POST | `/api/v1/admin/super/providers/otp` | Configure OTP Provider | SUPER_ADMIN |
| PUT | `/api/v1/admin/super/providers/otp/{providerId}` | Update OTP Provider | SUPER_ADMIN |
| PUT | `/api/v1/admin/super/providers/otp/{providerId}/toggle` | Toggle OTP Provider | SUPER_ADMIN |
| POST | `/api/v1/admin/super/providers/otp/{providerId}/test` | Test OTP Provider | SUPER_ADMIN |
| GET | `/api/v1/admin/super/providers/payment` | Get Payment Providers | SUPER_ADMIN |
| POST | `/api/v1/admin/super/providers/payment` | Configure Payment Provider | SUPER_ADMIN |
| PUT | `/api/v1/admin/super/providers/payment/{providerId}` | Update Payment Provider | SUPER_ADMIN |
| PUT | `/api/v1/admin/super/providers/payment/{providerId}/toggle` | Toggle Payment Provider | SUPER_ADMIN |
| POST | `/api/v1/admin/super/providers/payment/{providerId}/test` | Test Payment Provider | SUPER_ADMIN |
| GET | `/api/v1/admin/super/providers/analytics/otp` | OTP Provider Analytics | SUPER_ADMIN |
| GET | `/api/v1/admin/super/providers/analytics/payment` | Payment Provider Analytics | SUPER_ADMIN |
| GET | `/api/v1/admin/super/providers/health/otp` | OTP Provider Health | SUPER_ADMIN |
| GET | `/api/v1/admin/super/providers/health/payment` | Payment Provider Health | SUPER_ADMIN |
| POST | `/api/v1/admin/super/providers/otp/bulk-toggle` | Bulk Toggle OTP Providers | SUPER_ADMIN |
| POST | `/api/v1/admin/super/providers/payment/bulk-toggle` | Bulk Toggle Payment Providers | SUPER_ADMIN |
| POST | `/api/v1/admin/super/providers/backup` | Backup Provider Configurations | SUPER_ADMIN |
| POST | `/api/v1/admin/super/providers/restore` | Restore Provider Configurations | SUPER_ADMIN |

---

## 3. AGENT MODULE

### UnifiedAgentController
**Base Path:** `/api/agent`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| POST | `/api/agent/login` | Agent Login | Public |
| GET | `/api/agent/features` | Get Agent Features | AGENT |
| POST | `/api/agent/citizen/lookup` | Citizen Lookup | AGENT |
| POST | `/api/agent/otp/generate` | Generate OTP | AGENT |
| POST | `/api/agent/otp/verify` | Verify OTP | AGENT |
| POST | `/api/agent/biometric/enroll` | Enroll Fingerprint | AGENT |
| POST | `/api/agent/biometric/verify` | Verify Fingerprint | AGENT |
| GET | `/api/agent/biometric/status/{userId}` | Get Biometric Status | AGENT |
| POST | `/api/agent/consent/request` | Request Consent | AGENT |
| GET | `/api/agent/consent/status/{consentId}` | Check Consent Status | AGENT |
| GET | `/api/agent/services/templates` | Get Service Templates | AGENT |
| GET | `/api/agent/services/citizen/{userId}` | Get Citizen Services | AGENT |
| POST | `/api/agent/services/add` | Add Service | AGENT |
| POST | `/api/agent/telco/sim-register` | Telco SIM Registration | AGENT (TELCO) |
| POST | `/api/agent/immigration/create-profile` | Immigration Profile Creation | AGENT (IMMIGRATION) |
| POST | `/api/agent/police/traffic-stop` | Police Traffic Stop | AGENT (POLICE) |
| POST | `/api/agent/access-code/verify` | Verify Access Code | Public |

---

## 4. AUDIT MODULE

### AuditController
**Base Path:** `/audit`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| POST | `/audit/log` | Log audit event | SUPER_ADMIN, DEPARTMENT_ADMIN, SYSTEM |
| GET | `/audit/logs` | Get audit logs | SUPER_ADMIN, DEPARTMENT_ADMIN |
| GET | `/audit/user-activity` | Get user activity | SUPER_ADMIN, DEPARTMENT_ADMIN |
| GET | `/audit/system-events` | Get system events | SUPER_ADMIN |
| GET | `/audit/security-logs` | Get security logs | SUPER_ADMIN |
| POST | `/audit/security-alert` | Log security alert | SUPER_ADMIN, SYSTEM |
| GET | `/audit/compliance-report` | Get compliance report | SUPER_ADMIN |

---

## 5. AUTHENTICATION MODULE

### AccessCodeController
**Base Path:** `/api/access-code`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| POST | `/api/access-code/generate` | Generate access code | PARTNER_AGENT, ADMIN |
| POST | `/api/access-code/verify` | Verify access code | PARTNER_AGENT, ADMIN |

### AuthController
**Base Path:** `/api/auth`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| POST | `/api/auth/login` | User login | Public |
| POST | `/api/auth/logout` | User logout | Authenticated |
| POST | `/api/auth/refresh-token` | Refresh JWT token | Public |
| GET | `/api/auth/verify-token` | Verify JWT token | Public |
| POST | `/api/auth/register` | User registration | Public |
| POST | `/api/auth/forgot-password` | Forgot password | Public |
| POST | `/api/auth/reset-password` | Reset password | Public |
| POST | `/api/auth/verify-phone` | Verify phone number | Public |
| POST | `/api/auth/verify-email` | Verify email | Public |
| GET | `/api/auth/me` | Get current user | Authenticated |
| PUT | `/api/auth/change-password` | Change password | Authenticated |

### OtpController
**Base Path:** `/api/otp`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| POST | `/api/otp/generate` | Generate OTP | PARTNER_AGENT, ADMIN |
| POST | `/api/otp/verify` | Verify OTP | PARTNER_AGENT, ADMIN |

---

## 6. BIOMETRIC MODULE

### BiometricAuditController
**Base Path:** `/api/v1/biometric/audit`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| POST | `/api/v1/biometric/audit/logs` | Get audit logs | ADMIN, AUDITOR |
| GET | `/api/v1/biometric/audit/user/{userId}/statistics` | Get user verification statistics | ADMIN, AUDITOR, Owner |
| GET | `/api/v1/biometric/audit/agent/{agentId}/statistics` | Get agent verification statistics | ADMIN, AUDITOR |

### BiometricController
**Base Path:** `/api/biometric`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| POST | `/api/biometric/enroll` | Enroll fingerprint | PARTNER_AGENT, ADMIN |
| POST | `/api/biometric/verify` | Verify fingerprint | PARTNER_AGENT, ADMIN |
| GET | `/api/biometric/user/{userId}` | Get user fingerprints | PARTNER_AGENT, ADMIN, Owner |
| DELETE | `/api/biometric/{templateId}` | Delete fingerprint | ADMIN |

### BiometricTemplateController
**Base Path:** `/api/v1/biometric/templates`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| POST | `/api/v1/biometric/templates/convert` | Convert template between formats | AGENT, ADMIN |
| POST | `/api/v1/biometric/templates/batch-enroll` | Batch enroll fingerprints | AGENT, ADMIN |
| GET | `/api/v1/biometric/templates/user/{userId}` | Get user templates | AGENT, ADMIN, Owner |
| GET | `/api/v1/biometric/templates/quality-range` | Get templates by quality range | ADMIN |
| GET | `/api/v1/biometric/templates/statistics` | Get enrollment statistics | ADMIN |
| GET | `/api/v1/biometric/templates/enrollment-status/{userId}` | Get enrollment status | AGENT, ADMIN, Owner |
| DELETE | `/api/v1/biometric/templates/user/{userId}` | Delete user templates | ADMIN |

---

## 7. CITIZEN MODULE

### CitizenAddressController
**Base Path:** `/api/citizen/addresses`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| GET | `/api/citizen/addresses/my-addresses` | Get user's addresses | CITIZEN |
| POST | `/api/citizen/addresses/register` | Register new address | CITIZEN |
| GET | `/api/citizen/addresses/tracking-status/{verificationId}` | Get tracking status | CITIZEN |
| POST | `/api/citizen/addresses/submit-location` | Submit location data | CITIZEN |
| GET | `/api/citizen/addresses/verification-certificate/{verificationId}` | Download verification certificate | CITIZEN |
| POST | `/api/citizen/addresses/resume-tracking/{verificationId}` | Resume tracking | CITIZEN |

### CitizenAppController
**Base Path:** `/api/citizen/app`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| POST | `/api/citizen/app/onboarding/complete` | Complete Onboarding | Public |
| GET | `/api/citizen/app/dashboard` | Get Dashboard | CITIZEN |
| GET | `/api/citizen/app/services/nearby` | Get Nearby Services | CITIZEN |
| POST | `/api/citizen/app/reports/incident` | Report Incident | CITIZEN |
| POST | `/api/citizen/app/digital-address/generate` | Generate Digital Address | CITIZEN |
| GET | `/api/citizen/app/qa/search` | Search Q&A | Public |
| POST | `/api/citizen/app/qa/ask` | Ask Question | CITIZEN |
| POST | `/api/citizen/app/emergency-contacts` | Add Emergency Contact | CITIZEN |
| GET | `/api/citizen/app/tracking/activities` | Get Activity Tracking | CITIZEN |

### CitizenDigitalIdController
**Base Path:** `/api/citizen/digital-id`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| GET | `/api/citizen/digital-id/my-licenses` | Get My Digital Licenses | CITIZEN |
| POST | `/api/citizen/digital-id/apply-license` | Apply for License | CITIZEN |
| GET | `/api/citizen/digital-id/license/{licenseId}/qr` | Get License QR Code | CITIZEN |
| GET | `/api/citizen/digital-id/applications/status` | Check Application Status | CITIZEN |
| POST | `/api/citizen/digital-id/vehicle/register` | Register Vehicle | CITIZEN |

### CitizenEmergencyController
**Base Path:** `/api/citizen/emergency`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| POST | `/api/citizen/emergency/report` | Report Emergency | CITIZEN |
| GET | `/api/citizen/emergency/my-reports` | Get My Emergency Reports | CITIZEN |
| GET | `/api/citizen/emergency/track/{incidentId}` | Track Emergency Response | CITIZEN |
| POST | `/api/citizen/emergency/cancel/{incidentId}` | Cancel Emergency | CITIZEN |

---

## 8. CONSENT MODULE

### ConsentController
**Base Path:** `/api/consent`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| POST | `/api/consent/request` | Request consent | PARTNER_AGENT, ADMIN |
| POST | `/api/consent/update` | Update consent | Public |
| GET | `/api/consent/check` | Check consent | PARTNER_AGENT, ADMIN |

---

## 9. DOCUMENT MODULE

### DocumentController
**Base Path:** `/documents`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| POST | `/documents/upload` | Upload document | SUPER_ADMIN, DEPARTMENT_ADMIN, VERIFICATION_AGENT, CITIZEN |
| GET | `/documents/{id}` | Get document | SUPER_ADMIN, DEPARTMENT_ADMIN, VERIFICATION_AGENT, Owner |
| DELETE | `/documents/{id}` | Delete document | SUPER_ADMIN, DEPARTMENT_ADMIN, Owner |
| GET | `/documents/user/{userId}` | Get user documents | SUPER_ADMIN, DEPARTMENT_ADMIN, VERIFICATION_AGENT, Owner |
| POST | `/documents/verify` | Verify document | SUPER_ADMIN, VERIFICATION_AGENT |
| GET | `/documents/{id}/download` | Download document | SUPER_ADMIN, DEPARTMENT_ADMIN, VERIFICATION_AGENT, Owner |
| POST | `/documents/bulk-upload` | Bulk upload documents | SUPER_ADMIN, DEPARTMENT_ADMIN |
| GET | `/documents/types` | Get document types | Public |

---

## 10. EMERGENCY MODULE

### CompleteEmergencyController
**Base Path:** `/api/emergency`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| POST | `/api/emergency/report` | Report Emergency | Public |
| GET | `/api/emergency/track/{incidentId}` | Track Emergency | Public |
| POST | `/api/emergency/sos` | SOS Emergency | Public |
| POST | `/api/emergency/wellness-check/setup` | Setup Wellness Check | CITIZEN |
| POST | `/api/emergency/wellness-check/respond` | Respond to Wellness Check | CITIZEN |
| GET | `/api/emergency/contacts/nearby` | Get Nearby Emergency Contacts | Public |
| POST | `/api/emergency/volunteer/register` | Register as Volunteer | CITIZEN |
| GET | `/api/emergency/volunteer/opportunities` | Get Volunteer Opportunities | CITIZEN |

---

## 11. GOVERNMENT MODULE

### ClientController
**Base Path:** `/api/clients`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| POST | `/api/clients/register` | Register client | Public |
| GET | `/api/clients/{id}` | Get client | ADMIN |
| PUT | `/api/clients/{id}` | Update client | ADMIN |
| DELETE | `/api/clients/{id}` | Delete client | ADMIN |
| GET | `/api/clients` | Get all clients | ADMIN |

### CompleteGovernmentController
**Base Path:** `/api/government`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| GET | `/api/government/services` | Get Government Services | Public |
| POST | `/api/government/services/apply` | Apply for Service | CITIZEN |
| GET | `/api/government/services/track/{applicationId}` | Track Application | CITIZEN |
| GET | `/api/government/departments` | Get Departments | Public |
| GET | `/api/government/forms/{serviceType}` | Get Service Forms | Public |
| POST | `/api/government/documents/upload` | Upload Documents | CITIZEN |
| GET | `/api/government/fees/{serviceType}` | Get Service Fees | Public |
| POST | `/api/government/payment/process` | Process Payment | CITIZEN |

### CreditScoreController
**Base Path:** `/api/credit-score`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| GET | `/api/credit-score/{userId}` | Get credit score | CITIZEN, ADMIN |
| POST | `/api/credit-score/calculate` | Calculate credit score | ADMIN |
| PUT | `/api/credit-score/{userId}` | Update credit score | ADMIN |
| GET | `/api/credit-score/history/{userId}` | Get credit history | CITIZEN, ADMIN |

### DigitalAddressController
**Base Path:** `/api/digital-address`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| POST | `/api/digital-address/generate` | Generate digital address | CITIZEN |
| GET | `/api/digital-address/{addressCode}` | Get address by code | Public |
| PUT | `/api/digital-address/{id}` | Update address | CITIZEN |
| DELETE | `/api/digital-address/{id}` | Delete address | CITIZEN |
| GET | `/api/digital-address/search` | Search addresses | Public |
| POST | `/api/digital-address/verify` | Verify address | ADMIN |

### KycController
**Base Path:** `/api/kyc`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| POST | `/api/kyc/submit` | Submit KYC | CITIZEN |
| GET | `/api/kyc/{userId}` | Get KYC status | CITIZEN, ADMIN |
| PUT | `/api/kyc/{id}/verify` | Verify KYC | ADMIN |
| GET | `/api/kyc/pending` | Get pending KYC | ADMIN |

### LicenseController
**Base Path:** `/api/licenses`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| GET | `/api/licenses/types` | Get all license types | Public |
| GET | `/api/licenses/requirements/{licenseType}` | Get license requirements | Public |
| POST | `/api/licenses/apply` | Apply for license | CITIZEN |
| POST | `/api/licenses/{applicationId}/documents` | Upload documents | CITIZEN |
| POST | `/api/licenses/{applicationId}/payment` | Process payment | CITIZEN |
| GET | `/api/licenses/application/{applicationId}/status` | Get application status | CITIZEN |
| POST | `/api/licenses/{applicationId}/schedule-written-test` | Schedule written test | ADMIN |
| POST | `/api/licenses/{applicationId}/test-results` | Submit test results | ADMIN |
| POST | `/api/licenses/{applicationId}/schedule-practical-test` | Schedule practical test | ADMIN |
| POST | `/api/licenses/{applicationId}/issue` | Issue license | ADMIN |

### SimRegistrationController
**Base Path:** `/api/sim-registration`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| POST | `/api/sim-registration/register` | Register SIM | AGENT |
| GET | `/api/sim-registration/{simNumber}` | Get SIM details | AGENT, ADMIN |
| PUT | `/api/sim-registration/{id}/status` | Update SIM status | ADMIN |
| GET | `/api/sim-registration/user/{userId}` | Get user SIMs | CITIZEN, ADMIN |

### VehicleController
**Base Path:** `/api/vehicles`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| POST | `/api/vehicles/register` | Register vehicle | CITIZEN |
| GET | `/api/vehicles/{vehicleId}` | Get vehicle details | CITIZEN, ADMIN |
| PUT | `/api/vehicles/{vehicleId}` | Update vehicle | CITIZEN |
| GET | `/api/vehicles/user/{userId}` | Get user vehicles | CITIZEN, ADMIN |
| POST | `/api/vehicles/{vehicleId}/transfer` | Transfer ownership | CITIZEN |

---

## 12. NOTIFICATION MODULE

### NotificationController
**Base Path:** `/api/notifications`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| POST | `/api/notifications/send` | Send notification | ADMIN |
| GET | `/api/notifications/user/{userId}` | Get user notifications | CITIZEN, ADMIN |
| PUT | `/api/notifications/{id}/read` | Mark as read | CITIZEN |
| DELETE | `/api/notifications/{id}` | Delete notification | CITIZEN |
| GET | `/api/notifications/templates` | Get templates | ADMIN |
| POST | `/api/notifications/templates` | Create template | ADMIN |

---

## 13. PATROL MODULE

### CompletePatrolController
**Base Path:** `/api/patrol`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| POST | `/api/patrol/officers/create` | Create Officer | ADMIN |
| GET | `/api/patrol/officers` | Get All Officers | ADMIN |
| PUT | `/api/patrol/officers/{id}` | Update Officer | ADMIN |
| DELETE | `/api/patrol/officers/{id}` | Delete Officer | ADMIN |
| POST | `/api/patrol/teams/create` | Create Team | ADMIN |
| GET | `/api/patrol/teams` | Get All Teams | ADMIN |
| POST | `/api/patrol/teams/{teamId}/members` | Add Team Member | ADMIN |
| POST | `/api/patrol/routes/create` | Create Route | ADMIN |
| GET | `/api/patrol/routes` | Get All Routes | ADMIN |
| POST | `/api/patrol/schedules/create` | Create Schedule | ADMIN |
| GET | `/api/patrol/schedules` | Get All Schedules | ADMIN |
| POST | `/api/patrol/equipment/request` | Request Equipment | OFFICER |
| GET | `/api/patrol/equipment/requests` | Get Equipment Requests | ADMIN |
| POST | `/api/patrol/training/create` | Create Training | ADMIN |
| GET | `/api/patrol/training/overview` | Get Training Overview | ADMIN |

### PatrolResponseController
**Base Path:** `/api/patrol/response`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| POST | `/api/patrol/response/check-in` | Officer Check-in | OFFICER |
| GET | `/api/patrol/response/nearby-incidents` | Get Nearby Incidents | OFFICER |
| POST | `/api/patrol/response/accept-incident/{incidentId}` | Accept Incident | OFFICER |
| POST | `/api/patrol/response/update-location` | Update Location | OFFICER |
| POST | `/api/patrol/response/traffic-stop` | Record Traffic Stop | OFFICER |
| POST | `/api/patrol/response/verify-license` | Verify License | OFFICER |
| POST | `/api/patrol/response/complete-incident/{incidentId}` | Complete Incident | OFFICER |

---

## 14. PAYMENT MODULE

### BillingController
**Base Path:** `/api/billing`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| POST | `/api/billing/create-invoice` | Create invoice | ADMIN |
| GET | `/api/billing/invoices/{userId}` | Get user invoices | CITIZEN, ADMIN |
| POST | `/api/billing/pay/{invoiceId}` | Pay invoice | CITIZEN |
| GET | `/api/billing/payment-history/{userId}` | Get payment history | CITIZEN, ADMIN |

### PaymentController
**Base Path:** `/api/payments`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| POST | `/api/payments/process` | Process payment | CITIZEN |
| GET | `/api/payments/{transactionId}` | Get payment details | CITIZEN, ADMIN |
| POST | `/api/payments/refund` | Process refund | ADMIN |
| GET | `/api/payments/user/{userId}` | Get user payments | CITIZEN, ADMIN |

---

## 15. POLICE MODULE

### PoliceAdminController
**Base Path:** `/api/police/admin`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| GET | `/api/police/admin/dashboard` | Get Police Dashboard | POLICE_ADMIN |
| GET | `/api/police/admin/officers` | Get All Officers | POLICE_ADMIN |
| POST | `/api/police/admin/officers` | Create Officer | POLICE_ADMIN |
| PUT | `/api/police/admin/officers/{id}` | Update Officer | POLICE_ADMIN |
| GET | `/api/police/admin/incidents` | Get All Incidents | POLICE_ADMIN |
| GET | `/api/police/admin/reports` | Get Reports | POLICE_ADMIN |

### PolicePatrolController
**Base Path:** `/api/police/patrol`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| POST | `/api/police/patrol/start` | Start Patrol | POLICE_OFFICER |
| POST | `/api/police/patrol/end` | End Patrol | POLICE_OFFICER |
| POST | `/api/police/patrol/checkpoint` | Record Checkpoint | POLICE_OFFICER |
| GET | `/api/police/patrol/active` | Get Active Patrols | POLICE_ADMIN |

### PoliceSearchController
**Base Path:** `/api/police/search`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| POST | `/api/police/search/person` | Search Person | POLICE_OFFICER |
| POST | `/api/police/search/vehicle` | Search Vehicle | POLICE_OFFICER |
| POST | `/api/police/search/license` | Search License | POLICE_OFFICER |
| GET | `/api/police/search/history` | Get Search History | POLICE_ADMIN |

### PoliceVehicleManagementController
**Base Path:** `/api/police/vehicles`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| GET | `/api/police/vehicles` | Get Police Vehicles | POLICE_ADMIN |
| POST | `/api/police/vehicles` | Add Vehicle | POLICE_ADMIN |
| PUT | `/api/police/vehicles/{id}` | Update Vehicle | POLICE_ADMIN |
| POST | `/api/police/vehicles/{id}/assign` | Assign Vehicle | POLICE_ADMIN |

---

## 16. REPORTING MODULE

### AnalyticsController
**Base Path:** `/api/analytics`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| GET | `/api/analytics/dashboard` | Get Analytics Dashboard | ADMIN |
| GET | `/api/analytics/users` | Get User Analytics | ADMIN |
| GET | `/api/analytics/services` | Get Service Analytics | ADMIN |
| GET | `/api/analytics/performance` | Get Performance Metrics | ADMIN |

### DashboardController
**Base Path:** `/api/dashboard`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| GET | `/api/dashboard/overview` | Get Dashboard Overview | ADMIN |
| GET | `/api/dashboard/metrics` | Get Key Metrics | ADMIN |
| GET | `/api/dashboard/alerts` | Get System Alerts | ADMIN |

### ReportsController
**Base Path:** `/api/reports`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| GET | `/api/reports/generate/{reportType}` | Generate Report | ADMIN |
| GET | `/api/reports/download/{reportId}` | Download Report | ADMIN |
| GET | `/api/reports/scheduled` | Get Scheduled Reports | ADMIN |
| POST | `/api/reports/schedule` | Schedule Report | ADMIN |

---

## 17. SERVICE MODULE

### DynamicServiceController
**Base Path:** `/api/services/dynamic`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| POST | `/api/services/dynamic/create` | Create Dynamic Service | ADMIN |
| GET | `/api/services/dynamic/{serviceId}` | Get Service | Public |
| PUT | `/api/services/dynamic/{serviceId}` | Update Service | ADMIN |
| DELETE | `/api/services/dynamic/{serviceId}` | Delete Service | ADMIN |
| GET | `/api/services/dynamic` | Get All Services | Public |

### ServiceTemplateController
**Base Path:** `/api/service-templates`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| POST | `/api/service-templates` | Create Template | ADMIN |
| GET | `/api/service-templates` | Get All Templates | ADMIN |
| GET | `/api/service-templates/{id}` | Get Template | ADMIN |
| PUT | `/api/service-templates/{id}` | Update Template | ADMIN |
| DELETE | `/api/service-templates/{id}` | Delete Template | ADMIN |

---

## 18. SUPPORT MODULE

### SupportController
**Base Path:** `/api/support`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| POST | `/api/support/tickets` | Create Support Ticket | CITIZEN |
| GET | `/api/support/tickets/{userId}` | Get User Tickets | CITIZEN |
| PUT | `/api/support/tickets/{id}` | Update Ticket | SUPPORT_AGENT |
| GET | `/api/support/tickets` | Get All Tickets | SUPPORT_AGENT |

---

## 19. SYSTEM MODULE

### MonitoringController
**Base Path:** `/api/monitoring`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| GET | `/api/monitoring/health` | System Health Check | ADMIN |
| GET | `/api/monitoring/metrics` | Get System Metrics | ADMIN |
| GET | `/api/monitoring/logs` | Get System Logs | ADMIN |
| POST | `/api/monitoring/alerts` | Create Alert | ADMIN |

### SystemController
**Base Path:** `/api/system`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| PUT | `/api/system/config` | Update system configuration | SUPER_ADMIN |
| GET | `/api/system/health` | Get system health | SUPER_ADMIN |
| POST | `/api/system/backup` | Create system backup | SUPER_ADMIN |
| GET | `/api/system/status` | Get system status | SUPER_ADMIN |
| POST | `/api/system/maintenance` | Toggle maintenance mode | SUPER_ADMIN |

---

## 20. USER MODULE

### UserController
**Base Path:** `/api/users`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| POST | `/api/users` | Create user | ADMIN |
| GET | `/api/users/{id}` | Get user | ADMIN, Owner |
| PUT | `/api/users/{id}` | Update user | ADMIN, Owner |
| DELETE | `/api/users/{id}` | Delete user | ADMIN |
| GET | `/api/users` | Get all users | ADMIN |
| GET | `/api/users/profile` | Get user profile | Authenticated |
| PUT | `/api/users/profile` | Update user profile | Authenticated |

---

## 21. VEHICLE MODULE

### VehicleRegistrationController
**Base Path:** `/api/vehicle-registration`

| Method | Endpoint | Description | Security |
|--------|----------|-------------|----------|
| POST | `/api/vehicle-registration/register` | Register Vehicle | CITIZEN |
| GET | `/api/vehicle-registration/{registrationId}` | Get Registration Details | CITIZEN, ADMIN |
| PUT | `/api/vehicle-registration/{registrationId}` | Update Registration | CITIZEN |
| POST | `/api/vehicle-registration/{registrationId}/payment` | Process Payment | CITIZEN |
| GET | `/api/vehicle-registration/summary/{userId}` | Get Registration Summary | CITIZEN, ADMIN |
| POST | `/api/vehicle-registration/{registrationId}/drivers` | Add Driver | CITIZEN |

---

## Security Roles Reference

- **Public**: No authentication required
- **Authenticated**: Any authenticated user
- **CITIZEN**: Regular citizen users
- **AGENT**: Partner agents (Bank, Telco, Police, etc.)
- **ADMIN**: System administrators
- **SUPER_ADMIN**: Super administrators
- **DEPARTMENT_ADMIN**: Department administrators
- **VERIFICATION_AGENT**: Address verification agents
- **PARTNER_AGENT**: Partner organization agents
- **POLICE_OFFICER**: Police officers
- **POLICE_ADMIN**: Police administrators
- **SUPPORT_AGENT**: Customer support agents
- **AUDITOR**: System auditors
- **SYSTEM**: System-level operations
- **Owner**: Resource owner (user can access their own data)

---

## Notes

1. All endpoints return responses in the `ApiResponse<T>` format
2. Authentication is handled via JWT tokens
3. Role-based access control is implemented using Spring Security
4. Some endpoints have additional business logic validation
5. File uploads use multipart/form-data
6. Date parameters typically use ISO 8601 format
7. Pagination is supported where applicable using Spring Data Pageable

---

**Total Endpoints**: 200+ endpoints across 21 modules

This documentation provides a complete overview of all API endpoints in the E-Government Unified Backend System.
