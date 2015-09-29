package com.elecnor.issue.tracker.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.annotations.Expose;

public class DomainDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Expose
	private Long domainId;

	@Expose
	private String companyAddress;

	@Expose
	private String companyName;

	@Expose
	private String companyPhoneCarrier;

	@Expose
	private String companyPhoneNumber;

	@Expose
	@Temporal(TemporalType.TIMESTAMP)
	private Date domainCreatedDate;

	@Expose
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	@Expose
	private String domainName;

	@Expose
	private String status;

	// bi-directional many-to-one association to UserDetail
	@Expose
	private UserDetails updatedBy;

	public Long getDomainId() {
		return domainId;
	}

	public void setDomainId(Long domainId) {
		this.domainId = domainId;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyPhoneCarrier() {
		return companyPhoneCarrier;
	}

	public void setCompanyPhoneCarrier(String companyPhoneCarrier) {
		this.companyPhoneCarrier = companyPhoneCarrier;
	}

	public String getCompanyPhoneNumber() {
		return companyPhoneNumber;
	}

	public void setCompanyPhoneNumber(String companyPhoneNumber) {
		this.companyPhoneNumber = companyPhoneNumber;
	}

	public Date getDomainCreatedDate() {
		return domainCreatedDate;
	}

	public void setDomainCreatedDate(Date domainCreatedDate) {
		this.domainCreatedDate = domainCreatedDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserDetails getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(UserDetails updatedBy) {
		this.updatedBy = updatedBy;
	}

}
