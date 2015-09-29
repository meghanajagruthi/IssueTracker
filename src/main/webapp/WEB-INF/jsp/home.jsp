
<!DOCTYPE html>
<%@page import="com.elecnor.issue.tracker.bean.UserDetails"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta charset="utf-8">
<title>Issue Tracker</title>
<link rel="shortcut icon" type="image/x-icon"
	href="assets/img/favicon.ico" />

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
	
<!-- CSS for Gritter Notification -->	
<link rel="stylesheet" href="assets/css/gritterNotification.css" />

<!-- CSS for bootstrap & fontawesome -->
<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
<link rel="stylesheet" href="assets/css/font-awesome.min.css" />

<!-- CSS for ace styles -->
<link rel="stylesheet" href="assets/css/ace.min.css" />
<link rel="stylesheet" href="assets/css/ace-skins.min.css" />
<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />

<!-- text fonts -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Open+Sans:400,300">


<!--[if lte IE 9]>
			<link rel="stylesheet" href="../assets/css/ace-part2.min.css" />
		<![endif]-->

<!--[if lte IE 9]>
		  <link rel="stylesheet" href="../assets/css/ace-ie.min.css" />
		<![endif]-->

<!-- ace settings handler -->
<script src="assets/js/ace-extra.min.js"></script>

<!-- selectpicker css -->
<link rel="stylesheet" type="text/css" media="screen" href="assets/css/selectMin.css">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lte IE 8]>
		<script src="../assets/js/html5shiv.js"></script>
		<script src="../assets/js/respond.min.js"></script>
		<![endif]-->
<!-- dependencies for ladda buttons starts-->
<link rel="stylesheet" href="assets/css/ladda-themeless.min.css">
<script src="assets/js/spin.min.js"></script>
<script src="assets/js/ladda.min.js"></script>
<!-- dependencies for ladda buttons ends-->
<style type="text/css">
.jqstooltip {
	position: absolute;
	left: 0px;
	top: 0px;
	visibility: hidden;
	background: rgb(0, 0, 0) transparent;
	background-color: rgba(0, 0, 0, 0.6);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000,
		endColorstr=#99000000);
	-ms-filter:
		"progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000)";
	color: white;
	font: 10px arial, san serif;
	text-align: left;
	white-space: nowrap;
	padding: 5px;
	border: 1px solid white;
	z-index: 10000;
}

.jqsfield {
	color: white;
	font: 10px arial, san serif;
	text-align: left;
}
.ace-spinner{width: auto !important;}
/* .navbar-default .navbar-nav>.active>a, .navbar-default .navbar-nav>.active>a:focus, .navbar-default .navbar-nav>.active>a:hover
{background-color: whitesmoke !important ;color:black} */

.datepicker{z-index:99999999 !important;cursor:pointer}

/* scroller css start*/
.custScrollerModuleTable::-webkit-scrollbar{
	height: 9px;
	width: 10px;
	background-color: white;
	border: 1px solid lightgrey;
}
.custScrollerModuleTable::-webkit-scrollbar-thumb {
	background-color: #93BDD5;
}
.custScrollerModuleTable::-webkit-scrollbar-thumb:hover {
	background-color: #93BDD5;
}
.custScrollerModuleTable::-webkit-scrollbar-thumb:active {
	background-color: #93BDD5;
}
/* scroller css end*/

/* css for placeholder */
::-webkit-input-placeholder {
   color: #D5D5D5 !important;
   font-size:13px;
}
:-moz-placeholder { /* Firefox 18- */
   color: #D5D5D5 !important; 
 font-size:13px; 
}
::-moz-placeholder {  /* Firefox 19+ */
   color: #D5D5D5;
    font-size:13px !important;
}
:-ms-input-placeholder {  
   color: #D5D5D5 !important;
    font-size:13px;
}

.navbar .navbar-nav>li>a ,.navbar .navbar-brand{
color:rgb(66, 139, 202) !important;
border-radius: 6px;
}
.navbar .navbar-nav>li{
border-width: 0 0px !important;
}
.modal.fade{
    -webkit-transition: opacity .12s linear, none;
    -moz-transition: opacity .12s linear, none;
    -ms-transition: opacity .12s linear, none;
    -o-transition: opacity .12s linear, none;
    transition: opacity .20s linear, none;
    transition: transform 0.12s ease-out;
}
/* for making tab active css  */
.activeTab{
box-shadow: 1px 3px 3px 1px rgba(140, 112, 112, 0.3) inset !important;
background: white !important;
border-bottom: 2px solid rgb(230, 230, 230) !important;
}
#gritter-notice-wrapper{
top:53px !important;
}

