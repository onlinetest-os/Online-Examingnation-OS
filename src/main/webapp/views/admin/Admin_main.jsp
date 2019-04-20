<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="${APP_PATH }/static/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
</head>
<style>
	float: left;
	width: 700px;
	height: 400px;
	margin-top: 20px;
	border: 0px;
	background: aliceblue;
}
</style>
<body>
	<%@include file="a_nav.jsp" %>
	<a href="admin_get_teachers">测试查询</a>
	<div>
		<iframe id="frame" class="iframe_right"
			style="width: 100%; margin-left: 5px;" src="" name="main_right"></iframe>
	</div>
</body>
</html>