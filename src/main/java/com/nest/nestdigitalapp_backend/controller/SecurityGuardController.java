package com.nest.nestdigitalapp_backend.controller;

import com.nest.nestdigitalapp_backend.dao.SecurityGuardDao;
import com.nest.nestdigitalapp_backend.model.EmployeeModel;
import com.nest.nestdigitalapp_backend.model.LeaveCountModel;
import com.nest.nestdigitalapp_backend.model.SecurityGuardModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class SecurityGuardController {

    @Autowired
    private SecurityGuardDao sdao;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/secGuardLogin",consumes = "application/json", produces = "application/json")
    public HashMap<String, String> securityGuardLogin(@RequestBody SecurityGuardModel s){
        HashMap<String,String > map = new HashMap<>();
        List<SecurityGuardModel> result = (List<SecurityGuardModel>) sdao.secGuardLogin(s.getUsername(),s.getPassword());
        if(result.size()>0){
            map.put("status","success");
            map.put("secId",String.valueOf(result.get(0).getId()));
        } else {
            map.put("status","fail");
        }
        return map;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/addSec",consumes = "application/json", produces = "application/json")
    public HashMap<String, String> addSecGuard(@RequestBody SecurityGuardModel s){
        sdao.save(s);
        HashMap<String,String > map = new HashMap<>();
        map.put("status","success");
        return map;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/viewSec")
    public List<SecurityGuardModel> viewSecurityGuard(){
        return (List<SecurityGuardModel>) sdao.findAll();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/viewSecProfile", consumes = "application/json", produces = "application/json")
    public List<SecurityGuardModel> viewSecGuardProfile(@RequestBody SecurityGuardModel s){
         return  (List<SecurityGuardModel>) sdao.viewSecProfile(s.getId());
    }

}
