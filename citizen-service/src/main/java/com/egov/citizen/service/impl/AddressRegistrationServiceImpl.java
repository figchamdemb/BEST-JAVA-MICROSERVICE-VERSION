package com.egov.citizen.service.impl;

import com.egov.citizen.dto.request.AddressRegistrationRequest;
import com.egov.citizen.dto.response.AddressRegistrationResponse;
import com.egov.citizen.entity.AddressRegistration;
import com.egov.citizen.entity.Citizen;
import com.egov.citizen.exception.AddressRegistrationException;
import com.egov.citizen.integration.GpsTrackingService;
import com.egov.citizen.model.GpsData;
import com.egov.citizen.repository.AddressRegistrationRepository;
import com.egov.citizen.service.interfaces.AddressRegistrationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AddressRegistrationServiceImpl implements AddressRegistrationService {

    private final AddressRegistrationRepository addressRegistrationRepository;
    private final GpsTrackingService gpsTrackingService;

    public AddressRegistrationServiceImpl(
        AddressRegistrationRepository addressRegistrationRepository,
        GpsTrackingService gpsTrackingService
    ) {
        this.addressRegistrationRepository = addressRegistrationRepository;
        this.gpsTrackingService = gpsTrackingService;
    }

    @Override
    public AddressRegistrationResponse registerAddress(AddressRegistrationRequest request) {
        AddressRegistration address = new AddressRegistration();
        mapRequestToEntity(request, address);
        address.setVerificationStatus("TRACKING_IN_PROGRESS");
        address.setCreatedAt(LocalDateTime.now());
        address.setTrackingStartDate(LocalDateTime.now());
        address.setTrackingEndDate(LocalDateTime.now().plusDays(30));
        address.setVerificationScore(0);
        
        // Start GPS tracking process
        startGpsTracking(address);
        
        AddressRegistration savedAddress = addressRegistrationRepository.save(address);
        return mapEntityToResponse(savedAddress);
    }

    private void startGpsTracking(AddressRegistration address) {
        // Implementation would integrate with GPS tracking service
        // This would setup daily location checks and scoring
    }

    @Override
    public AddressRegistrationResponse updateAddress(Long id, AddressRegistrationRequest request) {
        AddressRegistration address = addressRegistrationRepository.findById(id)
            .orElseThrow(() -> new AddressRegistrationException("Address not found with id: " + id));
        
        mapRequestToEntity(request, address);
        address.setUpdatedAt(LocalDateTime.now());
        
        AddressRegistration updatedAddress = addressRegistrationRepository.save(address);
        return mapEntityToResponse(updatedAddress);
    }

    @Override
    public AddressRegistrationResponse verifyAddress(Long id, String verificationStatus, String notes) {
        AddressRegistration address = addressRegistrationRepository.findById(id)
            .orElseThrow(() -> new AddressRegistrationException("Address not found with id: " + id));
        
        // Only allow verification if tracking is complete
        if (!"TRACKING_COMPLETE".equals(address.getVerificationStatus())) {
            throw new AddressRegistrationException("Cannot verify address - tracking not complete");
        }

        // Minimum score requirement
        if ("APPROVED".equals(verificationStatus) && address.getVerificationScore() < 80) {
            throw new AddressRegistrationException("Verification score too low for approval");
        }
        
        address.setVerificationStatus(verificationStatus);
        address.setVerificationNotes(notes);
        address.setVerificationDate(LocalDateTime.now());
        address.setUpdatedAt(LocalDateTime.now());
        
        AddressRegistration verifiedAddress = addressRegistrationRepository.save(address);
        return mapEntityToResponse(verifiedAddress);
    }

    @Scheduled(cron = "0 0 6 * * ?") // Runs daily at 6 AM
    public void processDailyTracking() {
        List<AddressRegistration> trackingAddresses = addressRegistrationRepository
            .findByVerificationStatus("TRACKING_IN_PROGRESS");
            
        trackingAddresses.forEach(address -> {
            // Get GPS data for the past 24 hours
            List<GpsData> dailyData = gpsTrackingService.getDailyData(address.getId());
            
            // Update verification score
            int dailyScore = calculateDailyScore(dailyData, address);
            address.setVerificationScore(
                Math.min(100, address.getVerificationScore() + dailyScore));
                
            // Check if tracking period is complete
            if (LocalDateTime.now().isAfter(address.getTrackingEndDate())) {
                address.setVerificationStatus("TRACKING_COMPLETE");
                if (address.getVerificationScore() >= 80) {
                    address.setVerificationStatus("AUTO_APPROVED");
                }
            }
            
            addressRegistrationRepository.save(address);
        });
    }

    private int calculateDailyScore(List<GpsData> dailyData, AddressRegistration address) {
        // Implementation would analyze GPS data points
        // and return a score (0-10) based on presence at claimed address
        return dailyData.stream()
            .filter(data -> isAtClaimedAddress(data, address))
            .count() > 0 ? 10 : 0;
    }

    private boolean isAtClaimedAddress(GpsData data, AddressRegistration address) {
        // Implementation would check if GPS coordinates match claimed address
        // within allowed radius (e.g. 100 meters)
        return true; // Simplified for example
    }

    @Override
    public List<AddressRegistrationResponse> getAddressesByCitizen(Citizen citizen) {
        return addressRegistrationRepository.findByCitizen(citizen).stream()
            .map(this::mapEntityToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<AddressRegistrationResponse> getAddressesByVerificationStatus(String status) {
        return addressRegistrationRepository.findByVerificationStatus(status).stream()
            .map(this::mapEntityToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public AddressRegistrationResponse getAddressById(Long id) {
        AddressRegistration address = addressRegistrationRepository.findById(id)
            .orElseThrow(() -> new AddressRegistrationException("Address not found with id: " + id));
        return mapEntityToResponse(address);
    }

    @Override
    public void deleteAddress(Long id) {
        addressRegistrationRepository.deleteById(id);
    }

    @Override
    public long countAddressesByCitizen(Citizen citizen) {
        return addressRegistrationRepository.countByCitizen(citizen);
    }

    @Override
    public long countAddressesByVerificationStatus(String status) {
        return addressRegistrationRepository.countByVerificationStatus(status);
    }

    private void mapRequestToEntity(AddressRegistrationRequest request, AddressRegistration entity) {
        entity.setFullAddress(request.getFullAddress());
        entity.setGpsLatitude(request.getGpsLatitude());
        entity.setGpsLongitude(request.getGpsLongitude());
        entity.setDigitalAddress(request.getDigitalAddress());
        entity.setNearestLandmark(request.getNearestLandmark());
        entity.setHouseType(request.getHouseType());
        entity.setOwnershipStatus(request.getOwnershipStatus());
    }

    private AddressRegistrationResponse mapEntityToResponse(AddressRegistration entity) {
        AddressRegistrationResponse response = new AddressRegistrationResponse();
        response.setId(entity.getId());
        response.setFullAddress(entity.getFullAddress());
        response.setGpsLatitude(entity.getGpsLatitude());
        response.setGpsLongitude(entity.getGpsLongitude());
        response.setDigitalAddress(entity.getDigitalAddress());
        response.setNearestLandmark(entity.getNearestLandmark());
        response.setHouseType(entity.getHouseType());
        response.setOwnershipStatus(entity.getOwnershipStatus());
        response.setVerificationStatus(entity.getVerificationStatus());
        response.setVerificationDate(entity.getVerificationDate());
        response.setVerificationOfficer(entity.getVerificationOfficer());
        response.setVerificationNotes(entity.getVerificationNotes());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
