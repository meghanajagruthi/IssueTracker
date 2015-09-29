package com.elecnor.issue.tracker.dao;

import java.util.ArrayList;

import com.elecnor.issue.tracker.bean.IssueDocumentsMapping;
import com.elecnor.issue.tracker.bean.IssueRelatedDocument;

public interface IssueRelatedDocumentDao {

	public void setIssueRealtedDocumentToSave(IssueRelatedDocument IssueRelatedDocument)throws Exception;
	public ArrayList<IssueRelatedDocument> getReltedDocumentsByIssueSno(String issueSno)throws Exception;
	public IssueRelatedDocument getReltedDocumentByDocmentId(String documentId)throws Exception;
	public String setDocumentToDelete(int descId)throws Exception;
	public void saveDocumentMapping(IssueDocumentsMapping issueDocumentMapping)
			throws Exception;
	public void deleteDocumentMapping(int fileId);
	public ArrayList<Integer> getRelatedDocumentsByIssueNumber(Integer issueId);
}
