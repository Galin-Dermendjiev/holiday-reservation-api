package com.example.holidayReservation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String country;
	private String city;
	private String street;
	private String number;
	private String imageUrl;
	
	public Location(long id, String country, String city, String street, String number, String imageUrl) {
		super();
		this.id = id;
		this.country = country;
		this.city = city;
		this.street = street;
		this.number = number;
		this.imageUrl = imageUrl;
	}

	public Location(String country, String city, String street, String number, String imageUrl) {
		this.country = country;
		this.city = city;
		this.street = street;
		this.number = number;
		this.imageUrl = imageUrl;
	}
	
	public Location() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", country=" + country + ", city=" + city + ", street=" + street + ", number="
				+ number + ", imageUrl=" + imageUrl + "]";
	}
	
	
	
}
