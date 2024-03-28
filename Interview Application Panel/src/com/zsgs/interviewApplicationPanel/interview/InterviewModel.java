package com.zsgs.interviewApplicationPanel.interview;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import com.zsgs.interviewApplicationPanel.datalayer.Database;
import com.zsgs.interviewApplicationPanel.login.LoginView;
import com.zsgs.interviewApplicationPanel.models.Candidates;
import com.zsgs.interviewApplicationPanel.models.Interviews;

public class InterviewModel {

	InterviewView interviewView;
	// Interviews currentInterview = null;
	// private static String interview;

	InterviewModel(InterviewView interviewView) {
		this.interviewView = interviewView;
	}

	void setInterview(Interviews interview) {
		boolean chk = false;
		
			if (interview.getCandidates().size() == 0) {
				if (interview.getPosition() == null) {
					String position = interviewView.setInterview(interview);
					if (Database.getInstance().setInterview(interview.getUsername(), position)) {
						interviewView.alert("Interview position " + interview.getPosition() + " set.");
						interviewView.onInit(interview);
					} else {
						interviewView.alert("Position not set, please try again.");
						interviewView.onInit(interview);
					}
				} else {
					interviewView.alert("Interview position already set, cannot add again.");
					interviewView.onInit(interview);
				}
			} else {
				interviewView.alert("Cannot add interviews after candidates' arrival.");
				interviewView.onInit(interview);
			}
		
	}

	void checkInterview(Interviews interview, LoginView loginView) {
		if (interview.getPosition() == null) {
			interviewView.alert("Please enter the interview position.");
			interviewView.onInit(interview);
		} else {
			loginView.onInit();
		}
	}

	void checkCompletion(Interviews interview, boolean start) {
		if (interview.getCandidates().size() != 0 && interview.getCandidates().get(0).getComments() != null) {
			interviewView.alert("Interviews have already been conducted. Cannot conduct again.");
			interviewView.onInit(interview);
		} else {
			viewCandidates(interview);
		}
	}

	// finish receptionist
	void viewCandidates(Interviews interview) {
		if (Database.getInstance().getCandidates().size() != 0) {
			for (Candidates candidate : Database.getInstance().getCandidates()) {// getCandidates pass interview
				if (candidate.getPosition().equals(interview.getPosition())) // view list for only that interview
				{
					interviewView.viewCandidate(candidate, interview);
					System.out.println("Candidate " + candidate.getRating() + " " + candidate.getId());
				}

				else {
					interviewView.alert("\nCandidates for this interview have not been added yet.\n");
					interviewView.onInit(interview);
				}
			}
			String name = Database.getInstance().getInterviews().get(0).getCandidates().get(0).getName();
			String rat = Database.getInstance().getInterviews().get(0).getCandidates().get(0).getComments();
			interviewView.onInit(interview);
		} else {
			interviewView.alert("\nCandidates have not been added yet.");
			interviewView.onInit(interview);
		}

	}
	//

	boolean checkOut(LocalTime in, LocalTime out) {
		return (in.isBefore(out));
	}

	void rateInterview(Candidates candidate, Interviews interview, LocalTime in) {
		interviewView.alert("\nEnter a rating from 0-10: ");
		int rating = 0;
		do {
			rating = Integer.parseInt(interviewView.getData());
			if (rating < 0 || rating > 10)
				interviewView.alert("Please enter a value between 0 & 10, inclusive.");
		} while (rating < 0 || rating > 10);
		interviewView.alert("Enter your comments: ");
		String comments = interviewView.getData();
		interviewView.alert("\nCandidate rated.\n");

		LocalTime out = null;
		do {
			interviewView.alert(
					"\nEnter the time of interview completion for the candidate in the format '16:25' or '09:45': ");
			String time = interviewView.getData();
			out = LocalTime.parse(time);
			if (!checkOut(in, out))
				System.out.println("The out time must be after the in time.");
		} while (!checkOut(in, out));

		for (Candidates c : interview.getCandidates()) {
			if (candidate.getId().equals(c.getId())) {
				System.out.println(c.getId() + " hhuhhuhuuh");
				setIn(c, in);
				setOut(c, out);
				c.setRating(rating);
				c.setComments(comments);
				System.out.println("This is check " + getIn(c) + " " + getOut(c) + " null " + c.getRating() + " ttt "
						+ c.getComments());
			}
		}

	}

	void setIn(Candidates candidate, LocalTime time) {
		candidate.setIn(Date.from(time.atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant()));
	}

	LocalTime getIn(Candidates candidate) {
		if (candidate.getIn() != null)
			return LocalDateTime.ofInstant(candidate.getIn().toInstant(), ZoneId.systemDefault()).toLocalTime();
		return null;
		// return null;
	}

	void setOut(Candidates candidate, LocalTime time) {
		candidate.setOut(Date.from(time.atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant()));
	}

	LocalTime getOut(Candidates candidate) {
		if (candidate.getOut() != null)
			return LocalDateTime.ofInstant(candidate.getOut().toInstant(), ZoneId.systemDefault()).toLocalTime();
		return null;
		// return null;
	}

	void setArrival(Candidates candidate, LocalTime time) {
		candidate.setArrival(Date.from(time.atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant()));
	}

	LocalTime getArrival(Candidates candidate) {
		if (candidate.getArrival() != null)
			return LocalDateTime.ofInstant(candidate.getArrival().toInstant(), ZoneId.systemDefault()).toLocalTime();
		return null;
	}

}
