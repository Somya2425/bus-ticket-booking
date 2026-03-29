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
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Logger (industry standard)
    private static final Logger log =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Common builder (DRY)
    private ResponseEntity<ErrorResponseDto> buildErrorResponse(
            String message, Object errors, HttpStatus status) {

        ErrorResponseDto error = new ErrorResponseDto(
                status.value(),
                message,
                errors,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(error, status);
    }

    // Resource Not Found (expected → WARN)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(
            ResourceNotFoundException ex) {

        log.warn("Resource not found: {}", ex.getMessage());

        return buildErrorResponse(ex.getMessage(), null, HttpStatus.NOT_FOUND);
    }

    // Validation Errors (client mistake → WARN)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDto> handleValidation(ConstraintViolationException ex) {

        Map<String, List<String>> errors = new HashMap<>();

        ex.getConstraintViolations().forEach(v -> {

            String field = extractFieldName(v.getPropertyPath().toString());

            errors.computeIfAbsent(field, k -> new ArrayList<>())
                    .add(v.getMessage());
        });

        log.warn("Validation failed: {}", errors);

        return buildErrorResponse("Validation failed", errors, HttpStatus.BAD_REQUEST);
    }

    //  Missing Request Parameter (client mistake → WARN)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponseDto> handleMissingParams(
            MissingServletRequestParameterException ex) {

        String message = "Required parameter '" + ex.getParameterName() + "' is missing";

        log.warn("Missing request parameter: {}", ex.getParameterName());

        return buildErrorResponse(message, null, HttpStatus.BAD_REQUEST);
    }

    //type mismatch exception
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDto> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex) {

        String message = "Invalid value '" + ex.getValue() +
                "' for parameter '" + ex.getName() + "'";

        log.warn("Type mismatch: {}", ex.getMessage());

        return buildErrorResponse(message, null, HttpStatus.BAD_REQUEST);
    }

    // Fallback (unexpected → ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception ex) {

        log.error("Unexpected error occurred", ex); // 🔥 MOST IMPORTANT LOG

        return buildErrorResponse("Something went wrong", null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 🔧 Helper method (clean code)
    private String extractFieldName(String path) {
        return path.substring(path.lastIndexOf(".") + 1);
    }
}