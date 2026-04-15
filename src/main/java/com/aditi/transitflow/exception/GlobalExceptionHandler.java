package com.aditi.transitflow.exception;

import com.aditi.transitflow.dto.response.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Global exception handler for the application.
 * Handles all exceptions thrown by controllers and returns
 * a structured error response to the client.
 */
@RestControllerAdvice
@NoArgsConstructor
public class GlobalExceptionHandler {

    /**
     * Logger to maintain the chain of logs
     */
    private static final Logger log =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * @param path    request URI
     * @param message error message
     * @param errors  additional error details
     * @param status  HTTP status
     * @return ResponseEntity containing ErrorResponseDto
     */
    private ResponseEntity<ErrorResponseDto> buildErrorResponse(
            String path,
            String message,
            Object errors,
            HttpStatus status) {

        ErrorResponseDto error = new ErrorResponseDto(
                path,
                status.value(),
                status.getReasonPhrase(),
                message,
                LocalDateTime.now()
        );
        error.setError(errors);

        return new ResponseEntity<>(error, status);
    }

    /**
     * Handles resource not found exceptions.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(
            ResourceNotFoundException ex,
            HttpServletRequest request) {
        log.warn("Resource not found: {}", ex.getMessage());
        return buildErrorResponse(request.getRequestURI(), ex.getMessage(), Collections.emptyMap(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles route not found exceptions.
     */
    @ExceptionHandler(RouteNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleRouteNotFound(
            RouteNotFoundException ex,
            HttpServletRequest request) {
        log.warn("Route not found: {}", ex.getMessage());
        return buildErrorResponse(request.getRequestURI(), ex.getMessage(), Collections.emptyMap(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles invalid route exceptions.
     */
    @ExceptionHandler(InvalidRouteException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidRoute(
            InvalidRouteException ex,
            HttpServletRequest request) {
        log.warn("Invalid Route: {}", ex.getMessage());
        return buildErrorResponse(request.getRequestURI(), ex.getMessage(), Collections.emptyMap(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Extracts field name from validation path.
     */
    private String extractFieldName(String path) {
        return path.substring(path.lastIndexOf(".") + 1);
    }

    /**
     * Handles validation errors (e.g., @Min, @NotNull).
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDto> handleValidation(
            ConstraintViolationException ex,
            HttpServletRequest request) {
        Map<String, List<String>> errors = new HashMap<>();
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        for(ConstraintViolation<?> violation : violations) {
            String fieldName = extractFieldName(violation.getPropertyPath().toString());
            errors.computeIfAbsent(fieldName, k -> new ArrayList<>())
                    .add(violation.getMessage());
        }
        log.warn("Validation failed: {}", errors);

        return buildErrorResponse(request.getRequestURI(), "Validation failed", errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles missing request parameters.
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponseDto> handleMissingParams(
            MissingServletRequestParameterException ex,
            HttpServletRequest request) {

        String message = "Required parameter '" + ex.getParameterName() + "' is missing";

        log.warn("Missing request parameter: {}", ex.getParameterName());

        return buildErrorResponse(request.getRequestURI(), message, Collections.emptyMap(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles invalid parameter type errors.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDto> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex,
            HttpServletRequest request) {

        String message = "Invalid value '" + ex.getValue() +
                "' for parameter '" + ex.getName() + "'";

        log.warn("Type mismatch: {}", ex.getMessage());

        return buildErrorResponse(request.getRequestURI(), message, Collections.emptyMap(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles incorrect endpoint access.
     */
    @ExceptionHandler(org.springframework.web.servlet.resource.NoResourceFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNoResourceFound(Exception ex, HttpServletRequest request) {

        log.warn("Incorrect endpoint: {}", ex.getMessage());

        return buildErrorResponse(request.getRequestURI(), "Endpoint not found.", Collections.emptyMap(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles all uncaught exceptions.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(
            Exception ex,
            HttpServletRequest request) {

        log.error("Unexpected error occurred", ex);

        return buildErrorResponse(request.getRequestURI(), "Something went wrong", Collections.emptyMap(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
