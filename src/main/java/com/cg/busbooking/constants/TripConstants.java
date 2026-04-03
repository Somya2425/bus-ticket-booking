package com.cg.busbooking.constants;

/**
 * Contains constant values related to Trip operations.
 *
 * This class centralizes all static messages and status codes used in
 * Trip-related functionalities to maintain consistency across the application.
 */
public class TripConstants {

    /**
     * Error message used when a Trip is not found for a given trip ID.
     *
     * Usage:
     * Append the trip ID to this message.
     * Example: "Trip not found with id 101"
     */
    public static final String TRIP_NOT_FOUND = "Trip not found with id";

    /**
     * Represents HTTP status code 200 (OK).
     *
     * Used to indicate successful operations.
     */
    public static final String STATUS_200 = "200";

    /**
     * Message used when a Trip is successfully found.
     */
    public static final String TRIP_FOUND = "Trip Found";

    /**
     * Message used when available seats are successfully retrieved.
     */
    public static final String SEATS_FOUND = "Available seats fetched successfully";

    /**
     * Error message used when no trips are found between two locations.
     *
     */
    public static final String TRIPS_NOT_FOUND_BETWEEN = "No trips found from '%s' to '%s'";
}