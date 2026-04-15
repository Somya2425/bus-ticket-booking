package com.aditi.transitflow.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Entity representing a review with rating,
 * feedback, and associated customer and trip details.
 */
@Getter
@Setter
@Entity
@Table(name = "reviews")
@NoArgsConstructor
public class Review {

    /** Unique identifier for the review. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewId;

    /** Rating given by the customer. */
    private Integer rating;

    /** Review comment or feedback. */
    private String comment;

    /** Date and time when the review was submitted. */
    private LocalDateTime reviewDate;

    /** Customer who submitted the review. */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /** Trip associated with this review. */
    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;
}