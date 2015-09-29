package com.elecnor.issue.tracker.daoImpl;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.issue.tracker.bean.IssueTrackerLookup;
import com.elecnor.issue.tracker.dao.IssueTrackerLookupDao;
import com.elecnor.issue.tracker.util.IssueTrackerConstants;

@Repository
public class IssueTrackerLookupDaoImpl implements IssueTrackerLookupDao {

	Logger logger = Logger.getLogger(IssueTrackerLookupDaoImpl.class);
	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<IssueTrackerLookup> getLookupTableDetailsList()
			throws Exception {

		logger.info("---- Entered getLookupTableDetailsList() of IssueTrackerLookupDaoImpl ----");

		Query query = sessionFactory.getCurrentSession().createQuery(
				"from IssueTrackerLookup");
		return (ArrayList<IssueTrackerLookup>) query.list();
	}

	@Override
	@Transactional
	public IssueTrackerLookup getLookupTableDetailsById(String sNo)
			throws Exception {

		logger.info("---- Entered getLookupTableDetailsById() of IssueTrackerLookupDaoImpl ----");

		IssueTrackerLookup resultDetails = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_FROM
									+ " "
									+ IssueTrackerConstants.LOOKUP_BEAN_NAME
									+ " "
									+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE
									+ " "
									+ IssueTrackerConstants.LOOKUP_COLUMN_LOOKUP_SNO
									+ "=:lookupSno");
			query.setParameter("lookupSno", sNo);
			resultDetails = (IssueTrackerLookup) query.list().get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return resultDetails;
	}

	@Override
	@Transactional
	public IssueTrackerLookup getLookupTableDetailsByLookupName(
			String lookupName) throws Exception {

		logger.info("---- Entered getLookupTableDetailsByLookupName() of IssueTrackerLookupDaoImpl ----");

		IssueTrackerLookup resultDetails = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_FROM
									+ " "
									+ IssueTrackerConstants.LOOKUP_BEAN_NAME
									+ " "
									+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE
									+ " "
									+ IssueTrackerConstants.LOOKUP_COLUMN_LOOKUP_NAME
									+ " LIKE :lookupName");
			query.setParameter("lookupName", lookupName);
			resultDetails = (IssueTrackerLookup) query.list().get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return resultDetails;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<IssueTrackerLookup> getLookupIssuetypeAndPriorityList()
			throws Exception {

		logger.info("---- Entered getLookupIssuetypeAndPriorityList() of IssueTrackerLookupDaoImpl ----");

		ArrayList<IssueTrackerLookup> resultDetails = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_FROM
									+ " "
									+ IssueTrackerConstants.LOOKUP_BEAN_NAME
									+ " "
									+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE
									+ " "
									+ IssueTrackerConstants.LOOKUP_COLUMN_CATEGORY
									+ "='"
									+ IssueTrackerConstants.LOOKUP_VALUE_ISSUETYPE
									+ "' OR "
									+ IssueTrackerConstants.LOOKUP_COLUMN_CATEGORY
									+ "='"
									+ IssueTrackerConstants.LOOKUP_VALUE_SEVERITY
									+ "'");
			resultDetails = (ArrayList<IssueTrackerLookup>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return resultDetails;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<IssueTrackerLookup> getLookupListByCategory(
			String categoryName) throws Exception {

		logger.info("---- Entered getLookupListByCategory() of IssueTrackerLookupDaoImpl ----");

		ArrayList<IssueTrackerLookup> resultDetails = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_FROM
									+ " "
									+ IssueTrackerConstants.LOOKUP_BEAN_NAME
									+ " "
									+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE
									+ " "
									+ IssueTrackerConstants.LOOKUP_COLUMN_CATEGORY
									+ "=:categoryName");
			query.setParameter("categoryName", categoryName);

			resultDetails = (ArrayList<IssueTrackerLookup>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return resultDetails;
	}

}
