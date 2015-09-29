package com.elecnor.issue.tracker.bean;

import java.util.Date;


public class IssueSummary {
	
	private Integer issueSno;
	private String summary;
	private String description;
	private Date dueDate;
	private Integer reportedById;
	private String projectName;
	private Date reportedDate;
	private Date startDate;
	private Date updatedDate;
	private String moduleName;
	private String applicationName;
	private String severity;
	private String status;
	private String issueType;
	private Double percentageDone;

	
	private String assigneeName;
	
	public Integer getIssueSno() {
		return issueSno;
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
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Integer getReportedById() {
		return reportedById;
	}

	public void setReportedById(Integer reportedById) {
		this.reportedById = reportedById;
	}

/*	public String getReportedByName() {
		return reportedByName;
	}

	public void setReportedByName(String reportedByName) {
		this.reportedByName = reportedByName;
	}

	public String getReportedByEmailId() {
		return reportedByEmailId;
	}*/

	/*public void setReportedByEmailId(String reportedByEmailId) {
		this.reportedByEmailId = reportedByEmailId;
	}*/

	public Date getReportedDate() {
		return reportedDate;
	}

	public void setReportedDate(Date reportedDate) {
		this.reportedDate = reportedDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	public String getAssigneeName() {
		return assigneeName;
	}

	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
	}

	public Double getPercentageDone() {
		return percentageDone;
	}

	public void setPercentageDone(Double percentageDone) {
		this.percentageDone = percentageDone;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	

}
