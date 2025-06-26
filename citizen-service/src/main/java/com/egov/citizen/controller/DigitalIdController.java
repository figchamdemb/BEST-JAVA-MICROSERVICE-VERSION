package com.egov.citizen.controller;

import com.egov.citizen.dto.request.DigitalIdRequest;
import com.egov.citizen.dto.response.DigitalIdResponse;
import com.egov.citizen.service.interfaces.DigitalIdService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/citizen/digital-id")
public class DigitalIdController {
    private final DigitalIdService digitalIdService;

    public DigitalIdController(DigitalIdService digitalIdService) {
        this.digitalIdService = digitalIdService;
    }

    @PostMapping
    public ResponseEntity<DigitalIdResponse> createDigitalId(@RequestBody DigitalIdRequest request) {
        DigitalIdResponse response = digitalIdService.createDigitalId(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DigitalIdResponse> getDigitalId(@PathVariable Long id) {
        DigitalIdResponse response = digitalIdService.getDigitalId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/citizen/{citizenId}")
    public ResponseEntity<List<DigitalIdResponse>> getDigitalIdsByCitizen(@PathVariable Long citizenId) {
        List<DigitalIdResponse> responses = digitalIdService.getDigitalIdsByCitizen(citizenId);
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DigitalIdResponse> updateDigitalId(
            @PathVariable Long id,
            @RequestBody DigitalIdRequest request) {
        DigitalIdResponse response = digitalIdService.updateDigitalId(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateDigitalId(@PathVariable Long id) {
        digitalIdService.deactivateDigitalId(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/qr-code")
    public ResponseEntity<DigitalIdResponse> generateQrCode(@PathVariable Long id) {
        DigitalIdResponse response = digitalIdService.generateQrCode(id);
        return ResponseEntity.ok(response);
    }
}
