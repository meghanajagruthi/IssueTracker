package com.elecnor.issue.tracker.serviceImpl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecnor.issue.tracker.bean.ActivityStreamDirectory;
import com.elecnor.issue.tracker.bean.IssueTrackerMaster;
import com.elecnor.issue.tracker.bean.UserDetails;
import com.elecnor.issue.tracker.dao.ActivityStreamDirectoryDao;
import com.elecnor.issue.tracker.service.ActivityStreamDirectoryService;

@Service
public class ActivityStreamDirectoryServiceImpl implements ActivityStreamDirectoryService{

	Logger logger = Logger.getLogger(ActivityStreamDirectoryServiceImpl.class);
	@Autowired
	ActivityStreamDirectoryDao activityStreamDirectoryDao;
	
	@Override
	public void setActivityToSave(IssueTrackerMaster issueDetailBean,
			UserDetails logedInUserDetails, String activityType, String activitySummaryToSave, String oldValue, String newValue)
			throws Exception {
		
		logger.info("---- Entered setActivityToSave() of ActivityStreamDirectoryServiceImpl ----");
		
		try{
			ActivityStreamDirectory activityBean = new ActivityStreamDirectory();
			activityBean.setActivityType(activityType);
			activityBean.setActivitySummary(activitySummaryToSave);
			activityBean.setOldValue(oldValue);
			activityBean.setNewValue(newValue);
			activityBean.setRelatedIssueForActivity(issueDetailBean);
			activityBean.setDomainId(logedInUserDetails.getDomainIdTransient());//hardcoded by meghanajagruthi for testing purpose
			activityBean.setActivityGeneratedById(logedInUserDetails.getUserId());
			activityBean.setActivityGeneratedByName(logedInUserDetails.getFirstName());
			activityBean.setActivityGeneratedDate(new Date());
			activityStreamDirectoryDao.setActivityToSave(activityBean);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}

}
