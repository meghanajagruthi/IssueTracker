package com.elecnor.issue.tracker.helper;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.elecnor.issue.tracker.bean.IssueDocumentsMapping;
import com.elecnor.issue.tracker.bean.IssueRelatedDocument;
import com.elecnor.issue.tracker.bean.IssueTrackerMaster;
import com.elecnor.issue.tracker.bean.UserDetails;

public class IssueRelatedDocumentHelper {

	Logger logger = Logger.getLogger(IssueRelatedDocumentHelper.class);
	
	//method to return Document bean for a file
	/*
	public  MetadataBean  saveUploadedDocContents(MultipartFile fileDetails){
		
		if(fileDetails.getOriginalFilename().contains(".")){
			String[] stringArray = fileDetails.getOriginalFilename().split("\\.");
			metaDataBean.setDocumentName(stringArray[0]);
			metaDataBean.setDocumentType(stringArray[1]);
			
		}
		else{
			metaDataBean.setDocumentId(fileDetails.getOriginalFilename());
			metaDataBean.setDocumentType(fileDetails.getContentType());
		}
		
		return metaDataBean;
	}*/
	
	public IssueDocumentsMapping getDocumentMappingFile(Integer savedDocumentId,IssueTrackerMaster issueTrackerMasterBean){
		IssueDocumentsMapping documentMappingFile=new IssueDocumentsMapping();
		documentMappingFile.setRelatedDocument(savedDocumentId);
		documentMappingFile.setRelatedIssue(issueTrackerMasterBean.getIssueSno()); 
		return documentMappingFile;
	}
		public IssueRelatedDocument getDocumentBeanForFile(MultipartFile fileDetails, IssueTrackerMaster issueTrackerMasterBean, UserDetails logedInUserDetailsTemp)throws Exception{
			
			logger.info("---- Entered getDocumentBeanForFile() of IssueRelatedDocumentHelper ----");
			
			IssueRelatedDocument issueRelatedDocumentBean = new IssueRelatedDocument();
			try {
				if(fileDetails.getOriginalFilename().contains(".")){
					String[] stringArray = fileDetails.getOriginalFilename().split("\\.");
					issueRelatedDocumentBean.setDocumentName(stringArray[0]);
					issueRelatedDocumentBean.setDocumentType(stringArray[1]);
				} else {
					issueRelatedDocumentBean.setDocumentName(fileDetails.getOriginalFilename());
					issueRelatedDocumentBean.setDocumentType(fileDetails.getContentType());
				}
				
				issueRelatedDocumentBean.setDocumentContent(fileDetails.getBytes());
				issueRelatedDocumentBean.setIssueTrackerMaster(issueTrackerMasterBean);
				issueRelatedDocumentBean.setSubmittedBy(logedInUserDetailsTemp.getUserId());
				issueRelatedDocumentBean.setSubmittedByName(logedInUserDetailsTemp.getFirstName());
				issueRelatedDocumentBean.setSubmittedByEmail(logedInUserDetailsTemp.getEmailId());
				issueRelatedDocumentBean.setSubmittedDate(new Date());
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
			
			return issueRelatedDocumentBean;
		}
}
