# E-Government System - DTO Documentation

## Overview
This document provides comprehensive documentation for all Data Transfer Objects (DTOs) in the E-Government Unified Backend API system.

## DTO Categories

### 1. Common DTOs
Located in: `src/main/java/com/egov/common/dto/`

#### AddressDto
**File Path:** `src/main/java/com/egov/common/dto/AddressDto.java`
**Purpose:** Standardized address handling with geolocation support
**Key Features:**
- GPS coordinates integration
- Digital address code support (Ghana Post GPS)
- Address verification status tracking
- Multi-language support

**Validation Rules:**
- Street, city, region, country are required
- Latitude/longitude must be valid coordinates
- Postal code format validation
- Digital address code format validation

#### UserDto
**File Path:** `src/main/java/com/egov/common/dto/UserDto.java`
**Purpose:** Complete user profile with biometric enrollment status
**Key Features:**
- Multi-role support
- Biometric enrollment tracking
- Email/phone verification status
- Account status management

**Validation Rules:**
- Username must be unique and 3-50 characters
- Email format validation
- Phone number format validation (+233XXXXXXXXX)
- National ID format validation (GHA-XXXXXXXXX-X)

#### VehicleDto
**File Path:** `src/main/java/com/egov/common/dto/VehicleDto.java`
**Purpose:** Vehicle registration and licensing data
**Key Features:**
- Multiple driver support (up to 3)
- Insurance information tracking
- Road worthy certificate management
- Registration status tracking

**Validation Rules:**
- VIN must be 17 characters
- Registration number format validation
- Year must be between 1900 and current year + 1
- Insurance expiry date must be future date

#### IncidentDto
**File Path:** `src/main/java/com/egov/common/dto/IncidentDto.java`
**Purpose:** Emergency incident reporting and tracking
**Key Features:**
- Priority-based classification
- Officer assignment tracking
- Response time monitoring
- Attachment support

**Validation Rules:**
- Incident type must be valid enum value
- Priority level required
- Location coordinates required
- Reporter information required

### 2. Emergency Service DTOs
Located in: `src/main/java/com/egov/modules/emergency/dto/`

#### EmergencyRequestDto
**File Path:** `src/main/java/com/egov/modules/emergency/dto/EmergencyRequestDto.java`
**Purpose:** Real-time emergency service requests
**Key Features:**
- Multi-service type support (Police, Fire, Medical, Ambulance, SOS)
- Urgency level classification
- Real-time dispatch tracking
- Emergency contact management

**Validation Rules:**
- Request type must be valid enum
- Urgency level required
- Caller information required
- Location coordinates required

### 3. Government Service DTOs
Located in: `src/main/java/com/egov/modules/government/dto/`

#### LicenseDto
**File Path:** `src/main/java/com/egov/modules/government/dto/LicenseDto.java`
**Purpose:** Digital license management with QR codes
**Key Features:**
- Dynamic QR code generation (refreshes every 10 seconds)
- Multiple license types support
- Renewal history tracking
- Digital signature verification

**Validation Rules:**
- License number must be unique
- Issue date cannot be future date
- Expiry date must be after issue date
- License class must be valid for type

#### KycDto
**File Path:** `src/main/java/com/egov/modules/government/dto/KycDto.java`
**Purpose:** Identity verification with document validation
**Key Features:**
- Multi-level KYC (Basic, Intermediate, Enhanced)
- Document verification tracking
- Biometric verification integration
- Risk assessment scoring

**Validation Rules:**
- At least one identity document required
- Address verification for Enhanced KYC
- Biometric verification for Enhanced KYC
- Income verification for financial services

#### CreditScoreDto
**File Path:** `src/main/java/com/egov/modules/government/dto/CreditScoreDto.java`
**Purpose:** Financial credit assessment and scoring
**Key Features:**
- Credit score calculation (300-850 range)
- Scoring factor breakdown
- Credit history tracking
- Improvement recommendations

**Validation Rules:**
- Credit score must be between 300-850
- Assessment date cannot be future
- Customer information required
- Scoring factors must sum to 100%

#### SimRegistrationDto
**File Path:** `src/main/java/com/egov/modules/government/dto/SimRegistrationDto.java`
**Purpose:** Mobile SIM registration with KYC integration
**Key Features:**
- Network provider integration
- KYC verification requirement
- Biometric verification
- Compliance checking

**Validation Rules:**
- SIM number must be 19-20 digits
- Phone number format validation
- KYC verification required
- Biometric verification required

#### DigitalAddressDto
**File Path:** `src/main/java/com/egov/modules/government/dto/DigitalAddressDto.java`
**Purpose:** Digital address system workflow
**Key Features:**
- Ghana Post GPS integration
- Field verification workflow
- QR code generation
- Usage statistics tracking

**Validation Rules:**
- Physical address required
- Property type must be valid enum
- GPS coordinates required for approval
- Verification officer assignment for field verification

## DTO Relationships

