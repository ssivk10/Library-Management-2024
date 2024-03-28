package com.zsgs.interviewApplicationPanel.interview;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import com.zsgs.interviewApplicationPanel.datalayer.Database;
import com.zsgs.interviewApplicationPanel.login.LoginView;
import com.zsgs.interviewApplicationPanel.models.Candidates;
import com.zsgs.interviewApplicationPanel.models.Interviews;

public class InterviewView {

	InterviewModel interviewModel;
	LoginView loginView;
	Interviews interview;
	private boolean start = false;
	Scanner sc = new Scanner(System.in);

	public InterviewView() {
		interviewModel = new InterviewModel(this);
		loginView = new LoginView();
	}

	public void onInit(Interviews interview) {
		start = false;
		this.interview = interview;
		if(interview.getCandidates().size()!=0)
		System.out.println(interview.getCandidates().size()+" size name "+interview.getCandidates().get(0).getName());
		System.out.println("\nWelcome interviewer! ");
		System.out.println(
				"Make your choice:\n1. Add Interview\n2. View Candidates\n3. Conduct Interviews\n4. Logout\n ");// view
																												// add
																												// etc
		System.out.println(
				"Warning, interviews can only be added if the candidates have not already arrived.\n\nEnter your choice: ");
		int ch = sc.nextInt();
		sc.nextLine();
		switch (ch) {
		case 1:
			interviewModel.setInterview(interview); // add interview - first login
			break;
		case 2:
			interviewModel.viewCandidates(interview); // view candidates per interview
			break;
		case 3:
			// conduct interview
			start = true;
			interviewModel.checkCompletion(interview, start);
			start = false;
			//interview.setCandidates(interview.getCandidates());
			break;
		case 4:
			interviewModel.checkInterview(interview, loginView);
			break;
		default:
			onInit(interview);
			break;
		}
	}

	String setInterview(Interviews interview) {
		System.out.println("Enter the interview topic: ");
		String position = sc.nextLine();
		return position;
	}

	// finish receptionist

	void viewCandidate(Candidates candidate, Interviews interview) { // for all candidates and individual
		
		LocalTime in = null;
		if (start) {
			do { 
				System.out.println("Enter interview start time for candidate "+candidate.getId()+" of name "+candidate.getName()+":");
				String inTime = sc.nextLine();
				in = LocalTime.parse(inTime);
			} while (!interviewModel.checkOut(interviewModel.getArrival(candidate), in));
			System.out.println("ID\tName\tEmail\tPosition\tCGPA\tArrival Time\tIn Time\tOut Time");
		} else
			System.out.println("\nID\tName\tEmail\tPosition");
		System.out.print(candidate.getId() + "\t");
		System.out.print(candidate.getName() + "\t");
		System.out.print(candidate.getEmail() + "\t");
		System.out.print(candidate.getPosition() + "\t");
		if (candidate.getComments() != null) {
			System.out.println("\n\nRatings: " + candidate.getRating());
			System.out.println("Comments: " + candidate.getComments());
		}
		if (start) {
			System.out.print(candidate.getCgpa() + "\t");
			System.out.print(interviewModel.getArrival(candidate) + "\t");
			System.out.print(interviewModel.getIn(candidate) + "\t");
			System.out.print(interviewModel.getOut(candidate) + "\t");
			interviewModel.rateInterview(candidate, interview, in);
			Database.getInstance().setInterviews();
			System.out.println("Candidate rated.");
		}
	}

	void alert(String msg) {
		System.out.println(msg);
	}
	
	String getData() {
		return sc.nextLine();
	}

}
