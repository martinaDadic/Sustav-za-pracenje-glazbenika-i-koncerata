package hr.fer.spgk_backend.controllers;

import hr.fer.spgk_backend.dtos.ConcertSummaryDTO;
import hr.fer.spgk_backend.services.ConcertService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ConcertController.class)
public class ConcertControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ConcertService concertService;

    @Test
    void testIfGetAllReturns200OK() throws Exception {
        when(concertService.getAll()).thenReturn(List.of(
                new ConcertSummaryDTO(1L, "Rock Night", LocalDateTime.now(), "Croatia", "Jakov")
        ));

        mockMvc.perform(get("/api/concerts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Rock Night"));
    }

    @Test
    void testIfGetByIdReturnsNotFoundWhenConcertDoesNotExist() throws Exception {
        when(concertService.getById(1L))
                .thenThrow(new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND, "Concert not found"));

        mockMvc.perform(get("/api/concerts/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testIfDeleteReturns204() throws Exception {
        mockMvc.perform(delete("/api/concerts/1"))
                .andExpect(status().isNoContent());
    }
}
