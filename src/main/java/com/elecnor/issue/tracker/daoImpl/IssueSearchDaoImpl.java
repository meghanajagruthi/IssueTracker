package com.elecnor.issue.tracker.daoImpl;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.issue.tracker.bean.IssueTrackerMaster;
import com.elecnor.issue.tracker.dao.IssueSearchDao;
import com.elecnor.issue.tracker.util.IssueTrackerConstants;

@Repository
public class IssueSearchDaoImpl implements IssueSearchDao {

	Logger logger = Logger.getLogger(IssueSearchDaoImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<IssueTrackerMaster> getIssueDetailsBasedOnSearchKeys(
			String queryToRun) throws Exception {

		logger.info("---- Entered getIssueDetailsBasedOnSearchKeys() of IssueSearchDaoImpl ----");

		ArrayList<IssueTrackerMaster> issueList = null;
		try {
			SQLQuery query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							queryToRun
									+ " "
									+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_ORDERBY
									+ " "
									+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_STATUS
									+ " "
									+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_ASC
									+ ","
									+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_SEVERITY
									+ " "
									+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_DESC
									+ ","
									+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_REPORTEDDATE
									+ " "
									+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_DESC);
			query.addEntity(IssueTrackerMaster.class);
			issueList = (ArrayList<IssueTrackerMaster>) query.list();
		} catch (Exception e) {
			issueList = null;
			e.printStackTrace();
			throw e;
		}
		return issueList;
	}

}
