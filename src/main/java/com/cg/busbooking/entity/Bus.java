package com.cg.busbooking.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "buses")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer busId;

    private String registrationNumber;
    private Integer capacity;
    private String type;

    @ManyToOne
    @JoinColumn(name = "office_id")
    private AgencyOffice office;
}
