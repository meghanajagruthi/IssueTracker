package com.elecnor.issue.tracker.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

public class IssueTrackerInterceptor implements HandlerInterceptor {
	boolean logout=false;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		// If Request url is not of login, then only check session and valid
		// user
		
		if(request.getRequestURI().equals("/ElecnorIssueTracker/logoutOutAction")){
			logout=true;
		}
		
		if(logout){
			request.getSession().invalidate();
			HttpSession session = request.getSession(false);
			if(session == null){
				final ModelAndView mv = new ModelAndView("logout");
				ModelAndViewDefiningException mvde = new ModelAndViewDefiningException(
						mv);
				logout=false;
				throw mvde;
			}
		}
		
		
		
		else if (!request.getRequestURI().equals("/ElecnorIssueTracker/login")
				&& !request.getRequestURI().equals("/ElecnorIssueTracker")
				&& !request.getRequestURI().equals("/ElecnorIssueTracker/")
				&& !request.getRequestURI().contains("assets")
				&& !request.getRequestURI().contains("/ElecnorIssueTracker/excludeInterceptor/")
				&& !request.getRequestURI().contains("/ElecnorIssueTracker/excludeIntercepter/")
				&& !request.getRequestURI().contains("/ElecnorIssueTracker/excludeIntercepterRedirectedFromApps")
				&& !request.getRequestURI().equals("/ElecnorIssueTracker/errorPage")
				&& !request.getRequestURI().equals("/ElecnorIssueTracker/getAllStatusFromIssueTracker")
				&& !request.getRequestURI().contains("/ElecnorIssueTracker/RedirectedFromDownStreamApp/")
				&& !request.getRequestURI().contains("/ElecnorIssueTracker/RedirectedFromEcosystem/")
				) {
			// If Session Is empty or user is not logged in -- then redirecting
			// the request to home page
			HttpSession session = request.getSession(false);
			System.out.println(session == null || session.getAttribute("logedInUserDetails") == null);
			if (session == null || session.getAttribute("logedInUserDetails") == null) {
				final ModelAndView mv = new ModelAndView("sessionExpire");
				ModelAndViewDefiningException mvde = new ModelAndViewDefiningException(
						mv);
				
				throw mvde;
			}
		}
		/*else if(request.getRequestURI().equals("/ElecnorIssueTracker/home#homeScreen")){ //checking whether user is trying to click back button
			HttpSession session = request.getSession(false);
			System.out.println("User is out of the application");
			if (session == null || session.getAttribute("logedInUserDetails") == null) {
				final ModelAndView mv = new ModelAndView("sessionExpire");
				ModelAndViewDefiningException mvde = new ModelAndViewDefiningException(
						mv);
				throw mvde;
			}	
		}*/

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
