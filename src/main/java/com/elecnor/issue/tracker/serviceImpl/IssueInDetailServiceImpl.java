package com.elecnor.issue.tracker.serviceImpl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elecnor.issue.tracker.bean.IssueDocumentsMapping;
import com.elecnor.issue.tracker.bean.IssueStatusTracking;
import com.elecnor.issue.tracker.bean.IssueTrackerLookup;
import com.elecnor.issue.tracker.bean.IssueTrackerMaster;
import com.elecnor.issue.tracker.bean.UserDetails;
import com.elecnor.issue.tracker.dao.IssueRelatedDocumentDao;
import com.elecnor.issue.tracker.dao.IssueStatusTrackingDao;
import com.elecnor.issue.tracker.dao.IssueTrackerLookupDao;
import com.elecnor.issue.tracker.dao.IssueTrackerMasterDao;
import com.elecnor.issue.tracker.helper.EcosystemEmailExpose;
import com.elecnor.issue.tracker.service.ActivityStreamDirectoryService;
import com.elecnor.issue.tracker.service.IssueInDetailService;
import com.elecnor.issue.tracker.util.EncrypterDecrypter;
import com.elecnor.issue.tracker.util.IssueTrackerConstants;
import com.elecnor.issue.tracker.util.IssueTrackerUtility;
import com.elecnor.issue.tracker.util.PropertyFileReader;
import com.email.utility.bean.EmailNotificationBean;
import com.email.utility.executor.EmailTaskExecutor;
//import com.file.handler.service.FileUploadService;
import com.file.handler.service.FileUploadService;

@Service
public class IssueInDetailServiceImpl implements IssueInDetailService {

	Logger logger = Logger.getLogger(IssueInDetailServiceImpl.class);

	@Autowired
	EmailTaskExecutor emailHandlerThread;
	@Autowired
	FileUploadService fileUploadService;
	@Autowired
	IssueTrackerMasterDao issueTrackerMasterDao;
	@Autowired
	IssueTrackerLookupDao issueTrackerLookupDao;
	@Autowired
	IssueStatusTrackingDao issueStatusTrackingDao;
	@Autowired
	IssueRelatedDocumentDao issueRelatedDocumentDao;
	@Autowired
	ActivityStreamDirectoryService activityStreamDirectoryService;

