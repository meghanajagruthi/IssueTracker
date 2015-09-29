/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elecnor.issue.tracker.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

/*import javax.activation.DataHandler;
 import javax.activation.DataSource;
 import javax.activation.FileDataSource;
 */
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

/**
 * @author Ankur
 * 
 */
public class EmailConnection {
	Logger logger = Logger.getLogger(EmailConnection.class);

	public EmailConnection() {
	}

	private String protocol;
	private String host;
	private int smtpPortNo;
	private String smtpHost;
	private String emailId;
	private String password;

	public Store store = null;
	public Session session = null;
	public Properties props = null;

	private void doInit() {
		try {
			PropertyFileReader propertReader = PropertyFileReader.getInstance();
			emailId = propertReader.getStringProperty("errorMailFrom");
			password = propertReader.getStringProperty("errorMailPassword");
			protocol = propertReader.getStringProperty("mailProtocol");
			host = propertReader.getStringProperty("mailHost");
			smtpHost = propertReader.getStringProperty("mailSmtpHost");
			smtpPortNo = propertReader.getIntProperty("mailSmtpPortNo");
			props = System.getProperties();
			props.setProperty("mail.store.protocol", protocol);
			Authenticator auth = new MyAuthentication(emailId, password);
			session = Session.getDefaultInstance(props, auth);
			store = session.getStore("pop3");
			store.connect(host, emailId, password);
			props.put("mail.smtp.port", smtpPortNo);
			props.put("mail.smtp.host", smtpHost);
			props.put("mail.smtp.auth", "true");
			props.put("mail.debug", "false");
			props.put("mail.smtp.ssl.enable", "false");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void sendExceptionMail(String msgContent) {

		logger.info("Sending an exception mail");
		try {
			PropertyFileReader propertReader = PropertyFileReader.getInstance();
			doInit();
			String subject = "Exception Occurred in MPR";
			String msgRecipients = propertReader
					.getStringProperty("errorMailTo");
			String cc = propertReader.getStringProperty("errorMailCC");

			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(emailId));

			// Set To: header field of the header.
			message.addRecipients(Message.RecipientType.TO,
					InternetAddress.parse(msgRecipients));
			if (cc.length() != 0) {
				message.addRecipients(Message.RecipientType.CC,
						InternetAddress.parse(cc));
			}
			// Set Subject: header field
			message.setSubject(subject);

			// Create the message part
			MimeBodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart.setText(
					"<pre style='font-size:16px;font-family: Calibri'>"
							+ msgContent + "</pre>", null, "html");

			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);
			// Send the complete message parts
			message.setContent(multipart);
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public synchronized void sendMail(String to, String cc, String subject,
			String msgContent, String attachmentpos) {

		logger.info("While sending mail");
		try {
			PropertyFileReader propertReader = PropertyFileReader.getInstance();
			doInit();
			String debugMode = propertReader.getStringProperty("debugMode");

			if (debugMode.equalsIgnoreCase("true")) {
				to = propertReader.getStringProperty("debugModeEmailId");
				cc = null;
				msgContent += "<br/> Debug Mode Enabled";
			}
			msgContent += "<br/><br/><br/><br/> This is a system generated email, do not reply to this email id.";
			// String subject = "Exception Occurred in MPR";

			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(emailId));

			// Set To: header field of the header.
			message.addRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			if (cc != null && cc.length() != 0) {
				message.addRecipients(Message.RecipientType.CC,
						InternetAddress.parse(cc));
			}
			// Set Subject: header field
			message.setSubject(subject);

			/* Start Here */
			// Create the message part
			MimeBodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart.setText(
					"<pre style='font-size:16px;font-family: Calibri'>"
							+ msgContent + "</pre>", null, "html");

			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);
			// Send the complete message parts
			message.setContent(multipart);
			// Send message
			Transport.send(message);
			/* End Here */

			System.out.println("Sent message successfully.... Sent to " + to);
		} catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.error(errors.toString());
			// Thread thread = new Thread(new EmailThread(errors.toString()));
			// thread.start();
		}

	}

	public static void main(String[] agrs) throws Exception {

		System.out.println("==== Sending email ==== ");
		EmailConnection emainConn = new EmailConnection();
		emainConn.sendMail("ankur.srivastav@cerridsolutions.com", "", "Test",
				"Test", null);
		// emainConn.getMailConnection("vdixit@cerridsolutions.com" ,
		// "vaibhav123");
		// emainConn.sendMail("vdixit@cerridsolutions.com", "vdixit", "Test",
		// "Test Mail", "vdixit@cerridsolutions.com",
		// "vdixit@cerridsolutions.com", "");
		// emainConn.sendExceptionMail("");
	}
}

class MyAuthentication extends Authenticator {

	private String password;
	private String emailId;

	public MyAuthentication(String emailId, String password) {
		this.password = password;
		this.emailId = emailId;
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(emailId, password);
	}
}
