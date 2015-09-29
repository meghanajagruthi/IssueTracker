package com.elecnor.issue.tracker.dao;

import java.util.ArrayList;

import com.elecnor.issue.tracker.bean.ActivityStreamDirectory;

public interface ActivityStreamDirectoryDao {
 
	public ActivityStreamDirectory setActivityToSave(ActivityStreamDirectory activityBean)throws Exception;
	public ArrayList<ActivityStreamDirectory> getActivityStreamsListForSelctedIssue(int selIssueNo)throws Exception;
}
