package com.zsgs.librarymanagement.datalayer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zsgs.librarymanagement.models.Admin;
import com.zsgs.librarymanagement.models.Library;

public class Database {

	private static Database database;
	private static ArrayList<Admin> admins = new ArrayList<Admin>();
	private static ArrayList<String> usernames = new ArrayList<String>();
	private static ArrayList<Library> libraries = new ArrayList<Library>();
	private File adminsf = new File(
			"C:\\Users\\Sivakumar\\eclipse-workspace\\Library Management 2024\\src\\com\\zsgs\\librarymanagement\\datalayer\\Admins.json");
	private File librariesf = new File(
			"C:\\Users\\Sivakumar\\eclipse-workspace\\Library Management 2024\\src\\com\\zsgs\\librarymanagement\\datalayer\\Libraries.json");
	private File usernamesf = new File(
			"C:\\Users\\Sivakumar\\eclipse-workspace\\Library Management 2024\\src\\com\\zsgs\\librarymanagement\\datalayer\\Usernames.json");

	ObjectMapper map = new ObjectMapper();

	public static Database getInstance() {
		if (database == null)
			database = new Database();
		return database;
	}

	// --------------------------------------------------------------------

	private ArrayList<Admin> getAdminJSON() {
		try {
			if (adminsf.length() != 0)
				return map.readValue(adminsf, new TypeReference<ArrayList<Admin>>() {
				});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return admins;
	}

	public void setAdmin() {
		try {
			map.writeValue(adminsf, admins);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Admin> getAdmins() {
		admins = getAdminJSON();
		return admins;
	}

	// ----------------------------------------------------------------------

	private ArrayList<String> getUsernameJSON() {
		try {
			if (usernamesf.length() != 0)
				return map.readValue(usernamesf, new TypeReference<ArrayList<String>>() {
				});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return usernames;
	}

	public void setUsername() {
		try {
			map.writeValue(usernamesf, usernames);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getUsernames() {
//		return usernames;
		usernames = getUsernameJSON();
		return usernames;
	}

	// ---------------------------------------------------------------------

	private ArrayList<Library> getLibraryJSON() {
		try {
			if (librariesf.length() != 0)
				return map.readValue(librariesf, new TypeReference<ArrayList<Library>>() {
				});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return libraries;
	}

	public void setLibrary() {
		try {
			map.writeValue(librariesf, libraries);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Library> getLibraries() {
		libraries = getLibraryJSON();
		return libraries;
	}

}
