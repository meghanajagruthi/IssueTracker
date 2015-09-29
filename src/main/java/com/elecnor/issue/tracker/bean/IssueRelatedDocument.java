package com.elecnor.issue.tracker.bean;

import java.io.Serializable;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

import java.util.Date;

/**
 * The persistent class for the issue_related_documents database table.
 * 
 */
@Entity
@Table(name = "ISSUE_RELATED_DOCUMENTS")
public class IssueRelatedDocument implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DOCUMENT_SNO")
	@Expose
	private Integer documentSno;

	@Lob
	@Column(name = "DOCUMENT_CONTENT")
	private byte[] documentContent;

	@Column(name = "DOCUMENT_NAME")
	@Expose
	private String documentName;

	@Column(name = "DOCUMENT_TYPE")
	@Expose
	private String documentType;

	@Column(name = "SUBMITTED_BY")
	private Integer submittedBy;
	
	@Column(name = "SUBMITTED_BY_NAME")
	private String submittedByName;
	
	@Column(name = "SUBMITTED_BY_EMAIL")
	private String submittedByEmail;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMITTED_DATE")
	private Date submittedDate;

	// bi-directional many-to-one association to IssueTrackerMaster
	@ManyToOne
	@JoinColumn(name = "ISSUE_SNO")
	private IssueTrackerMaster issueTrackerMaster;

	public IssueRelatedDocument() {
	}

	public Integer getDocumentSno() {
		return this.documentSno;
	}

	public void setDocumentSno(Integer documentSno) {
		this.documentSno = documentSno;
	}

	public byte[] getDocumentContent() {
		return this.documentContent;
	}

	public void setDocumentContent(byte[] documentContent) {
		this.documentContent = documentContent;
	}

	public String getDocumentName() {
		return this.documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentType() {
		return this.documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public Integer getSubmittedBy() {
		return this.submittedBy;
	}

	public void setSubmittedBy(Integer submittedBy) {
		this.submittedBy = submittedBy;
	}

	public Date getSubmittedDate() {
		return this.submittedDate;
	}

	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	public IssueTrackerMaster getIssueTrackerMaster() {
		return this.issueTrackerMaster;
	}

	public void setIssueTrackerMaster(IssueTrackerMaster issueTrackerMaster) {
		this.issueTrackerMaster = issueTrackerMaster;
	}

	public String getSubmittedByName() {
		return submittedByName;
	}

	public void setSubmittedByName(String submittedByName) {
		this.submittedByName = submittedByName;
	}

	public String getSubmittedByEmail() {
		return submittedByEmail;
	}

	public void setSubmittedByEmail(String submittedByEmail) {
		this.submittedByEmail = submittedByEmail;
	}
	
	

}