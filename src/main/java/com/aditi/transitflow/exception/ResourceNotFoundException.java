package com.aditi.transitflow.exception;

/**
 * Exception thrown when a requested resource is not found.
 * Typically used when an entity does not exist in the system
 * for a given identifier.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructs a new ResourceNotFoundException with the specified message.
     *
     * @param message detailed error message describing the missing resource
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
