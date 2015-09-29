package com.elecnor.issue.tracker.daoImpl;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.issue.tracker.bean.ActivityStreamDirectory;
import com.elecnor.issue.tracker.dao.ActivityStreamDirectoryDao;
import com.elecnor.issue.tracker.util.IssueTrackerConstants;

@Repository
public class ActivityStreamDirectoryDaoImpl implements
		ActivityStreamDirectoryDao {

	Logger logger = Logger.getLogger(ActivityStreamDirectoryDaoImpl.class);
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public ActivityStreamDirectory setActivityToSave(
			ActivityStreamDirectory activityBean) throws Exception {
		
		logger.info("---- Entered setActivityToSave() of ActivityStreamDirectoryDaoImpl ----");
		
		ActivityStreamDirectory savedActivityBean = null;
		try {
			Session hybSession = sessionFactory.getCurrentSession();
			savedActivityBean = (ActivityStreamDirectory) hybSession
					.merge(activityBean);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return savedActivityBean;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<ActivityStreamDirectory> getActivityStreamsListForSelctedIssue(
			int selIssueNo) throws Exception {
		
		logger.info("---- Entered getActivityStreamsListForSelctedIssue() of ActivityStreamDirectoryDaoImpl ----");
		
		ArrayList<ActivityStreamDirectory> activitiesList = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_FROM
									+ " "
									+ IssueTrackerConstants.ACTIVITY_STREAM_DIRECTORY_BEAN_NAME
									+ " "
									+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE
									+ " "
									+ IssueTrackerConstants.ACTIVITY_STREAM_DIRECTORY_COLUMN_ISSUESNO
									+ "=:issueSno "
									+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_ORDERBY
									+ " "
									+ IssueTrackerConstants.ACTIVITY_STREAM_DIRECTORY_COLUMN_ACTIVITYID
									+ " "
									+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_DESC);
			query.setParameter("issueSno", selIssueNo);
			activitiesList = (ArrayList<ActivityStreamDirectory>)query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return activitiesList;
	}

}
