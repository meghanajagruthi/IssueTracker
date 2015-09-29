package com.elecnor.issue.tracker.daoImpl;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.issue.tracker.bean.ApplicationModuleDirectory;
import com.elecnor.issue.tracker.dao.ApplicationModuleDirectoryDao;
import com.elecnor.issue.tracker.util.IssueTrackerConstants;

@Repository
public class ApplicationModuleDirectoryDaoImpl implements
		ApplicationModuleDirectoryDao {

	Logger logger = Logger.getLogger(ApplicationModuleDirectoryDaoImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public ApplicationModuleDirectory getModuleDetailsById(String moduleId)
			throws Exception {

		logger.info("---- Entered getModuleDetailsById() of ApplicationModuleDirectoryDaoImpl ----");

		ApplicationModuleDirectory resultDetails = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_FROM
									+ " "
									+ IssueTrackerConstants.MODULEDIRECTORY_BEAN_NAME
									+ " "
									+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE
									+ " "
									+ IssueTrackerConstants.MODULEDIRECTORY_COLUMN_MODULE_SNO
									+ "=:moduleId");
			query.setParameter("moduleId", moduleId);
			resultDetails = (ApplicationModuleDirectory) query.list().get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return resultDetails;
	}

}
