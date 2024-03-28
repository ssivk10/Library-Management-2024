package com.zsgs.librarymanagement.models;

import java.util.ArrayList;

public class Library {
	private String id;
	private String name;
	private String email;
	private ArrayList<Books> books = new ArrayList<Books>();
	private ArrayList<Users> users = new ArrayList<Users>();

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Books> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<Books> books) {
		this.books = books;
	}
	
	public ArrayList<Users> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<Users> users) {
		this.users = users;
	}

}
