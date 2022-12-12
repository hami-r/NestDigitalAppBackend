package com.nest.nestdigitalapp_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "leavecount")
public class LeaveCountModel {
    @Id
    @GeneratedValue
    private int id;
    private int casual = 20;
    private int sick = 7;
    private int special = 3;
    private int empId;
}
