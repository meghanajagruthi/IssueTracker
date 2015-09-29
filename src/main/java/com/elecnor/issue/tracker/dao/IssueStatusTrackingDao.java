package com.elecnor.issue.tracker.dao;

import java.util.ArrayList;

import com.elecnor.issue.tracker.bean.IssueStatusTracking;

public interface IssueStatusTrackingDao {

	public void setIssueStatusTrackingToSave(IssueStatusTracking issueStatusTracking)throws Exception;
	public ArrayList<IssueStatusTracking> getIssueStatusLogsByIssueId(int issueSno)throws Exception;
}
