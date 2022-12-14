package com.nest.nestdigitalapp_backend.controller;

import com.nest.nestdigitalapp_backend.dao.EmployeeDao;
import com.nest.nestdigitalapp_backend.dao.LeaveCountDao;
import com.nest.nestdigitalapp_backend.model.EmployeeModel;
import com.nest.nestdigitalapp_backend.model.LeaveCountModel;
import com.nest.nestdigitalapp_backend.model.LeaveModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeDao edao;
    @Autowired
    private LeaveCountDao lcdao;

    DateTimeFormatter year=DateTimeFormatter.ofPattern("yyyy");
    LocalDateTime now = LocalDateTime.now();

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/empLogin",consumes = "application/json", produces = "application/json")
    public HashMap<String, String> EmployeeLogin(@RequestBody EmployeeModel e){
        HashMap<String,String > map = new HashMap<>();
        List<EmployeeModel> result = (List<EmployeeModel>) edao.empLogin(e.getUsername(),e.getPassword());
        if(result.size()>0){
            map.put("status","success");
            map.put("empId",String.valueOf(result.get(0).getId()));
        } else {
            map.put("status","fail");
        }
        return map;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/addEmp",consumes = "application/json", produces = "application/json")
    public HashMap<String, String> addEmployee(@RequestBody EmployeeModel e){
        edao.save(e);
        LeaveCountModel lc = new LeaveCountModel();
        lc.setEmpId(e.getId());
        lc.setSick(7);
        lc.setCasual(20);
        lc.setSpecial(3);
        lc.setYear(year.format(now));
        lcdao.save(lc);
        HashMap<String,String > map = new HashMap<>();
        map.put("status","success");
        return map;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/searchEmp", consumes = "application/json", produces = "application/json")
    public List<EmployeeModel> searchPage(@RequestBody EmployeeModel e){
        return (List<EmployeeModel>) edao.searchEmp(e.getName());
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/deleteEmp", consumes = "application/json", produces = "application/json")
    public HashMap<String, String> deleteEmployee(@RequestBody EmployeeModel e){
        edao.deleteEmp(e.getId());
        HashMap<String,String > map = new HashMap<>();
        map.put("status","success");
        return map;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/editEmp", consumes = "application/json", produces = "application/json")
    public HashMap<String, String> editEmployee(@RequestBody EmployeeModel e){
        edao.editEmp(e.getDesignation(),e.getEmail(),e.getEmployeeCode(),e.getName(),e.getPassword(),e.getPhoneNo(),e.getSalary(),e.getUsername(),e.getId());
        HashMap<String,String > map = new HashMap<>();
        map.put("status","success");
        return map;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/viewEmp")
    public List<EmployeeModel> viewEmployee(){
        return (List<EmployeeModel>) edao.findAll();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/viewEmpProfile", consumes = "application/json", produces = "application/json")
    public List<EmployeeModel> viewEmployeeProfile(@RequestBody EmployeeModel e){
        return  (List<EmployeeModel>) edao.viewProfile(String.valueOf(e.getId()));
    }
}
