package com.elecnor.issue.tracker.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elecnor.issue.tracker.bean.IssueDocumentsMapping;
import com.elecnor.issue.tracker.bean.IssueRelatedDocument;
import com.elecnor.issue.tracker.bean.IssueStatusTracking;
import com.elecnor.issue.tracker.bean.IssueTrackerLookup;
import com.elecnor.issue.tracker.bean.IssueTrackerMaster;
import com.elecnor.issue.tracker.bean.UserDetails;
import com.elecnor.issue.tracker.dao.ApplicationDirectoryDao;
import com.elecnor.issue.tracker.dao.ApplicationModuleDirectoryDao;
import com.elecnor.issue.tracker.dao.AssigneeProfileDao;
import com.elecnor.issue.tracker.dao.IssueRelatedDocumentDao;
import com.elecnor.issue.tracker.dao.IssueStatusTrackingDao;
import com.elecnor.issue.tracker.dao.IssueTrackerLookupDao;
import com.elecnor.issue.tracker.dao.IssueTrackerMasterDao;
import com.elecnor.issue.tracker.helper.EcosystemEmailExpose;
import com.elecnor.issue.tracker.helper.IssueRelatedDocumentHelper;
import com.elecnor.issue.tracker.service.ActivityStreamDirectoryService;
import com.elecnor.issue.tracker.service.IssueTrackerMasterService;
import com.elecnor.issue.tracker.util.EncrypterDecrypter;
import com.elecnor.issue.tracker.util.IssueTrackerConstants;
import com.elecnor.issue.tracker.util.IssueTrackerUtility;
import com.elecnor.issue.tracker.util.PropertyFileReader;
import com.email.utility.bean.EmailNotificationBean;
import com.email.utility.executor.EmailTaskExecutor;
import com.file.handler.service.FileUploadService;

@Service
public class IssueTrackerMasterServiceImpl implements IssueTrackerMasterService {

	Logger logger = Logger.getLogger(IssueTrackerMasterServiceImpl.class);
	
	@Autowired
	EmailTaskExecutor emailHandlerThread;
	@Autowired
	FileUploadService fileUploadService;
	@Autowired
	IssueTrackerMasterDao issueTrackerMasterDao;
	@Autowired
	IssueStatusTrackingDao issueStatusTrackingDao;
	@Autowired
	IssueTrackerLookupDao issueTrackerLookupDao;
	@Autowired
	ApplicationDirectoryDao applicationDirectoryDao;
	@Autowired
	ApplicationModuleDirectoryDao applicationModuleDirectoryDao;
	@Autowired
	IssueRelatedDocumentDao issueRelatedDocumentDao;
	@Autowired
	AssigneeProfileDao assigneeProfileDao;
	@Autowired
	ActivityStreamDirectoryService activityStreamDirectoryService;

