package com.egov.citizen.controller;

import com.egov.citizen.dto.request.AddressRegistrationRequest;
import com.egov.citizen.dto.response.AddressRegistrationResponse;
import com.egov.citizen.entity.Citizen;
import com.egov.citizen.service.interfaces.AddressRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citizen/addresses")
public class AddressRegistrationController {

    private final AddressRegistrationService addressRegistrationService;

    public AddressRegistrationController(AddressRegistrationService addressRegistrationService) {
        this.addressRegistrationService = addressRegistrationService;
    }

    @PostMapping
    public ResponseEntity<AddressRegistrationResponse> registerAddress(
            @RequestBody AddressRegistrationRequest request) {
        AddressRegistrationResponse response = addressRegistrationService.registerAddress(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressRegistrationResponse> updateAddress(
            @PathVariable Long id,
            @RequestBody AddressRegistrationRequest request) {
        AddressRegistrationResponse response = addressRegistrationService.updateAddress(id, request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/verify")
    public ResponseEntity<AddressRegistrationResponse> verifyAddress(
            @PathVariable Long id,
            @RequestParam String status,
            @RequestParam(required = false) String notes) {
        AddressRegistrationResponse response = addressRegistrationService.verifyAddress(id, status, notes);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/citizen/{citizenId}")
    public ResponseEntity<List<AddressRegistrationResponse>> getAddressesByCitizen(
            @PathVariable Long citizenId) {
        Citizen citizen = new Citizen();
        citizen.setCitizenId(citizenId);
        List<AddressRegistrationResponse> responses = addressRegistrationService.getAddressesByCitizen(citizen);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<AddressRegistrationResponse>> getAddressesByStatus(
            @PathVariable String status) {
        List<AddressRegistrationResponse> responses = addressRegistrationService.getAddressesByVerificationStatus(status);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressRegistrationResponse> getAddressById(
            @PathVariable Long id) {
        AddressRegistrationResponse response = addressRegistrationService.getAddressById(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(
            @PathVariable Long id) {
        addressRegistrationService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/citizen/{citizenId}/count")
    public ResponseEntity<Long> countAddressesByCitizen(
            @PathVariable Long citizenId) {
        Citizen citizen = new Citizen();
        citizen.setCitizenId(citizenId);
        long count = addressRegistrationService.countAddressesByCitizen(citizen);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/status/{status}/count")
    public ResponseEntity<Long> countAddressesByStatus(
            @PathVariable String status) {
        long count = addressRegistrationService.countAddressesByVerificationStatus(status);
        return ResponseEntity.ok(count);
    }
}
