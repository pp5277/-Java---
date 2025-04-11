package com.example.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.model.bo.Employee;
import com.example.backend.model.bo.SeatingChart;
import com.example.backend.repository.EmployeeRepository;
import com.example.backend.repository.SeatingChartRepository;

@Service
public class MyService {

    @Autowired
    private EmployeeRepository<Employee> employeeRepository;
    @Autowired
    private SeatingChartRepository seatingChartRepository;

    @Transactional
    public List<Employee> getEmployeeSeatingInfo() {
        return employeeRepository.getEmployeeSeatingInfo();
    }
    
    @Transactional
    public List<Employee> getAllEmployees() {
    	return employeeRepository.findAll();
    }
    
    @Transactional
    public void saveseatingInfo(SeatingChart bo) {
    	seatingChartRepository.save(bo);
    }
    
    @Transactional
    public void cancelseatingInfo(SeatingChart bo) {
    	seatingChartRepository.save(bo);
    }
}
