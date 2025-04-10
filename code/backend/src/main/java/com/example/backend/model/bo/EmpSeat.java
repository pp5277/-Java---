package com.example.backend.model.bo;


public class EmpSeat {


    private String empId;
    private String name;
    private String email;
    private int floorSeatSeq;
    private int floorNo;
    private int seatNo;
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
	public int getFloorSeatSeq() {
		return floorSeatSeq;
	}
	public void setFloorSeatSeq(int floorSeatSeq) {
		this.floorSeatSeq = floorSeatSeq;
	}
	public int getFloorNo() {
		return floorNo;
	}
	public void setFloorNo(int floorNo) {
		this.floorNo = floorNo;
	}
	public int getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}
    
    
}