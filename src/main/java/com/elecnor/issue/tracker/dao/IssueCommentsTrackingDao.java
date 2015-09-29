package com.elecnor.issue.tracker.dao;

import java.util.ArrayList;

import com.elecnor.issue.tracker.bean.IssueCommentsTracking;

public interface IssueCommentsTrackingDao {

	public ArrayList<IssueCommentsTracking> getCommentsByIssueSno(String issueSno)throws Exception;
	public void setCommentToAddForSelectedIssue(IssueCommentsTracking issueCommentsTrackingBean)throws Exception;
}
