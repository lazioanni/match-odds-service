package com.match.service.exception;

/**
 * Custom exception class that is thrown when a match is not found in the system.
 *
 * @author lioannidis
 * @version 0.1
 */
public class MatchOddNotFoundException extends RuntimeException {
    /**
     * Constructs a new {@link MatchOddNotFoundException} with the specified message.
     *
     * @param message The message of the exception.
     */
    public MatchOddNotFoundException(String message) {
        super(message);
    }
}
