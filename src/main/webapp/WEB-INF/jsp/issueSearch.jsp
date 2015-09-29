<!-- <!DOCTYPE html>
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
	</head>

	<body class="no-skin">
	<div class="page-header">
					<h1>
						Issue  <small> <i
							class="ace-icon fa fa-angle-double-right"></i> Search
						</small>
					</h1>
				</div>
				
				
	<div class="row well">
	
	<div class="timeline-item clearfix">
					<div class="widget-box transparent" style="margin-left: 0px;">
						<div class="widget-header widget-header-small">
							<h5 class="widget-title smaller">
								<a href="#" class="blue">Main Search</a> <span class="grey">
									 Criteria</span>
							</h5>
							<span class="widget-toolbar"> <a href="#" data-action="collapse"> <i class="ace-icon fa fa-chevron-up"></i>
							</a>
							</span>
						</div>

						<div class="widget-body" style="display: block;">
							<div class="widget-main" style="background: #fff; border-bottom: 1px solid rgb(220, 235, 247); border-right: 1px solid rgb(220, 235, 247)">
								<div class="form-horizontal">
						
							<div class="form-group">
								<label class="col-md-1 control-label no-padding-right"
									for="form-field-1"> Application </label>
								<div class="col-md-3">
									<select class="form-control selectpicker show-tick" multiple
										id="applicationToSearch" name="applicationToSearch" onchange="getModuleListForSelectedAppsIssueSearch();">
									</select> 
								</div>
								<label class="col-md-1 control-label no-padding-right"
									for="form-field-1"> Issues </label>
								<div class="col-md-3">
									<select class="form-control selectpicker show-tick"
										id="issuesToSearch" name="issuesToSearch">
										<option value="all">All</option>
										<option value="unresolved" selected="selected">Unresolved</option>
										<option value="reportedByMe">Reported By Me</option>
										<option value="assignedToMe">Assigned To Me</option>
									</select> 
								</div>
								<label class="col-md-1 control-label no-padding-right"
									for="form-field-1"> Time </label>
								<div class="col-md-3" id="divClassChange">
									<select class="form-control selectpicker show-tick" 
										id="timeToSearch" name="timeToSearch">
									    <option value="entire">Entire Duration</option>
										<option value="30">Last 30 days</option>
										<option value="90">Last 90 days</option>
										<option value="other">Other</option>
									</select>
								</div>
								<div class="col-md-1 hide" id="timeEnterDiv">
									<input class="form-control input-mask-phone" type="text" style="border-radius:4px !important"
									id="daysToSearch" name="daysToSearch"
									placeholder="Enter Time(in days)">
								</div>
								
							</div>
							
						</div>
							</div>
						</div>
					</div>
				</div>
				<div class="timeline-item clearfix">
					<div class="widget-box transparent collapsed" style="margin-left: 0px;">
						<div class="widget-header widget-header-small">
							<h5 class="widget-title smaller">
								<a href="#" class="blue">Other Search</a> <span class="grey">
									Criteria</span>
							</h5>
							<span class="widget-toolbar"> <a href="#" data-action="collapse" id="issueSearchOtherSearchAnchorId"> <i class="ace-icon fa fa-chevron-down"></i>
							</a>
							</span>
						</div>

						<div class="widget-body" style="display: none;">
							<div class="widget-main" style="background: #fff; border-bottom: 1px solid rgb(220, 235, 247); border-right: 1px solid rgb(220, 235, 247)">
								<div  class="form-horizontal">
						
							<div class="form-group">
								<label class="col-md-1 control-label no-padding-right"
									for="form-field-1"> Severity </label>
								<div class="col-md-3">
									<select class="form-control selectpicker show-tick"
										id="issuePriorityToSearch" name="issuePriorityToSearch">
									</select>
								</div>
								<label class="col-md-1 control-label no-padding-right"
									for="form-field-1"> Status </label>
								<div class="col-md-3">
									<select class="form-control selectpicker show-tick"
										id="statusToSearch" name="statusToSearch">
									</select>
								</div>
								<label class="col-md-1 control-label no-padding-right"
									for="form-field-1"> Module </label>
								<div class="col-md-3">
									<select class="form-control selectpicker show-tick"
										id="moduleToSearch" name="moduleToSearch">
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-1 control-label no-padding-right"
									for="form-field-1"> Issue Type </label>
								<div class="col-md-3">
									<select class="form-control selectpicker show-tick"
										id="issueTypeToSearch" name="issueTypeToSearch">
									</select>
								</div>
								<label class="col-md-1 control-label no-padding-right"
									for="form-field-1"> Assignee </label>
								<div class="col-md-3">
									<select class="form-control selectpicker show-tick"
										id="assigneeToSearch" name="assigneeToSearch">
									</select>
								</div>
								<label class="col-md-1 control-label no-padding-right"
									for="form-field-1"> Issue No </label>
								<div class="col-md-3">
									<input
								class="form-control input-mask-phone" type="text"
								id="issueNoToSearch" name="issueNoToSearch" placeholder="Issue #">
								</div>
								
							</div>
							
							
						</div>
							</div>
						</div>
					</div>
				</div>
	
				
      
       <div class="col-md-12">
						<button
							style="margin-top: 10px;float: right;"
							type="button" class="btn  btn-sm btn-info" onclick="getIssueDetailsBasedOnSearchKeys();">Search</button>
					</div>
	</div>
	
				<div class="row">
			<div class="">
			<div class="well">
				<div class="row">
				 	<div class="timeline-item clearfix">
						<div class="widget-header widget-header-small">
							<h5 class="widget-title smaller">
								<a href="#" class="blue">Main Search </a> <span class="grey">Criteria</span>
							</h5>
						</div>
					</div>
					<div>
					<div class="col-lg-4 col-md-4 col-sm-4">
					<div class="input-group">
							<span class="input-group-addon"> Application </span>
							<select class="form-control selectpicker show-tick" multiple
										id="applicationToSearch" name="applicationToSearch">
									</select> 
						</div>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-4">
						<div class="input-group">
							<span class="input-group-addon"> Issues </span>
							<select class="form-control selectpicker show-tick"
										id="issuesToSearch" name="issuesToSearch">
										<option value="all">All</option>
										<option value="unresolved">Unresolved</option>
										<option value="reportedByMe">Reported By Me</option>
										<option value="assignedToMe">Assigned To Me</option>
									</select> 
						</div>
					</div>
					<div id="divClassChange" class="col-lg-4 col-md-4 col-sm-4">
						<div class="input-group">
							<span class="input-group-addon"> Time </span> 
							<select class="form-control selectpicker show-tick" 
										id="timeToSearch" name="timeToSearch">
									    <option value="entire">Entire Duration</option>
										<option value="30">Last 30 days</option>
										<option value="90">Last 90 days</option>
										<option value="other">Other</option>
									</select>
						</div>
					</div>
						<div id="timeEnterDiv" class="col-md-1 col-lg-1 col-sm-1 hide" style="padding-left: 0px;">
							<div class="input-group">
								<input class="form-control input-mask-phone" type="text" style="border-radius:4px !important"
									id="daysToSearch" name="daysToSearch"
									placeholder="Enter Time(in days)">
							</div>
						</div>
					</div> 
					
					
				<div class="timeline-item clearfix" style="padding-top: 55px !important;">
						<div class="widget-header widget-header-small">
							<h5 class="widget-title smaller">
								<a href="#" class="blue">Other Search </a> <span class="grey">Criteria</span>
							</h5>
						</div>
					</div>
					<div>	
					<div>
					<div class="col-lg-4 col-md-4 col-sm-4">
						<div class="" style="display: table !important;">
							<span class="input-group-addon"> Severity </span> <select class="form-control selectpicker show-tick"
										id="issuePriorityToSearch" name="issuePriorityToSearch">
									</select>
						</div>
					</div>	
					<div class="col-lg-4 col-md-4 col-sm-4">
						<div class="" style="display: table !important;">
							<span class="input-group-addon"> Status </span><select class="form-control selectpicker show-tick"
										id="statusToSearch" name="statusToSearch">
									</select> 
						</div>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-4">
						<div class="" style="display: table !important;">
							<span class="input-group-addon"> Module </span><select class="form-control selectpicker show-tick"
										id="moduleToSearch" name="moduleToSearch">
									</select> 
						</div>
					</div>
				</div>	
				<div class="col-md-12" style="padding-top: 8px">
				<div class="row">
					<div class="col-lg-4 col-md-4 col-sm-4">
						<div class="" style="display: table !important;">
							<span class="input-group-addon"> Issue Type </span><select class="form-control selectpicker show-tick"
										id="issueTypeToSearch" name="issueTypeToSearch">
									</select> 
						</div>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-4">
						<div class="" style="display: table !important;">
							<span class="input-group-addon"> Assignee </span><select class="form-control selectpicker show-tick"
										id="assigneeToSearch" name="assigneeToSearch">
									</select> 
						</div>
					</div>
				   <div class="col-lg-4 col-md-4 col-sm-4">
					<div class="" style="display: table !important;">
							<span class="input-group-addon"> Issue No </span> <input
								class="form-control input-mask-phone" type="text"
								id="issueNoToSearch" name="issueNoToSearch" placeholder="Issue #" style="border-radius:0px 4px 4px 0px !important">
						</div>
					</div>
					</div>
					</div>
                 </div>
                 					
				</div>
				<div class="row">
					<div class="col-md-12">
						<button
							style="margin-top: 10px;float: right;"
							type="button" class="btn  btn-sm btn-info" onclick="getIssueDetailsBasedOnSearchKeys();">Search</button>
					</div>
				</div>

			</div>
			
				<div class="table-header">
					 Search Results
				</div>
				<div>
				<table id="issueSearchResultTable" class="center table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th class="center">Issue Type</th>
								<th class="center">Key</th>
								<th class="center">Summary</th>
								<th class="center">Priority</th>
								<th class="center">Reported Date</th>
							</tr>
						</thead>

						<tbody>
						
						</tbody>
					</table>
				</div>
			</div>
		</div>
		hidden form for selecting issue in details starts
	<div style="display: none">
		<form id="issueInDetailsForm" name="issueInDetailsForm" method="POST"
			action="getIssueDetails">
			<input type="hidden" name="issueIdToGetDetails"
				id="issueIdToGetDetails">
		</form>
	</div>
	hidden form for selecting issue in details ends
		
        Respective jsp js dependencies 
		 <script src="assets/js/issueSearch.js"></script> 
	<script>
	
	/* js for making showing time textbox in TIME dropdown START */
	$( "#timeToSearch" ).change(function() {
		val = $(this).val();
		switch (val) {
		
        case 'other':$('#timeEnterDiv').removeClass('hide');
                 $('#divClassChange').toggleClass('col-md-2 col-md-3');
        break; 
        
        default:$('#timeEnterDiv').addClass('hide');
         $('#divClassChange').removeClass('col-md-2');
         $('#divClassChange').addClass('col-md-3'); 
    }
		});
	/* END */	
	
	//making active tab
	$('#dashboard').removeClass('active');
	$('#homeScreen').removeClass('active');
	$('#issueSearch').addClass('active');
	
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
	</script>
	</body>

</html>
 -->