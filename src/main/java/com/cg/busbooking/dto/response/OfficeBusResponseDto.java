package com.cg.busbooking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO representing bus details returned for an agency office.
 * Used in API responses to provide bus information.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfficeBusResponseDto {
    private Integer busId;
    private String type;
}
