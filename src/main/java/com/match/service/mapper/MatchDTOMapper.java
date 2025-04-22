package com.match.service.mapper;

import com.match.service.domain.Match;
import com.match.service.dto.MatchDTO;
import com.match.service.dto.MatchOddsDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;
import java.util.List;

/**
 * <h1>MatchDTOMapper</h1>
 *
 * Mapper for MatchDTO.
 *
 * @author lioannidis
 * @version 0.1
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchDTOMapper {
    /**
     * map
     *
     * @param match {@link Match}
     * @return a {@link MatchDTO}
     * @desc Maps from Match to MatchDTO
     */
    public static MatchDTO map(Match match) {
        List<MatchOddsDTO> odds = match.getOdds().stream()
                .map(MatchOddsDTOMapper::map)
                .collect(Collectors.toList());

        return new MatchDTO(
                match.getId(),
                match.getDescription(),
                match.getMatchDate(),
                match.getMatchTime(),
                match.getTeamA(),
                match.getTeamB(),
                match.getSport(),
                odds
        );
    }
}