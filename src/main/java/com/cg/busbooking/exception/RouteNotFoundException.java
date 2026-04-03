package com.cg.busbooking.exception;

/**
 * Exception thrown when a requested bus route is not found.
 */
public class RouteNotFoundException extends RuntimeException{
    /**
     * Constructs a new RouteNotFoundException with the specified detail message.
     */
    public RouteNotFoundException(String message){
        super(message);
    }
}
