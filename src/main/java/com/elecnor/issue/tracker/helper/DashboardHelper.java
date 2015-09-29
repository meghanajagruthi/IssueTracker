package com.elecnor.issue.tracker.helper;

public class DashboardHelper {

	public String[] parseJsonStringFromUI(String applicationId)
	{
		String appString=applicationId.substring(1, applicationId.length()-1);
		appString=appString.replaceAll("\"", "");
		return appString.split(",");
	}
}
