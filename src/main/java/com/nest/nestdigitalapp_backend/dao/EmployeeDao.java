package com.nest.nestdigitalapp_backend.dao;

import com.nest.nestdigitalapp_backend.model.EmployeeModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeDao extends CrudRepository<EmployeeModel,Integer> {
    @Query(value = "SELECT * FROM `employees` WHERE `name` LIKE %:name%",nativeQuery = true)
    List<EmployeeModel> searchEmp(String name);

    @Query(value = "SELECT * FROM `employees` WHERE `username` =:username AND `password` =:password",nativeQuery = true)
    List<EmployeeModel> userLogin(String username,String password);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM `employees` WHERE `id`= :id",nativeQuery = true)
    void deleteEmp(int id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE `employees` SET `designation`=:designation,`email`=:email,`employee_code`=:employeeCode,`name`=:name,`password`=:password,`phone_no`=:phoneNo,`salary`=:salary,`username`=:username WHERE `id`=:id",nativeQuery = true)
    void editEmp(String designation,String email,String employeeCode,String name,String password,String phoneNo,String salary,String username,int id);

    @Query(value = "SELECT `name`,`employee_code`,`email`,`phone_no`,`designation`,`salary` FROM `employees` WHERE `id`= :empId",nativeQuery = true)
    List<EmployeeModel> viewProfile(String empId);
}


