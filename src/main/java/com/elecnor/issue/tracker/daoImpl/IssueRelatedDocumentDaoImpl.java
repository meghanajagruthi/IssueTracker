package com.elecnor.issue.tracker.daoImpl;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elecnor.issue.tracker.bean.IssueDocumentsMapping;
import com.elecnor.issue.tracker.bean.IssueRelatedDocument;
import com.elecnor.issue.tracker.dao.IssueRelatedDocumentDao;
import com.elecnor.issue.tracker.util.IssueTrackerConstants;

@Repository
public class IssueRelatedDocumentDaoImpl implements IssueRelatedDocumentDao {

	Logger logger = Logger.getLogger(IssueRelatedDocumentDaoImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	@Override
	public void deleteDocumentMapping(int fileId){
		Query query=sessionFactory.getCurrentSession().createQuery("delete from IssueDocumentsMapping  where documentId=:fileId");
		query.setParameter("fileId", fileId);
		query.executeUpdate();
	}
	@Override
	@Transactional
	public void saveDocumentMapping(IssueDocumentsMapping issueDocumentMapping) throws Exception{
		logger.info("---- Entered setIssueDocumentMapping Record ToSave() of IssueRelatedDocumentDaoImpl ----");

		try {
			logger.info("The related document id is       "+issueDocumentMapping.getRelatedDocument());
			Session session = sessionFactory.getCurrentSession();
			session.merge(issueDocumentMapping);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	@Override
	@Transactional
	public void setIssueRealtedDocumentToSave(
			IssueRelatedDocument IssueRelatedDocument) throws Exception {

		logger.info("---- Entered setIssueRealtedDocumentToSave() of IssueRelatedDocumentDaoImpl ----");

		try {
			Session session = sessionFactory.getCurrentSession();
			session.merge(IssueRelatedDocument);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	

@SuppressWarnings("unchecked")
@Override
@Transactional
public ArrayList<Integer> getRelatedDocumentsByIssueNumber(Integer issueId){
	Query query=sessionFactory.getCurrentSession().createQuery("select documentId from IssueDocumentsMapping where relatedIssue=:issueId");
	query.setParameter("issueId", issueId);
	return (ArrayList<Integer>)query.list();
}
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<IssueRelatedDocument> getReltedDocumentsByIssueSno(
			String issueSno) throws Exception {

		logger.info("---- Entered getReltedDocumentsByIssueSno() of IssueRelatedDocumentDaoImpl ----");

		ArrayList<IssueRelatedDocument> documentsList = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_FROM
									+ " "
									+ IssueTrackerConstants.ISSUE_RELATED_DOCUMENTS_BEAN_NAME
									+ " "
									+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE
									+ " "
									+ IssueTrackerConstants.ISSUE_RELATED_DOCUMENTS_COLUMN_ISSUESNO
									+ "=:issueId");
			query.setParameter("issueId", issueSno);
			documentsList = (ArrayList<IssueRelatedDocument>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return documentsList;
	}

	@Override
	@Transactional
	public IssueRelatedDocument getReltedDocumentByDocmentId(String documentId)
			throws Exception {

		logger.info("---- Entered getReltedDocumentByDocmentId() of IssueRelatedDocumentDaoImpl ----");

		IssueRelatedDocument documentDetails = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_FROM
									+ " "
									+ IssueTrackerConstants.ISSUE_RELATED_DOCUMENTS_BEAN_NAME
									+ " "
									+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE
									+ " "
									+ IssueTrackerConstants.ISSUE_RELATED_DOCUMENTS_COLUMN_DOCUMENT_SNO
									+ "=:documentId");
			query.setParameter("documentId", documentId);
			documentDetails = (IssueRelatedDocument) query.list().get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return documentDetails;
	}

	@Override
	@Transactional
	public String setDocumentToDelete(int descId) throws Exception {

		logger.info("---- Entered setDocumentToDelete() of IssueRelatedDocumentDaoImpl ----");

		String result = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_DELETE
									+ " "
									+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_FROM
									+ " "
									+ IssueTrackerConstants.ISSUE_RELATED_DOCUMENTS_BEAN_NAME
									+ " "
									+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE
									+ " "
									+ IssueTrackerConstants.ISSUE_RELATED_DOCUMENTS_COLUMN_DOCUMENT_SNO
									+ "=:documentId");
			query.setParameter("documentId", descId);
			query.executeUpdate();

		} catch (HibernateException e) {
			result = e.getMessage();
			e.printStackTrace();
			throw e;
		}

		return result;
	}

}
