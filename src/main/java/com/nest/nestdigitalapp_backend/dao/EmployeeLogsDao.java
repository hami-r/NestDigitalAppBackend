package com.nest.nestdigitalapp_backend.dao;

import com.nest.nestdigitalapp_backend.model.EmployeeLogsModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;

public interface EmployeeLogsDao extends CrudRepository<EmployeeLogsModel,Integer> {

    @Query(value = "SELECT e.name,el.date,el.entry_time,el.exit_time FROM `employeelogs` el JOIN `employees` e ON e.id=el.emp_id",nativeQuery = true)
    List<Map<String, String>> viewAllEmpLogs();

    @Query(value = "SELECT e.name,el.date,el.entry_time,el.exit_time FROM `employeelogs` el JOIN `employees` e ON e.id=el.emp_id WHERE el.date = :date",nativeQuery = true)
    List<Map<String, String>> viewEmpLogsOnDate(String date);
}
