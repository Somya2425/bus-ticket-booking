package com.cg.busbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="agency_offices")
@Getter
@Setter
public class AgencyOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer officeId;
    private String officeMail;
    private String officeContactPersonName;
    private String officeContactNumber;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "agency_id")
    @JsonIgnore
    private Agency agency;

    @ManyToOne
    @JoinColumn(name = "office_address_id")
    @JsonIgnore
    private Address address;
}
