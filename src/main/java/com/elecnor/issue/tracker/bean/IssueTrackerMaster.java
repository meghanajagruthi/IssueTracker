package com.elecnor.issue.tracker.bean;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.Set;

/**
 * The persistent class for the issue_tracker_master database table.
 * 
 */
@Entity
@Table(name = "ISSUE_TRACKER_MASTER")
public class IssueTrackerMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ISSUE_SNO")
	@Expose
	private Integer issueSno;

	@Expose
	@Column(name = "SUMMARY")
	private String summary;

	@Expose
	@Column(name = "DESCRIPTION")
	private String description;

	@Expose
	@Column(name = "DOMAIN_ID")
	private Long domainId;

	@Expose
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DUE_DATE")
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private Date dueDate;

	@Expose
	@Column(name = "ESTIMATED_TIME")
	private Integer estimatedTime;

	@Expose
	@Column(name = "PERCENTAGE_DONE")
	private Double percentageDoneId;

	@Expose
	@Column(name = "RELATED_PROJECT")
	private String relatedProject;

	@Expose
	@Column(name = "REPORTED_BY_ID")
	private Integer reportedById;

	@Expose
	@Column(name = "REPORTED_BY_NAME")
	private String reportedByName;
	
	@Expose
	@Column(name = "REPORTED_BY_EMAILID")
	private String reportedByEmailId;

	@Expose
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REPORTED_DATE")
	private Date reportedDate;

	@Expose
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "START_DATE")
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private Date startDate;

	@Expose
	@Column(name = "UPDATED_BY_ID")
	private Integer updatedById;

	@Expose
	@Column(name = "UPDATED_BY_NAME")
	private String updatedByName;

	@Expose
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;
	
	@Expose
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RESOLVED_DATE")
	private Date resolvedDate;

	// bi-directional many-to-one association to IssueCommentsTracking
	@OneToMany(mappedBy = "issueTrackerMaster")
	private Set<IssueCommentsTracking> issueCommentsTrackings;

	// bi-directional many-to-one association to IssueRelatedDocument
	@OneToMany(mappedBy = "issueTrackerMaster")
	private Set<IssueRelatedDocument> issueRelatedDocuments;

	// bi-directional many-to-one association to IssueStatusTracking
	@OneToMany(mappedBy = "issueSno")
	private Set<IssueStatusTracking> issueStatusTrackings;

	// bi-directional many-to-one association to ApplicationModuleDirectory
	@Expose
	@ManyToOne
	@JoinColumn(name = "MODULE_ID")
	private ApplicationModuleDirectory applicationModuleDirectory;

	// bi-directional many-to-one association to ApplicationDirectory
	@Expose
	@ManyToOne
	@JoinColumn(name = "APPLICATION_ID")
	private ApplicationDirectory applicationDirectory;

	// bi-directional many-to-one association to IssueTrackerLookup

	@Expose
	@ManyToOne
	@JoinColumn(name = "SEVERITY")
	private IssueTrackerLookup severity;

	// bi-directional many-to-one association to IssueTrackerLookup
	@Expose
	@ManyToOne
	@JoinColumn(name = "STATUS")
	private IssueTrackerLookup status;

	// bi-directional many-to-one association to AssigneeProfile
	@Expose
	@ManyToOne
	@JoinColumn(name = "ASSIGNEE")
	private AssigneeProfile assigneeProfile;

	// bi-directional many-to-one association to IssueTrackerLookup
	@Expose
	@ManyToOne
	@JoinColumn(name = "ISSUE_TYPE")
	private IssueTrackerLookup issueType;
	
	// bi-directional many-to-one association to IssueCommentsTracking
	@OneToMany(mappedBy = "relatedIssueForActivity")
	private Set<ActivityStreamDirectory> activityStreamDirectiry;

	public IssueTrackerMaster() {
	}

	public Integer getIssueSno() {
		return this.issueSno;
	}

	public void setIssueSno(Integer issueSno) {
		this.issueSno = issueSno;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getDomainId() {
		return this.domainId;
	}

	public void setDomainId(Long domainId) {
		this.domainId = domainId;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Integer getEstimatedTime() {
		return this.estimatedTime;
	}

	public void setEstimatedTime(Integer estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	public Double getPercentageDoneId() {
		return percentageDoneId;
	}

	public void setPercentageDoneId(Double percentageDoneId) {
		this.percentageDoneId = percentageDoneId;
	}

	public String getRelatedProject() {
		return this.relatedProject;
	}

	public void setRelatedProject(String relatedProject) {
		this.relatedProject = relatedProject;
	}

	public Integer getReportedById() {
		return this.reportedById;
	}

	public void setReportedById(Integer reportedById) {
		this.reportedById = reportedById;
	}

	public String getReportedByName() {
		return this.reportedByName;
	}

	public void setReportedByName(String reportedByName) {
		this.reportedByName = reportedByName;
	}

	public Date getReportedDate() {
		return this.reportedDate;
	}

	public void setReportedDate(Date reportedDate) {
		this.reportedDate = reportedDate;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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

	public Set<IssueCommentsTracking> getIssueCommentsTrackings() {
		return this.issueCommentsTrackings;
	}

	public void setIssueCommentsTrackings(
			Set<IssueCommentsTracking> issueCommentsTrackings) {
		this.issueCommentsTrackings = issueCommentsTrackings;
	}

	public Set<IssueRelatedDocument> getIssueRelatedDocuments() {
		return this.issueRelatedDocuments;
	}

	public void setIssueRelatedDocuments(
			Set<IssueRelatedDocument> issueRelatedDocuments) {
		this.issueRelatedDocuments = issueRelatedDocuments;
	}

	public Set<IssueStatusTracking> getIssueStatusTrackings() {
		return this.issueStatusTrackings;
	}

	public void setIssueStatusTrackings(
			Set<IssueStatusTracking> issueStatusTrackings) {
		this.issueStatusTrackings = issueStatusTrackings;
	}

	public ApplicationModuleDirectory getApplicationModuleDirectory() {
		return this.applicationModuleDirectory;
	}

	public void setApplicationModuleDirectory(
			ApplicationModuleDirectory applicationModuleDirectory) {
		this.applicationModuleDirectory = applicationModuleDirectory;
	}

	public ApplicationDirectory getApplicationDirectory() {
		return this.applicationDirectory;
	}

	public void setApplicationDirectory(
			ApplicationDirectory applicationDirectory) {
		this.applicationDirectory = applicationDirectory;
	}

	public AssigneeProfile getAssigneeProfile() {
		return this.assigneeProfile;
	}

	public void setAssigneeProfile(AssigneeProfile assigneeProfile) {
		this.assigneeProfile = assigneeProfile;
	}

	public IssueTrackerLookup getSeverity() {
		return severity;
	}

	public void setSeverity(IssueTrackerLookup severity) {
		this.severity = severity;
	}

	public IssueTrackerLookup getStatus() {
		return status;
	}

	public void setStatus(IssueTrackerLookup status) {
		this.status = status;
	}

	public IssueTrackerLookup getIssueType() {
		return issueType;
	}

	public void setIssueType(IssueTrackerLookup issueType) {
		this.issueType = issueType;
	}

	public String getReportedByEmailId() {
		return reportedByEmailId;
	}

	public void setReportedByEmailId(String reportedByEmailId) {
		this.reportedByEmailId = reportedByEmailId;
	}

	public Set<ActivityStreamDirectory> getActivityStreamDirectiry() {
		return activityStreamDirectiry;
	}

	public void setActivityStreamDirectiry(
			Set<ActivityStreamDirectory> activityStreamDirectiry) {
		this.activityStreamDirectiry = activityStreamDirectiry;
	}

	public Date getResolvedDate() {
		return resolvedDate;
	}

	public void setResolvedDate(Date resolvedDate) {
		this.resolvedDate = resolvedDate;
	}
	

}