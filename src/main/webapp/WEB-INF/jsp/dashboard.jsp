<!DOCTYPE html>
<html lang="en">

<head>
<link rel="stylesheet" href="assets/css/d3Style.css" />
<script src="assets/js/datatable/datatables.responsive.min.js"></script>
<script src="assets/js/datatable/jquery.dataTables.min.js"></script>
<script src="assets/js/dataTables.bootstrap.js"></script>
<script src="assets/js/dashboard.js"></script>
<script src="assets/js/graphCharts.js"></script>
 <!-- <script type="text/javascript" src="assets/js/highcharts.js"></script>
 <script type="text/javascript" src="assets/js/drilldown.js"></script>
<script type="text/javascript" src="assets/js/exporting.js"></script>
 -->


<style type="text/css">
table {
	border: 1px solid #DDD;
	border-top: none;
}

.header {
	font-size: 20px;
}

.sector {
	cursor: pointer;
}

.d3-tip {
	line-height: 1;
	font-weight: bold;
	padding: 12px;
	background: rgba(0, 0, 0, 0.8);
	color: #fff;
	border-radius: 2px;
}

/
Creates a small triangle extender for the tooltip /
.d3-tip:after {
	box-sizing: border-box;
	display: inline;
	font-size: 10px;
	width: 100%;
	line-height: 1;
	color: rgba(0, 0, 0, 0.8);
	content: "\25BC";
	position: absolute;
	text-align: center;
}

/
Style northward tooltips differently /
.d3-tip.n:after {
	margin: -1px 0 0 0;
	top: 100%;
	left: 0;
}
.nav-tabs>li {
float: right;
margin-bottom: -1px;
}
</style>
</head>

