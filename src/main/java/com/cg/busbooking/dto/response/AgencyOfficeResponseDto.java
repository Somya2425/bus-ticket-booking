package com.cg.busbooking.dto.response;

import com.cg.busbooking.entity.Address;
import com.cg.busbooking.entity.Agency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgencyOfficeResponseDto {
    private Integer officeId;
    private String officeMail;
    private String officeContactPersonName;
    private String officeContactNumber;
    private Agency agency;
    private Address address;
}
