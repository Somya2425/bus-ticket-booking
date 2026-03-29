package com.cg.busbooking.dto.response;

import com.cg.busbooking.entity.AgencyOffice;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusResponseDto {
    private Integer busId;
    private String registrationNumber;
    private Integer capacity;
    private String type;
    private AgencyOffice office;
}
