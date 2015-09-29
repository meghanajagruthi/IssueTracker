package com.elecnor.issue.tracker.daoImpl;

import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.issue.tracker.bean.ApplicationDirectory;
import com.elecnor.issue.tracker.bean.IssueTrackerMaster;
import com.elecnor.issue.tracker.bean.LineChart;
import com.elecnor.issue.tracker.dao.DashboardDao;
import com.elecnor.issue.tracker.util.IssueTrackerConstants;

@Repository
public class DashboardDaoImpl implements DashboardDao {

	Logger logger = Logger.getLogger(DashboardDaoImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<IssueTrackerMaster> getIssuesListForDashboard(
			String appName, int logedinUserid) throws Exception {

		logger.info("---- Entered getIssuesListForDashboard() of DashboardDaoImpl ----");
		ArrayList<IssueTrackerMaster> issuesList = null;
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
									+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_APPLICATIONID
									+ " IN("+appName+") "
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
			issuesList = (ArrayList<IssueTrackerMaster>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return issuesList;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<ApplicationDirectory> getAppsListForDashboard()
			throws Exception {

		logger.info("---- Entered getAppsListForDashboard() of DashboardDaoImpl ----");
		ArrayList<ApplicationDirectory> allApplicationsList = null;

		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_FROM
									+ " "
									+ IssueTrackerConstants.APPLICATION_DIRECTORY_BEAN_NAME);
			allApplicationsList = (ArrayList<ApplicationDirectory>) query
					.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return allApplicationsList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<String> getAllStatus()
	{
		ArrayList<String> resultList = null;

		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery("select lookupName from IssueTrackerLookup   where CATEGORY=:status");
			query.setParameter("status", "Status");
			resultList = (ArrayList<String>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
//			throw e;
		}
		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<IssueTrackerMaster> getStatusListForDashboard(Integer appId)
			throws Exception {

		logger.info("---- Entered getStatusListForDashboard() of DashboardDaoImpl ----");

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
									+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_APPLICATIONID
									+ "=:appId");
			query.setParameter("appId", appId);
			resultList = (ArrayList<IssueTrackerMaster>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<LineChart> getTotalReportedIssues(Integer[] appId,
			Date reportedDate, Date past30Days) throws Exception {
		logger.info("---- Entered getTotalReportedIssues() of DashboardDaoImpl ----");
		ArrayList<LineChart> resultList = null;

		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"select count(ISSUE_SNO) AS sum ,reportedDate from IssueTrackerMaster where APPLICATION_ID in (:appId) and REPORTED_DATE  >=:past30Days and  REPORTED_DATE <=:reportedDate group by DATE(reportedDate)");
			query.setParameterList("appId", appId);
			query.setParameter("reportedDate", reportedDate);
			query.setParameter("past30Days", past30Days);
			resultList = (ArrayList<LineChart>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<LineChart> getTotalResolvedIssues(Integer[] appId,
			Date reportedDate, Date past30Days) throws Exception {

		logger.info("---- Entered getTotalResolvedIssues() of DashboardDaoImpl ----");
		ArrayList<LineChart> resultList = null;

		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"select count(ISSUE_SNO) AS sum ,resolvedDate from IssueTrackerMaster where APPLICATION_ID in (:appId) and RESOLVED_DATE >=:past30Days and RESOLVED_DATE <=:reportedDate and STATUS=:status group by DATE(RESOLVED_DATE)");
			query.setParameterList("appId", appId);
			query.setParameter("status", 11);
			query.setParameter("reportedDate", reportedDate);
			query.setParameter("past30Days", past30Days);
			resultList = (ArrayList<LineChart>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<LineChart> getTotalResolvedIssues(Integer[] appId)
			throws Exception {

		logger.info("---- Entered getTotalResolvedIssues() of DashboardDaoImpl ----");
		ArrayList<LineChart> resultList = null;

		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"select count(ISSUE_SNO) AS sum ,resolvedDate from IssueTrackerMaster where APPLICATION_ID in (:appId) and  STATUS=:status group by DATE(resolvedDate)");
			query.setParameterList("appId", appId);
			query.setParameter("status", 11);
			resultList = (ArrayList<LineChart>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public ArrayList<IssueTrackerMaster> getIssuesListForDashboardByReportedDate(
			String appName, int logedinUserid, Date startDate, Date endDate)
			throws Exception {

		logger.info("---- Entered getIssuesListForDashboardByReportedDate() of DashboardDaoImpl ----");
		ArrayList<IssueTrackerMaster> issueList = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"from IssueTrackerMaster where APPLICATION_ID IN("+appName+") and REPORTED_DATE >=:startDate and REPORTED_DATE <=:endDate  ORDER BY STATUS ASC,SEVERITY DESC,REPORTED_DATE DESC");
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			issueList = (ArrayList<IssueTrackerMaster>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return issueList;

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<LineChart> getTotalReportedIssue(Integer[] appId)
			throws Exception {

		logger.info("---- Entered getTotalReportedIssue() of DashboardDaoImpl ----");
		ArrayList<LineChart> lineChartList = null;

		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"select count(ISSUE_SNO) AS sum ,reportedDate from IssueTrackerMaster where APPLICATION_ID in (:appId)  group by DATE(reportedDate)");
			query.setParameterList("appId", appId);
			lineChartList = (ArrayList<LineChart>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lineChartList;
	}

}
