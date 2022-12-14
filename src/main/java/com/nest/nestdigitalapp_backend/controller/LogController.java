package com.nest.nestdigitalapp_backend.controller;

import com.nest.nestdigitalapp_backend.dao.EmployeeDao;
import com.nest.nestdigitalapp_backend.dao.EmployeeLogsDao;
import com.nest.nestdigitalapp_backend.dao.VisitorLogsDao;
import com.nest.nestdigitalapp_backend.model.EmployeeLogsModel;
import com.nest.nestdigitalapp_backend.model.EmployeeModel;
import com.nest.nestdigitalapp_backend.model.LeaveCountModel;
import com.nest.nestdigitalapp_backend.model.VisitorLogsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LogController {

    @Autowired
    private EmployeeLogsDao eldao;
    @Autowired
    private VisitorLogsDao vldao;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/addEmployeeLogs",consumes = "application/json", produces = "application/json")
    public HashMap<String, String> addEmployeeLogs(@RequestBody EmployeeLogsModel el){
        eldao.save(el);
        HashMap<String,String > map = new HashMap<>();
        map.put("status","success");
        return map;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/addVisitorLogs",consumes = "application/json", produces = "application/json")
    public HashMap<String, String> addVisitorLogs(@RequestBody VisitorLogsModel vl){
        vldao.save(vl);
        HashMap<String,String > map = new HashMap<>();
        map.put("status","success");
        return map;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/viewAllEmpLogs")
    public List<Map<String, String>> viewAllEmployeeLogs(){
        return (List<Map<String, String>>) eldao.viewAllEmpLogs();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/viewAllVisLogs")
    public List<VisitorLogsModel> viewAllVisitorLogs(){
        return (List<VisitorLogsModel>) vldao.findAll();
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/viewEmpLogsOnDate")
    public List<Map<String, String>> viewAllEmployeeLogsOnDate(@RequestBody EmployeeLogsModel el){
        return (List<Map<String, String>>) eldao.viewEmpLogsOnDate(el.getDate());
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/viewVisLogsOnDate")
    public List<VisitorLogsModel> viewVisitorLogsOnDate(@RequestBody VisitorLogsModel vl){
        return (List<VisitorLogsModel>) vldao.viewVisOnDate(vl.getDate());
    }
}
