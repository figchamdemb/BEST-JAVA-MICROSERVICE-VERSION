package com.egov.dto;

import lombok.Data;
import java.util.List;

@Data
public class CreateUserRequest {
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private String userType;
    private String status;
    private String tenantId;
    private List<String> roles;
}
