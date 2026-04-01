package com.cg.busbooking.exception;

import com.cg.busbooking.dto.response.ErrorResponseDto;
import com.cg.busbooking.exception.agency.AgencyNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AgencyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseDto> handleAgencyNotFoundException(AgencyNotFoundException ex, HttpServletRequest request) {
        return new ResponseEntity(new ErrorResponseDto(400,"Agency Not Found",ex.getMessage(),request.getRequestURI(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }
}
