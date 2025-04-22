package com.match.service.data;

import com.match.service.dto.MatchDTO;
import com.match.service.enums.Sport;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.match.service.data.TestMatchOddsDTOData.createFirstMatchOddsDTO;
import static com.match.service.data.TestMatchOddsDTOData.createSecondMatchOddsDTO;


public class TestMatchDTOData {

    public static MatchDTO createFirstMatchDTO() {
        return new MatchDTO(
                1L,
                "Big Athens Derby",
                LocalDate.of(2025, 4, 22),
                LocalTime.of(20, 0),
                "OSFP",
                "PAO",
                Sport.FOOTBALL,
                List.of(createFirstMatchOddsDTO())
        );
    }

    public static MatchDTO createSecondMatchDTO() {
        return new MatchDTO(
                2L,
                "Big Thessaloniki's Derby",
                LocalDate.of(2025, 4, 22),
                LocalTime.of(20, 0),
                "PAOK",
                "ARHS",
                Sport.FOOTBALL,
                List.of(createSecondMatchOddsDTO())
        );
    }

}
