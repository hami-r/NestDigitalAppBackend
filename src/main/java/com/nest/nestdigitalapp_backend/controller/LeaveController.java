package com.nest.nestdigitalapp_backend.controller;
import com.nest.nestdigitalapp_backend.dao.LeaveCountDao;
import com.nest.nestdigitalapp_backend.dao.LeaveDao;
import com.nest.nestdigitalapp_backend.model.LeaveCountModel;
import com.nest.nestdigitalapp_backend.model.LeaveModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@RestController
public class LeaveController {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    @Autowired
    private LeaveDao ldao;

    @Autowired
    private LeaveCountDao lcdao;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/applyLeave",consumes = "application/json", produces = "application/json")
    public HashMap<String, String> applyLeave(@RequestBody LeaveModel l) {
        HashMap<String,String > map = new HashMap<>();
        List<LeaveCountModel> result = lcdao.viewLeaveCountEmp(l.getEmpId());
        LocalDate to = LocalDate.parse(l.getToDate());
        LocalDate from = LocalDate.parse(l.getFromDate());
        int days = (int) ChronoUnit.DAYS.between(from, to)+1;
        if(result.size()>0){
            if(days < checkLeave(result,l.getType())){
                ldao.save(l);
                map.put("status","success");
            } else {
                map.put("status","fail");
            }
        } else {
            map.put("message","not found");
        }

        return map;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/viewLeaveStatus",consumes = "application/json", produces = "application/json")
    public List<LeaveModel> viewLeaveStatus(@RequestBody LeaveModel l){
        return (List<LeaveModel>) ldao.viewLeaveStatus(l.getEmpId());
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/viewLeaveCountEmp",consumes = "application/json", produces = "application/json")
    public List<LeaveCountModel> viewLeaveCountEmp(@RequestBody LeaveCountModel lc){
        return (List<LeaveCountModel>) lcdao.viewLeaveCountEmp(lc.getEmpId());
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/viewAllLeaves")
    public List<Map<String, String>> viewAllLeaves(){
        return (List<Map<String, String>>) ldao.viewAllLeaves();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/viewAllPendingLeaves")
    public List<Map<String, String>> viewAllPendingLeaves(){
        return (List<Map<String, String>>) ldao.viewAllPendingLeaves();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/rejectLeave",consumes = "application/json", produces = "application/json")
    public HashMap<String, String> rejectLeave(@RequestBody LeaveModel l){
        ldao.rejectLeave((l.getId()));
        HashMap<String,String > map = new HashMap<>();
        map.put("status","success");
        return map;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/acceptLeave",consumes = "application/json", produces = "application/json")
    public HashMap<String, String> acceptLeave(@RequestBody LeaveModel l) {
        HashMap<String,String > map = new HashMap<>();
        List<LeaveCountModel> result = lcdao.viewLeaveCountEmp(l.getEmpId());
        LocalDate tos = LocalDate.parse(l.getToDate());
        LocalDate froms = LocalDate.parse(l.getFromDate());
        int days = (int) ChronoUnit.DAYS.between(froms, tos)+1;
        System.out.println(l.getType());
        ldao.acceptLeave(l.getId());
        int difference = checkLeave(result,l.getType()) - days;
        if(l.getType().equals("casual")){
            lcdao.casual(difference,l.getEmpId());
        } else if(l.getType().equals("sick")) {
            lcdao.sick(difference,l.getEmpId());
        } else {
            lcdao.special(difference,l.getEmpId());
        }
        map.put("status","success");
        return map;
    }


    public int checkLeave(List<LeaveCountModel> lc,String type){
        if(type.equals("casual")){
            return lc.get(0).getCasual();
        } else if(type.equals("sick"))
            return lc.get(0).getSick();
        else {
            return lc.get(0).getSpecial();
        }
    }

}
