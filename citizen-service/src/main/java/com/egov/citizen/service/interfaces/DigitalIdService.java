package com.egov.citizen.service.interfaces;

import com.egov.citizen.dto.request.DigitalIdRequest;
import com.egov.citizen.dto.response.DigitalIdResponse;
import java.util.List;

public interface DigitalIdService {
    DigitalIdResponse createDigitalId(DigitalIdRequest request);
    DigitalIdResponse getDigitalId(Long id);
    List<DigitalIdResponse> getDigitalIdsByCitizen(Long citizenId);
    DigitalIdResponse updateDigitalId(Long id, DigitalIdRequest request);
    void deactivateDigitalId(Long id);
    DigitalIdResponse generateQrCode(Long id);
}
