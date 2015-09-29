package com.elecnor.issue.tracker.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.elecnor.issue.tracker.bean.IssueCommentsTracking;
import com.elecnor.issue.tracker.bean.IssueTrackerMaster;
import com.elecnor.issue.tracker.bean.UserDetails;
import com.elecnor.issue.tracker.dao.ActivityStreamDirectoryDao;
import com.elecnor.issue.tracker.dao.IssueCommentsTrackingDao;
import com.elecnor.issue.tracker.dao.IssueRelatedDocumentDao;
import com.elecnor.issue.tracker.dao.IssueStatusTrackingDao;
import com.elecnor.issue.tracker.dao.IssueTrackerMasterDao;
import com.elecnor.issue.tracker.service.ActivityStreamDirectoryService;
import com.elecnor.issue.tracker.service.IssueInDetailService;
import com.elecnor.issue.tracker.service.IssueRelatedDocumentService;
import com.elecnor.issue.tracker.util.IssueTrackerConstants;
import com.elecnor.issue.tracker.util.IssueTrackerUtility;
import com.file.handler.bean.FileUploadBean;

@Controller
public class IssueInDetailController {

	Logger logger = Logger.getLogger(IssueInDetailController.class);
	
	@Autowired
	IssueCommentsTrackingDao issueCommentsTrackingDao;
	@Autowired
	IssueInDetailService issueInDetailService;
	@Autowired
	IssueRelatedDocumentDao issueRelatedDocumentDao;
	@Autowired
	IssueRelatedDocumentService issueRelatedDocumentService;
	@Autowired
	IssueStatusTrackingDao issueStatusTrackingDao;
	@Autowired
	ActivityStreamDirectoryService activityStreamDirectoryService;
	@Autowired
	ActivityStreamDirectoryDao activityStreamDirectoryDao;
	@Autowired
	IssueTrackerMasterDao issueTrackerMasterDao;

	@RequestMapping(value = "/getSelectedIssuedetails", method = RequestMethod.POST)
	@ResponseBody
	public String getSelectedIssuedetails(HttpSession session,
			HttpServletRequest request) {
		
		logger.info("---- Entered getSelectedIssuedetails() of IssueInDetailController ----");
		
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		IssueTrackerUtility issueTrackerUtility = new IssueTrackerUtility();
		try {
			
			if(request.getParameter("issueIdToGetDetails") != null && request.getParameter("issueIdToGetDetails") != ""){
				int issueId= Integer.valueOf(request.getParameter("issueIdToGetDetails"));
				ArrayList<IssueTrackerMaster> issueInDetails = issueTrackerMasterDao.getIssueDetailsById(issueId);
				session.setAttribute(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_SELECTED_ISSUE_SESSION_NAME, issueInDetails.get(0));
			}
			
			IssueTrackerMaster selectedIssue = (IssueTrackerMaster)session.getAttribute(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_SELECTED_ISSUE_SESSION_NAME);	
			ArrayList<IssueCommentsTracking> commentsList = issueCommentsTrackingDao
					.getCommentsByIssueSno(String.valueOf(selectedIssue.getIssueSno()));
			
			//ArrayList<IssueRelatedDocument> documentList = issueRelatedDocumentDao.getReltedDocumentsByIssueSno(String.valueOf(selectedIssue.getIssueSno()));
			ArrayList<FileUploadBean> documentList=issueRelatedDocumentService.getDocumentsBasedOnIssue(selectedIssue.getIssueSno());
			resultMap.put("selectedIssue", selectedIssue);
			resultMap.put("commentsList", commentsList);
			resultMap.put("documentList", documentList);
			resultMap.put("statusLogs", issueStatusTrackingDao.getIssueStatusLogsByIssueId(selectedIssue.getIssueSno()));
			resultMap.put("activityLogs", activityStreamDirectoryDao.getActivityStreamsListForSelctedIssue(selectedIssue.getIssueSno()));
			resultMap.put(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AJAXRESULT, IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_SUCCESS);
		} catch (Exception e) {
			resultMap.put(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AJAXRESULT, IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_FAILURE);
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.error(errors.toString());
		}

		return issueTrackerUtility.getJsonResult(resultMap);
	}

