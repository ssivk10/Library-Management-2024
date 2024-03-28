package com.zsgs.librarymanagement.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Users {
	private String id;
	private String name;
	private long phone;
	private ArrayList<Dues> dues = new ArrayList<Dues>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public ArrayList<Dues> getDues() {
		return dues;
	}

	public void setDues(ArrayList<Dues> dues) {
		this.dues = dues;
	}

}
