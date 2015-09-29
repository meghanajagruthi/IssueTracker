package com.elecnor.issue.tracker.daoImpl;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.issue.tracker.bean.ApplicationDirectory;
import com.elecnor.issue.tracker.dao.ApplicationDirectoryDao;
import com.elecnor.issue.tracker.util.IssueTrackerConstants;

@Repository
public class ApplicationDirectoryDaoImpl implements ApplicationDirectoryDao {

	Logger logger = Logger.getLogger(ApplicationDirectoryDaoImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public ApplicationDirectory getApplicationDetailsById(String applicationId)
			throws Exception {

		logger.info("---- Entered getApplicationDetailsById() of ApplicationDirectoryDaoImpl ----");

		ApplicationDirectory resultDetails = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_FROM
									+ " "
									+ IssueTrackerConstants.APPLICATION_DIRECTORY_BEAN_NAME
									+ " "
									+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE
									+ " "
									+ IssueTrackerConstants.APPLICATION_DIRECTORY_COLUMN_APPLICATIONSNO
									+ "=:applicationSno");
			query.setParameter("applicationSno", applicationId);
			resultDetails = (ApplicationDirectory) query.list().get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return resultDetails;
	}

}
