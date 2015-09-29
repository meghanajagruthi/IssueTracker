package com.elecnor.issue.tracker.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.issue.tracker.bean.ApplicationModuleDirectory;
import com.elecnor.issue.tracker.bean.IssueTrackerLookup;
import com.elecnor.issue.tracker.bean.IssueTrackerMaster;
import com.elecnor.issue.tracker.bean.UserDetails;
import com.elecnor.issue.tracker.dao.CommonDao;
import com.elecnor.issue.tracker.dao.DashboardDao;
import com.elecnor.issue.tracker.dao.IssueTrackerLookupDao;
import com.elecnor.issue.tracker.dao.IssueTrackerMasterDao;
import com.elecnor.issue.tracker.service.DashboardService;
import com.elecnor.issue.tracker.service.IssueSearchService;
import com.elecnor.issue.tracker.util.IssueTrackerConstants;
import com.elecnor.issue.tracker.util.IssueTrackerUtility;

@Service
public class DashboardServiceImpl implements DashboardService {
	Logger logger = Logger.getLogger(DashboardServiceImpl.class);
	
	@Autowired
	DashboardDao dashboardDao;
	@Autowired
	IssueSearchService issueSearchService;
	@Autowired
	IssueTrackerMasterDao issueTrackerMasterDao;
	@Autowired
	IssueTrackerLookupDao issueTrackerLookupDao;
	@Autowired
	CommonDao commonDao;
	IssueTrackerUtility issueTrackerUtility = new IssueTrackerUtility();

	@Override
	public ArrayList<IssueTrackerMaster> getIssuesListForDashboard(
			String appName, int logedinUserid) throws Exception {
		ArrayList<IssueTrackerMaster> issuesListForDashboad = null;
		logger.info("Inside getIssuesListForDashBoard");
		try {
			issuesListForDashboad = dashboardDao.getIssuesListForDashboard(
					appName, logedinUserid);
		} catch (Exception e) {
			issuesListForDashboad = null;
			e.printStackTrace();
		}
		return issuesListForDashboad;
	}

