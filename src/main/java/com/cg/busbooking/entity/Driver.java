package com.cg.busbooking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer driverId;
    private String licenseNumber;
    private String name;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "office_id")
    private AgencyOffice office;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
}
