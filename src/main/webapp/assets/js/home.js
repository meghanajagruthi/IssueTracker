$(document).ready(function() {

});

function getLookUpDetailsToCreateIssue() {
	$('#applicationId').removeAttr('disabled', '');
	$('#applicationId').selectpicker('refresh');

	$("#loadingModalDicId").modal("show");
	$.ajax({
		url : "getLookUpDetailsToCreateIssue",
		type : "POST",
		contentType : "text/plain",
		async : false,
		success : function(e) {
			var lookupDetails = JSON.parse(e);
			console.log(lookupDetails)
			setOptionsToSelectBoxes(lookupDetails);
		},
		error : function() {
			$("#loadingModalDicId").modal("hide");
		}
	});
}

function settingDefaultValuesForCreateIssueForm() {
	$("#estimateAndPercentageDoneDivId").hide();
	$("#assigneeDivId").hide();

	if (($("select[name='applicationId'").find('option:selected').text())
			.toLowerCase() == "ecosystem") {
		// bootstrap will dynamically creates the div that contain a button with
		// dropdown as options of select element. in order to make the select
		// box as read only
		// u need to give the class of select box(for ex:applicationId for this
		// select box) that will exactly applicable for that div also. using
		// that class set pointer events as 'none' to make read only
		$('div.applicationId').children().first().attr("style",
				"pointer-events: fill;");

	} else {
		$('div.applicationId').children().first().attr("style",
				"pointer-events: none;");

	}
	$("#startDate").val(moment(new Date()).format("MM-DD-YYYY"));

	$("#startDate").prop("readonly", true);
	$("#startDate").css("pointer-events", "none");

	$('div.statusId').children().first().attr("style", "pointer-events: none;");
	$('div.statusId').children().first().attr("style", "pointer-events: none;");
	// method to calculate and set due date based on selected severity
	setDueDatebasedOnSeverity();
}

var statusList = "";

