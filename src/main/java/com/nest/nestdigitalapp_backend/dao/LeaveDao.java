package com.nest.nestdigitalapp_backend.dao;

import com.nest.nestdigitalapp_backend.model.LeaveModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LeaveDao extends CrudRepository<LeaveModel,Integer> {
    @Query(value = "SELECT * FROM `leaves` WHERE `emp_id` = :empId",nativeQuery = true)
    List<LeaveModel> viewLeaveStatus(int empId);
}
