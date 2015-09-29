package com.elecnor.issue.tracker.expose;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elecnor.issue.tracker.bean.ApplicationDirectory;
import com.elecnor.issue.tracker.bean.ApplicationModuleDirectory;
import com.elecnor.issue.tracker.bean.IssueDocumentsMapping;
import com.elecnor.issue.tracker.bean.IssueSummary;
import com.elecnor.issue.tracker.bean.IssueTrackerLookup;
import com.elecnor.issue.tracker.bean.IssueTrackerMaster;
import com.elecnor.issue.tracker.dao.ApplicationDirectoryDao;
import com.elecnor.issue.tracker.dao.ApplicationModuleDirectoryDao;
import com.elecnor.issue.tracker.dao.CommonDao;
import com.elecnor.issue.tracker.dao.IssueRelatedDocumentDao;
import com.elecnor.issue.tracker.dao.IssueTrackerLookupDao;
import com.elecnor.issue.tracker.dao.IssueTrackerMasterDao;
import com.elecnor.issue.tracker.service.CommonService;
import com.elecnor.issue.tracker.util.IssueTrackerUtility;
import com.file.handler.service.FileUploadService;
import com.google.gson.Gson;

@Controller
public class SaveRaisedTickets {
	
	
	@Autowired
	FileUploadService fileUploadService;
	@Autowired
	ApplicationDirectoryDao applicationDirectoryDAO;
	
	@Autowired
	IssueTrackerMasterDao issueTrackerMasterDao;
	
	@Autowired
	CommonDao commonDao;
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	IssueRelatedDocumentDao issueRelatedDocumentDao;
	
	@Autowired
	ApplicationModuleDirectoryDao applicationModuleDirectoryDAO;
	@Autowired
	IssueTrackerLookupDao issueTrackerLookUpDAO;
	
	@Autowired
	IssueTrackerLookupDao issueTrackerLookupDao;
	ArrayList<IssueTrackerLookup> lookUpList;
	
	/*Integer userId=null;
	Integer issueId=null;
	
	IssueTrackerMaster savedIssue;*/
    
	
	
	public void getAllLookUpDetails() throws Exception{
    	 lookUpList=issueTrackerLookupDao.getLookupTableDetailsList();
    }
	
	@RequestMapping(value="/RedirectedFromDownStreamApp/getAllStatusFromIssueTracker",method=RequestMethod.GET)
	public @ResponseBody String getAllStatus(HttpServletRequest request) throws Exception{
	getAllLookUpDetails();
	IssueTrackerUtility util=new IssueTrackerUtility();
	HashMap<Object,Object> statusList=new HashMap<Object,Object>();
	for(IssueTrackerLookup issueStatus:lookUpList){
		if(issueStatus.getCategory().equalsIgnoreCase("status")){
			statusList.put(issueStatus.getLookupSno(), issueStatus.getLookupName());
			
		}
	}
	return  util.getJsonResult(statusList);
	}
	
	@RequestMapping(value="/RedirectedFromDownStreamApp/getAllIssueTypesFromIssueTracker",method=RequestMethod.GET)
	public @ResponseBody String getAllIssueTypes(HttpServletRequest request) throws Exception{
	getAllLookUpDetails();
	IssueTrackerUtility util=new IssueTrackerUtility();
	HashMap<Object,Object> issueTypesList=new HashMap<Object,Object>();
	for(IssueTrackerLookup issueType:lookUpList){
		if(issueType.getCategory().equalsIgnoreCase("issue type")){
			issueTypesList.put(issueType.getLookupSno(), issueType.getLookupName());
		}
	}
	return  util.getJsonResult(issueTypesList);
	}
	
	@RequestMapping(value="/RedirectedFromDownStreamApp/getAllSeveritiesFromIssueTracker",method=RequestMethod.GET)
	public @ResponseBody String getAllSeverities(HttpServletRequest request) throws Exception{
	getAllLookUpDetails();
	IssueTrackerUtility util=new IssueTrackerUtility();
	HashMap<Object,Object> severitiesList=new HashMap<Object,Object>();
	for(IssueTrackerLookup severity:lookUpList){
		if(severity.getCategory().equalsIgnoreCase("Severity")){
			severitiesList.put(severity.getLookupSno(), severity.getLookupName());
		}
	}
	
	System.out.println(severitiesList.keySet());
	System.out.println(severitiesList.values());
	return  util.getJsonResult(severitiesList);
	}
	
	@RequestMapping(value="/RedirectedFromEcosystem/getRecentIssuesByUser",method=RequestMethod.GET)
	public @ResponseBody String getRecentIssuesByUser(HttpServletRequest request){
		IssueTrackerUtility util=new IssueTrackerUtility();
		String userId=request.getParameter("userId");
		ArrayList<IssueSummary>  allIssues=commonService.getAllIssuesReportedByUser(userId);
		HashMap<Object,Object> map=new HashMap<Object,Object>();
		map.put("reportedIssueList", allIssues);
		return util.getJsonResultWithoutExpose(map);
 		
	}
	
