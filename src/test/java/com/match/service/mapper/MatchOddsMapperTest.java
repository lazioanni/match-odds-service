package com.match.service.mapper;

import org.junit.jupiter.api.Test;

import static com.match.service.data.TestMatchOddsDTOData.createFirstMatchOddsDTO;
import static com.match.service.data.TestMatchOddsData.createFirstMatchOdds;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MatchOddsMapperTest {

    @Test
    void map() {
        final var matchOdds = createFirstMatchOdds();
        final var matchOddsDTO = createFirstMatchOddsDTO();

        final var result = MatchOddsMapper.map(matchOddsDTO);

        assertThat(result)
                .isNotNull()
                .usingRecursiveComparison()
                .ignoringFields("match", "id")
                .isEqualTo(matchOdds);
    }
}