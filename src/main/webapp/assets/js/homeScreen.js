/*$(document).ready(function() {
	initializeIssueTrackingTable();
});

function initializeIssueTrackingTable() {
	issueTrackingTable = $('#issueTrackingTable')
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
										return "<a style='cursor:pointer' href=\"javascript:getIssueDetails('"
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

										return full[5];

									}
								},{
									sClass : "center",
									mRender : function(data, type, full) {
										return full[4];
									}
								} ]
					});
	getIssueListForHomeScreen();
}

function getIssueListForHomeScreen() {
	$
			.ajax({
				url : "getIssueListForDashboard",
				type : "POST",
				contentType : "text/plain",
				success : function(e) {
					var mode = JSON.parse(e);
					insertingValuesIntoDatatable(mode);
				},
				error : function() {
					gritterError("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
				}
			});
}

function insertingValuesIntoDatatable(dataTemp) {
	issueTrackingTable.fnClearTable();
	for (var t = 0; t < dataTemp.issueList.length; t++) {
		var issueType, description, severity, reportedDateTemp, statusTemp;
		issueType = description = severity = reportedDateTemp = statusTemp = "";
		if (dataTemp.issueList[t].issueType != undefined
				&& dataTemp.issueList[t].issueType != null) {
			issueType = dataTemp.issueList[t].issueType.lookupName;
		}
		if (dataTemp.issueList[t].summary != undefined
				&& dataTemp.issueList[t].summary != null) {
			description = dataTemp.issueList[t].summary;
		}
		if (dataTemp.issueList[t].severity != undefined
				&& dataTemp.issueList[t].severity != null) {
			severity = dataTemp.issueList[t].severity.lookupName;
		}
		if (dataTemp.issueList[t].reportedDate != undefined
				&& dataTemp.issueList[t].reportedDate != null) {
			reportedDateTemp = moment(
					new Date(dataTemp.issueList[t].reportedDate)).format(
							"MM-DD-YYYY");
		}
		if (dataTemp.issueList[t].status != undefined
				&& dataTemp.issueList[t].status != null) {
			statusTemp = dataTemp.issueList[t].status.lookupName;
		}

		issueTrackingTable.fnAddData(
				[ issueType, dataTemp.issueList[t].issueSno, description,
						severity, reportedDateTemp, statusTemp ], false);
	}
	issueTrackingTable.fnDraw();

}
 */

$(document).ready(function() {
	
	// document.getElementById('issueSearchResultTable').style.width="100%";
	initializeIssueSearchResultTable();
	getLookupDetailsToPopulateIntoSelectbox();
});