	@RequestMapping(value="/RedirectedFromDownStreamApp/getAllModulesFromIssueTracker",method=RequestMethod.GET)
	public @ResponseBody String getAllModulesFromIssueTracker(HttpServletRequest request) throws Exception{
		
		
		IssueTrackerUtility util=new IssueTrackerUtility();
		Integer applicationId=Integer.parseInt(request.getParameter("applicationId"));
		ArrayList<ApplicationModuleDirectory> applicationModulesList = commonDao.getModulesListByApplicationId(applicationId);
	
		HashMap<Object,Object> modulesList=new HashMap<Object,Object>();
		for(ApplicationModuleDirectory module:applicationModulesList){
				modulesList.put(module.getModuleSno(), module.getModuleName());
		}
		
		return  util.getJsonResult(modulesList);
		
	}
	
	
	@RequestMapping(value="/RedirectedFromDownStreamApp/getAllProjectsFromIssueTracker",method=RequestMethod.GET)
	public @ResponseBody String getAllProjectsFromEcosystem(HttpServletRequest request) throws Exception{
		
		
		IssueTrackerUtility util=new IssueTrackerUtility();
		Integer applicationId=Integer.parseInt(request.getParameter("applicationId"));
		Integer domainId=Integer.parseInt(request.getParameter("domainId"));
		ArrayList<String> allJobNames = commonService.getJobnamesByDomainId(domainId.toString(), Integer.valueOf(applicationId)); //Hardcoded value is domain id
		HashMap<Object,Object> jobsMap=new HashMap<Object,Object>();
		for(String job:allJobNames){
			jobsMap.put(job, job);
		}
		return  util.getJsonResult(jobsMap);
		
	}
	
	@RequestMapping(value = "/RedirectedFromDownStreamApp/saveRaisedTickets" ,method=RequestMethod.POST)
	public @ResponseBody String saveRaisedTickets(@RequestBody String requestString){
		String status="success";
		IssueTrackerMaster savedIssue = null;
		try{
			Gson gson = new Gson();
			HashMap<String, Object> requestMap = new HashMap<String, Object>();
			requestMap = gson.fromJson(requestString, requestMap.getClass());

			IssueTrackerUtility util=new IssueTrackerUtility();

			System.out.println("Entered inside the save raised tickets   ");
			IssueTrackerMaster raisedIssue=new IssueTrackerMaster();

			ApplicationDirectory savedApp = applicationDirectoryDAO.getApplicationDetailsById(requestMap.get("applicationId").toString());
			System.out.println(savedApp.getApplicationName());

			raisedIssue.setApplicationDirectory(savedApp);
			if( requestMap.get("applicationModuleId").toString().trim()!=null){
				raisedIssue.setApplicationModuleDirectory(applicationModuleDirectoryDAO.getModuleDetailsById(requestMap.get("applicationModuleId").toString()));
			}
			else{
				raisedIssue.setApplicationModuleDirectory(null);
			}

			if(requestMap.get("relatedProject").toString()!=null){
				raisedIssue.setRelatedProject(requestMap.get("relatedProject").toString());
			}
			else{
				raisedIssue.setRelatedProject(null);
			}
			raisedIssue.setIssueType(issueTrackerLookUpDAO.getLookupTableDetailsById(requestMap.get("issueType").toString()));
			raisedIssue.setSummary(requestMap.get("summary").toString());
			raisedIssue.setDescription(requestMap.get("description").toString());
			IssueTrackerLookup associatedStatus = issueTrackerLookUpDAO.getLookupTableDetailsById(requestMap.get("statusId").toString());
			raisedIssue.setStatus(associatedStatus);
			raisedIssue.setSeverity(issueTrackerLookUpDAO.getLookupTableDetailsById(requestMap.get("severityId").toString()));
			raisedIssue.setReportedDate(new Date());
			raisedIssue.setDueDate(util.convertDate(requestMap.get("dueDate").toString()));
			raisedIssue.setReportedById(Integer.parseInt( ((String) requestMap.get("reportedById")).trim()));
			raisedIssue.setReportedByName(((String) requestMap.get("reportedByName")).trim());
			raisedIssue.setReportedByEmailId(((String) requestMap.get("reportedByEmailId")).trim());
			
			savedIssue=issueTrackerMasterDao.setIssueToSaveOrUpdate(raisedIssue);

			// ***************** Saving Attachment *****************
			//Map<String, byte[]> attchmentMap = (Map<String, byte[]>) gson.fromJson((String) requestMap.get("attchmentMap"), Map.class);
			Map<String, byte[]> attchmentMap = (Map<String, byte[]>) requestMap.get("attchmentMap");
			if(attchmentMap!=null && !attchmentMap.isEmpty()){
				String fileName = null;
				String fileExt = null;
				Integer fileId;
				IssueDocumentsMapping issueDocument = null;
				Iterator it = attchmentMap.entrySet().iterator();
				Map.Entry mapEntry = null;
				while(it.hasNext()){
					mapEntry = (Map.Entry) it.next();
					fileName = (String) mapEntry.getKey();
					fileExt = fileName.substring(fileName.lastIndexOf("."));
					fileId = fileUploadService.uploadFile(((String) mapEntry.getValue()).getBytes(), fileName, fileExt);
					issueDocument=new IssueDocumentsMapping();
					issueDocument.setRelatedDocument(fileId);
					issueDocument.setRelatedIssue(savedIssue.getIssueSno());
					issueDocument.setSubmittedBy(Integer.parseInt( ((String) requestMap.get("reportedById")).trim()));
					
					issueRelatedDocumentDao.saveDocumentMapping(issueDocument);
				}
			}

			System.out.println("************* " + savedIssue.toString());
		}
		catch(Exception e){
			e.printStackTrace();
			status=e.getMessage();
		}
		return  status;
	}
	
