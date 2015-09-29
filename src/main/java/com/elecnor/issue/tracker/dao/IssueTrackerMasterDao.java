package com.elecnor.issue.tracker.dao;

import java.util.ArrayList;
import java.util.Date;

import com.elecnor.issue.tracker.bean.IssueTrackerLookup;
import com.elecnor.issue.tracker.bean.IssueTrackerMaster;

public interface IssueTrackerMasterDao {

	public IssueTrackerMaster setIssueToSaveOrUpdate(
			IssueTrackerMaster issueTrackerMaster) throws Exception;

	public ArrayList<IssueTrackerMaster> getIssueDetailsById(int issueSno)
			throws Exception;

	
	public Object getIssuesCountByForDashboard(String queryToExecute) throws Exception;

	int setIssueStatusToUpdate(int issueSno, IssueTrackerLookup statusToUpdate,
			int updatedById, String updatedByName, int estimatedTime,
			int perDone, Date resolvedDate) throws Exception;

	
}
