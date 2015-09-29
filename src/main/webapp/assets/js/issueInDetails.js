$(document).ready(function() {
	// getSelectedIssuedetails();
});

function getSelectedIssuedetails(issueNoTemp, methodCall) {

	var dataObj = {
		"issueIdToGetDetails" : issueNoTemp
	};
	$("#loadingModalDicId").modal("show");
	$
			.ajax({
				url : "getSelectedIssuedetails",
				type : "POST",
				data : dataObj,
				success : function(e) {
					$("#loadingModalDicId").modal("hide");
					selIssueDetails = JSON.parse(e);
					console.log(selIssueDetails);
					if (selIssueDetails.ajaxresult == "success") {
						setIssueDetailsToUIElements(selIssueDetails);
						handleButtonsInIssuedetails(selIssueDetails);
						if (methodCall != undefined && methodCall != null) {
							newTabCreationMethod(issueNoTemp);
						}

						$('.custFind .profile-info-row').first().css(
								'background', 'rgb(255, 251, 229)');
					} else {
						gritterError("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
					}

				},
				error : function() {
					$("#loadingModalDicId").modal("hide");
					gritterError("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
				}
			});
}

function setIssueDetailsToUIElements(issueDetailstemp) {
	$('#commentsDivId').empty();
	$('#issueRelatedAttachmentsDivId').empty();
	$('#statusLogsMainDivIdInIssueIndetails').empty();

	if (issueDetailstemp.selectedIssue.summary != undefined
			&& issueDetailstemp.selectedIssue.summary != null) {
		$("#issueHearderSpanId").text(issueDetailstemp.selectedIssue.summary);
	}
	if (issueDetailstemp.selectedIssue.issueType != undefined
			&& issueDetailstemp.selectedIssue.issueType != null) {
		$("#issueTypeSpanId").text(
				issueDetailstemp.selectedIssue.issueType.lookupName);
	}
	if (issueDetailstemp.selectedIssue.severity != undefined
			&& issueDetailstemp.selectedIssue.severity != null) {
		$("#issuePrioritySpanId").text(
				issueDetailstemp.selectedIssue.severity.lookupName);
	}
	if (issueDetailstemp.selectedIssue.status != undefined
			&& issueDetailstemp.selectedIssue.status != null) {
		$("#issueStatusSpanId").text(
				issueDetailstemp.selectedIssue.status.lookupName);
	}
	if (issueDetailstemp.selectedIssue.applicationDirectory != undefined
			&& issueDetailstemp.selectedIssue.applicationDirectory != null) {
		$("#applicationSpanId")
				.text(
						issueDetailstemp.selectedIssue.applicationDirectory.applicationName);
	}
	if (issueDetailstemp.selectedIssue.relatedProject != undefined
			&& issueDetailstemp.selectedIssue.relatedProject != null) {
		$("#projectSpanId").text(issueDetailstemp.selectedIssue.relatedProject);
	}

	if (issueDetailstemp.selectedIssue.applicationModuleDirectory != undefined
			&& issueDetailstemp.selectedIssue.applicationModuleDirectory != null) {
		$("#moduleSpanId")
				.text(
						issueDetailstemp.selectedIssue.applicationModuleDirectory.moduleName);
	}

	if (issueDetailstemp.selectedIssue.description != undefined
			&& issueDetailstemp.selectedIssue.description != null) {
		$("#descriptionTextArea").val(
				issueDetailstemp.selectedIssue.description);
	}

	if (issueDetailstemp.selectedIssue.assigneeProfile != undefined
			&& issueDetailstemp.selectedIssue.assigneeProfile != null) {
		$("#assigneePersonId").text(
				issueDetailstemp.selectedIssue.assigneeProfile.assigneeName);
	}
	if (issueDetailstemp.selectedIssue.reportedByName != undefined
			&& issueDetailstemp.selectedIssue.reportedByName != null) {
		$("#reportedPersonId").text(
				issueDetailstemp.selectedIssue.reportedByName);
	}
	if (issueDetailstemp.selectedIssue.reportedDate != undefined
			&& issueDetailstemp.selectedIssue.reportedDate != null) {
		$("#createdDateId").text(
				moment(new Date(issueDetailstemp.selectedIssue.reportedDate))
						.format("MM-DD-YYYY HH:mm"));
	}
	if (issueDetailstemp.selectedIssue.updatedDate != undefined
			&& issueDetailstemp.selectedIssue.updatedDate != null) {
		$("#updatedDateId").text(
				moment(new Date(issueDetailstemp.selectedIssue.updatedDate))
						.format("MM-DD-YYYY HH:mm"));
	}
	if (issueDetailstemp.selectedIssue.dueDate != undefined
			&& issueDetailstemp.selectedIssue.dueDate != null) {
		$("#dueDateId").text(
				moment(new Date(issueDetailstemp.selectedIssue.dueDate))
						.format("MM-DD-YYYY"));
	}
	$("#commentsDivId").hide();
	var commentsDivBody = document.getElementById("commentsDivId");
	for (var comListLen = 0; comListLen < issueDetailstemp.commentsList.length; comListLen++) {
		$("#commentsDivId").show();
		var divTag = document.createElement('div');
		divTag.className = 'profile-info-row';
		divTag.innerHTML = '<div class="profile-info-value"><a><span class="commentedPerName"><i class="ace-icon fa fa-user blue"></i> &nbsp;'
				+ issueDetailstemp.commentsList[comListLen].submittedByName
				+ '</span></a> added a comment - <span class="daysBeforeComment" style="color:rgb(188, 188, 188)">'
				+ getNumberOfdays(new Date(
						issueDetailstemp.commentsList[comListLen].submittedDate))
				+ '</span><div style="padding-top: 4px;padding-left: 12px;width:550px"><span class="editable" style="white-space:wrap">'
				+ issueDetailstemp.commentsList[comListLen].comment
				+ '</span></div></div>';
		commentsDivBody.appendChild(divTag);
	}

	var attachMentsDivBody = document
			.getElementById("issueRelatedAttachmentsDivId");
	for (var attachListLen = 0; attachListLen < issueDetailstemp.documentList.length; attachListLen++) {
		var divTag = document.createElement('p');
		divTag.innerHTML = '<a title = "Download Document" href="javascript:void(0))" onclick="setDocumentToDownload('
				+ issueDetailstemp.documentList[attachListLen].fileId
				+ ');">'
				+ issueDetailstemp.documentList[attachListLen].fileName
				/*+ "."
				+ issueDetailstemp.documentList[attachListLen].fileType  since the filename is containing the filename along with type*/
				+ '</a>&nbsp;&nbsp;&nbsp;<i class="ace-icon fa fa-times" title = "Delete Document" onclick="setDocumentToDelete('
				+ issueDetailstemp.documentList[attachListLen].fileId
				+ ');"></i>';
		attachMentsDivBody.appendChild(divTag);
	}

	var statusLogsDivBody = document
			.getElementById("statusLogsMainDivIdInIssueIndetails");
	for (var statusLogsLen = 0; statusLogsLen < issueDetailstemp.statusLogs.length; statusLogsLen++) {
		var divTag = document.createElement('div');
		divTag.className = 'profile-info-row';
		divTag.innerHTML = '<div class="profile-info-value"><a><span><i class="ace-icon fa fa-user blue"></i> &nbsp;'
				+ issueDetailstemp.statusLogs[statusLogsLen].updatedByName
				+ '</span></a> updated the status from <b><i>'
				+ issueDetailstemp.statusLogs[statusLogsLen].currentStatus.lookupName
				+ '</i></b>&nbsp; to <b><i>'
				+ issueDetailstemp.statusLogs[statusLogsLen].updatedStatus.lookupName
				+ '</i></b> - <span style="color:rgb(188, 188, 188)">'
				+ getNumberOfdays(new Date(
						issueDetailstemp.statusLogs[statusLogsLen].updatedDate))
				+ '</span><div style="padding-top: 4px;padding-left: 12px;width:260px"><span class="editable">'
				+ (issueDetailstemp.statusLogs[statusLogsLen].statusUpdateRelatedComment != undefined
						&& issueDetailstemp.statusLogs[statusLogsLen].statusUpdateRelatedComment != null ? issueDetailstemp.statusLogs[statusLogsLen].statusUpdateRelatedComment
						: "") + '</span></div></div>';
		statusLogsDivBody.appendChild(divTag);
	}

	if (issueDetailstemp.statusLogs != undefined
			&& issueDetailstemp.statusLogs.length == 0) {
		var divTag = document.createElement('div');
		divTag.innerHTML = '<p style="padding-left:5px">No status logs available for this Issue</p>';
		statusLogsDivBody.appendChild(divTag);
	}
}

// method for hide and show the buttons based on the selected issue status
function handleButtonsInIssuedetails(selIssueDetailsForShowHide) {

	if ((selIssueDetailsForShowHide.selectedIssue.status.lookupName)
			.toLowerCase() == "new"
			|| (selIssueDetailsForShowHide.selectedIssue.status.lookupName)
					.toLowerCase() == "Reopened") {
		$("#reopneButtonIdInIssueInDetails").hide();

		$("#cannotbeResldButtonIdInIssueInDetails").show();
		$("#awaitingCusResButtonIdInIssueInDetails").show();
		$("#editButtonIdInIssueInDetails").show();
		$("#resolveButtonIdInIssueInDetails").show();
		$("#closeButtonIdInIssueInDetails").show();
		$("#commentButtonIdInIssueInDetails").show();
		$("#commentButtonIdInCommentDivSection").show();
		$("#uploadAttachmentidInIssueDetails").show();

	} else if ((selIssueDetailsForShowHide.selectedIssue.status.lookupName)
			.toLowerCase() == "resolved") {
		$("#cannotbeResldButtonIdInIssueInDetails").hide();
		$("#awaitingCusResButtonIdInIssueInDetails").hide();
		$("#commentButtonIdInIssueInDetails").hide();
		$("#commentButtonIdInCommentDivSection").hide();
		$("#editButtonIdInIssueInDetails").hide();
		$("#resolveButtonIdInIssueInDetails").hide();
		$("#uploadAttachmentidInIssueDetails").hide();

		$("#reopneButtonIdInIssueInDetails").show();
		$("#closeButtonIdInIssueInDetails").show();
	} else if ((selIssueDetailsForShowHide.selectedIssue.status.lookupName)
			.toLowerCase() == "awating customer response") {
		$("#reopneButtonIdInIssueInDetails").hide();
		$("#awaitingCusResButtonIdInIssueInDetails").hide();

		$("#cannotbeResldButtonIdInIssueInDetails").show();
		$("#editButtonIdInIssueInDetails").show();
		$("#resolveButtonIdInIssueInDetails").show();
		$("#closeButtonIdInIssueInDetails").show();
		$("#commentButtonIdInIssueInDetails").show();
		$("#commentButtonIdInCommentDivSection").show();
		$("#uploadAttachmentidInIssueDetails").show();
	} else if ((selIssueDetailsForShowHide.selectedIssue.status.lookupName)
			.toLowerCase() == "cannot be resolved") {
		$("#reopneButtonIdInIssueInDetails").hide();
		$("#cannotbeResldButtonIdInIssueInDetails").hide();

		$("#awaitingCusResButtonIdInIssueInDetails").show();
		$("#editButtonIdInIssueInDetails").show();
		$("#resolveButtonIdInIssueInDetails").show();
		$("#closeButtonIdInIssueInDetails").show();
		$("#commentButtonIdInIssueInDetails").show();
		$("#commentButtonIdInCommentDivSection").show();
		$("#uploadAttachmentidInIssueDetails").show();
	} else if ((selIssueDetailsForShowHide.selectedIssue.status.lookupName)
			.toLowerCase() == "closed") {
		$("#reopneButtonIdInIssueInDetails").show();

		$("#cannotbeResldButtonIdInIssueInDetails").hide();
		$("#awaitingCusResButtonIdInIssueInDetails").hide();
		$("#editButtonIdInIssueInDetails").hide();
		$("#resolveButtonIdInIssueInDetails").hide();
		$("#closeButtonIdInIssueInDetails").hide();
		$("#commentButtonIdInIssueInDetails").hide();
		$("#commentButtonIdInCommentDivSection").hide();
		$("#uploadAttachmentidInIssueDetails").hide();
	} else {
		$("#reopneButtonIdInIssueInDetails").hide();

		$("#cannotbeResldButtonIdInIssueInDetails").show();
		$("#awaitingCusResButtonIdInIssueInDetails").show();
		$("#editButtonIdInIssueInDetails").show();
		$("#resolveButtonIdInIssueInDetails").show();
		$("#closeButtonIdInIssueInDetails").show();
		$("#commentButtonIdInIssueInDetails").show();
		$("#commentButtonIdInCommentDivSection").show();
		$("#uploadAttachmentidInIssueDetails").show();
	}
}

function getNumberOfdays(subDateTemp) {
	try {

		var curDate = new Date();
		var start = Math.floor(subDateTemp.getTime() / (3600 * 24 * 1000));
		var end = Math.floor(curDate.getTime() / (3600 * 24 * 1000));
		var daysDiff = end - start;
		if (daysDiff == 0) {
			return "Today";
		} else if (daysDiff == 1) {
			return "Yesterday";
		} else if (daysDiff > 30) {
			if (daysDiff < 60) {
				return getNumberOfMonthsAndDaysForOneMonth(daysDiff);
			} else {
				return getNumberOfMonthsAndDays(daysDiff / 30);
			}

		} else {
			return daysDiff + " days ago";
		}

	} catch (e) {
		return 0 + " days ago";
		;
	}
}

function getNumberOfMonthsAndDaysForOneMonth(monDiffTempForOneMon) {

	if (monDiffTempForOneMon == 30) {
		return "1 month ago";
	} else {
		var diffInString = (monDiffTempForOneMon / 30).toString();
		var splitedString = diffInString.split(".");
		var daysInNumber = parseInt(((parseFloat("0." + splitedString[1])) * 30));
		if (daysInNumber == 1) {
			return splitedString[0] + " month " + daysInNumber + " day ago";
		} else {
			return splitedString[0] + " month " + daysInNumber + " days ago";
		}

	}

}

function getNumberOfMonthsAndDays(monthsDiffTemp) {

	var diffInString = monthsDiffTemp.toString();
	var splitedString = diffInString.split(".");
	if (splitedString.length == 1) {
		return splitedString + " months ago";
	} else {
		var daysInNumber = Math
				.round(((parseFloat("0." + splitedString[1])) * 30));
		if (daysInNumber == 1) {
			return splitedString[0] + " months " + daysInNumber + " day ago";
		} else {
			return splitedString[0] + " months " + daysInNumber + " days ago";
		}
	}

}
function setIssueToEditFromIssueInDetails() {

	getLookUpDetailsToCreateIssue();
	$('button[data-id="applicationId"]').css('pointer-events','none');
	
	
	$('div.statusId').children().first().attr("style", "pointer-events: fill;");
	$("#createNewIssueHeaderId").text("Update Issue");
	$("#createIssueButtonSpanId").text("Update");
	$("#issueSno").val(selIssueDetails.selectedIssue.issueSno);
	if (selIssueDetails.selectedIssue.applicationDirectory != undefined
			&& selIssueDetails.selectedIssue.applicationDirectory != null
			&& selIssueDetails.selectedIssue.applicationDirectory != "") {
		$("#applicationId")
				.val(
						selIssueDetails.selectedIssue.applicationDirectory.applicationSno);
	} else {
		$("#applicationId").val("");
	}

	if (selIssueDetails.selectedIssue.applicationModuleDirectory != undefined
			&& selIssueDetails.selectedIssue.applicationModuleDirectory != null
			&& selIssueDetails.selectedIssue.applicationModuleDirectory != "") {
		$("#applicationModuleId")
				.val(
						selIssueDetails.selectedIssue.applicationModuleDirectory.moduleSno);
	} else {
		$("#applicationModuleId").val("");
	}

	if (selIssueDetails.selectedIssue.relatedProject != undefined
			&& selIssueDetails.selectedIssue.relatedProject != null
			&& selIssueDetails.selectedIssue.relatedProject != "") {
		$("#relatedProject").val(selIssueDetails.selectedIssue.relatedProject);
	} else {
		$("#relatedProject").val("");
	}

	if (selIssueDetails.selectedIssue.issueType != undefined
			&& selIssueDetails.selectedIssue.issueType != null
			&& selIssueDetails.selectedIssue.issueType != "") {
		$("#issueTypeId")
				.val(selIssueDetails.selectedIssue.issueType.lookupSno);
	} else {
		$("#issueTypeId").val("");
	}

	if (selIssueDetails.selectedIssue.status != undefined
			&& selIssueDetails.selectedIssue.status != null
			&& selIssueDetails.selectedIssue.status != "") {
		$("#statusId").val(selIssueDetails.selectedIssue.status.lookupSno);
	} else {
		$("#statusId").val("");
	}

	if (selIssueDetails.selectedIssue.severity != undefined
			&& selIssueDetails.selectedIssue.severity != null
			&& selIssueDetails.selectedIssue.severity != "") {
		$("#severityId").val(selIssueDetails.selectedIssue.severity.lookupSno);
	} else {
		$("#severityId").val("");
	}

	if (selIssueDetails.selectedIssue.startDate != undefined
			&& selIssueDetails.selectedIssue.startDate != null
			&& selIssueDetails.selectedIssue.startDate != "") {
		$("#startDate").val(
				moment(new Date(selIssueDetails.selectedIssue.startDate))
						.format("MM-DD-YYYY"));
	}

	if (selIssueDetails.selectedIssue.dueDate != undefined
			&& selIssueDetails.selectedIssue.dueDate != null
			&& selIssueDetails.selectedIssue.dueDate != "") {
		$("#dueDate").val(
				moment(new Date(selIssueDetails.selectedIssue.dueDate)).format(
						"MM-DD-YYYY"));
	}

	if (selIssueDetails.selectedIssue.summary != undefined
			&& selIssueDetails.selectedIssue.summary != null
			&& selIssueDetails.selectedIssue.summary != "") {
		$("#summary").val(selIssueDetails.selectedIssue.summary);
	}

	if (selIssueDetails.selectedIssue.estimatedTime != undefined
			&& selIssueDetails.selectedIssue.estimatedTime != null
			&& selIssueDetails.selectedIssue.estimatedTime != "") {
		$("#estimatedTime").val(selIssueDetails.selectedIssue.estimatedTime);
	}
	if (selIssueDetails.selectedIssue.percentageDoneId != undefined
			&& selIssueDetails.selectedIssue.percentageDoneId != null
			&& selIssueDetails.selectedIssue.percentageDoneId != "") {
		$("#percentageDoneId").val(
				selIssueDetails.selectedIssue.percentageDoneId);
	}
	if (selIssueDetails.selectedIssue.description != undefined
			&& selIssueDetails.selectedIssue.description != null
			&& selIssueDetails.selectedIssue.description != "") {
		$("#description").val(selIssueDetails.selectedIssue.description);
	}

	if (selIssueDetails.selectedIssue.assigneeProfile != undefined
			&& selIssueDetails.selectedIssue.assigneeProfile != null
			&& selIssueDetails.selectedIssue.assigneeProfile != "") {
		$("#asigneeId").val(
				selIssueDetails.selectedIssue.assigneeProfile.assigneeSno);
	} else {
		$("#asigneeId").val("");
	}
	$("#estimateAndPercentageDoneDivId").show();
	$("#assigneeDivId").show();
	$('#createIssue').modal('show');

	if ($("#statusId").val() != "8") // to check if the status is new or
	// not.... If new reopened status must
	// be closed and vice versa
	{
		$("#statusId option[value='13']").show();
		$("#statusId option[value='8']").hide();
	} else {

		$("#statusId option[value='13']").hide();
		$("#statusId option[value='8']").show();
	}
	// method to calculate and set due date based on selected severity and this
	// method is implemented in home.js
	setDueDatebasedOnSeverity();

	$(".selectpicker").selectpicker("refresh");
}

function setCommentToAddForSelectedIssue() {
	if ($("#commentTextArea").val() == "") {

		gritterWarningMsg("Please enter your comment.");
		$("#commentTextArea").focus();

	} else if ($("#commentTextArea").val().length > 500) {

		gritterWarningMsg("Max 500 characters are allowed");
		$("#commentTextArea").val('');
		return false;

	} else {

		var dataObj = {
			commentToSave : $("#commentTextArea").val()
		}
		$
				.ajax({
					url : "setCommentToAddForSelectedIssue",
					type : "POST",
					data : dataObj,
					success : function(e) {
						var addCommentsStatus = JSON.parse(e);
						$("#commentTextArea").val("");
						if (addCommentsStatus.ajaxresult != undefined
								&& addCommentsStatus.ajaxresult == "success") {
							gritterSuccess("Comment has been added successfully.");
							getSelectedIssuedetails();
						} else {
							gritterError("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
						}

					},
					error : function() {
						$("#commentTextArea").val("");
						gritterError("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
					}
				});
	}

}

function setIssueStatusToUpdate(typeOfAction) {

	var bootboxConfirmMsg = "";
	bootboxConfirmMsg = '<div class="space-4"></div><div class="widget-header"><h4 class="smaller">'
			+ ' Update Issue?</h4></div><div class="space-2"></div>'
			+ '<div id="dialog-confirm" class="ui-dialog-content ui-widget-content" style="width: auto; min-height: 28px; max-height: none; height: auto;">'
			+ '<div class="alert alert-info bigger-110">'
			+ 'This will make the issue as "<b>'
			+ typeOfAction
			+ '</b>"<br/><br/><div class="row"><div class="col-md-12"><div class="form-horizontal"><form> <div class="form-group" id="estmAndPerDoneDivIdOfStatusModel" style="display:none"><label class="col-sm-2 control-label "'
			+ 'for="form-field-1"> Estimate Time :</label>	<div class="col-md-4"><input type="text" class="form-control" id="estimatedTimeFromStatusModal" name="estimatedTimeFromStatusModal" placeholder="Estimate Time"></div>'
			+ '<label class="col-sm-2 control-label " for="form-field-1"> Percentage Done : </label><div class="col-md-4"> <input type="text" id="percentageDoneFromStatusModal" name="percentageDoneFromStatusModal" style="height: 34px !important;"/>	</div></div>'
			+ '<div class="form-group"><label class="col-sm-2 control-label "for="form-field-1">Comment<span style="color: #FF2600!important">*</span>:</label><div class="col-md-10"><textarea class="form-control" style="resize: none;" type="textarea" id="updateCommentTextAreaId" name="updateCommentTextAreaId" maxlength = 500 rows = "3" cols="60">'
			+ '</textarea><font color="red" size= 1px>(max: 500 chars)</font></div></div></form>'
			+ '</div></div></div>'
			+ '<p class="bigger-110 bolder center grey">'
			+ '<i class="ace-icon fa fa-hand-o-right blue bigger-120"></i>'
			+ ' Are you sure?' + '</p>	</div>';
	bootbox.confirm(bootboxConfirmMsg, function(result1) {
		if (result1) {
			if ($("#updateCommentTextAreaId").val() == "") {
				gritterWarningMsg("Please enter your comment.");
				$("#updateCommentTextAreaId").focus();
				return false;
			} else {
				ajaxCallToUpdateStatus(typeOfAction);
			}
		}
	});
	handlingFieldsInStatusModel(typeOfAction);
}

function handlingFieldsInStatusModel(statusTypeTemp) {

	$('#percentageDoneFromStatusModal').ace_spinner({
		value : 0,
		min : 0,
		max : 100,
		step : 10,
		btn_up_class : 'btn-info',
		btn_down_class : 'btn-info'
	}).closest('.ace-spinner').on('changed.fu.spinbox', function() {
	});

	// code for validation for allowing only numbers in estimated time of model
	// box. this code should be here to apply the validation
	$('input').on('keypress', function(e) {
		if (this.id == "estimatedTimeFromStatusModal") {// this is for number
			// validation
			if (e.which != 8 && e.which != 0 && e.which < 48) {
				gritterWarningMsg('Only numbers allowed.');
				return false;
			} else if (e.which > 57) {
				gritterWarningMsg('Only numbers allowed.');
				return false;
			}
		} else {
			if (e.which == 32 && (this.value == "" || this.value == "_"))
				return false;
		}
	});

	if (selIssueDetails.selectedIssue.estimatedTime != undefined
			&& selIssueDetails.selectedIssue.estimatedTime != null
			&& selIssueDetails.selectedIssue.estimatedTime != "") {
		$("#estimatedTimeFromStatusModal").val(
				selIssueDetails.selectedIssue.estimatedTime);
	} else {
		$("#estimatedTimeFromStatusModal").val(0);
	}
	if (selIssueDetails.selectedIssue.percentageDoneId != undefined
			&& selIssueDetails.selectedIssue.percentageDoneId != null
			&& selIssueDetails.selectedIssue.percentageDoneId != "") {
		$("#percentageDoneFromStatusModal").val(
				selIssueDetails.selectedIssue.percentageDoneId);
	} else {
		$("#percentageDoneFromStatusModal").val(0);
	}

	if (statusTypeTemp == "Resolve" || statusTypeTemp == "Cannot be Resolved") {
		$("#estmAndPerDoneDivIdOfStatusModel").attr("style", "display:block");
	}
}

function ajaxCallToUpdateStatus(typeOfAction) {
	var dataObj = {
		statusToUpdate : typeOfAction,
		commentToSave : $("#updateCommentTextAreaId").val(),
		estimatedTime : $("#estimatedTimeFromStatusModal").val(),
		percentageDone : $("#percentageDoneFromStatusModal").val()
	};

	$
			.ajax({
				url : "setIssueStatusToUpdate",
				type : "POST",
				data : dataObj,
				success : function(e) {
					var statusUpdateResult = JSON.parse(e);
					if (statusUpdateResult.ajaxresult != undefined
							&& statusUpdateResult.ajaxresult == "success") {
						gritterSuccess("Issue has been updated successfully.");
						getSelectedIssuedetails();
					} else {
						gritterError("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
					}

				},
				error : function() {
					gritterError("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
				}
			});
}

function setDocumentToDelete(docIdTempToDelete) {

	if (selIssueDetails.selectedIssue.status.lookupName == "Resolved"
			|| selIssueDetails.selectedIssue.status.lookupName == "Closed") {
		gritterWarningMsg("You Cannot update the <b><i>"
				+ selIssueDetails.selectedIssue.status.lookupName
				+ "</i></b> issue");
	} else {

		var bootboxConfirmMsg = "";
		bootboxConfirmMsg = '<div class="space-4"></div><div class="widget-header"><h4 class="smaller">'
				+ 'Delete Document?</h4></div><div class="space-2"></div>'
				+ '<div id="dialog-confirm" class="ui-dialog-content ui-widget-content" style="width: auto; min-height: 28px; max-height: none; height: auto;">'
				+ '<div class="alert alert-info bigger-110">'
				+ 'This will delete the document permanently.'
				+ '</div><div class="space-2"></div>'
				+ '<p class="bigger-110 bolder center grey">'
				+ '<i class="ace-icon fa fa-hand-o-right blue bigger-120"></i>'
				+ ' Are you sure?' + '</p>	</div>';
		bootbox.confirm(bootboxConfirmMsg, function(result1) {
			if (result1) {
				ajaxCallToDeleteSelDocument(docIdTempToDelete);
			}
		});

	}

}

function ajaxCallToDeleteSelDocument(docIdToDelete) {
	var dataObj = {
		docIdToDelete : docIdToDelete
	};
	$
			.ajax({
				url : "setIssueRelDocumentToDelete",
				type : "POST",
				data : dataObj,
				success : function(e) {
					var statusUpdateResult = JSON.parse(e);
					if (statusUpdateResult.ajaxresult != undefined
							&& statusUpdateResult.ajaxresult == "success") {
						gritterSuccess("Document has been deleted successfully.");
						getSelectedIssuedetails();
						getActivityStreamsListForIssue();
					} else {
						gritterError("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
					}

				},
				error : function() {
					gritterError("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
				}
			});
}
function setDocumentToDownload(docIdTempToDownload) {

	if (docIdTempToDownload != undefined && docIdTempToDownload != null
			&& docIdTempToDownload != "") {
		$("#documentIdToDownload").val(docIdTempToDownload);
		$("#hiddenFormForDocumentDownload").submit();
	}

}

function ajaxCallToUploadDocument(methodcalledFrom, attachmentFlag) {

	var data = new FormData($("#uploadFileFormId")[0]);

	if ($("#addionalNewFile").data().ace_input_method != "drop") {
		if (!validateUploadFileForm(methodcalledFrom)
				&& (methodcalledFrom != "createIssueModal")) {
			return;
		}
	} else {
		// var file_input=$("#uploadFileFormId").find('input[type=file]');//code
		// to append filename to formdata when a file is dropped at attachment
		// div
		var files = $("#addionalNewFile").data('ace_input_files');
		var totalFilesSize = 0;
		for (var len = 0; len < files.length; len++) {
			totalFilesSize = totalFilesSize + files[len].size;
		}
		if (totalFilesSize > 5 * Math.pow(2, 20)) {
			gritterWarningMsg("The attachement size cannot exceed 5MB.");
			var file_input = $('#addionalNewFile');
			file_input.ace_file_input('reset_input');
			return false;
		}
		if (files && files.length > 0) {// drag and drop multiple files

			for (var i = 0; i < files.length; i++) {
				data.append('attachmentToBeuploaded', files[i]);
			}
		} else {
			gritterWarningMsg("Please attach the file to upload.");
			var file_input = $('#addionalNewFile');
			file_input.ace_file_input('reset_input');
			return false;
		}
	}

	if ($("#uploadFile").data().ace_input_method == "drop") {
		var file_input = $("#createIssueFormId").find('input[type=file]'); // code
		// to
		// append
		// filename
		// to
		// formdata
		// when
		// a
		// file
		// is
		// dropped
		// at
		// createIssueModal
		// div
		var files = $("#uploadFile").data('ace_input_files');
		console.log(files);
		if (files && files.length > 0) {
			for (var i = 0; i < files.length; i++) { // drag and drop
				// multiple files
				data.append(file_input.attr('name'), files[i]);
			}
		} else {
			gritterWarningMsg("Please attach the file to upload.");
			return false;
		}
	}

	if (methodcalledFrom == "attachmentDiv") {
		$("#loadingModalDicId").modal("show");
	}

	$
			.ajax({
				url : "setIssueRelDocumentToUpload",
				type : "POST",
				data : data,
				cache : false,
				contentType : false,
				processData : false,
				success : function(result) {
					result = JSON.parse(result);
					$('#uploadFileModal').modal('hide');
					console.log("result.ajaxresult");
					if (result.ajaxresult == "success") {
						gritterSuccess("Document has been uploaded successfully.");
						if (attachmentFlag) {
							getSelectedIssuedetails();
							getActivityStreamsListForIssue();
						}
						console.log(methodcalledFrom);
						if (methodcalledFrom == "attachmentDiv") {
							$("#loadingModalDicId").modal("hide");
						}
						var file_input = $('#addionalNewFile');
						file_input.ace_file_input('reset_input');

					} else {
						if (methodcalledFrom != "createIssueModal") {

							$("#loadingModalDicId").modal("hide");
						}
						
						if(result.ajaxresult!="failure"){
							gritterError("Recent File/Files have been failed to upload as the size limit is getting exceeded.. Delete the exisiting files before you upload new set of files");
						}
						else{
							gritterError("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
						}
						
						var file_input = $('#addionalNewFile');
						file_input.ace_file_input('reset_input');
					}
				},
				error : function() {

					$('#uploadFileModal').modal('hide');
					if (methodcalledFrom != "createIssueModal") {

						$("#loadingModalDicId").modal("hide");
					}
					
					
					gritterError("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
					var file_input = $('#addionalNewFile');
					file_input.ace_file_input('reset_input');
				}
			});
}

function validateUploadFileForm(methodcalledFrom) {

	if (methodcalledFrom != "createIssueModal") {
		if ($("#addionalNewFile").val() == "") {
			gritterWarningMsg("Please attach the file to upload.");
			return false;
		} else {
			var totalFiles = $('#addionalNewFile')[0].files;
			var totalFilesSize = 0;
			for (var len = 0; len < totalFiles.length; len++) {
				totalFilesSize = totalFilesSize + totalFiles[len].size;
			}
			// validation for 5MB.
			if (totalFilesSize > 5 * Math.pow(2, 20)) {
				gritterWarningMsg("The attachements size cannot exceed 5MB.");
				var file_input = $('#addionalNewFile');
				console.log(file_input);
				file_input.ace_file_input('reset_input');
				return false;
			}
		}
	}

	return true;
}

function getActivityStreamsListForIssue() {

	$
			.ajax({
				url : "getActivityStreamsListForSelctedIssue",
				type : "POST",
				success : function(e) {
					var activitiesListForSelIssue = JSON.parse(e);
					if (activitiesListForSelIssue.ajaxresult != undefined
							&& activitiesListForSelIssue.ajaxresult == "success") {
						setToAddActivitiesToActivitySection(activitiesListForSelIssue);
					} else {
						gritterError("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
					}

				},
				error : function() {
					$("#commentTextArea").val("");
					gritterError("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
				}
			});

}

function setToAddActivitiesToActivitySection(activitiesForSelIssueTemp) {

	$("#activityStreamSectionDivId").empty();
	var activityLogsDivBody = document
			.getElementById("activityStreamSectionDivId");
	for (var activityLogsLen = 0; activityLogsLen < activitiesForSelIssueTemp.activityLogs.length; activityLogsLen++) {
		if ((activitiesForSelIssueTemp.activityLogs[activityLogsLen].activityType)
				.toLowerCase() == "created new ticket") {

			var divTag = document.createElement('div');
			divTag.className = 'profile-info-row';
			divTag.innerHTML = '<div class="profile-info-value"><a><span><i class="ace-icon fa fa-user blue"></i> &nbsp;'
					+ activitiesForSelIssueTemp.activityLogs[activityLogsLen].activityGeneratedByName
					+ '</span></a> created new ticket ( <a><i>'
					+ moment(
							activitiesForSelIssueTemp.activityLogs[activityLogsLen].activityGeneratedDate)
							.format("MM-DD-YYYY h:mm:ss a")
					+ '</i></a>&nbsp;)&nbsp; - <span style="color:rgb(188, 188, 188)">'
					+ getNumberOfdays(new Date(
							activitiesForSelIssueTemp.activityLogs[activityLogsLen].activityGeneratedDate))
					+ '</span><div style="padding-top: 4px;padding-left: 12px;width:550px;"><span class="editable">'
					+ (activitiesForSelIssueTemp.activityLogs[activityLogsLen].relatedIssueForActivity.description != undefined
							&& activitiesForSelIssueTemp.activityLogs[activityLogsLen].relatedIssueForActivity.description != null ? activitiesForSelIssueTemp.activityLogs[activityLogsLen].relatedIssueForActivity.description
							: "") + '</span></div></div>';
			activityLogsDivBody.appendChild(divTag);

		} else if ((activitiesForSelIssueTemp.activityLogs[activityLogsLen].activityType)
				.toLowerCase() == "updated ticket") {

			var divTag = document.createElement('div');
			divTag.className = 'profile-info-row';
			divTag.innerHTML = '<div class="profile-info-value"><a><span><i class="ace-icon fa fa-user blue"></i> &nbsp;'
					+ activitiesForSelIssueTemp.activityLogs[activityLogsLen].activityGeneratedByName
					+ '</span></a> updated the ticket ( <a><i>'
					+ moment(
							activitiesForSelIssueTemp.activityLogs[activityLogsLen].activityGeneratedDate)
							.format("MM-DD-YYYY h:mm:ss a")
					+ '</i></a>&nbsp;)&nbsp; - <span style="color:rgb(188, 188, 188)">'
					+ getNumberOfdays(new Date(
							activitiesForSelIssueTemp.activityLogs[activityLogsLen].activityGeneratedDate))
					+ '</span><div style="padding-top: 4px;padding-left: 12px;width:550px;"><span class="editable">'
					+ (activitiesForSelIssueTemp.activityLogs[activityLogsLen].relatedIssueForActivity.description != undefined
							&& activitiesForSelIssueTemp.activityLogs[activityLogsLen].relatedIssueForActivity.description != null ? activitiesForSelIssueTemp.activityLogs[activityLogsLen].relatedIssueForActivity.description
							: "") + '</span></div></div>';
			activityLogsDivBody.appendChild(divTag);

		} else if ((activitiesForSelIssueTemp.activityLogs[activityLogsLen].activityType)
				.toLowerCase() == "added comment") {

			var divTag = document.createElement('div');
			divTag.className = 'profile-info-row';
			divTag.innerHTML = '<div class="profile-info-value"><a><span><i class="ace-icon fa fa-user blue"></i> &nbsp;'
					+ activitiesForSelIssueTemp.activityLogs[activityLogsLen].activityGeneratedByName
					+ '</span></a> commented on ticket ( <a><i>'
					+ moment(
							activitiesForSelIssueTemp.activityLogs[activityLogsLen].activityGeneratedDate)
							.format("MM-DD-YYYY h:mm:ss a")
					+ '</i></a>&nbsp;)&nbsp; - <span style="color:rgb(188, 188, 188)">'
					+ getNumberOfdays(new Date(
							activitiesForSelIssueTemp.activityLogs[activityLogsLen].activityGeneratedDate))
					+ '</span><div style="padding-top: 4px;padding-left: 12px;width:550px;"><span class="editable">'
					+ (activitiesForSelIssueTemp.activityLogs[activityLogsLen].activitySummary != undefined
							&& activitiesForSelIssueTemp.activityLogs[activityLogsLen].activitySummary != null ? activitiesForSelIssueTemp.activityLogs[activityLogsLen].activitySummary
							: "") + '</span></div></div>';
			activityLogsDivBody.appendChild(divTag);

		} else if ((activitiesForSelIssueTemp.activityLogs[activityLogsLen].activityType)
				.toLowerCase() == "added attachment") {

			var divTag = document.createElement('div');
			divTag.className = 'profile-info-row';
			divTag.innerHTML = '<div class="profile-info-value"><a><span><i class="ace-icon fa fa-user blue"></i> &nbsp;'
					+ activitiesForSelIssueTemp.activityLogs[activityLogsLen].activityGeneratedByName
					+ '</span></a> added attachment for the ticket ( <a><i>'
					+ moment(
							activitiesForSelIssueTemp.activityLogs[activityLogsLen].activityGeneratedDate)
							.format("MM-DD-YYYY h:mm:ss a")
					+ '</i></a>&nbsp;)&nbsp; - <span style="color:rgb(188, 188, 188)">'
					+ getNumberOfdays(new Date(
							activitiesForSelIssueTemp.activityLogs[activityLogsLen].activityGeneratedDate))
					+ '</span><div style="padding-top: 4px;padding-left: 12px;width:550px;"><span class="editable">'
					+ (activitiesForSelIssueTemp.activityLogs[activityLogsLen].relatedIssueForActivity.description != undefined
							&& activitiesForSelIssueTemp.activityLogs[activityLogsLen].relatedIssueForActivity.description != null ? activitiesForSelIssueTemp.activityLogs[activityLogsLen].relatedIssueForActivity.description
							: "") + '</span></div></div>';
			activityLogsDivBody.appendChild(divTag);

		} else if ((activitiesForSelIssueTemp.activityLogs[activityLogsLen].activityType)
				.toLowerCase() == "deleted attachment") {

			var divTag = document.createElement('div');
			divTag.className = 'profile-info-row';
			divTag.innerHTML = '<div class="profile-info-value"><a><span><i class="ace-icon fa fa-user blue"></i> &nbsp;'
					+ activitiesForSelIssueTemp.activityLogs[activityLogsLen].activityGeneratedByName
					+ '</span></a> deleted attachment of  ticket ( <a><i>'
					+ moment(
							activitiesForSelIssueTemp.activityLogs[activityLogsLen].activityGeneratedDate)
							.format("MM-DD-YYYY h:mm:ss a")
					+ '</i></a>&nbsp;)&nbsp; - <span style="color:rgb(188, 188, 188)">'
					+ getNumberOfdays(new Date(
							activitiesForSelIssueTemp.activityLogs[activityLogsLen].activityGeneratedDate))
					+ '</span><div style="padding-top: 4px;padding-left: 12px;width:550px;"><span class="editable">'
					+ (activitiesForSelIssueTemp.activityLogs[activityLogsLen].relatedIssueForActivity.description != undefined
							&& activitiesForSelIssueTemp.activityLogs[activityLogsLen].relatedIssueForActivity.description != null ? activitiesForSelIssueTemp.activityLogs[activityLogsLen].relatedIssueForActivity.description
							: "") + '</span></div></div>';
			activityLogsDivBody.appendChild(divTag);

		} else if ((activitiesForSelIssueTemp.activityLogs[activityLogsLen].activityType)
				.toLowerCase() == "assigned") {

			var divTag = document.createElement('div');
			divTag.className = 'profile-info-row';
			divTag.innerHTML = '<div class="profile-info-value"><a><span><i class="ace-icon fa fa-user blue"></i> &nbsp;'
					+ activitiesForSelIssueTemp.activityLogs[activityLogsLen].activityGeneratedByName
					+ '</span></a> assigned the ticket to&nbsp;<a>'
					+ (activitiesForSelIssueTemp.activityLogs[activityLogsLen].newValue != undefined
							&& activitiesForSelIssueTemp.activityLogs[activityLogsLen].newValue != null ? activitiesForSelIssueTemp.activityLogs[activityLogsLen].newValue
							: "")
					+ '</a> ( <a><i>'
					+ moment(
							activitiesForSelIssueTemp.activityLogs[activityLogsLen].activityGeneratedDate)
							.format("MM-DD-YYYY h:mm:ss a")
					+ '</i></a>&nbsp;)&nbsp; - <span style="color:rgb(188, 188, 188)">'
					+ getNumberOfdays(new Date(
							activitiesForSelIssueTemp.activityLogs[activityLogsLen].activityGeneratedDate))
					+ '</span><div style="padding-top: 4px;padding-left: 12px;width:550px;"><span class="editable">'
					+ (activitiesForSelIssueTemp.activityLogs[activityLogsLen].relatedIssueForActivity.description != undefined
							&& activitiesForSelIssueTemp.activityLogs[activityLogsLen].relatedIssueForActivity.description != null ? activitiesForSelIssueTemp.activityLogs[activityLogsLen].relatedIssueForActivity.description
							: "") + '</span></div></div>';
			activityLogsDivBody.appendChild(divTag);

		} else if ((activitiesForSelIssueTemp.activityLogs[activityLogsLen].activityType)
				.toLowerCase() == "changed the assinee") {

			var divTag = document.createElement('div');
			divTag.className = 'profile-info-row';
			divTag.innerHTML = '<div class="profile-info-value"><a><span><i class="ace-icon fa fa-user blue"></i> &nbsp;'
					+ activitiesForSelIssueTemp.activityLogs[activityLogsLen].activityGeneratedByName
					+ '</span></a> changed assignee to&nbsp;<a>'
					+ (activitiesForSelIssueTemp.activityLogs[activityLogsLen].newValue != undefined
							&& activitiesForSelIssueTemp.activityLogs[activityLogsLen].newValue != null ? activitiesForSelIssueTemp.activityLogs[activityLogsLen].newValue
							: "")
					+ '</a> ( <a><i>'
					+ moment(
							activitiesForSelIssueTemp.activityLogs[activityLogsLen].activityGeneratedDate)
							.format("MM-DD-YYYY h:mm:ss a")
					+ '</i></a>&nbsp;)&nbsp; - <span style="color:rgb(188, 188, 188)">'
					+ getNumberOfdays(new Date(
							activitiesForSelIssueTemp.activityLogs[activityLogsLen].activityGeneratedDate))
					+ '</span><div style="padding-top: 4px;padding-left: 12px;width:550px;"><span class="editable">'
					+ (activitiesForSelIssueTemp.activityLogs[activityLogsLen].relatedIssueForActivity.description != undefined
							&& activitiesForSelIssueTemp.activityLogs[activityLogsLen].relatedIssueForActivity.description != null ? activitiesForSelIssueTemp.activityLogs[activityLogsLen].relatedIssueForActivity.description
							: "") + '</span></div></div>';
			activityLogsDivBody.appendChild(divTag);

		} else if ((activitiesForSelIssueTemp.activityLogs[activityLogsLen].activityType)
				.toLowerCase() == "status update") {

			var divTag = document.createElement('div');
			divTag.className = 'profile-info-row';
			divTag.innerHTML = '<div class="profile-info-value"><a><span><i class="ace-icon fa fa-user blue"></i> &nbsp;'
					+ activitiesForSelIssueTemp.activityLogs[activityLogsLen].activityGeneratedByName
					+ '</span></a> updated the status as&nbsp;<a>'
					+ activitiesForSelIssueTemp.activityLogs[activityLogsLen].newValue
					+ '</a> ( <a><i>'
					+ moment(
							activitiesForSelIssueTemp.activityLogs[activityLogsLen].activityGeneratedDate)
							.format("MM-DD-YYYY h:mm:ss a")
					+ '</i></a>&nbsp;)&nbsp; - <span style="color:rgb(188, 188, 188)">'
					+ getNumberOfdays(new Date(
							activitiesForSelIssueTemp.activityLogs[activityLogsLen].activityGeneratedDate))
					+ '</span><div style="padding-top: 4px;padding-left: 12px;width:550px;"><span class="editable">'
					+ (activitiesForSelIssueTemp.activityLogs[activityLogsLen].activitySummary != undefined
							&& activitiesForSelIssueTemp.activityLogs[activityLogsLen].activitySummary != null ? activitiesForSelIssueTemp.activityLogs[activityLogsLen].activitySummary
							: "") + '</span></div></div>';
			activityLogsDivBody.appendChild(divTag);

		}

	}

}
