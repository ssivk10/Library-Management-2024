package com.zsgs.librarymanagement.appadmin;

import com.zsgs.librarymanagement.datalayer.Database;
import com.zsgs.librarymanagement.models.Admin;
import com.zsgs.librarymanagement.models.Library;

public class AdminModel {
	
	AdminView adminView;
	Database database = Database.getInstance();
	
	AdminModel(AdminView adminView){
		this.adminView = adminView;
	}
	
	void viewLibraries(){
		System.out.printf("| %-5s | %-20s | %-20s | %14s | %14s |\n", "ID", "Name", "Email-ID", "No. of Books", "No. of Users");
		for(Library l:database.getLibraries()) {
			adminView.viewLibraries(l);
		}
		adminView.onInit();
	}
	
	void viewAdmins() {
		System.out.printf("| %-5s | %-20s | %-20s | %14s |\n", "ID", "Name", "Username", "Library ID");
		for(Admin a:database.getAdmins()) {
			adminView.viewAdmins(a);
		}
		adminView.onInit();
	}

}
