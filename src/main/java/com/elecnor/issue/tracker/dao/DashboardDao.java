package com.elecnor.issue.tracker.dao;

import java.util.ArrayList;
import java.util.Date;

import com.elecnor.issue.tracker.bean.ApplicationDirectory;
import com.elecnor.issue.tracker.bean.IssueTrackerMaster;
import com.elecnor.issue.tracker.bean.LineChart;

public interface DashboardDao {
	
	public ArrayList<IssueTrackerMaster> getIssuesListForDashboard(String appName, int logedinUserid)throws Exception;

	public ArrayList<IssueTrackerMaster> getStatusListForDashboard(Integer appId)
			throws Exception;

	public ArrayList<ApplicationDirectory> getAppsListForDashboard() throws Exception;

	public ArrayList<LineChart> getTotalReportedIssues(Integer[] apps,Date reportedDate,Date past30Days) throws Exception;

	public ArrayList<LineChart> getTotalResolvedIssues(Integer[] appId,Date reportedDate,Date past30Days) throws Exception;

	public ArrayList<LineChart> getTotalResolvedIssues(Integer[] appId)throws Exception;

	public ArrayList<LineChart> getTotalReportedIssue(Integer[] appId)throws Exception;

	public ArrayList<IssueTrackerMaster> getIssuesListForDashboardByReportedDate(
			String appName, int logedinUserid, Date startDate, Date endDate)throws Exception;

	ArrayList<String> getAllStatus();
}
