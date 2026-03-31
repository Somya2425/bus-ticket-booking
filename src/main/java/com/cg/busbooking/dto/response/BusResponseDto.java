package com.cg.busbooking.dto.response;

import com.cg.busbooking.entity.AgencyOffice;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusResponseDto {
    private Integer busId;
    private String registrationNumber;
    private Integer capacity;
    private String type;
    private AgencyOffice office;
}
