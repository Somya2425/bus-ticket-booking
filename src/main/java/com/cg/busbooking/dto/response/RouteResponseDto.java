package com.cg.busbooking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RouteResponseDto {
    private Integer routeId;
    private String fromCity;
    private String toCity;
    private Integer breakPoints;
    private Integer duration;

}
