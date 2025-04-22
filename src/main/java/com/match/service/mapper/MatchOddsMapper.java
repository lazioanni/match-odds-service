package com.match.service.mapper;

import com.match.service.domain.Match;
import com.match.service.domain.MatchOdds;
import com.match.service.dto.MatchOddsDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <h1>MatchOddsMapper</h1>
 *
 * Mapper for MatchOdds.
 *
 * @author lioannidis
 * @version 0.1
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchOddsMapper {
    /**
     * map
     *
     * @param matchOddsDTO {@link MatchOddsDTO}
     * @return a {@link MatchOdds}
     * @desc Maps from MatchOddsDTO to MatchOdds
     */
    public static MatchOdds map(MatchOddsDTO matchOddsDTO, Match match) {
        return MatchOdds.builder()
                .withSpecifier(matchOddsDTO.specifier())
                .withOdd(matchOddsDTO.odd())
                .withMatch(match)
                .build();
    }
}