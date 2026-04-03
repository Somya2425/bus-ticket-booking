package com.cg.busbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity representing a customer with personal and contact details.
 */
@Setter
@Getter
@Entity
@Table(name = "customers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer {

    /** Unique identifier for the customer. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    /** Name of the customer. */
    private String name;

    /** Email address of the customer. */
    private String email;

    /** Contact number of the customer. */
    private String phone;

    /** Address associated with the customer. */
    @ManyToOne
    @JoinColumn(name = "address_id")
    @JsonIgnore
    private Address address;
}