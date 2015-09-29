package com.elecnor.issue.tracker.bean;

import java.io.Serializable;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.Set;

/**
 * The persistent class for the issue_tracker_lookup database table.
 * 
 */
@Entity
@Table(name = "ISSUE_TRACKER_LOOKUP")
public class IssueTrackerLookup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LOOKUP_SNO")
	@Expose
	private Integer lookupSno;

	@Column(name = "CATEGORY")
	@Expose
	private String category;

	@Column(name = "LOOKUP_NAME")
	@Expose
	private String lookupName;

	@Column(name = "SUBMITTED_BY")
	@Expose
	private Integer submittedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMITTED_DATE")
	@Expose
	private Date submittedDate;

	@Column(name = "UPDATED_BY")
	@Expose
	private Integer updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	@Expose
	private Date updatedDate;

	// bi-directional many-to-one association to IssueStatusTracking updatedStatus
	@OneToMany(mappedBy = "currentStatus")
	private Set<IssueStatusTracking> issueStatusTrackings;
	
	// bi-directional many-to-one association to IssueStatusTracking 
		@OneToMany(mappedBy = "updatedStatus")
		private Set<IssueStatusTracking> issueStatusTrackings1;

	// bi-directional many-to-one association to IssueTrackerMaster
	@OneToMany(mappedBy = "severity")
	private Set<IssueTrackerMaster> issueTrackerMasters1;

	// bi-directional many-to-one association to IssueTrackerMaster
	@OneToMany(mappedBy = "status")
	private Set<IssueTrackerMaster> issueTrackerMasters2;

	// bi-directional many-to-one association to IssueTrackerMaster
	@OneToMany(mappedBy = "issueType")
	private Set<IssueTrackerMaster> issueTrackerMasters3;

	public IssueTrackerLookup() {
	}

	public Integer getLookupSno() {
		return this.lookupSno;
	}

	public void setLookupSno(Integer lookupSno) {
		this.lookupSno = lookupSno;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLookupName() {
		return this.lookupName;
	}

	public void setLookupName(String lookupName) {
		this.lookupName = lookupName;
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

	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Set<IssueStatusTracking> getIssueStatusTrackings() {
		return this.issueStatusTrackings;
	}

	public void setIssueStatusTrackings(
			Set<IssueStatusTracking> issueStatusTrackings) {
		this.issueStatusTrackings = issueStatusTrackings;
	}

	public Set<IssueTrackerMaster> getIssueTrackerMasters1() {
		return this.issueTrackerMasters1;
	}

	public void setIssueTrackerMasters1(
			Set<IssueTrackerMaster> issueTrackerMasters1) {
		this.issueTrackerMasters1 = issueTrackerMasters1;
	}

	public Set<IssueTrackerMaster> getIssueTrackerMasters2() {
		return this.issueTrackerMasters2;
	}

	public void setIssueTrackerMasters2(
			Set<IssueTrackerMaster> issueTrackerMasters2) {
		this.issueTrackerMasters2 = issueTrackerMasters2;
	}

	public Set<IssueTrackerMaster> getIssueTrackerMasters3() {
		return this.issueTrackerMasters3;
	}

	public void setIssueTrackerMasters3(
			Set<IssueTrackerMaster> issueTrackerMasters3) {
		this.issueTrackerMasters3 = issueTrackerMasters3;
	}

	public Set<IssueStatusTracking> getIssueStatusTrackings1() {
		return issueStatusTrackings1;
	}

	public void setIssueStatusTrackings1(
			Set<IssueStatusTracking> issueStatusTrackings1) {
		this.issueStatusTrackings1 = issueStatusTrackings1;
	}
	
	

}