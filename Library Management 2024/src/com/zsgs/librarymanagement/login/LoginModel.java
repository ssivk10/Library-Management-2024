package com.zsgs.librarymanagement.login;

import com.zsgs.librarymanagement.appadmin.AdminView;
import com.zsgs.librarymanagement.datalayer.Database;
import com.zsgs.librarymanagement.models.Admin;
import com.zsgs.librarymanagement.models.Library;

public class LoginModel {

	LoginView loginView;
	Database database = Database.getInstance();
	Admin admin;

	LoginModel(LoginView loginView) {
		this.loginView = loginView;
	}

	void signUp(String name, String username, String password) {
		if (!database.getUsernames().contains(username) && !username.equals("main")) {
			if (username.length() >= 3 && password.length() >= 3) {
				Admin admin = new Admin();
				String id = "A";
				if (database.getAdmins().size() < 9) {
					id += 0;
				}
				int temp = database.getAdmins().size() + 1;
				id += temp;
				admin.setId(id);
				admin.setName(name);
				admin.setUsername(username);
				admin.setPassword(password);
				database.getAdmins().add(admin);
				this.admin = admin;
				database.getUsernames().add(username);
				database.setUsername();
				loginView.successful(admin);
			} else {
				loginView.alert("Username and password must contain atleast three characters.");
				loginView.signUp();
			}
		} else {
			loginView.alert("Username already taken.");
			loginView.onInit();
		}
	}

	void login(String username, String password) {
		Admin admin = validateUsername(username);
		if (admin != null) {
			if (admin.getPassword().equals(password)) {
				loginView.successful(admin);

			} else {
				loginView.alert("\nLogin unsuccessful. Incorrect password.\n");
				loginView.onInit();
			}
		} else {
			loginView.alert("\nUsername not found.\n");
			loginView.onInit();
		}
	}

	private Admin validateUsername(String username) {
		for (Admin admin : database.getAdmins()) {
			if (admin.getUsername().equals(username)) {
				this.admin = admin;
				return admin;
			}
		}
		return null;
	}

	Library librarySetup() {
		if (admin.getLibraryId() != null) {
			for (Library library : database.getLibraries()) {
				if (library.getId().equals(admin.getLibraryId())) {
					return library;
				}
			}
			return null;
		} else {
			loginView.alert("Enter name and email ID of the library.");
			String name = loginView.getData();
			String email = loginView.getData();
			if (name.length() < 2 || email.length() < 11) {
				loginView.alert("Name should be atleast 2 letters long. Email should follow xyz@xyz.co format. ");
				librarySetup();
				return null;
			} else {
				String id = "L";
				if (database.getLibraries().size() < 9)
					id += 0;
				int temp = database.getLibraries().size() + 1;
				id += temp;
				Library library = new Library();
				library.setId(id);
				library.setName(name);
				library.setEmail(email);
				admin.setLibraryId(id);
				database.setAdmin();
				database.getLibraries().add(library);
				database.setLibrary();
				loginView.alert("Library added successfully.");
				return library;
			}
		}

	}
	
	void mainAdmin() {
		loginView.alert("Enter username: ");
		String username = loginView.getData();
		loginView.alert("Enter username: ");
		String password = loginView.getData();
		if(username.equals("main")&& password.equals("pw@1"))
		{
			AdminView adminView = new AdminView();
			adminView.onInit();
		}
		else {
			loginView.alert("Incorrect username or password.");
			loginView.onInit();
		}
	}

}
