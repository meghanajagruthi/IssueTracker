package com.elecnor.issue.tracker.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import com.file.handler.bean.FileUploadBean;

public interface IssueRelatedDocumentService {

	public void setRelatedDocumentToDownload(String relDocmentId, HttpServletResponse httpResp)throws Exception;

	public ArrayList<FileUploadBean> getDocumentsBasedOnIssue(Integer issueId);
}
