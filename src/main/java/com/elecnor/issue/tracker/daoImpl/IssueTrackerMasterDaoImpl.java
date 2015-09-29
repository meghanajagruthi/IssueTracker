package com.elecnor.issue.tracker.daoImpl;

import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.issue.tracker.bean.IssueTrackerLookup;
import com.elecnor.issue.tracker.bean.IssueTrackerMaster;
import com.elecnor.issue.tracker.dao.IssueTrackerMasterDao;
import com.elecnor.issue.tracker.util.IssueTrackerConstants;

@Repository
public class IssueTrackerMasterDaoImpl implements IssueTrackerMasterDao {

	Logger logger = Logger.getLogger(IssueTrackerMasterDaoImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public IssueTrackerMaster setIssueToSaveOrUpdate(
			IssueTrackerMaster issueTrackerMaster) throws Exception {

		logger.info("---- Entered setIssueToSaveOrUpdate() of IssueTrackerMasterDaoImpl ----");

		IssueTrackerMaster savedIssue = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			savedIssue = (IssueTrackerMaster) session.merge(issueTrackerMaster);
		} catch (Exception e) {
			savedIssue = null;
			e.printStackTrace();
			throw e;
		}
		return savedIssue;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<IssueTrackerMaster> getIssueDetailsById(int issueSno)
			throws Exception {

		logger.info("---- Entered getIssueDetailsById() of IssueTrackerMasterDaoImpl ----");

		ArrayList<IssueTrackerMaster> resultList = null;

		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_FROM
									+ " "
									+ IssueTrackerConstants.ISSUETRACKERMASTER_BEAN_NAME
									+ " "
									+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE
									+ " "
									+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_ISSUESNO
									+ "=:issueNo");
			query.setParameter("issueNo", issueSno);
			resultList = (ArrayList<IssueTrackerMaster>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return resultList;
	}

	@Override
	@Transactional
	public int setIssueStatusToUpdate(int issueSno,
			IssueTrackerLookup statusToUpdate, int updatedById,
			String updatedByName, int estimatedTime, int perDone,Date resolvedDate)
			throws Exception {

		logger.info("---- Entered setIssueStatusToUpdate() of IssueTrackerMasterDaoImpl ----");

		int result = 0;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							IssueTrackerConstants.ISSUETRACKERCONSTANTS_UPDATE
							+ " "
							+ IssueTrackerConstants.ISSUETRACKERMASTER_BEAN_NAME
							+ " "
							+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_SET
							+ " "
							+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_STATUS
							+ "=:status,"
							+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_UPDATED_BY_ID
							+ "=:updatedById,"
							+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_UPDATED_BY_NAME
							+ "=:updatedByName,"
							+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_UPDATED_DATE
							+ "=:updatedDate,"
							+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_RESOLVEDDATE
							+ "=:resolvedDate,"
							+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_ESTIMATED_TIME
							+ "=:estmTime,"
							+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_PERCENTAGE_DONE
							+ "=:perDone "
							+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE
							+ " "
							+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_ISSUESNO
							+ "=:issueNo");
	query.setParameter("status", statusToUpdate);
	query.setParameter("estmTime", estimatedTime);
	query.setParameter("perDone", perDone);
	query.setParameter("updatedById", updatedById);
	query.setParameter("updatedByName", updatedByName);
	query.setParameter("updatedDate", new Date());
	query.setParameter("issueNo", issueSno);
	query.setParameter("resolvedDate", resolvedDate);
			result = query.executeUpdate();
		} catch (Exception e) {
			result = 0;
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	
	
	@Override
	@Transactional
	public Object getIssuesCountByForDashboard(String queryToExecute)
			throws Exception {

		logger.info("---- Entered getIssuesCountByForDashboard() of IssueTrackerMasterDaoImpl ----");

		Object resultCount = 0;
		try {
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(
					queryToExecute);
			resultCount = (Object) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return resultCount;
	}

}
