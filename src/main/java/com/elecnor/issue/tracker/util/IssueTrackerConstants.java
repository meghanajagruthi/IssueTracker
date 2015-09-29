package com.elecnor.issue.tracker.util;

public class IssueTrackerConstants {

	public static final String ISSUETRACKERCONSTANTS_MANAGER_EMAIL="manager@belco.com";
	public static final String ISSUETRACKERCONSTANTS_MANAGER_FIRSTNAME="manager";
	public static final String ISSUETRACKERCONSTANTS_SAVE = "save";
	public static final String ISSUETRACKERCONSTANTS_UPDATE = "update";
	public static final String ISSUETRACKERCONSTANTS_SET = "set";
	public static final String ISSUETRACKERCONSTANTS_STATUS = "status";
	public static final String ISSUETRACKERCONSTANTS_COMMENT = "comment";
	public static final String ISSUETRACKERCONSTANTS_REDIRECT_ERROR_PAGE = "redirect:/errorPage";
	public static final String ISSUETRACKERCONSTANTS_REDIRECT_SESSIONEXPIRE_PAGE = "redirect:/sessionExpire";
	public static final String ISSUETRACKERCONSTANTS_REDIRECT_LOGOUT_PAGE = "redirect:/logout";
	public static final String ISSUETRACKERCONSTANTS_REDIRECT_ISSUEINDETAILS_PAGE = "redirect:/home#issueDetail";
	public static final String ISSUETRACKERCONSTANTS_REDIRECT_ISSUESEARCH_PAGE = "redirect:/home#issueSearch";
	public static final String ISSUETRACKERCONSTANTS_REDIRECT_HOMESCREEN_PAGE = "redirect:/home#homeScreen";
	public static final String ISSUETRACKERCONSTANTS_STRING_UNRESOLVED = "unresolved";
	public static final String ISSUETRACKERCONSTANTS_STRING_REPORTEDBYME = "reportedbyme";
	public static final String ISSUETRACKERCONSTANTS_STRING_ASSIGNEDTOME = "assignedtome";
	public static final String ISSUETRACKERCONSTANTS_STRING_WHERE = "where";
	public static final String ISSUETRACKERCONSTANTS_STRING_FROM = "from";
	public static final String ISSUETRACKERCONSTANTS_STRING_AND = "and";
	public static final String ISSUETRACKERCONSTANTS_APPLICATIONID_SESSION_NAME = "application";
	public static final String ISSUETRACKERCONSTANTS_LOGIN_USER_SESSION_NAME = "logedInUserDetails";
	public static final String ISSUETRACKERCONSTANTS_STRING_OTHER = "other";
	public static final String ISSUETRACKERCONSTANTS_STRING_SELECT = "SELECT";
	public static final String ISSUETRACKERCONSTANTS_STRING_ORDERBY = "ORDER BY";
	public static final String ISSUETRACKERCONSTANTS_STRING_ASC = "ASC";
	public static final String ISSUETRACKERCONSTANTS_STRING_DESC = "DESC";
	public static final String ISSUETRACKERCONSTANTS_STRING_AJAXRESULT = "ajaxresult";
	public static final String ISSUETRACKERCONSTANTS_STRING_SUCCESS = "success";
	public static final String ISSUETRACKERCONSTANTS_STRING_FAILURE = "failure";
	public static final String ISSUETRACKERCONSTANTS_STRING_DELETE = "delete";
	public static final String ISSUETRACKERCONSTANTS_STRING_SELECTED_ISSUE_SESSION_NAME = "selectedIssueDetals";

	// ISSUE_TRACKER_MASTER table column name constants starts
	public static final String ISSUETRACKERMASTER_BEAN_NAME = "IssueTrackerMaster";
	public static final String ISSUETRACKERMASTER_DB_TABLE_NAME = "ISSUE_TRACKER_MASTER";
	public static final String ISSUETRACKERMASTER_COLUMN_ISSUESNO = "ISSUE_SNO";
	public static final String ISSUETRACKERMASTER_COLUMN_ISSUETYPE = "ISSUE_TYPE";
	public static final String ISSUETRACKERMASTER_COLUMN_SEVERITY = "SEVERITY";
	public static final String ISSUETRACKERMASTER_COLUMN_STATUS = "STATUS";
	public static final String ISSUETRACKERMASTER_COLUMN_MODULEID = "MODULE_ID";
	public static final String ISSUETRACKERMASTER_COLUMN_ASSIGNEE = "ASSIGNEE";
	public static final String ISSUETRACKERMASTER_COLUMN_REPORTEDDATE = "REPORTED_DATE";
	public static final String ISSUETRACKERMASTER_COLUMN_APPLICATIONID = "APPLICATION_ID";
	public static final String ISSUETRACKERMASTER_COLUMN_REPORTEDBYID = "REPORTED_BY_ID";
	public static final String ISSUETRACKERMASTER_COLUMN_UPDATED_BY_ID = "UPDATED_BY_ID";
	public static final String ISSUETRACKERMASTER_COLUMN_UPDATED_BY_NAME = "UPDATED_BY_NAME";
	public static final String ISSUETRACKERMASTER_COLUMN_UPDATED_DATE = "UPDATED_DATE";
	public static final String ISSUETRACKERMASTER_COLUMN_ESTIMATED_TIME = "ESTIMATED_TIME";
	public static final String ISSUETRACKERMASTER_COLUMN_PERCENTAGE_DONE = "PERCENTAGE_DONE";
	public static final String ISSUETRACKERMASTER_COLUMN_RESOLVEDDATE = "RESOLVED_DATE";
	// ISSUE_TRACKER_MASTER table column name constants ends

