package com.aditi.transitflow.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entity class representing a User in the system.
 * This class is mapped to the "users" table in the database
 * and stores authentication and authorization details such as
 * username, password, and role.
 */
@Entity
@Data
@Table(name = "users")
public class User {

    /**
     * Unique identifier for the user.
     * This serves as the primary key and represents the username
     * used for login.
     */
    @Id
    private String username;

    /**
     * Password of the user.
     */
    private String password;

    /**
     * Role assigned to the user.
     * Used for authorization and access control.
     */
    private String role;
}