package com.elecnor.issue.tracker.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;

import com.google.gson.annotations.Expose;

public class UserDetails implements Serializable{

	private static final long serialVersionUID = 1L;

	@Expose
	private Integer userId;

	@Expose
	private String emailId;

	@Expose
	private String phoneNumber;

	@Expose
	private String phoneCarrier;

	@Expose
	private String firstName;

	@Expose
	private String lastName;

	@Expose
	private String middleName;

	@Expose
	private String password;

	@Temporal(TemporalType.DATE)
	@Expose
	private Date submittedDate;

	// added by Siva Sankar field required while delegating role
	@Expose
	private int delegatedRole;
	@Expose
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private Date delegatedRoleEndDate;
	@Expose
	private Integer delegatedByUserId;

	// bi-directional many-to-one association to UserProfile
	private UserDetails supervisorUserProfile;

	// bi-directional many-to-one association to UserProfile

	@Expose
	@Formula("DOMAIN_ID")
	private Long domainIdTransient;
	// bi-directional many-to-one association to UserProfile
	@Expose
	private UserDetails submittedBy;

	private UserDetails updatedBy;

	@Temporal(TemporalType.DATE)
	private Date updatedDate;

	@Expose
	private String status;
	
	//this property is for using while user is redirected from MPR. this needs to delete (or) not used after MPR integrated with ecosystem. 
	@Expose
	private String temporaryEmailId;
	
	//this property is for using while user is redirected from MPR. this needs to delete (or) not used after MPR integrated with ecosystem. 
	@Expose
	private boolean isRedirectedFromMPR;
	
	/*@Expose
	private DomainDetail domainDetail;
*/
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneCarrier() {
		return phoneCarrier;
	}

	public void setPhoneCarrier(String phoneCarrier) {
		this.phoneCarrier = phoneCarrier;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	public int getDelegatedRole() {
		return delegatedRole;
	}

	public void setDelegatedRole(int delegatedRole) {
		this.delegatedRole = delegatedRole;
	}

	public Date getDelegatedRoleEndDate() {
		return delegatedRoleEndDate;
	}

	public void setDelegatedRoleEndDate(Date delegatedRoleEndDate) {
		this.delegatedRoleEndDate = delegatedRoleEndDate;
	}

	public Long getDomainIdTransient() {
		return domainIdTransient;
	}

	public void setDomainIdTransient(Long domainIdTransient) {
		this.domainIdTransient = domainIdTransient;
	}

	public Integer getDelegatedByUserId() {
		return delegatedByUserId;
	}

	public void setDelegatedByUserId(Integer delegatedByUserId) {
		this.delegatedByUserId = delegatedByUserId;
	}

	public UserDetails getSupervisorUserProfile() {
		return supervisorUserProfile;
	}

	public void setSupervisorUserProfile(UserDetails supervisorUserProfile) {
		this.supervisorUserProfile = supervisorUserProfile;
	}

	public UserDetails getSubmittedBy() {
		return submittedBy;
	}

	public void setSubmittedBy(UserDetails submittedBy) {
		this.submittedBy = submittedBy;
	}

	public UserDetails getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(UserDetails updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/*public DomainDetail getDomainDetail() {
		return domainDetail;
	}

	public void setDomainDetail(DomainDetail domainDetail) {
		this.domainDetail = domainDetail;
	}*/

	public String getTemporaryEmailId() {
		return temporaryEmailId;
	}

	public void setTemporaryEmailId(String temporaryEmailId) {
		this.temporaryEmailId = temporaryEmailId;
	}

	public boolean isRedirectedFromMPR() {
		return isRedirectedFromMPR;
	}

	public void setRedirectedFromMPR(boolean isRedirectedFromMPR) {
		this.isRedirectedFromMPR = isRedirectedFromMPR;
	}
	
	
}
