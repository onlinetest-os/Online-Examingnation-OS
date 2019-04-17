<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
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

.font {
	font-size: 30px;
	color: white;
}
</style>
</head>
<body>
	<%@include file="s_nav.jsp"%>
	<div class="container">
		<a class="main" href=""><font class="font">Get Paper</font></a>
	</div>
</body>
</html>