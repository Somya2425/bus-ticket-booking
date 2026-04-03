package com.cg.busbooking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for representing driver details of an office.
 * This DTO is used to provide basic information about drivers associated
 * with a specific agency office. It is typically used in listing APIs
 * where only essential driver details are required.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfficeDriverResponseDto {

    /**
     * Unique identifier of the driver.
     */
    private Integer driverId;

    /**
     * Name of the driver.
     */
    private String name;
}
