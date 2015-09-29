package com.elecnor.issue.tracker.daoImpl;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.issue.tracker.bean.AssigneeProfile;
import com.elecnor.issue.tracker.dao.AssigneeProfileDao;

@Repository
public class AssigneeProfileDaoImpl implements AssigneeProfileDao{

	Logger logger = Logger.getLogger(AssigneeProfileDaoImpl.class);
	
	@Autowired
	SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<AssigneeProfile> getAllAssigneeProfiles() throws Exception {
		
		logger.info("---- Entered getAllAssigneeProfiles() of AssigneeProfileDaoImpl ----");
		
		ArrayList<AssigneeProfile> allAssigneeList = null;
		try{
			Query query = sessionFactory.getCurrentSession().createQuery("from AssigneeProfile");
			allAssigneeList = (ArrayList<AssigneeProfile>)query.list();
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return allAssigneeList;
	}

	@Override
	@Transactional
	public AssigneeProfile getAssigneeProfileById(String assigneeId)
			throws Exception {
		
		logger.info("---- Entered getAssigneeProfileById() of AssigneeProfileDaoImpl ----");
		
		AssigneeProfile assigneeProfileBean = null;
		try{
			Query query = sessionFactory.getCurrentSession().createQuery("from AssigneeProfile where ASSIGNEE_SNO=:assigneeId");
			query.setParameter("assigneeId", assigneeId);
			assigneeProfileBean = (AssigneeProfile)query.list().get(0);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return assigneeProfileBean;
	}

}
