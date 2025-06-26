package com.egov.citizen.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "digital_ids")
public class DigitalId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "citizen_id", nullable = false)
    private Citizen citizen;
    
    @Enumerated(EnumType.STRING)
    private IdType idType;
    
    private String idNumber;
    private String qrCode;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private boolean active;
    
    public enum IdType {
        DRIVERS_LICENSE, NATIONAL_ID, PASSPORT, VOTER_ID
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Citizen getCitizen() { return citizen; }
    public void setCitizen(Citizen citizen) { this.citizen = citizen; }
    public IdType getIdType() { return idType; }
    public void setIdType(IdType idType) { this.idType = idType; }
    public String getIdNumber() { return idNumber; }
    public void setIdNumber(String idNumber) { this.idNumber = idNumber; }
    public String getQrCode() { return qrCode; }
    public void setQrCode(String qrCode) { this.qrCode = qrCode; }
    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
