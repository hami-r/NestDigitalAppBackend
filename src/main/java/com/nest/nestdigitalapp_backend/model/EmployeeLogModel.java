package com.nest.nestdigitalapp_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employeelogs")
public class EmployeeLogModel {

    @Id
    @GeneratedValue
    private int id;
    private String entry;
    private String exit;
    private String empId;
}
