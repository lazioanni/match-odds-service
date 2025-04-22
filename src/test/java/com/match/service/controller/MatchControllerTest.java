package com.match.service.controller;

import com.match.service.service.MatchService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.match.service.data.TestMatchDTOData.createFirstMatchDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MatchController.class)
class MatchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MatchService matchService;

    @Test
    public void testGetAllMatches_thenSuccess() throws Exception {
        final var matchDTOList = List.of(createFirstMatchDTO());
        when(matchService.getAllMatches()).thenReturn(matchDTOList);

        mockMvc.perform(get("/api/matches"))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].description").value("Big Athens Derby"))
                .andExpect(jsonPath("$[0].teamA").value("OSFP"))
                .andExpect(jsonPath("$[0].teamB").value("PAO"));
    }

    @Test
    public void testGetMatchById_thenSuccess() throws Exception {
        final var matchDTO = createFirstMatchDTO();
        when(matchService.getMatchById(1L)).thenReturn(Optional.of(matchDTO));

        mockMvc.perform(get("/api/matches/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.description").value("Big Athens Derby"))
                .andExpect(jsonPath("$.teamA").value("OSFP"))
                .andExpect(jsonPath("$.teamB").value("PAO"));
    }


    @Test
    public void testGetMatchById_whenIdDoesNotExist() throws Exception {
        when(matchService.getMatchById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/matches/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Match with ID 1 not found"));
    }

    @Test
    void testCreateMatch_thenSuccess() throws Exception {
        final var matchDTO = createFirstMatchDTO();
        when(matchService.createMatch(any())).thenReturn(matchDTO);

        final var matchDTOJson = """
                {
                    "id": 1,
                    "description": "Big Athens Derby",
                    "matchDate": "2025-04-25",
                    "matchTime": "18:30:00",
                    "teamA": "OSFP",
                    "teamB": "PAO",
                    "sport": "FOOTBALL",
                    "odds": [{
                        "id": null,
                        "specifier": "X",
                        "odd": 2.0,
                        "matchId": 1
                    }]
                }
                """;

        mockMvc.perform(post("/api/matches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(matchDTOJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.description").value("Big Athens Derby"));
    }


    @Test
    void testCreateMatch_whenTeamANotFound_thenMethodArgumentNotValidException() throws Exception {
        final var matchDTOJson = """
                {
                    "id": 1,
                    "description": "Big Athens Derby",
                    "matchDate": "2025-04-25",
                    "matchTime": "18:30:00",
                    "teamB": "PAO",
                    "sport": "FOOTBALL",
                    "odds": [{
                        "id": null,
                        "specifier": "X",
                        "odd": 2.0,
                        "matchId": 1
                    }]
                }
                """;

        mockMvc.perform(post("/api/matches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(matchDTOJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation errors: Team A is required"));
    }

    @Test
    void testUpdateMatch_thenSuccess() throws Exception {
        final var updatedMatchDTO = createFirstMatchDTO();

        when(matchService.updateMatch(any(), any())).thenReturn(Optional.of(updatedMatchDTO));

        final var matchDTOJson = """
                {
                    "id": 1,
                    "description": "Big Athens Derby - Updated",
                    "matchDate": "2025-05-10",
                    "matchTime": "19:00:00",
                    "teamA": "OSFP",
                    "teamB": "PAO",
                    "sport": "FOOTBALL",
                    "odds": [{
                        "id": null,
                        "specifier": "X",
                        "odd": 3.0,
                        "matchId": 1
                    }]
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.put("/api/matches/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(matchDTOJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.description").value("Big Athens Derby"))
                .andExpect(jsonPath("$.matchDate").value("2025-04-22"))
                .andExpect(jsonPath("$.teamA").value("OSFP"))
                .andExpect(jsonPath("$.teamB").value("PAO"));
    }


    @Test
    public void testUpdateMatch_whenMatchNotFound_thenMatchNotFoundException() throws Exception {
        String matchDTOJson = """
                {
                    "id": 1,
                    "description": "Updated Match",
                    "matchDate": "2025-04-25",
                    "matchTime": "18:30:00",
                    "teamA": "OSFP",
                    "teamB": "PAO",
                    "sport": "FOOTBALL",
                     "odds": [{
                        "id": null,
                        "specifier": "X",
                        "odd": 3.0,
                        "matchId": 1
                    }]
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.put("/api/matches/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(matchDTOJson))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Match with ID 1 not found"));
    }

    @Test
    void testDeleteMatch_thenSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/matches/1"))
                .andExpect(status().isNoContent());
    }


    @Test
    public void testMalformedJsonRequest_thenBadRequest() throws Exception {
        String malformedJson = "{ \"id\": 1, \"description\": \"Big Athens Derby\"";

        mockMvc.perform(post("/api/matches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(malformedJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"message\":\"Malformed or invalid JSON\"}"));
    }

}