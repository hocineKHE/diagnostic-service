package com.medtech.diagnostic.dto;

public record DiagnosticResponse(
        int healthIndex,
        String medicalUnits
) {}
