package com.elecnor.issue.tracker.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.issue.tracker.bean.ApplicationDirectory;
import com.elecnor.issue.tracker.bean.ApplicationModuleDirectory;
import com.elecnor.issue.tracker.bean.AssigneeProfile;
import com.elecnor.issue.tracker.bean.IssueTrackerLookup;
import com.elecnor.issue.tracker.bean.IssueTrackerMaster;
import com.elecnor.issue.tracker.dao.AssigneeProfileDao;
import com.elecnor.issue.tracker.dao.CommonDao;
import com.elecnor.issue.tracker.dao.IssueTrackerLookupDao;
import com.elecnor.issue.tracker.service.IssueSearchService;
import com.elecnor.issue.tracker.util.IssueTrackerConstants;
import com.elecnor.issue.tracker.util.IssueTrackerUtility;

@Controller
public class IssueSearchController {
	
	Logger logger = Logger.getLogger(IssueSearchController.class);
	
	@Autowired
	IssueTrackerLookupDao issueTrackerLookupDao;
	@Autowired
	IssueSearchService issueSearchService;
	@Autowired
	CommonDao commonDao;
	@Autowired
	AssigneeProfileDao assigneeProfileDao;
	
		@RequestMapping("/issueSearch")
		public String getIssueSearch() {
			return "issueSearch";
		}
		
		@RequestMapping("/issueDetail")
		public String getProject() {
			return "issueInDetails";
		}
		
				@RequestMapping(value="/getIssueDetailsBasedOnSearchKeys", method=RequestMethod.POST)
				@ResponseBody
				public String getIssueDetailsBasedOnSearchKeys(HttpSession session, HttpServletRequest request) {
					
					logger.info("---- Entered getIssueDetailsBasedOnSearchKeys() of IssueSearchController ----");
					
					HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
					IssueTrackerUtility issueTrackerUtility = new IssueTrackerUtility();
					try
					{
						String issueNo = request.getParameter("issueNoToSearch");
						String issueType = request.getParameter("issueTypeToSearch");
						String issuePriority = request.getParameter("issuePriorityToSearch");
						String applicationToSearch = request.getParameter("applicationToSearch");
						String issuesToSearch = request.getParameter("issuesToSearch");
						String statusToSearch = request.getParameter("statusToSearch");
						String moduleToSearch = request.getParameter("moduleToSearch");
						String assigneeToSearch = request.getParameter("assigneeToSearch");
						String timeToSearch = request.getParameter("timeToSearch");
						String reportedByMeCheckbox = request.getParameter("reportedByMeCheckbox");
						String requestFrom = request.getParameter("requestFromPage");
						ArrayList<IssueTrackerMaster> searchResults = issueSearchService.getIssueDetailsBasedOnSearchKeys(session, requestFrom, issueNo, issueType, issuePriority, applicationToSearch, issuesToSearch, statusToSearch, moduleToSearch, assigneeToSearch, timeToSearch, reportedByMeCheckbox);
						resultMap.put("searchResults", searchResults);
						resultMap.put(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AJAXRESULT, IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_SUCCESS);
					}catch(Exception e){
						resultMap.put(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AJAXRESULT, IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_FAILURE);
						e.printStackTrace();
						StringWriter errors = new StringWriter();
						e.printStackTrace(new PrintWriter(errors));
						logger.error(errors.toString());
					}
					
					return issueTrackerUtility.getJsonResult(resultMap);
				}
		
		@RequestMapping(value="/getLookupDetailsToPopulateIntoSelectbox", method=RequestMethod.POST)
		@ResponseBody
		public String getLookupDetailsToPopulateIntoSelectbox(HttpSession session, HttpServletRequest request) {
			
			logger.info("---- Entered getLookupDetailsToPopulateIntoSelectbox() of IssueSearchController ----");
			
			HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
			IssueTrackerUtility issueTrackerUtility = new IssueTrackerUtility();
			try
			{
				String applicationId = (String)session.getAttribute(IssueTrackerConstants.ISSUETRACKERCONSTANTS_APPLICATIONID_SESSION_NAME);
				ArrayList<IssueTrackerLookup> lookUpDetailsToCreateIssue = issueTrackerLookupDao.getLookupTableDetailsList();
				ArrayList<ApplicationDirectory> applicationsList = commonDao.getApplicationsList();
				ArrayList<ApplicationModuleDirectory> applicationModulesList = commonDao.getModulesListByApplicationId(Integer.valueOf(applicationId));
				ArrayList<AssigneeProfile> allAssigneeList = assigneeProfileDao.getAllAssigneeProfiles();
				resultMap.put("lookupDetailsList", lookUpDetailsToCreateIssue);
				resultMap.put("applicationsList", applicationsList);
				resultMap.put("applicationModulesList", applicationModulesList);
				resultMap.put("allAssigneeList", allAssigneeList);
				resultMap.put("selectedApplication", applicationId);
				resultMap.put(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AJAXRESULT, IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_SUCCESS);
			}catch(Exception e){
				resultMap.put(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AJAXRESULT, IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_FAILURE);
				e.printStackTrace();
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				logger.error(errors.toString());
			}
			
			return issueTrackerUtility.getJsonResult(resultMap);
		}
		
		@RequestMapping(value="/getModulesListByMultipleApplicationIds", method=RequestMethod.POST)
		@ResponseBody
		public String getModulesListByMultipleApplicationIds(HttpSession session, HttpServletRequest request) {
			
			logger.info("---- Entered getModulesListByMultipleApplicationIds() of IssueSearchController ----");
			
			HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
			IssueTrackerUtility issueTrackerUtility = new IssueTrackerUtility();
			try
			{
				String applicationToSearch = request.getParameter("applicationToSearch");
				ArrayList<ApplicationModuleDirectory> moduleList = issueSearchService.getModulesListByMultipleApplicationIds(applicationToSearch);
				resultMap.put("moduleList", moduleList);
				resultMap.put(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AJAXRESULT, IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_SUCCESS);
			}catch(Exception e){
				resultMap.put(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AJAXRESULT, IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_FAILURE);
				e.printStackTrace();
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				logger.error(errors.toString());
			}
			
			return issueTrackerUtility.getJsonResult(resultMap);
		}
}


