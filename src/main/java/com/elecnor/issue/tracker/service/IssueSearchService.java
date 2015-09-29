package com.elecnor.issue.tracker.service;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.elecnor.issue.tracker.bean.ApplicationModuleDirectory;
import com.elecnor.issue.tracker.bean.IssueTrackerMaster;

public interface IssueSearchService {

	public ArrayList<IssueTrackerMaster> getIssueDetailsBasedOnSearchKeys(HttpSession session, String requestFrom, String issueNo, String issueType, String issuePriority, String applicationToSearch, String issuesToSearch, String statusToSearch, String moduleToSearch, String assigneeToSearch, String timeToSearch, String reportedByMeCheckbox)throws Exception;
	public ArrayList<ApplicationModuleDirectory> getModulesListByMultipleApplicationIds(String applicationIds)throws Exception;
	
}
