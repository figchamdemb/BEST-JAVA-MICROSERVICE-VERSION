package com.egov.citizen.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "citizens", indexes = {
    @Index(name = "idx_citizen_phone", columnList = "phoneNumber"),
    @Index(name = "idx_citizen_email", columnList = "email"),
    @Index(name = "idx_citizen_status", columnList = "status"),
    @Index(name = "idx_citizen_created", columnList = "createdAt")
})
public class Citizen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long citizenId;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(length = 50)
    private String middleName;

    @Column(unique = true, nullable = false, length = 13)
    private String phoneNumber;

    @Column(length = 100)
    private String email;

    @Column(length = 255)
    private String encryptedPin;

    @Column(nullable = false)
    private Boolean biometricEnabled = false;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;

    @Column(length = 50)
    private String nationality = "GHANAIAN";

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private MaritalStatus maritalStatus;

    @Column(length = 100)
    private String occupation;

    @Column(length = 100)
    private String emergencyContactName;

    @Column(length = 13)
    private String emergencyContactPhone;

    @Column(length = 50)
    private String emergencyContactRelationship;

    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private BloodType bloodType;

    @Column(length = 500)
    private String allergies;

    @Column(length = 500)
    private String medicalConditions;

    @Column(length = 200)
    private String emergencyMedicalContact;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Language preferredLanguage = Language.ENGLISH;

    @Column(nullable = false)
    private Boolean locationServicesEnabled = true;

    @Column(nullable = false)
    private Boolean emergencyNotificationsEnabled = true;

    @Column(nullable = false)
    private Boolean pushNotificationsEnabled = true;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CitizenStatus status = CitizenStatus.PENDING_VERIFICATION;

    @Column(nullable = false)
    private Boolean phoneVerified = false;

    @Column(nullable = false)
    private Boolean emailVerified = false;

    @Column(nullable = false)
    private Boolean kycCompleted = false;

    @Column(name = "kyc_completion_date")
    private LocalDateTime kycCompletionDate;

    @Column(length = 20)
    private String digitalAddress;

    @Column(name = "address_verified", nullable = false)
    private Boolean addressVerified = false;

    @Column(name = "address_verification_date")
    private LocalDateTime addressVerificationDate;

    @Column(length = 100)
    private String lastUsedDeviceId;

    @Column(name = "last_login_date")
    private LocalDateTime lastLoginDate;

    @Column(length = 50)
    private String lastUsedAppVersion;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(length = 50)
    private String createdBy = "SELF_REGISTRATION";

    @Column(length = 50)
    private String updatedBy;

    @OneToMany(mappedBy = "citizen", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EmergencyReport> emergencyReports = new ArrayList<>();

    // Enums
    public enum Gender {
        MALE, FEMALE, OTHER
    }

    public enum MaritalStatus {
        SINGLE, MARRIED, DIVORCED, WIDOWED, SEPARATED
    }

    public enum BloodType {
        A_POSITIVE, A_NEGATIVE, B_POSITIVE, B_NEGATIVE,
        AB_POSITIVE, AB_NEGATIVE, O_POSITIVE, O_NEGATIVE, UNKNOWN
    }

    public enum Language {
        ENGLISH, TWI, GA, EWE, HAUSA, DAGBANI, FANTE
    }

    public enum CitizenStatus {
        PENDING_VERIFICATION, VERIFIED, SUSPENDED, BLOCKED, INACTIVE
    }

    // Constructors
    public Citizen() {}

    public Citizen(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters
    public Long getCitizenId() { return citizenId; }
    public void setCitizenId(Long citizenId) { this.citizenId = citizenId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getMiddleName() { return middleName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getEncryptedPin() { return encryptedPin; }
    public void setEncryptedPin(String encryptedPin) { this.encryptedPin = encryptedPin; }

    public Boolean getBiometricEnabled() { return biometricEnabled; }
    public void setBiometricEnabled(Boolean biometricEnabled) { this.biometricEnabled = biometricEnabled; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public MaritalStatus getMaritalStatus() { return maritalStatus; }
    public void setMaritalStatus(MaritalStatus maritalStatus) { this.maritalStatus = maritalStatus; }

    public String getOccupation() { return occupation; }
    public void setOccupation(String occupation) { this.occupation = occupation; }

    public String getEmergencyContactName() { return emergencyContactName; }
    public void setEmergencyContactName(String emergencyContactName) { this.emergencyContactName = emergencyContactName; }

    public String getEmergencyContactPhone() { return emergencyContactPhone; }
    public void setEmergencyContactPhone(String emergencyContactPhone) { this.emergencyContactPhone = emergencyContactPhone; }

    public String getEmergencyContactRelationship() { return emergencyContactRelationship; }
    public void setEmergencyContactRelationship(String emergencyContactRelationship) { this.emergencyContactRelationship = emergencyContactRelationship; }

    public BloodType getBloodType() { return bloodType; }
    public void setBloodType(BloodType bloodType) { this.bloodType = bloodType; }

    public String getAllergies() { return allergies; }
    public void setAllergies(String allergies) { this.allergies = allergies; }

    public String getMedicalConditions() { return medicalConditions; }
    public void setMedicalConditions(String medicalConditions) { this.medicalConditions = medicalConditions; }

    public String getEmergencyMedicalContact() { return emergencyMedicalContact; }
    public void setEmergencyMedicalContact(String emergencyMedicalContact) { this.emergencyMedicalContact = emergencyMedicalContact; }

    public Language getPreferredLanguage() { return preferredLanguage; }
    public void setPreferredLanguage(Language preferredLanguage) { this.preferredLanguage = preferredLanguage; }

    public Boolean getLocationServicesEnabled() { return locationServicesEnabled; }
    public void setLocationServicesEnabled(Boolean locationServicesEnabled) { this.locationServicesEnabled = locationServicesEnabled; }

    public Boolean getEmergencyNotificationsEnabled() { return emergencyNotificationsEnabled; }
    public void setEmergencyNotificationsEnabled(Boolean emergencyNotificationsEnabled) { this.emergencyNotificationsEnabled = emergencyNotificationsEnabled; }

    public Boolean getPushNotificationsEnabled() { return pushNotificationsEnabled; }
    public void setPushNotificationsEnabled(Boolean pushNotificationsEnabled) { this.pushNotificationsEnabled = pushNotificationsEnabled; }

    public CitizenStatus getStatus() { return status; }
    public void setStatus(CitizenStatus status) { this.status = status; }

    public Boolean getPhoneVerified() { return phoneVerified; }
    public void setPhoneVerified(Boolean phoneVerified) { this.phoneVerified = phoneVerified; }

    public Boolean getEmailVerified() { return emailVerified; }
    public void setEmailVerified(Boolean emailVerified) { this.emailVerified = emailVerified; }

    public Boolean getKycCompleted() { return kycCompleted; }
    public void setKycCompleted(Boolean kycCompleted) { this.kycCompleted = kycCompleted; }

    public LocalDateTime getKycCompletionDate() { return kycCompletionDate; }
    public void setKycCompletionDate(LocalDateTime kycCompletionDate) { this.kycCompletionDate = kycCompletionDate; }

    public String getDigitalAddress() { return digitalAddress; }
    public void setDigitalAddress(String digitalAddress) { this.digitalAddress = digitalAddress; }

    public Boolean getAddressVerified() { return addressVerified; }
    public void setAddressVerified(Boolean addressVerified) { this.addressVerified = addressVerified; }

    public LocalDateTime getAddressVerificationDate() { return addressVerificationDate; }
    public void setAddressVerificationDate(LocalDateTime addressVerificationDate) { this.addressVerificationDate = addressVerificationDate; }

    public String getLastUsedDeviceId() { return lastUsedDeviceId; }
    public void setLastUsedDeviceId(String lastUsedDeviceId) { this.lastUsedDeviceId = lastUsedDeviceId; }

    public LocalDateTime getLastLoginDate() { return lastLoginDate; }
    public void setLastLoginDate(LocalDateTime lastLoginDate) { this.lastLoginDate = lastLoginDate; }

    public String getLastUsedAppVersion() { return lastUsedAppVersion; }
    public void setLastUsedAppVersion(String lastUsedAppVersion) { this.lastUsedAppVersion = lastUsedAppVersion; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    public String getUpdatedBy() { return updatedBy; }
    public void setUpdatedBy(String updatedBy) { this.updatedBy = updatedBy; }

    public List<EmergencyReport> getEmergencyReports() { return emergencyReports; }
    public void setEmergencyReports(List<EmergencyReport> emergencyReports) { this.emergencyReports = emergencyReports; }

    // Utility Methods
    public String getFullName() {
        StringBuilder fullName = new StringBuilder(firstName);
        if (middleName != null && !middleName.trim().isEmpty()) {
            fullName.append(" ").append(middleName);
        }
        fullName.append(" ").append(lastName);
        return fullName.toString();
    }

    public boolean isFullyVerified() {
        return phoneVerified && kycCompleted && addressVerified;
    }

    public boolean canUseEmergencyServices() {
        return phoneVerified && (status == CitizenStatus.VERIFIED || status == CitizenStatus.PENDING_VERIFICATION);
    }

    @Override
    public String toString() {
        return "Citizen{" +
                "citizenId=" + citizenId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", status=" + status +
                '}';
    }
}
