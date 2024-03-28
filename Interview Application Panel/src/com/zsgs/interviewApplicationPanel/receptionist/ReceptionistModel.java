package com.zsgs.interviewApplicationPanel.receptionist;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import com.zsgs.interviewApplicationPanel.datalayer.Database;
import com.zsgs.interviewApplicationPanel.models.Candidates;
import com.zsgs.interviewApplicationPanel.models.Interviews;

public class ReceptionistModel {

	ReceptionistView receptionistView;
	private ArrayList<Candidates> candidates = Database.getInstance().getCandidates();
	private ArrayList<Interviews> interviews = Database.getInstance().getInterviews();
	private Candidates candidate = null;
	private static int count = 0;

	ReceptionistModel(ReceptionistView receptionistView) {
		this.receptionistView = receptionistView;
	}

	void addCandidates(String name, String id, String email, double cgpa, LocalTime arrival) {
		Candidates candidate = new Candidates(); // called from checkInterviewsExist
		boolean chk = false;
		candidate.setId(id);
		candidate.setName(name);
		candidate.setEmail(email);
		candidate.setCgpa(cgpa);
		setArrival(candidate, arrival);
		for (Candidates candidate1 : candidates) {
			if (candidate1.getId().equals(id))
				chk = true;
		}
		if (chk) {
			receptionistView.alert("\nCandidate " + id + " already exists.\n");
			receptionistView.addCandidates();
		} else {
			this.candidate = candidate;
			receptionistView.addPosition();
		}
	}

	void viewInterviews() { // choice 2 onInit
		if (interviews.size() == 0) {
			receptionistView.alert("\nNo interviews to view yet.\n");
			receptionistView.onInit();
		} else {
			System.out.println("\nID\tname\tusername\tposition\tcandidate count");
			for (Interviews interview : interviews) {
				receptionistView.viewInterviews(interview);
			}
			receptionistView.onInit();
		}
	}

	void viewInterviews(int choice) { // viewing while adding candidates

		receptionistView.alert("ID\tName\tUsername\tPosition\tCandidate count");
		for (Interviews interview : interviews) {
			receptionistView.viewInterviews(interview);
		}
	}

	void checkInterviewsExist() { // called when adding candidates, choice 1 onInit
		if (interviews.size() == 0) {
			receptionistView.alert("\nNo interviews scheduled for today. Candidates cannot be added.\n");
			receptionistView.onInit();
		} else {
			receptionistView.addCandidates();
		}
	}

	void viewCandidates() {
		if (candidates.size() == 0) {
			receptionistView.alert("\nNo candidates to view yet.\n");
			receptionistView.onInit();
		} else {
			receptionistView.alert("\nId\tName\tPosition\tArrival Time\n");
			for (Candidates candidate : candidates) {
				receptionistView.viewCandidates(candidate);
			}
			receptionistView.onInit();
		}
	}

	void addPosition(String id) { // add position and sort into interview lists
		for (Interviews interview : interviews) {
			if (interview.getId().equals(id)) {
				candidate.setPosition(interview.getPosition());
				if (Database.getInstance().addCandidate(candidate)) {
					System.out.println("Before " + interview.getCandidates().size());
					interview.getCandidates().add(candidate);
					Database.getInstance().setInterviews();
					System.out.println("After " + interview.getCandidates().size());
					receptionistView.alert("\nCandidate " + candidate.getId() + " successfully added.\n");
					count = 0;
				} else {
					receptionistView.alert("\nNo interview with ID " + id + " exists.\n");
					viewInterviews(1);
				}
				receptionistView.onInit();
				return;
			}
		}
		count++;
		if (count != 0 && count <= 3) {
			receptionistView.alert("No interview with ID " + id + " found. Please enter the right interview ID.");
			receptionistView.addPosition();
			return;
		} else if (count != 0) {
			receptionistView.alert("Number of tries exceeded. Candidate will not be added.");
			candidates.remove(candidate);
			Database.getInstance().setInterviews();
			return;
		}

	}
	// ---------

	

	void setArrival(Candidates candidate, LocalTime time) {
		candidate.setArrival(Date.from(time.atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant()));
	}
	
	LocalTime getArrival(Candidates candidate) {
		if (candidate.getArrival() != null)
			return LocalDateTime.ofInstant(candidate.getArrival().toInstant(), ZoneId.systemDefault()).toLocalTime();
		return null;
	}

}
