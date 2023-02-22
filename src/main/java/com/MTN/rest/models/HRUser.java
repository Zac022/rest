package com.MTN.rest.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "hr_users")
public class HRUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "contract_type")
    private String contractType;

    @Column(name = "benefit_percentage")
    private String benefitPercentage;

    public boolean isPresent() {
        return false;
    }

    // getters and setters
}

