package com.zsgs.interviewApplicationPanel.login;

import java.util.Scanner;

import com.zsgs.interviewApplicationPanel.datalayer.Database;
import com.zsgs.interviewApplicationPanel.models.Interviews;
import com.zsgs.interviewApplicationPanel.receptionist.ReceptionistView;

public class LoginModel {

	LoginView loginView;

	LoginModel(LoginView loginView) {
		this.loginView = loginView;
	}

	Interviews interviewerLogin(String username, String password) {
		if (checkUsername(username)) {
			if (checkPassword(username, password) != null) {
				loginView.alert("Login successful!");
				Interviews interview = checkPassword(username, password);
				return interview;

			} else {
				loginView.alert("Incorrect password.");
				loginView.interviewer(); // calls interviewer menu again
				return null;
			}
		} else {
			loginView.alert("Incorrect username.");
			loginView.interviewer(); // calls interviewer menu again
			return null;
		}

	}

	public boolean checkUsername(String username) {
		for (Interviews interview : Database.getInstance().getInterviews()) {
			if (interview.getUsername().equals(username))
				return true;
		}
		return false;
	}

	// put both in model
	public Interviews checkPassword(String username, String password) {
		for (Interviews interview : Database.getInstance().getInterviews()) {
			if (interview.getUsername().equals(username)) {
				if (interview.getPassword().equals(password))
					return interview;
			}
		}
		return null;
	}

	Interviews interviewerSignup(String id, String name, String username, String password) {
		boolean chk = false;

		Interviews interview = new Interviews();
		interview.setId(id);
		interview.setName(name);
		interview.setUsername(username);
		interview.setPassword(password);
		for (Interviews i : Database.getInstance().getInterviews())
			if (i.getId().equals(interview.getId()))
				chk = true;
		if (!chk) {
			if (Database.getInstance().addInterview(interview)) {
				loginView.alert("Sign-up successful, you are now logged in!");
				return interview;
			} else {
				loginView.alert("Sign-up failed, interviewer already exists.");
				loginView.interviewerSignup();
				return null;
			}
		}
		else {
			loginView.alert("Sign-up failed, interviewer already exists.");
			loginView.interviewer();
			return null;
		}
	}

	void receptionist(String username, String password) {
		if (username.equals("receptionist")) {
			if (password.equals("pw@1")) {
				loginView.alert("Login Successful.");
				ReceptionistView receptionistView = new ReceptionistView();
				receptionistView.onInit();
			} else {
				loginView.alert("Incorrect password.");
				loginView.receptionist();
			}
		} else {
			loginView.alert("Incorrect username.");
			loginView.receptionist();
		}
	}

}
