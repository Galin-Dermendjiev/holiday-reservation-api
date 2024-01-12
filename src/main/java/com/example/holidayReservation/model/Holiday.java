package com.example.holidayReservation.model;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Holiday {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private Integer duration;
	private Integer freeSlots;
	private Double price;
	private LocalDate startDate;
	
	@ManyToOne
	private Location location;
 
	
	public Holiday(long id, String title, int duration, int freeSlots, double price, LocalDate startDate,
			Location location) {
		super();
		this.id = id;
		this.title = title;
		this.duration = duration;
		this.freeSlots = freeSlots;
		this.price = price;
		this.startDate = startDate;
		this.location = location;
	}

	public Holiday(String title, int duration, int freeSlots, double price, LocalDate startDate,
			Location location) {
		this.title = title;
		this.duration = duration;
		this.freeSlots = freeSlots;
		this.price = price;
		this.startDate = startDate;
		this.location = location;
	} 
	
	public Holiday() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getFreeSlots() {
		return freeSlots;
	}

	public void setFreeSlots(int freeSlots) {
		this.freeSlots = freeSlots;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Holiday [id=" + id + ", title=" + title + ", duration=" + duration + ", freeSlots=" + freeSlots
				+ ", price=" + price + ", startDate=" + startDate + ", location=" + location + "]";
	}
	
	
	
}
