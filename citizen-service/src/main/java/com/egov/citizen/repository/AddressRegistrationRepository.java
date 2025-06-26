package com.egov.citizen.repository;

import com.egov.citizen.entity.AddressRegistration;
import com.egov.citizen.entity.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRegistrationRepository extends JpaRepository<AddressRegistration, Long> {
    
    List<AddressRegistration> findByCitizen(Citizen citizen);
    
    List<AddressRegistration> findByVerificationStatus(String verificationStatus);
    
    List<AddressRegistration> findByCitizenAndVerificationStatus(Citizen citizen, String verificationStatus);
    
    long countByCitizen(Citizen citizen);
    
    long countByVerificationStatus(String verificationStatus);
}
