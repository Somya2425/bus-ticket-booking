package com.cg.busbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing a bus in the system.
 */
@Entity
@Table(name = "buses")
@Getter
@Setter
@NoArgsConstructor
public class Bus {

    /**
     * Unique identifier for the bus.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer busId;

    /**
     * Registration number of the bus.
     */
    private String registrationNumber;

    /**
     * Total seating capacity of the bus.
     */
    private Integer capacity;

    /**
     * Type of the bus (e.g., AC, Non-AC, Sleeper).
     */
    private String type;

    /**
     * The agency office to which this bus is assigned.
     * Represents a many-to-one relationship with AgencyOffice.
     */
    @ManyToOne
    @JoinColumn(name = "office_id")
    @JsonIgnore
    private AgencyOffice office;
}