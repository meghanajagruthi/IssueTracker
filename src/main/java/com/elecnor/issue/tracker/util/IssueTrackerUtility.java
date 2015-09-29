package com.elecnor.issue.tracker.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.client.RestTemplate;

import com.elecnor.issue.tracker.bean.ApplicationDirectory;
import com.elecnor.issue.tracker.bean.EmailBean;
import com.elecnor.issue.tracker.bean.UserDetails;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class IssueTrackerUtility {

	public String getEcosystemUrl() {

		try {
			PropertyFileReader propertyFileInstance = PropertyFileReader
					.getInstance();
			String hostName = propertyFileInstance
					.getStringProperty("PDHostName");
			String portNum = propertyFileInstance
					.getStringProperty("PDPortNumber");
			String protocol = propertyFileInstance
					.getStringProperty("PDProtocol");
			String ecosystemDeployementName = propertyFileInstance
					.getStringProperty("ecosystemDeployementName");
			String url = protocol + "://" + hostName + ":" + portNum + "/"
					+ ecosystemDeployementName + "/";
			return url;
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}

	}

	public Date getRequiredDate(int noOfDays) {
		Date repDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(repDate);
		int daysToDecrement = -noOfDays;
		cal.add(Calendar.DATE, daysToDecrement);
		Date past30Days = cal.getTime();
		return past30Days;
	}

	/*public String getApplicationUrl(int selApplicationId) {

		ApplicationDirectory applicationDetails = null;
		String urlToReturn = null;
		String applicationName = "MPR";
		try {
			String url = getEcosystemUrl()
					+ "excludeInterceptor/getApplicationDetailsByApplicationName?applicationName="
					+ applicationName;
			RestTemplate restTemplate = new RestTemplate();
			String apiResponse = restTemplate.getForObject(url, String.class);
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setDateFormat("MM-dd-yyyy");
			Gson gson = gsonBuilder.create();
			if (!apiResponse.contains("failed") && apiResponse.length() != 8) {
				urlToReturn = url;
				applicationDetails = gson.fromJson(apiResponse,
						ApplicationDirectory.class);
				if (applicationDetails.getApplicationLink().charAt(
						(applicationDetails.getApplicationLink().length()) - 1) == '/') {
					urlToReturn = applicationDetails.getApplicationLink();
				} else {
					urlToReturn = applicationDetails.getApplicationLink() + "/";
				}

			} else {
				urlToReturn = null;
			}

		} catch (Exception e) {
			urlToReturn = null;
			e.printStackTrace();
		}
		return urlToReturn;

	}*/

	private static EmailBean getRequestedEmailObj(HttpServletRequest request) {
		EmailBean emailBean = new EmailBean();
		emailBean.setMailTo(request.getParameter("mailTo"));
		if (request.getParameter("mailTo") != null && !request.getParameter("mailTo").equals("")) {
			emailBean.setMailTo(request.getParameter("mailTo"));
		}
		if (request.getParameter("mailCc") != null && !request.getParameter("mailCc").equals("")) {
			emailBean.setMailCc(request.getParameter("mailCc"));
		}
		if (request.getParameter("mailBcc") != null && !request.getParameter("mailBcc").equals("")) {
			emailBean.setMailBcc(request.getParameter("mailBcc"));
		}
		if (request.getParameter("mailSubject") != null && !request.getParameter("mailSubject").equals("")) {
			emailBean.setMailSubject(request.getParameter("mailSubject"));
		}
		if (request.getParameter("mailBody") != null && !request.getParameter("mailBody").equals("")) {
			emailBean.setMailBody(request.getParameter("mailBody"));
		}
		
		return emailBean;
	}
	public String getJsonResult(List<Object> resultMap) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		String json = gson.toJson(resultMap);
		return json;

	}
	
	

	public String getJsonResult(HashMap<Object, Object> resultMap) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		String json = gson.toJson(resultMap);
		return json;

	}
	public String getJsonResultWithoutExpose(HashMap<Object, Object> resultMap) {
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(resultMap);
		return json;

	}

	public String formatDate(Date dDate) throws Exception {
		SimpleDateFormat sdf = null;
		if (dDate == null)
			return "";
		sdf = new SimpleDateFormat("MM-dd-yyyy");
		String s1 = sdf.format(dDate);
		return s1;
	}
	public Date convertDate(String dDate) throws Exception {
		SimpleDateFormat sdf = null;
		if (dDate == null)
			return null;
		sdf = new SimpleDateFormat("MM-dd-yyyy");
		Date s1 = sdf.parse(dDate);
		return s1;
	}
	public String getIssueSearchQuery(HttpSession session, String requestFrom,
			String issueNo, String issueType, String issuePriority,
			String applicationToSearch, String issuesToSearch,
			String statusToSearch, String moduleToSearch,
			String assigneeToSearch, String timeToSearch,
			String reportedByMeCheckbox) throws Exception {

		String queryToReturn = "";
		if (requestFrom.equalsIgnoreCase("dashboard")) {
			queryToReturn = IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_SELECT
					+ " COUNT(*) "
					+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_FROM
					+ " "
					+ IssueTrackerConstants.ISSUETRACKERMASTER_DB_TABLE_NAME;
		} else if (requestFrom.equalsIgnoreCase("IssueSearch")) {
			queryToReturn = IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_SELECT
					+ " * "
					+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_FROM
					+ " "
					+ IssueTrackerConstants.ISSUETRACKERMASTER_DB_TABLE_NAME;
		}

		try {

			// if search is by issue no the no need to look for other conditions
			if (issueNo != "" && issueNo != null) {
				if (queryToReturn
						.contains(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE)) {
					queryToReturn += " "
							+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AND
							+ " "
							+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_ISSUESNO
							+ "='" + issueNo + "'";
				} else {
					queryToReturn += " "
							+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE
							+ " "
							+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_ISSUESNO
							+ "='" + issueNo + "'";
				}

			} else {

				if (issueType != "" && issueType != null) {
					if (queryToReturn
							.contains(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE)) {
						queryToReturn += " "
								+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AND
								+ " "
								+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_ISSUETYPE
								+ " IN(" + issueType + ")";
					} else {
						queryToReturn += " "
								+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE
								+ " "
								+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_ISSUETYPE
								+ " IN(" + issueType + ")";
					}

				}

				if (issuePriority != "" && issuePriority != null) {
					if (queryToReturn
							.contains(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE)) {
						queryToReturn += " "
								+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AND
								+ " "
								+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_SEVERITY
								+ " IN(" + issuePriority + ")";
					} else {
						queryToReturn += " "
								+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE
								+ " "
								+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_SEVERITY
								+ " IN(" + issuePriority + ")";
					}

				}

				if (statusToSearch != "" && statusToSearch != null) {
					if (queryToReturn
							.contains(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE)) {
						queryToReturn += " "
								+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AND
								+ " "
								+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_STATUS
								+ " IN(" + statusToSearch + ")";
					} else {
						queryToReturn += " "
								+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE
								+ " "
								+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_STATUS
								+ " IN(" + statusToSearch + ")";
					}

				}

				if (moduleToSearch != "" && moduleToSearch != null) {
					if (queryToReturn
							.contains(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE)) {
						queryToReturn += " "
								+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AND
								+ " "
								+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_MODULEID
								+ " IN(" + moduleToSearch + ")";
					} else {
						queryToReturn += " "
								+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE
								+ " "
								+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_MODULEID
								+ " IN(" + moduleToSearch + ")";
					}

				}

				if (assigneeToSearch != "" && assigneeToSearch != null) {
					if (queryToReturn
							.contains(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE)) {
						queryToReturn += " "
								+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AND
								+ " "
								+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_ASSIGNEE
								+ " IN(" + assigneeToSearch + ")";
					} else {
						queryToReturn += " "
								+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE
								+ " "
								+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_ASSIGNEE
								+ " IN(" + assigneeToSearch + ")";
					}

				}

				if (reportedByMeCheckbox != "" && reportedByMeCheckbox != null) {
					if (reportedByMeCheckbox.equalsIgnoreCase("true")) {
						UserDetails logedInUserDetails = (UserDetails) session
								.getAttribute(IssueTrackerConstants.ISSUETRACKERCONSTANTS_LOGIN_USER_SESSION_NAME);
						if (queryToReturn
								.contains(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE)) {
							queryToReturn += " "
									+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AND
									+ " "
									+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_REPORTEDBYID
									+ "=" + logedInUserDetails.getUserId();
						} else {
							queryToReturn += " "
									+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE
									+ " "
									+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_REPORTEDBYID
									+ "=" + logedInUserDetails.getUserId();
						}
					}
				}

				if (issuesToSearch != "" && issuesToSearch != null) {
					// if issuesToSearch is unresolved then get all the issues
					// which status is not equal to "Resolved" i.e. 11 (Resolved
					// lookup id from DB lookup table)
					if (IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_UNRESOLVED
							.equalsIgnoreCase(issuesToSearch)) {
						if (queryToReturn
								.contains(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE)) {
							queryToReturn += " "
									+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AND
									+ " "
									+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_STATUS
									+ " != '11'"
									+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AND
									+ " "
									+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_STATUS
									+ " != '12'";
						} else {
							queryToReturn += " "
									+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE
									+ " "
									+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_STATUS
									+ " != '11'"
									+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AND
									+ " "
									+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_STATUS
									+ " != '12'";
						}
					} else
					// if issuesToSearch is reportedByMe then get all the issues
					// which are reported by log in user
					if (IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_REPORTEDBYME
							.equalsIgnoreCase(issuesToSearch)) {
						UserDetails logedInUserDetails = (UserDetails) session
								.getAttribute(IssueTrackerConstants.ISSUETRACKERCONSTANTS_LOGIN_USER_SESSION_NAME);
						if (queryToReturn
								.contains(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE)) {
							queryToReturn += " "
									+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AND
									+ " "
									+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_REPORTEDBYID
									+ "=" + logedInUserDetails.getUserId();
						} else {
							queryToReturn += " "
									+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE
									+ " "
									+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_REPORTEDBYID
									+ "=" + logedInUserDetails.getUserId();
						}
					} else
					// if issuesToSearch is assignedToMe then get all the issues
					// which are assigned to log in user
					if (IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_ASSIGNEDTOME
							.equalsIgnoreCase(issuesToSearch)) {
						UserDetails logedInUserDetails = (UserDetails) session
								.getAttribute(IssueTrackerConstants.ISSUETRACKERCONSTANTS_LOGIN_USER_SESSION_NAME);
						if (queryToReturn
								.contains(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE)) {
							queryToReturn += " "
									+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AND
									+ " "
									+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_ASSIGNEE
									+ "= " + logedInUserDetails.getUserId();
						} else {
							queryToReturn += " "
									+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE
									+ " "
									+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_ASSIGNEE
									+ "= " + logedInUserDetails.getUserId();
						}
					}

				}

				if (timeToSearch != "" && timeToSearch != null) {
					if (queryToReturn
							.contains(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE)) {
						queryToReturn += " "
								+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AND
								+ " "
								+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_REPORTEDDATE
								+ " BETWEEN  DATE_SUB( CURDATE() ,INTERVAL "
								+ timeToSearch + " DAY ) AND NOW()";
					} else {
						queryToReturn += " "
								+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE
								+ " "
								+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_REPORTEDDATE
								+ " BETWEEN  DATE_SUB( CURDATE() ,INTERVAL "
								+ timeToSearch + " DAY ) AND NOW()";
					}

				}
			}

			// if application is not selected then we are setting the
			// application available in session.
			if (applicationToSearch != "" && applicationToSearch != null) {
				if (queryToReturn
						.contains(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE)) {
					queryToReturn += " "
							+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AND
							+ " "
							+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_APPLICATIONID
							+ " IN (" + applicationToSearch + ")";
				} else {
					queryToReturn += " "
							+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE
							+ " "
							+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_APPLICATIONID
							+ " IN (" + applicationToSearch + ")";
				}

			} else {

				if (queryToReturn
						.contains(IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE)) {
					queryToReturn += " "
							+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_AND
							+ " "
							+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_APPLICATIONID
							+ "="
							+ (String) session
									.getAttribute(IssueTrackerConstants.ISSUETRACKERCONSTANTS_APPLICATIONID_SESSION_NAME);
				} else {
					queryToReturn += " "
							+ IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_WHERE
							+ " "
							+ IssueTrackerConstants.ISSUETRACKERMASTER_COLUMN_APPLICATIONID
							+ "="
							+ (String) session
									.getAttribute(IssueTrackerConstants.ISSUETRACKERCONSTANTS_APPLICATIONID_SESSION_NAME);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return queryToReturn;
	}

}
