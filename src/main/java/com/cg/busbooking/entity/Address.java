package com.cg.busbooking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Entity representing an address in the system.
 */
@Getter
@Setter
@Entity
@Table(name = "addresses")
@NoArgsConstructor
public class Address {
    /**
     * Unique identifier for the address.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;

    /**
     * Street or detailed address information.
     */
    private String address;

    /**
     * City of the address.
     */
    private String city;

    /**
     * State of the address.
     */
    private String state;

    /**
     * ZIP or postal code of the address.
     */
    @Column(name = "zip_code")
    private String zipCode;
}