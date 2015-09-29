package com.elecnor.issue.tracker.daoImpl;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.issue.tracker.bean.ApplicationDirectory;
import com.elecnor.issue.tracker.bean.ApplicationModuleDirectory;
import com.elecnor.issue.tracker.bean.IssueTrackerMaster;
import com.elecnor.issue.tracker.dao.CommonDao;
import com.elecnor.issue.tracker.util.IssueTrackerConstants;

@Repository
public class CommonDaoImpl implements CommonDao {

	Logger logger = Logger.getLogger(CommonDaoImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<ApplicationDirectory> getApplicationsList()
			throws Exception {

		logger.info("---- Entered getApplicationsList() of CommonDaoImpl ----");

		ArrayList<ApplicationDirectory> allApplications = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_FROM
									+ " "
									+ IssueTrackerConstants.APPLICATION_DIRECTORY_BEAN_NAME);
			allApplications = (ArrayList<ApplicationDirectory>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return allApplications;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<ApplicationModuleDirectory> getModulesListByApplicationId(
			int applicationId) throws Exception {

		logger.info("---- Entered getModulesListByApplicationId() of CommonDaoImpl ----");
		ArrayList<ApplicationModuleDirectory> moduleList = null;
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
									+ IssueTrackerConstants.MODULEDIRECTORY_COLUMN_APPLICATIONSNO
									+ "=:appId");
			query.setParameter("appId", applicationId);
			moduleList = (ArrayList<ApplicationModuleDirectory>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return moduleList;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<ApplicationModuleDirectory> getModulesListByMultipleApplicationIds(
			String queryToRun) throws Exception {

		logger.info("---- Entered getModulesListByMultipleApplicationIds() of CommonDaoImpl ----");

		ArrayList<ApplicationModuleDirectory> moduleList = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(
					queryToRun);
			moduleList = (ArrayList<ApplicationModuleDirectory>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return moduleList;
	}
	
	@Override
	@Transactional
	public ArrayList<IssueTrackerMaster> getAllIssuesReportedByUser(String userId){
		
		logger.info("---- Entered getAllIssuesReportedByUser() of CommonDaoImpl ----");
		ArrayList<IssueTrackerMaster> allIssues=null;
		try{
			
			Query query=sessionFactory.getCurrentSession().createQuery("from IssueTrackerMaster where REPORTED_BY_ID=:reportedById order by REPORTED_DATE");
			query.setParameter("reportedById", userId);
			allIssues=(ArrayList<IssueTrackerMaster>)query.list();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return allIssues;
	}

	@Override
	@Transactional
	public ApplicationModuleDirectory getModulesListByModuleName(
			String moduleName) throws Exception {

		logger.info("---- Entered getModulesListByMultipleApplicationIds() of CommonDaoImpl ----");

		ApplicationModuleDirectory moduleBean = null;
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
									+ IssueTrackerConstants.MODULEDIRECTORY_COLUMN_MODULE_NAME
									+ "=:moduleName");
			query.setParameter("moduleName", moduleName);
			moduleBean = (ApplicationModuleDirectory) query.list().get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return moduleBean;
	}

}
