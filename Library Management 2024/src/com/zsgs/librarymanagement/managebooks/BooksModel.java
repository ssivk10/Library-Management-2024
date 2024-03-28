package com.zsgs.librarymanagement.managebooks;

import java.util.ArrayList;
import java.util.Collections;

import com.zsgs.librarymanagement.datalayer.Database;
import com.zsgs.librarymanagement.models.Books;
import com.zsgs.librarymanagement.models.Library;

public class BooksModel {

	BooksView booksView;
	Library library;
	Books book;
	Database database = Database.getInstance();

	public BooksModel(BooksView booksView) {
		this.booksView = booksView;
	}

	void addBook(Library library) {
		this.library = library;
		book = new Books();
		do {
			booksView.alert("Enter name of the book: ");
			book.setName(booksView.getData());
		} while (book.getName().length() < 2);
		booksView.alert("Enter author of the book: ");
		book.setAuthor(booksView.getData());
		booksView.alert("Enter publication of the book: ");
		book.setPublication(booksView.getData());
		booksView.alert("Enter edition of the book: ");
		book.setEdition(booksView.getData());
		booksView.alert("Enter availability count of the book (numeric): ");
		String avail = booksView.getData();
		book.setAvailability(Integer.parseInt(avail));
		ArrayList<String> idL = new ArrayList<String>();
		String id = "B";
		for (int i = 0; i < book.getAvailability(); i++) {
			int temp = library.getBooks().size() + 1;
			id += temp;
			if (i + 1 <= 99)
				id += 0;
			temp = i + 1;
			id += temp;
			idL.add(id);
			id = "B";
		}
		book.setId(idL);
		booksView.alert("Enter genre of the book: ");
		book.setGenre(booksView.getData());
		booksView.alert("Enter volume of the book (numeric): ");
		String vol = booksView.getData();
		book.setVolume(Integer.parseInt(vol));
		library.getBooks().add(book);
		database.setLibrary();
		booksView.alert("Book added successfully!");
		
		booksView.onInit(library);
	}

	// ------------------------------------------------------------------------------------------------------------------------
	void viewBooks(Library library) {
		booksView.alert("Name\tAuthor\tPublication\tEdition\tAvailability\tGenre\tVolume");
		for (Books book : library.getBooks()) {
			booksView.viewBook(book);
		}
		booksView.alert("---------------------------------------------------------------------------------\n");
		booksView.onInit(library);
	}

	// ---------------------------------------------
	void editAvail(Library library) {
		booksView.alert("Enter name of book to edit availability of: ");
		boolean chk = true;
		String name = booksView.getData();
		for (Books book : library.getBooks()) {
			if (book.getName().equalsIgnoreCase(name)) {
				chk = true;
				booksView.alert("Enter new availability: ");
				String avail = booksView.getData();
				int old = book.getAvailability();
				book.setAvailability(Integer.parseInt(avail));
				//
				if (Integer.parseInt(avail) > old) {
					String id = "B";
					for (int i = old; i < book.getAvailability(); i++) {
						int temp = library.getBooks().indexOf(book) + 1;
						id += temp;
						if (i + 1 <= 9)
							id += 0;
						temp = i + 1;
						id += temp;
						book.getId().add(id);
						id = "B";
					}
				} else {
					for (int i = old - 1; i >= book.getAvailability(); i--) {
						book.getId().remove(i);
					}
					Collections.sort(book.getId());
				}
				//
				break;
			}
		}
		if (!chk)
			booksView.alert(":( Book not found.");
		database.setLibrary();
		booksView.onInit(library);
	}
	
	//----------------------------------------------------------------------------------------
	void search(Library library) {
		booksView.alert("Enter name of book to search for: ");
		String name = booksView.getData();
		boolean chk = false;
		for(Books book: library.getBooks()) {
			if(book.getName().equalsIgnoreCase(name) || book.getName().contains(name)) {
				chk=true;
				booksView.viewBook(book);
			}
		}
		if(!chk)
			booksView.alert("No results were found for the search "+name);
		booksView.onInit(library);
		
	}
}
