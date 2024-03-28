package com.zsgs.librarymanagement.appadmin;

import java.util.Scanner;

import com.zsgs.librarymanagement.login.LoginView;
import com.zsgs.librarymanagement.models.Admin;
import com.zsgs.librarymanagement.models.Library;

public class AdminView {
	
	AdminModel adminModel;
	Scanner sc =  new Scanner(System.in);
	
	public AdminView() {
		adminModel = new AdminModel(this);
	}
	
	public void onInit() {
		System.out.println("Hello admin!");	
		System.out.println("What would you like to do?\n1. View all Libraries\n2. View all admins\n3. Logout");
		int ch = sc.nextInt();
		sc.nextLine();
		switch(ch)
		{
		case 1:
			adminModel.viewLibraries();
			break;
		case 2:
			adminModel.viewAdmins();
			break;
		case 3:
			LoginView loginView = new LoginView();
			loginView.onInit();
			break;
		default:
			System.out.println("Invalid choice, please enter a valid choice.");
			onInit();
			break;
		}
	}
	
	void viewLibraries(Library l) {
		System.out.printf("| %-5s | %-20s | %-20s | %14s | %14s |\n",l.getId(),l.getName(),l.getEmail(),l.getBooks().size(),l.getUsers().size());
	}
	
	void viewAdmins(Admin a) {
		System.out.printf("| %-5s | %-20s | %-20s | %14s |\n",a.getId(),a.getName(),a.getUsername(),a.getLibraryId());
	}
	
	void printF(String msg) {
		System.out.printf(msg);
	}

}
