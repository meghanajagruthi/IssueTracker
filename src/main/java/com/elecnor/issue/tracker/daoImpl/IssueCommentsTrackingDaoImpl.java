package com.elecnor.issue.tracker.daoImpl;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.issue.tracker.bean.IssueCommentsTracking;
import com.elecnor.issue.tracker.dao.IssueCommentsTrackingDao;
import com.elecnor.issue.tracker.util.IssueTrackerConstants;

@Repository
public class IssueCommentsTrackingDaoImpl implements IssueCommentsTrackingDao {

	Logger logger = Logger.getLogger(IssueCommentsTrackingDaoImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<IssueCommentsTracking> getCommentsByIssueSno(
			String issueSno) throws Exception {

		logger.info("---- Entered getCommentsByIssueSno() of IssueCommentsTrackingDaoImpl ----");

		ArrayList<IssueCommentsTracking> commentsList = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_FROM
									+ " "
									+ IssueTrackerConstants.COMMENTS_TRACKING_BEAN_NAME
									+ " "
									+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE
									+ " "
									+ IssueTrackerConstants.COMMENTS_TRACKING_COLUMN_ISSUESNO
									+ "=:issueSno "
									+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_ORDERBY
									+ " "
									+ IssueTrackerConstants.COMMENTS_TRACKING_COLUMN_COMMENTS_TRACKING_SNO
									+ " "
									+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_DESC);
			query.setParameter("issueSno", issueSno);
			commentsList = (ArrayList<IssueCommentsTracking>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return commentsList;
	}

	@Override
	@Transactional
	public void setCommentToAddForSelectedIssue(
			IssueCommentsTracking issueCommentsTrackingBean) throws Exception {

		logger.info("---- Entered getCommentsByIssueSno() of IssueCommentsTrackingDaoImpl ----");

		try {
			Session hybSession = sessionFactory.getCurrentSession();
			hybSession.merge(issueCommentsTrackingBean);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
