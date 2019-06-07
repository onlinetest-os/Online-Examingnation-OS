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
	<%@include file="t_nav.jsp" %>

<div class="container-fluid">
		<div class="row-fluid">
			<div class="col-md-2">
				<ul class="nav nav-list">
					<li class="nav-header">考中操作</li>
					<li><a href="teacher_t_viewExam?teaId=${teacher.teaId}" target="main_right">查看考试</a></li>
					<%-- <li><a href="teacher_t_addStudent?teaId=${teacher.teaId}" target="main_right">添加学生</a></li> --%>
					<li><a href="teacher_t_alertManage?teaId=${teacher.teaId}" target="main_right">通知管理</a></li>
					<li><a href="teacher_t_IPRelease?teaId=${teacher.teaId}" target="main_right">IP解绑</a></li>
				</ul>
			</div>
			<div class="col-md-10">
				<iframe id="frame" class="iframe_right"
					style="width: 950px; margin-left: 5px;" src="" name="main_right"></iframe>
			</div>
		</div>
	</div>
</body>
</html>