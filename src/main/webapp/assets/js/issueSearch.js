$(document).ready(function() {
	initializeIssueSearchResultTable();
	getLookupDetailsToPopulateIntoSelectbox();
});

function getLookupDetailsToPopulateIntoSelectbox() {
	$
			.ajax({
				type : 'POST',
				url : 'getLookUpDetailsToCreateIssue',
				async : false,
				success : function(result) {
					var lookupDetailsToPopulateIntoSelectbox = JSON
							.parse(result);
					if (lookupDetailsToPopulateIntoSelectbox.ajaxresult == "success") {
						setOptionsToSelectBoxesInSearchPage(lookupDetailsToPopulateIntoSelectbox);
					}
				}
			});
}
function initializeIssueSearchResultTable() {
	issueSearchResultTable = $('#issueSearchResultTable')
			.dataTable(
					{
						bFilter : true,
						bSearchable : true,
						deferRender : true,
						bDestroy : true,
						aoColumns : [
								{
									sClass : "center",
									mRender : function(data, type, full) {
										if (full[0] == "Bug") {
											return "<div class='action-buttons'>"
													+ "<a class='red' href='#' title='Bug' data-rel='tooltip'>"
													+ "<i class='ace-icon fa fa-square bigger-105' style='color:rgba(255, 0, 0, 0.82)'></i></a></div>";
										} else if (full[0] == "Feature") {
											return "<div class='action-buttons'>"
													+ "<a class='grey' href='#' title='New Feature' data-rel='tooltip'>"
													+ "<i class='ace-icon fa fa-plus-square bigger-105' style='color:rgb(82, 65, 65)'></i></a></div>";
										} else {
											return "<div class='action-buttons'>"
													+ "<a class='' href='#' title='Change Request' data-rel='tooltip'>"
													+ "<i class='ace-icon fa fa-file-o bigger-105' style='color: rgb(82, 65, 65);font-size: 14px; position: relative;left: 5px'></i>"
													+ "<i class='ace-icon fa fa-check smaller-70' style='color: green;position: relative;left: -5px'></i></a></div>";
										}

									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										return "<a style='cursor:pointer' href=\"javascript:getIssueInDetailsFromSearchPage('"
												+ full[1]
												+ "')\">"
												+ "ISSUE#"
												+ full[1] + "</a>";
									}
								}, {
									sClass : "center",
									mRender : function(data, type, full) {

										return full[2];

									}
								}, {
									sClass : "center",
									mRender : function(data, type, full) {

										return full[3];

									}
								}, {
									sClass : "center",
									mRender : function(data, type, full) {
										return full[4];
									}
								} ]
					});
}

