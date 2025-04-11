package com.example.backend.model.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
    
    @ManyToOne
    @JoinColumn(name = "FLOOR_SEAT_SEQ", referencedColumnName = "FLOOR_SEAT_SEQ")
    private SeatingChart seatingChart;
    

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

	public SeatingChart getSeatingChart() {
		return seatingChart;
	}

	public void setSeatingChart(SeatingChart seatingChart) {
		this.seatingChart = seatingChart;
	}

    
}