### Primary Relationships
\`\`\`
UserDto (1) ←→ (N) VehicleDto
UserDto (1) ←→ (N) IncidentDto
UserDto (1) ←→ (1) KycDto
UserDto (1) ←→ (N) LicenseDto
UserDto (1) ←→ (N) SimRegistrationDto
UserDto (1) ←→ (N) DigitalAddressDto
AddressDto (1) ←→ (N) UserDto
AddressDto (1) ←→ (N) IncidentDto
AddressDto (1) ←→ (N) EmergencyRequestDto
\`\`\`

### Validation Annotations Used

#### Jakarta Bean Validation
- `@NotNull` - Field cannot be null
- `@NotBlank` - String field cannot be null or empty
- `@NotEmpty` - Collection cannot be null or empty
- `@Valid` - Cascade validation to nested objects
- `@Size(min, max)` - String/Collection size validation
- `@Min(value)` - Minimum numeric value
- `@Max(value)` - Maximum numeric value
- `@Email` - Email format validation
- `@Pattern(regexp)` - Regular expression validation
- `@Past` - Date must be in the past
- `@Future` - Date must be in the future
- `@DecimalMin` - Minimum decimal value
- `@DecimalMax` - Maximum decimal value

#### Custom Validation Annotations
- `@ValidPhoneNumber` - Ghana phone number format
- `@ValidNationalId` - Ghana national ID format
- `@ValidVin` - Vehicle identification number format
- `@ValidGpsCoordinates` - GPS coordinate validation
- `@ValidDigitalAddress` - Digital address code format

## Error Handling

### Validation Error Response Format
\`\`\`json
{
  "success": false,
  "message": "Validation failed",
  "errors": [
    {
      "field": "phoneNumber",
      "message": "Phone number must be in format +233XXXXXXXXX",
      "rejectedValue": "123456789"
    }
  ],
  "timestamp": "2024-01-15T10:30:00Z",
  "path": "/api/users"
}
\`\`\`

### Common Validation Errors
1. **Required Field Missing**: Field marked with @NotNull or @NotBlank is empty
2. **Format Validation**: Field doesn't match expected pattern (phone, email, etc.)
3. **Range Validation**: Numeric field outside allowed range
4. **Cross-Field Validation**: Business rule validation across multiple fields
5. **Unique Constraint**: Field value already exists in database

## Usage Examples

### Creating a User with Address
\`\`\`json
{
  "username": "john.doe",
  "email": "john.doe@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "phoneNumber": "+233123456789",
  "nationalId": "GHA-123456789-0",
  "address": {
    "street": "123 Main Street",
    "city": "Accra",
    "region": "Greater Accra",
    "country": "Ghana",
    "latitude": 5.6037,
    "longitude": -0.1870,
    "digitalAddressCode": "GA-123-4567"
  }
}
\`\`\`

### Reporting an Emergency Incident
\`\`\`json
{
  "type": "MEDICAL",
  "priority": "HIGH",
  "title": "Medical Emergency",
  "description": "Person collapsed on street",
  "location": {
    "street": "Oxford Street",
    "city": "Accra",
    "latitude": 5.6037,
    "longitude": -0.1870
  },
  "reportedBy": {
    "id": 123,
    "phoneNumber": "+233123456789"
  }
}
\`\`\`

## Best Practices

### DTO Design Guidelines
1. **Immutability**: Use final fields where possible
2. **Validation**: Always validate input data
3. **Documentation**: Provide clear field descriptions
4. **Versioning**: Consider API versioning for breaking changes
5. **Serialization**: Ensure proper JSON serialization/deserialization

### Performance Considerations
1. **Lazy Loading**: Use @JsonIgnore for heavy nested objects
2. **Projection**: Create specific DTOs for different use cases
3. **Caching**: Cache frequently accessed reference data
4. **Pagination**: Use pagination for large collections

### Security Considerations
1. **Sensitive Data**: Never expose passwords or tokens
2. **Data Masking**: Mask sensitive information in logs
3. **Input Sanitization**: Validate and sanitize all input
4. **Authorization**: Check permissions before data access

## Testing

### Unit Test Examples
\`\`\`java
@Test
void shouldValidateUserDto() {
    UserDto user = new UserDto();
    user.setUsername("john.doe");
    user.setEmail("invalid-email");
    
    Set<ConstraintViolation<UserDto>> violations = validator.validate(user);
    assertThat(violations).hasSize(1);
    assertThat(violations.iterator().next().getMessage())
        .contains("must be a well-formed email address");
}
\`\`\`

### Integration Test Examples
\`\`\`java
@Test
void shouldCreateUserWithValidData() throws Exception {
    UserDto userDto = createValidUserDto();
    
    mockMvc.perform(post("/api/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userDto)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.success").value(true));
}
\`\`\`

## Changelog

### Version 2.1.0 (Current)
- Added 10 new DTOs for enhanced functionality
- Improved validation annotations
- Added biometric integration support
- Enhanced address management with GPS coordinates

### Version 2.0.0
- Initial comprehensive DTO structure
- Basic validation implementation
- Core entity relationships established

## Support

For questions about DTOs or validation issues:
- Email: api-support@government.gov
- Documentation: https://api.emergency.gov/docs
- Issue Tracker: https://github.com/egov/backend/issues
