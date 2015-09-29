package com.elecnor.issue.tracker.bean;

import java.io.Serializable;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

import java.util.Date;

/**
 * The persistent class for the issue_comments_tracking database table.
 * 
 */
@Entity
@Table(name = "ISSUE_COMMENTS_TRACKING")
public class IssueCommentsTracking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COMMENTS_TRACKING_SNO")
	@Expose
	private int commentsTrackingSno;

	@Column(name = "COMMENT")
	@Expose
	private String comment;

	@Column(name = "SUBMITTED_BY_ID")
	@Expose
	private int submittedById;

	@Column(name = "SUBMITTED_BY_NAME")
	@Expose
	private String submittedByName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMITTED_DATE")
	@Expose
	private Date submittedDate;

	// bi-directional many-to-one association to IssueTrackerMaster
	@ManyToOne
	@JoinColumn(name = "ISSUE_SNO")
	private IssueTrackerMaster issueTrackerMaster;

	public IssueCommentsTracking() {
	}

	public int getCommentsTrackingSno() {
		return this.commentsTrackingSno;
	}

	public void setCommentsTrackingSno(int commentsTrackingSno) {
		this.commentsTrackingSno = commentsTrackingSno;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getSubmittedById() {
		return this.submittedById;
	}

	public void setSubmittedById(int submittedById) {
		this.submittedById = submittedById;
	}

	public String getSubmittedByName() {
		return this.submittedByName;
	}

	public void setSubmittedByName(String submittedByName) {
		this.submittedByName = submittedByName;
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

}