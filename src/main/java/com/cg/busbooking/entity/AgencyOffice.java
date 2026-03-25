package com.cg.busbooking.entity;

import jakarta.persistence.*;

@Entity
@Table(name="agency_offices")
public class AgencyOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer officeId;

    private String officeMail;
    private String officeContactPersonName;
    private String officeContactNumber;

    @ManyToOne
    @JoinColumn(name = "agency_id")
    private Agency agency;

    @ManyToOne
    @JoinColumn(name = "office_address_id")
    private Address address;
}
