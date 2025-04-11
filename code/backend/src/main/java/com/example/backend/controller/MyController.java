package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.model.bo.Employee;
import com.example.backend.model.bo.SeatingChart;
import com.example.backend.service.MyService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MyController {

    @Autowired
    private MyService myService;

   
    @GetMapping("/api/empseat-info")
    public List<Employee> getEmployeeSeatingInfo() {
    	return myService.getEmployeeSeatingInfo();
    }
    
    @GetMapping("/api/employees")
    public List<Employee> getEmployees() {
        return myService.getAllEmployees();
    }
    
    @PostMapping("api/seatchart")
    public void saveSeatchart(@RequestBody SeatingChart bo){
    	myService.saveseatingInfo(bo);
    }
    
    @PostMapping("api/cancelseatchart")
    public void cancelSeatchart(@RequestBody SeatingChart bo){
    	bo.setFloorNo(0);
    	bo.setSeatNo(0);
    	myService.saveseatingInfo(bo);
    }
}
