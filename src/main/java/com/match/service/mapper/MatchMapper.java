package com.match.service.mapper;

import com.match.service.domain.Match;
import com.match.service.dto.MatchDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <h1>MatchMapper</h1>
 *
 * Mapper for Match.
 *
 * @author lioannidis
 * @version 0.1
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchMapper {
    /**
     * map
     *
     * @param matchDTO {@link MatchDTO}
     * @return a {@link Match}
     * @desc Maps from MatchDTO to Match
     */
    public static Match map(MatchDTO matchDTO) {
        return Match.builder()
                .withDescription(matchDTO.description())
                .withMatchDate(matchDTO.matchDate())
                .withMatchTime(matchDTO.matchTime())
                .withTeamA(matchDTO.teamA())
                .withTeamB(matchDTO.teamB())
                .withSport(matchDTO.sport())
                .build();
    }
}