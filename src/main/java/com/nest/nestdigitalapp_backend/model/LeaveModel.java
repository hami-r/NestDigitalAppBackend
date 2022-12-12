package com.nest.nestdigitalapp_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "leaves")
public class LeaveModel {

    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String remarks;
    private String fromDate;
    private String toDate;
    private String appliedDate;
    private String status;
    private String type;
    private int empId;


}
