package com.egov.dto;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String middleName;
    private String status;
}
