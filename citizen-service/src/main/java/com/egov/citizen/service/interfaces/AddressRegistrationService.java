package com.egov.citizen.service.interfaces;

import com.egov.citizen.entity.Citizen;
import com.egov.citizen.dto.request.AddressRegistrationRequest;
import com.egov.citizen.dto.response.AddressRegistrationResponse;

import java.util.List;

public interface AddressRegistrationService {
    AddressRegistrationResponse registerAddress(AddressRegistrationRequest request);
    AddressRegistrationResponse updateAddress(Long id, AddressRegistrationRequest request);
    AddressRegistrationResponse verifyAddress(Long id, String verificationStatus, String notes);
    List<AddressRegistrationResponse> getAddressesByCitizen(Citizen citizen);
    List<AddressRegistrationResponse> getAddressesByVerificationStatus(String status);
    AddressRegistrationResponse getAddressById(Long id);
    void deleteAddress(Long id);
    long countAddressesByCitizen(Citizen citizen);
    long countAddressesByVerificationStatus(String status);
}
