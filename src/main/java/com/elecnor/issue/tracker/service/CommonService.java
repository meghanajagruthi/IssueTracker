package com.elecnor.issue.tracker.service;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.elecnor.issue.tracker.bean.IssueSummary;

public interface CommonService {
	
	public String AuthenticateUser(String userName, /*String password,*/
			String temporaryEmailId, HttpSession session)throws Exception;
	public ArrayList<String> getJobnamesByDomainId(String domainId, int selApplicationId)throws Exception;
	public String setUserAndIssueDetailsInSession(String userEmailId, String issueSno, HttpSession httpSession)throws Exception;
	public ArrayList<IssueSummary> getAllIssuesReportedByUser(String userId);
	
}
