package com.match.service.data;

import com.match.service.domain.Match;
import com.match.service.enums.Sport;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.match.service.data.TestMatchOddsData.createFirstMatchOdds;
import static com.match.service.data.TestMatchOddsData.createSecondMatchOdds;

public class TestMatchData {
    public static Match createFirstMatch() {
        return Match.builder()
                .withId(1L)
                .withDescription("Big Athens Derby")
                .withMatchDate(LocalDate.of(2025, 4, 22))
                .withMatchTime(LocalTime.of(20,0))
                .withTeamA("OSFP")
                .withTeamB("PAO")
                .withOdds(List.of(createFirstMatchOdds()))
                .withSport(Sport.FOOTBALL)
                .build();
    }

    public static Match createSecondMatch() {
        return Match.builder()
                .withId(2L)
                .withDescription("Big Thessaloniki's Derby")
                .withMatchDate(LocalDate.of(2025, 4, 22))
                .withMatchTime(LocalTime.of(20,0))
                .withTeamA("PAOK")
                .withTeamB("ARHS")
                .withOdds(List.of(createSecondMatchOdds()))
                .withSport(Sport.FOOTBALL)
                .build();
    }
}
