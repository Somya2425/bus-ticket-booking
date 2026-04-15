package com.aditi.transitflow.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Entity representing a travel or bus agency in the system.
 */
@Entity
@Table(name = "agencies")
@Getter
@Setter
@NoArgsConstructor
public class Agency {

    /**
     * Unique identifier for the agency.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer agencyId;

    /**
     * Name of the agency.
     */
    private String name;

    /**
     * Name of the primary contact person for the agency.
     */
    private String contactPersonName;

    /**
     * Email address of the agency.
     */
    private String email;

    /**
     * Contact phone number of the agency.
     */
    private String phone;

    /**
     * List of offices associated with the agency.
     * Represents a one-to-many relationship with AgencyOffice.
     */
    @OneToMany(mappedBy = "agency")
    private List<AgencyOffice> offices;
}