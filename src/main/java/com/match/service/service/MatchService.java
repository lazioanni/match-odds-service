package com.match.service.service;

import com.match.service.dto.MatchDTO;
import com.match.service.exception.MatchNotFoundException;

import java.util.List;

import java.util.Optional;

/**
 * <h1>MatchService</h1>
 * <p>
 * Service for Match.
 *
 * @author lioannidis
 * @version 0.1
 */
public interface MatchService {

    /**
     * Retrieves all matches.
     *
     * @return a list of {@link MatchDTO} for all matches
     */
    List<MatchDTO> getAllMatches();

    /**
     * Retrieves a match by its unique ID.
     *
     * @param id the ID of the match to retrieve
     * @return an {@link Optional} with {@link MatchDTO} if  match exists, or empty if not exists.
     */
    Optional<MatchDTO> getMatchById(Long id);

    /**
     * Creates a new match.
     *
     * @param matchDTO the match data to create
     * @return the created {@link MatchDTO}
     */
    MatchDTO createMatch(MatchDTO matchDTO);

    /**
     * Updates an existing match with the given ID using the provided data.
     *
     * @param id       the ID of the match to update
     * @param matchDTO the updated match data
     * @return an {@link Optional} containing the updated {@link MatchDTO} if the match exists,
     * or empty if no match was found with the given ID
     * @throws MatchNotFoundException if no match is found with the given ID
     */
    Optional<MatchDTO> updateMatch(Long id, MatchDTO matchDTO);

    /**
     * Deletes the match with the specified ID.
     *
     * @param id the ID of the match to delete
     * @throws MatchNotFoundException if no match is found with the given ID
     */
    void deleteMatch(Long id);
}