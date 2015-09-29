package com.elecnor.issue.tracker.dao;

import java.util.ArrayList;

import com.elecnor.issue.tracker.bean.IssueTrackerLookup;

public interface IssueTrackerLookupDao {

	public ArrayList<IssueTrackerLookup> getLookupTableDetailsList()
			throws Exception;

	public IssueTrackerLookup getLookupTableDetailsById(String sNo)
			throws Exception;

	public IssueTrackerLookup getLookupTableDetailsByLookupName(
			String lookupName) throws Exception;
	
	public ArrayList<IssueTrackerLookup> getLookupIssuetypeAndPriorityList()
			throws Exception;
	
	public ArrayList<IssueTrackerLookup> getLookupListByCategory(String categoryName)
			throws Exception;
}