function setOptionsToSelectBoxesInSearchPage(dataTempLocal) {

	/*$('#moduleToSearch').find('option').remove().end().append(
			'<option value=""> Select Module</option>');
	$('#applicationToSearch').find('option').remove().end().append();
	$('#issueTypeToSearch').find('option').remove().end().append(
			'<option value=""> Select Type</option>');
	$('#issuePriorityToSearch').find('option').remove().end().append(
			'<option value=""> Select Severity</option>');
	$('#statusToSearch').find('option').remove().end().append(
			'<option value="">Select Status</option>');
	$('#assigneeToSearch').find('option').remove().end().append(
			'<option value=""> Select Assignee</option>');*/

	var statusList = $("#statusToSearch");
	var applicationModuleDirectoryList = $("#moduleToSearch");
	var applicationDirectoryList = $("#applicationToSearch");
	var issueTypeList = $("#issueTypeToSearch");
	var severityList = $("#issuePriorityToSearch");
	var assigneeList = $("#assigneeToSearch");

	for (var i = 0; i < dataTempLocal.applicationsList.length; i++) {
		applicationDirectoryList.append("<option value="
				+ dataTempLocal.applicationsList[i].applicationSno + ">"
				+ dataTempLocal.applicationsList[i].applicationName
				+ "</option>");
	}

	for (var i = 0; i < dataTempLocal.applicationModulesList.length; i++) {
		applicationModuleDirectoryList.append("<option value="
				+ dataTempLocal.applicationModulesList[i].moduleSno + ">"
				+ dataTempLocal.applicationModulesList[i].moduleName
				+ "</option>");
	}

	for (var n = 0; n < dataTempLocal.lookupDetailsList.length; n++) {

		if (dataTempLocal.lookupDetailsList[n].category == "Status") {
			statusList.append("<option value="
					+ dataTempLocal.lookupDetailsList[n].lookupSno + ">"
					+ dataTempLocal.lookupDetailsList[n].lookupName
					+ "</option>");
		} else if (dataTempLocal.lookupDetailsList[n].category == "Severity") {
			severityList.append("<option value="
					+ dataTempLocal.lookupDetailsList[n].lookupSno + ">"
					+ dataTempLocal.lookupDetailsList[n].lookupName
					+ "</option>");
		} else if (dataTempLocal.lookupDetailsList[n].category == "Issue Type") {
			issueTypeList.append("<option value="
					+ dataTempLocal.lookupDetailsList[n].lookupSno + ">"
					+ dataTempLocal.lookupDetailsList[n].lookupName
					+ "</option>");
		}
	}

	for (var i = 0; i < dataTempLocal.allAssigneeList.length; i++) {
		assigneeList.append("<option value="
				+ dataTempLocal.allAssigneeList[i].assigneeSno + ">"
				+ dataTempLocal.allAssigneeList[i].assigneeName + "</option>");
	}

	// setting selected application available in session in application select
	// box by default.
	if (dataTempLocal.selectedApplication != undefined
			&& dataTempLocal.selectedApplication != null
			&& dataTempLocal.selectedApplication != "") {
		$('#applicationToSearch').val(dataTempLocal.selectedApplication);
	}

	// this will execute when user is redirected from dashboard by clicking in
	// any data table
	if (dataTempLocal.dashBoardValuesMap != undefined
			&& dataTempLocal.dashBoardValuesMap != null) {

		$("#issueSearchOtherSearchAnchorId").click();

		if (dataTempLocal.dashBoardValuesMap.selctedKey == "severity") {
			$("#issuePriorityToSearch").val(
					"" + dataTempLocal.dashBoardValuesMap.selLookupBeanId + "");
		} else if (dataTempLocal.dashBoardValuesMap.selctedKey == "status") {
			$("#statusToSearch").val(
					"" + dataTempLocal.dashBoardValuesMap.selLookupBeanId + "");
		} else if (dataTempLocal.dashBoardValuesMap.selctedKey == "Issue Type") {
			$("#issueTypeToSearch").val(
					"" + dataTempLocal.dashBoardValuesMap.selLookupBeanId + "");
		} else if (dataTempLocal.dashBoardValuesMap.selctedKey == "module") {
			$("#moduleToSearch").val(
					"" + dataTempLocal.dashBoardValuesMap.selLookupBeanId + "");
		}
		try{
			var applicationToSearchArray = dataTempLocal.dashBoardValuesMap.applicationToSend
			.split(",");
	        $('#applicationToSearch').val(applicationToSearchArray);	
		}catch(e){
			
		}

		$("#issuesToSearch").val(dataTempLocal.dashBoardValuesMap.issuesToSend);
		$("#timeToSearch").val(dataTempLocal.dashBoardValuesMap.timeToSend);
		if (dataTempLocal.dashBoardValuesMap.timeToSend == "other") {
			$('#timeEnterDiv').removeClass('hide');
			$('#divClassChange').toggleClass('col-md-2 col-md-3');
			$("#daysToSearch").val(dataTempLocal.dashBoardValuesMap.daysToSend);
		}

		getIssueDetailsBasedOnSearchKeys();
	}

	$(".selectpicker").selectpicker("refresh");
}

