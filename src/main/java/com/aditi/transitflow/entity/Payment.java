package com.aditi.transitflow.entity;

import com.aditi.transitflow.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Entity representing a payment with amount,
 * status, and related booking and customer details.
 */
@Getter
@Setter
@Entity
@Table(name = "payments")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
public class Payment {

    /** Unique identifier for the payment. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;

    /** Total amount paid. */
    private Double amount;

    /** Date and time when the payment was made. */
    private LocalDateTime paymentDate;

    /** Status of the payment (e.g., SUCCESS, FAILED). */
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    /** Booking associated with this payment. */
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "booking_id")
    private Booking booking;

    /** Customer who made the payment. */
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
