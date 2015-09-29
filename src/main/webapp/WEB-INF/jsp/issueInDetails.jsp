<!-- <!DOCTYPE html>
<html lang="en">
<head>

<script src="assets/js/datatable/datatables.responsive.min.js"></script>
<script src="assets/js/datatable/jquery.dataTables.min.js"></script>
<script src="assets/js/dataTables.bootstrap.js"></script>

<style type="text/css">
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

.previewDiv p:not (:last-child ){
	border-bottom: none
}
</style>
<script>
	/* $('.previewDiv p:not(:last-child)').css('border-bottom','none');
	 $('.previewDiv p i').on('click',function(){
	 $(this).parents('p').hide();
	 }); */
</script>
</head>

<body class="no-skin">
	<div class="page-header">
		<h1>
			Issue <small> <i class="ace-icon fa fa-angle-double-right"></i>
			</small><span id="issueHearderSpanId"
				style="font-size: 20px; font-weight: 500; color: rgb(2, 2, 2);">
			</span>
		</h1>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="btn-group">
				<p>
					<button type="button" onclick="setIssueToEditFromIssueInDetails()"
						class="btn  btn-primary" id="editButtonIdInIssueInDetails" style="display: none"> 
						<i class="ace-icon fa fa-edit bigger-110"></i> Edit
					</button>
					<button type="button" class="btn  btn-info commentInBtnGroup" onclick="commentTabEnabled();" id="commentButtonIdInIssueInDetails"    style="display: none">
						<i class="ace-icon fa fa-comment bigger-110"></i> Comment
					</button>
				</p>
			</div>
			<div class="btn-group">
				<p>
					<button type="button" class="btn  btn-info"
						onclick="setIssueStatusToUpdate('Resolve');" id="resolveButtonIdInIssueInDetails" style="display: none"><i class="ace-icon fa fa-check-square bigger-110"></i> Resolve</button>
					<button type="button" class="btn  btn-info"
						onclick="setIssueStatusToUpdate('Awating Customer Response');" id="awaitingCusResButtonIdInIssueInDetails" style="display: none"><i class="ace-icon fa fa-clock-o bigger-110"></i>  Awaiting
						Customer Response</button>
					<button type="button" class="btn  btn-info"
						onclick="setIssueStatusToUpdate('Cannot be Resolved');" id="cannotbeResldButtonIdInIssueInDetails" style="display: none"><i class="ace-icon fa fa-plus-square bigger-110"
						style="-ms-transform: rotate(45deg);-webkit-transform: rotate(45deg);transform: rotate(45deg);"></i> Cannot
						be Resolved</button>
					<button type="button" class="btn  btn-primary"
						onclick="setIssueStatusToUpdate('Closed');" id="closeButtonIdInIssueInDetails" style="display: none"><i class="ace-icon fa fa-times bigger-110"></i> Close</button>
					<button type="button" class="btn  btn-primary"
						onclick="setIssueStatusToUpdate('Reopened');" id="reopneButtonIdInIssueInDetails" style="display: none"><i class="ace-icon fa fa-undo bigger-110"></i> Reopen</button>
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
							data-action="collapse"> <i class="ace-icon fa fa-chevron-up"></i>
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
										<div class="profile-info-row">
											<div class="profile-info-name"><b>Labels</b></div>
											<div class="profile-info-value">
												<span class="editable" id="age">Edit , UI issue</span>
											</div>
										</div>
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
										<div class="profile-info-row">
											<div class="profile-info-name"><b>Resolution</b></div>
											<div class="profile-info-value">
												<span class="editable" id="issueResolutionSpanId">Unresolved</span>
											</div>
										</div>
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
													name="descriptionTextArea" placeholder="Description  area"
													disabled></textarea>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12" style="position: relative; top: 12px;">
									<div class="profile-user-info profile-user-info-striped">
										<div class="profile-info-row">
											<div class="profile-info-name">
												<b>Attachment</b>
											</div>
											<div class="profile-info-value previewDiv"
												id="issueRelatedAttachmentsDivId">
												
											</div>
											<i class="ace-icon fa fa-plus bigger-110" href="#uploadFileModal" data-toggle="modal" 
											style="cursor:pointer;float: right;color: rgb(209, 91, 71);padding: 9px;background: rgb(237, 243, 244);" title="Upload Document" id="uploadAttachmentidInIssueDetails"></i>
										</div>
									</div>
								</div>
							</div>

							<div class="space-6"></div>
							<div class="widget-toolbox clearfix">
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
											</div>
						</div>
					</div>
				</div>
			</div>

			<div class="tabbable">
				<ul class="nav nav-tabs padding-12 tab-color-blue background-blue"
					id="myTab4">
					<li class="active"><a data-toggle="tab" href="#comment" id="commentDetails">Comment</a></li>
					<li class=""><a data-toggle="tab" href="#activityStream" onclick="getActivityStreamsListForIssue();">Activity
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
														class="btn  btn-sm btn-info commentBtn" id="commentButtonIdInCommentDivSection" style="display: none">
														<i class="ace-icon fa fa-comment bigger-110"></i> Comment
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
					<div id="activityStream" class="tab-pane" style="overflow-y: scroll; overflow-x: hidden; max-height: 398px;">
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
								data-action="collapse"> <i class="ace-icon fa fa-chevron-up"></i>
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
													<b>Assignee</b>
												</div>
												<div class="profile-info-value">
													<span class="editable" id="assigneePersonId"></span>
												</div>
											</div>
											<div class="profile-info-row">
												<div class="profile-info-name">
													<b>Reporter</b>
												</div>
												<div class="profile-info-value">
													<span class="editable" id="reportedPersonId"></span>
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
								data-action="collapse"> <i class="ace-icon fa fa-chevron-up"></i>
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
								data-action="collapse"> <i class="ace-icon fa fa-chevron-up"></i>
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
	modla space start
	
	<div id="uploadFileModal" class="modal fade" tabindex="-1" aria-hidden="false" data-backdrop="static" 
   data-keyboard="false">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header" style="background: rgb(239, 243, 248);">
						<button type="button" class="close" data-dismiss="modal"  style="opacity:3 !important;">
						<span class="badge pull-right" style="position: relative;top: 8px;">×</span></button>
						<h5 class="blue bigger" id=""><i class="fa fa-upload fa-fw txt-color-blue"></i> Upload New File</h5>
					</div>

					<div class="modal-body">
						<div class="row">
						  <div class="col-md-12">
                               <div class="form-horizontal">
                              <form id="uploadFileFormId" name="uploadFileFormId" method="POST">
									<div class="form-group">
										<label class="col-sm-2 control-label " for="form-field-1">
											Attachment</label>
										<div class="col-xs-10">
											<label class="ace-file-input ace-file-multiple"><input
												multiple="" type="file" id="addionalNewFile" name="addionalNewFile"><font color="red" size= 1px>(max size: 5 MB)</font>
										<a class="remove" href="#"><i class=" ace-icon fa fa-times"></i></a></label>
										</div>
									</div>
								</form> 
							</div>
						</div>	
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn btn-sm" data-dismiss="modal" >
							<i class="ace-icon fa fa-times"></i>
							Cancel
						</button>
						<button class="btn btn-sm btn-success" onclick="ajaxCallToUploadDocument();">
							<i class="ace-icon fa fa-upload"></i>
							<span>Upload</span>
						</button>
					</div>
				</div>
			</div>
		</div>
	
	modal space end
	hidden form for downloading the document starts
	<form action="setRelatedDocumentToDowload"
		id="hiddenFormForDocumentDownload"
		name="hiddenFormForDocumentDownload" style="display: none">
		<input type="hidden" name="documentIdToDownload"
			id="documentIdToDownload" />
	</form>
	hidden form for downloading the document ends
	<script>
	
	function commentTabEnabled(){
		console.log("clicked");
		 $('#commentDetails').addClass('active');
	}
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
			style:'well',
			btn_choose:'Drop files here or click to choose',
			btn_change:null,
			no_icon:'ace-icon fa fa-cloud-upload',
			droppable:true,
			thumbnail:'small'//large | fit
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
				//alert(error_code);
			}
	
		}).on('change', function(){
			//console.log($(this).data('ace_input_files'));
			//console.log($(this).data('ace_input_method'));
		});
		
		
		//$('#addionalNewFile')
		//.ace_file_input('show_file_list', [
			//{type: 'image', name: 'name of image', path: 'http://path/to/image/for/preview'},
			//{type: 'file', name: 'hello.txt'}
		//]);

		//dynamically change allowed formats by changing allowExt && allowMime function
		$('#id-file-format').removeAttr('checked').on('change', function() {
			var whitelist_ext, whitelist_mime;
			var btn_choose
			var no_icon
			if(this.checked) {
				btn_choose = "Drop images here or click to choose";
				no_icon = "ace-icon fa fa-picture-o";
	
				whitelist_ext = ["jpeg", "jpg", "png", "gif" , "bmp"];
				whitelist_mime = ["image/jpg", "image/jpeg", "image/png", "image/gif", "image/bmp"];
			}
			else {
				btn_choose = "Drop files here or click to choose";
				no_icon = "ace-icon fa fa-cloud-upload";
				
				whitelist_ext = null;
				whitelist_mime = null;
			}
			var file_input = $('#addionalNewFile');
			file_input
			.ace_file_input('update_settings',
			{
				'btn_choose': btn_choose,
				'no_icon': no_icon,
				'allowExt': whitelist_ext,
				'allowMime': whitelist_mime
			})
			file_input.ace_file_input('reset_input');
			
			file_input
			.off('file.error.ace')
			.on('file.error.ace', function(e, info) {
				
			});
		
		});
    //ENDS
	</script>
	<script type="text/javascript" src="assets/js/issueInDetails.js"></script>
</body>
</html>
 -->