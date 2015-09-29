package com.elecnor.issue.tracker.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.issue.tracker.bean.ApplicationDirectory;
import com.elecnor.issue.tracker.bean.UserDetails;
import com.elecnor.issue.tracker.dao.CommonDao;
import com.elecnor.issue.tracker.dao.DashboardDao;
import com.elecnor.issue.tracker.dao.IssueTrackerLookupDao;
import com.elecnor.issue.tracker.service.DashboardService;
import com.elecnor.issue.tracker.util.IssueTrackerConstants;
import com.elecnor.issue.tracker.util.IssueTrackerUtility;
import com.sun.mail.imap.Utility;

@Controller
public class DashboardController {
	
	Logger logger = Logger.getLogger(DashboardController.class);
	
	@Autowired
	CommonDao commonDao;
	@Autowired
	DashboardService dashboardService;
	@Autowired
	DashboardDao dashboardDao;
	@Autowired
	IssueTrackerLookupDao issueTrackerLookupDao;
	
		@RequestMapping("/dashboard")
		public String getProject() {
			return "dashboard";
		}
		@RequestMapping("/graphCharts")
		public String getgraphCharts() {
			return "graphCharts";
		}
		
		@RequestMapping(value="/getApplicationListForSelectBox", method=RequestMethod.POST)
		@ResponseBody
		public String getLookupDetailsToPopulateIntoSelectbox(HttpSession session, HttpServletRequest request) {
			HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
			IssueTrackerUtility issueTrackerUtility = new IssueTrackerUtility();
			try
			{
				logger.info("---- Entered getApplicationListForSelectBox() of DashboardController ----");
				ArrayList<ApplicationDirectory> applicationsList = commonDao.getApplicationsList();
				resultMap.put("applicationsList", applicationsList);
				resultMap.put("selectedApplication", (String)session.getAttribute(IssueTrackerConstants.ISSUETRACKERCONSTANTS_APPLICATIONID_SESSION_NAME));
				resultMap.put(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AJAXRESULT, IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_SUCCESS);
			}catch(Exception e){
				resultMap.put(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AJAXRESULT, IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_FAILURE);
				e.printStackTrace();
			}
			
			return issueTrackerUtility.getJsonResult(resultMap);
		}
		
