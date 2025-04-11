package com.example.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.example.backend.model.bo.Employee;

@Repository
public interface EmployeeRepository<T> extends JpaRepository<Employee, String> {
	
	@Procedure(name = "GetEmployeeSeatingInfo")
    List<Employee> getEmployeeSeatingInfo();
}