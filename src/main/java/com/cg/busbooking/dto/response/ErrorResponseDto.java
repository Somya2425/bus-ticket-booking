package com.cg.busbooking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {
    private String apiPath;
    private HttpStatus errorCode;
    private String errorMessage;
    private LocalDateTime errorTime;

    private int status;
    private String message;
    private Object errors;
    private LocalDateTime timestamp;
}
