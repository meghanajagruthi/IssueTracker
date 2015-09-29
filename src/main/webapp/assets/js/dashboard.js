var moduleListObject = null;

$(document).ready(function() {
	getApplicationListForSelectBox();
//	getModulesListForSelectedApps();
	getIssueCountsForDashboard();
//	initializePieCharts();
	
	
});

function getApplicationListForSelectBox() {
	
		$
				.ajax({
					type : 'POST',
					url : 'getApplicationListForSelectBox',
					async : false,
					success : function(result) {
						var applicationListResult = JSON.parse(result);
						if (applicationListResult.ajaxresult == "success") {
							insertingApplicationsIntoSelectBox(applicationListResult);
						} else {
							gritterError("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
						}
					},
					error : function() {
						gritterError("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
					}
				});

}

function insertingApplicationsIntoSelectBox(applicationListTemp){
	
	$('#applicationToSearchInDashboard').find('option').remove().end().append();
	var applicationDirectoryList = $("#applicationToSearchInDashboard");
	for (var i = 0; i < applicationListTemp.applicationsList.length; i++) {
		applicationDirectoryList.append("<option value="
				+ applicationListTemp.applicationsList[i].applicationSno + ">"
				+ applicationListTemp.applicationsList[i].applicationName
				+ "</option>");
	}
	//setting selected application(available in session) for select box
	if(applicationListTemp.selectedApplication != undefined && applicationListTemp.selectedApplication != null){
		$("#applicationToSearchInDashboard").val(applicationListTemp.selectedApplication);
	}
	
	if(($("select[name='applicationToSearchInDashboard'").find('option:selected').text()).toLowerCase() == "ecosystem"){
		//bootstrap will dynamically creates the div that contain a button with dropdown as options of select element. in order to make the select box as read only
		//u need to give the class of select box(for ex:applicationId for this select box) that will exactly applicable for that div also. using that class set pointer events as 'none' to make read only  
		$('div.applicationToSearchInDashboard').children().first().attr('style','pointer-events:fill')
	       } else {
	    	   $('div.applicationToSearchInDashboard').children().first().attr('style','pointer-events:none')
	          }
	$(".selectpicker").selectpicker("refresh");
}

function setMethodDecideToCall(valueSelected) {
	if(valueSelected == "other"){
		$("#daysToSearchInDashboard").focus();
	} else {
		getIssueCountsForDashboard();
	}
}


function getIssueCountsForDashboard() {
	
	$("#loadingModalDicId").modal("show");
	
	var timeToSearchTemp = null;
	var applicationToSearchTemp  = $("#applicationToSearchInDashboard").val();

		//if timeToSearch is "other" then we are sending timeToSearch as value of daysToSearch text. 
		//if timeToSearch is not "entire" then we are sending timeToSearch as value of timeToSearch select box.
		//if timeToSearch is "entire" then we are sending timeToSearch as null.
		if($("#timeToSearchInDashboard").val() != "" && $(
					"#timeToSearchInDashboard").val() != null){
				if($("#timeToSearchInDashboard").val() == "other"){
					timeToSearchTemp = $("#daysToSearchInDashboard").val();
				} else if($("#timeToSearchInDashboard").val() != "entire"){
					timeToSearchTemp = $("#timeToSearchInDashboard").val();
				} 	
		}
		var applicationToSearchString = "";
		if(applicationToSearchTemp != undefined && applicationToSearchTemp != null){
			for(var len = 0;len < applicationToSearchTemp.length;len++){
				if(len == applicationToSearchTemp.length - 1){
					applicationToSearchString += applicationToSearchTemp[len];
				} else {
					applicationToSearchString += applicationToSearchTemp[len]+",";
				}
			}
		}
		
		
		var dataObj = {
			applicationToSearch : applicationToSearchString,
			issuesToSearch : $("#issuesToSearchInDashboard").val(),
			timeToSearch : timeToSearchTemp,
			requestFromPage : "Dashboard"
		};
		
		$
				.ajax({
					type : 'POST',
					url : 'getIssueCountsForDashboard',
					data : dataObj,
					async : false,
					success : function(result) {
						
						var issueSearchResultsInDashboard = JSON.parse(result);
						if (issueSearchResultsInDashboard.ajaxresult == "success") {
							
							insertingValuesIntoDatatable(issueSearchResultsInDashboard);
						} else {
							$("#loadingModalDicId").modal("hide");
							gritterError("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
						}
					},
					error : function() {
						$("#loadingModalDicId").modal("hide");
						gritterError("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
					}
				});

}

function insertingValuesIntoDatatable(dataTemp) {
	
	//setting tables headings
	$("#severityTableHeadingSpanId").text($("#issuesToSearchInDashboard option:selected").html());
	$("#statusTableHeadingSpanId").text($("#issuesToSearchInDashboard option:selected").html());
	$("#issuetypeTableHeadingSpanId").text($("#issuesToSearchInDashboard option:selected").html());
	$("#moduleTableHeadingSpanId").text($("#issuesToSearchInDashboard option:selected").html());
	$("#assigneeTableHeadingSpanId").text($("#issuesToSearchInDashboard option:selected").html());
	
	$("#dashboardByModuleTable tbody").empty();
	$("#dashboardByPriorityTable tbody").empty();
	$("#dashboardByStatusTable tbody").empty();
	$("#dashboardByIssueTypeTable tbody").empty();
	
	for (var key in dataTemp.severityCountList) {
		  if ((dataTemp.severityCountList).hasOwnProperty(key)) {
			  if(dataTemp.totalIssuesCount != 0){
				  var rowToAppend = "<tr><td><a style='cursor:pointer' href=\"javascript:redirectToIssueSearchPage('severity','"+key+"')\">"+key+"</a></td><td>"+dataTemp.severityCountList[key]+"</td><td>"+(((dataTemp.severityCountList[key]/dataTemp.totalIssuesCount)*(100)) != 0?((dataTemp.severityCountList[key]/dataTemp.totalIssuesCount)*(100)).toFixed(2):(dataTemp.severityCountList[key]/dataTemp.totalIssuesCount)*(100))+"</td></tr>";
				  $("#dashboardByPriorityTable tbody").append(rowToAppend); 
			  } else {
				  var rowToAppend = "<tr><td><a style='cursor:pointer' href=\"javascript:redirectToIssueSearchPage('severity','"+key+"')\">"+key+"</a></td><td>"+dataTemp.severityCountList[key]+"</td><td>0</td></tr>";
				  $("#dashboardByPriorityTable tbody").append(rowToAppend);
			  }
			  
		  }
		}
	
	for (var key in dataTemp.statusCountList) {
		  if ((dataTemp.statusCountList).hasOwnProperty(key)) {
			  if(dataTemp.totalIssuesCount != 0){
				  var rowToAppend = "<tr><td><a style='cursor:pointer' href=\"javascript:redirectToIssueSearchPage('status','"+key+"')\">"+key+"</a></td><td>"+dataTemp.statusCountList[key]+"</td><td>"+(((dataTemp.statusCountList[key]/dataTemp.totalIssuesCount)*(100)) != 0?((dataTemp.statusCountList[key]/dataTemp.totalIssuesCount)*(100)).toFixed(2):(dataTemp.statusCountList[key]/dataTemp.totalIssuesCount)*(100))+"</td></tr>";
				  $("#dashboardByStatusTable tbody").append(rowToAppend); 
			  } else {
				  var rowToAppend = "<tr><td><a style='cursor:pointer' href=\"javascript:redirectToIssueSearchPage('status','"+key+"')\">"+key+"</a></td><td>"+dataTemp.statusCountList[key]+"</td><td>0</td></tr>";
				  $("#dashboardByStatusTable tbody").append(rowToAppend);
			  }
			 
		  }
		}
	
	for (var key in dataTemp.issueTypeCountList) {
		  if ((dataTemp.issueTypeCountList).hasOwnProperty(key)) {
			  if(dataTemp.totalIssuesCount != 0){
				  var rowToAppend = "<tr><td><a style='cursor:pointer' href=\"javascript:redirectToIssueSearchPage('Issue Type','"+key+"')\">"+key+"</a></td><td>"+dataTemp.issueTypeCountList[key]+"</td><td>"+(((dataTemp.issueTypeCountList[key]/dataTemp.totalIssuesCount)*(100)) != 0?((dataTemp.issueTypeCountList[key]/dataTemp.totalIssuesCount)*(100)).toFixed(2):(dataTemp.issueTypeCountList[key]/dataTemp.totalIssuesCount)*(100))+"</td></tr>";
				  $("#dashboardByIssueTypeTable tbody").append(rowToAppend);
			  } else {
				  var rowToAppend = "<tr><td><a style='cursor:pointer' href=\"javascript:redirectToIssueSearchPage('Issue Type','"+key+"')\">"+key+"</a></td><td>"+dataTemp.issueTypeCountList[key]+"</td><td>0</td></tr>";
				  $("#dashboardByIssueTypeTable tbody").append(rowToAppend);
			  }
			  
		  }
		}
	
	for (var key in dataTemp.moduleCount) {
		  if ((dataTemp.moduleCount).hasOwnProperty(key)) {
			  if(dataTemp.totalIssuesCount != 0){
				  var rowToAppend = "<tr><td><a style='cursor:pointer' href=\"javascript:redirectToIssueSearchPage('module','"+key+"')\">"+key+"</a></td><td>"+dataTemp.moduleCount[key]+"</td><td>"+(((dataTemp.moduleCount[key]/dataTemp.totalIssuesCount)*(100)) != 0?((dataTemp.moduleCount[key]/dataTemp.totalIssuesCount)*(100)).toFixed(2):(dataTemp.moduleCount[key]/dataTemp.totalIssuesCount)*(100))+"</td></tr>";
				  $("#dashboardByModuleTable tbody").append(rowToAppend); 
			  } else {
				  var rowToAppend = "<tr><td><a style='cursor:pointer' href=\"javascript:redirectToIssueSearchPage('module','"+key+"')\">"+key+"</a></td><td>"+dataTemp.moduleCount[key]+"</td><td>0</td></tr>";
				  $("#dashboardByModuleTable tbody").append(rowToAppend);
			  }
			  
		  } 
		}
	$("#loadingModalDicId").modal("hide");
}

function redirectToIssueSearchPage(lookupType, lookupValue){
	
	$("#lookupType").val(lookupType);
	$("#lookupValue").val(lookupValue);
	$("#applicationToSend").val($("#applicationToSearchInDashboard").val());
	$("#issuesToSend").val($("#issuesToSearchInDashboard").val());
	$("#timeToSend").val($("#timeToSearchInDashboard").val());
	$("#daysToSend").val($("#daysToSearchInDashboard").val());
	$("#redirectToIssueSearchPageForm").submit();
	
}

