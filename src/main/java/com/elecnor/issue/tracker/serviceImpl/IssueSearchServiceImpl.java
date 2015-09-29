package com.elecnor.issue.tracker.serviceImpl;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.issue.tracker.bean.ApplicationModuleDirectory;
import com.elecnor.issue.tracker.bean.IssueTrackerMaster;
import com.elecnor.issue.tracker.dao.CommonDao;
import com.elecnor.issue.tracker.dao.IssueSearchDao;
import com.elecnor.issue.tracker.service.IssueSearchService;
import com.elecnor.issue.tracker.util.IssueTrackerConstants;
import com.elecnor.issue.tracker.util.IssueTrackerUtility;

@Service
public class IssueSearchServiceImpl implements IssueSearchService {

	Logger logger = Logger.getLogger(IssueSearchServiceImpl.class);
	
	@Autowired
	IssueSearchDao issueSearchDao;
	@Autowired
	CommonDao commonDao;

	@Override
	public ArrayList<IssueTrackerMaster> getIssueDetailsBasedOnSearchKeys(
			HttpSession session, String requestFrom, String issueNo, String issueType,
			String issuePriority, String applicationToSearch,
			String issuesToSearch, String statusToSearch,
			String moduleToSearch, String assigneeToSearch, String timeToSearch, String reportedByMeCheckbox)
			throws Exception {
		
		logger.info("---- Entered getIssueDetailsBasedOnSearchKeys() of IssueSearchServiceImpl ----");
		
        IssueTrackerUtility utilRef = new IssueTrackerUtility();
		ArrayList<IssueTrackerMaster> issueList = null;
		try {
			String queryToRun = utilRef.getIssueSearchQuery(session, requestFrom, issueNo,
					issueType, issuePriority, applicationToSearch,
					issuesToSearch, statusToSearch, moduleToSearch,
					assigneeToSearch, timeToSearch, reportedByMeCheckbox);
			
			issueList = issueSearchDao
					.getIssueDetailsBasedOnSearchKeys(queryToRun);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return issueList;
	}

	@Override
	public ArrayList<ApplicationModuleDirectory> getModulesListByMultipleApplicationIds(
			String applicationIds) throws Exception {
		
		logger.info("---- Entered getModulesListByMultipleApplicationIds() of IssueSearchServiceImpl ----");
		
		ArrayList<ApplicationModuleDirectory> moduleList = null;
		try {
			String queryToRun = IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_FROM+" "+IssueTrackerConstants.MODULEDIRECTORY_BEAN_NAME+" "+IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE+" "+IssueTrackerConstants.MODULEDIRECTORY_COLUMN_APPLICATIONSNO+" IN("+applicationIds+")";
			moduleList = commonDao.getModulesListByMultipleApplicationIds(queryToRun);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;	
		}
		return moduleList;
	}

}