	@Override
	@SuppressWarnings("unchecked")
	public String setIssueToSaveOrUpdate(IssueTrackerMaster issueTrackerMaster,
			UserDetails logedUser, String relatedApplication,
			String relatedModule, String issueType,
			String issueStatus, String issueSeverity, String assigneeId, HttpSession httpSession, List<MultipartFile> uploadedFiles) throws Exception {

		logger.info("---- Entered setIssueToSaveOrUpdate() of IssueTrackerMasterServiceImpl ----");
		
		String result = null;
		IssueRelatedDocumentHelper issueRelatedDocumentHelperRef = new IssueRelatedDocumentHelper();
		
		try {
			
			if(relatedApplication != null && relatedApplication != "" && !relatedApplication.isEmpty()){
				issueTrackerMaster.setApplicationDirectory(applicationDirectoryDao.getApplicationDetailsById(relatedApplication));
			}
			if(relatedModule != null && relatedModule != "" && !relatedModule.isEmpty()){
				issueTrackerMaster.setApplicationModuleDirectory(applicationModuleDirectoryDao.getModuleDetailsById(relatedModule));
			}
			if(issueType != null && issueType != "" && !issueType.isEmpty()){
				issueTrackerMaster.setIssueType(issueTrackerLookupDao.getLookupTableDetailsById(issueType));
			}
			if(issueStatus != null && issueStatus != "" && !issueStatus.isEmpty()){
				IssueTrackerLookup  status =issueTrackerLookupDao.getLookupTableDetailsById(issueStatus);
  			if(status.getLookupName().equalsIgnoreCase("resolved"))
 				{
				issueTrackerMaster.setResolvedDate(new Date());
			    }
				issueTrackerMaster.setStatus(status);
			}
			if(issueSeverity != null && issueSeverity != "" && !issueSeverity.isEmpty()){
				issueTrackerMaster.setSeverity(issueTrackerLookupDao.getLookupTableDetailsById(issueSeverity));
			}
			
			if (issueTrackerMaster.getIssueSno() == null) {
				issueTrackerMaster.setDomainId(logedUser.getDomainIdTransient());
				issueTrackerMaster.setReportedById(logedUser.getUserId());
				issueTrackerMaster.setReportedByName(logedUser.getFirstName());
				if(logedUser.getTemporaryEmailId() != null && logedUser.getTemporaryEmailId() != ""){
					issueTrackerMaster.setReportedByEmailId(logedUser.getTemporaryEmailId());
				} else {
					
					if(assigneeId != null && assigneeId != "" && !assigneeId.isEmpty()){
					
						if(!issueTrackerMaster.getAssigneeProfile().getAssigneeSno().equals(assigneeId)){//SENDING AN EMAIL WHENEVER A NEW ASSIGNEE IS ASSIGNED TO A TICKET
						
						sendEmailOfIssueDetails(IssueTrackerConstants.ISSUETRACKERCONSTANTS_SAVE,IssueTrackerConstants.ISSUETRACKERCONSTANTS_MANAGER_EMAIL,IssueTrackerConstants.ISSUETRACKERCONSTANTS_MANAGER_FIRSTNAME,issueTrackerMaster, logedUser);
					}
				 }
					issueTrackerMaster.setReportedByEmailId(logedUser.getEmailId());
				}
				
				if(assigneeId != null && assigneeId != "" && !assigneeId.isEmpty()){
					
					issueTrackerMaster.setAssigneeProfile(assigneeProfileDao.getAssigneeProfileById(assigneeId));
				}
				issueTrackerMaster.setReportedDate(new Date());
				IssueTrackerMaster savedIssueBean = issueTrackerMasterDao
						.setIssueToSaveOrUpdate(issueTrackerMaster);
				if(savedIssueBean != null){
					if(logedUser.getTemporaryEmailId() != null){
						sendEmailOfIssueDetails(IssueTrackerConstants.ISSUETRACKERCONSTANTS_SAVE,logedUser.getTemporaryEmailId(),logedUser.getFirstName(),savedIssueBean, logedUser);
					} else {
                         
						sendEmailOfIssueDetails(IssueTrackerConstants.ISSUETRACKERCONSTANTS_SAVE,logedUser.getEmailId(),logedUser.getFirstName(),savedIssueBean, logedUser);
					}
					
					//code for saving related document if user uploaded any documents
					
				
					if(uploadedFiles != null){
						Map<String, Object> fileIdsReturned = fileUploadService
								.uploadListOfFile(uploadedFiles);
						Object status = fileIdsReturned.get("success");
						if (status != null) {

							List<Integer> idsList = (List<Integer>) fileIdsReturned
									.get("success");

							for (Integer tempId : idsList) {
								IssueDocumentsMapping issueDocument = new IssueDocumentsMapping();
								issueDocument.setRelatedDocument(tempId);
								issueDocument.setRelatedIssue(savedIssueBean.getIssueSno());
								issueDocument.setSubmittedBy(logedUser.getUserId());
								issueRelatedDocumentDao.saveDocumentMapping(issueDocument);
							}
						}
						
					}
					
					//Code for saving activity in DB
					activityStreamDirectoryService.setActivityToSave(savedIssueBean, logedUser, IssueTrackerConstants.ACTIVITY_TYPE_FOR_CREATE_ISSUE,"","","");
				}
			} else {
				ArrayList<IssueTrackerMaster> issueMasterList = issueTrackerMasterDao
						.getIssueDetailsById(issueTrackerMaster.getIssueSno());
				
				issueTrackerMaster.setReportedById(issueMasterList.get(0).getReportedById());
				issueTrackerMaster.setReportedByName(issueMasterList.get(0).getReportedByName());
				issueTrackerMaster.setReportedByEmailId(issueMasterList.get(0).getReportedByEmailId());
				issueTrackerMaster.setReportedDate(issueMasterList.get(0).getReportedDate());
				issueTrackerMaster.setDomainId(issueMasterList.get(0).getDomainId());
				issueTrackerMaster.setUpdatedById(logedUser.getUserId());
				issueTrackerMaster.setUpdatedByName(logedUser.getFirstName());
				issueTrackerMaster.setUpdatedDate(new Date());
				if(assigneeId != null && assigneeId != "" && !assigneeId.isEmpty()){
					issueTrackerMaster.setAssigneeProfile(assigneeProfileDao
							.getAssigneeProfileById(assigneeId));
					if(issueTrackerMaster.getStatus() != null && issueTrackerMaster.getStatus().getLookupName().equalsIgnoreCase("new")){
						try {
							IssueTrackerLookup inProgessStatusBean = issueTrackerLookupDao.getLookupTableDetailsByLookupName("In Progress%");
							issueTrackerMaster.setStatus(inProgessStatusBean);
						} catch (Exception e) {
							e.printStackTrace();
						}	
					}
					
				}
				IssueTrackerMaster savedIssue = issueTrackerMasterDao
						.setIssueToSaveOrUpdate(issueTrackerMaster);
				 if(savedIssue != null){
					 
					 sendEmailOfIssueDetails(IssueTrackerConstants.ISSUETRACKERCONSTANTS_UPDATE,logedUser.getEmailId(), logedUser.getFirstName(),savedIssue, logedUser);
					 if(!logedUser.getEmailId().equalsIgnoreCase(savedIssue.getReportedByEmailId())){
						 sendEmailOfIssueDetails(IssueTrackerConstants.ISSUETRACKERCONSTANTS_UPDATE,savedIssue.getReportedByEmailId(), logedUser.getFirstName(),savedIssue, logedUser); 
					 }
					 
				 //if both the status's are not equal then we will save the
				 //old status into statusTracking table.
				 httpSession.setAttribute("selectedIssueDetals", savedIssue);
				 if(savedIssue.getStatus().getLookupSno() !=
				 issueMasterList.get(0).getStatus().getLookupSno()){
				 IssueStatusTracking issueStatusTracking = new
				 IssueStatusTracking();
				 issueStatusTracking.setCurrentStatus(issueMasterList.get(0).getStatus());
				 issueStatusTracking.setUpdatedStatus(savedIssue.getStatus());
				 issueStatusTracking.setIssueSno(issueMasterList.get(0));
				 issueStatusTracking.setUpdatedById(logedUser.getUserId());
				 issueStatusTracking.setUpdatedByName(logedUser.getFirstName());
				 issueStatusTracking.setUpdatedDate(new Date());
				 issueStatusTrackingDao.setIssueStatusTrackingToSave(issueStatusTracking);
				 }
				 
				 //code for saving related document if user uploaded any documents
				 if(uploadedFiles != null){
						for(int fs=0; fs<uploadedFiles.size();fs++){
							if(uploadedFiles.size() == 1){
								if(uploadedFiles.get(fs).getOriginalFilename() != null && !uploadedFiles.get(fs).getOriginalFilename().isEmpty() && uploadedFiles.get(fs).getSize() != 0){
									IssueRelatedDocument issueRelatedDocumentBean = issueRelatedDocumentHelperRef.getDocumentBeanForFile(uploadedFiles.get(fs), savedIssue, logedUser);
									issueRelatedDocumentDao.setIssueRealtedDocumentToSave(issueRelatedDocumentBean);
								}
							} else {
								IssueRelatedDocument issueRelatedDocumentBean = issueRelatedDocumentHelperRef.getDocumentBeanForFile(uploadedFiles.get(fs), savedIssue, logedUser);
								issueRelatedDocumentDao.setIssueRealtedDocumentToSave(issueRelatedDocumentBean);
							}
						}
					}
				 
				//Code for saving activity in DB
				 if(assigneeId != null && !assigneeId.isEmpty() && issueMasterList.get(0).getAssigneeProfile() != null && Integer.parseInt(assigneeId) != issueMasterList.get(0).getAssigneeProfile().getAssigneeSno()){
						activityStreamDirectoryService.setActivityToSave(savedIssue, logedUser, IssueTrackerConstants.ACTIVITY_TYPE_FOR_UPDATE_ASSIGNEE, "",issueMasterList.get(0).getAssigneeProfile().getAssigneeName(),savedIssue.getAssigneeProfile().getAssigneeName());
					} else if(assigneeId != null && !assigneeId.isEmpty() && issueMasterList.get(0).getAssigneeProfile() == null){
						activityStreamDirectoryService.setActivityToSave(savedIssue, logedUser, IssueTrackerConstants.ACTIVITY_TYPE_FOR_ADD_ASSIGNEE, "","",savedIssue.getAssigneeProfile().getAssigneeName());
					} else {
						activityStreamDirectoryService.setActivityToSave(savedIssue, logedUser, IssueTrackerConstants.ACTIVITY_TYPE_FOR_UPDATE_ISSUE, "","","");
					}
				 }

			}
		} catch (Exception e) {
			result = e.getMessage();
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	
	//method for creating email format while creating and updating the issue
	private void sendEmailOfIssueDetails(String actiontype, String logedUserEmailId, String logedUserName, IssueTrackerMaster issueBean, UserDetails logedInUserDetails){
		
		logger.info("---- Entered sendEmailOfIssueDetails() of IssueTrackerMasterServiceImpl ----");
		
		String emailSub = "";
		String toEmails = null;
		String ccEmail = null;
		String emailBody = "";
		boolean isSendEmail = false;
		try{
			IssueTrackerUtility itUtil = new IssueTrackerUtility();
			EncrypterDecrypter encrypterDecrypter = new EncrypterDecrypter();
			//defining values required for send email starts here
			PropertyFileReader pfr = PropertyFileReader.getInstance();
			toEmails = logedUserEmailId;
			
				String hostName = pfr.getStringProperty("PDHostName");
				String portNumber = pfr.getStringProperty("PDPortNumber");
				String appName = pfr.getStringProperty("issueTrackerDeployementName");
				String actionUrl = "";
				if(logedInUserDetails.isRedirectedFromMPR()){
					actionUrl = "http://" + hostName + ":" + portNumber + "/"+appName+"/excludeIntercepter/getIssueInDetailFromEmail?emailID="
							+ encrypterDecrypter.encryptData(logedInUserDetails.getEmailId()) + "&issueNo=" + issueBean.getIssueSno() +"&applicationId="+issueBean.getApplicationDirectory().getApplicationSno();
				} else {
					actionUrl = "http://" + hostName + ":" + portNumber + "/"+appName+"/excludeIntercepter/getIssueInDetailFromEmail?emailID="
							+ encrypterDecrypter.encryptData(logedUserEmailId) + "&issueNo=" + issueBean.getIssueSno() +"&applicationId="+issueBean.getApplicationDirectory().getApplicationSno();
				}
		 
		 
			emailSub = "["+issueBean.getApplicationDirectory().getApplicationName()+" - "+issueBean.getIssueType().getLookupName()+" #"+issueBean.getIssueSno()+"] "+issueBean.getSummary();
			
			if(IssueTrackerConstants.ISSUETRACKERCONSTANTS_SAVE.equalsIgnoreCase(actiontype)){
				isSendEmail = true;
				emailBody = "<b>Ticket <a href='" + actionUrl
				+ "'>["+issueBean.getIssueType().getLookupName()+" #"+issueBean.getIssueSno()+"] "+issueBean.getSummary()+"</a> has been raised. Please find the following details of ticket:</b><br><br>"
				+"Application:&nbsp; "+issueBean.getApplicationDirectory().getApplicationName()+"<br>"
				+"Module:&nbsp;"+ ((issueBean.getApplicationModuleDirectory() != null) ? issueBean.getApplicationModuleDirectory().getModuleName():"")+"<br>"
				+"Project :&nbsp; "+issueBean.getRelatedProject()+"<br>"
				+"Issue Type:&nbsp; "+issueBean.getIssueType().getLookupName()+"<br>"
				+"Status:&nbsp; "+((issueBean.getStatus() != null)?issueBean.getStatus().getLookupName():"")+"<br>"
				+"Priority:&nbsp; "+((issueBean.getSeverity() != null)?issueBean.getSeverity().getLookupName():"")+"<br>"
				+"Raised On:&nbsp; "+itUtil.formatDate(issueBean.getReportedDate())+"<br>"
				+"Raised By:&nbsp; "+issueBean.getReportedByName()+"<br>"
				+"Description :&nbsp; "+issueBean.getDescription()+"<br>"
				+"Due on :&nbsp; "+itUtil.formatDate(issueBean.getDueDate())+"<br>";
			} else if(IssueTrackerConstants.ISSUETRACKERCONSTANTS_UPDATE.equalsIgnoreCase(actiontype)){
				isSendEmail = true;
				emailBody = "<b>Ticket <a href='" + actionUrl
				        + "'>["+issueBean.getIssueType().getLookupName()+" #"+issueBean.getIssueSno()+"] "+issueBean.getSummary()+"</a> has been updated. Please find the following details of ticket:</b><br><br>"
						+"Application:&nbsp; "+issueBean.getApplicationDirectory().getApplicationName()+"<br>"
						+"Module:&nbsp; "+((issueBean.getApplicationModuleDirectory() != null)?issueBean.getApplicationModuleDirectory().getModuleName():"")+"<br>"
						+"Project :&nbsp; "+issueBean.getRelatedProject()+"<br>"
						+"Issue Type:&nbsp; "+issueBean.getIssueType().getLookupName()+"<br>"
						+"Status:&nbsp; "+((issueBean.getStatus() != null)?issueBean.getStatus().getLookupName():"")+"<br>"
						+"Priority:&nbsp; "+((issueBean.getSeverity() != null)?issueBean.getSeverity().getLookupName():"")+"<br>"
						+"Raised On:&nbsp; "+itUtil.formatDate(issueBean.getReportedDate())+"<br>"
						+"Raised By:&nbsp; "+issueBean.getReportedByName()+"<br>"
						+"Updated On:&nbsp; "+itUtil.formatDate(issueBean.getUpdatedDate())+"<br>"
						+"Updated By:&nbsp; "+issueBean.getUpdatedByName()+"<br>"
						+"Description :&nbsp; "+issueBean.getDescription()+"<br>"
						+"Due on :&nbsp; "+itUtil.formatDate(issueBean.getDueDate())+"<br>";
			} 
			
			if(isSendEmail){
				
				EmailNotificationBean emailNotificationBean = new EmailNotificationBean();
				emailNotificationBean.setDomainId(1L);
				emailNotificationBean.setEmailTo(logedInUserDetails.getEmailId());//hardcoded for test
				emailNotificationBean.setEmailBcc(null);
				emailNotificationBean.setEmailCc(ccEmail);
				emailNotificationBean.setEmailContent(emailBody);
				emailNotificationBean.setEmailSubject(emailSub);
				emailNotificationBean.setEmailFrom(logedUserEmailId);
				emailNotificationBean.setSubmittedBy(Long.valueOf(logedInUserDetails.getUserId()));
				emailNotificationBean.setSubmittedDate(new Date());
				emailHandlerThread.executeEmailTask(emailNotificationBean);
				//EcosystemEmailExpose.sendEmailWithEcosystemExpose(toEmails, ccEmail, emailSub, emailBody, null);	
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
