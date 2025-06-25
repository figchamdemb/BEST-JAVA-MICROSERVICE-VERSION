package com.egov.citizen.repository;

import com.egov.citizen.entity.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Long> {
    Optional<Citizen> findByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);
    
    @Modifying
    @Query("UPDATE Citizen c SET c.phoneVerified = :verified WHERE c.phoneNumber = :phoneNumber")
    void updatePhoneVerificationStatus(@Param("phoneNumber") String phoneNumber, 
                                     @Param("verified") boolean verified);

    @Modifying
    @Query("UPDATE Citizen c SET c.emailVerified = :verified WHERE c.email = :email")
    void updateEmailVerificationStatus(@Param("email") String email, 
                                     @Param("verified") boolean verified);
}
