package com.match.service.mapper;

import org.junit.jupiter.api.Test;

import static com.match.service.data.TestMatchOddsDTOData.createFirstMatchOddsDTO;
import static com.match.service.data.TestMatchOddsData.createFirstMatchOdds;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MatchOddsDTOMapperTest {

    @Test
    void map() {
        final var matchOdds = createFirstMatchOdds();
        final var matchOddsDTO = createFirstMatchOddsDTO();

        final var result = MatchOddsDTOMapper.map(matchOdds);

        assertThat(result)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(matchOddsDTO);
    }
}