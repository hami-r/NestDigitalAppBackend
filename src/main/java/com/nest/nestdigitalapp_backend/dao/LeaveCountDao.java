package com.nest.nestdigitalapp_backend.dao;

import com.nest.nestdigitalapp_backend.model.LeaveCountModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;

public interface LeaveCountDao extends CrudRepository<LeaveCountModel,Integer> {
    @Query(value = "SELECT * FROM `leavecount` WHERE `emp_id`= :empId",nativeQuery = true)
    List<LeaveCountModel> viewLeaveCountEmp(int empId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE `leavecount` SET `casual`=:difference WHERE `emp_id` = :empId", nativeQuery = true)
    void casual(int difference,int empId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE `leavecount` SET `sick`=:difference WHERE `emp_id` = :empId", nativeQuery = true)
    void sick(int difference,int empId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE `leavecount` SET `special`=:difference WHERE `emp_id` = :empId", nativeQuery = true)
    void special(int difference,int empId);
}
