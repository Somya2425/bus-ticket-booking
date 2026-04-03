package com.cg.busbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing a Customer.
 * This class maps to the "customers" table in the database and contains
 * customer-related details such as name, email, phone, and associated address.
 */
@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "customers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer {

    /**
     * Unique identifier for the customer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    /**
     * Name of the customer.
     */
    private String name;

    /**
     * Email address of the customer.
     */
    private String email;

    /**
     * Contact phone number of the customer.
     */
    private String phone;

    /**
     * Address associated with the customer.
     * This is a many-to-one relationship.
     */
    @ManyToOne
    @JoinColumn(name = "address_id")
    @JsonIgnore
    private Address address;

}
