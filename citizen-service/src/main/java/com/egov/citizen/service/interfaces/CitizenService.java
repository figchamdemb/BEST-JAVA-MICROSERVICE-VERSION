package com.egov.citizen.service.interfaces;

import com.egov.citizen.entity.Citizen;
import java.util.Optional;

public interface CitizenService {
    Citizen registerCitizen(Citizen citizen);
    Optional<Citizen> findCitizenById(Long id);
    Optional<Citizen> findCitizenByPhoneNumber(String phoneNumber);
    Citizen updateCitizen(Citizen citizen);
    void verifyPhoneNumber(String phoneNumber);
    void verifyEmail(String email);
    boolean isPhoneNumberAvailable(String phoneNumber);
    boolean isEmailAvailable(String email);
}