	@RequestMapping(value = "/setCommentToAddForSelectedIssue", method = RequestMethod.POST)
	@ResponseBody
	public String setCommentToAddForSelectedIssue(HttpSession session,
			HttpServletRequest request) {
		
		logger.info("---- Entered setCommentToAddForSelectedIssue() of IssueInDetailController ----");
		
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		IssueTrackerUtility issueTrackerUtility = new IssueTrackerUtility();
		IssueCommentsTracking issueCommentsTrackingBean = new IssueCommentsTracking();
		try {
			String commentToSave = request.getParameter("commentToSave");
			IssueTrackerMaster selectedIssue = (IssueTrackerMaster) session
					.getAttribute(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_SELECTED_ISSUE_SESSION_NAME);
			 UserDetails logedInUserDetails =
			 (UserDetails)session.getAttribute(IssueTrackerConstants.ISSUETRACKERCONSTANTS_LOGIN_USER_SESSION_NAME);
			issueCommentsTrackingBean.setComment(commentToSave);
			issueCommentsTrackingBean.setIssueTrackerMaster(selectedIssue);
			issueCommentsTrackingBean.setSubmittedById(logedInUserDetails.getUserId());
			issueCommentsTrackingBean.setSubmittedByName(logedInUserDetails.getFirstName());
			issueCommentsTrackingBean.setSubmittedDate(new Date());
			issueCommentsTrackingDao
					.setCommentToAddForSelectedIssue(issueCommentsTrackingBean);
			//method for sending email
			issueInDetailService.sendEmailWhileUserCommentOnIssue(IssueTrackerConstants.ISSUETRACKERCONSTANTS_COMMENT, logedInUserDetails, selectedIssue, "", "", commentToSave);
			
			//method for saving activity
			activityStreamDirectoryService.setActivityToSave(selectedIssue, logedInUserDetails, IssueTrackerConstants.ACTIVITY_TYPE_FOR_COMMENT, commentToSave,"","");
			resultMap.put(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AJAXRESULT, IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_SUCCESS);
		} catch (Exception e) {
			resultMap.put(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AJAXRESULT, IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_FAILURE);
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.error(errors.toString());
		}

		return issueTrackerUtility.getJsonResult(resultMap);
	}

	@RequestMapping(value = "/setIssueStatusToUpdate", method = RequestMethod.POST)
	@ResponseBody
	public String setIssueStatusToUpdate(HttpSession session,
			HttpServletRequest request) {
		
		logger.info("---- Entered setIssueStatusToUpdate() of IssueInDetailController ----");
		
		HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
		IssueTrackerUtility issueTrackerUtility = new IssueTrackerUtility();
		try {
			
			int estimatedTime = Integer.valueOf((request.getParameter("estimatedTime") != null && request.getParameter("estimatedTime") != ""?request.getParameter("estimatedTime"):"0"));
			int percentageDone = Integer.valueOf((request.getParameter("percentageDone") != null && request.getParameter("percentageDone") != ""?request.getParameter("percentageDone"):"0"));
			String statusToUpdate = request.getParameter("statusToUpdate");
			String statusUpdateRelComment = request.getParameter("commentToSave");
			
			IssueTrackerMaster selectedIssue = (IssueTrackerMaster) session
					.getAttribute(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_SELECTED_ISSUE_SESSION_NAME);
			issueInDetailService.setIssueStatusToUpdate(selectedIssue, estimatedTime,
					percentageDone, statusToUpdate, statusUpdateRelComment, session);
			
			resultMap.put(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AJAXRESULT, IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_SUCCESS);
		} catch (Exception e) {
			resultMap.put(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AJAXRESULT, IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_FAILURE);
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.error(errors.toString());
		}

		return issueTrackerUtility.getJsonResult(resultMap);
	}
	
