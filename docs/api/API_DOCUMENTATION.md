# E-Government Unified Backend API Documentation

## Overview

This comprehensive API documentation covers all 247+ endpoints across 15 core modules for the unified e-Government platform. The API provides a complete solution for government services, including authentication, police services, banking, immigration, court services, and more.

## Authentication

All API endpoints require JWT authentication except for login, registration, and public endpoints. Include the JWT token in the Authorization header:

\`\`\`
Authorization: Bearer <token>
\`\`\`

## Multi-tenant Support

For department-specific endpoints, include the tenant ID in the X-Tenant-ID header:

\`\`\`
X-Tenant-ID: <tenant-id>
\`\`\`

## Core Modules

### 1. Authentication & User Management (18 endpoints)

#### Features:
- JWT Authentication with token refresh
- Multi-role user registration
- OTP verification via SMS/Email
- Access code generation and validation
- Password management (reset, change, forgot)

#### Key Endpoints:
- `POST /auth/login` - User authentication
- `POST /auth/register` - User registration
- `POST /auth/refresh-token` - Token refresh
- `POST /otp/send` - Send OTP
- `POST /otp/verify` - Verify OTP
- `GET /users` - List users (admin)

### 2. Police Department Services (45 endpoints)

#### Features:
- Emergency response and incident management
- Vehicle registration with up to 3 drivers
- 5-year digital licenses with QR codes
- Patrol team coordination and route planning
- Traffic violation tracking and fine processing

#### Key Endpoints:
- `POST /police/emergency/incidents` - Report incident
- `POST /police/vehicles` - Register vehicle
- `POST /police/licenses` - Issue digital license
- `POST /police/patrol/teams` - Create patrol team
- `POST /police/traffic/violations` - Record violation

### 3. Banking & Financial Services (32 endpoints)

#### Features:
- KYC verification with document validation
- Credit assessment and scoring
- Loan application processing and disbursement
- Payment processing with Stripe integration

#### Key Endpoints:
- `POST /banking/kyc/applications` - Submit KYC application
- `POST /banking/credit/assessment` - Request credit assessment
- `POST /banking/loans/applications` - Submit loan application
- `POST /banking/payments/process` - Process payment

### 4. Immigration Services (28 endpoints)

#### Features:
- Visa application processing
- Document verification
- FGTIT biometric enrollment and verification
- Border control and entry/exit tracking

#### Key Endpoints:
- `POST /immigration/visas/applications` - Submit visa application
- `POST /immigration/documents/verify` - Verify document
- `POST /immigration/biometric/enroll` - Enroll biometric
- `POST /immigration/border/entry` - Record entry

### 5. Court & Legal Services (24 endpoints)

#### Features:
- Court case tracking and scheduling
- Traffic fine processing
- Criminal record management
- Legal document management

#### Key Endpoints:
- `POST /court/cases` - Create case
- `POST /court/fines` - Create fine
- `POST /court/criminal-records/check` - Background check
- `POST /court/criminal-records/certificates` - Issue certificate

### 6. Government Services (35 endpoints)

#### Features:
- Business license issuance
- GPS-based digital addressing
- Citizen service applications
- Document issuance and validation
- SIM card registration

#### Key Endpoints:
- `POST /government/licenses/applications` - Submit license application
- `POST /government/addresses/applications` - Apply for digital address
- `POST /government/services/applications` - Submit service application
- `POST /government/documents/certificates` - Issue certificate
- `POST /government/sim/register` - Register SIM card

### 7. Healthcare Services (22 endpoints)

#### Features:
- Patient registration and management
- Medical record management
- Ambulance dispatch and medical emergency response

#### Key Endpoints:
- `POST /healthcare/patients` - Register patient
- `POST /healthcare/records` - Create medical record
- `POST /healthcare/emergency/ambulance` - Request ambulance

### 8. Biometric Integration (18 endpoints)

#### Features:
- FGTIT fingerprint enrollment
- Biometric verification and matching
- Template management and conversion
- Audit logging and statistics

#### Key Endpoints:
- `POST /biometric/enroll` - Enroll biometric
- `POST /biometric/verify` - Verify biometric
- `POST /biometric/templates/convert` - Convert template format
- `GET /biometric/statistics/enrollment` - Enrollment statistics

### 9. Agent Management (12 endpoints)

#### Features:
- Unified agent portal for multi-service access
- Cross-department citizen lookup
- Agent-assisted service processing

#### Key Endpoints:
- `POST /agent/login` - Agent login
- `POST /agent/citizens/lookup` - Search citizens
- `POST /agent/services/process` - Process service

### 10. Emergency Management (25 endpoints)

#### Features:
- Multi-agency emergency dispatch
- SOS services with panic button
- Volunteer coordination
- Citizen wellness checks

#### Key Endpoints:
- `POST /emergency/dispatch/fire` - Dispatch fire emergency
- `POST /emergency/sos` - Trigger SOS
- `POST /emergency/volunteers/register` - Register volunteer
- `POST /emergency/wellness/schedule` - Schedule wellness check

### 11. Address Verification (16 endpoints)

#### Features:
- Digital address application processing
- GPS-based field verification
- Location tracking for verification officers
- Application progress monitoring

#### Key Endpoints:
- `POST /address/applications` - Submit address application
- `POST /address/verification/{id}/start` - Start verification
- `POST /address/tracking/start` - Start location tracking
- `GET /address/progress/dashboard` - Get progress dashboard

### 12. Admin & System Management (28 endpoints)

#### Features:
- Dynamic service template management
- Multi-tenant department administration
- System analytics and reporting
- User management and role assignment

#### Key Endpoints:
- `POST /admin/templates` - Create service template
- `POST /admin/departments` - Create department
- `GET /admin/analytics/dashboard` - Get admin dashboard
- `POST /admin/users/{id}/roles` - Assign roles

### 13. Notification Services (8 endpoints)

#### Features:
- WebSocket-based real-time notifications
- Multi-channel delivery (SMS, email, push)
- Notification status tracking

#### Key Endpoints:
- `POST /notifications/send` - Send notification
- `GET /notifications` - List notifications
- `GET /notifications/delivery-status/{id}` - Get delivery status

### 14. Audit & Compliance (12 endpoints)

#### Features:
- System audit logging
- Security monitoring
- Regulatory compliance reporting

#### Key Endpoints:
- `GET /audit/logs` - Get audit logs
- `GET /audit/security/access-logs` - Get access logs
- `POST /audit/compliance/generate` - Generate compliance report

### 15. System & Monitoring (8 endpoints)

#### Features:
- System health monitoring
- Configuration management
- Feature flag management

#### Key Endpoints:
- `GET /system/health` - System health check
- `GET /system/metrics` - System metrics
- `PUT /system/features/{feature}` - Toggle feature flag

## External Integrations

The API integrates with several external services:

1. **Google Maps API** - Address verification, location services
2. **Stripe Payment Gateway** - Payment processing
3. **Twilio SMS Service** - SMS notifications, OTP delivery
4. **SendGrid Email Service** - Email notifications
5. **Firebase Cloud Messaging** - Push notifications
6. **FGTIT Biometric API** - Fingerprint enrollment and verification
7. **Redis Cache** - Session management, caching
8. **PostgreSQL Database** - Primary data storage

## Error Handling

All API endpoints return standardized error responses with appropriate HTTP status codes:

- `400 Bad Request` - Invalid input parameters
- `401 Unauthorized` - Authentication required
- `403 Forbidden` - Insufficient permissions
- `404 Not Found` - Resource not found
- `422 Unprocessable Entity` - Validation error
- `500 Internal Server Error` - Server error

Error response format:
\`\`\`json
{
  "success": false,
  "message": "Error message",
  "errors": [
    {
      "field": "Field name",
      "message": "Error description"
    }
  ],
  "timestamp": "2023-06-05T07:36:35.123Z",
  "path": "/api/endpoint"
}
\`\`\`

## Rate Limiting

API endpoints are rate-limited to prevent abuse. Check the following response headers:

- `X-RateLimit-Limit` - Requests allowed per time window
- `X-RateLimit-Remaining` - Requests remaining in current window
- `X-RateLimit-Reset` - Time when the rate limit resets

## Pagination

List endpoints support pagination with the following query parameters:

- `page` - Page number (zero-based, default: 0)
- `size` - Page size (default: 20)
- `sort` - Sort field and direction (e.g., `name,asc`)

Paginated response format:
\`\`\`json
{
  "content": [
    // Array of items
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 20,
    "sort": {
      "sorted": true,
      "unsorted": false
    }
  },
  "totalElements": 100,
  "totalPages": 5,
  "last": false,
  "first": true,
  "empty": false
}
\`\`\`

## Available Roles

The system supports the following user roles:

- `SUPER_ADMIN` - System-wide access
- `POLICE_ADMIN`, `BANK_ADMIN`, `IMMIGRATION_ADMIN` - Department admins
- `POLICE_OFFICER`, `BANK_OFFICER`, `IMMIGRATION_OFFICER` - Operational staff
- `AGENT` - Multi-service agent access
- `CITIZEN` - Limited citizen-facing services

## API Versioning

The API uses URL versioning:

- Production: `https://api.emergency.gov/v2`
- Staging: `https://staging-api.emergency.gov/v2`
- Development: `http://localhost:8080/api`

## WebSocket Support

Real-time features use WebSocket connections:

- Connect to: `wss://api.emergency.gov/v2/ws`
- Authentication: Include JWT token in connection request
- Supported topics:
  - `/topic/notifications` - User notifications
  - `/topic/emergency` - Emergency alerts
  - `/topic/location` - Location updates

## API Testing

A complete Postman collection is available for testing all API endpoints:

- Collection: `docs/api/egov-postman-collection.json`
- Environment variables:
  - `baseUrl` - API base URL
  - `token` - JWT authentication token

## Support

For API support and issues:

- Email: api-support@government.gov
- Support portal: https://api.emergency.gov/support
- Documentation: https://api.emergency.gov/docs
\`\`\`

Let me now create a comprehensive biometric flow analysis document:
