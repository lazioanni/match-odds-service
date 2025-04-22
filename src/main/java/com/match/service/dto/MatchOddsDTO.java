package com.match.service.dto;

import com.match.service.enums.Specifier;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * MatchOddsDTO is a DTO that represents
 * the user input and used for transfer data
 * from Controller layer to Service layer.
 *
 * @author lioannidis
 * @version 0.1
 */
public record MatchOddsDTO(
        Long id,
        @NotNull(message = "Specifier is required")
        Specifier specifier,

        @NotNull(message = "Odd value is required")
        @DecimalMin(value = "1.0", message = "Odd value must be at least 1.0")
        BigDecimal odd,

        @NotNull(message = "Match ID is required")
        Long matchId
) {
}