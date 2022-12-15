package com.nest.nestdigitalapp_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employeelogs")
public class EmployeeLogsModel {

    @Id
    @GeneratedValue
    private int id;
    private int empId;
    private String date;
    private String entryTime;
    private String exitTime;
    private String employeeCode;

    public EmployeeLogsModel() {
    }

    public EmployeeLogsModel(int id, int empId, String date, String entryTime, String exitTime, String employeeCode) {
        this.id = id;
        this.empId = empId;
        this.date = date;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.employeeCode = employeeCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getExitTime() {
        return exitTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }
}
