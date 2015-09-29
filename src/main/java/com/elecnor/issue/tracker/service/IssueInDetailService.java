package com.elecnor.issue.tracker.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.elecnor.issue.tracker.bean.IssueTrackerMaster;
import com.elecnor.issue.tracker.bean.UserDetails;

public interface IssueInDetailService {

	public void setIssueStatusToUpdate(IssueTrackerMaster selectedIssuebean, int estimatedTime, int perDone, String statusToUpdate, String statusUpdateRelatedComment, HttpSession httpSession)throws Exception;
	public void sendEmailWhileUserCommentOnIssue(String actiontype, UserDetails logedUserDetails, IssueTrackerMaster issueBean, String prevStatus, String curStatus, String comment);
	public void setIssueRelDocumentToUpload(HttpSession session, List<MultipartFile> uploadedFiles)throws Exception;
	void setDocumentToDelete(int descId, HttpSession session)
			throws Exception;
}
