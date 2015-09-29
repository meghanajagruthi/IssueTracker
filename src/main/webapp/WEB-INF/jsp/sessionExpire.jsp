<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta charset="utf-8">
<title>Issue Tracker</title>

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

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lte IE 8]>
		<script src="../assets/js/html5shiv.js"></script>
		<script src="../assets/js/respond.min.js"></script>
		<![endif]-->
		
<style type="text/css">
.lockscreen {
height: 250px;
left: 50%;
margin-left: -239px;
margin-top: -185px;
position: absolute;
top: 50%;
width: 541px;
}
.lockscreen .logo+div {
background: #FFF;
box-shadow: -31px 32px 53px rgba(0,0,0,.2);
overflow: hidden;
padding: 13px;
position: relative;
}
.lockscreen .logo+div>img+div {
float: right;
width: 370px;
}
.lockscreen .logo+div>img+div>:first-child {
margin-top: 0;
}
h1 {
letter-spacing: -1px;
font-size: 24px;
margin: 10px 0;
}
h1, h2, h3, h4 {
margin: 0;
font-family: "Open Sans",Arial,Helvetica,Sans-Serif;
font-weight: 300;
}
.lockscreen .logo+div>img+div>:first-child+p {
margin-bottom: 12px;
}
.text-muted {
color: #999;
}
p {
margin: 0 0 9px;
}
.input-group {
position: relative;
display: table;
border-collapse: separate;
}
.margin-top-5 {
margin-top: 5px!important;
}
.no-margin {
margin: 0!important;
}
p {
margin: 0 0 9px;
}

</style>
</head>
<body class="no-skin" style="background-color:rgb(239, 240, 240) !important;">
	
	
	<form class="lockscreen flipInY" action="">
	<div class="logo">
				</div>
				<div>
				<img src="./assets/img/logo.png" alt="" style="border: 1px solid #ccc;padding: 8px 0px;width: 120px;\height: 100px;">
					<div>
						<h1 style="color:#B3B3B3;">Oooops : Session Expired !</h1>
						<p class="text-muted">
						</p>
						<h3>Your session has been expired. Please login again from Ecosystem.</h3>
						<p style="margin-top: 15px!important;visibility: hidden;">
							Go Back to Home Page ? <a href="home#dashboard"> Click here</a>
						</p>
					</div>

				</div>
			</form>
			

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
		
		
		<!-- for datatable -->
		<!-- <script src="assets/js/datatable/datatables.responsive.min.js"></script>
		<script src="assets/js/datatable/jquery.dataTables.min.js"></script>
		<script src="assets/js/dataTables.bootstrap.js"></script>  -->

		<!-- ace scripts -->
		<script src="assets/js/ace-elements.min.js"></script>
		<script src="assets/js/ace.min.js"></script>
</body>
</html>