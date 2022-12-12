package com.nest.nestdigitalapp_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "securityguards")
public class SecurityGuardModel {

    @Id
    @GeneratedValue
    private int id;
    private String salary;
    private String email;
    private String phoneNo;
    private String username;
    private String password;
}
