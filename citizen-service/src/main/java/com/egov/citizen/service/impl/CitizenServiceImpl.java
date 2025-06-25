package com.egov.citizen.service.impl;

import com.egov.citizen.entity.Citizen;
import com.egov.citizen.repository.CitizenRepository;
import com.egov.citizen.service.interfaces.CitizenService;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CitizenServiceImpl implements CitizenService {

    private final CitizenRepository citizenRepository;

    public CitizenServiceImpl(CitizenRepository citizenRepository) {
        this.citizenRepository = citizenRepository;
    }

    @Override
    public Citizen registerCitizen(Citizen citizen) {
        return citizenRepository.save(citizen);
    }

    @Override
    public Optional<Citizen> findCitizenById(Long id) {
        return citizenRepository.findById(id);
    }

    @Override
    public Optional<Citizen> findCitizenByPhoneNumber(String phoneNumber) {
        return citizenRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Citizen updateCitizen(Citizen citizen) {
        return citizenRepository.save(citizen);
    }

    @Override
    public void verifyPhoneNumber(String phoneNumber) {
        citizenRepository.updatePhoneVerificationStatus(phoneNumber, true);
    }

    @Override
    public void verifyEmail(String email) {
        citizenRepository.updateEmailVerificationStatus(email, true);
    }

    @Override
    public boolean isPhoneNumberAvailable(String phoneNumber) {
        return !citizenRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public boolean isEmailAvailable(String email) {
        return !citizenRepository.existsByEmail(email);
    }
}