/* issue Search jsp css */
.widget-header{
background-image: linear-gradient(to bottom, #d2e0ec 0, #d9edf7 100%);
height:none !important;
}
.input-group .input-group-addon {
border-radius:  4px  !important;
}
.btn-group>.btn:first-child, .btn-group>.btn:last-child ,input[type=text],.form-group textarea{
border-radius: 4px !important;
}

 .form-control .selectpicker{ 
border: 0px !important;
}
.form-control .selectpicker {
border: 1px solid #D5D5D5 !important;
}
 .btn-default, .btn-default:focus,.btn-default:hover,.btn-default:active,.open .btn-default.dropdown-toggle, .open .btn.dropdown-toggle {
background-color: #FFFFFF !important;
border-color: #abbac3;
} 
.well{background:rgb(240, 241, 241) !important}
.table-header{
border-color: #9cb4c5;
color: #F4F8FB;
background: #3675AB;
}
.selectpicker.btn-default{color:#333 !important;text-shadow: none !important;}
.btn-group>.btn>.caret{border-top-color:#333 !important}
.open>.dropdown-menu {
padding: 0px !important;
margin: 0px;
border-top: 0px solid #fff;
}
.timeline-item .transparent .widget-header{
background-image: linear-gradient(to bottom, #d2e0ec 0, #d9edf7 100%);
border: 1px solid #B6D5EA;
border-left: none;
}
.widget-title a{
text-decoration: none !important ;
cursor:text	;
}
</style>
</head>

<body class="no-skin">
	<div id="navbar" class="navbar navbar-default">
		<script type="text/javascript">
			try {
				ace.settings.check('navbar', 'fixed')
			} catch (e) {
			}
		</script>

		<div class="navbakr-container" id="navbar-container">
			<button type="button" class="navbar-toggle menu-toggler pull-left"
				id="menu-toggler">
				<span class="sr-only">Toggle sidebar</span> <span class="icon-bar"></span>

				<span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>

			<div class="navbar-header pull-left">
				<a 
					href="home#homeScreen"
					class="navbar-brand"> <small >
						Issue Tracker
				</small>
				</a>
			</div>
			<nav class="navbar navbar-default navbar-fixed-top" role="navigation" style="background:-o-linear-gradient(#f9f9f9, #cccccc); background: -moz-linear-gradient(#f9f9f9, #cccccc); background: linear-gradient(#f9f9f9, #cccccc); box-shadow: 0px 2px 2px #ccc;border-bottom: 3px solid #F5F5F5;">
			 <div class="navbar-header pull-left">
				<a
					href="home#homeScreen"
					class="navbar-brand"> <small style="color: rgb(54, 117, 171) !important;font-weight: bold !important;font-size: 18px !important">
						Issue Tracker
				</small>
				</a>
			</div>
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-ex1-collapse">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
				</div>
				<div class="navbar-collapse navbar-ex1-collapse collapse"
					style="height: 1px;">
					<ul id="headertab" class="nav navbar-nav">
					    <li id="homeScreenTab" ><a id="homeScreen" class=""
							href="home#homeScreen">Home</a></li>
						 <li id="dashboardTab"><a id="dashboard" class=""
							href="home#dashboard">Dashboard</a></li> 	
						 <!-- li id="issueSearchTab" ><a id="issueSearch"
							href="home#issueSearch">Issue Search</a></li> -->
						<li id="createIssueTab"
							style="position: relative; top: 5px; left: 10px;"><button
								class="btn btn-primary" href="#createIssue" role="button"
								data-toggle="modal" onclick="getLookUpDetailsToCreateIssue();">Create
								Issue <i class="fa fa-plus"></i></button></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
                        <li id="createIssueTab"
							style="position: relative; top: 5px; right: 10px;">
							<button class="btn btn-default" style="background: #abbac3 !important;">
							<a id="logout" href="logoutOutAction" style="color:#fff !important;text-decoration: none">Logout <i class="fa fa-sign-out"></i></a></button></li>
						<li class="dropdown">
						<a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> <small>Welcome &nbsp;<b><%=(UserDetails) session.getAttribute("logedInUserDetails") != null ? ((UserDetails) session
					.getAttribute("logedInUserDetails")).getFirstName() : ""%></b>
									&nbsp;
							</small><!-- <i class="ace-icon fa fa-cog bigger-105"></i><b class="caret"></b> --></a>
							<!-- <ul class="dropdown-menu">
								<li id=""><a id="" href="home#dashboard">Dashboard</a></li> 
								 <li id=""><a id="" href="home#issueSearch">Issue Search</a></li> 
								<li class="divider"></li>
								<li><a id="logout" href="logoutOutAction">Logout</a></li>
							</ul></li> -->
					</ul>
				</div>
			</nav>

      <!-- SIDEBAR CODE start commented On "Header" for future use-->
			<!-- <div class="navbar-buttons navbar-header pull-right"
				role="navigation">
				<ul class="nav ace-nav">
			 		<li class="grey"><a data-toggle="dropdown"
						class="dropdown-toggle"
						href="#">
							<i class="ace-icon fa fa-tasks"></i> <span
							class="badge badge-grey">4</span>
					</a>

						<ul
							class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
							<li class="dropdown-header"><i class="ace-icon fa fa-check"></i>
								4 Tasks to complete</li>

							<li><a
								href="#">
									<div class="clearfix">
										<span class="pull-left">Software Update</span> <span
											class="pull-right">65%</span>
									</div>

									<div class="progress progress-mini">
										<div style="width: 65%" class="progress-bar"></div>
									</div>
							</a></li>

							<li><a
								href="#">
									<div class="clearfix">
										<span class="pull-left">Hardware Upgrade</span> <span
											class="pull-right">35%</span>
									</div>

									<div class="progress progress-mini">
										<div style="width: 35%"
											class="progress-bar progress-bar-danger"></div>
									</div>
							</a></li>

							<li><a
								href="#">
									<div class="clearfix">
										<span class="pull-left">Unit Testing</span> <span
											class="pull-right">15%</span>
									</div>

									<div class="progress progress-mini">
										<div style="width: 15%"
											class="progress-bar progress-bar-warning"></div>
									</div>
							</a></li>

							<li><a
								href="#">
									<div class="clearfix">
										<span class="pull-left">Bug Fixes</span> <span
											class="pull-right">90%</span>
									</div>

									<div class="progress progress-mini progress-striped active">
										<div style="width: 90%"
											class="progress-bar progress-bar-success"></div>
									</div>
							</a></li>

							<li class="dropdown-footer"><a
								href="#">
									See tasks with details <i class="ace-icon fa fa-arrow-right"></i>
							</a></li>
						</ul></li> 

					 <li class="light-blue">
					 <a data-toggle="dropdown"
						href="#"
						class="dropdown-toggle"> <img class="nav-user-photo"
							src="#"
							alt="Jason&#39;s Photo"> <span class="user-info"> <small>Welcome,</small>
								Admin
						</span> <i class="ace-icon fa fa-caret-down"></i>
					</a> 
					<a data-toggle="dropdown" href="#" class="dropdown-toggle">  <button class="btn btn-primary"><i class="ace-icon fa fa-user"></i>&nbsp;&nbsp;<i class="ace-icon fa fa-caret-down"></i></button>
					</a>

						<ul
							class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
							<li class="hsub">
						<a href="#" class="dropdown-toggle" style="background: #A2C1D3;">
							<i class="menu-icon fa fa-desktop"></i>
							<span class="menu-text"> Home </span>
						</a>
						<ul class="submenu nav-show"
									style="display: block; border-left: 1px dotted #A5A5A5;">
									<li class=""
										style="list-style: none; line-height: 25px; font-size: 13px;">
										<a href="#" style="color: black;"><i
											class="menu-icon fa fa-caret-right"></i> Dashboard </a> <b
										class="arrow"></b>
									</li>

									<li class=""
										style="list-style: none; line-height: 25px; font-size: 13px;">
										<a href="#" style="color: black;"><i
											class="menu-icon fa fa-caret-right"></i> Issue Page </a> <b
										class="arrow"></b>
									</li>
								</ul>
							</li>

							<li class="divider"></li>

							<li><a
								href="#">
									<i class="ace-icon fa fa-power-off"></i> Logout
							</a></li>
						</ul></li> 
				</ul>
			</div> -->
			<!-- SIDEBAR CODE ends commented On "Header" for future use-->
		</div>
		<!-- /.navbar-container -->
	</div>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>

<!-- SIDEBAR CODE start commented On "Main Container" for future use-->
		 <!-- <div id="sidebar" class="sidebar                  responsive">
			<script type="text/javascript">
				try {
					ace.settings.check('sidebar', 'fixed')
				} catch (e) {
				}
			</script>

			<div class="sidebar-shortcuts" id="sidebar-shortcuts">
				<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
					<button class="btn btn-success">
						<i class="ace-icon fa fa-signal"></i>
					</button>

					<button class="btn btn-info">
						<i class="ace-icon fa fa-pencil"></i>
					</button>

					<button class="btn btn-warning">
						<i class="ace-icon fa fa-users"></i>
					</button>

					<button class="btn btn-danger">
						<i class="ace-icon fa fa-cogs"></i>
					</button>
				</div>

				<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
					<span class="btn btn-success"></span> <span class="btn btn-info"></span>

					<span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
				</div>
			</div>
			/.sidebar-shortcuts

       <ul class="nav nav-list " id="menuBox">
					<li class="active" id="dashboardActive">
						<a data-url="home/dashboard" href="home#dashboard">
									<i class="menu-icon fa fa-tachometer"></i>
							<span class="menu-text" id="spanText"> Dashboard </span>
						</a>

						<b class="arrow"></b>
					</li>
					<li class="" id="dataTableScriptLoaded">
						<a data-url="home/project" href="home#project">
							<i class="menu-icon fa fa-list-alt"></i>
							<span class="menu-text"> Issue Page </span>
						</a>

						<b class="arrow"></b>
					</li>
					<li class="">
						<a data-url="home/sov" href="home#sov">
							<i class="menu-icon fa fa-desktop"></i>
							<span class="menu-text"> SOV </span>
						</a>

						<b class="arrow"></b>
					</li>
					<li class="">
						<a data-url="home/cover" href="home#cover">
							<i class="menu-icon fa fa-list"></i>
							<span class="menu-text"> Cover </span>
						</a>

						<b class="arrow"></b>
					</li>
					
					commented for future use
					<li class="hsub"><a
					href="#"
					class="dropdown-toggle"> <i class="menu-icon fa fa-list"></i> <span
						class="menu-text"> Tables </span> <b
						class="arrow fa fa-angle-down"></b>
				</a> <b class="arrow"></b>

					<ul class="submenu">
						<li class=""><a data-url="page/tables"
							href="#">
								<i class="menu-icon fa fa-caret-right"></i> Simple &amp; Dynamic
						</a> <b class="arrow"></b></li>

						<li class=""><a data-url="page/jqgrid"
							href="#">
								<i class="menu-icon fa fa-caret-right"></i> jqGrid plugin
						</a> <b class="arrow"></b></li>
					</ul></li>
					<li class=""><a data-url="page/calendar"
					href="#">
						<i class="menu-icon fa fa-calendar"></i> <span class="menu-text">
							Calendar <span class="badge badge-transparent tooltip-error"
							title="" data-original-title="2 Important Events"> <i
								class="ace-icon fa fa-exclamation-triangle red bigger-130"></i>
						</span>
					</span>
				</a> <b class="arrow"></b></li>
				</ul>
			<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
				<i class="ace-icon fa fa-angle-double-left"
					data-icon1="ace-icon fa fa-angle-double-left"
					data-icon2="ace-icon fa fa-angle-double-right"></i>
			</div>

			<script type="text/javascript">
				try {
					ace.settings.check('sidebar', 'collapsed')
				} catch (e) {
				}
			</script>
		</div> -->
		<!-- SIDEBAR CODE ends commented On "Main Container" for future use-->
 
		<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
					</script>

					<ul class="breadcrumb">
						<li>
							<i class="ace-icon fa fa-home home-icon"></i>
							<a href="home#homeScreen" id="homeLink">Home</a>
						</li>
						<li>
							<span id="insertContent">Home</span>
						</li>
						
					</ul><!-- /.breadcrumb -->

					<div class="nav-search hide" id="nav-search">
						<form class="form-search">
							<span class="input-icon">
								 <!-- <input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" /> -->
								<i class="ace-icon fa fa-search nav-search-icon"></i> 
								<input type="text" placeholder="Search by Issue#..." id="custSearch" class="nav-search-input">
								 <i class="ace-icon fa fa-search nav-search-icon"></i> 
							</span>
						</form>
					</div><!-- /.nav-search -->
				</div>

				<div class="page-content">
					<div class="ace-settings-container" id="ace-settings-container" style="top:-41px !important">
						<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
							<i class="ace-icon fa fa-cog bigger-150"></i>
						</div>

						<div class="ace-settings-box clearfix" id="ace-settings-box">
							<div class="pull-left width-50">
								<div class="ace-settings-item">
									<div class="pull-left">
										<select id="skin-colorpicker" class="hide">
											<option data-skin="no-skin" value="#438EB9">#438EB9</option>
											<option data-skin="skin-1" value="#222A2D">#222A2D</option>
											<option data-skin="skin-2" value="#C6487E">#C6487E</option>
											<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
										</select>
									</div>
									<span>&nbsp; Choose Skin</span>
								</div>

								<div class="ace-settings-item">
									<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-navbar" />
									<label class="lbl" for="ace-settings-navbar"> Fixed Navbar</label>
								</div>

								<div class="ace-settings-item">
									<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar" />
									<label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
								</div>

								<div class="ace-settings-item">
									<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-breadcrumbs" />
									<label class="lbl" for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
								</div>

								<div class="ace-settings-item">
									<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" />
									<label class="lbl" for="ace-settings-rtl"> Right To Left (rtl)</label>
								</div>

								<div class="ace-settings-item">
									<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container" />
									<label class="lbl" for="ace-settings-add-container">
										Inside
										<b>.container</b>
									</label>
								</div>
							</div><!-- /.pull-left -->

							<div class="pull-left width-50">
								<div class="ace-settings-item">
									<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-hover" />
									<label class="lbl" for="ace-settings-hover"> Submenu on Hover</label>
								</div>

								<div class="ace-settings-item">
									<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-compact" />
									<label class="lbl" for="ace-settings-compact"> Compact Sidebar</label>
								</div>

								<div class="ace-settings-item">
									<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-highlight" />
									<label class="lbl" for="ace-settings-highlight"> Alt. Active Item</label>
								</div>
							</div><!-- /.pull-left -->
						</div><!-- /.ace-settings-box -->
					</div><!-- /.ace-settings-container -->

					<div class="page-content-area">
						<!-- ajax content goes here -->
					</div><!-- /.page-content-area -->
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->
		<!-- /.main-content -->

		<!-- <div class="footer">
			<div class="footer-inner">
				<div class="footer-content">
					<span class="bigger-120"> <span class="blue bolder">Elecnor</span>
						Application @ 2014-2015
					</span>
				</div>
			</div>
		</div> -->

		<a
			href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>
	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->
	<!--[if !IE]> -->
	<script src="assets/js/jquery.min.js"></script>
	<!-- <![endif]-->

	<!--[if IE]>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<![endif]-->

	<!--[if !IE]> -->
	<script type="text/javascript">
		window.jQuery
				|| document.write("<script src='assets/js/jquery.min.js'>"
						+ "<"+"/script>");
	</script>

	<!-- <![endif]-->

	<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='assets/js/jquery1x.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
	<script type="text/javascript">
		if ('ontouchstart' in document.documentElement)
			document
					.write("<script src='assets/js/jquery.mobile.custom.min.js'>"
							+ "<"+"/script>");
	</script>
	<script src="assets/js/bootstrap.min.js"></script>
	
	<!-- page specific plugin scripts -->

		<!--[if lte IE 8]>
		  <script src="assets/js/excanvas.min.js"></script>
		<![endif]-->
		<script src="assets/js/jquery-ui.custom.min.js"></script>
		<script src="assets/js/jquery.ui.touch-punch.min.js"></script>
		<script src="assets/js/chosen.jquery.min.js"></script>
		<script src="assets/js/fuelux/fuelux.spinner.min.js"></script>
		<script src="assets/js/date-time/bootstrap-datepicker.min.js"></script>
		<script src="assets/js/date-time/bootstrap-timepicker.min.js"></script>
		<script src="assets/js/date-time/moment.min.js"></script>
		<script src="assets/js/date-time/daterangepicker.min.js"></script>
		<script src="assets/js/date-time/bootstrap-datetimepicker.min.js"></script>
		<script src="assets/js/bootstrap-colorpicker.min.js"></script>
		<script src="assets/js/jquery.knob.min.js"></script>
		<script src="assets/js/jquery.autosize.min.js"></script>
		<script src="assets/js/jquery.inputlimiter.1.3.1.min.js"></script>
		<script src="assets/js/jquery.maskedinput.min.js"></script>
		<script src="assets/js/bootstrap-tag.min.js"></script>
		<script src="assets/js/typeahead.jquery.min.js"></script>
		<script src="assets/js/gritterNotification.js"></script>
		<script src="assets/js/bootbox.min.js"></script>
		<script src="assets/js/datepicker.js"></script>
		<!-- selectpicker js -->
        <script type="text/javascript" src="assets/js/selectMin.js"></script>
		
		<!-- for datatable -->
		<!-- <script src="assets/js/datatable/datatables.responsive.min.js"></script>
		<script src="assets/js/datatable/jquery.dataTables.min.js"></script>
		<script src="assets/js/dataTables.bootstrap.js"></script>  -->

		<!-- ace scripts -->
		<script src="assets/js/ace-elements.min.js"></script>
		<script src="assets/js/ace.min.js"></script>
		<script src="assets/js/home.js"></script>
		
	<script>
	$("#dataTableScriptLoaded").click(function(){
		$.getScript("assets/js/dataTables.bootstrap.js");
	});
	</script>	
	
	<script type="text/javascript">
		//Load content via ajax
		jQuery(function($) {
			if ('enable_ajax_content' in ace) {
				var options = {
					content_url : function(url) {
						//this is for Ace demo only, you should change it
						//please refer to documentation for more info
						var path = document.location.pathname;
						return "/ElecnorIssueTracker/"+url;
					},
					default_url : 'homeScreen'//default url
				}
				ace.enable_ajax_content($, options)
			}

		})
	</script>
    <script>
    /* $('#menuBox li').click(function(){
	    $('#menuBox li').removeClass('active');
	    $(this).addClass('active');
	}); */
  
  //dynamically content for breadcrumb
    $('#headertab li a').click(function() {
        var thisitem = $(this).html();
        var insertText =document.getElementById("insertContent");
        //alert(thisitem);
        insertText.innerHTML = thisitem ;
    });
	$('#homeLink').click(function() {
        var thisitem = $(this).html();
        var insertText =document.getElementById("insertContent");
        insertText.innerHTML = thisitem ;
    });
    </script>


	<div class="tooltip top in"
		style="top: 342px; left: 1191px; display: none;">
		<div class="tooltip-inner">search engines : 24.5%</div>
	</div>
	<div class="tooltip top in" style="display: none;">
		<div class="tooltip-inner"></div>
	</div>

	<div id="createIssue" class="modal fade" tabindex="-1" aria-hidden="false" data-backdrop="static" 
         data-keyboard="false" >
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header" style="background: rgb(239, 243, 248);">
						<button type="button" class="close" data-dismiss="modal" onclick="resetCreateIssueForm();" style="opacity:3 !important;">
						<span class="badge pull-right" style="position: relative;top: 8px;">×</span></button>
						<h5 class="blue bigger" id="createNewIssueHeaderId"><i class="fa fa-edit fa-fw txt-color-blue"></i> Create New Issue</h5>
					</div>

					<div class="modal-body">
						<div class="row">
						  <div class="col-md-12">
                               <div class="form-horizontal">
                              <form id="createIssueFormId" name="createIssueFormId" method="POST" commandName="issueTrackerMasterForm">
                              <input type="hidden" id="issueSno" name="issueSno">
                               <div class="form-group">
								<label class="col-sm-2 control-label no-padding-right"
									for="form-field-1">Application <span style="color: #FF2600!important">*</span></label>
								<div class="col-md-4">
									<select class="form-control selectpicker show-tick applicationId"
										id="applicationId" tabindex="-1" name="applicationId" onchange="getModuleListForSelApp(this.value);">
									</select>
								</div>
								<label class="col-sm-2 control-label "
									for="form-field-1"> Module</label>
								<div class="col-md-4">
									<select class="form-control selectpicker show-tick"
										id="applicationModuleId" name="applicationModuleId">
									</select>
								</div>
							</div>
							
							 <div class="form-group">
								<label class="col-sm-2 control-label "
									for="form-field-1"> Project</label>
								<div class="col-md-4">
								<select class="form-control selectpicker show-tick"
										id="relatedProject" name="relatedProject">
									</select>
								</div>
								<label class="col-sm-2 control-label no-padding-right"
									for="form-field-1"> Issue Type <span style="color: #FF2600!important">*</span></label>
								<div class="col-md-4">
									<select class="form-control selectpicker show-tick"
										id="issueTypeId" name="issueTypeId">
									</select>
								</div>
							</div>
							<hr>
							<div class="form-group">
								<label class="col-sm-2 control-label "
									for="form-field-1"> Summary <span style="color: #FF2600!important">*</span></label>
								<div class="col-md-10">
									<input type="text" class="form-control" id="summary" name="summary" placeholder="Summary" 
										>
								</div>
							</div>
							<div class="form-group" >
								<label class="col-sm-2 control-label "
									for="form-field-1"> Description</label>
								<div class="col-md-10" >
									<textarea class="form-control"  id="description" name="description" placeholder="Description" rows="3" style="resize:none"></textarea>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label "
									for="form-field-1"> Status <span style="color: #FF2600!important">*</span></label>
								<div class="col-md-4">
								<select class="form-control selectpicker show-tick statusId"
										id="statusId" name="statusId" data-toggle = "" tabindex="-1">
									</select>
								</div>
								<label class="col-sm-2 control-label "
									for="form-field-1"> <!-- <span type=""
										class=" btn btn-link popover-dismiss" id="popOverMessageExt"
										data-container="body" data-toggle="popover"
										data-placement="bottom"
										style="visibility: visible;padding: 0px !important;"
										data-content="The message " data-original-title="SLA (Service Level Agreement)"> -->
											<span class="glyphicon glyphicon-flash orange" id="popOverMessageExt"></span>
									<!-- </span> --> Severity </label>
										<div class="popover bottom" id="popoverID" role="tooltip" 
											style="top: 196px; left: 208px; display: none;max-width: 333px !important;">
											<div class="arrow"></div>
											<h3 class="popover-title">SLA (Service Level Agreement)</h3>
											<!-- <div class="popover-content"><div><b>Low :</b>due Date is 12 days</div><br>
											                             <div><b>Normal :</b>due Date is 17 days</div><br>
											                             <div><b>High :</b>due Date is 1 days</div>
											                             </div> -->
											 <div class="popover-content" style="width: 333px !important;">
											 <table class="center table table-striped table-bordered table-hover" style="  margin-bottom: 0px;">
											 <thead><tr><th></th><th>Low</th><th>Normal</th><th>High</th><th>Urgent</th></tr></thead>
											 <tbody>
											 <tr><td>Due date (working days)</td><td>10</td><td>5</td><td>3</td><td>1</td></tr>
											 </tbody>
											 </table>
											                             </div>                            
										</div>
										<div class="col-md-4">
									<select class="form-control selectpicker show-tick"
										id="severityId" name="severityId" onchange="setDueDatebasedOnSeverity();">
									</select>
								</div>
							</div>
							<div class="form-group" >
								<label class="col-sm-2 control-label "
									for="form-field-1"> Reported Date</label>
								<div class="col-md-4">
									<input type="text" class="form-control" id="startDate" name="startDate" placeholder="Date" 
										>
								</div>
								<label class="col-sm-2 control-label"
									for="form-field-1"> Due date </label>
								<div class="col-md-4">
									<input type="text" class="form-control" id="dueDate" name="dueDate" placeholder="Date" 
										class="" readonly='true'>
								</div>
							</div>
							<div class="form-group" style="display: none" id="assigneeDivId">
								<label class="col-sm-2 control-label "
									for="form-field-1"> Assignee </label>
								<div class="col-md-10">
									<select class="form-control "
										id="asigneeId" name="asigneeId">
									</select>
								</div>
							</div>
							<div class="form-group" style="display: none" id="estimateAndPercentageDoneDivId">
							<label class="col-sm-2 control-label "
									for="form-field-1"> Estimate Time</label>
								<div class="col-md-4">
									<input type="text" class="form-control" id="estimatedTime" name="estimatedTime" placeholder="Estimate Time" 
										>
								</div>
								<label class="col-sm-2 control-label "
									for="form-field-1"> Percentage Done </label>
								<div class="col-md-4">
								   <input type="number" id="percentageDoneId" name="percentageDoneId" style="height: 34px !important;"/>
									
								</div>
							</div>
							
									<div class="form-group">
										<label class="col-sm-2 control-label " for="form-field-1">
											Attachment</label>
										<div class="col-xs-10">
											<label class="ace-file-input ace-file-multiple"><input
												multiple="" type="file" id="uploadFile" name="uploadFile"><font color="red" size= 1px>(max size: 5 MB)</font>
										<a class="remove" href="#"><i class=" ace-icon fa fa-times"></i></a></label>
										</div>
									</div>
								</form> 
							</div>
						</div>	
								
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn btn-sm" data-dismiss="modal" onclick="resetCreateIssueForm();">
							<i class="ace-icon fa fa-times"></i>
							Cancel
						</button>
						<button class="btn btn-sm btn-success ladda-button" data-style="expand-right" onclick="setSaveOrUpdateIssue(this);">
							<i class="ace-icon fa fa-check"></i>
							<span id="createIssueButtonSpanId">Create</span>
						</button>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
		var  nowTemp = new Date();
		var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp
				.getDate(), 0, 0, 0, 0);
 		$("#dueDate").datepicker({
			format : "mm-dd-yyyy",
			 startDate: now
		}).on('changeDate', function(ev) { 
			$('#dueDate').datepicker('hide');
		});
		
		function openModal(){
			$('#createIssue').modal('show');
		}
	</script>

<div class="well col-md-5 hide">
<span class="red">Its is on home.jsp</span>
	<p> Gritter Notification
		<button class="btn" onclick="gritterInfo()">Info</button>
		<button class="btn btn-info" onclick="gritterError()">Error</button>
		<button class="btn btn-success" onclick="gritterSuccess()">Success</button>
	</p>
	<p> Custon alert,popup,tooltip
		<button class="btn btn-success" onclick="bootboxAlert()">Custom
			Alert</button>
		<span class="btn btn-sm" data-rel="popover" title=""
			data-content="Heads up! This alert needs your attention, but it's not super important."
			data-original-title="Default">Popup</span>
		<span class="btn btn-sm" data-rel="tooltip" title=""
			data-original-title="Default">Tooltip</span> 	
	</p>
	</div>
	
	<!-- Global search and Selecting Issue form dashboard.jsp :UI  -->
	<div style="display:none">
		   <form id="issueInDetailsForm" name="issueInDetailsForm" method="POST" action="getIssueDetails">
		   <input type="hidden" name="issueIdToGetDetails" id="issueIdToGetDetails">
		   </form>
	</div>
	
		
	<script>
	// Gritter Notification starts here
		function gritterInfo(InfoContent) {
			$.gritter
					.add({
						title : '<b>Issue Tracker : Info !!</b>',
						text : InfoContent,
						sticky : true,
						time : '',
						sticky : false,
						before_open : function() {
							if ($('.gritter-item-wrapper').length >= 3) {
								return false;
							}
						},
						class_name : 'gritter-info',
					});
			return false;
		}

		function gritterError(ErrorContent) {
			$.gritter
					.add({
						title : '<b>Issue Tracker : Error !!</b>',
						text : ErrorContent,
						sticky : false,
						time : '',
						class_name : 'gritter-error'
					});
			return false;
		}

		function gritterSuccess(successContent) {
			$.gritter
					.add({
						title : '<b>Issue Tracker : Success !!</b>',
						text : successContent,
						class_name : 'gritter-success'
					});
			return false;
		}
		function gritterWarningMsg(ErrorContent) {
			$.gritter
					.add({
						title : '<b>Issue Tracker : Warning !!</b>',
						text : ErrorContent,
						sticky : false,
						time : '',
						class_name : 'gritter-warning'
					});
			return false;
		}
	//ENDS

	// Bootbox Alert starts here
		function bootboxAlert() {
			bootbox.dialog({
				message : "<span class='bigger-110'>Are you sure ?</span>",
				buttons : {
					"ok" : {
						"label" : "<i class='ace-icon fa fa-check'></i> Ok!",
						"className" : "btn-sm btn-success",
						"callback" : function() {
							gritterSuccess();
						}
					},
					"danger" : {
						"label" : "Cancel!",
						"className" : "btn-sm btn-info",
						"callback" : function() {
							gritterError();
						}
					},
				}
			});
		}
	//ENDS

		jQuery(function($) {
			$('[data-rel=tooltip]').tooltip();
			$('[data-rel=popover]').popover({
				html : true
			});
			
	// UploadFile script starts here	
			$('#uploadFile').ace_file_input({
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
			
			
			//$('#uploadFile')
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
					
					whitelist_ext = null;//all extensions are acceptable
					whitelist_mime = null;//all mimes are acceptable
				}
				var file_input = $('#uploadFile');
				file_input
				.ace_file_input('update_settings',
				{
					'btn_choose': btn_choose,
					'no_icon': no_icon,
					'allowExt': whitelist_ext,
					droppable:true,
					'allowMime': whitelist_mime
				})
				file_input.ace_file_input('reset_input');
				
				file_input
				.off('file.error.ace')
				.on('file.error.ace', function(e, info) {
					//console.log(info.file_count);//number of selected files
					//console.log(info.invalid_count);//number of invalid files
					//console.log(info.error_list);//a list of errors in the following format
					
					//info.error_count['ext']
					//info.error_count['mime']
					//info.error_count['size']
					
					//info.error_list['ext']  = [list of file names with invalid extension]
					//info.error_list['mime'] = [list of file names with invalid mimetype]
					//info.error_list['size'] = [list of file names with invalid size]
					
					/**
					if( !info.dropped ) {
						//perhapse reset file field if files have been selected, and there are invalid files among them
						//when files are dropped, only valid files will be added to our file array
						e.preventDefault();//it will rest input
					}
					*/
					//if files have been selected (not dropped), you can choose to reset input
					//because browser keeps all selected files anyway and this cannot be changed
					//we can only reset file field to become empty again
					//on any case you still should check files with your server side script
					//because any arbitrary file can be uploaded by user and it's not safe to rely on browser-side measures
				});
			
			});
	    //ENDS
             
	// Spinner script starts here
	       
			/* $('#percentageDoneId').ace_spinner({value:0,min:0,max:100,step:10, btn_up_class:'btn-info' , btn_down_class:'btn-info'})
			.closest('.ace-spinner')
			.on('changed.fu.spinbox', function(){
				//alert($('#spinner1').val())
			});  */
	     //ENDS
			
	// Timepicker script starts here
			$('#timepicker1').timepicker({
				minuteStep: 1,
				showSeconds: true,
				showMeridian: false
			}).next().on(ace.click_event, function(){
				$(this).prev().focus();
			});
	    //ENDS
	
		});
		//code for validation for allowing only numbers in input boxes
		$('input').on('keypress', function(e) {
			if (this.id == "custSearch") {//this is for number validation
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
		//$('#popOverMessageExt').popover();
		$( "#popOverMessageExt" ).hover(
				  function() {
				    $('#popoverID').css('display','block')
				  }, function() {
				    $('#popoverID').css('display','none')
				  }
				);
	</script>
	
	<!-- Start of modal for progress bar -->
	<div class="modal fade" id="loadingModalDicId" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		data-backdrop="static">
		<div class="modal-dialog"
			style="position: absolute; top: 30%; left: 27%;">
			<div class="modal-content">
				<div class="modal-body" style="background: #eff3f8">
					<div class="form-horizontal">
						<div class="progress progress-striped active">
							<div class="progress-bar" role="progressbar" aria-valuenow="45"
								aria-valuemin="0" aria-valuemax="100" style="width: 89%;">
								<span class="sr-only">45% Complete</span>
							</div>
						</div>
						<center>
							<div>
								<h4>Loading....</h4>
							</div>
						</center>
					</div>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- End of modal for progress bar -->
</body>
</html>