package com.match.service.dto;

import com.match.service.enums.Sport;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * MatchDTO is a DTO that represents
 * the user input and used for transfer data
 * from Controller layer to Service layer.
 *
 * @author lioannidis
 * @version 0.1
 */
public record MatchDTO(

        Long id,

        @NotBlank(message = "Description is required")
        String description,

        @NotNull(message = "Date is required")
        LocalDate matchDate,

        @NotNull(message = "Time is required")
        LocalTime matchTime,

        @NotNull(message = "Team A is required")
        String teamA,

        @NotNull(message = "Team B is required")
        String teamB,

        @NotNull(message = "Sport is required")
        Sport sport,

        @NotNull(message = "Odds list is required")
        @Size(min = 1, message = "At least one odd must be provided")
        List<@Valid MatchOddsDTO> odds

) {
}