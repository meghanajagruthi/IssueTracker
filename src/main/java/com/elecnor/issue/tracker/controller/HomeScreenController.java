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
import com.elecnor.issue.tracker.bean.UserDetails;
import com.elecnor.issue.tracker.dao.AssigneeProfileDao;
import com.elecnor.issue.tracker.dao.CommonDao;
import com.elecnor.issue.tracker.dao.IssueTrackerLookupDao;
import com.elecnor.issue.tracker.dao.IssueTrackerMasterDao;
import com.elecnor.issue.tracker.service.DashboardService;
import com.elecnor.issue.tracker.util.IssueTrackerConstants;
import com.elecnor.issue.tracker.util.IssueTrackerUtility;

@Controller
public class HomeScreenController {
	
	Logger logger = Logger.getLogger(HomeScreenController.class);
	
	@Autowired
	DashboardService dashboardService;
	
	@Autowired
	IssueTrackerMasterDao issueTrackerMasterDao;
	@Autowired
	IssueTrackerLookupDao issueTrackerLookupDao;
	@Autowired
	CommonDao commonDao;
	@Autowired
	AssigneeProfileDao assigneeProfileDao;
	
	@RequestMapping("/homeScreen")
	public String getStaticHome() {
		return "homeScreen";
	}

	@RequestMapping("/home")
	public String getHome() {
		return "home";
	}

	@RequestMapping(value="/getIssueListForDashboard", method=RequestMethod.POST)
	@ResponseBody
	public String getIssueListForDashboard(HttpSession session, HttpServletRequest request) {
		
		logger.info("---- Entered getIssueListForDashboard() of HomeScreenController ----");
		
		HashMap<Object, Object> map=new HashMap<Object, Object>();
		IssueTrackerUtility issueTrackerUtility = new IssueTrackerUtility();
		try{
			String appName=(String)session.getAttribute(IssueTrackerConstants.ISSUETRACKERCONSTANTS_APPLICATIONID_SESSION_NAME);
			UserDetails logedInUserDetails = (UserDetails)session.getAttribute(IssueTrackerConstants.ISSUETRACKERCONSTANTS_LOGIN_USER_SESSION_NAME);
			ArrayList<IssueTrackerMaster> issueListForDashboard = dashboardService.getIssuesListForDashboard(appName, logedInUserDetails.getUserId());
			map.put("issueList", issueListForDashboard);
			map.put("ajaxResult", IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			map.put("ajaxResult", IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_FAILURE);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.error(errors.toString());
			
		}
		
		String json = issueTrackerUtility.getJsonResult(map);
		return json;
		
	}
	
	@RequestMapping(value="/getIssueDetails", method=RequestMethod.POST)
	public String getIssueDetails(HttpSession session, HttpServletRequest request) {
		
		logger.info("---- Entered getIssueDetails() of HomeScreenController ----");
		
		try{
			int issueId= Integer.valueOf(request.getParameter("issueIdToGetDetails"));
			ArrayList<IssueTrackerMaster> issueInDetails = issueTrackerMasterDao.getIssueDetailsById(issueId);
			session.setAttribute(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_SELECTED_ISSUE_SESSION_NAME, issueInDetails.get(0));
		}catch(Exception e){
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.error(errors.toString());
		}
		
		return IssueTrackerConstants.ISSUETRACKERCONSTANTS_REDIRECT_ISSUEINDETAILS_PAGE;
		
	}
	
	//method calls when user clicks on create Issue to populate options in respective select boxes 
			@RequestMapping(value="/getLookupDetailsToPopulateIntoSelectBoxHomeScreen", method=RequestMethod.POST)
			@ResponseBody
			public String getLookUpDetailsToCreateIssue(HttpSession session, HttpServletRequest request) {
				
				logger.info("---- Entered getLookUpDetailsToCreateIssue() of HomeScreenController ----");
				
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
					//these values are getting from session and using only when user is redirected from dashboard to issue search page
					//and after put in map we are setting these values to null
					resultMap.put("dashBoardValuesMap", session.getAttribute("dashBoardValuesMap"));
					session.setAttribute("dashBoardValuesMap",null);
					
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
