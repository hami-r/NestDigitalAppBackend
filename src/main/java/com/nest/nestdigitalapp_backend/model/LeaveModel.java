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

    public LeaveModel() {
    }

    public LeaveModel(int id, String title, String remarks, String fromDate, String toDate, String appliedDate, String status, String type, int empId) {
        this.id = id;
        this.title = title;
        this.remarks = remarks;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.appliedDate = appliedDate;
        this.status = status;
        this.type = type;
        this.empId = empId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(String appliedDate) {
        this.appliedDate = appliedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }
}
