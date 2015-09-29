package com.elecnor.issue.tracker.bean;

import java.io.Serializable;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

import java.util.Date;

/**
 * The persistent class for the issue_status_tracking database table.
 * 
 */
@Entity
@Table(name = "ISSUE_STATUS_TRACKING")
public class IssueStatusTracking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "STATUS_TRACKING_SNO")
	@Expose
	private Integer statusTrackingSno;

	@Column(name = "STATUS_UPDATE_RELATED_COMMENT")
	@Expose
	private String statusUpdateRelatedComment;
	
	@Column(name = "UPDATED_BY_ID")
	@Expose
	private Integer updatedById;

	@Column(name = "UPDATED_BY_NAME")
	@Expose
	private String updatedByName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	@Expose
	private Date updatedDate;

	// bi-directional many-to-one association to IssueTrackerMaster
	@ManyToOne
	@JoinColumn(name = "ISSUE_SNO")
	@Expose
	private IssueTrackerMaster issueSno;

	// bi-directional many-to-one association to IssueTrackerLookup 
	@ManyToOne
	@JoinColumn(name = "STATUS")
	@Expose
	private IssueTrackerLookup currentStatus;
	
	@ManyToOne
	@JoinColumn(name = "UPDATED_STATUS")
	@Expose
	private IssueTrackerLookup updatedStatus;

	public IssueStatusTracking() {
	}

	public Integer getUpdatedById() {
		return this.updatedById;
	}

	public void setUpdatedById(Integer updatedById) {
		this.updatedById = updatedById;
	}

	public String getUpdatedByName() {
		return this.updatedByName;
	}

	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getStatusTrackingSno() {
		return statusTrackingSno;
	}

	public void setStatusTrackingSno(Integer statusTrackingSno) {
		this.statusTrackingSno = statusTrackingSno;
	}

	public IssueTrackerMaster getIssueSno() {
		return issueSno;
	}

	public void setIssueSno(IssueTrackerMaster issueSno) {
		this.issueSno = issueSno;
	}

	public IssueTrackerLookup getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(IssueTrackerLookup currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getStatusUpdateRelatedComment() {
		return statusUpdateRelatedComment;
	}

	public void setStatusUpdateRelatedComment(String statusUpdateRelatedComment) {
		this.statusUpdateRelatedComment = statusUpdateRelatedComment;
	}

	public IssueTrackerLookup getUpdatedStatus() {
		return updatedStatus;
	}

	public void setUpdatedStatus(IssueTrackerLookup updatedStatus) {
		this.updatedStatus = updatedStatus;
	}

	
	
}