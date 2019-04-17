<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ExamSystem</title>
	<link rel="stylesheet" type="text/css" href="${APP_PATH }/static/assets/style.css">
</head>
<body>
	<!-- <script src="/demos/googlegg.js"></script> -->
	
	<div class="tab" style="margin-top: 0px;">
	<div class="box" style="color: white;">
		<ul class="menus" style="height:auto;margin-top: 100px;background: white">
			<li class="bg">I am a student</li>
			<li >I am a teacher</li>
			<li>I am a administrator</li>
		</ul>
		<div class="right" style="height:600px;background: #ccccff">
			<div class="scroll" style="margin-top: -900px;">
			<div class="tab_right">
					<%@include file="student/s_login.jsp"%>
				</div>
				<div class="tab_right">
					<%@include file="teacher/t_login.jsp"%>
				</div>
				<div class="tab_right" >
					<%@include file="admin/a_login.jsp"%>
				</div>
			</div>
		</div>
	</div>
	<div class="clear"></div>
</div>

<script src="${APP_PATH }/static/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${APP_PATH }/static/assets/index.js"></script>
	<%@include file="inclu/copyright.jsp"%>
</body>
</html>
