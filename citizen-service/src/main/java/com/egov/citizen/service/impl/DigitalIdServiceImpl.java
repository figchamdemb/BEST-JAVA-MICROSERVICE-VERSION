package com.egov.citizen.service.impl;

import com.egov.citizen.dto.request.DigitalIdRequest;
import com.egov.citizen.dto.response.DigitalIdResponse;
import com.egov.citizen.entity.DigitalId;
import com.egov.citizen.exception.DigitalIdException;
import com.egov.citizen.repository.DigitalIdRepository;
import com.egov.citizen.service.interfaces.DigitalIdService;
import com.egov.citizen.util.DigitalIdMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DigitalIdServiceImpl implements DigitalIdService {

    private final DigitalIdRepository digitalIdRepository;

    public DigitalIdServiceImpl(DigitalIdRepository digitalIdRepository) {
        this.digitalIdRepository = digitalIdRepository;
    }

    @Override
    public DigitalIdResponse createDigitalId(DigitalIdRequest request) {
        DigitalId digitalId = DigitalIdMapper.toEntity(request);
        digitalId = digitalIdRepository.save(digitalId);
        return DigitalIdMapper.toResponse(digitalId);
    }

    @Override
    public DigitalIdResponse getDigitalId(Long id) {
        return digitalIdRepository.findById(id)
            .map(DigitalIdMapper::toResponse)
            .orElseThrow(() -> new DigitalIdException("Digital ID not found"));
    }

    @Override
    public List<DigitalIdResponse> getDigitalIdsByCitizen(Long citizenId) {
        return digitalIdRepository.findByCitizen_CitizenId(citizenId).stream()
            .map(DigitalIdMapper::toResponse)
            .collect(Collectors.toList());
    }

    @Override
    public DigitalIdResponse updateDigitalId(Long id, DigitalIdRequest request) {
        DigitalId digitalId = digitalIdRepository.findById(id)
            .orElseThrow(() -> new DigitalIdException("Digital ID not found"));
        
        DigitalIdMapper.updateEntity(digitalId, request);
        digitalId = digitalIdRepository.save(digitalId);
        return DigitalIdMapper.toResponse(digitalId);
    }

    @Override
    public void deactivateDigitalId(Long id) {
        DigitalId digitalId = digitalIdRepository.findById(id)
            .orElseThrow(() -> new DigitalIdException("Digital ID not found"));
        digitalId.setActive(false);
        digitalIdRepository.save(digitalId);
    }

    @Override
    public DigitalIdResponse generateQrCode(Long id) {
        DigitalId digitalId = digitalIdRepository.findById(id)
            .orElseThrow(() -> new DigitalIdException("Digital ID not found"));
        // QR generation logic would go here
        digitalId.setQrCode("generated-qr-code-" + id);
        digitalId = digitalIdRepository.save(digitalId);
        return DigitalIdMapper.toResponse(digitalId);
    }
}
