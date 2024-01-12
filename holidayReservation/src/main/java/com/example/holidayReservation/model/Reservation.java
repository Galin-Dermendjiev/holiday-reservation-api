package com.example.holidayReservation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String phoneNumber;
	private String contactName;
	
	@ManyToOne
	private Holiday holiday;

	public Reservation(long id, String phoneNumber, String contactName, Holiday holiday) {
		super();
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.contactName = contactName;
		this.holiday = holiday;
	}

	public Reservation(String phoneNumber, String contactName, Holiday holiday) {
		super();
		this.phoneNumber = phoneNumber;
		this.contactName = contactName;
		this.holiday = holiday;
	}
	
	public Reservation() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public Holiday getHoliday() {
		return holiday;
	}

	public void setHoliday(Holiday holiday) {
		this.holiday = holiday;
	}

	@Override
	public String toString() {
		return "Reservations [id=" + id + ", phoneNumber=" + phoneNumber + ", contactName=" + contactName + ", holiday="
				+ holiday + "]";
	}
	
	
}
