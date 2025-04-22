package com.match.service.mapper;

import org.junit.jupiter.api.Test;

import static com.match.service.data.TestMatchDTOData.createFirstMatchDTO;
import static com.match.service.data.TestMatchData.createFirstMatch;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MatchDTOMapperTest {

    @Test
    void map() {
        final var match = createFirstMatch();
        final var matchDTO = createFirstMatchDTO();

        final var result = MatchDTOMapper.map(match);

        assertThat(result)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(matchDTO);
    }
}