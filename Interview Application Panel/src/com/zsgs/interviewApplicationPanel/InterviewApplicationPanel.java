package com.zsgs.interviewApplicationPanel;
import com.zsgs.interviewApplicationPanel.login.LoginView;
//interview
public class InterviewApplicationPanel {
	
	private static InterviewApplicationPanel interviewApplicationPanel;
	
	private InterviewApplicationPanel()
	{
		//to ensure no new instance of this class can be created from elsewhere
	}
	
	private void onInit()
	{
		LoginView loginView = new LoginView();
		loginView.onInit();
	}
	
	static InterviewApplicationPanel getInstance()
	{
		if(interviewApplicationPanel==null)
			interviewApplicationPanel = new InterviewApplicationPanel();
		return interviewApplicationPanel;
	}
	
	

	public static void main(String[] args) {
		getInstance().onInit();		
	}

}
