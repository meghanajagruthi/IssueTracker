package com.elecnor.issue.tracker.bean;

import java.io.Serializable;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

import java.util.Set;


/**
 * The persistent class for the application_module_directory database table.
 * 
 */
@Entity
@Table(name="APPLICATION_MODULE_DIRECTORY")
public class ApplicationModuleDirectory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="MODULE_SNO")
	@Expose
	private Integer moduleSno;

	@Expose
	@Column(name="MODULE_NAME")
	private String moduleName;

	//bi-directional many-to-one association to ApplicationDirectory
	@ManyToOne
	@JoinColumn(name="APPLICATION_SNO")
	private ApplicationDirectory applicationDirectory;

	//bi-directional many-to-one association to IssueTrackerMaster
	@OneToMany(mappedBy="applicationModuleDirectory")
	private Set<IssueTrackerMaster> issueTrackerMasters;

	public ApplicationModuleDirectory() {
	}

	public Integer getModuleSno() {
		return this.moduleSno;
	}

	public void setModuleSno(Integer moduleSno) {
		this.moduleSno = moduleSno;
	}

	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public ApplicationDirectory getApplicationDirectory() {
		return this.applicationDirectory;
	}

	public void setApplicationDirectory(ApplicationDirectory applicationDirectory) {
		this.applicationDirectory = applicationDirectory;
	}

	public Set<IssueTrackerMaster> getIssueTrackerMasters() {
		return this.issueTrackerMasters;
	}

	public void setIssueTrackerMasters(Set<IssueTrackerMaster> issueTrackerMasters) {
		this.issueTrackerMasters = issueTrackerMasters;
	}

}