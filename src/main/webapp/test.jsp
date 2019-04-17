<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>test ajax</title>
<%
	application.setAttribute("APP_PATH", request.getContextPath());
%>

</head>
<body>
<div>${exams}</div>
	<c:forEach items="${exams}" var="exam">
		<div>${exam }</div>
	</c:forEach>

	<input id="stuNumber">
	<input id="stuPassword">
	<button id="login_form_login_btn" type="button" >提交ajax请求</button>
	<div id="mydiv">hello</div>
	<script src="${APP_PATH}/static/assets/js/jquery.min.js"></script>
	<script type="text/javascript">
	//学生登录
	$("#login_form_login_btn").click(function() {
		alert("发送ajax請求！");
		//发送请求，核对学生信息
		var stuNumber = $("#stuNumber").val();
		var stuPassword = $("#stuPassword").val();
		$.ajax({
			url : "${APP_PATH}/student_login",
			type : "POST",
			data : {
				stuNumber : stuNumber,
				stuPassword : stuPassword
			},
			success : function(result) {
				//1、登录成功，跳转页面
				if (result.code == 100) {
					alert("登录成功！");
					var url = "${APP_PATH}/student_main";
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