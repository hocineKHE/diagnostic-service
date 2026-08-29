package com.medtech.diagnostic.controller;

import com.medtech.diagnostic.dto.DiagnosticResponse;
import com.medtech.diagnostic.service.DiagnosticService;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/diagnostic")
@Validated
public class DiagnosticController {

    private final DiagnosticService diagnosticService;

    public DiagnosticController(DiagnosticService diagnosticService) {
        this.diagnosticService = diagnosticService;
    }

    @GetMapping
    public ResponseEntity<DiagnosticResponse> diagnose(
            @RequestParam @Positive(message = "L'index de santé doit être un entier strictement positif")
            int healthIndex) {

        String result = diagnosticService.diagnoseAsString(healthIndex);

        return ResponseEntity.ok(
                new DiagnosticResponse(healthIndex, result)
        );
    }
}
