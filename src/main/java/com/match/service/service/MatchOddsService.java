package com.match.service.service;

import com.match.service.dto.MatchOddsDTO;
import com.match.service.exception.MatchOddNotFoundException;


import java.util.List;
import java.util.Optional;

/**
 * <h1>MatchOddsService</h1>
 * <p>
 * Service for Match odds.
 *
 * @author lioannidis
 * @version 0.1
 */
public interface MatchOddsService {

    /**
     * Retrieves all match odds.
     *
     * @return a list of {@link MatchOddsDTO} for all match odds.
     */
    List<MatchOddsDTO> getAllOdds();

    /**
     * Retrieves an odd by its unique ID.
     *
     * @param id the ID of the odd to retrieve
     * @return an {@link Optional} with {@link MatchOddsDTO} if  match exists, or empty if not exists.
     */
    Optional<MatchOddsDTO> getOddsById(Long id);

    /**
     * Creates a new match odd.
     *
     * @param matchOddsDTO the match odd data to create
     * @return the created {@link MatchOddsDTO}
     */
    MatchOddsDTO createOdds(MatchOddsDTO matchOddsDTO);

    /**
     * Updates an existing match odd with the given ID using the provided data.
     *
     * @param id           the ID of the match odd to update
     * @param matchOddsDTO the updated match odd data
     * @return an {@link Optional} containing the updated {@link MatchOddsDTO} if the match odd exists,
     * or empty if no match was found with the given ID
     * @throws MatchOddNotFoundException if no match odd is found with the given ID
     */
    MatchOddsDTO updateOdds(Long id, MatchOddsDTO matchOddsDTO);

    /**
     * Deletes the match odd with the specified ID.
     *
     * @param id the ID of the match odd to delete
     * @throws MatchOddNotFoundException if no match odd is found with the given ID
     */
    void deleteOdds(Long id);
}