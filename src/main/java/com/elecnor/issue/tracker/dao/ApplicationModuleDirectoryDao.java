package com.elecnor.issue.tracker.dao;

import com.elecnor.issue.tracker.bean.ApplicationModuleDirectory;

public interface ApplicationModuleDirectoryDao {

	public ApplicationModuleDirectory getModuleDetailsById(String moduleId)throws Exception;
}