<body class="no-skin">
	<!-- <div class="page-header">
		<h1>
			Dashboard <small> <i class="ace-icon fa fa-angle-double-right"></i>
				Overview
			</small>
		</h1>
	</div> -->

	<div class="row">
	<div class="col-xs-12">
	
	<!-- <h4>Graph view</h4> -->
	<!-- <div id="graphChartDiv"></div> -->
	</div>
	</div>
		<div class="col-xs-12">
			<div class="well">
				<div class="">
				
					<!-- <div class="alert alert-info center">
						<span class="glyphicon glyphicon-exclamation-sign red"
							aria-hidden="true"></span> &nbsp;&nbsp; <b>It is under
							construction</b>
					</div> -->
					
					<div class="timeline-item clearfix">
					<div class="widget-box transparent" style="margin-left: 0px;">
						<div class="widget-header widget-header-small">
							<h5 class="widget-title smaller">
								<a href="#" class="blue">Search</a> <span class="grey">
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
									<select class="form-control selectpicker show-tick applicationToSearchInDashboard" multiple
									id="applicationToSearchInDashboard" name="applicationToSearchInDashboard">
								</select>
								</div>
								<label class="col-md-1 control-label no-padding-right"
									for="form-field-1"> Issues </label>
								<div class="col-md-2">
									<select class="form-control selectpicker show-tick"
									id="issuesToSearchInDashboard" name="issuesToSearchInDashboard">

									    <option value="all" selected="selected">All</option>
										<option value="unresolved" >Unresolved</option>
										<option value="reportedByMe">Reported By Me</option>
										<option value="assignedToMe">Assigned To Me</option>
								</select>
								</div>
								<label class="col-md-1 control-label no-padding-right"
									for="form-field-1"> Time </label>
								<div class="col-md-3" id="divClassChangeInDashboard">
									<select class="form-control selectpicker show-tick" id="timeToSearchInDashboard"
									name="timeToSearchInDashboard">
									<option value="entire">Entire Duration</option>
										<option value="30">Last 30 days</option>
										<option value="90">Last 90 days</option>
										<option value="other">Other</option>
								</select>
								</div>
								<div class="col-md-1 hide" id="timeEnterDivInDashboard" style="padding-left: 0px;">
									<input class="form-control input-mask-phone" type="text"
									id="daysToSearchInDashboard" name="daysToSearchInDashboard" style="border-radius: 4px !important;"
									placeholder="Time (days)">
								</div>
								<div class="col-md-1"
												style="position: relative; top: -6px; padding-right: 0px;">
								<button
							style="margin-top: 10px;margin-right: 10px;float: right;"
							type="button" class="btn  btn-sm btn-info" onclick="getIssueCountsForDashboard();">Get Details</button>
							</div>

											
												
											</div>
										</div>

							
						</div>
							</div>
						</div>
					</div>
				</div>
				</div>
				
					<!-- <div class="timeline-item clearfix">
						<div class="widget-header widget-header-small">
							<h5 class="widget-title smaller">
								<a href="#" class="blue">Issues </a> <span class="grey">
								</span>
							</h5>
						</div>
					</div>
					<div>
						<div class="col-md-4">
							<div class="input-group">
								<span class="input-group-addon"> Application </span><select
									class="form-control selectpicker show-tick" multiple
									id="applicationToSearchInDashboard" name="applicationToSearchInDashboard" onchange="getIssueCountsForDashboard();">
								</select>
							</div>
						</div>
						<div class="col-md-4">
							<div class="input-group">
								<span class="input-group-addon"> Issues</span><select
									class="form-control selectpicker show-tick"
									id="issuesToSearchInDashboard" name="issuesToSearchInDashboard" onchange="getIssueCountsForDashboard();">
									<option value="all">All</option>
										<option value="unresolved" selected="selected">Unresolved</option>
										<option value="reportedByMe">Reported By Me</option>
										<option value="assignedToMe">Assigned To Me</option>
								</select>
								<input
								class="form-control input-mask-phone" type="text"
								id="issueTypeToSearch" name="issueTypeToSearch">
							</div>
						</div>
						<div id="divClassChangeInDashboard" class="col-md-4">
							<div class="input-group">
								<span class="input-group-addon"> Time </span> <select
									class="form-control selectpicker show-tick" id="timeToSearchInDashboard"
									name="timeToSearchInDashboard" onchange="getIssueCountsForDashboard();">
									<option value="entire">Entire Duration</option>
										<option value="30">Last 30 days</option>
										<option value="90">Last 90 days</option>
										<option value="other">Other</option>
								</select>
							</div>
						</div>
						<div id="timeEnterDivInDashboard" class="col-md-1 hide"
							style="padding-left: 0px;">
							<div class="input-group">
								<input class="form-control input-mask-phone" type="text"
									id="daysToSearchInDashboard" name="daysToSearchInDashboard" style="border-radius: 4px !important;"
									placeholder="Enter Time(in days)">
							</div>
						</div>
					</div> -->
			
			<div class="tabbable">
				<ul class="nav nav-tabs padding-12 tab-color-blue background-blue"
					id="myTab4">
					<!-- <li class=""><a data-toggle="tab" href="#activityStream">Activity Stream</a></li> -->
					<li class=""><a data-toggle="tab" href="#charts" onclick="getDashBoardCharts();">Charts content</a></li>
					<li class="active"><a data-toggle="tab" href="#table" onclick="getIssueCountsForDashboard();">Table content</a>
					</li>

				</ul>

				<div class="tab-content">
					<div id="table" class="tab-pane active">

						<!-- <div class="timeline-item clearfix">
							<div class="widget-header widget-header-small">
								<h5 class="widget-title smaller">
									<a href="#" class="blue">Table Content Of </a> <span
										class="grey">Dashboard</span>
								</h5>
							</div>
						</div> -->
						<div class="row">
							<div class="col-sm-6">
								<div class="table-header" style="margin-bottom: 0px !important">
									<span id="severityTableHeadingSpanId">Unresolved</span> : <span
										style="color: #93C2E4 !important">By Severity</span>
								</div>
								<table id="dashboardByPriorityTable"
									class="center table table-striped  table-hover">
									<thead>
										<tr>
											<th class="center">Severity</th>
											<th class="center">Issues count</th>
											<th class="center">Percentage</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
							<div class="col-sm-6">
								<div class="table-header" style="margin-bottom: 0px !important">
									<span id="statusTableHeadingSpanId">Unresolved</span> : <span
										style="color: #93C2E4 !important">By Status</span>
								</div>
								<div style="height: 171px; overflow: scroll; overflow-x: hidden;" class="custScrollerModuleTable">
									<table id="dashboardByStatusTable"
										class="center table table-striped  table-hover">
										<thead>
											<tr>
												<th class="center">Status</th>
												<th class="center">Issues count</th>
												<th class="center">Percentage</th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-sm-6">
								<div class="table-header" style="margin-bottom: 0px !important">
									<span id="issuetypeTableHeadingSpanId">Unresolved</span> : <span
										style="color: #93C2E4 !important">By Issue Type</span>
								</div>
								<div
									style="height: 176px; overflow: hidden; border: 1px solid #DDDDDD !important;">
									<table id="dashboardByIssueTypeTable"
										style="border: none !important; border-bottom: 1px solid #DDDDDD !important"
										class="center table table-striped  table-hover">
										<thead>
											<tr>
												<th class="center">Issue Type</th>
												<th class="center">Issues count</th>
												<th class="center">Percentage</th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="table-header" style="margin-bottom: 0px !important">
									<span id="moduleTableHeadingSpanId">Unresolved</span> : <span
										style="color: #93C2E4 !important">By Module</span>
								</div>
								<div
									style="height: 176px; overflow: scroll; overflow-x: hidden;"
									class="custScrollerModuleTable">
									<table id="dashboardByModuleTable"
										class="center table table-striped  table-hover">
										<thead>
											<tr>
												<th class="center">Module Name</th>
												<th class="center">Issues count</th>
												<th class="center">Percentage</th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
								</div>
							</div>
						</div>

					</div>

					<div id="charts" class="tab-pane">
					<div class="row">
					<div class="col-md-6" style="border: 1px solid #EFF3F8;border-left: none;">
						<div id="container" style="height: 400px; margin: 0 auto"></div>
					</div>	
					<div class="row">
					<div class="col-md-6" style="border-top: 1px solid #EFF3F8;border-bottom: 1px solid #EFF3F8;">
						<div id="container1" style="height: 400px; margin: 0 auto"></div>
					</div>
					</div>
					</div>
                    </div>
                    
                    <div id="activityStream" class="tab-pane">
					<div class="row">
					<div class="col-md-12" >
					<h4>This functionality is Under construction</h4>
					</div>
                    </div>

				</div>
			</div>


			<div class="row hide" >
				<div class="col-xs-6">
						<div class="table-header" style="margin-bottom:0px !important"><span id="assigneeTableHeadingSpanId">Unresolved</span> : <span
								 style="color: #93C2E4 !important">By Assignee</span></div>
						<div>
							<table id="issueTrackingTable"
								class="center table table-striped  table-hover">
								<thead>
									<tr>
										<th class="center">Later Stage : will be done</th>
									</tr>
								</thead>

								<tbody>
								</tbody>
							</table>
						</div>
					</div>
				</div>
	</div>
	<!-- hidden form for redirecting to issue search page from dashboard where user clicks in link of any table starts -->
	<div style="display: none">
	   <form action="redirectToIssueSearchPage" name="redirectToIssueSearchPageForm" id="redirectToIssueSearchPageForm" method="POST">
	       <input type="hidden" name="lookupType" id="lookupType">
	       <input type="hidden" name="lookupValue" id="lookupValue">
	       <input type="hidden" name="applicationToSend" id="applicationToSend">
	       <input type="hidden" name="issuesToSend" id="issuesToSend">
	       <input type="hidden" name="timeToSend" id="timeToSend">
	       <input type="hidden" name="daysToSend" id="daysToSend">
	   </form>
	</div>
	<!-- hidden form for redirecting to issue search page from dashboard where user clicks in link of any table starts -->
</div>
	<script>
	//initializing selectpicker
	$('.selectpicker').selectpicker(); 
	
	/* js for making showing time textbox in TIME dropdown START */
	$( "#timeToSearchInDashboard" ).change(function() {
		val = $(this).val();
		switch (val) {
		
        case 'other':$('#timeEnterDivInDashboard').removeClass('hide');
                 $('#divClassChangeInDashboard').addClass('col-md-2');
                 $('#divClassChangeInDashboard').removeClass('col-md-3');
        break; 
        
        default:$('#timeEnterDivInDashboard').addClass('hide');
        $('#divClassChangeInDashboard').addClass('col-md-3');
        $('#divClassChangeInDashboard').removeClass('col-md-2');
    }
		});
	/* END */	
	
	//making active tab
		$('#issueSearch').removeClass('activeTab');
		$('#homeScreen').removeClass('activeTab');
		$('#dashboard').addClass('activeTab');
		
		//code for validation for allowing only numbers in input boxes
		$('input').on('keypress', function(e) {
			if (this.id == "daysToSearchInDashboard") {//this is for number validation
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
	<!-- Respective jsp js dependencies  -->
		
			
</body>

</html>
