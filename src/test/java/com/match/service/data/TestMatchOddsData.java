package com.match.service.data;

import com.match.service.domain.Match;
import com.match.service.domain.MatchOdds;
import com.match.service.enums.Specifier;

import java.math.BigDecimal;

import static com.match.service.data.TestMatchData.createFirstMatch;
import static com.match.service.data.TestMatchData.createSecondMatch;

public class TestMatchOddsData {

    public static MatchOdds createFirstMatchOdds() {
        return MatchOdds.builder()
                .withId(1L)
                .withOdd(new BigDecimal("1.05"))
                .withSpecifier(Specifier.X)
                .withMatch(Match.builder().withId(1L).build())
                .build();
    }

    public static MatchOdds createSecondMatchOdds() {
        return MatchOdds.builder()
                .withId(2L)
                .withOdd(new BigDecimal("2.10"))
                .withSpecifier(Specifier.X)
                .withMatch(Match.builder().withId(2L).build())
                .build();
    }
}
