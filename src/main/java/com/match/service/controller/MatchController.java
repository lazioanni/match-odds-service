package com.match.service.controller;

import com.match.service.dto.MatchDTO;
import com.match.service.exception.MatchNotFoundException;
import com.match.service.service.MatchService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class that handles HTTP requests.
 *
 * @author lioannidis
 * @version 0.1
 */
@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private static final Logger logger = LoggerFactory.getLogger(MatchController.class);
    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    /**
     * Retrieves a list of all matches.
     *
     * @return a {@link ResponseEntity} containing a list of MatchDTOs.
     */
    @GetMapping
    public ResponseEntity<List<MatchDTO>> getAllMatches() {
        logger.info("GET - Retrieve all matches");
        return ResponseEntity.ok(matchService.getAllMatches());
    }

    /**
     * Retrieves a specific match by its ID.
     *
     * @param id the ID of the match to retrieve.
     * @return a {@link ResponseEntity} containing the MatchDTO.
     * @throws MatchNotFoundException if the match is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MatchDTO> getMatchById(@PathVariable Long id) {
        logger.info("GET - Retrieve Match by id: {}", id);
        MatchDTO matchDTO = matchService.getMatchById(id)
                .orElseThrow(() -> new MatchNotFoundException("Match with ID " + id + " not found"));
        return ResponseEntity.ok(matchDTO);
    }

    /**
     * Creates a new match.
     *
     * @param matchDTO the MatchDTO containing match details.
     * @return a {@link ResponseEntity} containing the created MatchDTO.
     */
    @PostMapping
    public ResponseEntity<MatchDTO> createMatch(@Valid @RequestBody MatchDTO matchDTO) {
        logger.info("POST - Create match request received");
        return ResponseEntity.ok(matchService.createMatch(matchDTO));
    }

    /**
     * Updates an existing match by its ID.
     *
     * @param id       the ID of the match to update.
     * @param matchDTO the updated match details.
     * @return a {@link ResponseEntity} containing the updated MatchDTO.
     * @throws MatchNotFoundException if the match is not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<MatchDTO> updateMatch(@PathVariable Long id, @Valid @RequestBody MatchDTO matchDTO) {
        logger.info("PUT - Update match request received by id {}", id);
        return matchService.updateMatch(id, matchDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new MatchNotFoundException("Match with ID " + id + " not found"));
    }

    /**
     * Deletes a match by its ID.
     *
     * @param id the ID of the match to delete.
     * @return a {@link ResponseEntity} with no content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        logger.info("DELETE - Delete match request received by id {}", id);
        matchService.deleteMatch(id);
        return ResponseEntity.noContent().build();
    }
}
