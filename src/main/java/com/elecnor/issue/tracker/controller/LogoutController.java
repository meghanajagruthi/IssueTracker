package com.elecnor.issue.tracker.controller;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.issue.tracker.util.IssueTrackerConstants;

@Controller
public class LogoutController {
	
	Logger logger = Logger.getLogger(LogoutController.class);
	
	@RequestMapping("/logout")
	public String getLogout() {
		return "logout";
	}

	// method will call when user clicks on the link given in email
	@RequestMapping(value = "/logoutOutAction")
	public String getIssueDetails(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {

		logger.info("---- Entered getIssueDetails() of LogoutController ----");
		
		try {
			session = request.getSession(false);
			response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
			response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
			response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
			response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
			session.removeAttribute(IssueTrackerConstants.ISSUETRACKERCONSTANTS_LOGIN_USER_SESSION_NAME);
			session.setAttribute(IssueTrackerConstants.ISSUETRACKERCONSTANTS_LOGIN_USER_SESSION_NAME, null);
			session.invalidate();
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.error(errors.toString());
		}

		return "logout";

	}
	@RequestMapping(value="/checkSessions")
	public @ResponseBody boolean checkSessions(HttpSession session){
		if(session==null)
		{
			return true;
		}
		else{
			return false;
		}
	}
}
