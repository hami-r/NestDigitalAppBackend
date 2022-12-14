package com.nest.nestdigitalapp_backend.dao;

import com.nest.nestdigitalapp_backend.model.LeaveCountModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;

public interface LeaveCountDao extends CrudRepository<LeaveCountModel,Integer> {
    @Query(value = "SELECT * FROM `leavecount` WHERE `emp_id`= :empId",nativeQuery = true)
    List<LeaveCountModel> viewLeaveCountEmp(int empId);
}
