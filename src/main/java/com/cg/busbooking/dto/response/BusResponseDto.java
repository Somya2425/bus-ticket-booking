package com.cg.busbooking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.cg.busbooking.entity.AgencyOffice;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BusResponseDto {
    private Integer busId;
    private String registrationNumber;
    private Integer capacity;
    private String type;
    private AgencyOffice office;
}
