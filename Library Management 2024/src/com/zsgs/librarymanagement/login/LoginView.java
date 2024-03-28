package com.zsgs.librarymanagement.login;

import java.util.Scanner;

import com.zsgs.librarymanagement.appadmin.AdminView;
import com.zsgs.librarymanagement.managebooks.BooksView;
import com.zsgs.librarymanagement.manageusers.UserView;
import com.zsgs.librarymanagement.models.Admin;
import com.zsgs.librarymanagement.models.Library;

//import com.zsgs.librarymanagement.librarySetup.LibrarySetupView;

//library
public class LoginView {
	
	LoginModel loginModel;
	Scanner sc = new Scanner(System.in);
	
	public LoginView(){
		loginModel = new LoginModel(this);
	}
	
	public void onInit(){
		System.out.println("Hello admin! Welcome to the Library Management Application! :)");
		System.out.println("Please choose from the following options: \n1. Sign-Up\n2. Login\n3. Login as application admin\n4. Exit Application\nEnter your choice: ");
		int ch = sc.nextInt();
		sc.nextLine();
		switch(ch)
		{
		case 1:
			signUp();
			break;
		case 2:
			login();
			break;
		case 3:
			loginModel.mainAdmin();
			break;
		case 4:
			System.out.println("Thank you! Please come again :D");
			break;
		default:
			System.out.println("Invalid choice, please enter a valid choice.");
			onInit();
			break;
		}
		
		
		//loginModel.validateUser(username, password);		
	}
	
	void signUp() {
		System.out.println("\n------Sign up------\n");
		System.out.println("\nEnter your name: ");
		String name = sc.nextLine();
		System.out.println("Enter a username: ");
		String username=sc.nextLine();
		System.out.println("Enter your password: ");
		String password = sc.nextLine();
		loginModel.signUp(name, username, password);
	}
	
	void login() {
		System.out.println("\n------Login------\n");
		System.out.println("\nEnter your username: ");
		String username = sc.nextLine();
		System.out.println("Enter your password: ");
		String password = sc.nextLine();
		loginModel.login(username, password);
	}
	
	void alert(String msg)
	{
		System.out.println(msg);
	}
	
	String getData() {
		String data = sc.nextLine();
		return data;
	}
	
	
	void successful(Admin admin)
	{
		System.out.flush();
		System.out.println("Successfully logged in!");
		System.out.println("--------------------------------------------------------------------------------------------");
		System.out.println("\nHello! Welcome, "+admin.getName()+".");
		Library library = loginModel.librarySetup();
		adminMenu(library);
	}
	
	public void adminMenu(Library library) {
		System.out.println("Here are your options!\n1. Manage Books\n2. Manage Users\n3. Logout\nEnter your choice:");
		int ch = sc.nextInt();
		sc.nextLine();
		switch(ch)
		{
		case 1:
			BooksView booksView = new BooksView();
			booksView.onInit(library);
			break;
		case 2:
			UserView userView = new UserView();
			userView.onInit(library);
			break;
		case 3:
			onInit();
			break;
		default:
			System.out.println("Invalid choice, please enter a valid option.");
			adminMenu(library);
			break;
		}
	}

}
