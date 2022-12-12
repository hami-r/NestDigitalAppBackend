package com.nest.nestdigitalapp_backend.controller;

import com.nest.nestdigitalapp_backend.dao.LeaveDao;
import com.nest.nestdigitalapp_backend.model.EmployeeModel;
import com.nest.nestdigitalapp_backend.model.LeaveModel;
import com.nest.nestdigitalapp_backend.model.SecurityGuardModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;

public class LeaveController {

    @Autowired
    private LeaveDao ldao;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/applyLeave",consumes = "application/json", produces = "application/json")
    public HashMap<String, String> applyLeave(@RequestBody LeaveModel l){
        ldao.save(l);
        HashMap<String,String > map = new HashMap<>();
        map.put("status","success");
        return map;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/viewLeaveStatus",consumes = "application/json", produces = "application/json")
    public List<LeaveModel> viewLeaveStatus(@RequestBody LeaveModel l){
        return (List<LeaveModel>) ldao.viewLeaveStatus(l.getEmpId());
    }


}
