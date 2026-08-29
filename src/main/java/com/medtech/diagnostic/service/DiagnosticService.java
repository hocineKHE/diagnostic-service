package com.medtech.diagnostic.service;

import com.medtech.diagnostic.model.MedicalUnit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiagnosticService {

    public List<MedicalUnit> diagnose(int healthIndex) {
        if (healthIndex <= 0) {
            throw new IllegalArgumentException(
                    "L'index de santé doit être un entier strictement positif"
            );
        }

        List<MedicalUnit> units = new ArrayList<>();

        if (healthIndex % 3 == 0) {
            units.add(MedicalUnit.CARDIOLOGIE);
        }
        if (healthIndex % 5 == 0) {
            units.add(MedicalUnit.TRAUMATOLOGIE);
        }

        return units;
    }

    public String diagnoseAsString(int healthIndex) {
        List<MedicalUnit> units = diagnose(healthIndex);

        if (units.isEmpty()) {
            return "Aucune pathologie détectée";
        }

        return units.stream()
                .map(MedicalUnit::name)
                .collect(java.util.stream.Collectors.joining(", "));
    }
}
