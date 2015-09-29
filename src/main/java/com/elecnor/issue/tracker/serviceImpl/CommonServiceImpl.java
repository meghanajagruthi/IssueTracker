package com.elecnor.issue.tracker.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.elecnor.issue.tracker.bean.ApplicationDirectory;
import com.elecnor.issue.tracker.bean.IssueSummary;
import com.elecnor.issue.tracker.bean.IssueTrackerMaster;
import com.elecnor.issue.tracker.bean.UserDetails;
import com.elecnor.issue.tracker.dao.ApplicationDirectoryDao;
import com.elecnor.issue.tracker.dao.CommonDao;
import com.elecnor.issue.tracker.dao.IssueTrackerMasterDao;
import com.elecnor.issue.tracker.service.CommonService;
import com.elecnor.issue.tracker.util.IssueTrackerConstants;
import com.elecnor.issue.tracker.util.IssueTrackerUtility;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class CommonServiceImpl implements CommonService {

	Logger logger = Logger.getLogger(CommonServiceImpl.class);

	@Autowired
	CommonDao commonDao;
	@Autowired
	IssueTrackerMasterDao issueTrackerMasterDao;
	@Autowired
	ApplicationDirectoryDao applicationDirectoryDao;

	public static ArrayList<String> allMPRJobNamesList = null;
	public static ArrayList<String> allJobNamesListFromEcosystem = null;

	@Override
	public String AuthenticateUser(String userName, /* String password, */
			String temporaryEmailId, HttpSession htSession) throws Exception {

		logger.info("---- Entered AuthenticateUser() of CommonServiceImpl ----");

		IssueTrackerUtility utilRef = new IssueTrackerUtility();
		UserDetails logedUser = null;
		String finalResult = IssueTrackerConstants.ISSUETRACKERCONSTANTS_REDIRECT_HOMESCREEN_PAGE;

		try {
			String url = utilRef.getEcosystemUrl() // for ecosystem redesign the
													// utilRef.getEcosystemUrl()
													// must be changed to
													// http://localhost:8080/EcosystemNew/
					+ "/excludeInterceptor/getUserDetails?emailId=" + userName; /*
																				 * +
																				 * "&&password="
																				 * +
																				 * password
																				 * ;
																				 */
			RestTemplate restTemplate = new RestTemplate();
			String jsonDetails = restTemplate.getForObject(url, String.class);
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setDateFormat("MM-dd-yyyy");
			Gson gson = gsonBuilder.create();
			// if we will get the response as "failed" then user is not
			// authenticated or plan is not active
			if (!jsonDetails.contains("failed") && jsonDetails.length() != 8) {
				logedUser = gson.fromJson(jsonDetails, UserDetails.class);
				if (temporaryEmailId != null && temporaryEmailId != "") {
					logedUser.setTemporaryEmailId(temporaryEmailId);
					logedUser.setRedirectedFromMPR(true);
				}
				htSession
						.setAttribute(
								IssueTrackerConstants.ISSUETRACKERCONSTANTS_LOGIN_USER_SESSION_NAME,
								logedUser);
			} else {
				finalResult = IssueTrackerConstants.ISSUETRACKERCONSTANTS_REDIRECT_ERROR_PAGE;
			}

		} catch (Exception e) {
			finalResult = IssueTrackerConstants.ISSUETRACKERCONSTANTS_REDIRECT_ERROR_PAGE;
			e.printStackTrace();
			throw e;
		}
		return finalResult;

	}

	@Override
	public ArrayList<IssueSummary> getAllIssuesReportedByUser(String userId) {
		ArrayList<IssueTrackerMaster> allIssues = commonDao
				.getAllIssuesReportedByUser(userId);
		ArrayList<IssueSummary> issuesSummary = new ArrayList<IssueSummary>();
		/*IssueTrackerUtility utilRef = new IssueTrackerUtility();*/

		int i=allIssues.size()-10;
		while (i<allIssues.size()) {
			
			IssueTrackerMaster issue = allIssues.get(i);
				IssueSummary tempIssue = new IssueSummary();
				tempIssue.setApplicationName(issue.getApplicationDirectory()
						.getApplicationName());
				if (issue.getAssigneeProfile() != null) {
					tempIssue.setAssigneeName(issue.getAssigneeProfile()
							.getAssigneeName());
				} else {
					tempIssue.setAssigneeName(null);
				}
				tempIssue.setSummary(issue.getSummary());
				tempIssue.setDueDate(issue.getDueDate());

				if (issue.getUpdatedDate() != null) {
					tempIssue.setUpdatedDate(issue.getUpdatedDate());
				} else {
					tempIssue.setUpdatedDate(null);
				}
				tempIssue.setIssueSno(issue.getIssueSno());
				if (issue.getPercentageDoneId() != null
						&& issue.getPercentageDoneId() != 0.0) {
					tempIssue.setPercentageDone(issue.getPercentageDoneId());
				} else {
					tempIssue.setPercentageDone(0.0);
				}
				tempIssue.setIssueType(issue.getIssueType().getLookupName());
				tempIssue.setIssueType(issue.getStatus().getLookupName());
				tempIssue.setSeverity(issue.getSeverity().getLookupName());
				if (issue.getApplicationModuleDirectory() != null) {
					tempIssue.setModuleName(issue
							.getApplicationModuleDirectory().getModuleName());
				} else {
					tempIssue.setModuleName(null);
				}
				if (issue.getRelatedProject() != null) {
					tempIssue.setProjectName(issue.getRelatedProject());
				} else {
					tempIssue.setProjectName(null);
				}

				issuesSummary.add(tempIssue);
				
				i++;
		}
		return issuesSummary;
	}

	@Override
	public ArrayList<String> getJobnamesByDomainId(String domainId,
			int selApplicationId) throws Exception {

		
		logger.info("---- Entered getJobnamesByDomainId() of CommonServiceImpl ----");

		ArrayList<String> allJobDetails = null;
		IssueTrackerUtility utilRef = new IssueTrackerUtility();
		RestTemplate restTemplate = new RestTemplate();
		try {
			
				if (allJobNamesListFromEcosystem == null) {
					String url = utilRef.getEcosystemUrl()
							+ "excludeInterceptor/getJobNamesByDomainId?domainId="
							+ domainId;

					String jobDetails = restTemplate.getForObject(url,
							String.class);
					GsonBuilder gsonBuilder = new GsonBuilder();
					gsonBuilder.setDateFormat("MM-dd-yyyy");
					Gson gson = gsonBuilder.create();

					String[] jobArray = gson.fromJson(jobDetails,
							String[].class);
					allJobNamesListFromEcosystem = new ArrayList<String>(
							Arrays.asList(jobArray));
					allJobDetails = allJobNamesListFromEcosystem;
				} else {
					allJobDetails = allJobNamesListFromEcosystem;
				}

			

		} catch (Exception e) {
			allJobDetails = null;
			e.printStackTrace();
			throw e;
		}
		return allJobDetails;
	}

	@Override
	public String setUserAndIssueDetailsInSession(String userEmailId,
			String issueSno, HttpSession httpSession) throws Exception {

		logger.info("---- Entered setUserAndIssueDetailsInSession() of CommonServiceImpl ----");

		IssueTrackerUtility utilRef = new IssueTrackerUtility();
		UserDetails logedUser = null;
		String finalResult = IssueTrackerConstants.ISSUETRACKERCONSTANTS_REDIRECT_ISSUEINDETAILS_PAGE;
		try {
			String url = utilRef.getEcosystemUrl()
					+ "/excludeInterceptor/getUserBeanByEmailId?emailId="
					+ userEmailId;
			RestTemplate restTemplate = new RestTemplate();
			String jsonDetails = restTemplate.getForObject(url, String.class);
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setDateFormat("MM-dd-yyyy");
			Gson gson = gsonBuilder.create();
			// if we will get the response as "failed" then user is not
			// authenticated or plan is not active
			ArrayList<IssueTrackerMaster> issueInDetails = issueTrackerMasterDao
					.getIssueDetailsById(Integer.valueOf(issueSno));

			if (!jsonDetails.contains("failed") && jsonDetails.length() != 8
					&& issueInDetails.size() > 0) {
				logedUser = gson.fromJson(jsonDetails, UserDetails.class);
				httpSession
						.setAttribute(
								IssueTrackerConstants.ISSUETRACKERCONSTANTS_LOGIN_USER_SESSION_NAME,
								logedUser);
				httpSession
						.setAttribute(
								IssueTrackerConstants.ISSUETRACKERCONSTANTS_STRING_SELECTED_ISSUE_SESSION_NAME,
								issueInDetails.get(0));
			} else {
				finalResult = IssueTrackerConstants.ISSUETRACKERCONSTANTS_REDIRECT_ERROR_PAGE;
			}

		} catch (Exception e) {
			finalResult = IssueTrackerConstants.ISSUETRACKERCONSTANTS_REDIRECT_ERROR_PAGE;
			e.printStackTrace();
			throw e;
		}
		return finalResult;
	}

}
