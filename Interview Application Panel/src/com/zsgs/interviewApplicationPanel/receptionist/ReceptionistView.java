package com.zsgs.interviewApplicationPanel.receptionist;

import java.time.LocalTime;
import java.util.Scanner;

import com.zsgs.interviewApplicationPanel.login.LoginView;
import com.zsgs.interviewApplicationPanel.models.Candidates;
import com.zsgs.interviewApplicationPanel.models.Interviews;

public class ReceptionistView {
	
	private ReceptionistModel receptionistModel;
	private Candidates candidates;
	
	public ReceptionistView() {
		receptionistModel = new ReceptionistModel(this);
	}
	
	public void onInit() {
		System.out.flush();
		System.out.println("\nWelcome, receptionist!\n");
		System.out.println("What would you like to do?\n1. Add Candidates\n2. View Candidates\n3. View upcoming interviews\n4. Go back\n\nChoose an option: ");
		Scanner sc = new Scanner(System.in);
		int ch=sc.nextInt();
		switch(ch) {
		case 1:
			receptionistModel.checkInterviewsExist(); //to add the candidates
			break;
		case 2:
			receptionistModel.viewCandidates(); //to view all the candidates
			break;
		case 3: 
			receptionistModel.viewInterviews();//view all interviews
			break;
		case 4:
			LoginView loginView = new LoginView(); //go back
			loginView.onInit();
			break;
		default:
			System.out.println("Please enter a valid choice.");
			onInit();
			break;
		}
	}
		
	void addCandidates() {
		System.out.println("Enter the candidate details: ");
		System.out.println("Name: ");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		System.out.println("ID: ");
		String id = sc.nextLine();
		System.out.println("Email: ");
		String email = sc.nextLine();
		System.out.println("CGPA: ");
		double cgpa = sc.nextDouble();
		sc.nextLine();
		System.out.println("Arrival Time: ");
		String arrivalT = sc.nextLine();
		LocalTime arrival = LocalTime.parse(arrivalT);
		receptionistModel.addCandidates(name, id, email, cgpa, arrival);
	}
	
	void addPosition() {
		Scanner sc = new Scanner(System.in);
		System.out.println();
		receptionistModel.viewInterviews(1);
		System.out.println("Enter interview ID from the above list: ");
		String interviewId = sc.nextLine();
		receptionistModel.addPosition(interviewId);
	}
	
	void viewCandidates(Candidates candidate) {
		System.out.print(candidate.getId()+"\t");
		System.out.print(candidate.getName()+"\t");
		System.out.print(candidate.getPosition()+"\t");
		System.out.print(receptionistModel.getArrival(candidate)+"\t\n");
	}
	
	void viewInterviews(Interviews interview) {
		System.out.print(interview.getId()+"\t"+interview.getName()+"\t");
		System.out.print(interview.getUsername()+"\t");
		System.out.print(interview.getPosition()+"\t");
		if(interview.getCandidates()==null)
			System.out.println(0);
		else
			System.out.println(interview.getCandidates().size());
	}
	
	//alert
	void alert(String msg) {
		System.out.println(msg);
	}
	
	

}
