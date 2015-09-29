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
import org.springframework.web.servlet.ModelAndView;

import com.elecnor.issue.tracker.bean.ApplicationDirectory;
import com.elecnor.issue.tracker.bean.ApplicationModuleDirectory;
import com.elecnor.issue.tracker.bean.AssigneeProfile;
import com.elecnor.issue.tracker.bean.IssueTrackerLookup;
import com.elecnor.issue.tracker.bean.UserDetails;
import com.elecnor.issue.tracker.dao.AssigneeProfileDao;
import com.elecnor.issue.tracker.dao.CommonDao;
import com.elecnor.issue.tracker.dao.IssueTrackerLookupDao;
import com.elecnor.issue.tracker.dao.IssueTrackerMasterDao;
import com.elecnor.issue.tracker.service.CommonService;
import com.elecnor.issue.tracker.util.IssueTrackerConstants;
import com.elecnor.issue.tracker.util.IssueTrackerUtility;

@Controller
public class CommonController {
	
	Logger logger = Logger.getLogger(CommonController.class);
	
	@Autowired
	IssueTrackerLookupDao issueTrackerLookupDao;
	@Autowired
	CommonDao commonDao;
	@Autowired
	CommonService commonService;
	@Autowired
	AssigneeProfileDao assigneeProfileDao;
	@Autowired
	IssueTrackerMasterDao issueTrackerMasterDao;
	
	//method calls when user clicks on reportIssue in any other applications(like Ecosystem, MPR, PD etc...). 
	@RequestMapping(value="/excludeIntercepterRedirectedFromApps", method=RequestMethod.POST)
	public ModelAndView setApplicationInSession(HttpSession session, HttpServletRequest request) {
		
		logger.info("---- Entered setApplicationInSession() method of CommonController ----");
		ModelAndView mav = new ModelAndView(IssueTrackerConstants.ISSUETRACKERCONSTANTS_REDIRECT_HOMESCREEN_PAGE);
		try {
			session.setAttribute(IssueTrackerConstants.ISSUETRACKERCONSTANTS_APPLICATIONID_SESSION_NAME, request.getParameter("appIdForIssueTracker"));
			String viewPage = commonService.AuthenticateUser(request.getParameter("emailidForIssueTracker"),  request.getParameter("temporaryEmailId"), session);
			mav = new ModelAndView(viewPage);
		} catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.error(errors.toString());
			mav = new ModelAndView(IssueTrackerConstants.ISSUETRACKERCONSTANTS_REDIRECT_ERROR_PAGE);
			e.printStackTrace();
		}
		return mav;
	}
	
	//method calls when user clicks on create Issue to populate options in respective select boxes 
		@RequestMapping(value="/getLookUpDetailsToCreateIssue", method=RequestMethod.POST)
		@ResponseBody
		public String getLookUpDetailsToCreateIssue(HttpSession session, HttpServletRequest request) {
			
			logger.info("---- Entered getLookUpDetailsToCreateIssue() method of CommonController ----");
			
			HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
			IssueTrackerUtility issueTrackerUtility = new IssueTrackerUtility();
			try
			{
				String applicationId = (String)session.getAttribute(IssueTrackerConstants.ISSUETRACKERCONSTANTS_APPLICATIONID_SESSION_NAME);
				ArrayList<IssueTrackerLookup> lookUpDetailsToCreateIssue = issueTrackerLookupDao.getLookupTableDetailsList();
				ArrayList<ApplicationDirectory> applicationsList = commonDao.getApplicationsList();
				UserDetails logedInUserDetails = (UserDetails)session.getAttribute(IssueTrackerConstants.ISSUETRACKERCONSTANTS_LOGIN_USER_SESSION_NAME);
				ArrayList<ApplicationModuleDirectory> applicationModulesList = commonDao.getModulesListByApplicationId(Integer.valueOf(applicationId));
				ArrayList<String> allJobNames = commonService.getJobnamesByDomainId(String.valueOf(logedInUserDetails.getDomainIdTransient()), Integer.valueOf(applicationId));//hardcoded domain value
				ArrayList<AssigneeProfile> allAssigneeList = assigneeProfileDao.getAllAssigneeProfiles();
				resultMap.put("lookupDetailsList", lookUpDetailsToCreateIssue);
				resultMap.put("applicationsList", applicationsList);
				resultMap.put("applicationModulesList", applicationModulesList);
				resultMap.put("allJobNames", allJobNames);
				resultMap.put("allAssigneeList", allAssigneeList);
				resultMap.put("selectedApplication", applicationId);
				//these values are getting from session and using only when user is redirected from dashboard to issue search page
				//and after put in map we are setting these values to null
				resultMap.put("dashBoardValuesMap", session.getAttribute("dashBoardValuesMap"));
				session.setAttribute("dashBoardValuesMap",null);
				
				resultMap.put(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AJAXRESULT, IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_SUCCESS);
			}catch(Exception e){
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				logger.error(errors.toString());
				resultMap.put(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AJAXRESULT, IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_FAILURE);
				e.printStackTrace();
			}
			
			return issueTrackerUtility.getJsonResult(resultMap);
		}
		
		@RequestMapping(value="/getModuleListForSelApp", method=RequestMethod.POST)
		@ResponseBody
		public String getModuleListForSelApp(HttpSession session, HttpServletRequest request) {
			
			logger.info("---- Entered getModuleListForSelApp() method of CommonController ----");
			
			HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
			IssueTrackerUtility issueTrackerUtility = new IssueTrackerUtility();
			try
			{
				String applicationId = (String)request.getParameter("selAppId");
				UserDetails logedInUserDetails = (UserDetails)session.getAttribute(IssueTrackerConstants.ISSUETRACKERCONSTANTS_LOGIN_USER_SESSION_NAME);
				ArrayList<ApplicationModuleDirectory> applicationModulesList = commonDao.getModulesListByApplicationId(Integer.valueOf(applicationId));
				ArrayList<String> allJobsList = commonService.getJobnamesByDomainId(String.valueOf(logedInUserDetails.getDomainIdTransient()), Integer.valueOf(applicationId));
				resultMap.put("applicationModulesList", applicationModulesList);
				resultMap.put("allJobsList", allJobsList);
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
		
		//method will call when user clicks on the link given in email
		@RequestMapping(value="/excludeIntercepter/getIssueInDetailFromEmail")
		public String getIssueDetails(HttpSession session, HttpServletRequest request) {
			
			logger.info("---- Entered getIssueDetails() method of CommonController ----");
			
			String resultView = IssueTrackerConstants.ISSUETRACKERCONSTANTS_REDIRECT_ISSUEINDETAILS_PAGE;
			try{
				session.setAttribute(IssueTrackerConstants.ISSUETRACKERCONSTANTS_APPLICATIONID_SESSION_NAME, request.getParameter("applicationId"));
				resultView = commonService.setUserAndIssueDetailsInSession(request.getParameter("emailID"), request.getParameter("issueNo"), session);
			}catch(Exception e){
				e.printStackTrace();
				resultView = IssueTrackerConstants.ISSUETRACKERCONSTANTS_REDIRECT_ERROR_PAGE;
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				logger.error(errors.toString());
			}
			
			return resultView;
			
		}
		
}