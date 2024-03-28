package com.zsgs.interviewApplicationPanel.datalayer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zsgs.interviewApplicationPanel.models.Candidates;
import com.zsgs.interviewApplicationPanel.models.Interviews;

//interview
public class Database {

	ArrayList<Interviews> interviews = new ArrayList<Interviews>();
	ArrayList<Candidates> candidates = new ArrayList<Candidates>();
	private File interviewsf = new File("C:\\Users\\Sivakumar\\eclipse-workspace\\Interview Application Panel\\src\\com\\zsgs\\interviewApplicationPanel\\datalayer\\Interviews.json");
	ObjectMapper map = new ObjectMapper();
	
	//private static String curInterview;

	private static Database database;

	public static Database getInstance() {
		if (database == null)
			database = new Database();
		return database;
	}

	public boolean addInterview(Interviews interview) {
		boolean c= false;
		if (!getInstance().getInterviews().contains(interview)) {
			// System.out.println("interviewer adding "+interview.toString());
			c = interviews.add(interview);
			setInterviews();
			return c;
		}
		return false;
	}

	public boolean addCandidate(Candidates candidate) {
		boolean c = false;
		if (!candidates.contains(candidate)) {
			c = candidates.add(candidate);
			setInterviews();
			return c;
		} 			
		return false;
	}
	
	//-------

	public ArrayList<Interviews> getInterviews() {
		interviews = getInterviewsJSON();
		return interviews;
	}
	
	public ArrayList<Interviews> getInterviewsJSON(){
		try {
			if (interviewsf.length() != 0)
				return map.readValue(interviewsf, new TypeReference<ArrayList<Interviews>>() {
				});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return interviews;
	}
	
	public void setInterviews() {
		try {
			map.writeValue(interviewsf, interviews);
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}
	
	//----------

	public ArrayList<Candidates> getCandidates() {
		return candidates;
	}
	
	/*
	 * public void setCandidates(ArrayList<Candidates> candidates) {
	 * this.candidates=candidates; }
	 */
	
	/*
	 * public ArrayList<Candidates> getCandidatesJSON(){ try { if
	 * (candidatesf.length() != 0) return map.readValue(candidatesf, new
	 * TypeReference<ArrayList<Candidates>>() { }); } catch (IOException e) {
	 * e.printStackTrace(); } return candidates; }
	 * 
	 * public void setCandidates() { try { map.writeValue(candidatesf, candidates);
	 * } catch (IOException e) { e.printStackTrace(); } }
	 */

	/*
	 * public boolean checkUsername(String username) { for (Interviews interview :
	 * interviews) { if (interview.getUsername().equals(username)) return true; }
	 * return false; }
	 * 
	 * // put both in model public Interviews checkPassword(String username, String
	 * password) { for (Interviews interview : interviews) { if
	 * (interview.getUsername().equals(username)) { if
	 * (interview.getPassword().equals(password)) return interview; } } return null;
	 * }
	 */

	//////////

	public boolean setInterview(String username, String position) {
		for (Interviews interview : interviews) {
			if (interview.getUsername().equals(username)) {
				{
					interview.setPosition(position);
					setInterviews();
					return true;
				}

			}
		}
		return false;
	}

	// Receptionist method
	public ArrayList<Candidates> candidateInterviewList(String position) {
		ArrayList<Candidates> cand = new ArrayList<Candidates>();
		for (Candidates candidate : candidates) {
			if (candidate.getPosition().equals(position)) {
				cand.add(candidate);
			}
		}
		return cand;
	}

}
