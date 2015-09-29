package com.elecnor.issue.tracker.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import com.elecnor.issue.tracker.bean.IssueTrackerMaster;
import com.elecnor.issue.tracker.bean.UserDetails;

public interface DashboardService {

	public ArrayList<IssueTrackerMaster> getIssuesListForDashboard(String appName, int logedinUserid)throws Exception; 
	public HashMap<Object,Object> getIssueCountsForDashboard(HttpSession htpSession, String applications, String issuesToSearch, String timeToSearch, String requestFromPage)throws Exception;
	public void setSelectedValuesInSession(HttpSession htpSession, String selctedKey, String selctedValue, String applicationToSend, String issuesToSend, String timeToSend, String daysToSend)throws Exception;
	String getTotalStatusByApp(String appIds, UserDetails selectedUser,
			 int noOfDays);
	String getIssueTypesForDashboard(String[] appIds, UserDetails selectedUser,
			int noOfDays, String status);
	
}
