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
    @Column(name = "FLOOR_SEAT_SEQ")
    private int floorSeatSeq;

    @Column(name = "FLOOR_NO")
    private int floorNo;

    @Column(name = "SEAT_NO")
    private int seatNo;
    
	// Getters and Setters
    
    
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
