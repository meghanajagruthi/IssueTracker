package com.elecnor.issue.tracker.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.elecnor.issue.tracker.dao.IssueRelatedDocumentDao;
import com.elecnor.issue.tracker.service.IssueRelatedDocumentService;
import com.file.handler.bean.FileUploadBean;
import com.file.handler.service.FileUploadService;
import com.file.handler.serviceImpl.FileUploadServiceImpl;


@Service
public class IssueRelatedDocumentServiceImpl implements
		IssueRelatedDocumentService {

	@Autowired
	FileUploadService fileUploadServiceImpl;
	
	Logger logger = Logger.getLogger(IssueRelatedDocumentServiceImpl.class);

	@Autowired
	IssueRelatedDocumentDao issueRelatedDocumentDao; 

	@Override
	public ArrayList<FileUploadBean> getDocumentsBasedOnIssue(Integer issueId){
		ArrayList<Integer> idsList=issueRelatedDocumentDao.getRelatedDocumentsByIssueNumber(issueId);
		ArrayList<FileUploadBean> associatedDocuments=new ArrayList<FileUploadBean>();
	    for(Integer tempId:idsList){
	    	//uploadServiceImpl. must be implemented
	    
	    	FileUploadBean uploadedBean=fileUploadServiceImpl.getFileInfo(tempId);
	    	associatedDocuments.add(uploadedBean);
	    }
	    return associatedDocuments;
	}
	
	@Override
	public void setRelatedDocumentToDownload(String relDocmentId,
			HttpServletResponse httpResp) throws Exception {

		logger.info("---- Entered setRelatedDocumentToDownload() of IssueRelatedDocumentServiceImpl ----");
   
		
		try {
			if (relDocmentId != null && relDocmentId != "") {
				//File downloadedFile=uploadServiceImpl.downloadFile(Integer.parseInt(relDocmentId));
				//System.out.println(downloadedFile.getName());
				FileUploadBean fileToDownload = fileUploadServiceImpl.getFileInfo(Integer.parseInt(relDocmentId));
				Map<String, Object> fileContent=fileUploadServiceImpl.downloadFile(Integer.parseInt(relDocmentId));
				System.out.println(fileContent);
				File file=(File)fileContent.get("success");
				 InputStream targetStream = new FileInputStream(file);
				String fileName = fileToDownload.getFileName().trim();
				try {
					if (fileToDownload.getFileType().trim()
							.equalsIgnoreCase("txt")) {
						httpResp.setContentType("text/plain");
					} else if (fileToDownload.getFileType().trim()
							.equalsIgnoreCase("doc")) {
						httpResp.setContentType("application/msword");
					} else if (fileToDownload.getFileType().trim()
							.equalsIgnoreCase("xls")) {
						httpResp.setContentType("application/vnd.ms-excel");
					} else if (fileToDownload.getFileType().trim()
							.equalsIgnoreCase("pdf")) {
						httpResp.setContentType("application/pdf");
					} else if (fileToDownload.getFileType().trim()
							.equalsIgnoreCase("ppt")) {
						httpResp.setContentType("application/ppt");
					} else {
						httpResp.setContentType("application/octet-stream");
					}
					httpResp.setContentLength((int)file.length());
					httpResp.setHeader("Content-Disposition",
							"attachment; filename=\"" + fileName + "\"");

					FileCopyUtils.copy(targetStream,
							httpResp.getOutputStream());

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
