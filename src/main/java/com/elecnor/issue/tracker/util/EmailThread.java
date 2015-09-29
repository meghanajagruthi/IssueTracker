package com.elecnor.issue.tracker.util;

public class EmailThread implements Runnable {

	private String to;
	private String cc;
	private String subject;
	private String emailText;
	private String attachmentPos;

	public EmailThread(String to, String cc, String subject,
			String emailText, String attachmentPos) {
		this.emailText = emailText;
		this.to = to;
		this.cc = cc;
		this.subject = subject;
		this.attachmentPos = attachmentPos;
	}

	public void run() {
		// TODO Auto-generated method stub
		EmailConnection emailConn = new EmailConnection();
		emailConn.sendMail(to, cc, subject, emailText, attachmentPos);
	}
}
