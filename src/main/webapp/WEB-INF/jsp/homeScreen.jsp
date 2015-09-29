<!DOCTYPE html>
<html lang="en">

<head>
<link rel="stylesheet" href="assets/css/d3Style.css" />
<script src="assets/js/datatable/datatables.responsive.min.js"></script>
<script src="assets/js/datatable/jquery.dataTables.min.js"></script>
<script src="assets/js/dataTables.bootstrap.js"></script>
<script type="text/javascript">
	//initializing selectpicker
	$('.selectpicker').selectpicker();
</script>
<style>
.spanClass {
	top: 8px;
	right: 10px;
	z-index: 1
}

.changeWidth{
    width: 100%;
}
.custDisplay {
	display: none
}
/* start of css for issueDetail */
body>div.bootbox.modal.fade.bootbox-confirm.in>div>div>div.modal-footer>button.btn.btn-default
	{
	background: #abbac3 !important
}
/* start of css for scroller for comment div */
.widget-main::-webkit-scrollbar {
	height: 9px;
	width: 9px;
	background-color: white;
	border: 1px solid lightgrey;
}

.widget-main::-webkit-scrollbar-thumb {
	background-color: #DBDBDB;
}

.widget-main::-webkit-scrollbar-thumb:hover {
	background-color: #DBDBDB;
}

.widget-main::-webkit-scrollbar-thumb:active {
	background-color: #EEE;
}
/* end of css for scroller for comment div */
.previewDiv p {
	border: 1px solid #ccc;
	margin: 0 0 0px !important;
	padding: 4px 8px 3px 9px;
}

.previewDiv p i {
	float: right;
	cursor: pointer;
	color: #dd5a43
}

.previewDiv


 


p




:not


 


(
:last-child


 


){
border-bottom




:


 


none






}
.displayNone {
	display: none;
}

.padding0px {
	padding-left: 0px
}

;
.padding12px {
	padding-left: 12px
}
/* ends of css for issuedetail */
</style>
</head>

