package com.bbs.beans;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="booking_info_jpa")
public class Ticket {

	@Id @Column(name="booking_id") @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer bookingId;
	@Temporal(value=TemporalType.DATE)
	private Date journeyDate;
	private Integer numofSeats;
	@Temporal(TemporalType.DATE)
	private Date bookingDatetime;
	private Integer availId;
	@Temporal(value=TemporalType.DATE)
	private Date availDate;
	private Integer availSeats;
	@Column(name="user_id")
	private Integer userId;
	@Column(name ="bus_id")

	private Integer busId;
	private String source;
	private String destination;

	
	
	public Integer getBookingId() {
		return bookingId;
	}



	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}



	public Date getJourneyDate() {
		return journeyDate;
	}



	public void setJourneyDate(Date journeyDate) {
		this.journeyDate = journeyDate;
	}



	public int getNumofSeats() {
		return numofSeats;
	}



	public void setNumofSeats(int numofSeats) {
		this.numofSeats = numofSeats;
	}



	public Date getBookingDatetime() {
		return bookingDatetime;
	}



	public void setBookingDatetime(Date bookingDatetime) {
		this.bookingDatetime = bookingDatetime;
	}



	public Integer getAvailId() {
		return availId;
	}



	public void setAvailId(Integer availId) {
		this.availId = availId;
	}



	public Date getAvailDate() {
		return availDate;
	}



	public void setAvailDate(Date availDate) {
		this.availDate = availDate;
	}



	public Integer getAvailSeats() {
		return availSeats;
	}



	public void setAvailSeats(Integer availSeats) {
		this.availSeats = availSeats;
	}



	public Integer getUserId() {
		return userId;
	}



	public void setUserId(Integer userId) {
		this.userId = userId;
	}



	public Integer getBusId() {
		return busId;
	}



	public void setBusId(Integer busId) {
		this.busId = busId;
	}



	public String getSource() {
		return source;
	}



	public void setSource(String source) {
		this.source = source;
	}



	public String getDestination() {
		return destination;
	}



	public void setDestination(String destination) {
		this.destination = destination;
	}



	public void setNumofSeats(Integer numofSeats) {
		this.numofSeats = numofSeats;
	}



	@Override
	public String toString() {
		return "Ticket [bookingId=" + bookingId + ", journeyDate=" + journeyDate + ", numofSeats=" + numofSeats
				+ ", bookingDatetime=" + bookingDatetime + ", availId=" + availId + ", availDate=" + availDate
				+ ", availSeats=" + availSeats + ", userId=" + userId + ", busId=" + busId + ", source=" + source
				+ ", destination=" + destination + "]";
	}


	}


	

