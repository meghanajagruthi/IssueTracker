package com.elecnor.issue.tracker.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.elecnor.issue.tracker.bean.IssueTrackerMaster;
import com.elecnor.issue.tracker.bean.UserDetails;

public interface IssueTrackerMasterService {

	public String setIssueToSaveOrUpdate(IssueTrackerMaster issueTrackerMaster,
			UserDetails logedUser, String relatedApplication,
			String relatedModule, String issueType,
			String issueStatus, String issueSeverity, String assigneeId, HttpSession session, List<MultipartFile> uploadedFiles) throws Exception;
}
