package com.cg.busbooking.constants;

/**
 * Utility class that holds all constant values used in Trip-related APIs.
 *
 * Purpose:
 * - Avoid hardcoding values across the application
 * - Ensure consistency in API responses and messages
 * - Improve maintainability and readability
 *
 * Design:
 * - Declared as final to prevent inheritance
 * - Contains only static constants
 * - Has a private constructor to prevent instantiation
 */
public final class TripConstants {

    /**
     * Private constructor to prevent instantiation of this utility class.
     *
     * Throws:
     * UnsupportedOperationException - if an attempt is made to instantiate this class
     */
    private TripConstants() {
        throw new UnsupportedOperationException(
                "This is a constants class and cannot be instantiated"
        );
    }

    /**
     * Error message returned when a trip is not found for a given ID.
     *
     * Note: Can be appended with tripId for more clarity.
     * Example: "Trip not found with id 1"
     */
    public static final String TRIP_NOT_FOUND = "Trip not found with id";

    /**
     * Standard success status code used in API responses.
     *
     * Note: Stored as String to match ApiResponseDto structure.
     */
    public static final String STATUS_200 = "200";

    /**
     * Success message returned when trip data is fetched successfully.
     */
    public static final String TRIP_FOUND = "Trip Found";

    /**
     * Error message template when no trips are found between source and destination.
     *
     * Placeholders:
     * - %s → source city
     * - %s → destination city
     */
    public static final String TRIPS_NOT_FOUND_BETWEEN = "No trips found from '%s' to '%s'";

    /**
     * Success message returned when available seats are fetched successfully.
     */
    public static final String SEATS_FOUND =
            "Available seats fetched successfully";
}