function getIssueDetailsBasedOnSearchKeys() {

	var timeToSearchTemp = null;
	var applicationToSearchTemp = $("#applicationToSearch").val();
	if (($("#issueTypeToSearch").val() != "" && $("#issueTypeToSearch").val() != null)
			|| ($("#issueNoToSearch").val() != "" && $("#issueNoToSearch")
					.val() != null)
			|| ($("#issuePriorityToSearch").val() != "" && $(
					"#issuePriorityToSearch").val() != null)
			|| ($("#applicationToSearch").val() != "" && $(
					"#applicationToSearch").val() != null)
			|| ($("#issuesToSearch").val() != "" && $("#issuesToSearch").val() != null)
			|| ($("#statusToSearch").val() != "" && $("#statusToSearch").val() != null)
			|| ($("#moduleToSearch").val() != "" && $("#moduleToSearch").val() != null)
			|| ($("#assigneeToSearch").val() != "" && $("#assigneeToSearch")
					.val() != null)
			|| ($("#timeToSearch").val() != "" && $("#timeToSearch").val() != null)) {

		// if timeToSearch is "other" then we are sending timeToSearch as value
		// of daysToSearch text.
		// if timeToSearch is not "entire" then we are sending timeToSearch as
		// value of timeToSearch select box.
		// if timeToSearch is "entire" then we are sending timeToSearch as null.
		if ($("#timeToSearch").val() != "" && $("#timeToSearch").val() != null) {
			if ($("#timeToSearch").val() == "other") {
				timeToSearchTemp = $("#daysToSearch").val();
			} else if ($("#timeToSearch").val() != "entire") {
				timeToSearchTemp = $("#timeToSearch").val();
			}
		}
		var applicationToSearchString = "";
		for (var len = 0; len < applicationToSearchTemp.length; len++) {
			if (len == applicationToSearchTemp.length - 1) {
				applicationToSearchString += applicationToSearchTemp[len];
			} else {
				applicationToSearchString += applicationToSearchTemp[len] + ",";
			}
		}

		var dataObj = {
			issueTypeToSearch : $("#issueTypeToSearch").val(),
			issueNoToSearch : $("#issueNoToSearch").val(),
			issuePriorityToSearch : $("#issuePriorityToSearch").val(),
			applicationToSearch : applicationToSearchString,
			issuesToSearch : $("#issuesToSearch").val(),
			statusToSearch : $("#statusToSearch").val(),
			moduleToSearch : $("#moduleToSearch").val(),
			assigneeToSearch : $("#assigneeToSearch").val(),
			timeToSearch : timeToSearchTemp,
			requestFromPage : "IssueSearch"
		};
		$
				.ajax({
					type : 'POST',
					url : 'getIssueDetailsBasedOnSearchKeys',
					data : dataObj,
					async : false,
					success : function(result) {
						var issueSearchResults = JSON.parse(result);
						if (issueSearchResults.ajaxresult == "success") {
							insertingSearchresultsIntoDatatable(issueSearchResults);
						} else {
							gritterError("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
						}
					},
					error : function() {
						gritterError("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
					}
				});

	} else {
		gritterWarningMsg("Please enter atleast one search key word.");
	}

}

function insertingSearchresultsIntoDatatable(issueSearchResultsTemp) {
	issueSearchResultTable.fnClearTable();
	for (var t = 0; t < issueSearchResultsTemp.searchResults.length; t++) {
		var issueType, description, severity, reportedDateTemp;
		issueType = description = severity = reportedDateTemp = "";
		if (issueSearchResultsTemp.searchResults[t].issueType != undefined
				&& issueSearchResultsTemp.searchResults[t].issueType != null) {
			issueType = issueSearchResultsTemp.searchResults[t].issueType.lookupName;
		}
		if (issueSearchResultsTemp.searchResults[t].summary != undefined
				&& issueSearchResultsTemp.searchResults[t].summary != null) {
			description = issueSearchResultsTemp.searchResults[t].summary;
		}
		if (issueSearchResultsTemp.searchResults[t].severity != undefined
				&& issueSearchResultsTemp.searchResults[t].severity != null) {
			severity = issueSearchResultsTemp.searchResults[t].severity.lookupName;
		}

		if (issueSearchResultsTemp.searchResults[t].reportedDate != undefined
				&& issueSearchResultsTemp.searchResults[t].reportedDate != null) {
			reportedDateTemp = moment(
					new Date(
							issueSearchResultsTemp.searchResults[t].reportedDate))
					.format("MM-DD-YYYY");
		}

		issueSearchResultTable.fnAddData([ issueType,
				issueSearchResultsTemp.searchResults[t].issueSno, description,
				severity, reportedDateTemp ], false);
	}
	issueSearchResultTable.fnDraw();

}

function getModuleListForSelectedAppsIssueSearch() {

	$('#moduleToSearch').find('option').remove().end().append(
			'<option value=""> Select Module</option>');

	var applicationModuleDirectoryList = $("#moduleToSearch");

	if ($("#applicationToSearch").val() != null) {

		var applicationIdsTemp = $("#applicationToSearch").val();
		var applicationToSearch = "";
		for (var len = 0; len < applicationIdsTemp.length; len++) {
			if (len == applicationIdsTemp.length - 1) {
				applicationToSearch += applicationIdsTemp[len];
			} else {
				applicationToSearch += applicationIdsTemp[len] + ",";
			}
		}

		var dataObj = {
			applicationToSearch : applicationToSearch
		};
		$.ajax({
			url : "getModulesListByMultipleApplicationIds",
			type : "POST",
			data : dataObj,
			success : function(e) {

				var moduleListJson = JSON.parse(e);
				if (moduleListJson.ajaxresult == "success") {

					for (var i = 0; i < moduleListJson.moduleList.length; i++) {
						applicationModuleDirectoryList.append("<option value="
								+ moduleListJson.moduleList[i].moduleSno + ">"
								+ moduleListJson.moduleList[i].moduleName
								+ "</option>");
					}
					try {
						$(".selectpicker").selectpicker("refresh");
					} catch (e) {
						console.log(e);
					}
				}
			}
		});
	}

	try {
		$(".selectpicker").selectpicker("refresh");
	} catch (e) {
		console.log(e);
	}

}
// controller for this method/URL mapping is written in DashboardController
// class.
function getIssueInDetailsFromSearchPage(issueIdInSearchPage) {
	$("#issueIdToGetDetails").val(issueIdInSearchPage);
	$("#issueInDetailsForm").submit();
}