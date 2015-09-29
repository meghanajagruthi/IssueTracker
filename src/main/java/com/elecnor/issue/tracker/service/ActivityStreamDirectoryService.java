package com.elecnor.issue.tracker.service;

import com.elecnor.issue.tracker.bean.IssueTrackerMaster;
import com.elecnor.issue.tracker.bean.UserDetails;

public interface ActivityStreamDirectoryService {

	public void setActivityToSave(IssueTrackerMaster issueDetailBean, UserDetails logedInUserDetails, String activityType, String activitySummaryToSave, String oldValue, String newValue)throws Exception;
}
