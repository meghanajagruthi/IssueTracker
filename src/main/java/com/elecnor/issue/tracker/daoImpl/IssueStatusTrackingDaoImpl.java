package com.elecnor.issue.tracker.daoImpl;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.issue.tracker.bean.IssueStatusTracking;
import com.elecnor.issue.tracker.dao.IssueStatusTrackingDao;
import com.elecnor.issue.tracker.util.IssueTrackerConstants;

@Repository
public class IssueStatusTrackingDaoImpl implements IssueStatusTrackingDao {

	Logger logger = Logger.getLogger(IssueStatusTrackingDaoImpl.class);
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public void setIssueStatusTrackingToSave(
			IssueStatusTracking issueStatusTracking) throws Exception {

		logger.info("---- Entered setIssueStatusTrackingToSave() of IssueStatusTrackingDaoImpl ----");
		
		try {
			Session session = sessionFactory.getCurrentSession();
			session.merge(issueStatusTracking);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<IssueStatusTracking> getIssueStatusLogsByIssueId(
			int issueSno) throws Exception {
		
		logger.info("---- Entered getIssueStatusLogsByIssueId() of IssueStatusTrackingDaoImpl ----");
		
		ArrayList<IssueStatusTracking> statusLogsList = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_FROM
									+ " "
									+ IssueTrackerConstants.SATUS_TRACKING_BEAN_NAME
									+ " "
									+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE
									+ " "
									+ IssueTrackerConstants.SATUS_TRACKING_COLUMN_ISSUESNO
									+ "=:issueNo "
									+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_ORDERBY
									+ " "
									+ IssueTrackerConstants.SATUS_TRACKING_COLUMN_STATUSTRACKINGSNO
									+ " "
									+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_DESC);
			query.setParameter("issueNo", issueSno);
			statusLogsList = (ArrayList<IssueStatusTracking>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return statusLogsList;
	}

}
