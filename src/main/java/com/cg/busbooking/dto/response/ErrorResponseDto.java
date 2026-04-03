package com.cg.busbooking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) for representing standardized error responses.
 *
 * This DTO is used by the global exception handler to return structured
 * error information to the client when an exception occurs.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {

    /**
     * The API endpoint path where the error occurred.
     */
    private String path;

    /**
     * HTTP status code representing the error.
     */
    private int status;

    /**
     * Detailed error information (e.g., validation errors or exception details).
     */
    private Object error;

    /**
     * Human-readable message describing the error.
     */
    private String message;

    /**
     * Timestamp when the error occurred.
     */
    private LocalDateTime timestamp;
}