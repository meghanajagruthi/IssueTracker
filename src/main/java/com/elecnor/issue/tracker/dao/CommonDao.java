package com.elecnor.issue.tracker.dao;

import java.util.ArrayList;

import com.elecnor.issue.tracker.bean.ApplicationDirectory;
import com.elecnor.issue.tracker.bean.ApplicationModuleDirectory;
import com.elecnor.issue.tracker.bean.IssueTrackerMaster;

public interface CommonDao {

	public ArrayList<ApplicationDirectory> getApplicationsList()throws Exception;
	public ArrayList<ApplicationModuleDirectory> getModulesListByApplicationId(int applicationId)throws Exception;
	public ArrayList<ApplicationModuleDirectory> getModulesListByMultipleApplicationIds(String query)throws Exception;
	public ApplicationModuleDirectory getModulesListByModuleName(String moduleName)throws Exception;
	public ArrayList<IssueTrackerMaster> getAllIssuesReportedByUser(String userId);
}
