package com.cg.busbooking.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto {
    private Integer customerId;
    private String name;
    private String email;
    private String phone;
    private String city;
}
