package com.elecnor.issue.tracker.bean;

import java.io.Serializable;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.Set;

/**
 * The persistent class for the assignee_profile database table.
 * 
 */
@Entity
@Table(name = "ASSIGNEE_PROFILE")
public class AssigneeProfile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ASSIGNEE_SNO")
	@Expose
	private Integer assigneeSno;

	@Expose
	@Column(name = "ASSIGNEE_EMAILID")
	private String assigneeEmailid;

	@Expose
	@Column(name = "ASSIGNEE_NAME")
	private String assigneeName;

	@Column(name = "SUBMITTED_BY")
	private Integer submittedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMITTED_DATE")
	private Date submittedDate;

	@Column(name = "UPDATED_BY")
	private Integer updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	// bi-directional many-to-one association to IssueTrackerMaster
	@OneToMany(mappedBy = "assigneeProfile")
	private Set<IssueTrackerMaster> issueTrackerMasters;

	public AssigneeProfile() {
	}

	public Integer getAssigneeSno() {
		return this.assigneeSno;
	}

	public void setAssigneeSno(Integer assigneeSno) {
		this.assigneeSno = assigneeSno;
	}

	public String getAssigneeEmailid() {
		return this.assigneeEmailid;
	}

	public void setAssigneeEmailid(String assigneeEmailid) {
		this.assigneeEmailid = assigneeEmailid;
	}

	public String getAssigneeName() {
		return this.assigneeName;
	}

	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
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

	public Set<IssueTrackerMaster> getIssueTrackerMasters() {
		return this.issueTrackerMasters;
	}

	public void setIssueTrackerMasters(
			Set<IssueTrackerMaster> issueTrackerMasters) {
		this.issueTrackerMasters = issueTrackerMasters;
	}

}