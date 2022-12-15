package com.nest.nestdigitalapp_backend.controller;

import com.nest.nestdigitalapp_backend.dao.EmployeeDao;
import com.nest.nestdigitalapp_backend.dao.EmployeeLogsDao;
import com.nest.nestdigitalapp_backend.dao.LeaveDao;
import com.nest.nestdigitalapp_backend.dao.VisitorLogsDao;
import com.nest.nestdigitalapp_backend.model.*;
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

    @Autowired
    private EmployeeDao edao;

    @Autowired
    private LeaveDao ldao;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/addEmployeeLogs",consumes = "application/json", produces = "application/json")
    public HashMap<String, String> addEmployeeLogs(@RequestBody EmployeeLogsModel el){
        HashMap<String,String > map = new HashMap<>();
        List<EmployeeModel> id = edao.getEmpByCode(el.getEmployeeCode());
        if(id.size()>0){
            List<LeaveModel> checkLeaves = ldao.checkEmpLeave(id.get(0).getId(),el.getDate());
            if (checkLeaves.size() == 0){
                el.setEmpId(id.get(0).getId());
                eldao.save(el);
                map.put("status","success");
            } else {
                map.put("status","fail");
            }
        }
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
