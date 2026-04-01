package com.cg.busbooking.dto;

import com.cg.busbooking.dto.response.ErrorResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto extends ErrorResponseDto {
    private String message;
    private LocalDateTime timestamp;
    private String path;
}
