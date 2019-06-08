<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="${APP_PATH }/static/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script src="${APP_PATH }/static/assets/js/jquery.min.js"></script>
<script src="${APP_PATH }/static/assets/js/bootstrap.min.js"></script>
</head>
<style>
.iframe_right {
	float: left;
	width: 700px;
	height: 400px;
	margin-top: 20px;
	border: 0px;
	background: aliceblue;
}
</style>
<body>
	<nav class="navbar navbar-inverse" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<a href="admin_manage_account" class="navbar-brand">ExamSystem</a>
		</div>
		<div>
			<ul class="nav navbar-nav">
				<li><a href="admin_manage_account">账户管理</a></li>
				<li><a href="admin_a_examClean">考试清理</a></li>
				<li><a href="admin_a_config">全局配置</a></li>
			</ul>
		</div>
		<ul class="nav pull-right">
			<li style="float:left;">
			<a href="#">${teacher.teaName }</a>
			</li>
			<li style="float:left;">
			<a href="admin_cancel">退出</a>
			</li>
		</ul>
	</div>
	</nav>
</body>
</html>