		@RequestMapping(value="/getDataForLineCharts",method=RequestMethod.POST)
		public @ResponseBody String getDataForLineCharts(HttpSession session, HttpServletRequest request,@RequestParam(value = "appId", defaultValue = "-1") String applId,@RequestParam(value="issueType",defaultValue="")String issueType,@RequestParam(value="days",defaultValue="")String days)
		{
			
			
			logger.info("---- Entered getDataForLineCharts() of DashboardController ----");
			String appString=applId.substring(1, applId.length()-1);
			appString=appString.replaceAll("\"", "");
			String appId[]=appString.split(",");
			Integer apps[]=new Integer[appId.length];
			for(int i=0;i<appId.length;i++)
			{
			   apps[i]=Integer.parseInt(appId[i]);	
			   
			}
			Integer noOfDays=Integer.parseInt(days);
			IssueTrackerUtility issueTrackerUtility = new IssueTrackerUtility();
			try{
			HashMap<Object,Object> map=new HashMap<Object,Object>();
			if(noOfDays!=-1){
				Date repDate=new Date();
				Date past30Days=issueTrackerUtility.getRequiredDate(noOfDays);
				map.put("dataForLineCharts", dashboardDao.getTotalReportedIssues(apps, repDate,past30Days));
				map.put("dataForLineChartsResolved", dashboardDao.getTotalResolvedIssues(apps, repDate, past30Days));
				return issueTrackerUtility.getJsonResult(map);
			}
			else{
				map.put("dataForLineCharts", dashboardDao.getTotalReportedIssue(apps));
				map.put("dataForLineChartsResolved", dashboardDao.getTotalResolvedIssues(apps));
				return issueTrackerUtility.getJsonResult(map);
			}
		
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		          return null;	
		}
		
		
		
		@RequestMapping(value="/getTotalStatusByApp",method=RequestMethod.POST)
		public @ResponseBody  String getTotalStatusByApp(HttpSession session, HttpServletRequest request,@RequestParam(value = "appId", defaultValue = "-1") String applicationId,@RequestParam(value="noOfDays")int noOfDays)
		{
		
			logger.info("---- Entered getTotalStatusByApp() of DashboardController ----");	
			UserDetails logedInUserDetails = (UserDetails)session.getAttribute(IssueTrackerConstants.ISSUETRACKERCONSTANTS_LOGIN_USER_SESSION_NAME);
			
			return dashboardService.getTotalStatusByApp(applicationId, logedInUserDetails,  noOfDays);
			
		}
		
		@RequestMapping(value="/getAllStatus",method=RequestMethod.POST)
		public @ResponseBody String getAllStatus(HttpSession session,HttpServletRequest request)
		{
			
			logger.info("---- Entered getAllStatus() of DashboardController ----");	
			HashMap< Object, Object> map=new HashMap<Object,Object>();
			IssueTrackerUtility util=new IssueTrackerUtility();
			try{
			map.put("statusList", dashboardDao.getAllStatus());
			}
			catch(Exception e)
			{
				map.put("ajaxResult", "failure");
				map.put("reason", e.getMessage());
			}
			return util.getJsonResult(map);
		}
		
		@RequestMapping(value="/getIssueTypesForDashboard",method=RequestMethod.POST)
		public @ResponseBody String getIssueTypesForDashBoard(HttpSession session, HttpServletRequest request,@RequestParam(value = "appId", defaultValue = "-1") String applicationId,@RequestParam(value = "status", defaultValue = "") String status,@RequestParam(value="noOfDays")int noOfDays) throws Exception
		{
			
			
			logger.info("---- Entered getIssueTypesForDashBoard() of DashboardController ----");	
			String appString=applicationId.substring(1, applicationId.length()-1);
			appString=appString.replaceAll("\"", "");
			String appId[]=appString.split(",");
			UserDetails logedInUserDetails = (UserDetails)session.getAttribute(IssueTrackerConstants.ISSUETRACKERCONSTANTS_LOGIN_USER_SESSION_NAME);
			return dashboardService.getIssueTypesForDashboard(appId, logedInUserDetails, noOfDays, status);
		}
		
		@RequestMapping(value="/getAllAppsForDashboard", method=RequestMethod.POST)
		public @ResponseBody String getAppDetailsForDashBoard(HttpSession session, HttpServletRequest request)
		{
			
			logger.info("---- Entered getAllAppsForDashboard() of DashboardController ----");	
			IssueTrackerUtility issueTrackerUtility = new IssueTrackerUtility();
			HashMap<Object , Object> map=new HashMap<Object , Object>();
			try {
				ArrayList<ApplicationDirectory> applicationList=dashboardDao.getAppsListForDashboard();
				map.put("applicationList", applicationList);
				return issueTrackerUtility.getJsonResult(map);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
			
		}
		@RequestMapping(value="/getIssueCountsForDashboard", method=RequestMethod.POST)
		@ResponseBody
		public String getIssueCountsForDashboard(HttpSession session, HttpServletRequest request) {
			HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
			IssueTrackerUtility issueTrackerUtility = new IssueTrackerUtility();
			try
			{
				logger.info("---- Entered getIssueCountsForDashboard() of DashboardController ----");
			    String applications = request.getParameter("applicationToSearch");
			    String issuesToSearch = request.getParameter("issuesToSearch");
			    String timeToSearch = request.getParameter("timeToSearch");
			    String requestFromPage = request.getParameter("requestFromPage");
			    resultMap = dashboardService.getIssueCountsForDashboard(session, applications, issuesToSearch, timeToSearch, requestFromPage);
				resultMap.put(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AJAXRESULT, IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_SUCCESS);
			}catch(Exception e){
				resultMap.put(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AJAXRESULT, IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_FAILURE);
				e.printStackTrace();
			}
			
			return issueTrackerUtility.getJsonResult(resultMap);
		}
		
		@RequestMapping(value="/redirectToIssueSearchPage", method=RequestMethod.POST)
		public String redirectToIssueSearchPage(HttpSession session, HttpServletRequest request) {
			String returnPage = IssueTrackerConstants.ISSUETRACKERCONSTANTS_REDIRECT_HOMESCREEN_PAGE;
			try
			{
				logger.info("---- Entered redirectToIssueSearchPage() of DashboardController ----");
			    String lookupType = request.getParameter("lookupType");
			    String lookupValue = request.getParameter("lookupValue");
			    String applicationToSend = request.getParameter("applicationToSend");
			    String issuesToSend = request.getParameter("issuesToSend");
			    String timeToSend = request.getParameter("timeToSend");
			    String daysToSend = request.getParameter("daysToSend");
			    dashboardService.setSelectedValuesInSession(session, lookupType, lookupValue, applicationToSend, issuesToSend, timeToSend, daysToSend);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return returnPage;
		}
}
