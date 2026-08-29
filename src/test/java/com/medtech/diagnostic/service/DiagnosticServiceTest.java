package com.medtech.diagnostic.service;

import com.medtech.diagnostic.model.MedicalUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class DiagnosticServiceTest {

    private DiagnosticService diagnosticService;

    @BeforeEach
    void setUp() {
        diagnosticService = new DiagnosticService();
    }

    @Test
    void shouldReturnCardiologie_whenHealthIndexIsMultipleOf3() {
        List<MedicalUnit> result = diagnosticService.diagnose(33);
        assertThat(result).containsExactly(MedicalUnit.CARDIOLOGIE);
    }

    @Test
    void shouldReturnTraumatologie_whenHealthIndexIsMultipleOf5() {
        List<MedicalUnit> result = diagnosticService.diagnose(55);
        assertThat(result).containsExactly(MedicalUnit.TRAUMATOLOGIE);
    }

    @Test
    void shouldReturnBothUnits_whenHealthIndexIsMultipleOf15() {
        List<MedicalUnit> result = diagnosticService.diagnose(15);
        assertThat(result).containsExactlyInAnyOrder(
                MedicalUnit.CARDIOLOGIE,
                MedicalUnit.TRAUMATOLOGIE
        );
    }

    @Test
    void shouldReturnEmptyList_whenNoPathologyDetected() {
        List<MedicalUnit> result = diagnosticService.diagnose(7);
        assertThat(result).isEmpty();
    }

    @Test
    void shouldReturnCorrectString_whenMultipleOf3() {
        String result = diagnosticService.diagnoseAsString(33);
        assertThat(result).isEqualTo("CARDIOLOGIE");
    }

    @Test
    void shouldReturnCorrectString_whenMultipleOf5() {
        String result = diagnosticService.diagnoseAsString(55);
        assertThat(result).isEqualTo("TRAUMATOLOGIE");
    }

    @Test
    void shouldReturnBothUnitsAsString_whenMultipleOf15() {
        String result = diagnosticService.diagnoseAsString(15);
        assertThat(result).contains("CARDIOLOGIE");
        assertThat(result).contains("TRAUMATOLOGIE");
    }

    @Test
    void shouldReturnNoPathologyMessage_whenNoPathologyDetected() {
        String result = diagnosticService.diagnoseAsString(7);
        assertThat(result).isEqualTo("Aucune pathologie détectée");
    }

    @Test
    void shouldThrowException_whenHealthIndexIsZero() {
        assertThatThrownBy(() -> diagnosticService.diagnose(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("strictement positif");
    }

    @Test
    void shouldThrowException_whenHealthIndexIsNegative() {
        assertThatThrownBy(() -> diagnosticService.diagnose(-3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("strictement positif");
    }
}