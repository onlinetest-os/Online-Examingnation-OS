<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta name="description"
	content="A fully featured admin theme which can be used to build CRM, CMS, etc.">
<meta name="author" content="Coderthemes">

<link rel="shortcut icon"
	href="${APP_PATH }/static/assets/images/favicon_1.ico">

<link href="${APP_PATH }/static/assets/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="${APP_PATH }/static/assets/css/core.css" rel="stylesheet"
	type="text/css">
<link href="${APP_PATH }/static/assets/css/icons.css" rel="stylesheet"
	type="text/css">
<link href="${APP_PATH }/static/assets/css/components.css"
	rel="stylesheet" type="text/css">
<link href="${APP_PATH }/static/assets/css/pages.css" rel="stylesheet"
	type="text/css">
<link href="${APP_PATH }/static/assets/css/menu.css" rel="stylesheet"
	type="text/css">
<link href="${APP_PATH }/static/assets/css/responsive.css"
	rel="stylesheet" type="text/css">

<script src="${APP_PATH }/static/assets/js/modernizr.min.js"></script>
</head>
<body>

<!-- 	<div id="mydiv">this is student login</div> -->
	<div class="wrapper-page">
		<div class="panel panel-color panel-primary panel-pages"
			style="height: 440px;">
			<div class="panel-heading bg-img">
				<h3 class="text-center m-t-10 text-white">
					<strong>Teacher</strong>
				</h3>
			</div>


			<div class="panel-body">
				<form id="teacher_login_form" class="form-horizontal m-t-20">

					<div class="form-group">
						<div class="col-xs-12">
							<input id="teaNumber" class="form-control input-lg" type="text" required=""
								placeholder="Username">
						</div>
					</div>

					<div class="form-group">
						<div class="col-xs-12">
							<input id="teaPassword" class="form-control input-lg" type="password" required=""
								placeholder="Password">
						</div>
					</div>

					<div class="form-group text-center m-t-40">
						<div class="col-xs-12">
							<button
							type="button"
							id="teacher_login_form_login_btn"
								class="btn btn-primary btn-lg w-lg waves-effect waves-light"
								>Log In</button>
						</div>
					</div>

					<div class="form-group m-t-30">
						<div class="col-sm-7">
							<a href="#"><i class="fa fa-lock m-r-5"></i>
								Forgot your password?</a>
						</div>
						<div class="col-sm-5 text-right">
							<a href="#">Create an account</a>
						</div>
					</div>
				</form>
			</div>

		</div>
	</div>


	<script>
		var resizefunc = [];
	</script>

	<!-- Main  -->
	<script src="${APP_PATH }/static/assets/js/jquery.min.js"></script>
	<script src="${APP_PATH }/static/assets/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/static/assets/js/detect.js"></script>
	<script src="${APP_PATH }/static/assets/js/fastclick.js"></script>
	<script src="${APP_PATH }/static/assets/js/jquery.slimscroll.js"></script>
	<script src="${APP_PATH }/static/assets/js/jquery.blockUI.js"></script>
	<script src="${APP_PATH }/static/assets/js/waves.js"></script>
	<script src="${APP_PATH }/static/assets/js/wow.min.js"></script>
	<script src="${APP_PATH }/static/assets/js/jquery.nicescroll.js"></script>
	<script src="${APP_PATH }/static/assets/js/jquery.scrollTo.min.js"></script>

	<script src="${APP_PATH }/static/assets/js/jquery.app.js"></script>
	
	<!-- 业务js -->
	<script type="text/javascript">
	//教师登录
	$("#teacher_login_form_login_btn").click(function() {
		//alert("发送ajax請求！");
		//发送请求，核对教師信息
		var teaNumber = $("#teaNumber").val();
		var teaPassword = $("#teaPassword").val();
		$.ajax({
			url : "${APP_PATH}/teacher_login",
			type : "POST",
			data : {
				teaNumber : teaNumber,
				teaPassword : teaPassword
			},
			success : function(result) {
				//1、登录成功，跳转页面
				if (result.code == 100) {
					alert("登录成功！");
					var url = "${APP_PATH}/teacher_main";
					window.open(url, '_self');
				} else {
					alert("账号或密码错误！");
				}
			}
		});
	});
</script>
</body>
</html>