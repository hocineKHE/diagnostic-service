package com.medtech.diagnostic.dto;

public record ErrorResponse(
        String code,
        String message
) {}
