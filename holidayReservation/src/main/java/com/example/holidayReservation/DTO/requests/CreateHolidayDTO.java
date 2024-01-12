package com.example.holidayReservation.DTO.requests;

import java.time.LocalDate;

public class CreateHolidayDTO {
	
	private Long location;
	private String title;
	private LocalDate startDate;
	private Integer duration;
	private String price;
	private Integer freeSlots;
	
	public CreateHolidayDTO(Long location, String title, LocalDate startDate, Integer duration, String price,
			Integer freeSlots) {
		this.location = location;
		this.title = title;
		this.startDate = startDate;
		this.duration = duration;
		this.price = price;
		this.freeSlots = freeSlots;
	}
	
	public CreateHolidayDTO() {}

	public Long getLocation() {
		return location;
	}

	public void setLocation(Long location) {
		this.location = location;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Integer getFreeSlots() {
		return freeSlots;
	}

	public void setFreeSlots(Integer freeSlots) {
		this.freeSlots = freeSlots;
	} 
	
	
}
