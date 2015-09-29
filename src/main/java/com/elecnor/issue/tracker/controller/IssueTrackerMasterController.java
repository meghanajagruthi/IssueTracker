package com.elecnor.issue.tracker.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.elecnor.issue.tracker.bean.IssueTrackerMaster;
import com.elecnor.issue.tracker.bean.UserDetails;
import com.elecnor.issue.tracker.service.IssueTrackerMasterService;
import com.elecnor.issue.tracker.util.IssueTrackerConstants;
import com.elecnor.issue.tracker.util.IssueTrackerUtility;

@Controller
public class IssueTrackerMasterController {

	Logger logger = Logger.getLogger(IssueTrackerMasterController.class);
	
	@Autowired
	IssueTrackerMasterService issueTrackerMasterService;

	@ModelAttribute("issueTrackerMasterForm")
	public IssueTrackerMaster registerIssueTrackerMaster() {
		return new IssueTrackerMaster();
	}

	@RequestMapping(value = "/saveOrUpdateIssue", method = RequestMethod.POST)
	public @ResponseBody String setSaveOrUpdateIssue(
			@ModelAttribute("issueTrackerMasterForm") IssueTrackerMaster issueTrackerMaster,
			@RequestParam(value = "uploadFile") List<MultipartFile> uploadedFiles,
			HttpSession session, HttpServletRequest request) {
		
		logger.info("---- Entered setSaveOrUpdateIssue() of IssueTrackerMasterController ----");
		
		// While Registering a user checking if email Id already exits in User
		// Detail, Check for the Tempory User is already done on UI(js file)
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		IssueTrackerUtility util = new IssueTrackerUtility();
		try {
			UserDetails logedUser = (UserDetails) session
					.getAttribute(IssueTrackerConstants.ISSUETRACKERCONSTANTS_LOGIN_USER_SESSION_NAME);
			String relatedApplication = request.getParameter("applicationId");
			String relatedModule = request.getParameter("applicationModuleId");
			String issueType = request.getParameter("issueTypeId");
			String issueStatus = request.getParameter("statusId");
			String issueSeverity = request.getParameter("severityId");
			String assigneeId = request.getParameter("asigneeId");
			issueTrackerMasterService.setIssueToSaveOrUpdate(
					issueTrackerMaster, logedUser, relatedApplication,
					relatedModule, issueType, issueStatus,
					issueSeverity, assigneeId, session, uploadedFiles);
			resultMap.put(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AJAXRESULT, IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_SUCCESS);
		} catch (Exception e) {
			resultMap.put(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AJAXRESULT, IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_FAILURE);
			resultMap.put("reason", e.getMessage());
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.error(errors.toString());
		}
		return util.getJsonResult(resultMap);
	}

}
