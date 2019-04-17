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
.btn {
	color: white;
	text-decoration: none;
	cusor: pointer;
	background: #0066ff;
	border-radius: 4px;
	padding: 5px;
}
</style>
</head>
<body>
	<%@include file="a_nav.jsp" %>
	<div class="container">
	<form calss="main">
		分页记录数<input type="text" /><br /> <br /> 
		手动开启考试时间阈值<input type="text" />分钟<br /><br /> 
		是否允许主考教师清理删除&nbsp;&nbsp;&nbsp;<input type="checkbox" /><br /><br /> 
		<button class="btn" type="submit">OK</button>
	</form>
	</div>

</body>
</html>