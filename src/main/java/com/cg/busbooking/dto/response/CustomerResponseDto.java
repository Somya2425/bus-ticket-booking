package com.cg.busbooking.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponseDto {
    private Integer customerId;
    private String name;
    private String email;
    private String phone;
}
