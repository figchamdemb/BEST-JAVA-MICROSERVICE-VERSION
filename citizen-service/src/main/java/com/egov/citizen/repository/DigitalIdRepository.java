package com.egov.citizen.repository;

import com.egov.citizen.entity.DigitalId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DigitalIdRepository extends JpaRepository<DigitalId, Long> {
    List<DigitalId> findByCitizen_CitizenId(Long citizenId);
    DigitalId findByIdNumber(String idNumber);
}