function getLookupDetailsToPopulateIntoSelectbox() {
	$
			.ajax({
				type : 'POST',
				url : 'getLookupDetailsToPopulateIntoSelectBoxHomeScreen',
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
	

	jQuery.fn.dataTableExt.oSort['alphanumeric-html-asc']  = function(a,b) {
	    a = parseInt($(a).text());
	    b = parseInt($(b).text());
	    return ((a < b) ? -1 : ((a > b) ?  1 : 0));
	};
	 
	jQuery.fn.dataTableExt.oSort['alphanumeric-html-desc']  = function(a,b) {
	    a = parseInt($(a).text());
	    b = parseInt($(b).text());
	    return ((a < b) ? 1 : ((a > b) ?  -1 : 0));
	};
	
	
	issueSearchResultTable = $('#issueSearchResultTable')
			.dataTable(
					{
						bFilter : true,
						"bSort": true,
						bSearchable : true,
						deferRender : true,
						bDestroy : true,
						"columnDefs": [
						              
						              /* {
						                   "targets": [ 6 ],
						                   "visible": false
						               },
						               {
						                   "targets": [ 1 ],
						                   "orderData": [ 1, 6 ]
						               }*/
						               ],
						 
						aoColumns : [
								{
									sType : null,
									sClass : "center",
									mRender : function(data, type, full) {
										if (full[0] == "Bug") {
											return "<div class='action-buttons'>"
													+ "<a class='red' href='#' title='Bug' data-rel='tooltip'>"
													+ "<i class='fa fa-bug' style='color:rgba(255, 0, 0, 0.82)'></i></a></div>";
										} else if (full[0] == "Feature") {
											return "<div class='action-buttons'>"
													+ "<a class='grey' href='#' title='New Feature' data-rel='tooltip'>"
													+ "<i class='ace-icon fa fa-plus-square bigger-105' style='color:rgb(82, 65, 65)'></i></a></div>";
										} else {
											return "<div class='action-buttons'>"
													+ "<a class='' href='#' title='Change Request' data-rel='tooltip'>"
													/*+ "<i class='ace-icon fa fa-file-o bigger-105' style='color: rgb(82, 65, 65);font-size: 14px; position: relative;left: 5px'></i>"*/
													+ "<i class='fa fa-retweet bigger-115' style='color: black ;position: relative;left: -4px'></i></a></div>";
										}

									}
								},
								{
									sType : "alphanumeric-html",
									sClass : "center",
									mRender : function(data, type, full) {
										return "<a  class='btnAddTab' style='cursor:pointer' href=\"javascript:setMethodToDecideCreateTab('"
												+ full[1]
												+ "')\">"
												+ "ISSUE#"
												+ full[1] + "</a>";
									}
								}, {
									sType : null,
									sClass : "center",
									mRender : function(data, type, full) {

										return full[2];

									}
								}, {
									sType : null,
									sClass : "center",
									mRender : function(data, type, full) {

										return full[3];

									}
								}, {
									sType : null,
									sClass : "center",
									mRender : function(data, type, full) {

										return full[5];

									}
								},
								{
									sType : null,
									sClass : "center",
									mRender : function(data, type, full) {
										return full[7];
									}
								},
								{
									sType : null,
									sClass : "center",
									mRender : function(data, type, full) {
										return full[4];
									}
								},{
									sType : null,
									sClass : "center",
									mRender : function(data, type, full) {
										return full[6];
									}
								}/*,
								{
									sType : null,
									sClass : "center",
									mRender : function(data, type, full) {
										return full[1];
									}
								}*/
								]
					});
}

function setOptionsToSelectBoxesInSearchPage(dataTempLocal) {

//	$('#moduleToSearch').find('option').remove().end().append(
//			'<option value=""> Select Module</option>');
//	$('#applicationToSearch').find('option').remove().end().append();
//	$('#issueTypeToSearch').find('option').remove().end().append(
//			'<option value=""> Select Type</option>');
//	$('#issuePriorityToSearch').find('option').remove().end().append(
//			'<option value=""> Select Severity</option>');
//	$('#statusToSearch').find('option').remove().end().append(
//			'<option value="">Select Status</option>');
//	$('#assigneeToSearch').find('option').remove().end().append(
//			'<option value=""> Select Assignee</option>');

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
		try {
			var applicationToSearchArray = dataTempLocal.dashBoardValuesMap.applicationToSend
					.split(",");
			$('#applicationToSearch').val(applicationToSearchArray);
		} catch (e) {

		}

		$("#issuesToSearch").val(dataTempLocal.dashBoardValuesMap.issuesToSend);
		$("#timeToSearch").val(dataTempLocal.dashBoardValuesMap.timeToSend);
		if (dataTempLocal.dashBoardValuesMap.timeToSend == "other") {
			$('#timeEnterDiv').removeClass('hide');
			$('#divClassChange').toggleClass('col-md-2 col-md-3');
			$("#daysToSearch").val(dataTempLocal.dashBoardValuesMap.daysToSend);
		}

	}

	if (($("select[name='applicationToSearch'").find('option:selected').text())
			.toLowerCase() == "ecosystem") {
		// bootstrap will dynamically creates the div that contain a button with
		// dropdown as options of select element. in order to make the select
		// box as read only
		// u need to give the class of select box(for ex:applicationId for this
		// select box) that will exactly applicable for that div also. using
		// that class set pointer events as 'none' to make read only
		$('div.applicationToSearch').children().first().attr("style",
				"pointer-events: fill;");
	} else {
		$('div.applicationToSearch').children().first().attr("style",
				"pointer-events: none;");
	}

	$(".selectpicker").selectpicker("refresh");
	getIssueDetailsBasedOnSearchKeys();
}

function getIssueDetailsBasedOnSearchKeys() {

	$("#loadingModalDicId").modal("show");
	var timeToSearchTemp = null;
	var applicationToSearchTemp = $("#applicationToSearch").val();
	var issuePriorityToSearchTemp = $("#issuePriorityToSearch").val();
	var issueTypeToSearchTemp = $("#issueTypeToSearch").val();
	var statusToSearchTemp = $("#statusToSearch").val();
	var moduleToSearchTemp = $("#moduleToSearch").val();
	var assigneeToSearchTemp = $("#assigneeToSearch").val();

	var applicationToSearchString = "";
	var issuePriorityToSearchString = "";
	var issueTypeToSearchString = "";
	var statusToSearchString = "";
	var moduleToSearchString = "";
	var assigneeToSearchString = "";

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

		if (applicationToSearchTemp != undefined
				&& applicationToSearchTemp != null) {
			for (var len = 0; len < applicationToSearchTemp.length; len++) {
				if (len == applicationToSearchTemp.length - 1) {
					applicationToSearchString += applicationToSearchTemp[len];
				} else {
					applicationToSearchString += applicationToSearchTemp[len]
							+ ",";
				}
			}
		}

		if (issuePriorityToSearchTemp != undefined
				&& issuePriorityToSearchTemp != null) {
			for (var len = 0; len < issuePriorityToSearchTemp.length; len++) {
				if (len == issuePriorityToSearchTemp.length - 1) {
					issuePriorityToSearchString += issuePriorityToSearchTemp[len];
				} else {
					issuePriorityToSearchString += issuePriorityToSearchTemp[len]
							+ ",";
				}
			}
		}

		if (issueTypeToSearchTemp != undefined && issueTypeToSearchTemp != null) {
			for (var len = 0; len < issueTypeToSearchTemp.length; len++) {
				if (len == issueTypeToSearchTemp.length - 1) {
					issueTypeToSearchString += issueTypeToSearchTemp[len];
				} else {
					issueTypeToSearchString += issueTypeToSearchTemp[len] + ",";
				}
			}
		}

		if (statusToSearchTemp != undefined && statusToSearchTemp != null) {
			for (var len = 0; len < statusToSearchTemp.length; len++) {
				if (len == statusToSearchTemp.length - 1) {
					statusToSearchString += statusToSearchTemp[len];
				} else {
					statusToSearchString += statusToSearchTemp[len] + ",";
				}
			}
		}

		if (moduleToSearchTemp != undefined && moduleToSearchTemp != null) {
			for (var len = 0; len < moduleToSearchTemp.length; len++) {
				if (len == moduleToSearchTemp.length - 1) {
					moduleToSearchString += moduleToSearchTemp[len];
				} else {
					moduleToSearchString += moduleToSearchTemp[len] + ",";
				}
			}
		}

		if (assigneeToSearchTemp != undefined && assigneeToSearchTemp != null) {
			for (var len = 0; len < assigneeToSearchTemp.length; len++) {
				if (len == assigneeToSearchTemp.length - 1) {
					assigneeToSearchString += assigneeToSearchTemp[len];
				} else {
					assigneeToSearchString += assigneeToSearchTemp[len] + ",";
				}
			}
		}

		var dataObj = {
			issueTypeToSearch : issueTypeToSearchString,
			issueNoToSearch : $("#issueNoToSearch").val(),
			issuePriorityToSearch : issuePriorityToSearchString,
			applicationToSearch : applicationToSearchString,
			issuesToSearch : $("#issuesToSearch").val(),
			statusToSearch : statusToSearchString,
			moduleToSearch : moduleToSearchString,
			assigneeToSearch : assigneeToSearchString,
			timeToSearch : timeToSearchTemp,
			reportedByMeCheckbox : $('#reportedByMeCheckbox').is(':checked'),
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
							$("#loadingModalDicId").modal("hide");
							gritterError("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
						}
					},
					error : function() {
						$("#loadingModalDicId").modal("hide");
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
		var issueType, description, severity, reportedDateTemp, statusTemp;
		issueType = description = severity = reportedDateTemp = statusTemp = dueDate= "";
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

		if (issueSearchResultsTemp.searchResults[t].status != undefined
				&& issueSearchResultsTemp.searchResults[t].status != null) {
			statusTemp = issueSearchResultsTemp.searchResults[t].status.lookupName;
		}

		if (issueSearchResultsTemp.searchResults[t].reportedDate != undefined
				&& issueSearchResultsTemp.searchResults[t].reportedDate != null) {
			reportedDateTemp = moment(
					new Date(
							issueSearchResultsTemp.searchResults[t].reportedDate))
					.format("MM-DD-YYYY");
		}
		if (issueSearchResultsTemp.searchResults[t].dueDate != undefined
				&& issueSearchResultsTemp.searchResults[t].dueDate != null) {
			dueDate = moment(
					new Date(
							issueSearchResultsTemp.searchResults[t].dueDate))
					.format("MM-DD-YYYY");
		}
		var assigneeName="";
		if (issueSearchResultsTemp.searchResults[t].assigneeProfile != undefined
				&& issueSearchResultsTemp.searchResults[t].assigneeProfile != null) {
			assigneeName = issueSearchResultsTemp.searchResults[t].assigneeProfile.assigneeName;
		}
		console.log(assigneeName);

		issueSearchResultTable.fnAddData([ issueType,
				issueSearchResultsTemp.searchResults[t].issueSno, description,
				severity, reportedDateTemp, statusTemp,dueDate,assigneeName], false);
	}
	issueSearchResultTable.fnDraw();
	$("#loadingModalDicId").modal("hide");
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