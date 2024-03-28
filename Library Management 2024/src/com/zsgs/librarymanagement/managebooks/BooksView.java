package com.zsgs.librarymanagement.managebooks;

import java.util.Scanner;

import com.zsgs.librarymanagement.login.LoginView;
import com.zsgs.librarymanagement.models.Books;
import com.zsgs.librarymanagement.models.Library;

public class BooksView {
	
	Scanner sc = new Scanner(System.in);
	BooksModel booksModel;
	Library library;
	
	public BooksView() {
		booksModel = new BooksModel(this);
	}
	
	public void onInit(Library library) {
		this.library = library;
		System.out.println("\n--------------------------------Manage Books--------------------------------");
		System.out.println("\n1. Add books\n2. View all books\n3. Edit availability\n4. Search Books\n5. Go Back\nEnter your choice: ");
		int ch = sc.nextInt();
		sc.nextLine();
		switch(ch)
		{
		case 1:
			booksModel.addBook(library);
			break;
		case 2:
			booksModel.viewBooks(library);
			break;
		case 3:
			booksModel.editAvail(library);
			break;
		case 4:
			booksModel.search(library);
			break;
		case 5:
			LoginView l = new LoginView();
			l.adminMenu(library);
		default:
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
	
	void viewBook(Books book) {
		System.out.printf("| %-40s | %-20s | %-10s | %4s | %3s | %13s | %3s |%n", book.getName(), book.getAuthor(),  book.getPublication(), book.getEdition(), book.getAvailability(), book.getGenre(), book.getVolume());
		
		System.out.println("\n\n");
	}
	

}
