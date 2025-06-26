-- Create citizens table
CREATE TABLE citizens (
    citizen_id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    middle_name VARCHAR(50),
    phone_number VARCHAR(13) UNIQUE NOT NULL,
    email VARCHAR(100),
    encrypted_pin VARCHAR(255),
    biometric_enabled BOOLEAN NOT NULL DEFAULT false,
    date_of_birth DATE,
    gender VARCHAR(10),
    nationality VARCHAR(50) DEFAULT 'GHANAIAN',
    marital_status VARCHAR(20),
    occupation VARCHAR(100),
    emergency_contact_name VARCHAR(100),
    emergency_contact_phone VARCHAR(13),
    emergency_contact_relationship VARCHAR(50),
    blood_type VARCHAR(15),
    allergies TEXT,
    medical_conditions TEXT,
    emergency_medical_contact VARCHAR(200),
    preferred_language VARCHAR(10) NOT NULL DEFAULT 'ENGLISH',
    location_services_enabled BOOLEAN NOT NULL DEFAULT true,
    emergency_notifications_enabled BOOLEAN NOT NULL DEFAULT true,
    push_notifications_enabled BOOLEAN NOT NULL DEFAULT true,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING_VERIFICATION',
    phone_verified BOOLEAN NOT NULL DEFAULT false,
    email_verified BOOLEAN NOT NULL DEFAULT false,
    kyc_completed BOOLEAN NOT NULL DEFAULT false,
    kyc_completion_date TIMESTAMP,
    digital_address VARCHAR(20),
    address_verified BOOLEAN NOT NULL DEFAULT false,
    address_verification_date TIMESTAMP,
    last_used_device_id VARCHAR(100),
    last_login_date TIMESTAMP,
    last_used_app_version VARCHAR(50),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    created_by VARCHAR(50) DEFAULT 'SELF_REGISTRATION',
    updated_by VARCHAR(50)
);

-- Create emergency_reports table
CREATE TABLE emergency_reports (
    report_id BIGSERIAL PRIMARY KEY,
    citizen_id BIGINT NOT NULL REFERENCES citizens(citizen_id),
    emergency_type VARCHAR(50) NOT NULL,
    description TEXT,
    location_latitude NUMERIC(10,7) NOT NULL,
    location_longitude NUMERIC(10,7) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    priority VARCHAR(10) NOT NULL DEFAULT 'MEDIUM',
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    assigned_officer_id BIGINT,
    resolution_details TEXT
);

-- Create indexes
CREATE INDEX idx_citizen_phone ON citizens(phone_number);
CREATE INDEX idx_citizen_email ON citizens(email);
CREATE INDEX idx_citizen_status ON citizens(status);
CREATE INDEX idx_citizen_created ON citizens(created_at);
CREATE INDEX idx_emergency_citizen ON emergency_reports(citizen_id);
CREATE INDEX idx_emergency_status ON emergency_reports(status);
