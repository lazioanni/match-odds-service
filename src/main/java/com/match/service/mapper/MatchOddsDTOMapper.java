package com.match.service.mapper;

import com.match.service.domain.MatchOdds;
import com.match.service.dto.MatchOddsDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <h1>MatchOddsDTOMapper</h1>
 *
 * Mapper for MatchOddsDTO.
 *
 * @author lioannidis
 * @version 0.1
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchOddsDTOMapper {
    /**
     * map
     *
     * @param matchOdds {@link MatchOdds}
     * @return a {@link MatchOddsDTO}
     * @desc Maps from MatchOdds to MatchOddsDTO
     */
    public static MatchOddsDTO map(MatchOdds matchOdds) {
        return new MatchOddsDTO(
                matchOdds.getId(),
                matchOdds.getSpecifier(),
                matchOdds.getOdd(),
                matchOdds.getMatch().getId()
        );
    }
}