	// method for downloading uploaded document
		@RequestMapping(value = "/setRelatedDocumentToDowload", method = RequestMethod.GET)
		public void setRelatedDocumentToDowload(HttpServletRequest request,
				HttpServletResponse response) {
			
			logger.info("---- Entered setRelatedDocumentToDowload() of IssueInDetailController ----");
			
			try{
				issueRelatedDocumentService.setRelatedDocumentToDownload(request.getParameter("documentIdToDownload"), response);
			}catch(Exception e){
				e.printStackTrace();
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				logger.error(errors.toString());
			}
		}
		
		// method for deleting Document details
		@RequestMapping(value = "/setIssueRelDocumentToDelete", method = RequestMethod.POST)
		@ResponseBody
		public String setDocumentToDelete(HttpServletRequest request,HttpSession session) {
			
			logger.info("---- Entered setDocumentToDelete() of IssueInDetailController ----");
			
			HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
			IssueTrackerUtility issueTrackerUtility = new IssueTrackerUtility();
			try{
				int descId = 0;
				if (request.getParameter("docIdToDelete") != null
						&& request.getParameter("docIdToDelete") != "") {
					descId = Integer
							.parseInt(request.getParameter("docIdToDelete"));
				}
				    issueInDetailService.setDocumentToDelete(descId, session);
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
		
		@RequestMapping(value = "/setIssueRelDocumentToUpload", method = RequestMethod.POST)
		public @ResponseBody String setIssueRelDocumentToUpload(
				@RequestParam(value = "attachmentToBeuploaded") List<MultipartFile> uploadedFilesFromAttachmentDiv,
				@RequestParam(value = "uploadFile") List<MultipartFile> uploadedFilesFromEditModal,
				@RequestParam(value="addionalNewFile")List<MultipartFile> uploadedFiles,
				HttpSession session, HttpServletRequest request) {
			
			logger.info("---- Entered setIssueRelDocumentToUpload() of IssueInDetailController ----");
			
			HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
			IssueTrackerUtility util = new IssueTrackerUtility();
			try {
				if(uploadedFilesFromEditModal!=null&&(!uploadedFilesFromEditModal.isEmpty()))
				{
					uploadedFiles=uploadedFilesFromEditModal;
				}
				else if(uploadedFilesFromAttachmentDiv!=null&&(!uploadedFilesFromAttachmentDiv.isEmpty()))
				{
					uploadedFiles=uploadedFilesFromAttachmentDiv;
				}
				issueInDetailService.setIssueRelDocumentToUpload(session, uploadedFiles);
				resultMap.put(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AJAXRESULT, IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_SUCCESS);
			} catch (Exception e) {
				if(e.getMessage().equalsIgnoreCase("Recent File/Files have been failed to upload as the size limit is getting exceeded.. Delete the exisiting files before you upload new set of files")){
					
					resultMap.put(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AJAXRESULT, "Recent File/Files have been failed to upload as the size limit is getting exceeded.. Delete the exisiting files before you upload new set of files");
					
				}
				else{
				resultMap.put(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AJAXRESULT, IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_FAILURE);
				}
				e.printStackTrace();
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				logger.error(errors.toString());
			}
			return util.getJsonResult(resultMap);
		}  
		
		@RequestMapping(value = "/getActivityStreamsListForSelctedIssue", method = RequestMethod.POST)
		@ResponseBody
		public String getActivityStreamsListForSelctedIssue(HttpSession session,
				HttpServletRequest request) {
			
			logger.info("---- Entered getActivityStreamsListForSelctedIssue() of IssueInDetailController ----");
			
			HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
			IssueTrackerUtility issueTrackerUtility = new IssueTrackerUtility();
			try {
				IssueTrackerMaster selectedIssue = (IssueTrackerMaster) session
						.getAttribute(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_SELECTED_ISSUE_SESSION_NAME);
				resultMap.put("activityLogs", activityStreamDirectoryDao.getActivityStreamsListForSelctedIssue(selectedIssue.getIssueSno()));
				resultMap.put(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AJAXRESULT, IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_SUCCESS);
			} catch (Exception e) {
				resultMap.put(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AJAXRESULT, IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_FAILURE);
				e.printStackTrace();
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				logger.error(errors.toString());
			}

			return issueTrackerUtility.getJsonResult(resultMap);
		}
}
