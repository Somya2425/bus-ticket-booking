package com.cg.busbooking.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "agencies")
public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer agencyId;

    private String name;
    private String contactPersonName;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "agency")
    private List<AgencyOffice> offices;
}
