<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	application.setAttribute("APP_PATH", request.getContextPath());
%>
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
					<li class="nav-header">考前操作</li>
					<li><a href="teacher_t_newExam" target="main_right">新建考试</a></li>
					<li><a href="teacher_t_draftBox" target="main_right">草稿箱</a></li>
					<li><a href="teacher_t_examListMine" target="main_right">我的考试</a></li>
					<li><a href="teacher_t_examListAll" target="main_right">所有考试</a></li>
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