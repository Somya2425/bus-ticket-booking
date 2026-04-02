package com.cg.busbooking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for representing basic bus details of an office.
 *
 * This DTO is used to provide minimal information about buses associated
 * with a specific agency office, typically in listing APIs.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfficeBusResponseDto {

    /**
     * Unique identifier of the bus.
     */
    private Integer busId;

    /**
     * Type of the bus (e.g., AC, Non-AC, Sleeper).
     */
    private String type;
}
