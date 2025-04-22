package com.match.service.mapper;

import org.junit.jupiter.api.Test;

import static com.match.service.data.TestMatchDTOData.createFirstMatchDTO;
import static com.match.service.data.TestMatchData.createFirstMatch;
import static com.match.service.data.TestMatchOddsDTOData.createFirstMatchOddsDTO;
import static com.match.service.data.TestMatchOddsData.createFirstMatchOdds;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MatchMapperTest {

    @Test
    void map() {
        final var match = createFirstMatch();
        final var matchDTO = createFirstMatchDTO();

        final var result = MatchMapper.map(matchDTO);

        assertThat(result)
                .isNotNull()
                .usingRecursiveComparison()
                .ignoringFields("odds", "id")
                .isEqualTo(match);
    }
}