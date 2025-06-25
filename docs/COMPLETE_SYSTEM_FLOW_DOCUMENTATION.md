# 🏛️ Complete E-Government System Flow Documentation

## 📋 Table of Contents
1. [System Overview](#system-overview)
2. [Super Admin Complete Flow](#super-admin-complete-flow)
3. [Company/Department Admin Complete Flow](#companydepartment-admin-complete-flow)
4. [Patrol Team Complete Flow](#patrol-team-complete-flow)
5. [Agent Complete Flow](#agent-complete-flow)
6. [Citizen App Complete Flow](#citizen-app-complete-flow)
7. [Real-World Scenarios](#real-world-scenarios)

---

## System Overview

The E-Government System is a comprehensive multi-tenant platform that serves different user types through specialized interfaces:

- **Super Admin Portal**: `https://kzmkx0wxseuvu4lr359m.lite.vusercontent.net/login`
- **Emergency Dispatch Center**: `https://8juyhk3ix3hhzzxoo.lite.vusercontent.net/`
- **KYC/Government Services**: `https://kzmixiaaf443clitsc4x.lite.vusercontent.net/login`
- **Patrol Team Mobile App**: `https://gg10g1urv5roo2ucw.lite.vusercontent.net/`
- **Citizen Mobile App**: `https://kzmpxmtnzru8u4h3du9h.lite.vusercontent.net/`

---

## 🔥 Super Admin Complete Flow

### **Phase 1: System Initialization & Login**

#### Step 1: Initial System Access
\`\`\`
URL: https://kzmkx0wxseuvu4lr359m.lite.vusercontent.net/login
Endpoint: POST /api/auth/login
\`\`\`

**Super Admin Login Process:**
1. **Navigate to Super Admin Portal**
   - Opens secure login page with government branding
   - Two-factor authentication required
   - Session timeout: 30 minutes for security

2. **Authentication Flow**
   \`\`\`json
   POST /api/auth/login
   {
     "username": "super.admin@gov.system",
     "password": "SecurePassword123!",
     "mfaCode": "123456"
   }
   \`\`\`

3. **Dashboard Access**
   \`\`\`json
   GET /api/admin/super/system/stats
   Response: {
     "totalUsers": 50000,
     "totalDepartments": 15,
     "activeIncidents": 23,
     "systemHealth": "EXCELLENT",
     "dailyTransactions": 1250
   }
   \`\`\`

#### Step 2: System Health Monitoring
**Real-time Dashboard Features:**
- **Live System Metrics**: CPU, Memory, Database connections
- **Geographic Heat Map**: Incident distribution across regions
- **Department Performance**: Response times, resolution rates
- **User Activity**: Login patterns, peak usage times

### **Phase 2: Department Creation & Management**

#### Step 3: Creating Government Departments
\`\`\`
Endpoint: POST /api/admin/super/departments/create
\`\`\`

**Creating Police Department Example:**
1. **Department Setup Form**
   \`\`\`json
   {
     "name": "National Police Service",
     "type": "LAW_ENFORCEMENT",
     "region": "NATIONAL",
     "headquarters": {
       "address": "123 Police Headquarters Ave",
       "latitude": 5.6037,
       "longitude": -0.1870
     },
     "operatingHours": "24/7",
     "emergencyNumber": "911",
     "maxOfficers": 5000
   }
   \`\`\`

2. **System Response**
   \`\`\`json
   {
     "departmentId": "DEPT_001",
     "tenantId": "police_national",
     "status": "CREATED",
     "adminCredentials": {
       "username": "police.admin@gov.gh",
       "temporaryPassword": "TempPass123!",
       "mustChangePassword": true
     }
   }
   \`\`\`

#### Step 4: Creating Emergency Services Department
\`\`\`json
POST /api/admin/super/departments/create
{
  "name": "Emergency Response Center",
  "type": "EMERGENCY_SERVICES",
  "region": "NATIONAL",
  "capabilities": ["FIRE", "MEDICAL", "RESCUE", "DISASTER"],
  "responseRadius": 50,
  "averageResponseTime": 8
}
\`\`\`

#### Step 5: Creating KYC/Government Services Department
\`\`\`json
POST /api/admin/super/departments/create
{
  "name": "Digital Identity & Services",
  "type": "GOVERNMENT_SERVICES",
  "services": ["KYC", "LICENSES", "PERMITS", "CERTIFICATES"],
  "processingCapacity": 1000,
  "verificationLevels": ["BASIC", "ENHANCED", "PREMIUM"]
}
\`\`\`

### **Phase 3: Service Template Management**

#### Step 6: Creating Dynamic Service Templates
\`\`\`
Endpoint: POST /api/admin/super/templates/create
\`\`\`

**Driver's License Template:**
\`\`\`json
{
  "name": "Driver's License Application",
  "industry": "Transportation",
  "description": "Digital driver's license with QR verification",
  "dynamicFields": [
    {
      "fieldName": "license_class",
      "fieldType": "SELECT",
      "fieldLabel": "License Class",
      "required": true,
      "options": ["Class A", "Class B", "Class C", "Motorcycle"],
      "displayOrder": 1
    },
    {
      "fieldName": "medical_certificate",
      "fieldType": "FILE",
      "fieldLabel": "Medical Certificate",
      "required": true,
      "validationRules": {
        "fileTypes": ["PDF", "JPG"],
        "maxSize": "5MB"
      },
      "displayOrder": 2
    },
    {
      "fieldName": "driving_test_score",
      "fieldType": "NUMBER",
      "fieldLabel": "Driving Test Score",
      "required": true,
      "validationRules": {
        "min": 70,
        "max": 100
      },
      "displayOrder": 3
    }
  ]
}
\`\`\`

#### Step 7: Creating Agent Access Templates
\`\`\`json
POST /api/admin/super/templates/create
{
  "name": "Bank Account Verification",
  "industry": "Banking",
  "agentTypes": ["BANK", "MICROFINANCE"],
  "biometricRequired": true,
  "consentTypes": ["FINANCIAL_HISTORY", "CREDIT_SCORE"],
  "dynamicFields": [
    {
      "fieldName": "account_type",
      "fieldType": "SELECT",
      "options": ["SAVINGS", "CURRENT", "FIXED_DEPOSIT"]
    },
    {
      "fieldName": "initial_deposit",
      "fieldType": "CURRENCY",
      "validationRules": {
        "min": 10,
        "currency": "GHS"
      }
    }
  ]
}
\`\`\`

### **Phase 4: User Management & Permissions**

#### Step 8: Creating Department Administrators
\`\`\`
Endpoint: POST /api/admin/super/agents/create
\`\`\`

**Police Commissioner Account:**
\`\`\`json
{
  "firstName": "John",
  "lastName": "Mensah",
  "email": "commissioner.mensah@police.gov.gh",
  "phoneNumber": "+233244123456",
  "agentType": "POLICE_ADMIN",
  "organizationName": "National Police Service",
  "permissions": [
    "MANAGE_OFFICERS",
    "VIEW_ALL_INCIDENTS",
    "CREATE_PATROL_TEAMS",
    "GENERATE_REPORTS",
    "MANAGE_EQUIPMENT"
  ],
  "region": "NATIONAL",
  "badgeNumber": "COMM001"
}
\`\`\`

**Emergency Services Director:**
\`\`\`json
{
  "firstName": "Sarah",
  "lastName": "Asante",
  "email": "director.asante@emergency.gov.gh",
  "phoneNumber": "+233244789012",
  "agentType": "EMERGENCY_ADMIN",
  "organizationName": "Emergency Response Center",
  "permissions": [
    "DISPATCH_TEAMS",
    "MANAGE_INCIDENTS",
    "COORDINATE_RESPONSE",
    "ACCESS_MEDICAL_INFO"
  ]
}
\`\`\`

### **Phase 5: System Monitoring & Maintenance**

#### Step 9: Audit Trail Management
\`\`\`
Endpoint: GET /api/admin/super/audit/logs
\`\`\`

**Monitoring Activities:**
- **User Login Attempts**: Success/failure rates by department
- **Data Access Patterns**: Who accessed what citizen data when
- **System Changes**: Configuration modifications, permission updates
- **Performance Metrics**: Response times, error rates, uptime

#### Step 10: System Configuration
\`\`\`json
PUT /api/admin/super/system/config
{
  "sessionTimeout": 1800,
  "maxLoginAttempts": 3,
  "passwordPolicy": {
    "minLength": 12,
    "requireSpecialChars": true,
    "requireNumbers": true,
    "expiryDays": 90
  },
  "biometricSettings": {
    "requiredFingerprints": 5,
    "matchingThreshold": 85,
    "templateEncryption": "AES256"
  }
}
\`\`\`

---

## 🏢 Company/Department Admin Complete Flow

### **Phase 1: Department Admin Onboarding**

#### Step 1: First-Time Login
\`\`\`
URL: https://kzmkx0wxseuvu4lr359m.lite.vusercontent.net/login
Endpoint: POST /api/auth/login
\`\`\`

**Police Commissioner First Login:**
1. **Receives Credentials from Super Admin**
   - Email: commissioner.mensah@police.gov.gh
   - Temporary Password: TempPass123!
   - Must change password on first login

2. **Password Change Flow**
   \`\`\`json
   POST /api/auth/change-password
   {
     "currentPassword": "TempPass123!",
     "newPassword": "SecurePolicePass2024!",
     "confirmPassword": "SecurePolicePass2024!"
   }
   \`\`\`

3. **Department Dashboard Access**
   \`\`\`json
   GET /api/departments/dashboard
   Response: {
     "departmentName": "National Police Service",
     "totalOfficers": 0,
     "activePatrols": 0,
     "pendingIncidents": 0,
     "equipmentStatus": "NEEDS_SETUP"
   }
   \`\`\`

### **Phase 2: Staff Management**

#### Step 2: Creating Officer Accounts
\`\`\`
Endpoint: POST /api/users/officers/create
\`\`\`

**Creating Patrol Officer:**
\`\`\`json
{
  "firstName": "Kwame",
  "lastName": "Osei",
  "email": "officer.osei@police.gov.gh",
  "phoneNumber": "+233244567890",
  "badgeNumber": "PO001",
  "rank": "CONSTABLE",
  "station": "Accra Central",
  "specializations": ["TRAFFIC", "PATROL"],
  "vehicleAssigned": "PATROL_001",
  "shiftPattern": "DAY_SHIFT",
  "emergencyContact": {
    "name": "Akosua Osei",
    "phone": "+233244567891",
    "relationship": "SPOUSE"
  }
}
\`\`\`

**System Response:**
\`\`\`json
{
  "officerId": "OFF_001",
  "credentials": {
    "username": "officer.osei",
    "temporaryPassword": "OfficerPass123!",
    "mobileAppDownload": "https://gg10g1urv5roo2ucw.lite.vusercontent.net/"
  },
  "equipment": {
    "radio": "RADIO_001",
    "vehicle": "PATROL_001",
    "tablet": "TAB_001"
  }
}
\`\`\`

#### Step 3: Creating Dispatcher Accounts
\`\`\`json
POST /api/users/dispatchers/create
{
  "firstName": "Ama",
  "lastName": "Boateng",
  "email": "dispatcher.boateng@emergency.gov.gh",
  "phoneNumber": "+233244345678",
  "dispatcherId": "DISP001",
  "shift": "NIGHT_SHIFT",
  "languages": ["ENGLISH", "TWI", "GA"],
  "certifications": ["EMERGENCY_DISPATCH", "MEDICAL_DISPATCH"],
  "accessLevel": "REGIONAL"
}
\`\`\`

### **Phase 3: Operational Setup**

#### Step 4: Equipment Management
\`\`\`
Endpoint: POST /api/departments/equipment/assign
\`\`\`

**Assigning Patrol Vehicles:**
\`\`\`json
{
  "equipmentType": "PATROL_VEHICLE",
  "items": [
    {
      "vehicleId": "PATROL_001",
      "make": "Toyota",
      "model": "Hilux",
      "year": 2023,
      "licensePlate": "GP-1234-23",
      "gpsTracker": "GPS_001",
      "assignedOfficer": "OFF_001",
      "fuelCard": "FUEL_001"
    }
  ]
}
\`\`\`

#### Step 5: Setting Up Patrol Routes
\`\`\`json
POST /api/patrol/routes/create
{
  "routeName": "Accra Central Beat 1",
  "coordinates": [
    {"lat": 5.6037, "lng": -0.1870},
    {"lat": 5.6047, "lng": -0.1880},
    {"lat": 5.6057, "lng": -0.1890}
  ],
  "estimatedDuration": 120,
  "priority": "HIGH",
  "assignedOfficers": ["OFF_001", "OFF_002"]
}
\`\`\`

### **Phase 4: Daily Operations Management**

#### Step 6: Morning Briefing Setup
\`\`\`
Endpoint: POST /api/departments/briefings/create
\`\`\`

**Daily Briefing Creation:**
\`\`\`json
{
  "date": "2024-01-15",
  "shift": "DAY_SHIFT",
  "briefingItems": [
    {
      "type": "SECURITY_ALERT",
      "priority": "HIGH",
      "message": "Increased patrols needed in Market Area due to reported thefts"
    },
    {
      "type": "TRAFFIC_UPDATE",
      "priority": "MEDIUM",
      "message": "Road closure on Independence Avenue from 2PM-6PM"
    },
    {
      "type": "EQUIPMENT_UPDATE",
      "priority": "LOW",
      "message": "New body cameras available for pickup"
    }
  ],
  "attendees": ["OFF_001", "OFF_002", "OFF_003"]
}
\`\`\`

#### Step 7: Incident Assignment & Monitoring
\`\`\`json
GET /api/incidents/pending
Response: {
  "incidents": [
    {
      "incidentId": "INC_001",
      "type": "THEFT",
      "priority": "MEDIUM",
      "location": "Osu Market",
      "reportedAt": "2024-01-15T10:30:00Z",
      "status": "UNASSIGNED",
      "nearestOfficers": ["OFF_001", "OFF_003"]
    }
  ]
}
\`\`\`

**Assigning Incident:**
\`\`\`json
POST /api/incidents/INC_001/assign
{
  "assignedOfficer": "OFF_001",
  "priority": "MEDIUM",
  "estimatedResponseTime": 15,
  "instructions": "Respond to theft report at Osu Market. Victim waiting at main entrance."
}
\`\`\`

### **Phase 5: Performance Monitoring & Reporting**

#### Step 8: Real-Time Dashboard Monitoring
\`\`\`
Endpoint: GET /api/departments/dashboard/realtime
\`\`\`

**Live Department Metrics:**
\`\`\`json
{
  "activeOfficers": 25,
  "onPatrol": 18,
  "respondingToIncidents": 5,
  "onBreak": 2,
  "averageResponseTime": "8.5 minutes",
  "incidentsToday": 23,
  "resolvedToday": 18,
  "pendingIncidents": 5,
  "vehicleStatus": {
    "operational": 22,
    "maintenance": 3,
    "outOfService": 1
  }
}
\`\`\`

#### Step 9: Weekly Performance Reports
\`\`\`json
POST /api/reports/generate
{
  "reportType": "WEEKLY_PERFORMANCE",
  "dateRange": {
    "start": "2024-01-08",
    "end": "2024-01-14"
  },
  "metrics": [
    "RESPONSE_TIMES",
    "INCIDENT_RESOLUTION",
    "OFFICER_PERFORMANCE",
    "EQUIPMENT_UTILIZATION"
  ],
  "recipients": ["commissioner.mensah@police.gov.gh"]
}
\`\`\`

---

## 🚔 Patrol Team Complete Flow

### **Phase 1: Shift Preparation & Check-In**

#### Step 1: Mobile App Login
\`\`\`
URL: https://gg10g1urv5roo2ucw.lite.vusercontent.net/
Endpoint: POST /api/auth/login
\`\`\`

**Officer Mobile Login:**
\`\`\`json
{
  "username": "officer.osei",
  "password": "OfficerPass123!",
  "deviceId": "DEVICE_001",
  "location": {
    "latitude": 5.6037,
    "longitude": -0.1870
  }
}
\`\`\`

**Login Response:**
\`\`\`json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "officer": {
    "name": "Kwame Osei",
    "badgeNumber": "PO001",
    "rank": "CONSTABLE",
    "station": "Accra Central"
  },
  "shift": {
    "startTime": "06:00",
    "endTime": "18:00",
    "assignedVehicle": "PATROL_001",
    "route": "Accra Central Beat 1"
  }
}
\`\`\`

#### Step 2: Equipment Check & Vehicle Inspection
\`\`\`
Endpoint: POST /api/police/patrol/equipment/check
\`\`\`

**Pre-Shift Equipment Check:**
\`\`\`json
{
  "vehicleId": "PATROL_001",
  "equipmentChecks": [
    {
      "item": "RADIO",
      "status": "OPERATIONAL",
      "batteryLevel": 95
    },
    {
      "item": "BODY_CAMERA",
      "status": "OPERATIONAL",
      "storageAvailable": "8GB"
    },
    {
      "item": "TABLET",
      "status": "OPERATIONAL",
      "softwareVersion": "v2.1.3"
    },
    {
      "item": "FIRST_AID_KIT",
      "status": "COMPLETE",
      "expiryCheck": "VALID"
    }
  ],
  "vehicleInspection": {
    "fuelLevel": 85,
    "mileage": 45230,
    "tireCondition": "GOOD",
    "lightsWorking": true,
    "sirenWorking": true
  }
}
\`\`\`

#### Step 3: Shift Check-In
\`\`\`
Endpoint: POST /api/police/patrol/shift/start
\`\`\`

\`\`\`json
{
  "shiftId": "SHIFT_001",
  "startLocation": {
    "latitude": 5.6037,
    "longitude": -0.1870,
    "address": "Accra Central Police Station"
  },
  "vehicleId": "PATROL_001",
  "partnerId": "OFF_002",
  "routeAssignment": "Accra Central Beat 1"
}
\`\`\`

### **Phase 2: Active Patrol Operations**

#### Step 4: GPS Tracking & Route Following
\`\`\`
Endpoint: POST /api/police/patrol/location/update
\`\`\`

**Continuous Location Updates (every 30 seconds):**
\`\`\`json
{
  "latitude": 5.6047,
  "longitude": -0.1880,
  "speed": 25,
  "heading": 45,
  "timestamp": "2024-01-15T10:15:30Z",
  "status": "ON_PATROL"
}
\`\`\`

#### Step 5: Receiving Incident Assignment
\`\`\`
WebSocket: /ws/patrol/notifications
\`\`\`

**Incident Notification:**
\`\`\`json
{
  "type": "INCIDENT_ASSIGNMENT",
  "incidentId": "INC_001",
  "priority": "HIGH",
  "details": {
    "type": "TRAFFIC_ACCIDENT",
    "location": {
      "latitude": 5.6055,
      "longitude": -0.1885,
      "address": "Independence Avenue & Castle Road"
    },
    "description": "Two-vehicle collision, possible injuries",
    "reportedBy": "CITIZEN_001",
    "estimatedDistance": "0.8 km",
    "estimatedArrival": "3 minutes"
  },
  "actions": ["ACCEPT", "REQUEST_BACKUP", "UNABLE_TO_RESPOND"]
}
\`\`\`

**Officer Response:**
\`\`\`json
POST /api/incidents/INC_001/respond
{
  "response": "ACCEPT",
  "estimatedArrival": 3,
  "currentLocation": {
    "latitude": 5.6047,
    "longitude": -0.1880
  }
}
\`\`\`

### **Phase 3: Incident Response**

#### Step 6: En Route to Incident
\`\`\`
Endpoint: PUT /api/incidents/INC_001/status
\`\`\`

**Status Updates:**
\`\`\`json
{
  "status": "EN_ROUTE",
  "officerId": "OFF_001",
  "estimatedArrival": 2,
  "currentLocation": {
    "latitude": 5.6052,
    "longitude": -0.1883
  }
}
\`\`\`

#### Step 7: Arrival at Scene
\`\`\`json
PUT /api/incidents/INC_001/status
{
  "status": "ON_SCENE",
  "arrivalTime": "2024-01-15T10:18:45Z",
  "sceneAssessment": {
    "vehiclesInvolved": 2,
    "injuriesReported": true,
    "trafficBlocked": true,
    "backupRequired": true,
    "medicalRequired": true
  }
}
\`\`\`

#### Step 8: Requesting Additional Resources
\`\`\`json
POST /api/police/patrol/backup/request
{
  "incidentId": "INC_001",
  "urgency": "HIGH",
  "reason": "TRAFFIC_ACCIDENT_WITH_INJURIES",
  "resourcesNeeded": ["AMBULANCE", "TRAFFIC_UNIT", "TOW_TRUCK"],
  "location": {
    "latitude": 5.6055,
    "longitude": -0.1885
  }
}
\`\`\`

### **Phase 4: Traffic Stop Operations**

#### Step 9: Routine Traffic Stop
\`\`\`
Endpoint: POST /api/police/patrol/traffic/stop
\`\`\`

**Initiating Traffic Stop:**
\`\`\`json
{
  "reason": "SPEEDING",
  "location": {
    "latitude": 5.6060,
    "longitude": -0.1890,
    "address": "Ring Road East"
  },
  "vehicleDetails": {
    "licensePlate": "GR-5678-22",
    "make": "Honda",
    "model": "Civic",
    "color": "Blue"
  },
  "radarReading": 85,
  "speedLimit": 50
}
\`\`\`

#### Step 10: License Verification
\`\`\`
Endpoint: POST /api/police/patrol/licenses/verify
\`\`\`

**Scanning Digital License:**
\`\`\`json
{
  "qrCode": "DL_QR_CODE_DATA_HERE",
  "scanLocation": {
    "latitude": 5.6060,
    "longitude": -0.1890
  }
}
\`\`\`

**Verification Response:**
\`\`\`json
{
  "valid": true,
  "driverInfo": {
    "name": "Akosua Mensah",
    "licenseNumber": "DL-123456789",
    "class": "Class C",
    "expiryDate": "2026-12-31",
    "restrictions": "CORRECTIVE_LENSES"
  },
  "violations": [
    {
      "date": "2023-11-15",
      "type": "PARKING",
      "status": "PAID"
    }
  ],
  "warrants": []
}
\`\`\`

#### Step 11: Issuing Citation
\`\`\`
Endpoint: POST /api/police/patrol/citations/issue
\`\`\`

\`\`\`json
{
  "driverLicense": "DL-123456789",
  "violationType": "SPEEDING",
  "details": {
    "speedRecorded": 85,
    "speedLimit": 50,
    "location": "Ring Road East",
    "radarUnit": "RADAR_001"
  },
  "fineAmount": 150.00,
  "courtDate": "2024-02-15",
  "paymentOptions": ["MOBILE_MONEY", "BANK", "COURT"]
}
\`\`\`

### **Phase 5: End of Shift Procedures**

#### Step 12: Incident Report Filing
\`\`\`
Endpoint: POST /api/incidents/INC_001/report
\`\`\`

**Detailed Incident Report:**
\`\`\`json
{
  "incidentId": "INC_001",
  "summary": "Two-vehicle collision at Independence Avenue & Castle Road",
  "details": {
    "timeOfIncident": "2024-01-15T10:15:00Z",
    "weatherConditions": "Clear",
    "roadConditions": "Dry",
    "vehiclesInvolved": [
      {
        "licensePlate": "GR-1234-22",
        "driver": "John Doe",
        "insurance": "VALID",
        "damage": "FRONT_END"
      },
      {
        "licensePlate": "GR-5678-22",
        "driver": "Jane Smith",
        "insurance": "VALID",
        "damage": "REAR_END"
      }
    ],
    "injuries": [
      {
        "person": "Jane Smith",
        "type": "MINOR",
        "treatment": "FIRST_AID_ON_SCENE"
      }
    ],
    "citations": ["CIT_001"],
    "evidenceCollected": ["PHOTOS", "MEASUREMENTS", "WITNESS_STATEMENTS"]
  },
  "disposition": "REPORT_FILED",
  "followUpRequired": false
}
\`\`\`

#### Step 13: End Shift Check-Out
\`\`\`
Endpoint: POST /api/police/patrol/shift/end
\`\`\`

\`\`\`json
{
  "shiftId": "SHIFT_001",
  "endLocation": {
    "latitude": 5.6037,
    "longitude": -0.1870,
    "address": "Accra Central Police Station"
  },
  "milesDriven": 125.5,
  "fuelUsed": 18.2,
  "incidentsHandled": 3,
  "citationsIssued": 2,
  "equipmentReturned": true,
  "notes": "Routine patrol completed. All equipment operational."
}
\`\`\`

---

## 🏦 Agent Complete Flow

### **Phase 1: Agent Registration & Setup**

#### Step 1: Agent Organization Registration
\`\`\`
Endpoint: POST /api/agent/organization/register
\`\`\`

**Bank Agent Registration:**
\`\`\`json
{
  "organizationType": "BANK",
  "organizationName": "Ghana Commercial Bank",
  "licenseNumber": "BANK_LIC_001",
  "contactPerson": {
    "name": "Samuel Asante",
    "title": "Digital Services Manager",
    "email": "s.asante@gcb.com.gh",
    "phone": "+233244123456"
  },
  "businessAddress": {
    "street": "123 Banking Street",
    "city": "Accra",
    "region": "Greater Accra",
    "postalCode": "GA-123-4567"
  },
  "servicesOffered": ["ACCOUNT_OPENING", "KYC_VERIFICATION", "LOAN_PROCESSING"],
  "expectedVolume": 500
}
\`\`\`

#### Step 2: Agent Account Creation
\`\`\`json
POST /api/agent/accounts/create
{
  "firstName": "Kwame",
  "lastName": "Asante",
  "email": "k.asante@gcb.com.gh",
  "phoneNumber": "+233244789012",
  "agentId": "GCB_AGENT_001",
  "organizationId": "ORG_GCB_001",
  "role": "SENIOR_AGENT",
  "permissions": [
    "CITIZEN_LOOKUP",
    "BIOMETRIC_VERIFICATION",
    "SERVICE_CREATION",
    "CONSENT_REQUEST"
  ],
  "workingHours": {
    "monday": "08:00-17:00",
    "tuesday": "08:00-17:00",
    "wednesday": "08:00-17:00",
    "thursday": "08:00-17:00",
    "friday": "08:00-17:00",
    "saturday": "08:00-13:00"
  }
}
\`\`\`

### **Phase 2: Daily Agent Operations**

#### Step 3: Agent Login & Dashboard
\`\`\`
Endpoint: POST /api/agent/login
\`\`\`

\`\`\`json
{
  "username": "k.asante@gcb.com.gh",
  "password": "AgentPass123!",
  "organizationCode": "GCB",
  "branchCode": "ACCRA_MAIN"
}
\`\`\`

**Agent Dashboard Response:**
\`\`\`json
{
  "agentInfo": {
    "name": "Kwame Asante",
    "agentId": "GCB_AGENT_001",
    "organization": "Ghana Commercial Bank",
    "branch": "Accra Main Branch"
  },
  "todaysStats": {
    "customersServed": 12,
    "accountsOpened": 5,
    "kycCompleted": 8,
    "averageServiceTime": "15 minutes"
  },
  "availableServices": [
    "SAVINGS_ACCOUNT",
    "CURRENT_ACCOUNT",
    "LOAN_APPLICATION",
    "MOBILE_MONEY_REGISTRATION"
  ]
}
\`\`\`

### **Phase 3: Customer Service Process**

#### Step 4: Customer Lookup
\`\`\`
Endpoint: POST /api/agent/citizen/lookup
\`\`\`

**Looking Up Customer:**
\`\`\`json
{
  "phoneNumber": "+233244567890",
  "purpose": "ACCOUNT_OPENING",
  "serviceType": "SAVINGS_ACCOUNT"
}
\`\`\`

**Lookup Response:**
\`\`\`json
{
  "found": true,
  "citizen": {
    "name": "Akosua Mensah",
    "phone": "+233244567890",
    "kycStatus": "VERIFIED",
    "digitalIdAvailable": true,
    "existingServices": ["MOBILE_MONEY", "DIGITAL_ADDRESS"]
  },
  "verificationRequired": true,
  "availableVerificationMethods": ["OTP", "BIOMETRIC", "DIGITAL_ID"]
}
\`\`\`

#### Step 5: OTP Verification
\`\`\`
Endpoint: POST /api/agent/otp/generate
\`\`\`

\`\`\`json
{
  "phoneNumber": "+233244567890",
  "purpose": "IDENTITY_VERIFICATION",
  "agentId": "GCB_AGENT_001"
}
\`\`\`

**OTP Verification:**
\`\`\`json
POST /api/agent/otp/verify
{
  "phoneNumber": "+233244567890",
  "otpCode": "1234",
  "agentId": "GCB_AGENT_001"
}
\`\`\`

### **Phase 4: Biometric Verification Process**

#### Step 6: Biometric Enrollment (New Customer)
\`\`\`
Endpoint: POST /api/agent/biometric/enroll
\`\`\`

**Fingerprint Enrollment:**
\`\`\`json
{
  "citizenId": "CIT_001",
  "fingerprintTemplates": [
    {
      "finger": "RIGHT_THUMB",
      "template": "BIOMETRIC_TEMPLATE_DATA_BASE64",
      "quality": 95
    },
    {
      "finger": "RIGHT_INDEX",
      "template": "BIOMETRIC_TEMPLATE_DATA_BASE64",
      "quality": 92
    },
    {
      "finger": "LEFT_THUMB",
      "template": "BIOMETRIC_TEMPLATE_DATA_BASE64",
      "quality": 94
    }
  ],
  "enrollmentLocation": {
    "latitude": 5.6037,
    "longitude": -0.1870,
    "address": "GCB Accra Main Branch"
  },
  "agentId": "GCB_AGENT_001"
}
\`\`\`

#### Step 7: Biometric Verification (Existing Customer)
\`\`\`
Endpoint: POST /api/agent/biometric/verify
\`\`\`

\`\`\`json
{
  "fingerprintTemplate": "BIOMETRIC_TEMPLATE_DATA_BASE64",
  "finger": "RIGHT_THUMB",
  "agentId": "GCB_AGENT_001",
  "purpose": "ACCOUNT_ACCESS"
}
\`\`\`

**Verification Response:**
\`\`\`json
{
  "verified": true,
  "matchScore": 96,
  "citizen": {
    "id": "CIT_001",
    "name": "Akosua Mensah",
    "kycStatus": "VERIFIED"
  },
  "accessCode": "AC_789012",
  "expiresIn": 60,
  "message": "Biometric verification successful"
}
\`\`\`

### **Phase 5: Service Creation & Consent Management**

#### Step 8: Requesting Data Consent
\`\`\`
Endpoint: POST /api/agent/consent/request
\`\`\`

\`\`\`json
{
  "citizenId": "CIT_001",
  "consentTypes": [
    {
      "type": "FINANCIAL_HISTORY",
      "purpose": "CREDIT_ASSESSMENT",
      "duration": "12_MONTHS",
      "dataFields": ["INCOME", "EXPENSES", "EXISTING_LOANS"]
    },
    {
      "type": "GOVERNMENT_SERVICES",
      "purpose": "IDENTITY_VERIFICATION",
      "duration": "SINGLE_USE",
      "dataFields": ["DIGITAL_ID", "ADDRESS", "EMPLOYMENT"]
    }
  ],
  "agentId": "GCB_AGENT_001",
  "serviceType": "LOAN_APPLICATION"
}
\`\`\`

#### Step 9: Creating New Service
\`\`\`
Endpoint: POST /api/agent/services/add
\`\`\`

**Creating Bank Account:**
\`\`\`json
{
  "citizenId": "CIT_001",
  "serviceTemplate": "SAVINGS_ACCOUNT",
  "serviceData": {
    "accountType": "SAVINGS",
    "initialDeposit": 100.00,
    "currency": "GHS",
    "branchCode": "ACCRA_MAIN",
    "accountFeatures": ["MOBILE_BANKING", "ATM_CARD", "SMS_ALERTS"],
    "monthlyFee": 5.00,
    "minimumBalance": 50.00
  },
  "agentId": "GCB_AGENT_001",
  "consentId": "CONSENT_001"
}
\`\`\`

**Service Creation Response:**
\`\`\`json
{
  "serviceId": "SVC_001",
  "accountNumber": "1234567890",
  "status": "ACTIVE",
  "createdAt": "2024-01-15T14:30:00Z",
  "features": {
    "mobileApp": "Download GCB Mobile App",
    "atmCard": "Card will be ready in 3-5 business days",
    "onlineBanking": "Access available immediately"
  },
  "nextSteps": [
    "Download mobile app",
    "Set up PIN",
    "Collect ATM card"
  ]
}
\`\`\`

### **Phase 6: Special Agent Workflows**

#### Step 10: Telco SIM Registration (Telco Agents)
\`\`\`
Endpoint: POST /api/agent/telco/sim-register
\`\`\`

\`\`\`json
{
  "citizenId": "CIT_001",
  "simCardNumber": "89233123456789012345",
  "phoneNumber": "+233244567890",
  "planType": "PREPAID",
  "package": "STARTER_PACK",
  "agentId": "MTN_AGENT_001",
  "skipPhoneVerification": true
}
\`\`\`

#### Step 11: Immigration Profile Creation
\`\`\`
Endpoint: POST /api/agent/immigration/create-profile
\`\`\`

\`\`\`json
{
  "firstName": "John",
  "lastName": "Smith",
  "nationality": "AMERICAN",
  "passportNumber": "US123456789",
  "visaType": "BUSINESS",
  "entryDate": "2024-01-15",
  "purpose": "BUSINESS_MEETING",
  "agentId": "IMMIGRATION_AGENT_001",
  "skipPhoneRequirement": true
}
\`\`\`

---

## 📱 Citizen App Complete Flow

### **Phase 1: App Download & Registration**

#### Step 1: First-Time App Launch
\`\`\`
URL: https://kzmpxmtnzru8u4h3du9h.lite.vusercontent.net/
\`\`\`

**Welcome Screen Flow:**
1. **Language Selection**: English, Twi, Ga, Ewe, Hausa
2. **Terms & Conditions**: Government data usage policy
3. **Permissions Request**: Location, Camera, Contacts, Phone

#### Step 2: Account Registration
\`\`\`
Endpoint: POST /api/auth/register
\`\`\`

\`\`\`json
{
  "phoneNumber": "+233244567890",
  "firstName": "Akosua",
  "lastName": "Mensah",
  "email": "akosua.mensah@gmail.com",
  "dateOfBirth": "1990-05-15",
  "gender": "FEMALE",
  "preferredLanguage": "ENGLISH",
  "agreeToTerms": true
}
\`\`\`

#### Step 3: Phone Verification
\`\`\`
Endpoint: POST /api/auth/verify-phone
\`\`\`

**OTP Verification:**
\`\`\`json
{
  "phoneNumber": "+233244567890",
  "otpCode": "1234",
  "deviceId": "DEVICE_CITIZEN_001"
}
\`\`\`

### **Phase 2: Profile Setup & Onboarding**

#### Step 4: Complete Profile Information
\`\`\`
Endpoint: POST /api/citizen/app/onboarding/complete
\`\`\`

\`\`\`json
{
  "personalInfo": {
    "firstName": "Akosua",
    "lastName": "Mensah",
    "middleName": "Abena",
    "dateOfBirth": "1990-05-15",
    "placeOfBirth": "Accra",
    "nationality": "GHANAIAN",
    "maritalStatus": "MARRIED",
    "occupation": "TEACHER",
    "employer": "Accra Academy"
  },
  "address": {
    "houseNumber": "H123",
    "streetName": "Liberation Road",
    "area": "Osu",
    "city": "Accra",
    "region": "Greater Accra",
    "digitalAddress": "GA-123-4567"
  },
  "emergencyContact": {
    "name": "Kwame Mensah",
    "relationship": "SPOUSE",
    "phoneNumber": "+233244567891",
    "alternatePhone": "+233302123456"
  },
  "medicalInfo": {
    "bloodType": "O_POSITIVE",
    "allergies": ["PENICILLIN"],
    "chronicConditions": [],
    "emergencyMedicalContact": "Dr. Sarah Asante - +233244789012"
  }
}
\`\`\`

#### Step 5: Document Upload for KYC
\`\`\`
Endpoint: POST /api/kyc/documents/upload
\`\`\`

\`\`\`json
{
  "documents": [
    {
      "type": "NATIONAL_ID",
      "frontImage": "BASE64_IMAGE_DATA",
      "backImage": "BASE64_IMAGE_DATA",
      "documentNumber": "GHA-123456789-0"
    },
    {
      "type": "PROOF_OF_ADDRESS",
      "image": "BASE64_IMAGE_DATA",
      "documentType": "UTILITY_BILL",
      "issueDate": "2024-01-01"
    },
    {
      "type": "PASSPORT_PHOTO",
      "image": "BASE64_IMAGE_DATA",
      "capturedLive": true
    }
  ]
}
\`\`\`

### **Phase 3: Dashboard & Daily Usage**

#### Step 6: Accessing Citizen Dashboard
\`\`\`
Endpoint: GET /api/citizen/app/dashboard
\`\`\`

**Dashboard Response:**
\`\`\`json
{
  "welcomeMessage": "Welcome back, Akosua",
  "kycStatus": "VERIFIED",
  "digitalIdAvailable": true,
  "quickStats": {
    "activeServices": 5,
    "pendingApplications": 1,
    "unreadNotifications": 3,
    "creditScore": 750
  },
  "quickActions": [
    {
      "title": "Report Emergency",
      "icon": "emergency",
      "action": "EMERGENCY_REPORT",
      "color": "red"
    },
    {
      "title": "View Digital ID",
      "icon": "id_card",
      "action": "VIEW_DIGITAL_ID",
      "color": "blue"
    },
    {
      "title": "Apply for License",
      "icon": "license",
      "action": "APPLY_LICENSE",
      "color": "green"
    },
    {
      "title": "Register Vehicle",
      "icon": "car",
      "action": "REGISTER_VEHICLE",
      "color": "orange"
    }
  ],
  "recentActivity": [
    "KYC verification completed",
    "Digital ID issued",
    "Vehicle registration approved"
  ],
  "nearbyServices": [
    {
      "name": "Osu Police Station",
      "distance": "0.5 km",
      "type": "POLICE"
    },
    {
      "name": "Ridge Hospital",
      "distance": "1.2 km",
      "type": "MEDICAL"
    }
  ]
}
\`\`\`

### **Phase 4: Emergency Reporting**

#### Step 7: Emergency Report Initiation
\`\`\`
Endpoint: POST /api/citizen/app/reports/incident
\`\`\`

**Emergency Report Flow:**
1. **Emergency Type Selection**:
   - Medical Emergency
   - Fire Emergency
   - Police Emergency
   - Traffic Accident
   - Crime in Progress

2. **Automatic Location Capture**:
\`\`\`json
{
  "incidentType": "MEDICAL_EMERGENCY",
  "priority": "HIGH",
  "location": {
    "latitude": 5.6037,
    "longitude": -0.1870,
    "accuracy": 5,
    "address": "Liberation Road, Osu, Accra",
    "digitalAddress": "GA-123-4567"
  },
  "description": "Elderly man collapsed on the street, unconscious",
  "reporterInfo": {
    "citizenId": "CIT_001",
    "name": "Akosua Mensah",
    "phone": "+233244567890",
    "medicalInfo": "Available in profile"
  },
  "additionalInfo": {
    "victimsCount": 1,
    "consciousnessLevel": "UNCONSCIOUS",
    "breathing": "YES",
    "bleeding": "NO"
  }
}
\`\`\`

#### Step 8: Real-Time Emergency Tracking
\`\`\`
WebSocket: /ws/citizen/emergency-updates
\`\`\`

**Emergency Status Updates:**
\`\`\`json
{
  "incidentId": "INC_001",
  "status": "DISPATCHED",
  "updates": [
    {
      "timestamp": "2024-01-15T10:30:00Z",
      "message": "Emergency received and verified",
      "status": "RECEIVED"
    },
    {
      "timestamp": "2024-01-15T10:31:00Z",
      "message": "Ambulance dispatched from Ridge Hospital",
      "status": "DISPATCHED",
      "eta": "5 minutes"
    },
    {
      "timestamp": "2024-01-15T10:33:00Z",
      "message": "Police unit also dispatched",
      "status": "ADDITIONAL_RESOURCES"
    }
  ],
  "responders": [
    {
      "type": "AMBULANCE",
      "unit": "AMB_001",
      "eta": "3 minutes",
      "location": "0.8 km away"
    },
    {
      "type": "POLICE",
      "unit": "PATROL_001",
      "eta": "2 minutes",
      "location": "0.5 km away"
    }
  ]
}
\`\`\`

### **Phase 5: Government Services Access**

#### Step 9: Applying for Driver's License
\`\`\`
Endpoint: POST /api/licenses/apply
\`\`\`

**License Application:**
\`\`\`json
{
  "licenseType": "DRIVERS_LICENSE",
  "class": "CLASS_C",
  "applicantInfo": {
    "citizenId": "CIT_001",
    "hasExistingLicense": false,
    "previousLicenseNumber": null
  },
  "documents": [
    {
      "type": "MEDICAL_CERTIFICATE",
      "image": "BASE64_IMAGE_DATA",
      "issueDate": "2024-01-10",
      "expiryDate": "2024-07-10"
    },
    {
      "type": "DRIVING_TEST_CERTIFICATE",
      "image": "BASE64_IMAGE_DATA",
      "testScore": 85,
      "testDate": "2024-01-12"
    }
  ],
  "payment": {
    "amount": 50.00,
    "method": "MOBILE_MONEY",
    "reference": "MM_REF_123456"
  }
}
\`\`\`

#### Step 10: Vehicle Registration
\`\`\`
Endpoint: POST /api/vehicles/register
\`\`\`

\`\`\`json
{
  "vehicleInfo": {
    "make": "Toyota",
    "model": "Corolla",
    "year": 2022,
    "color": "Silver",
    "engineNumber": "ENG123456789",
    "chassisNumber": "CHS987654321",
    "fuelType": "PETROL"
  },
  "ownerInfo": {
    "citizenId": "CIT_001",
    "ownershipType": "INDIVIDUAL",
    "purchaseDate": "2024-01-01"
  },
  "documents": [
    {
      "type": "PURCHASE_RECEIPT",
      "image": "BASE64_IMAGE_DATA"
    },
    {
      "type": "CUSTOMS_CLEARANCE",
      "image": "BASE64_IMAGE_DATA"
    }
  ],
  "insurance": {
    "provider": "Enterprise Insurance",
    "policyNumber": "POL123456789",
    "expiryDate": "2025-01-01"
  }
}
\`\`\`

### **Phase 6: Digital Services Usage**

#### Step 11: Generating Digital Address
\`\`\`
Endpoint: POST /api/citizen/app/digital-address/generate
\`\`\`

\`\`\`json
{
  "location": {
    "latitude": 5.6037,
    "longitude": -0.1870
  },
  "description": "Main entrance of Akosua's house",
  "addressType": "RESIDENTIAL",
  "landmarks": ["Near Osu Castle", "Opposite Shell Filling Station"]
}
\`\`\`

**Digital Address Response:**
\`\`\`json
{
  "digitalAddress": "GA-123-4567",
  "qrCode": "QR_CODE_DATA_BASE64",
  "coordinates": {
    "latitude": 5.6037,
    "longitude": -0.1870
  },
  "accuracy": "5 meters",
  "validUntil": "2029-01-15",
  "usageInstructions": "Share this code with delivery services, emergency responders, or anyone who needs to find your location"
}
\`\`\`

#### Step 12: Viewing Digital ID
\`\`\`
Endpoint: GET /api/licenses/my-licenses
\`\`\`

**Digital ID Display:**
\`\`\`json
{
  "digitalId": {
    "idNumber": "GHA-123456789-0",
    "qrCode": "QR_CODE_DATA_BASE64",
    "hologram": "HOLOGRAM_DATA",
    "citizenInfo": {
      "name": "Akosua Abena Mensah",
      "photo": "PHOTO_DATA_BASE64",
      "dateOfBirth": "1990-05-15",
      "placeOfBirth": "Accra",
      "nationality": "Ghanaian"
    },
    "issuedDate": "2024-01-15",
    "expiryDate": "2034-01-15",
    "status": "ACTIVE"
  },
  "driversLicense": {
    "licenseNumber": "DL-987654321",
    "class": "CLASS_C",
    "qrCode": "DL_QR_CODE_DATA",
    "restrictions": [],
    "endorsements": [],
    "issuedDate": "2024-01-15",
    "expiryDate": "2029-01-15"
  }
}
\`\`\`

### **Phase 7: Community Features**

#### Step 13: Q&A System Usage
\`\`\`
Endpoint: GET /api/citizen/app/qa/search?query=driver license
\`\`\`

**Q&A Search Results:**
\`\`\`json
{
  "results": [
    {
      "question": "How long does it take to get a driver's license?",
      "answer": "After submitting all required documents and passing the driving test, your digital license is typically available within 3-5 business days.",
      "category": "LICENSES",
      "helpful": true,
      "lastUpdated": "2024-01-10"
    },
    {
      "question": "What documents do I need for a driver's license?",
      "answer": "You need a valid national ID, medical certificate, driving test certificate, and passport-sized photos.",
      "category": "LICENSES",
      "helpful": true,
      "lastUpdated": "2024-01-08"
    }
  ]
}
\`\`\`

#### Step 14: Activity Tracking
\`\`\`
Endpoint: GET /api/citizen/app/tracking/activities
\`\`\`

**Activity Status:**
\`\`\`json
{
  "activities": [
    {
      "type": "KYC_VERIFICATION",
      "title": "Identity Verification",
      "status": "COMPLETED",
      "progress": 100,
      "completedAt": "2024-01-10T14:30:00Z",
      "result": "APPROVED"
    },
    {
      "type": "DRIVERS_LICENSE",
      "title": "Driver's License Application",
      "status": "IN_PROGRESS",
      "progress": 75,
      "currentStep": "DOCUMENT_REVIEW",
      "estimatedCompletion": "2024-01-18",
      "nextAction": "Wait for KYC admin review"
    },
    {
      "type": "VEHICLE_REGISTRATION",
      "title": "Vehicle Registration",
      "status": "PENDING",
      "progress": 25,
      "currentStep": "DOCUMENT_UPLOAD",
      "nextAction": "Upload insurance documents"
    }
  ]
}
\`\`\`

---

## 🌟 Real-World Scenarios

### **Scenario 1: Complete Emergency Response Chain**

**Timeline: Monday, 10:30 AM - Traffic Accident**

1. **10:30:00** - Citizen Akosua witnesses car accident
   - Opens citizen app: `https://kzmpxmtnzru8u4h3du9h.lite.vusercontent.net/`
   - Taps "Emergency" → "Traffic Accident"
   - GPS automatically captures location: Independence Avenue & Castle Road
   - Describes: "Two cars collided, one person appears injured"

2. **10:30:15** - Emergency system processes report
   - Incident INC_001 created with HIGH priority
   - Location verified: 5.6055, -0.1885
   - Nearest resources identified automatically

3. **10:30:30** - Emergency dispatcher receives alert
   - Dashboard at `https://8juyhk3ix3hhzzxoo.lite.vusercontent.net/` shows new incident
   - Dispatcher Ama Boateng reviews details
   - Assigns ambulance AMB_001 and police PATROL_001

4. **10:30:45** - Patrol officer receives notification
   - Officer Kwame Osei on patrol app `https://gg10g1urv5roo2ucw.lite.vusercontent.net/`
   - Accepts assignment, marks "En Route"
   - GPS tracking shows 0.8km away, ETA 3 minutes

5. **10:31:00** - Citizen receives updates
   - Push notification: "Emergency services dispatched"
   - Real-time tracking shows ambulance and police locations
   - ETA displayed: Police 3 min, Ambulance 5 min

6. **10:33:30** - Officer arrives on scene
   - Updates status to "On Scene"
   - Assesses situation: 2 vehicles, 1 minor injury
   - Requests traffic unit for road clearance

7. **10:35:00** - Ambulance arrives
   - Treats injured person (minor cuts)
   - No hospital transport needed
   - Updates medical status in system

8. **10:45:00** - Incident resolution
   - Officer issues citation for failure to yield
   - Traffic cleared, vehicles moved
   - Incident marked "RESOLVED"
   - Citizen receives closure notification

### **Scenario 2: Complete Citizen Service Journey**

**Timeline: New Citizen Getting Full Government Services**

**Day 1: Registration & KYC**
1. **Morning** - Downloads citizen app
   - Registers with phone +233244567890
   - Completes profile with address, emergency contacts
   - Uploads national ID, proof of address, passport photo

2. **Afternoon** - KYC Admin Review
   - KYC admin at `https://kzmixiaaf443clitsc4x.lite.vusercontent.net/login`
   - Reviews uploaded documents
   - Verifies ID authenticity against government database
   - Approves KYC with VERIFIED status

**Day 2: Biometric Enrollment**
3. **Morning** - Visits Bank for Account Opening
   - Bank agent looks up citizen by phone
   - Enrolls 5 fingerprints using biometric scanner
   - Opens savings account with initial deposit
   - Account number: 1234567890 created

**Day 3: License Application**
4. **Morning** - Applies for Driver's License
   - Uses citizen app to apply for Class C license
   - Uploads medical certificate and driving test results
   - Pays 50 GHS fee via mobile money

5. **Afternoon** - KYC Admin Processes License
   - Reviews driving test score (85/100)
   - Verifies medical certificate validity
   - Generates digital license with QR code
   - License DL-987654321 issued

**Day 4: Vehicle Registration**
6. **Morning** - Registers New Vehicle
   - Uploads purchase receipt and customs clearance
   - Provides insurance details
   - Pays registration fee

7. **Afternoon** - Vehicle Approved
   - License plate GR-1234-24 assigned
   - Digital vehicle registration created
   - QR code generated for verification

**Day 5: Digital Address**
8. **Evening** - Generates Digital Address
   - Uses GPS to mark home location
   - System generates GA-123-4567
   - QR code created for delivery services

### **Scenario 3: Police Traffic Stop with Digital Verification**

**Timeline: Tuesday, 2:15 PM - Routine Traffic Stop**

1. **2:15:00** - Officer spots speeding vehicle
   - Patrol officer on Ring Road East
   - Radar shows 85 km/h in 50 km/h zone
   - Initiates traffic stop

2. **2:16:00** - Vehicle pulled over
   - Officer approaches driver
   - Requests license and registration
   - Driver shows digital license on phone

3. **2:16:30** - Digital license scan
   - Officer scans QR code with patrol app
   - System instantly verifies:
     - License DL-987654321 is valid
     - Expires 2029-01-15
     - No restrictions
     - Clean driving record

4. **2:17:00** - Vehicle verification
   - Officer scans vehicle registration QR
   - Confirms:
     - Registration current
     - Insurance valid
     - No outstanding violations
     - Not reported stolen

5. **2:18:00** - Citation issuance
   - Officer issues digital citation CIT_001
   - Fine: 150 GHS for speeding
   - Due date: 30 days
   - Payment options: Mobile money, bank, court

6. **2:18:30** - Driver receives citation
   - Citation appears instantly in citizen app
   - Payment link provided
   - Court date option available
   - Warning about license points

### **Scenario 4: Agent-Assisted Service Creation**

**Timeline: Wednesday, 11:00 AM - Bank Account Opening**

1. **11:00:00** - Customer visits bank
   - Wants to open business account
   - Provides phone number for lookup

2. **11:01:00** - Agent lookup
   - Bank agent searches by phone
   - Finds existing citizen profile
   - KYC status: VERIFIED
   - Biometric enrollment: COMPLETE

3. **11:02:00** - Identity verification
   - Agent requests fingerprint scan
   - Customer places thumb on scanner
   - 1:N matching against database
   - Match found: 96% confidence
   - Identity confirmed: Kwame Asante

4. **11:03:00** - Consent request
   - Agent requests access to:
     - Financial history (for credit check)
     - Employment information
     - Government services data
   - Consent request sent to customer's phone

5. **11:04:00** - Customer approves consent
   - Reviews consent details on phone
   - Approves access for 12 months
   - Digital signature captured

6. **11:05:00** - Account creation
   - Agent creates business account
   - Account type: Current Account
   - Features: Mobile banking, checkbook, ATM card
   - Initial deposit: 500 GHS

7. **11:06:00** - Service completion
   - Account number: 9876543210
   - Mobile banking activated
   - ATM card ordered (3-5 days)
   - Welcome package provided

### **Scenario 5: Super Admin System Management**

**Timeline: Thursday, 9:00 AM - Monthly System Review**

1. **9:00:00** - Super admin login
   - Accesses portal: `https://kzmkx0wxseuvu4lr359m.lite.vusercontent.net/login`
   - Reviews system health dashboard
   - Checks overnight alerts and incidents

2. **9:15:00** - Department performance review
   - Police Department: 95% incident response rate
   - Emergency Services: 7.5 min average response time
   - KYC Services: 1,250 verifications completed
   - System uptime: 99.8%

3. **9:30:00** - New service template creation
   - Creates "Passport Application" template
   - Defines required fields:
     - Travel purpose (business/tourism/family)
     - Destination countries
     - Travel dates
     - Emergency contact abroad
   - Sets processing time: 10 business days

4. **9:45:00** - Agent organization approval
   - Reviews application from new microfinance company
   - Verifies business license and credentials
   - Approves agent access for loan services
   - Creates agent account for MFI manager

5. **10:00:00** - System optimization
   - Reviews database performance metrics
   - Optimizes slow-running queries
   - Updates security policies
   - Schedules maintenance window

6. **10:30:00** - Audit trail review
   - Reviews suspicious login attempts
   - Checks data access patterns
   - Verifies compliance with data protection rules
   - Generates monthly security report

This comprehensive documentation covers every aspect of the E-Government system from initial setup to daily operations, ensuring complete understanding of all user flows and system interactions.