	/*
	 * @Autowired UploadServiceImpl uploadServiceImpl;
	 */
	@Override
	public void setIssueStatusToUpdate(IssueTrackerMaster selectedIssuebean,
			int estimatedTime, int perDone, String statusToUpdate,
			String statusUpdateRelatedComment, HttpSession httpSession)
			throws Exception {

		logger.info("---- Entered setIssueStatusToUpdate() of IssueInDetailServiceImpl ----");

		try {

			IssueTrackerLookup issueTrackerLookupBean = issueTrackerLookupDao
					.getLookupTableDetailsByLookupName(statusToUpdate + "%");
			UserDetails logedInUserDetails = (UserDetails) httpSession
					.getAttribute(IssueTrackerConstants.ISSUETRACKERCONSTANTS_LOGIN_USER_SESSION_NAME);
			int updateResult = 0;
			if (statusToUpdate.equalsIgnoreCase("resolve")) {
				updateResult = issueTrackerMasterDao.setIssueStatusToUpdate(
						selectedIssuebean.getIssueSno(),
						issueTrackerLookupBean, logedInUserDetails.getUserId(),
						logedInUserDetails.getFirstName(), estimatedTime,
						perDone, new Date());
			} else {
				updateResult = issueTrackerMasterDao.setIssueStatusToUpdate(
						selectedIssuebean.getIssueSno(),
						issueTrackerLookupBean, logedInUserDetails.getUserId(),
						logedInUserDetails.getFirstName(), estimatedTime,
						perDone, null);
			}
			if (updateResult > 0) {

				sendEmailOfIssueDetails(
						IssueTrackerConstants.ISSUETRACKERCONSTANTS_STATUS,
						logedInUserDetails.getEmailId(),
						logedInUserDetails.getFirstName(),
						selectedIssuebean,
						((selectedIssuebean.getStatus() != null) ? selectedIssuebean
								.getStatus().getLookupName() : ""),
						statusToUpdate, "", logedInUserDetails);
				if (!logedInUserDetails.getEmailId().equalsIgnoreCase(
						selectedIssuebean.getReportedByEmailId())) {
					sendEmailOfIssueDetails(
							IssueTrackerConstants.ISSUETRACKERCONSTANTS_STATUS,
							selectedIssuebean.getReportedByEmailId(),
							logedInUserDetails.getFirstName(),
							selectedIssuebean,
							((selectedIssuebean.getStatus() != null) ? selectedIssuebean
									.getStatus().getLookupName() : ""),
							statusToUpdate, "", logedInUserDetails);
				}
				IssueStatusTracking issueStatusTracking = new IssueStatusTracking();
				// old status
				issueStatusTracking.setCurrentStatus(selectedIssuebean
						.getStatus());
				// new status
				issueStatusTracking.setUpdatedStatus(issueTrackerLookupBean);
				issueStatusTracking
						.setStatusUpdateRelatedComment(statusUpdateRelatedComment);
				issueStatusTracking.setIssueSno(selectedIssuebean);
				issueStatusTracking.setUpdatedById(logedInUserDetails
						.getUserId());
				issueStatusTracking.setUpdatedByName(logedInUserDetails
						.getFirstName());
				issueStatusTracking.setUpdatedDate(new Date());
				issueStatusTrackingDao
						.setIssueStatusTrackingToSave(issueStatusTracking);

				// updating the selectedIssue details in session. this should be
				// after adding status tracking for old status.
				if (statusToUpdate.equalsIgnoreCase("Resolve")) {

					selectedIssuebean.setResolvedDate(new Date());

				}
				selectedIssuebean.setStatus(issueTrackerLookupBean);
				selectedIssuebean.setEstimatedTime(estimatedTime);
				selectedIssuebean.setPercentageDoneId(Double.valueOf(perDone));
				selectedIssuebean
						.setUpdatedById(logedInUserDetails.getUserId());
				selectedIssuebean.setUpdatedByName(logedInUserDetails
						.getFirstName());
				selectedIssuebean.setUpdatedDate(new Date());
				httpSession
						.setAttribute(
								IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_SELECTED_ISSUE_SESSION_NAME,
								selectedIssuebean);

				// code for saving activity
				activityStreamDirectoryService.setActivityToSave(
						selectedIssuebean, logedInUserDetails,
						IssueTrackerConstants.ACTIVITY_TYPE_FOR_UPDATE_STATUS,
						statusUpdateRelatedComment, selectedIssuebean
								.getStatus().getLookupName(),
						issueTrackerLookupBean.getLookupName());
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// method for creating email format while updating the status of the ticket
	private void sendEmailOfIssueDetails(String actiontype,
			String logedUserEmailId, String logedUserName,
			IssueTrackerMaster issueBean, String prevStatus, String curStatus,
			String comment, UserDetails logedInUserDetailsBean) {

		logger.info("---- Entered sendEmailOfIssueDetails() of IssueInDetailServiceImpl ----");

		IssueTrackerUtility itUtil = new IssueTrackerUtility();
		String emailSub = "";
		String toEmails = null;
		String ccEmail = null;
		String emailBody = "";
		boolean isSendEmail = false;
		try {
			EncrypterDecrypter encrydecry = new EncrypterDecrypter();
			// defining values required for send email starts here
			PropertyFileReader pfr = PropertyFileReader.getInstance();
			toEmails = logedUserEmailId;

			String hostName = pfr.getStringProperty("PDHostName");
			String portNumber = pfr.getStringProperty("PDPortNumber");
			String appName = pfr
					.getStringProperty("issueTrackerDeployementName");
			String actionUrl = "";
			if (logedInUserDetailsBean.isRedirectedFromMPR()) {
				actionUrl = "http://"
						+ hostName
						+ ":"
						+ portNumber
						+ "/"
						+ appName
						+ "/excludeIntercepter/getIssueInDetailFromEmail?emailID="
						+ encrydecry.encryptData(logedInUserDetailsBean
								.getEmailId())
						+ "&issueNo="
						+ issueBean.getIssueSno()
						+ "&applicationId="
						+ issueBean.getApplicationDirectory()
								.getApplicationSno();
			} else {
				actionUrl = "http://"
						+ hostName
						+ ":"
						+ portNumber
						+ "/"
						+ appName
						+ "/excludeIntercepter/getIssueInDetailFromEmail?emailID="
						+ encrydecry.encryptData(logedUserEmailId)
						+ "&issueNo="
						+ issueBean.getIssueSno()
						+ "&applicationId="
						+ issueBean.getApplicationDirectory()
								.getApplicationSno();
			}

			emailSub = "["
					+ issueBean.getApplicationDirectory().getApplicationName()
					+ " - " + issueBean.getIssueType().getLookupName() + " #"
					+ issueBean.getIssueSno() + "] " + issueBean.getSummary();

			if (IssueTrackerConstants.ISSUETRACKERCONSTANTS_STATUS
					.equalsIgnoreCase(actiontype)) {
				isSendEmail = true;
				emailBody = "<b><i><font color='#697FED'>"
						+ logedUserName
						+ "</font></i></b> updated the status of ticket <a href='"
						+ actionUrl
						+ "'>["
						+ issueBean.getIssueType().getLookupName()
						+ " #"
						+ issueBean.getIssueSno()
						+ "] "
						+ issueBean.getSummary()
						+ "</a> from <b><i>"
						+ prevStatus
						+ "</i></b> to <b><i>"
						+ curStatus
						+ "</i></b>. Please find the following details of ticket:<br><br>"
						+ "Application:&nbsp; "
						+ issueBean.getApplicationDirectory()
								.getApplicationName()
						+ "<br>"
						+ "Module:&nbsp;"
						+ ((issueBean.getApplicationModuleDirectory() != null) ? issueBean
								.getApplicationModuleDirectory()
								.getModuleName() : "")
						+ "<br>"
						+ "Project :&nbsp; "
						+ issueBean.getRelatedProject()
						+ "<br>"
						+ "Issue Type:&nbsp; "
						+ issueBean.getIssueType().getLookupName()
						+ "<br>"
						+ "Current Status:&nbsp; "
						+ curStatus
						+ "<br>"
						+ "Priority:&nbsp; "
						+ ((issueBean.getSeverity() != null) ? issueBean
								.getSeverity().getLookupName() : "") + "<br>"
						+ "Raised On:&nbsp; "
						+ itUtil.formatDate(issueBean.getReportedDate())
						+ "<br>" + "Raised By:&nbsp; "
						+ issueBean.getReportedByName() + "<br>"
						+ "Description :&nbsp; " + issueBean.getDescription()
						+ "<br>" + "Due on :&nbsp; "
						+ itUtil.formatDate(issueBean.getDueDate()) + "<br>";
			} else if (IssueTrackerConstants.ISSUETRACKERCONSTANTS_COMMENT
					.equalsIgnoreCase(actiontype)) {
				isSendEmail = true;
				emailBody = "<b><i><font color='#697FED'>"
						+ logedUserName
						+ "</font><i></b> commented on ticket <a href='"
						+ actionUrl
						+ "'>["
						+ issueBean.getIssueType().getLookupName()
						+ " #"
						+ issueBean.getIssueSno()
						+ "] "
						+ issueBean.getSummary()
						+ "</a>.<br><br>"
						+ "<i>"
						+ comment
						+ "</i><hr><br>"
						+ "Application:&nbsp; "
						+ issueBean.getApplicationDirectory()
								.getApplicationName()
						+ "<br>"
						+ "Module:&nbsp; "
						+ ((issueBean.getApplicationModuleDirectory() != null) ? issueBean
								.getApplicationModuleDirectory()
								.getModuleName() : "")
						+ "<br>"
						+ "Project :&nbsp; "
						+ issueBean.getRelatedProject()
						+ "<br>"
						+ "Issue Type:&nbsp; "
						+ issueBean.getIssueType().getLookupName()
						+ "<br>"
						+ "Status:&nbsp; "
						+ ((issueBean.getStatus() != null) ? issueBean
								.getStatus().getLookupName() : "")
						+ "<br>"
						+ "Priority:&nbsp; "
						+ ((issueBean.getSeverity() != null) ? issueBean
								.getSeverity().getLookupName() : "") + "<br>"
						+ "Raised On:&nbsp; "
						+ itUtil.formatDate(issueBean.getReportedDate())
						+ "<br>" + "Raised By:&nbsp; "
						+ issueBean.getReportedByName() + "<br>"
						+ "Description :&nbsp; " + issueBean.getDescription()
						+ "<br>" + "Due on :&nbsp; "
						+ itUtil.formatDate(issueBean.getDueDate()) + "<br>";
			}

			if (isSendEmail) {

				EmailNotificationBean emailNotificationBean = new EmailNotificationBean();
				emailNotificationBean.setDomainId(1L);
				emailNotificationBean.setEmailTo(logedInUserDetailsBean.getEmailId());//hardcoded for test
				emailNotificationBean.setEmailBcc(null);
				emailNotificationBean.setEmailCc(ccEmail);
				emailNotificationBean.setEmailContent(emailBody);
				emailNotificationBean.setEmailSubject(emailSub);
				emailNotificationBean.setEmailFrom(logedUserEmailId);
				emailNotificationBean.setSubmittedBy(Long.valueOf(logedInUserDetailsBean.getUserId()));
				emailNotificationBean.setSubmittedDate(new Date());
				emailHandlerThread.executeEmailTask(emailNotificationBean);
				
				/*EcosystemEmailExpose.sendEmailWithEcosystemExpose(toEmails,
						ccEmail, emailSub, emailBody, null);
*/			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendEmailWhileUserCommentOnIssue(String actiontype,
			UserDetails logedUserDetails, IssueTrackerMaster issueBean,
			String prevStatus, String curStatus, String comment) {

		logger.info("---- Entered sendEmailWhileUserCommentOnIssue() of IssueInDetailServiceImpl ----");

		try {
			sendEmailOfIssueDetails(
					IssueTrackerConstants.ISSUETRACKERCONSTANTS_COMMENT,
					logedUserDetails.getEmailId(),
					logedUserDetails.getFirstName(), issueBean, prevStatus,
					curStatus, comment, logedUserDetails);
			if (!logedUserDetails.getEmailId().equalsIgnoreCase(
					issueBean.getReportedByEmailId())) {
				sendEmailOfIssueDetails(
						IssueTrackerConstants.ISSUETRACKERCONSTANTS_COMMENT,
						issueBean.getReportedByEmailId(),
						logedUserDetails.getFirstName(), issueBean, prevStatus,
						curStatus, comment, logedUserDetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void setDocumentToDelete(int descId, HttpSession session)
			throws Exception {

		logger.info("Deleteing a document");
		issueRelatedDocumentDao.deleteDocumentMapping(descId);
		fileUploadService.deleteFile(descId);

	}

	@Override
	@SuppressWarnings("unchecked")
	public void setIssueRelDocumentToUpload(HttpSession session,
			List<MultipartFile> uploadedFiles) throws Exception {

		logger.info("---- Entered setIssueRelDocumentToUpload() of IssueInDetailServiceImpl ----");

		try {
			UserDetails logedUser = (UserDetails) session
					.getAttribute(IssueTrackerConstants.ISSUETRACKERCONSTANTS_LOGIN_USER_SESSION_NAME);
			IssueTrackerMaster selectedIssue = (IssueTrackerMaster) session
					.getAttribute(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_SELECTED_ISSUE_SESSION_NAME);

			Map<String, Object> fileIdsReturned = fileUploadService
					.uploadListOfFile(uploadedFiles);
			Object status = fileIdsReturned.get("success");
			if (status != null) {

				List<Integer> idsList = (List<Integer>) fileIdsReturned
						.get("success");

				for (Integer tempId : idsList) {
					IssueDocumentsMapping issueDocument = new IssueDocumentsMapping();
					issueDocument.setRelatedDocument(tempId);
					issueDocument.setRelatedIssue(selectedIssue.getIssueSno());
					issueDocument.setSubmittedBy(logedUser.getUserId());
					issueRelatedDocumentDao.saveDocumentMapping(issueDocument);
				}
			}

			if (selectedIssue != null) {
				activityStreamDirectoryService.setActivityToSave(selectedIssue,
						logedUser,
						IssueTrackerConstants.ACTIVITY_TYPE_1_FOR_ATTACHMENT,
						"added the dcument", "", "");
			}
		} catch (Exception e) {
			System.out.println("The error message is  " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

}
