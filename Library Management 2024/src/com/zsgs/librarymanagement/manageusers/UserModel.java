package com.zsgs.librarymanagement.manageusers;

import com.zsgs.librarymanagement.models.Users;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Collections;
//import java.util.Date;
import java.util.Date;

import com.zsgs.librarymanagement.datalayer.Database;
import com.zsgs.librarymanagement.models.Books;
import com.zsgs.librarymanagement.models.Dues;
import com.zsgs.librarymanagement.models.Library;

public class UserModel {

	UserView userView;
	Library library;
	Users user;
	Dues due;
	Database database = Database.getInstance();

	Users book = new Users();

	UserModel(UserView userView) {
		this.userView = userView;
	}

	void addUser(Library library) {
		this.library = library;
		user = new Users();
		userView.alert("Add user.");
		userView.alert("Enter name:"); // do while
		user.setName(userView.getData());
		userView.alert("Enter phone number:");
		String ph = userView.getData();
		user.setPhone(Long.parseLong(ph));
		String id = "U";
		if (library.getUsers().size() < 9)
			id += 0;
		int temp = (library.getUsers().size() + 1);
		id += temp;
		user.setId(id);
		library.getUsers().add(user);
		userView.alert("User added successfully!");
		database.setLibrary();
		userView.onInit(library);
	}

	// ------------------------------------------------------------------------------------------------------------------------------

	void viewUsers(Library library) {
		if (library.getUsers().size() == 0) {
			userView.alert("No users have been added.");
			userView.onInit(library);
		}
		userView.alert("--------------------------------------------------------------------------------");
		System.out.printf("\n\n| %-20s | %6s | %15s | %6s |%n", "Name", "ID", "Phone Number", "Dues Remaining");
		for (Users user : library.getUsers()) {
			userView.viewUser(user);
		}
		System.out.println("---------------------------------------------------------------------------------\n");
		userView.onInit(library);
	}

	// ---------------------------------------------------------------------------------------------------------------------------------------

	void checkOut(Library library) {
		if (library.getBooks().size() != 0) {
			System.out.printf("| %-20s | %-20s | %-10s | %4s | %13s | %13s | %6s |%n", "Name", "Author", "Publication",
					"Edition", "Availability", "Genre", "Volume");
			for (Books book : library.getBooks()) {
				userView.viewBook(book);
			}
			userView.alert("---------------------------------------------------------------------------------\n");
			userView.alert("\nEnter the ID of the user to checkout: ");
			String id = userView.getData();
			boolean ck = false;
			for (Users user : library.getUsers()) {
				if (user.getId().equals(id)) {
					this.user = user;
					ck = true;
					break;
				}
			}
			if (ck) {
				userView.alert("\nEnter the name of the book to checkout: ");
				String name = userView.getData();
				boolean chk = false;
				boolean notAlready = false;
				System.out.println(user.getName()+" user");
				for (Dues d : user.getDues()) {
					System.out.println();
					System.out.println(d.getIn());
					System.out.println(getIn(d));
					System.out.println("Marker---------------------");
					System.out.println(d.getBookId()+" "+d.getBookName()+" "+getIn(d)+" "+getOut(d)+"================");
					if (d.getBookName().equals(name) && getIn(d) == null) {
						notAlready = true;
					}
					System.out.println(d.getBookName().equals(name)+" ah "+getIn(d)+" no "+getOut(d));
				}
				for (Books book : library.getBooks()) {
					if (book.getName().equals(name) && !notAlready) {
						chk = true;
						if (book.getAvailability() > 0) {
							due = new Dues();
							due.setBookId(book.getId().get(0));
							due.setBookName(book.getName());
							System.out.println(due.getBookId());
							book.getId().remove(0);
							book.setAvailability(book.getAvailability() - 1);
							userView.alert("\nEnter the date of checkout as dd/mm/yyyy: ");
							String date = userView.getData();
							String[] temp = date.split("/");
							int day = Integer.parseInt(temp[0]);
							int month = Integer.parseInt(temp[1]);
							int year = Integer.parseInt(temp[2]);
							setOut(due, LocalDate.of(year, month, day));
							user.getDues().add(due);
							database.setLibrary();
							userView.alert("Successfully checked out.");
						} else {
							userView.alert("Copies of " + book.getName() + " not available currently, sorry.");
						}
					}
				}
				if (!chk) {
					userView.alert("Book not found.");
				} else if (notAlready)
					userView.alert("Book has already been borrowed.");
			} else {
				userView.alert("User not found.");
			}

		}

		userView.onInit(library);
	}

	void returnBook(Library library) {
		userView.alert("\nEnter the ID of the user to checkout: ");
		String id = userView.getData();
		boolean ck = false;
		for (Users user : library.getUsers()) {
			if (user.getId().equals(id)) {
				this.user = user;
				ck = true;
				break;
			}
		}
		if (ck) {
			userView.alert("\nEnter the name of the book to return: ");
			String name = userView.getData();
			boolean chk = false;
			Books b;
			for (Dues due : user.getDues()) {
				if (due.getBookName().equals(name)) {
					chk = true;
					for (Books book : library.getBooks()) {
						if (book.getName().equals(due.getBookName())) {
							book.getId().add(due.getBookId()); // returning the id to the list
							Collections.sort(book.getId());
							book.setAvailability(book.getAvailability() + 1); // making count accurate
							do {
								userView.alert("\nEnter the date of return as dd/mm/yyyy: ");
								String date = userView.getData();
								String[] temp = date.split("/");
								int day = Integer.parseInt(temp[0]);
								int month = Integer.parseInt(temp[1]);
								int year = Integer.parseInt(temp[2]);
								setIn(due, LocalDate.of(year, month, day));
								if (getOut(due).isAfter(getIn(due)))
									userView.alert("Return date must be after checkout date.");
							} while (getOut(due).isAfter(getIn(due)));
							Period od = Period.between(getOut(due), getIn(due));

							int dueDays = od.getDays() + (od.getMonths() * 30) + (od.getYears() * 365);
							System.out.println(dueDays+" due days");
							if (dueDays > 7)
								due.setOverdueFee((dueDays - 7) * 2.5);
							else
								due.setOverdueFee(0.0);
							database.setLibrary();
						}
					}

				}
			}
		}
		if (!ck) {
			userView.alert("\nUser not found.");
		}
		userView.onInit(library);
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	void search(Library library) {
		userView.alert("Enter name of user to search for: ");
		String name = userView.getData();
		boolean chk = false;
		for (Users user : library.getUsers()) {
			if (user.getName().equalsIgnoreCase(name) || user.getName().contains(name)) {
				chk = true;
				userView.viewUser(user);
			}
		}
		if (!chk)
			userView.alert("No results were found for the search " + name + ".");
		userView.onInit(library);

	}

	// -----------------------------------------------
	LocalDate getIn(Dues due) {
		if(due.getIn()!=null)
		return due.getIn().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return null;
	}

	void setIn(Dues due, LocalDate date) {
		due.setIn(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
	}

	LocalDate getOut(Dues due) {
		if(due.getOut()!=null)
		return due.getOut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return null;
	}

	void setOut(Dues due, LocalDate date) {
		due.setOut(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
	}

}
