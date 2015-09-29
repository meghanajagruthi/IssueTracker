package com.elecnor.issue.tracker.dao;

import java.util.ArrayList;

import com.elecnor.issue.tracker.bean.AssigneeProfile;

public interface AssigneeProfileDao {

	public ArrayList<AssigneeProfile> getAllAssigneeProfiles()throws Exception;
	public AssigneeProfile getAssigneeProfileById(String assigneeId)throws Exception;
}
