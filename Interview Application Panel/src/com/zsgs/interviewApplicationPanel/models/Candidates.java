package com.zsgs.interviewApplicationPanel.models;

import java.time.LocalTime;
import java.util.Date;

public class Candidates {

	private String name;
	private String id;
	private String email;
	private double cgpa;
	private int rating;
	private String comments;
	private Date arrival;
	private Date in;
	private Date out;
	private String position;
	
	public Candidates(){
		
	}

	public String getName() { //Name
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() { //ID
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() { //Email
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getCgpa() { //CGPA
		return cgpa;
	}

	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}

	public int getRating() { //Rating
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComments() { //Comments
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getArrival() { //Arrival Time
		return arrival;
	}

	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}

	public Date getIn() { //In Time
		return in;
	}

	public void setIn(Date in) {
		this.in = in;
	}

	public Date getOut() { //Out Time
		return out;
	}

	public void setOut(Date out) {
		this.out = out;
	}
	
	public String getPosition() { //Name
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	
}
