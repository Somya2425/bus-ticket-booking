package com.cg.busbooking.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer routeId;

    private String fromCity;
    private String toCity;
    private Integer breakPoints;
    private Integer duration;
}
