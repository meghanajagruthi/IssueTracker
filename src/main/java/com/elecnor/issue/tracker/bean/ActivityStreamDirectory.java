package com.elecnor.issue.tracker.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "ACTIVITY_STREAM_DIRECTORY")
public class ActivityStreamDirectory implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ACTIVITY_ID")
	@Expose
	private Integer activityId;
	
	@Column(name = "ACTIVITY_TYPE")
	@Expose
	private String activityType;
	
	@Column(name = "ACTIVITY_SUMMARY")
	@Expose
	private String activitySummary;
	
	@Column(name = "OLD_VALUE")
	@Expose
	private String oldValue;
	
	@Column(name = "NEW_VALUE")
	@Expose
	private String newValue;
	
	@Column(name = "ACTIVITY_GENERATED_BY_NAME")
	@Expose
	private String activityGeneratedByName;
	
	@Column(name = "ACTIVITY_GENERATED_BY_ID")
	@Expose
	private Integer activityGeneratedById;
	
	@Expose
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACTIVITY_GENERATED_DATE")
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private Date activityGeneratedDate;
	
	@Column(name = "DOMAIN_ID")
	@Expose
	private Long domainId;
	
	// bi-directional many-to-one association to IssueTrackerMaster
		@ManyToOne
		@JoinColumn(name = "ISSUE_SNO")
		@Expose
		private IssueTrackerMaster relatedIssueForActivity;

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getActivityGeneratedByName() {
		return activityGeneratedByName;
	}

	public void setActivityGeneratedByName(String activityGeneratedByName) {
		this.activityGeneratedByName = activityGeneratedByName;
	}

	public Integer getActivityGeneratedById() {
		return activityGeneratedById;
	}

	public void setActivityGeneratedById(Integer activityGeneratedById) {
		this.activityGeneratedById = activityGeneratedById;
	}

	public Date getActivityGeneratedDate() {
		return activityGeneratedDate;
	}

	public void setActivityGeneratedDate(Date activityGeneratedDate) {
		this.activityGeneratedDate = activityGeneratedDate;
	}

	public Long getDomainId() {
		return domainId;
	}

	public void setDomainId(Long domainId) {
		this.domainId = domainId;
	}

	public IssueTrackerMaster getRelatedIssueForActivity() {
		return relatedIssueForActivity;
	}

	public void setRelatedIssueForActivity(
			IssueTrackerMaster relatedIssueForActivity) {
		this.relatedIssueForActivity = relatedIssueForActivity;
	}

	public String getActivitySummary() {
		return activitySummary;
	}

	public void setActivitySummary(String activitySummary) {
		this.activitySummary = activitySummary;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

    
	
}
