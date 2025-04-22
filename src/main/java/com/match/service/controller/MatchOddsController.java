package com.match.service.controller;

import com.match.service.dto.MatchOddsDTO;
import com.match.service.exception.MatchOddNotFoundException;
import com.match.service.service.MatchOddsService;
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
@RequestMapping("/api/odds")
public class MatchOddsController {

    private static final Logger logger = LoggerFactory.getLogger(MatchOddsController.class);
    private final MatchOddsService matchOddsService;

    public MatchOddsController(MatchOddsService matchOddsService) {
        this.matchOddsService = matchOddsService;
    }

    /**
     * Retrieves a list of all matchOdds.
     *
     * @return a {@link ResponseEntity} containing a list of MatchOddsDTOs.
     */
    @GetMapping
    public ResponseEntity<List<MatchOddsDTO>> getAllOdds() {
        logger.info("GET /api/odds - Retrieve all match odds");
        return ResponseEntity.ok(matchOddsService.getAllOdds());
    }

    /**
     * Retrieves a specific matchOdd by its ID.
     *
     * @param id the ID of the matchOdd to retrieve.
     * @return a {@link ResponseEntity} containing the MatchOddsDTO.
     * @throws MatchOddNotFoundException if the match is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MatchOddsDTO> getOddsById(@PathVariable Long id) {
        logger.info("GET /api/odds/{} - Fetch match odds", id);
        return matchOddsService.getOddsById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new MatchOddNotFoundException("Match odds with ID " + id + " not found"));
    }

    /**
     * Creates a new matchOdd.
     *
     * @param matchOddsDTO the MatchOddsDTO containing matchOdd details.
     * @return a {@link ResponseEntity} containing the created MatchOddsDTO.
     */
    @PostMapping
    public ResponseEntity<MatchOddsDTO> createOdds(@Valid @RequestBody MatchOddsDTO matchOddsDTO) {
        logger.info("POST /api/odds - Create odds for match ID: {}", matchOddsDTO.matchId());

        return ResponseEntity.ok(matchOddsService.createOdds(matchOddsDTO));
    }

    /**
     * Updates an existing matchOdd by its ID.
     *
     * @param id           the ID of the matchOdd to update.
     * @param matchOddsDTO the updated matchOdd details.
     * @return a {@link ResponseEntity} containing the updated MatchOddsDTO.
     * @throws MatchOddNotFoundException if the match is not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<MatchOddsDTO> updateOdds(@PathVariable Long id, @Valid @RequestBody MatchOddsDTO matchOddsDTO) {
        logger.info("PUT /api/odds/{} - Update match odds", id);
        return ResponseEntity.ok(matchOddsService.updateOdds(id, matchOddsDTO));
    }

    /**
     * Deletes a matchOdd by its ID.
     *
     * @param id the ID of the matchOdd to delete.
     * @return a {@link ResponseEntity} with no content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOdds(@PathVariable Long id) {
        logger.info("DELETE /api/odds/{} - Delete match odds", id);
        matchOddsService.deleteOdds(id);
        return ResponseEntity.noContent().build();
    }
}