package com.aditi.transitflow.constants;

/**
 * This class contains constant messages related to Agency validations and errors.
 *
 * These constants are used across the application to maintain consistency
 * in exception handling and validation messages for Agency-related operations.
 */
public class AgencyConstants {

    /**
     * Error message used when an Agency is not found for a given agencyId.
     * Usage:
     * Append the agencyId to this message while throwing exceptions.
     * Example: "Agency not found for agencyId: 101"
     */
    public static final String AGENCY_NOT_FOUND = "Agency not found for agencyId: ";

    /**
     * Error message used when the agencyId provided is null.
     * Typically used in validation checks before processing requests.
     */
    public static final String AGENCY_ID_NULL = "Agency Id cannot be null";

    /**
     * Error message used when the agencyId is not a positive number.
     * Ensures that only valid (greater than zero) IDs are accepted.
     */
    public static final String AGENCY_ID_POSITIVE = "Agency Id must be positive";

    /**
     * Error message used when the agencyId exceeds the allowed maximum value.
     * Usage:
     * Append the maximum limit to this message dynamically.
     * Example: "Agency Id must be less than 1000"
     */
    public static final String AGENCY_ID_MAX = "Agency Id must be less than ";
}