	/*@RequestMapping(value = "/RedirectedFromDownStreamApp/saveRaisedTickets" ,method=RequestMethod.POST)
	public @ResponseBody String saveRaisedTickets(@RequestBody MultiValueMap<String, String> params){
		String status="success";
		try{
			
			IssueTrackerUtility util=new IssueTrackerUtility();
			
			System.out.println("Entered inside the save raised tickets   ");
			IssueTrackerMaster raisedIssue=new IssueTrackerMaster();
			
			ApplicationDirectory savedApp=applicationDirectoryDAO.getApplicationDetailsById(params.getFirst("applicationId").trim());
			System.out.println(savedApp.getApplicationName());
			
			raisedIssue.setApplicationDirectory(savedApp);
			if(params.getFirst("applicationModuleId").trim()!=null){
				raisedIssue.setApplicationModuleDirectory(applicationModuleDirectoryDAO.getModuleDetailsById(params.getFirst("applicationModuleId")));
			}
			else{
				raisedIssue.setApplicationModuleDirectory(null);
			}
			
			if(params.getFirst("relatedProject").trim()!=null){
				raisedIssue.setRelatedProject(params.getFirst("relatedProject"));
			}
			else{
				raisedIssue.setRelatedProject(null);
			}
			raisedIssue.setIssueType(issueTrackerLookUpDAO.getLookupTableDetailsById(params.getFirst("issueType").trim()));
			raisedIssue.setSummary(params.getFirst("summary"));
			raisedIssue.setDescription(params.getFirst("description"));
			IssueTrackerLookup associatedStatus=issueTrackerLookUpDAO.getLookupTableDetailsById(params.getFirst("statusId").trim());
			raisedIssue.setStatus(associatedStatus);
			raisedIssue.setSeverity(issueTrackerLookUpDAO.getLookupTableDetailsById(params.getFirst("severityId").trim()));
			raisedIssue.setReportedDate(new Date());
			raisedIssue.setDueDate(util.convertDate(params.getFirst("dueDate").trim()));
			raisedIssue.setReportedById(Integer.parseInt(params.getFirst("reportedById").trim()));
		   savedIssue=issueTrackerMasterDao.setIssueToSaveOrUpdate(raisedIssue);
		   
			
		}
		catch(Exception e){
			e.printStackTrace();
			status=e.getMessage();
		}
		return  status;
	}*/

	// Commented by Vaibhav, This method will not be used.
	/*@RequestMapping(value="/RedirectedFromDownStreamApp/saveUploadedFiles",method=RequestMethod.POST)
	public @ResponseBody String saveUploadedFiles(@RequestParam(value="Filename") String fileName,@RequestParam(value="uploadedFiles") String filesResource){
		
		String status=null;
		System.out.println("Inside the redirection from down stream application");
		String extension = fileName.substring(fileName.lastIndexOf("."));
		System.out.println(extension);
          Integer fileId=fileUploadService.uploadFile(filesResource.getBytes(), fileName, extension);
          IssueDocumentsMapping issueDocument=new IssueDocumentsMapping();
			issueDocument.setRelatedDocument(fileId);
			issueDocument.setRelatedIssue(savedIssue.getIssueSno());
			issueDocument.setSubmittedBy(userId);
			try {
				issueRelatedDocumentDao.saveDocumentMapping(issueDocument);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				status=e.getMessage();
				e.printStackTrace();
			}
	
		return "success";
}*/
}