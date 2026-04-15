package com.aditi.transitflow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing an office of an agency.
 */
@Entity
@Table(name = "agency_offices")
@Getter
@Setter
@NoArgsConstructor
public class AgencyOffice {

    /**
     * Unique identifier for the agency office.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer officeId;

    /**
     * Email address of the agency office.
     */
    private String officeMail;

    /**
     * Name of the contact person for the office.
     */
    private String officeContactPersonName;

    /**
     * Contact number of the office.
     */
    private String officeContactNumber;

    /**
     * The agency to which this office belongs.
     * Represents a many-to-one relationship with Agency.
     */
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "agency_id")
    private Agency agency;

    /**
     * Address associated with the agency office.
     * Represents a many-to-one relationship with Address.
     */
    @ManyToOne
    @JoinColumn(name = "office_address_id")
    private Address address;
}