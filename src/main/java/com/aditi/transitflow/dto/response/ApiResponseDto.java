package com.aditi.transitflow.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Generic API response DTO used to standardize responses across the application.
 *
 * It is typically used for both success and error responses.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDto {

    /**
     * Status code representing the result of the API call.
     * Example: "200", "400", "404"
     */
    private String statusCode;

    /**
     * Message describing the result of the API call.
     */
    private String statusMessage;

    /**
     * Response payload containing actual data or error details.
     */
    private Object object;
}
