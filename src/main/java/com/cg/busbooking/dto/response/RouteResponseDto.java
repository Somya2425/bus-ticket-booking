package com.cg.busbooking.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteResponseDto {
    private Integer routeId;
    private String fromCity;
    private String toCity;
    private Integer breakPoints;
    private Integer duration;

}
