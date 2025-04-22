package com.match.service.exception;

/**
 * Custom exception class that is thrown when a match is not found in the system.
 *
 * @author lioannidis
 * @version 0.1
 */
public class MatchNotFoundException extends RuntimeException {
    /**
     * Constructs a new {@link MatchNotFoundException} with the specified message.
     *
     * @param message The message of the exception.
     */
    public MatchNotFoundException(String message) {
        super(message);
    }
}
