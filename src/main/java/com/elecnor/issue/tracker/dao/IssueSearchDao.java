package com.elecnor.issue.tracker.dao;

import java.util.ArrayList;

import com.elecnor.issue.tracker.bean.IssueTrackerMaster;

public interface IssueSearchDao {

	public ArrayList<IssueTrackerMaster> getIssueDetailsBasedOnSearchKeys(String queryToRun)throws Exception;
}
