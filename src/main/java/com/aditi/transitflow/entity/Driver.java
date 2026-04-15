package com.aditi.transitflow.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing a driver with license, contact, and assignment details.
 */
@Getter
@Setter
@Entity
@Table(name = "drivers")
@NoArgsConstructor
public class Driver {

    /** Unique identifier for the driver. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer driverId;

    /** License number of the driver. */
    private String licenseNumber;

    /** Name of the driver. */
    private String name;

    /** Contact number of the driver. */
    private String phone;

    /** Agency office to which the driver is assigned. */
    @ManyToOne
    @JoinColumn(name = "office_id")
    private AgencyOffice office;

    /** Address associated with the driver. */
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
}