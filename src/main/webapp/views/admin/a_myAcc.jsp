<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

</head>
<style type="text/css">
.container {
	display: grid;
	grid-template-areas: "main";
	justify-items: center;
}

.main {
	width: 100px;
	height: 100px;
	background: #ccccff;
	border-radius: 50%;
	text-decoration: none;
	cusor: pointer;
	background: #0066ff;
	border-radius: 4px;
	text-align: center;
}
.btn {
	color: white;
	text-decoration: none;
	cusor: pointer;
	background: #0066ff;
	border-radius: 4px;
	padding: 5px;
}
</style>
<body>
	
	<%@include file="a_nav.jsp" %>
	<div class="container">
	<form calss="main">
	账户：<label>123</label><br/><br/>
	密码：<label>123</label><br/><br/>
	更改密码：<input typpe="password" /><br/><br/>
	确认密码：<input typpe="password" /><br/><br/>
	<input class="btn" type="submit" value="OK">
	</form>
	</div>
	
</body>
</html>