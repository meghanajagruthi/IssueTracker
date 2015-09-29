package com.elecnor.issue.tracker.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;

import com.elecnor.issue.tracker.bean.IssueTrackerMaster;
import com.elecnor.issue.tracker.dao.ApplicationDirectoryDao;
import com.elecnor.issue.tracker.dao.ApplicationModuleDirectoryDao;
import com.elecnor.issue.tracker.dao.IssueTrackerLookupDao;

public class IssueTrackerMasterHelper {
	
	
	@Autowired
	ApplicationDirectoryDao applicationDirectoryDAO;
	@Autowired
	ApplicationModuleDirectoryDao applicationModuleDirectoryDAO;
	@Autowired
	IssueTrackerLookupDao issueTrackerLookUpDAO;
	
	public IssueTrackerMaster setIssueBeanFromJson(MultiValueMap<String, String> params) {
		IssueTrackerMaster raisedIssue=new IssueTrackerMaster();
		try{
		System.out.println(params.getFirst("applicationId"));
		System.out.println(params.keySet());
		System.out.println(params.values().size());
	
		//Iterator<String> it = params.keySet().iterator();


      /*  while(it.hasNext()){
          String theKey = (String)it.next();
          System.out.println(theKey);
          System.out.println(params.getFirst(theKey));
      }*/

       
		raisedIssue.setApplicationDirectory(applicationDirectoryDAO.getApplicationDetailsById(params.getFirst("applicationId").trim()));
		raisedIssue.setApplicationModuleDirectory(applicationModuleDirectoryDAO.getModuleDetailsById(params.getFirst("applicationModuleId")));
		raisedIssue.setRelatedProject(params.getFirst("relatedProject"));
		raisedIssue.setIssueType(issueTrackerLookUpDAO.getLookupTableDetailsById(params.getFirst("issueTypeId")));
		raisedIssue.setSummary(params.getFirst("summary"));
		raisedIssue.setDescription(params.getFirst("description"));
		raisedIssue.setStatus(issueTrackerLookUpDAO.getLookupTableDetailsById(params.getFirst("statusId")));
		raisedIssue.setSeverity(issueTrackerLookUpDAO.getLookupTableDetailsById(params.getFirst("severityId")));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return raisedIssue;
	}

}
