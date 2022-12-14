package com.nest.nestdigitalapp_backend.dao;

import com.nest.nestdigitalapp_backend.model.LeaveModel;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;

public interface LeaveDao extends CrudRepository<LeaveModel,Integer> {
    @Query(value = "SELECT * FROM `leaves` WHERE `emp_id` = :empId",nativeQuery = true)
    List<LeaveModel> viewLeaveStatus(int empId);

    @Query(value = "SELECT l.id,e.name,l.applied_date,l.title,l.remarks,l.type,l.status,l.from_date,l.to_date,lc.casual,lc.sick,lc.special FROM `leaves` l JOIN `leavecount` lc ON l.emp_id=lc.emp_id JOIN `employees` e on l.emp_id=e.id WHERE l.status='pending' ",nativeQuery = true)
    List<Map<String, String>> viewAllPendingLeaves();

    @Query(value = "SELECT * FROM `leaves` l JOIN `employees` e ON l.emp_id=e.id",nativeQuery = true)
    List<Map<String, String>> viewAllLeaves();

}
