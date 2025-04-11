package com.example.backend.model.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SeatingChart")
public class SeatingChart implements Serializable{

	private static final long serialVersionUID = 1L;
	
    @Id
    @Column(name = "SYSID")
    private Long sysid;
    
    @Column(name = "FLOOR_SEAT_SEQ", unique = true)
    private int floorSeatSeq;

    @Column(name = "FLOOR_NO")
    private int floorNo;

    @Column(name = "SEAT_NO")
    private int seatNo;
    
    
	public int getFloorSeatSeq() {
		return floorSeatSeq;
	}

	// Getters and Setters
	
	
	
	public void setFloorSeatSeq(int floorSeatSeq) {
		this.floorSeatSeq = floorSeatSeq;
	}

	public Long getSysid() {
		return sysid;
	}

	public void setSysid(Long sysid) {
		this.sysid = sysid;
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
