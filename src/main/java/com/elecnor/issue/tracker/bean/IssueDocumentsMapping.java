package com.elecnor.issue.tracker.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;



@Entity
@Table(name="ISSUE_DOCUMENTS_MAPPING")
public class IssueDocumentsMapping  implements Serializable{

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="MAPPING_ID")
	private Integer mappingId;
	
	@Column(name="RELATED_ISSUE")
	@Expose
	private Integer relatedIssue;
	
	@Column(name="DOCUMENT_ID")
	@Expose
	private Integer documentId;
	
	@Column(name="SUBMITTED_BY")
	@Expose
	private Integer submittedBy;
	
	public Integer getSubmittedBy() {
		return submittedBy;
	}

	public void setSubmittedBy(Integer submittedBy) {
		this.submittedBy = submittedBy;
	}

	public Integer getMappingId() {
		return mappingId;
	}

	public void setMappingId(Integer mappingId) {
		this.mappingId = mappingId;
	}

	public Integer getRelatedIssue() {
		return relatedIssue;
	}

	public void setRelatedIssue(Integer relatedIssue) {
		this.relatedIssue = relatedIssue;
	}

	public Integer getRelatedDocument() {
		return documentId;
	}

	public void setRelatedDocument(Integer relatedDocument) {
		this.documentId = relatedDocument;
	}

	
	
	
	
}