function setOptionsToSelectBoxes(dataTempLocal) {

	$('#applicationModuleId').find('option').remove().end().append(
			'<option value=""> Select Module</option>');
	$('#applicationId').find('option').remove().end().append();
	$('#issueTypeId').find('option').remove().end().append(
			'<option value=""> Select Type</option>');
	$('#severityId').find('option').remove().end().append();
	$('#relatedProject').find('option').remove().end().append(
			'<option value="">Select Project</option>');
	$('#statusId').find('option').remove().end().append(
			'<option value="">Select Status</option>');
	$('#asigneeId').find('option').remove().end().append(
			'<option value=""> Select Assignee</option>');

	var relatedProjectList = $("#relatedProject");
	statusList = $("#statusId");
	var applicationModuleDirectoryList = $("#applicationModuleId");
	var applicationDirectoryList = $("#applicationId");
	var issueTypeList = $("#issueTypeId");
	var severityList = $("#severityId");
	var assigneeList = $("#asigneeId");

	if (dataTempLocal.applicationsList.length != 0) {
		for (var i = 0; i < dataTempLocal.applicationsList.length; i++) {
			applicationDirectoryList.append("<option value="
					+ dataTempLocal.applicationsList[i].applicationSno + ">"
					+ dataTempLocal.applicationsList[i].applicationName
					+ "</option>");
		}

	}

	if (dataTempLocal.applicationModulesList.length != 0) {

		for (var i = 0; i < dataTempLocal.applicationModulesList.length; i++) {
			applicationModuleDirectoryList.append("<option value="
					+ dataTempLocal.applicationModulesList[i].moduleSno + ">"
					+ dataTempLocal.applicationModulesList[i].moduleName
					+ "</option>");
		}
	}

	// lets put it in try catch. so that if any exception will come in Rest API
	// call it can't affect other execution
	try {
		if (dataTempLocal.allJobNames!=null&&dataTempLocal.allJobNames!=undefined) {
			for (var i = 0; i < dataTempLocal.allJobNames.length; i++) {
				relatedProjectList.append("<option value="
						+ dataTempLocal.allJobNames[i] + ">"
						+ dataTempLocal.allJobNames[i] + "</option>");
			}
		}

	} catch (e) {
		console.log(e);
	}

	for (var n = 0; n < dataTempLocal.lookupDetailsList.length; n++) {

		if (dataTempLocal.lookupDetailsList[n].category == "Status") {
			statusList.append("<option value="
					+ dataTempLocal.lookupDetailsList[n].lookupSno + ">"
					+ dataTempLocal.lookupDetailsList[n].lookupName
					+ "</option>");
			// setting Status as New By default....
			if ((dataTempLocal.lookupDetailsList[n].lookupName).toLowerCase() == "new") {

				$("#statusId option[value='8']").show();
				$("#statusId")
						.val(dataTempLocal.lookupDetailsList[n].lookupSno);

			}

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
		$('#applicationId').val(dataTempLocal.selectedApplication);
	}

	$(".selectpicker").selectpicker("refresh");
	settingDefaultValuesForCreateIssueForm();

}

function validateUploadFile() {

	console.log("inside validate upload file");
	var totalFiles;
	var totalFilesSize = 0;
	var checkInputMethod = $("#uploadFile").data().ace_input_method;
	if (checkInputMethod == "drop") {
		totalFiles = $("#uploadFile").data('ace_input_files');
	} else {
		totalFiles = $('#uploadFile')[0].files;
	}
	for (var len = 0; len < totalFiles.length; len++) {
		totalFilesSize = totalFilesSize + totalFiles[len].size;
	}
	// validation for 5MB.
	if (totalFilesSize > 5 * Math.pow(2, 20)) {
		gritterWarningMsg("The attachement size cannot exceed 5MB.");
		var file_input = $('#uploadFile');
		file_input.ace_file_input('reset_input');
		return false;
	}

	return true;
}

function setSaveOrUpdateIssue(createButtonObject) {

	console.log($("#createIssueFormId").serialize());

	if ($("#statusId").val() == "14") {
		if ($("#asigneeId").val() == "") {
			gritterError("Please select an assignee before you proceed");
			return;

		}
	}

	if (!validateUploadFile()) {
		return;
	}
	if (!validateCreateIssueForm()) {
		return;
	} else {
		var laddaRef;
		try {
			laddaRef = Ladda.create(createButtonObject);
			laddaRef.start();
		} catch (e) {
			console.log(e);
		}
		var attachmentFlag = true; // to check whether the attachment is
									// attached while creating the ticket
		if ($("#issueSno").val() == "") {
			attachmentFlag = false;
		}

		var checkInputMethod = $("#uploadFile").data().ace_input_method;

		$
				.ajax({
					url : "saveOrUpdateIssue",
					type : "POST",
					data : new FormData($("#createIssueFormId")[0]),
					cache : false,
					contentType : false,
					processData : false,
					async : false,
					success : function(result) {
						try {
							laddaRef.stop();
						} catch (e) {

						}

						result = JSON.parse(result);
						$('#createIssue').modal('hide');
						if (result.ajaxresult == "success") {
							var curlocation = window.location.href;
							if ($("#issueSno").val() == "") {
								gritterSuccess("Issue has been raised successfully");

							} else {
								gritterSuccess("Issue has been updated successfully");
								getSelectedIssuedetails();
							}
							if ($("#uploadFile").val() != null
									&& $("#uploadFile").val() != ""
									&& $("#uploadFile").data().ace_input_method == "select") {
								gritterSuccess("Document has been uploaded successfully");
							} else {
								if (checkInputMethod == "drop") {
									ajaxCallToUploadDocument(
											'createIssueModal', attachmentFlag);
								}
							}
							if (curlocation.indexOf("#homeScreen") != -1) {
								getIssueDetailsBasedOnSearchKeys();
							} else if (curlocation.indexOf("home#issueDetail") != -1) {
								getSelectedIssuedetails();
							}
							resetCreateIssueForm();
						} else {
							$('#createIssue').modal('hide');
							gritterError("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
							resetCreateIssueForm();
						}
					},
					error : function() {
						try {
							laddaRef.stop();
						} catch (e) {

						}

						$('#createIssue').modal('hide');
						gritterError("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
						resetCreateIssueForm();

					}
				});

	}

}

function resetCreateIssueForm() {
	$("#issueSno").val("");
	$('#createIssueFormId')[0].reset();
	$("#createNewIssueHeaderId").text("Create New Issue");
	$("#createIssueButtonSpanId").text("Create");
	// code for resetting ace_file_input.
	var file_input = $('#uploadFile');
	file_input.ace_file_input('reset_input');
}

function validateCreateIssueForm() {
	/* Validation for text_field */

	if ($('#applicationId').val() == "") {
		gritterWarningMsg('Application has to be selected.');
		return false;
	}

	if ($('#issueTypeId').val() == "") {
		gritterWarningMsg('Issue type has to be selected.');
		return false;
	}

	if ($('#summary').val() == "") {
		gritterWarningMsg("Summary cannot be empty.");
		document.getElementById('summary').focus();
		return false;
	}

	if ($('#statusId').val() == "") {
		gritterWarningMsg('Status has to be selected.');
		return false;
	}

	if ($("#percentageDoneId").val() != null
			&& ($("#percentageDoneId").val() < 0 || $("#percentageDoneId")
					.val() > 100)) {
		gritterWarningMsg('Percentage entered must be a positive number less than 100 ..Re enter the percentage');
		return false;
	}
	return true;
}

function getModuleListForSelApp(selAppId) {

	var dataObj = {
		selAppId : selAppId
	};
	$
			.ajax({
				url : "getModuleListForSelApp",
				type : "POST",
				data : dataObj,
				success : function(e) {

					var moduleList = JSON.parse(e);
					if (moduleList.ajaxresult == "success") {

						$('#applicationModuleId')
								.find('option')
								.remove()
								.end()
								.append(
										'<option value=""> Select Module</option>');

						$('#relatedProject')
								.find('option')
								.remove()
								.end()
								.append(
										'<option value="">Select Project</option>');

						var applicationModuleDirectoryList = $("#applicationModuleId");
						var relatedProjectsList = $("#relatedProject");

						for (var i = 0; i < moduleList.applicationModulesList.length; i++) {
							applicationModuleDirectoryList
									.append("<option value="
											+ moduleList.applicationModulesList[i].moduleSno
											+ ">"
											+ moduleList.applicationModulesList[i].moduleName
											+ "</option>");
						}
						try {
							if(moduleList.allJobsList!=null){
								for (var i = 0; i < moduleList.allJobsList.length; i++) {
									relatedProjectsList.append("<option value="
											+ moduleList.allJobsList[i] + ">"
											+ moduleList.allJobsList[i]
											+ "</option>");
								}
							}
							
						} catch (e) {
							console.log(e);
						}
						$(".selectpicker").selectpicker("refresh");
					}
				},
				error : function() {
				}
			});
}

function setDueDatebasedOnSeverity() {
	try {
		if (($("select[name='severityId'").find('option:selected').text())
				.toLowerCase() == "low"
				&& ($("#dueDate").val() == "" || $("#issueSno").val() == "")) {
			var curDate = new Date();
			$("#dueDate").datepicker(
					"setDate",
					moment(
							getDateByAddingNoOfDaysByExcludingWeekends(curDate,
									10)).format("MM-DD-YYYY"));
		} else if (($("select[name='severityId'").find('option:selected')
				.text()).toLowerCase() == "normal"
				&& ($("#dueDate").val() == "" || $("#issueSno").val() == "")) {
			var curDate = new Date();
			$("#dueDate").datepicker(
					"setDate",
					moment(
							getDateByAddingNoOfDaysByExcludingWeekends(curDate,
									5)).format("MM-DD-YYYY"));
		} else if (($("select[name='severityId'").find('option:selected')
				.text()).toLowerCase() == "high"
				&& ($("#dueDate").val() == "" || $("#issueSno").val() == "")) {
			var curDate = new Date();
			$("#dueDate").datepicker(
					"setDate",
					moment(
							getDateByAddingNoOfDaysByExcludingWeekends(curDate,
									3)).format("MM-DD-YYYY"));
		} else if (($("select[name='severityId'").find('option:selected')
				.text()).toLowerCase() == "urgent"
				&& ($("#dueDate").val() == "" || $("#issueSno").val() == "")) {
			var curDate = new Date();
			$("#dueDate").datepicker(
					"setDate",
					moment(
							getDateByAddingNoOfDaysByExcludingWeekends(curDate,
									1)).format("MM-DD-YYYY"));
		}
	} catch (e) {
		console.log(e);
		$("#loadingModalDicId").modal("hide");
	}
	$("#loadingModalDicId").modal("hide");
}

function getDateByAddingNoOfDaysByExcludingWeekends(fromDate, days) {
	var count = 0;
	while (count < days) {
		fromDate.setDate(fromDate.getDate() + 1);
		if (fromDate.getDay() != 0 && fromDate.getDay() != 6) // Skip weekends
			count++;
	}
	return fromDate;
}

function getIssueDetails(issueIdTemp) {
	$("#issueIdToGetDetails").val(issueIdTemp);
	$("#issueInDetailsForm").submit();
}

$(document).ready(function() {
	var foo = false;
	$('#custSearch').on('keypress', function(e) {
		var code = (e.keyCode ? e.keyCode : e.which);
		if (code == 13 & !foo) {
			e.preventDefault();
			// custSearchDomain();
			// table.draw();
			getIssueDetails(this.value);
			;
			foo = true;
		}
	});
});
