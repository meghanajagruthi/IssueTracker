package com.elecnor.issue.tracker.helper;


import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import com.elecnor.issue.tracker.daoImpl.IssueTrackerMasterDaoImpl;
import com.elecnor.issue.tracker.util.IssueTrackerUtility;
import com.elecnor.issue.tracker.util.PropertyFileReader;

public class EcosystemEmailExpose {
	
	public static  void sendEmailWithEcosystemExpose(String toEmail, String ccEmail, String subject,
			String msgContent, String attachmentpos){
		
		Logger logger = Logger.getLogger(IssueTrackerMasterDaoImpl.class);
		logger.info("Inside sending email");
		IssueTrackerUtility utilityRef = new IssueTrackerUtility();
		try{
			PropertyFileReader pfr = PropertyFileReader.getInstance();
		//
		String bccEmail = pfr.getStringProperty("developmentGroupEmailID");
		bccEmail += ","+pfr.getStringProperty("developmentManagerEmailID");
		
		String ecoUrl = utilityRef.getEcosystemUrl();
		String restApiUrl = ecoUrl+"excludeInterceptor/setExposeToSendEmail?toEmail={toEmail}&ccEmail={ccEmail}&subject={subject}&msgContent={msgContent}&attachmentpos={attachmentpos}&bccEmail={bccEmail}";
		RestTemplate restApi = new RestTemplate();
		restApi.getForObject(restApiUrl, String.class,toEmail,ccEmail,subject, msgContent, attachmentpos, bccEmail);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
