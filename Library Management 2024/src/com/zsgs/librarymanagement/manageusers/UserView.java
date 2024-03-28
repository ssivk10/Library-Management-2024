package com.zsgs.librarymanagement.manageusers;

import java.util.Scanner;

import com.zsgs.librarymanagement.models.Users;
import com.zsgs.librarymanagement.login.LoginView;
import com.zsgs.librarymanagement.models.Books;
import com.zsgs.librarymanagement.models.Dues;
import com.zsgs.librarymanagement.models.Library;

public class UserView {

	UserModel userModel;
	Library library;
	Scanner sc = new Scanner(System.in);

	public UserView() {
		userModel = new UserModel(this);
	}

	public void onInit(Library library) {
		this.library = library;
		System.out.println("\n--------------------------------Manage Books--------------------------------");
		System.out.println(
				"\n1. Add users\n2. View users\n3. Checkout/Return\n4. Search Users\n5. Go Back\nEnter your choice: ");
		int ch = sc.nextInt();
		sc.nextLine();
		switch (ch) {
		case 1:
			userModel.addUser(library);
			break;
		case 2:
			userModel.viewUsers(library);
			break;
		case 3:
			checkReturn();
			break;
		case 4:
			userModel.search(library);
			break;
		case 5:
			LoginView loginView = new LoginView();
			loginView.adminMenu(library);
			break;
		default:
			System.out.println("Invalid option. Please enter a valid choice.");
			onInit(library);
			break;
		}
		System.out.println();
	}

	String getData() {
		return sc.nextLine();
	}

	void alert(String msg) {
		System.out.println(msg);
	}

	void viewUser(Users user) {
		int c=0;
		for(Dues d:user.getDues()) {
			if(d.getIn()==null)
				c++;			
		}
		System.out.printf("| %-20s | %6s | %15s | %6s |%n", user.getName(), user.getId(), user.getPhone(),
				c);
	}
	
	void viewBook(Books book) {
		System.out.printf("| %-20s | %-20s | %-10s | %4s | %3s | %13s | %3s |%n", book.getName(), book.getAuthor(),  book.getPublication(), book.getEdition(), book.getAvailability(), book.getGenre(), book.getVolume());
		System.out.println("\n\nIds are:");
		for(String id:book.getId()) {
			System.out.println(id+ " yo.");
		}
		System.out.println("\n\n");
	}

	void checkReturn() {
		System.out.println("Would you like to:\n1. Checkout a book\n2. Return a book\n3. Go back\nEnter your choice: ");
		int ch = sc.nextInt();
		sc.nextLine();
		switch (ch) {
		case 1:
			userModel.checkOut(library);
			break;
		case 2:
			userModel.returnBook(library);
			break;
		case 3:
			LoginView loginView = new LoginView();
			loginView.adminMenu(library);
			break;
		default:
			System.out.println("Invalid choice. Please enter a valid option.");
			checkReturn();
			break;
		}
	}

}