	// APPLICATION_MODULE_DIRECTORY table column name constants starts
	public static final String MODULEDIRECTORY_BEAN_NAME = "ApplicationModuleDirectory";
	public static final String MODULEDIRECTORY_DB_TABLE_NAME = "APPLICATION_MODULE_DIRECTORY";
	public static final String MODULEDIRECTORY_COLUMN_APPLICATIONSNO = "APPLICATION_SNO";
	public static final String MODULEDIRECTORY_COLUMN_MODULE_SNO = "MODULE_SNO";
	public static final String MODULEDIRECTORY_COLUMN_MODULE_NAME = "MODULE_NAME";
	// APPLICATION_MODULE_DIRECTORY table column name constants ends
	
	// APPLICATION_DIRECTORY table column name constants starts
		public static final String APPLICATION_DIRECTORY_BEAN_NAME = "ApplicationDirectory";
		public static final String APPLICATION_DIRECTORY_DB_TABLE_NAME = "APPLICATION_DIRECTORY";
		public static final String APPLICATION_DIRECTORY_COLUMN_APPLICATIONSNO = "APPLICATION_SNO";
	// APPLICATION_MODULE_DIRECTORY table column name constants ends

	// ISSUE_TRACKER_LOOKUP table column name constants starts
	public static final String LOOKUP_BEAN_NAME = "IssueTrackerLookup";
	public static final String LOOKUP_COLUMN_CATEGORY = "CATEGORY";
	public static final String LOOKUP_VALUE_ISSUETYPE = "Issue Type";
	public static final String LOOKUP_VALUE_STATUS = "Status";
	public static final String LOOKUP_VALUE_SEVERITY = "Severity";
	public static final String LOOKUP_COLUMN_LOOKUP_NAME = "LOOKUP_NAME";
	public static final String LOOKUP_COLUMN_LOOKUP_SNO = "LOOKUP_SNO";
	// ISSUE_TRACKER_LOOKUP table column name constants ends

	// ISSUE_STATUS_TRACKING table column name constants starts
	public static final String SATUS_TRACKING_BEAN_NAME = "IssueStatusTracking";
	public static final String SATUS_TRACKING_TABLE_NAME = "ISSUE_STATUS_TRACKING";
	public static final String SATUS_TRACKING_COLUMN_ISSUESNO = "ISSUE_SNO";
	public static final String SATUS_TRACKING_COLUMN_STATUSTRACKINGSNO = "STATUS_TRACKING_SNO";
	// ISSUE_STATUS_TRACKING table column name constants ends

	//Activity table related constants starts ISSUE_SNO
	public static final String ACTIVITY_STREAM_DIRECTORY_BEAN_NAME = "ActivityStreamDirectory";
	public static final String ACTIVITY_STREAM_DIRECTORY_TABLE_NAME = "ACTIVITY_STREAM_DIRECTORY";
	public static final String ACTIVITY_STREAM_DIRECTORY_COLUMN_ISSUESNO = "ISSUE_SNO";
	public static final String ACTIVITY_STREAM_DIRECTORY_COLUMN_ACTIVITYID = "ACTIVITY_ID";
	public static final String ACTIVITY_TYPE_FOR_CREATE_ISSUE = "Created New Ticket";
	public static final String ACTIVITY_TYPE_FOR_UPDATE_ISSUE = "Updated Ticket";
	public static final String ACTIVITY_TYPE_1_FOR_ATTACHMENT = "Added Attachment";
	public static final String ACTIVITY_TYPE_FOR_COMMENT = "Added Comment";
	public static final String ACTIVITY_TYPE_FOR_ADD_ASSIGNEE = "Assigned";
	public static final String ACTIVITY_TYPE_FOR_UPDATE_ASSIGNEE = "Changed the Assinee";
	public static final String ACTIVITY_TYPE_FOR_UPDATE_STATUS = "Status Update";
	public static final String ACTIVITY_TYPE_2_FOR_ATTACHMENT = "Deleted Attachment";
	//Activity table related constants ends
	
	// ISSUE_COMMENTS_TRACKING table column name constants starts
		public static final String COMMENTS_TRACKING_BEAN_NAME = "IssueCommentsTracking";
		public static final String COMMENTS_TRACKING_COLUMN_ISSUESNO = "ISSUE_SNO";
		public static final String COMMENTS_TRACKING_COLUMN_COMMENTS_TRACKING_SNO = "COMMENTS_TRACKING_SNO";
		// ISSUE_COMMENTS_TRACKING table column name constants ends
		
		// ISSUE_RELATED_DOCUMENTS table column name constants starts
		public static final String ISSUE_RELATED_DOCUMENTS_BEAN_NAME = "IssueRelatedDocument";
		public static final String ISSUE_RELATED_DOCUMENTS_COLUMN_ISSUESNO = "ISSUE_SNO";
		public static final String ISSUE_RELATED_DOCUMENTS_COLUMN_DOCUMENT_SNO = "DOCUMENT_SNO";
		// ISSUE_RELATED_DOCUMENTS table column name constants ends
		

}
