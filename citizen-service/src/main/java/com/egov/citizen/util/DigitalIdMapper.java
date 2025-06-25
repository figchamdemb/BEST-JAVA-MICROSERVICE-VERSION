package com.egov.citizen.util;

import com.egov.citizen.dto.request.DigitalIdRequest;
import com.egov.citizen.dto.response.DigitalIdResponse;
import com.egov.citizen.entity.DigitalId;
public class DigitalIdMapper {
    public static DigitalId toEntity(DigitalIdRequest request) {
        DigitalId digitalId = new DigitalId();
        updateEntity(digitalId, request);
        return digitalId;
    }

    public static DigitalIdResponse toResponse(DigitalId digitalId) {
        DigitalIdResponse response = new DigitalIdResponse();
        response.setId(digitalId.getId());
        response.setIdType(digitalId.getIdType());
        response.setIdNumber(digitalId.getIdNumber());
        response.setQrCode(digitalId.getQrCode());
        response.setIssueDate(digitalId.getIssueDate());
        response.setExpiryDate(digitalId.getExpiryDate());
        response.setActive(digitalId.isActive());
        return response;
    }

    public static void updateEntity(DigitalId digitalId, DigitalIdRequest request) {
        digitalId.setIdType(request.getIdType());
        digitalId.setIdNumber(request.getIdNumber());
        digitalId.setIssueDate(request.getIssueDate());
        digitalId.setExpiryDate(request.getExpiryDate());
    }
}
