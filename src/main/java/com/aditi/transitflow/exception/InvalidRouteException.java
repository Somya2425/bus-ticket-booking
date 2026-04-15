package com.aditi.transitflow.exception;

/**
 * Exception thrown when an invalid route is provided.
 */
public class InvalidRouteException extends RuntimeException {
    /**
     * Constructs a new InvalidRouteException with the specified detail message.
     */
    public InvalidRouteException(String message) {
        super(message);
    }
}
