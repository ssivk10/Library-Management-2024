package com.zsgs.interviewApplicationPanel.models;

import java.util.ArrayList;
import java.util.LinkedList;
//interview
import java.util.List;
import java.util.Queue;

public class Interviews{
	private String id;
	private String name;
	private String username;
	private String password;
	private String position;
	private ArrayList<Candidates> candidates = new ArrayList<Candidates>();
	
	// Getter setter methods

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public ArrayList<Candidates> getCandidates() {
		return candidates;
	}

	public void setCandidates(ArrayList<Candidates> candidates) {
		this.candidates = candidates;
	}

	
}