<body class="no-skin">
	<!-- <div class="page-header">
					<h1>
						Issue  <small> <i
							class="ace-icon fa fa-angle-double-right"></i> Search
						</small>
					</h1>
				</div> -->

	<div class="col-md-3" id="small">
		<div class="row well" style="padding: 9px !important;">
			<div class="row">
				<div class="col-md-12">
					<div class="form-group">
						<button style="margin-top: 7px; float: right;" type="button"
							class="btn  btn-sm btn-info"
							onclick="getIssueDetailsBasedOnSearchKeys();">
							Search <i class="fa fa-search"></i>
						</button>
						<div class="checkbox" style="float: right; padding-right: 13px">
							<label class="block"> <input name="form-field-checkbox"
								type="checkbox" class="ace input-lg" id="reportedByMeCheckbox"
								name="reportedByMeCheckbox"> <span
								class="lbl bigger-100"> Reported By Me</span>
							</label>
						</div>
					</div>
				</div>
			</div>
			<div class="timeline-item clearfix">
				<div class="widget-box transparent" style="margin-left: 0px;">
					<div class="widget-header widget-header-small">
						<h5 class="widget-title smaller">
							<a href="#" class="blue">Search</a> <span class="grey">
								Criteria</span>
						</h5>
						<span class="widget-toolbar onClick"> <a href="#"
							data-action="collapse"> <i class="ace-icon fa fa-chevron-up"></i>
						</a>
						</span>
					</div>

					<div class="widget-body" style="display: block;">
						<div class="widget-main"
							style="background: #fff; border-bottom: 1px solid rgb(220, 235, 247); border-right: 1px solid rgb(220, 235, 247)">
							<div class="form-horizontal">

								<div class="form-group" >
									<label class="col-md-3 control-label no-padding-right"
										for="form-field-1"> Application</label>
									<div class="col-md-8" >
										<select 
											class="form-control selectpicker show-tick applicationToSearch"
											multiple id="applicationToSearch"  name="applicationToSearch"
											onchange="getModuleListForSelectedAppsIssueSearch();" tabindex="-1">
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right"
										for="form-field-1"> Issues </label>
									<div class="col-md-8">
										<select class="form-control selectpicker show-tick"
											id="issuesToSearch" name="issuesToSearch">
											<option value="all" selected="selected">All</option>
											<option value="unresolved">Unresolved</option>
											<option value="reportedByMe">Reported By Me</option>
											<option value="assignedToMe">Assigned To Me</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right"
										for="form-field-1"> Time </label>
									<div class="col-md-8" id="divClassChange">
										<select class="form-control selectpicker show-tick"
											id="timeToSearch" name="timeToSearch">
											<option value="entire">Entire Duration</option>
											<option value="30">Last 30 days</option>
											<option value="90">Last 90 days</option>
											<option value="other">Other</option>
										</select>

									</div>
									<div class="col-md-2 hide" id="timeEnterDiv"
										style="padding-left: 0px">
										<input class="form-control input-mask-phone" type="text"
											style="border-radius: 4px !important" id="daysToSearch"
											name="daysToSearch" placeholder="Enter Time(in days)">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="timeline-item clearfix">
				<div class="widget-box transparent collapsed"
					style="margin-left: 0px;">
					<div class="widget-header widget-header-small">
						<h5 class="widget-title smaller">
							<a href="#" class="blue">Advanced Search</a> <span class="grey">
								Criteria</span>
						</h5>
						<span class="widget-toolbar onClick"> <a href="#"
							data-action="collapse" id="issueSearchOtherSearchAnchorId"> <i
								class="ace-icon fa fa-chevron-down"></i>
						</a>
						</span>
					</div>

					<div class="widget-body" style="display: none;">
						<div class="widget-main"
							style="background: #fff; border-bottom: 1px solid rgb(220, 235, 247); border-right: 1px solid rgb(220, 235, 247)">
							<div class="form-horizontal">

								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right"
										for="form-field-1"> Severity </label>
									<div class="col-md-8">
										<select class="form-control selectpicker show-tick"
											id="issuePriorityToSearch" name="issuePriorityToSearch"
											multiple>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right"
										for="form-field-1"> Status </label>
									<div class="col-md-8">
										<select class="form-control selectpicker show-tick"
											id="statusToSearch" name="statusToSearch" multiple>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right"
										for="form-field-1"> Module </label>
									<div class="col-md-8">
										<select class="form-control selectpicker show-tick"
											id="moduleToSearch" name="moduleToSearch" multiple>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right"
										for="form-field-1"> Issue Type </label>
									<div class="col-md-8">
										<select class="form-control selectpicker show-tick"
											id="issueTypeToSearch" name="issueTypeToSearch" multiple>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right"
										for="form-field-1"> Assignee </label>
									<div class="col-md-8">
										<select class="form-control selectpicker show-tick"
											id="assigneeToSearch" name="assigneeToSearch" multiple>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right"
										for="form-field-1"> Issue No </label>
									<div class="col-md-8">
										<input class="form-control input-mask-phone" type="text"
											id="issueNoToSearch" name="issueNoToSearch"
											placeholder="Issue #">
									</div>

								</div>


							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="col-md-9" id="big">

		<div class="tabbable">
			<ul class="nav nav-tabs padding-12 tab-color-blue background-blue"
				id="tabs">
				<li class="active"><a href="#tab1" data-toggle="tab"
					id="allIssueAnchorId" onclick="getIssueDetailsBasedOnSearchKeys();">&nbsp;&nbsp;
						All &nbsp;&nbsp;</a></li>
				<div class="widget-toolbar" style="line-height: 0px;">
					<a id="expandCollapse"><i class="ace-icon fa fa-expand"></i></a>
				</div>
			</ul>

			<div class="tab-content" style="">
				<div class="tab-pane active" id="tab1">
					<div class="table-header">Search Results</div>
					<div>
						<table id="issueSearchResultTable" style="width:100%"
							class="center table table-striped table-bordered table-hover changeWidth">
							<thead>
								<tr>
									<th class="center">Issue Type</th>
									<th class="center">Key</th>
									<th class="center">Summary</th>
									<th class="center">Severity</th>
									<th class="center">Status</th>
									<th class="center">Assignee</th>
									<th class="center">Reported Date</th>
									<th class="center">Due Date</th>
									<th style="display:none"></th>
								</tr>
							</thead>

							<tbody>

							</tbody>
						</table>
					</div>
				</div>
				<div class="tab-pane" id="issueDetailFields">
					<div class="page-header"
						style="padding: 0px 0px 5px 0px; margin-bottom: 5px;">
						<h3 style="margin: 0px;">
							Issue <small> <i
								class="ace-icon fa fa-angle-double-right"></i>
							</small><span id="issueHearderSpanId"
								style="font-size: 20px; font-weight: 500; color: rgb(2, 2, 2);">
							</span>
						</h3>
					</div>
					<div class="row">
						<div class="col-lg-12">
							<div class="btn-group">
								<p>
									<button type="button"
										onclick="setIssueToEditFromIssueInDetails()"
										class="btn  btn-primary btn-sm"
										id="editButtonIdInIssueInDetails" style="display: none">
										<i class="ace-icon fa fa-edit bigger-110"></i> Edit
									</button>
									<!-- <button type="button"
										class="btn  btn-primary btn-sm commentInBtnGroup"
										id="commentButtonIdInIssueInDetails" style="display:none">
										<i class="ace-icon fa fa-comment bigger-110"></i> Comment
									</button> -->
								</p>
							</div>
							<div class="btn-group">
								<p>
									<button type="button" class="btn  btn-info btn-sm"
										onclick="setIssueStatusToUpdate('Resolve');"
										id="resolveButtonIdInIssueInDetails" style="display: none">
										<i class="ace-icon fa fa-check-square bigger-110"></i> Resolve
									</button>
									<button type="button" class="btn  btn-info btn-sm"
										onclick="setIssueStatusToUpdate('Awating Customer Response');"
										id="awaitingCusResButtonIdInIssueInDetails"
										style="display: none">
										<i class="ace-icon fa fa-clock-o bigger-110"></i> Awaiting
										Customer Response
									</button>
									<button type="button" class="btn  btn-info btn-sm"
										onclick="setIssueStatusToUpdate('Cannot be Resolved');"
										id="cannotbeResldButtonIdInIssueInDetails"
										style="display: none">
										<i class="ace-icon fa fa-plus-square bigger-110"
											style="-ms-transform: rotate(45deg); -webkit-transform: rotate(45deg); transform: rotate(45deg);"></i>
										Cannot be Resolved
									</button>
									<button type="button" class="btn  btn-info btn-sm"
										onclick="setIssueStatusToUpdate('Closed');"
										id="closeButtonIdInIssueInDetails" style="display: none">
										<i class="ace-icon fa fa-times bigger-110"></i> Close
									</button>
									<button type="button" class="btn  btn-info btn-sm"
										onclick="setIssueStatusToUpdate('Reopened');"
										id="reopneButtonIdInIssueInDetails" style="display: none">
										<i class="ace-icon fa fa-undo bigger-110"></i> Reopen
									</button>
								</p>
							</div>
						</div>

						<div class="col-lg-8">
							<div class="timeline-item clearfix">
								<div class="widget-box transparent" style="margin-left: 0px;">
									<div class="widget-header widget-header-small">
										<h5 class="widget-title smaller">
											<a href="#" class="blue">Detail</a> <span class="grey">of
												a selected Issue</span>
										</h5>
										<span class="widget-toolbar"> <a href="#"
											data-action="collapse"> <i
												class="ace-icon fa fa-chevron-up"></i>
										</a>
										</span>
									</div>

									<div class="widget-body" style="display: block;">
										<div class="widget-main"
											style="background: #fff; border-bottom: 1px solid rgb(220, 235, 247); border-right: 1px solid rgb(220, 235, 247)">
											<div class="row">
												<div class="col-md-6">
													<div class="profile-user-info profile-user-info-striped">
														<div class="profile-info-row">
															<div class="profile-info-name">
																<b>Application</b>
															</div>
															<div class="profile-info-value">
																<span class="editable" id="applicationSpanId"></span>
															</div>
														</div>
														<div class="profile-info-row">
															<div class="profile-info-name">
																<b>Type</b>
															</div>
															<div class="profile-info-value">
																<span class="editable" id="issueTypeSpanId"></span>
															</div>
														</div>
														<div class="profile-info-row">
															<div class="profile-info-name">
																<b>Severity</b>
															</div>
															<div class="profile-info-value">
																<span class="editable" id="issuePrioritySpanId"></span>
															</div>
														</div>
														<!-- <div class="profile-info-row">
											<div class="profile-info-name"><b>Labels</b></div>
											<div class="profile-info-value">
												<span class="editable" id="age">Edit , UI issue</span>
											</div>
										</div> -->
													</div>
												</div>

												<div class="col-md-6">
													<div class="profile-user-info profile-user-info-striped">
														<div class="profile-info-row">
															<div class="profile-info-name">
																<b>Project</b>
															</div>
															<div class="profile-info-value">
																<span class="editable" id="projectSpanId"></span>
															</div>
														</div>
														<div class="profile-info-row">
															<div class="profile-info-name">
																<b>Module</b>
															</div>
															<div class="profile-info-value">
																<span class="editable" id="moduleSpanId"></span>
															</div>
														</div>
														<div class="profile-info-row">
															<div class="profile-info-name">
																<b>Status</b>
															</div>
															<div class="profile-info-value">
																<span class="editable" id="issueStatusSpanId"></span>
															</div>
														</div>
														<!-- <div class="profile-info-row">
											<div class="profile-info-name"><b>Resolution</b></div>
											<div class="profile-info-value">
												<span class="editable" id="issueResolutionSpanId">Unresolved</span>
											</div>
										</div> -->
													</div>
												</div>
												<div class="col-md-12" style="position: relative; top: 6px;">
													<div class="profile-user-info profile-user-info-striped">
														<div class="profile-info-row">
															<div class="profile-info-name">
																<b>Description</b>
															</div>
															<div class="profile-info-value"
																style="padding: 0px !important">
																<textarea class="form-control"
																	style="resize: none; background: #fff; cursor: text; border: none !important"
																	rows="4" id="descriptionTextArea"
																	name="descriptionTextArea"
																	placeholder="Description  area" disabled></textarea>
															</div>
														</div>
													</div>
												</div>
												<div class="col-md-12"
													style="position: relative; top: 12px;">
													<div class="profile-user-info profile-user-info-striped">
														<div class="profile-info-row">
															<div class="profile-info-name">
																<b>Attachment</b>
															</div>
															<div class="profile-info-value previewDiv"
																id="issueRelatedAttachmentsDivId"></div>
															<i class="ace-icon fa fa-plus bigger-110"
																href="#uploadFileModal" data-toggle="modal"
																style="cursor: pointer; float: right; color: rgb(209, 91, 71); padding: 9px; background: rgb(237, 243, 244);"
																title="Upload Document"
																id="uploadAttachmentidInIssueDetails"></i>
														</div>
													</div>
												</div>
											</div>

											<div class="space-6"></div>
											<!-- <div class="widget-toolbox clearfix">
												<div class="pull-left">
													<i class="ace-icon fa fa-hand-o-right grey bigger-125"></i>
													<a href="#" class="bigger-110">Click to read </a>
												</div>

												<div class="pull-right action-buttons">
													<a href="#">
														<i class="ace-icon fa fa-check green bigger-130"></i>
													</a>

													<a href="#">
														<i class="ace-icon fa fa-pencil blue bigger-125"></i>
													</a>

													<a href="#">
														<i class="ace-icon fa fa-times red bigger-125"></i>
													</a>
												</div>
											</div> -->
										</div>
									</div>
								</div>
							</div>

							<div class="tabbable">
								<ul
									class="nav nav-tabs padding-12 tab-color-blue background-blue"
									id="myTab4">
									<li class="active"><a data-toggle="tab" href="#comment">Comment</a></li>
									<li class=""><a data-toggle="tab" href="#activityStream"
										onclick="getActivityStreamsListForIssue();">Activity
											Stream</a></li>

								</ul>

								<div class="tab-content">
									<div id="comment" class="tab-pane active">
										<div class="row">
											<div class="widget-body" style="display: block;">
												<div class="widget-main"
													style="background: #fff; border-bottom: 1px solid rgb(220, 235, 247); border-right: 1px solid rgb(220, 235, 247); overflow-y: scroll; overflow-x: hidden; max-height: 398px;">
													<div class="row">
														<div class="col-md-12">
															<div
																class="profile-user-info profile-user-info-striped custFind"
																id="commentsDivId"></div>

														</div>
													</div>
												</div>

												<div class="widget-body" style="display: block;">
													<div class="widget-main">
														<div class="row">
															<div class="col-md-12">
																<div class="col-md-12">
																	<button type="button"
																		class="btn  btn-sm btn-info commentBtn"
																		id="commentButtonIdInCommentDivSection"
																		style="display: none">
																		<i class="ace-icon fa fa-comment bigger-110"></i>
																		Comment
																	</button>
																</div>
															</div>

															<div class="col-md-12">
																<div class="profile-user-info commentField hide"
																	style="position: relative; top: 10px">
																	<textarea class="form-control" style="resize: none"
																		rows="4" id="commentTextArea" name=""
																		placeholder="Add Comment"></textarea>
																	<div class="space-6"></div>
																	<div class="btn-group">
																		<p>
																			<button type="button" class="btn  btn-sm btn-primary"
																				onclick="setCommentToAddForSelectedIssue();">
																				<i class="ace-icon fa fa-plus"></i> Add
																			</button>
																			<button type="button"
																				class="btn  btn-sm btn-primary hideCommentFieldBtn">
																				<i class="ace-icon fa fa-times"></i> Cancel
																			</button>
																		</p>
																	</div>
																	<div class="space-6"></div>
																</div>
															</div>
														</div>
													</div>
												</div>

											</div>
										</div>
									</div>
									<div id="activityStream" class="tab-pane"
										style="overflow-y: scroll; overflow-x: hidden; max-height: 398px;">
										<div class="row">
											<div class="col-md-12" id="activityStreamSectionDivId">
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="col-lg-4">
							<div class="">
								<div class="timeline-item clearfix">
									<div class="widget-box transparent" style="margin-left: 0px;">
										<div class="widget-header widget-header-small">
											<h5 class="widget-title smaller">
												<a href="#" class="blue">People</a> <span class="grey">
													related to selected Issue</span>
											</h5>
											<span class="widget-toolbar"> <a href="#"
												data-action="collapse"> <i
													class="ace-icon fa fa-chevron-up"></i>
											</a>
											</span>
										</div>

										<div class="widget-body" style="display: block;">
											<div class="widget-main"
												style="background: #fff; border-bottom: 1px solid rgb(220, 235, 247); border-right: 1px solid rgb(220, 235, 247)">
												<div class="row">
													<div class="col-md-12">
														<div class="profile-user-info profile-user-info-striped">
														<div class="profile-info-row">
																<div class="profile-info-name">
																	<b>Reporter</b>
																</div>
																<div class="profile-info-value">
																	<span class="editable" id="reportedPersonId"></span>
																</div>
															</div>
															<div class="profile-info-row">
																<div class="profile-info-name">
																	<b>Assignee</b>
																</div>
																<div class="profile-info-value">
																	<span class="editable" id="assigneePersonId"></span>
																</div>
															</div>
															
														</div>
													</div>
												</div>

												<div class="space-6"></div>
											</div>
										</div>
									</div>
								</div>
								<div class="timeline-item clearfix">
									<div class="widget-box transparent" style="margin-left: 0px;">
										<div class="widget-header widget-header-small">
											<h5 class="widget-title smaller">
												<a href="#" class="blue">Date</a> <span class="grey">related
													to selected Issue</span>
											</h5>
											<span class="widget-toolbar"> <a href="#"
												data-action="collapse"> <i
													class="ace-icon fa fa-chevron-up"></i>
											</a>
											</span>
										</div>

										<div class="widget-body" style="display: block;">
											<div class="widget-main"
												style="background: #fff; border-bottom: 1px solid rgb(220, 235, 247); border-right: 1px solid rgb(220, 235, 247)">
												<div class="row">
													<div class="col-md-12">
														<div class="profile-user-info profile-user-info-striped">
															<div class="profile-info-row">
																<div class="profile-info-name">
																	<b>Created</b>
																</div>
																<div class="profile-info-value">
																	<span class="editable" id="createdDateId"></span>
																</div>
															</div>
															<div class="profile-info-row">
																<div class="profile-info-name">
																	<b>Updated</b>
																</div>
																<div class="profile-info-value">
																	<span class="editable" id="updatedDateId"></span>
																</div>
															</div>
															<div class="profile-info-row">
																<div class="profile-info-name">
																	<b>Due On</b>
																</div>
																<div class="profile-info-value">
																	<span class="editable" id="dueDateId"></span>
																</div>
															</div>
														</div>
													</div>
												</div>

												<div class="space-6"></div>
											</div>
										</div>
									</div>
								</div>
								<div class="timeline-item clearfix">
									<div class="widget-box transparent" style="margin-left: 0px;">
										<div class="widget-header widget-header-small">
											<h5 class="widget-title smaller">
												<a href="#" class="blue">Status</a> <span class="grey">logs
													for selected Issue</span>
											</h5>
											<span class="widget-toolbar"> <a href="#"
												data-action="collapse"> <i
													class="ace-icon fa fa-chevron-up"></i>
											</a>
											</span>
										</div>

										<div class="widget-body" style="display: block;">
											<div class="widget-main"
												style="border-bottom: 1px solid rgb(220, 235, 247); overflow-y: scroll; overflow-x: hidden; max-height: 296px;">
												<div class="row" id="statusLogsMainDivIdInIssueIndetails">

												</div>

												<div class="space-6"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>


			</div>
			<!-- <a href="#" class="btnAddTab"><i class="icon-plus-sign-alt"></i> Add Tab</a> -->
		</div>


	</div>
	<!-- hidden form for selecting issue in details starts -->
	<div style="display: none">
		<form id="issueInDetailsForm" name="issueInDetailsForm" method="POST"
			action="getIssueDetails">
			<input type="hidden" name="issueIdToGetDetails"
				id="issueIdToGetDetails">
		</form>
	</div>
	<!-- hidden form for selecting issue in details ends -->

	<!-- modla space start -->
	<div id="uploadFileModal" class="modal fade" tabindex="-1"
		aria-hidden="false" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" style="background: rgb(239, 243, 248);">
					<button type="button" class="close" data-dismiss="modal"
						style="opacity: 3 !important;">
						<span class="badge pull-right"
							style="position: relative; top: 8px;">×</span>
					</button>
					<h5 class="blue bigger" id="">
						<i class="fa fa-upload fa-fw txt-color-blue"></i> Upload New File
					</h5>
				</div>

				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<div class="form-horizontal">
								<form id="uploadFileFormId" name="uploadFileFormId"
									method="POST">
									<div class="form-group">
										<label class="col-sm-2 control-label " for="form-field-1">
											Attachment</label>
										<div class="col-xs-10">
											<label class="ace-file-input ace-file-multiple"><input
												multiple="" type="file" id="addionalNewFile"
												name="addionalNewFile" ><font color="red" size=1px>(max
													size: 5 MB)</font> <a class="remove" href="#"><i
													class=" ace-icon fa fa-times"></i></a></label>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-sm" data-dismiss="modal">
						<i class="ace-icon fa fa-times"></i> Cancel
					</button>
					<button class="btn btn-sm btn-success"
						onclick="ajaxCallToUploadDocument('attachmentDiv',true);">
						<i class="ace-icon fa fa-upload"></i> <span>Upload</span>
					</button>
				</div>
			</div>
		</div>
	</div>

	<!-- modal space end-->
	<!-- hidden form for downloading the document starts-->
	<form action="setRelatedDocumentToDowload"
		id="hiddenFormForDocumentDownload"
		name="hiddenFormForDocumentDownload" style="display: none">
		<input type="hidden" name="documentIdToDownload"
			id="documentIdToDownload" />
	</form>
	<!-- hidden form for downloading the document ends-->

	<!-- Respective jsp js dependencies  -->
	<script src="assets/js/homeScreen.js"></script>
	<script>
		/* js for making showing time textbox in TIME dropdown START */
		 document.getElementById('issueSearchResultTable').style.width="100%"; //setting the alignment of the table in correct position
		$("#timeToSearch").change(function() {
			val = $(this).val();
			switch (val) {

			case 'other':
				$('#timeEnterDiv').removeClass('hide');
				$('#divClassChange').toggleClass('col-md-6 col-md-8');
				break;

			default:
				$('#timeEnterDiv').addClass('hide');
				$('#divClassChange').removeClass('col-md-6');
				$('#divClassChange').addClass('col-md-8');
			}
		});
		/* END */

		//making active tab
		$('#dashboard').removeClass('activeTab');
		$('#homeScreen').addClass('activeTab');

		//code for validation for allowing only numbers in input boxes
		$('input').on('keypress', function(e) {
			if (this.id == "daysToSearch" || this.id == "issueNoToSearch") {//this is for number validation
				if (e.which != 8 && e.which != 0 && e.which < 48) {
					gritterError('Only numbers allowed.');
					return false;
				} else if (e.which > 57) {
					gritterError('Only numbers allowed.');
					return false;
				}
			} else {
				if (e.which == 32 && (this.value == "" || this.value == "_"))
					return false;
			}
		});

		/* $('.onClick').on('click',function(){
			if($($(this).parent('.widget-header').next().css('display') == 'block'))
				{
				$(this).parents('.timeline-item').siblings().find('.widget-body').css('display','block');
				}
			else
				{
				$(this).parents('.timeline-item').siblings().find('.widget-body').css('display','none');
				}
		}) */
	</script>



	<script>
		function setMethodToDecideCreateTab(valueTocheck) {

			if (validateIssueTabs(valueTocheck)) {
				getSelectedIssuedetails(valueTocheck,
						"setMethodToDecideCreateTab");
			}

		}

		function newTabCreationMethod(valueTocheck) {

			var tabText = "ISSUE#" + valueTocheck;
			var allTabs = $('#tabs > li > a').text();

			if (allTabs.indexOf(tabText) == -1) {
				var nextTab = $('#tabs li').size() + 1;

				// create the tab
				//$('<li><a href="#tab'+nextTab+'" data-toggle="tab">Tab '+nextTab+'&nbsp;&nbsp;<span>x</span></a></li>').appendTo('#tabs');
				$(
						'<li><a href="#issueDetailFields" data-toggle="tab" onclick="getSelectedIssuedetails('
								+ valueTocheck
								+ ')">'
								+ tabText
								+ '&nbsp;&nbsp;</a><span style="cursor:pointer;position:absolute;top:8px;right:11px;z-index:1" class="spanClass">x</span></li>')
						.appendTo('#tabs');

				// create the tab content
				//$('<div class="tab-pane dyanamicContent" id="tab'+nextTab+'"></div>').appendTo('.tab-content');
				//$('.issueDetailFields').css('display','block');
				//$('.issueDetailFields').appendTo('.dyanamicContent');
				//$('.issueDetailFields').appendTo('#tab'+nextTab+'');
				$('.issueDetailFields').appendTo('#tab' + nextTab + '');
				$("li").hover(function() {
					$(this).find('span').css("color", "pink");
				}, function() {
					$("li span").css("color", "#4c718a");
				});

				// make the new tab active
				$('#tabs  a:last').tab('show');
			}
		}
		function validateIssueTabs(valueTocheck) {

			if ($('#tabs li').length <= 8) {
				var tabText = "ISSUE#" + valueTocheck;
				var allTabs = $('#tabs > li > a').text();

				if (allTabs.indexOf(tabText) !== -1) {

					$.gritter.add({
						title : '<b>Issue Tracker : Info !!</b>',
						text : '<b style="color:#EEBD82">' + tabText + '</b>'
								+ '&nbsp; is already opened',
						sticky : false,
						time : '',
						class_name : 'gritter-info'
					});
					return false;
				}

				if ($('#tabs li').length == 8) {
					//$('#tabs li:nth-child(3)').remove();

					$.gritter
							.add({
								title : '<b>Issue Tracker : Info !!</b>',
								text : 'Can not open more than <b style="color:#EEBD82">Seven</b> Issues at a time',
								sticky : false,
								time : '',
								class_name : 'gritter-info'
							});
					return false;

				}
			}

			return true;
		}

		$("#tabs").on("click", "span", function(event) {
			event.preventDefault();
			$(this).parents('li').remove();
			$(".nav-tabs li").children('a').first().click();
		});
	</script>

	<script>
		$('.commentBtn').on('click', function() {
			$('.commentField').toggleClass('hide', '');
			$('.commentBtn').toggleClass('hide', '');
		});
		$('.commentInBtnGroup').on('click', function() {
			if ($('.commentField').hasClass('hide')) {
				$('.commentField').toggleClass('hide', '');
				$('.commentBtn').toggleClass('hide', '');
				$('#myTab4 li').first().addClass('active');
				$('#myTab4 li').first().next().removeClass('active');
				$('#comment').addClass('active');
				$('#activityStream').removeClass('active');
				$("body").scrollTop(700);
			} else {
			}
		});
		$('.hideCommentFieldBtn').on('click', function() {
			$('.commentField').toggleClass('hide', '');
			$('.commentBtn').toggleClass('hide', '');
		});

		// UploadFile script starts here	
		$('#addionalNewFile').ace_file_input({
			style : 'well',
			btn_choose : 'Drop files here or click to choose',
			btn_change : null,
			no_icon : 'ace-icon fa fa-cloud-upload',
			droppable : true,
			thumbnail : 'small'//large | fit
			//,icon_remove:null//set null, to hide remove/reset button
			/**,before_change:function(files, dropped) {
				//Check an example below
				//or examples/file-upload.html
				return true;
			}*/
			/**,before_remove : function() {
				return true;
			}*/
			,
			preview_error : function(filename, error_code) {
				//name of the file that failed
				//error_code values
				//1 = 'FILE_LOAD_FAILED',
				//2 = 'IMAGE_LOAD_FAILED',
				//3 = 'THUMBNAIL_FAILED'
			}

		}).on('change', function() {
			
		});

		//$('#addionalNewFile')
		//.ace_file_input('show_file_list', [
		//{type: 'image', name: 'name of image', path: 'http://path/to/image/for/preview'},
		//{type: 'file', name: 'hello.txt'}
		//]);

		//dynamically change allowed formats by changing allowExt && allowMime function
		$('#id-file-format').removeAttr('checked').on(
				'change',
				function() {
					var whitelist_ext, whitelist_mime;
					var btn_choose
					var no_icon
					if (this.checked) {
						btn_choose = "Drop images here or click to choose";
						no_icon = "ace-icon fa fa-picture-o";

						whitelist_ext = [ "jpeg", "jpg", "png", "gif", "bmp" ];
						whitelist_mime = [ "image/jpg", "image/jpeg",
								"image/png", "image/gif", "image/bmp" ];
					} else {
						btn_choose = "Drop files here or click to choose";
						no_icon = "ace-icon fa fa-cloud-upload";

						whitelist_ext = null;
						whitelist_mime = null;
					}
					var file_input = $('#addionalNewFile');
					file_input.ace_file_input('update_settings', {
						'btn_choose' : btn_choose,
						'no_icon' : no_icon,
						droppable:true,
						'allowExt' : whitelist_ext,
						'allowMime' : whitelist_mime
					})
					file_input.ace_file_input('reset_input');

					file_input.off('file.error.ace').on('file.error.ace',
							function(e, info) {

							});

				});
		//ENDS

		//STARTS collapsing and expanding div
		$('#expandCollapse').on('click', function() {
			$("#small").toggleClass("displayNone col-md-3");
			$("#issueSearchResultTable").toggleClass("changeWidth");
			 document.getElementById('issueSearchResultTable').style.width="100%";
			$("#big").toggleClass("col-md-12 col-md-9");
			$("#big").toggleClass("padding12px padding0px");
			
			$("#expandCollapse i").toggleClass("fa-compress fa-expand");
		});
		//ENDS
	</script>
	<script type="text/javascript" src="assets/js/issueInDetails.js"></script>

</body>

</html>
