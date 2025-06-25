package com.egov.citizen.dto.request;

import com.egov.citizen.entity.DigitalId.IdType;
import java.time.LocalDate;

public class DigitalIdRequest {
    private IdType idType;
    private String idNumber;
    private LocalDate issueDate;
    private LocalDate expiryDate;

    // Getters and setters
    public IdType getIdType() { return idType; }
    public void setIdType(IdType idType) { this.idType = idType; }
    public String getIdNumber() { return idNumber; }
    public void setIdNumber(String idNumber) { this.idNumber = idNumber; }
    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
}
