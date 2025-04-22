package com.match.service.service;

import com.match.service.domain.MatchOdds;
import com.match.service.dto.MatchOddsDTO;
import com.match.service.exception.MatchNotFoundException;
import com.match.service.exception.MatchOddNotFoundException;
import com.match.service.mapper.MatchOddsDTOMapper;
import com.match.service.mapper.MatchOddsMapper;
import com.match.service.repository.MatchOddsRepository;
import com.match.service.repository.MatchRepository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

import static com.match.service.mapper.MatchOddsDTOMapper.*;


/**
 * <h1>MatchOddsServiceImpl</h1>
 * <p>
 * Implementation class for {@link MatchOddsService}.
 *
 * @author lioannidis
 * @version 0.1
 */
@Service
public class MatchOddsServiceImpl implements MatchOddsService {

    private static final Logger logger = LoggerFactory.getLogger(MatchOddsServiceImpl.class);

    private final MatchOddsRepository oddsRepository;
    private final MatchRepository matchRepository;

    public MatchOddsServiceImpl(MatchOddsRepository oddsRepository, MatchRepository matchRepository) {
        this.oddsRepository = oddsRepository;
        this.matchRepository = matchRepository;
    }

    @Override
    public List<MatchOddsDTO> getAllOdds() {
        logger.info("Get all matches odds.");
        return oddsRepository.findAll()
                .stream()
                .map(MatchOddsDTOMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MatchOddsDTO> getOddsById(Long id) {
        logger.info("Get match odd with ID: {}", id);
        final var match = oddsRepository.findById(id)
                .orElseThrow(() ->
                        new MatchOddNotFoundException("Match odd with ID " + id + " not found"));
        return Optional.of(map(match));
    }

    @Override
    public MatchOddsDTO createOdds(MatchOddsDTO matchOddsDTO) {
        logger.info("Creating new odds for match ID: {}", matchOddsDTO.matchId());
        final var match = matchRepository.findById(matchOddsDTO.matchId())
                .orElseThrow(() -> new MatchNotFoundException("Match with ID " + matchOddsDTO.matchId() + " not found"));

        MatchOdds odds = MatchOddsMapper.map(matchOddsDTO);
        odds.setMatch(match);

        return map(oddsRepository.save(odds));
    }

    @Override
    public MatchOddsDTO updateOdds(Long id, MatchOddsDTO matchOddsDTO) {
        logger.info("Updating match odds with ID: {}", id);
        final var existing = oddsRepository.findById(id)
                .orElseThrow(() -> new MatchOddNotFoundException("MatchOdds with ID " + id + " not found"));

        existing.setOdd(matchOddsDTO.odd());
        existing.setSpecifier(matchOddsDTO.specifier());

        return map(oddsRepository.save(existing));
    }

    @Override
    public void deleteOdds(Long id) {
        logger.info("Deleting match odds with ID: {}", id);
        if (!oddsRepository.existsById(id)) {
            throw new MatchOddNotFoundException("MatchOdds with ID " + id + " not found");
        }
        oddsRepository.deleteById(id);
    }
}