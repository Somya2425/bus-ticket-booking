package com.cg.busbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "buses")
@Getter
@Setter
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer busId;

    private String registrationNumber;
    private Integer capacity;
    private String type;

    @ManyToOne
    @JoinColumn(name = "office_id")
    @JsonIgnore
    private AgencyOffice office;
}