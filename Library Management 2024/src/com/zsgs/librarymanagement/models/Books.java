package com.zsgs.librarymanagement.models;

import java.util.ArrayList;

public class Books {
	
	private String name;
	private String author;
	private String publication;
	private String edition;
	private int availability;
	private ArrayList<String> id= new ArrayList<String>();
	private String genre;
	private int volume;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublication() {
		return publication;
	}

	public void setPublication(String publication) {
		this.publication = publication;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}
	
	public ArrayList<String> getId() {
		return id;
	}

	public void setId(ArrayList<String> id) { //one by one or all at once????
		this.id = id; //all at once i guess ??
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}
}
