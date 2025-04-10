package com.example.backend.model.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @Column(name = "EMP_ID")
    private String empId;

    @Column(name = "NAME")
    private String name;
    
    @Column(name = "EMAIL")
    private String email;
    
    @Column(name = "FLOOR_SEAT_SEQ")
    private Integer floorSeatSeq; 
    

    // Getters and Setters
    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public Integer getFloorSeatSeq() {
		return floorSeatSeq;
	}

	public void setFloorSeatSeq(Integer floorSeatSeq) {
		this.floorSeatSeq = floorSeatSeq;
	}

    
}
