package com.zsgs.interviewApplicationPanel.login;

import java.util.Scanner;

import com.zsgs.interviewApplicationPanel.interview.InterviewView;
import com.zsgs.interviewApplicationPanel.models.Interviews;

public class LoginView {
	
	LoginModel loginModel;
	
	public LoginView(){
		loginModel = new LoginModel(this);
	}
	
	public void onInit(){
		Scanner sc  = new Scanner(System.in);
		System.out.println("\nHello! Welcome to the Interview Panel Application!");
		System.out.println("You are logging in as:\n1. Interviewer\n2. Receptionist\n3. Exit\nEnter your choice: ");
		int ch = sc.nextInt();
		switch(ch)
		{
		case 1: 
			interviewer();
			break;
		case 2:
			receptionist();
			break;
		case 3: 
			System.out.println("Thank you for using interview Panel Application. Come again! :)");
			break;
		default:
			System.out.println("Invalid choice. Please enter a valid choice!");
			onInit();
			break;
		}
	}
	
	void interviewer() {
		System.out.println("\nHello interviewer! Would you like to:\n1. Sign-up\n2. Login\n3. Go back\nEnter your choice: ");
		Scanner sc  = new Scanner(System.in);
		int ch = sc.nextInt();
		switch(ch)
		{
		case 1:
			interviewerSignup();
			break;
		case 2: 
			interviewerLogin();
			break;
		case 3:
			onInit();
			break;
		default:
			System.out.println("Invalid choice. Please enter a valid choice.");
			interviewer();
			break;
		}
	}
	
	void interviewerSignup() {
		InterviewView interviewView = new InterviewView();
		System.out.println("To signup, enter the following details: ");
		Scanner sc = new Scanner (System.in);
		System.out.println("Enter your Employee Id: ");
		String id = sc.nextLine();
		System.out.println("Enter your name: ");
		String name = sc.nextLine();
		System.out.println("Enter username: ");
		String username = sc.nextLine();
		System.out.println("Enter a password: ");
		String password = sc.nextLine();
		Interviews interview = loginModel.interviewerSignup(id, name, username, password);
		if(interview!=null)
			interviewView.onInit(interview);		
	}
	
	void interviewerLogin() {
		Scanner sc = new Scanner (System.in);
		System.out.println("To login, enter your credentials.");
		System.out.println("Enter your username: ");
		String username = sc.nextLine();
		System.out.println("Enter your password: ");
		String password = sc.nextLine();
		Interviews interview;
		interview=loginModel.interviewerLogin(username, password);
		InterviewView interviewView = new InterviewView();
		if(interview!=null)
		interviewView.onInit(interview);
	}
	
	void receptionist() {
		System.out.println("\n\nHello receptionist! Choose: \n1. Login\n2. Go Back\nEnter: ");
		Scanner sc = new Scanner(System.in);
		int ch = sc.nextInt();
		sc.nextLine();
		switch(ch) {
		case 1: 
			System.out.print("\nEnter username: ");
			String username = sc.nextLine();
			System.out.print("\nEnter password: ");
			String password = sc.nextLine();
			loginModel.receptionist(username, password);
			break;
		case 2:
			onInit();
			break;
		default:
			System.out.println("Enter a valid choice please.");
			receptionist();
			break;
		}
		
	}
	
	void alert(String msg) {
		System.out.println(msg);
	}
	
	

}