	@Override
	public String getIssueTypesForDashboard(String[] appIds,
			UserDetails selectedUser, int noOfDays, String status) {
		List<Object> fullList = new ArrayList<Object>();
		logger.info(" fetching  IssueTypes For  Dashboard");
		ArrayList<IssueTrackerMaster> issuesList = null;
		int bugscount = 0, changeRequestCount = 0, featureCount = 0;
		try {

			for (int m = 0; m < appIds.length; m++) {

				if (noOfDays != -1) {
					Date repDate = new Date();
					Date past30Days = issueTrackerUtility
							.getRequiredDate(noOfDays);
					issuesList = dashboardDao
							.getIssuesListForDashboardByReportedDate(appIds[m],
									selectedUser.getUserId(), past30Days,
									repDate);

				} else {

					issuesList = dashboardDao.getIssuesListForDashboard(
							appIds[m], selectedUser.getUserId());
				}
				for (IssueTrackerMaster issue : issuesList) {

					if (issue.getStatus().getLookupName()
							.equalsIgnoreCase(status)
							&& issue.getIssueType().getLookupName()
									.equalsIgnoreCase("Bug")) {
						++bugscount;

					} else if (issue.getStatus().getLookupName()
							.equalsIgnoreCase(status)
							&& issue.getIssueType().getLookupName()
									.equalsIgnoreCase("Feature")) {
						++featureCount;

					} else if (issue.getStatus().getLookupName()
							.equalsIgnoreCase(status)
							&& issue.getIssueType().getLookupName()
									.equalsIgnoreCase("Change Request")) {
						++changeRequestCount;

					}

				}
			}

			HashMap<String, Object> map4 = new HashMap<String, Object>();
			map4.put("IssueTypeName", "Bugs");
			map4.put("IssueTypeValue", bugscount);
			HashMap<String, Object> map5 = new HashMap<String, Object>();
			map5.put("IssueTypeName", "Change Requests");
			map5.put("IssueTypeValue", changeRequestCount);
			HashMap<String, Object> map6 = new HashMap<String, Object>();
			map6.put("IssueTypeName", "Features");
			map6.put("IssueTypeValue", featureCount);
			fullList.add(map4);
			fullList.add(map5);
			fullList.add(map6);
			return issueTrackerUtility.getJsonResult(fullList);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String getTotalStatusByApp(String appIds, UserDetails selectedUser,
			int noOfDays) {
		ArrayList<IssueTrackerMaster> issuesList = null;
		List<Object> fullList = new ArrayList<Object>();
		logger.info(" fetching  total of  status");
		int custApprovalCounts = 0, newCounts = 0, cannotbeResolved = 0, resolved = 0, inProgress = 0, closed = 0, reopen = 0;
		try {

			if (noOfDays != -1) {
				Date repDate = new Date();

				Date past30Days = issueTrackerUtility.getRequiredDate(noOfDays);
				issuesList = dashboardDao
						.getIssuesListForDashboardByReportedDate(appIds,
								selectedUser.getUserId(), past30Days, repDate);

			} else {
				issuesList = dashboardDao.getIssuesListForDashboard(appIds,
						selectedUser.getUserId());
			}

			for (IssueTrackerMaster issue : issuesList) {

				if (issue.getStatus().getLookupName().equalsIgnoreCase("New")) {

					++newCounts;

				} else if (issue.getStatus().getLookupName()
						.equalsIgnoreCase("Awating Customer Response")) {

					++custApprovalCounts;

				} else if (issue.getStatus().getLookupName()
						.equalsIgnoreCase("Cannot be Resolved")) {
					++cannotbeResolved;

				} else if (issue.getStatus().getLookupName()
						.equalsIgnoreCase("resolved")) {

					++resolved;
				} else if (issue.getStatus().getLookupName()
						.equalsIgnoreCase("Closed")) {

					++closed;
				} else if (issue.getStatus().getLookupName()
						.equalsIgnoreCase("Reopened")) {

					++reopen;
				} else if (issue.getStatus().getLookupName()
						.equalsIgnoreCase("In Progress")) {

					++inProgress;
				}

			}

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("statusName", "New");
			map.put("statusValue", newCounts);
			HashMap<String, Object> valueMap = new HashMap<String, Object>();
			valueMap.put("statusName", "Awaiting for customer response");
			valueMap.put("statusValue", custApprovalCounts);
			HashMap<String, Object> map2 = new HashMap<String, Object>();
			map2.put("statusName", "Cannot be Resolved");
			map2.put("statusValue", cannotbeResolved);

			HashMap<String, Object> map3 = new HashMap<String, Object>();
			map3.put("statusName", "Resolved");
			map3.put("statusValue", resolved);

			HashMap<String, Object> map4 = new HashMap<String, Object>();
			map4.put("statusName", "Closed");
			map4.put("statusValue", closed);

			HashMap<String, Object> map5 = new HashMap<String, Object>();
			map5.put("statusName", "Reopened");
			map5.put("statusValue", reopen);

			HashMap<String, Object> map6 = new HashMap<String, Object>();
			map6.put("statusName", "In Progress");
			map6.put("statusValue", inProgress);

			fullList.add(map);
			fullList.add(valueMap);
			fullList.add(map2);
			fullList.add(map3);
			fullList.add(map4);
			fullList.add(map5);
			fullList.add(map6);
			return issueTrackerUtility.getJsonResult(fullList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public HashMap<Object, Object> getIssueCountsForDashboard(
			
			HttpSession htpSession, String applications, String issuesToSearch,
			String timeToSearch, String requestFromPage) throws Exception {
		HashMap<Object, Object> mapToReturn = new HashMap<Object, Object>();
		IssueTrackerUtility utilRef = new IssueTrackerUtility();
		logger.info(" fetching  counts of issues");
		try {
			String queryToRun = utilRef.getIssueSearchQuery(htpSession,
					requestFromPage, null, null, null, applications,
					issuesToSearch, null, null, null, timeToSearch, null);

			// getting total issues count to calculate percentage
			mapToReturn.put("totalIssuesCount", issueTrackerMasterDao
					.getIssuesCountByForDashboard(queryToRun));
			// getting count by modules start
			// getting all the modules related to selected applications.
			ArrayList<ApplicationModuleDirectory> moduleList = issueSearchService
					.getModulesListByMultipleApplicationIds(((applications != null && applications != "") ? applications
							: ((String) htpSession
									.getAttribute(IssueTrackerConstants.ISSUETRACKERCONSTANTS_APPLICATIONID_SESSION_NAME) != null ? (String) htpSession
									.getAttribute(IssueTrackerConstants.ISSUETRACKERCONSTANTS_APPLICATIONID_SESSION_NAME)
									: "0")));
			LinkedHashMap<Object, Object> moduleCountMap = new LinkedHashMap<Object, Object>();
			for (int modLen = 0; modLen < moduleList.size(); modLen++) {
				moduleCountMap
						.put(moduleList.get(modLen).getModuleName(),
								issueTrackerMasterDao
										.getIssuesCountByForDashboard(queryToRun
												+ " "
												+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AND
												+ " "
												+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_MODULEID
												+ "='"
												+ String.valueOf(moduleList
														.get(modLen)
														.getModuleSno()) + "'"));
			}
			mapToReturn.put("moduleCount", moduleCountMap);
			// getting count by modules ends

			// getting count by 'status' start
			// getting lookup table list for category 'status'
			ArrayList<IssueTrackerLookup> statusTypesList = issueTrackerLookupDao
					.getLookupListByCategory(IssueTrackerConstants.LOOKUP_VALUE_STATUS);
			LinkedHashMap<Object, Object> statusCountMap = new LinkedHashMap<Object, Object>();
			for (int stLen = 0; stLen < statusTypesList.size(); stLen++) {
				statusCountMap
						.put(statusTypesList.get(stLen).getLookupName(),
								issueTrackerMasterDao.getIssuesCountByForDashboard(queryToRun
										+ " "
										+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AND
										+ " "
										+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_STATUS
										+ "='"
										+ String.valueOf(statusTypesList.get(
												stLen).getLookupSno()) + "'"));
			}
			mapToReturn.put("statusCountList", statusCountMap);
			// getting count by 'status' ends

			// getting count by 'Severity' start
			// getting lookup table list for category 'Severity'
			ArrayList<IssueTrackerLookup> severityTypesList = issueTrackerLookupDao
					.getLookupListByCategory(IssueTrackerConstants.LOOKUP_VALUE_SEVERITY);
			LinkedHashMap<Object, Object> severityCountMap = new LinkedHashMap<Object, Object>();
			for (int seLen = 0; seLen < severityTypesList.size(); seLen++) {
				severityCountMap
						.put(severityTypesList.get(seLen).getLookupName(),
								issueTrackerMasterDao.getIssuesCountByForDashboard(queryToRun
										+ " "
										+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AND
										+ " "
										+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_SEVERITY
										+ "='"
										+ String.valueOf(severityTypesList.get(
												seLen).getLookupSno()) + "'"));
			}
			mapToReturn.put("severityCountList", severityCountMap);
			// getting count by 'Severity' ends

			// getting count by 'issue type' start
			// getting lookup table list for category 'issue type'
			ArrayList<IssueTrackerLookup> issueTypeList = issueTrackerLookupDao
					.getLookupListByCategory(IssueTrackerConstants.LOOKUP_VALUE_ISSUETYPE);
			LinkedHashMap<Object, Object> issueTypeCountMap = new LinkedHashMap<Object, Object>();
			for (int itLen = 0; itLen < issueTypeList.size(); itLen++) {
				issueTypeCountMap
						.put(issueTypeList.get(itLen).getLookupName(),
								issueTrackerMasterDao
										.getIssuesCountByForDashboard(queryToRun
												+ " "
												+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AND
												+ " "
												+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_ISSUETYPE
												+ "='"
												+ String.valueOf(issueTypeList
														.get(itLen)
														.getLookupSno()) + "'"));
			}
			mapToReturn.put("issueTypeCountList", issueTypeCountMap);
			// getting count by 'issue type' ends

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return mapToReturn;
	}

	// method for setting selected values into session
	@Override
	public void setSelectedValuesInSession(HttpSession htpSession,
			String selctedKey, String selctedValue, String applicationToSend,
			String issuesToSend, String timeToSend, String daysToSend)
			throws Exception {
		try {

			logger.info("Inside setSelectedValuesInSession");
			HashMap<Object, Object> dashBoardValuesMap = new HashMap<Object, Object>();
			if (selctedKey.equalsIgnoreCase("module")) {
				ApplicationModuleDirectory moduleBean = commonDao
						.getModulesListByModuleName(selctedValue);
				dashBoardValuesMap.put("selLookupBeanId",
						moduleBean.getModuleSno());
			} else {
				IssueTrackerLookup selLookupBean = issueTrackerLookupDao
						.getLookupTableDetailsByLookupName(selctedValue);
				dashBoardValuesMap.put("selLookupBeanId",
						selLookupBean.getLookupSno());

			}
			dashBoardValuesMap.put("selctedKey", selctedKey);
			dashBoardValuesMap.put("applicationToSend", applicationToSend);
			dashBoardValuesMap.put("issuesToSend", issuesToSend);
			dashBoardValuesMap.put("timeToSend", timeToSend);
			dashBoardValuesMap.put("daysToSend", daysToSend);

			htpSession.setAttribute("dashBoardValuesMap", dashBoardValuesMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
