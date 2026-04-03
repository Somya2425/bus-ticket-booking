package com.cg.busbooking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO representing driver details returned for an agency office.
 * Used in API responses to expose driver information.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfficeDriverResponseDto {

    private Integer driverId;
    private String name;
}
