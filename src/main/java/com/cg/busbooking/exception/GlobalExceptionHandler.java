package com.cg.busbooking.exception;

import com.cg.busbooking.dto.response.ErrorResponseDto;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Common builder (updated according to new DTO)
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

        return new ResponseEntity<>(error, status);
    }

    // Resource Not Found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(
            ResourceNotFoundException ex,
            WebRequest request) {

        String path = request.getDescription(false).replace("uri=", "");

        log.warn("Resource not found: {}", ex.getMessage());

        return buildErrorResponse(path, ex.getMessage(), null, HttpStatus.NOT_FOUND);
    }

    // Route Not Found
    @ExceptionHandler(RouteNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleRouteNotFound(
            RouteNotFoundException ex,
            WebRequest request) {

        String path = request.getDescription(false).replace("uri=", "");

        log.warn("Route not found: {}", ex.getMessage());

        return buildErrorResponse(path, ex.getMessage(), null, HttpStatus.NOT_FOUND);
    }

    // Validation Errors
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDto> handleValidation(
            ConstraintViolationException ex,
            WebRequest request) {

        String path = request.getDescription(false).replace("uri=", "");

        Map<String, List<String>> errors = new HashMap<>();

        ex.getConstraintViolations().forEach(v -> {
            String field = extractFieldName(v.getPropertyPath().toString());
            errors.computeIfAbsent(field, k -> new ArrayList<>())
                    .add(v.getMessage());
        });

        log.warn("Validation failed: {}", errors);

        return buildErrorResponse(path, "Validation failed", errors, HttpStatus.BAD_REQUEST);
    }

    // Missing Request Parameter
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponseDto> handleMissingParams(
            MissingServletRequestParameterException ex,
            WebRequest request) {

        String path = request.getDescription(false).replace("uri=", "");

        String message = "Required parameter '" + ex.getParameterName() + "' is missing";

        log.warn("Missing request parameter: {}", ex.getParameterName());

        return buildErrorResponse(path, message, null, HttpStatus.BAD_REQUEST);
    }

    // Type Mismatch
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDto> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex,
            WebRequest request) {

        String path = request.getDescription(false).replace("uri=", "");

        String message = "Invalid value '" + ex.getValue() +
                "' for parameter '" + ex.getName() + "'";

        log.warn("Type mismatch: {}", ex.getMessage());

        return buildErrorResponse(path, message, null, HttpStatus.BAD_REQUEST);
    }

    // Global Exception (fallback)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(
            Exception ex,
            WebRequest request) {

        String path = request.getDescription(false).replace("uri=", "");

        log.error("Unexpected error occurred", ex);

        return buildErrorResponse(path, "Something went wrong", null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Helper
    private String extractFieldName(String path) {
        return path.substring(path.lastIndexOf(".") + 1);
    }
}