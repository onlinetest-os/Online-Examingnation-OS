<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="${APP_PATH}/static/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script src="${APP_PATH}/static/assets/js/jquery.min.js"></script>
<script src="${APP_PATH}/static/assets/js/bootstrap.min.js"></script>
</head>
<style>
#right li{
	float:left;
}
</style>
<body>
<nav class="navbar navbar-inverse" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<a href="Student_main.jsp" class="navbar-brand">ExamSystem</a>
		</div>
		<ul class="nav pull-right" id="right">
			<li><img src="${APP_PATH}/static/assets/images/users/avatar-1.jpg" style="width:50px;height:50px;border-radius:50%"/></li>
			<li><a><strong>${student.stuName }</strong></a></li>
			<li><a href="index.jsp">退出</a></li>
		</ul>
	</div>
	</nav>
</body>
</html>