package com.medtech.diagnostic.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DiagnosticControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturn200WithCardiologie_whenHealthIndexIs33() throws Exception {
        mockMvc.perform(get("/api/diagnostic")
                        .param("healthIndex", "33"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.healthIndex").value(33))
                .andExpect(jsonPath("$.medicalUnits").value("CARDIOLOGIE"));
    }

    @Test
    void shouldReturn200WithTraumatologie_whenHealthIndexIs55() throws Exception {
        mockMvc.perform(get("/api/diagnostic")
                        .param("healthIndex", "55"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.healthIndex").value(55))
                .andExpect(jsonPath("$.medicalUnits").value("TRAUMATOLOGIE"));
    }

    @Test
    void shouldReturn200WithBothUnits_whenHealthIndexIs15() throws Exception {
        mockMvc.perform(get("/api/diagnostic")
                        .param("healthIndex", "15"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.healthIndex").value(15))
                .andExpect(jsonPath("$.medicalUnits").value(containsString("CARDIOLOGIE")))
                .andExpect(jsonPath("$.medicalUnits").value(containsString("TRAUMATOLOGIE")));
    }

    @Test
    void shouldReturn200WithNoPathology_whenHealthIndexIs7() throws Exception {
        mockMvc.perform(get("/api/diagnostic")
                        .param("healthIndex", "7"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.medicalUnits").value("Aucune pathologie détectée"));
    }

    @Test
    void shouldReturn400_whenHealthIndexIsNegative() throws Exception {
        mockMvc.perform(get("/api/diagnostic")
                        .param("healthIndex", "-1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400_whenHealthIndexIsZero() throws Exception {
        mockMvc.perform(get("/api/diagnostic")
                        .param("healthIndex", "0"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400_whenHealthIndexIsMissing() throws Exception {
        mockMvc.perform(get("/api/diagnostic"))
                .andExpect(status().isInternalServerError());
    }
}