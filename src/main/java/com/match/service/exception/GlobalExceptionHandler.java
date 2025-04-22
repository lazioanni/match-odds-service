package com.match.service.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Global exception handler for the Match Service application.
 * <p>
 *
 * @author lioannidis
 * @version 0.1
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles {@link MatchNotFoundException} when a match with the specified ID is not found.
     *
     * @param ex the thrown MatchNotFoundException.
     * @return a 404 NOT FOUND response with an error message.
     */
    @ExceptionHandler(MatchNotFoundException.class)
    public ResponseEntity<Object> handleMatchNotFound(MatchNotFoundException ex) {
        logger.warn("Match not found: {}", ex.getMessage());
        return new ResponseEntity<>(Map.of("message", ex.getMessage()), HttpStatus.NOT_FOUND);
    }


    /**
     * Handles {@link MatchOddNotFoundException} when odds for a match are not found.
     *
     * @param ex the thrown MatchOddNotFoundException.
     * @return a 404 NOT FOUND response with an error message.
     */
    @ExceptionHandler(MatchOddNotFoundException.class)
    public ResponseEntity<Object> handleMatchOddNotFound(MatchOddNotFoundException ex) {
        logger.warn("Match odds not found: {}", ex.getMessage());
        return new ResponseEntity<>(Map.of("message", ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles {@link HttpMessageNotReadableException} for invalid or unreadable JSON input.
     *
     * @param ex the thrown HttpMessageNotReadableException.
     * @return a 400 BAD REQUEST response indicating malformed JSON.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleInvalidJSON(HttpMessageNotReadableException ex) {
        logger.error("Malformed JSON request: {}", ex.getMessage());
        return new ResponseEntity<>(Map.of("message", "Malformed or invalid JSON"), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles {@link MethodArgumentNotValidException} for failed validation on request bodies.
     *
     * @param ex the thrown MethodArgumentNotValidException.
     * @return a 400 BAD REQUEST response with aggregated validation error messages.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(". ", "Validation errors: ", ""));

        logger.error("Validation failed: {}", errorMessage);

        return new ResponseEntity<>(Map.of("message", errorMessage.trim()), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles all other unexpected exceptions not specifically caught by other handlers.
     *
     * @param ex the thrown Exception.
     * @return a 500 INTERNAL SERVER ERROR response with a generic error message.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        logger.error("Internal server error: ", ex);
        return new ResponseEntity<>(Map.of("message", "An unexpected error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
