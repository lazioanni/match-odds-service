package com.match.service.service;


import com.match.service.dto.MatchDTO;
import com.match.service.exception.MatchNotFoundException;
import com.match.service.mapper.MatchDTOMapper;
import com.match.service.repository.MatchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.match.service.mapper.MatchDTOMapper.map;
import static com.match.service.mapper.MatchMapper.map;

/**
 * <h1>MatchOddsServiceImpl</h1>
 * <p>
 * Implementation class for {@link MatchOddsService}.
 *
 * @author lioannidis
 * @version 0.1
 */
@Service
public class MatchServiceImpl implements MatchService {

    private static final Logger logger = LoggerFactory.getLogger(MatchServiceImpl.class);

    private final MatchRepository matchRepository;

    public MatchServiceImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public List<MatchDTO> getAllMatches() {
        logger.info("Get all matches.");
        return matchRepository.findAll()
                .stream()
                .map(MatchDTOMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MatchDTO> getMatchById(Long id) {
        logger.info("Get match with ID: {}", id);
        final var match = matchRepository.findById(id)
                .orElseThrow(() ->
                        new MatchNotFoundException("Match with ID " + id + " not found"));
        return Optional.of(map(match));
    }

    @Override
    public MatchDTO createMatch(MatchDTO matchDTO) {
        logger.info("Create a new match: {}", matchDTO.description());
        final var saved = matchRepository.save(map(matchDTO));
        return map(saved);
    }

    @Override
    public Optional<MatchDTO> updateMatch(Long id, MatchDTO matchDTO) {
        logger.info("Updating match with ID: {}", id);

        final var existingMatch = matchRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Match with ID {} not found for update", id);
                    return new MatchNotFoundException("Match with ID " + id + " not found");
                });

        final var updated = map(matchDTO);
        updated.setId(existingMatch.getId());

        final var saved = matchRepository.save(updated);
        logger.info("Match with ID {} updated successfully", id);

        return Optional.of(map(saved));
    }

    @Override
    public void deleteMatch(Long id) {
        logger.info("Delete match with ID: {}", id);

        if (!matchRepository.existsById(id)) {
            logger.warn("Match with ID: {} not found.", id);
            throw new MatchNotFoundException("Match with ID " + id + " not found");
        }

        matchRepository.deleteById(id);
    }
}