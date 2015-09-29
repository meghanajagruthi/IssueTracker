package com.elecnor.issue.tracker.bean;

public class EmailBean {

	private String mailTo;
	private String mailCc;
	private String mailFrom;
	private String mailSubject;
	private String mailBody;
	private String mailBcc;
/*
	// These two fields are only used to get the vendors id and name when bulk
	// order mail is
	// sent to the vendors @Ashutosh
	private String vendorsId;
	private String vendorsName;
*/
	/**
	 * @return the mailTo
	 */
	public String getMailTo() {
		return mailTo;
	}

	/**
	 * @param mailTo
	 *            the mailTo to set
	 */
	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	/**
	 * @return the mailFrom
	 */
	public String getMailFrom() {
		return mailFrom;
	}

	/**
	 * @param mailFrom
	 *            the mailFrom to set
	 */
	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	/**
	 * @return the mailSubject
	 */
	public String getMailSubject() {
		return mailSubject;
	}

	/**
	 * @param mailSubject
	 *            the mailSubject to set
	 */
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	/**
	 * @return the mailBody
	 */
	public String getMailBody() {
		return mailBody;
	}

	/**
	 * @param mailBody
	 *            the mailBody to set
	 */
	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}

	/**
	 * @return the mailBcc
	 */
	public String getMailBcc() {
		return mailBcc;
	}

	/**
	 * @param mailBcc
	 *            the mailBcc to set
	 */
	public void setMailBcc(String mailBcc) {
		this.mailBcc = mailBcc;
	}

	/**
	 * @return the mailCc
	 */
	public String getMailCc() {
		return mailCc;
	}

	/**
	 * @param mailCc
	 *            the mailCc to set
	 */
	public void setMailCc(String mailCc) {
		this.mailCc = mailCc;
	}

	/**
	 * @return the vendorsId
	 *//*
	public String getVendorsId() {
		return vendorsId;
	}

	*//**
	 * @param vendorsId
	 *            the vendorsId to set
	 *//*
	public void setVendorsId(String vendorsId) {
		this.vendorsId = vendorsId;
	}

	*//**
	 * @return the vendorsName
	 *//*
	public String getVendorsName() {
		return vendorsName;
	}

	*//**
	 * @param vendorsName
	 *            the vendorsName to set
	 *//*
	public void setVendorsName(String vendorsName) {
		this.vendorsName = vendorsName;
	}
*/
}
