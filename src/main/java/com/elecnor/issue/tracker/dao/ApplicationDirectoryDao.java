package com.elecnor.issue.tracker.dao;

import com.elecnor.issue.tracker.bean.ApplicationDirectory;

public interface ApplicationDirectoryDao {

	public ApplicationDirectory getApplicationDetailsById(String applicationId)throws Exception;
}
