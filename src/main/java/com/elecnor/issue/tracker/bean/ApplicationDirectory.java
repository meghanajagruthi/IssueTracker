package com.elecnor.issue.tracker.bean;

import java.io.Serializable;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

import java.util.Set;

/**
 * The persistent class for the application_directory database table.
 * 
 */
@Entity
@Table(name = "APPLICATION_DIRECTORY")
public class ApplicationDirectory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "APPLICATION_SNO")
	@Expose
	private Integer applicationSno;

	@Expose
	@Column(name = "APPLICATION_NAME")
	private String applicationName;

	// bi-directional many-to-one association to ApplicationModuleDirectory
	@OneToMany(mappedBy = "applicationDirectory")
	private Set<ApplicationModuleDirectory> applicationModuleDirectories;

	// bi-directional many-to-one association to IssueTrackerMaster
	@OneToMany(mappedBy = "applicationDirectory")
	private Set<IssueTrackerMaster> issueTrackerMasters;

	//Transient fields are using to map application details coming from the Ecosystem
	@Expose
	@Transient
	private Long applicationId;

	@Expose
	@Transient
	private String applicationLink;

	
	public ApplicationDirectory() {
	}

	public Integer getApplicationSno() {
		return this.applicationSno;
	}

	public void setApplicationSno(Integer applicationSno) {
		this.applicationSno = applicationSno;
	}

	public String getApplicationName() {
		return this.applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public Set<ApplicationModuleDirectory> getApplicationModuleDirectories() {
		return this.applicationModuleDirectories;
	}

	public void setApplicationModuleDirectories(
			Set<ApplicationModuleDirectory> applicationModuleDirectories) {
		this.applicationModuleDirectories = applicationModuleDirectories;
	}

	public Set<IssueTrackerMaster> getIssueTrackerMasters() {
		return this.issueTrackerMasters;
	}

	public void setIssueTrackerMasters(
			Set<IssueTrackerMaster> issueTrackerMasters) {
		this.issueTrackerMasters = issueTrackerMasters;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getApplicationLink() {
		return applicationLink;
	}

	public void setApplicationLink(String applicationLink) {
		this.applicationLink = applicationLink;
	}
	
	

}