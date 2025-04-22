package com.match.service.data;

import com.match.service.dto.MatchOddsDTO;
import com.match.service.enums.Specifier;

import java.math.BigDecimal;

public class TestMatchOddsDTOData {

    public static MatchOddsDTO createFirstMatchOddsDTO() {
        return new MatchOddsDTO(
                1L,
                Specifier.X,
                new BigDecimal("1.05"),
                1L
        );
    }

    public static MatchOddsDTO createSecondMatchOddsDTO() {
        return new MatchOddsDTO(
                2L,
                Specifier.X,
                new BigDecimal("2.10"),
                2L
        );
    }
}
