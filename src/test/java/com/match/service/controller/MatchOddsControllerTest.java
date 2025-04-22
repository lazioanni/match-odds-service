package com.match.service.controller;

import com.match.service.exception.MatchOddNotFoundException;
import com.match.service.service.MatchOddsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.match.service.data.TestMatchOddsDTOData.createFirstMatchOddsDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MatchOddsController.class)
class MatchOddsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MatchOddsService matchOddsService;


    @Test
    void testGetAllOdds_thenSuccess() throws Exception {
        final var oddsList = List.of(createFirstMatchOddsDTO());

        when(matchOddsService.getAllOdds()).thenReturn(oddsList);

        mockMvc.perform(get("/api/odds"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].specifier").value("X"))
                .andExpect(jsonPath("$[0].odd").value(1.05));
    }


    @Test
    void testGetAllOdds_whenOddsNotFound_thenEmptyList() throws Exception {
        when(matchOddsService.getAllOdds()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/odds"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void testGetOddsById_whenOddsExist_thenSuccess() throws Exception {
        final var matchOddsDTO = createFirstMatchOddsDTO();
        when(matchOddsService.getOddsById(1L)).thenReturn(Optional.of(matchOddsDTO));

        mockMvc.perform(get("/api/odds/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.specifier").value("X"))
                .andExpect(jsonPath("$.odd").value(1.05));
    }

    @Test
    void testGetOddsById_whenOddsNotFound_thenException() throws Exception {
        when(matchOddsService.getOddsById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/odds/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Match odds with ID 999 not found"));
    }

    @Test
    void testCreateOdds_whenValidData_thenSuccess() throws Exception {
        final var matchOddsDTOJson = """
                {
                    "id": null,
                    "specifier": "X",
                    "odd": 2.0,
                    "matchId": 1
                }
                """;

        final var matchOddsDTO = createFirstMatchOddsDTO();
        when(matchOddsService.createOdds(any())).thenReturn(matchOddsDTO);

        mockMvc.perform(post("/api/odds")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(matchOddsDTOJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.specifier").value("X"))
                .andExpect(jsonPath("$.odd").value(1.05))
                .andExpect(jsonPath("$.matchId").value(1));
    }

    @Test
    void testCreateOdds_whenInvalidData_thenBadRequest() throws Exception {
        final var matchOddsDTOJson = """
                {
                    "id": null,
                    "specifier": "X",
                    "odd": -1.0,
                    "matchId": 1
                }
                """;

        mockMvc.perform(post("/api/odds")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(matchOddsDTOJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation errors: Odd value must be at least 1.0"));
    }

    @Test
    void testUpdateOdds_whenValidData_thenSuccess() throws Exception {
        final var matchOddsDTOJson = """
                {
                    "id": 1,
                    "specifier": "X",
                    "odd": 2.5,
                    "matchId": 1
                }
                """;

        final var matchOddsDTO = createFirstMatchOddsDTO();
        when(matchOddsService.updateOdds(any(), any())).thenReturn(matchOddsDTO);

        mockMvc.perform(put("/api/odds/1", 123L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(matchOddsDTOJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.specifier").value("X"))
                .andExpect(jsonPath("$.odd").value(1.05));
    }


    @Test
    void testDeleteOdds_whenOddsExist_thenNoContent() throws Exception {
        doNothing().when(matchOddsService).deleteOdds(1L);

        mockMvc.perform(delete("/api/odds/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteOdds_whenOddsNotFound_thenNotFound() throws Exception {
        doThrow(new MatchOddNotFoundException("Match odds with ID 999 not found")).when(matchOddsService).deleteOdds(999L);

        mockMvc.perform(delete("/api/odds/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Match odds with ID 999 not found"));
    }
}