package com.zsgs.librarymanagement;

import com.zsgs.librarymanagement.login.LoginView;

//mine
public class LibraryManagement2024 {

	private static LibraryManagement2024 libraryManagement;
	
	//app name and version
	
	private LibraryManagement2024() {
		
	}
	
	private static LibraryManagement2024 getInstance()
	{
		if(libraryManagement==null)
			libraryManagement=new LibraryManagement2024();
		return libraryManagement;
	}
	
	private void viewInit()
	{
		LoginView loginView = new LoginView();
		loginView.onInit();
	}
	
	public static void main(String[] args) {
		getInstance().viewInit();
		//calling a private method by creating or returning (RETRIEVING!!!!)
		//an object of this class
	}

}