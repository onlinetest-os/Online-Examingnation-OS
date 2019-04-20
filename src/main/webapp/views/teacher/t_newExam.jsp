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
<style type="text/css">
*{max-height: 25px;margin-top: 10px;}
.container {
	display: grid;
	grid-template-columns:repeat(2,170px);
	justify-items: stretch;
	height:50px;
	margin: 50px;
}
.btn {
	color:white;
	text-decoration: none;
	cusor: pointer;
	background: #0066ff;
	border-radius: 4px;
}
.items {
	margin-top:10px;
	height:30px;
}
</style>
</head>

<body>
<div style="text-align: center;">
	<form  action="" class="container" method="get">
			<font class="items"></font>
			<font class="items">考试名称</font>
			<input class="items" type="text" name="exam_name" />
					<font class="items">班级</font>
					<input class="items" type="text" name="classNumber" />
<!-- 					<select class="items"> -->
<!-- 						<option>1</option> -->
<!-- 						<option>2</option> -->
<!-- 						<option>3</option> -->
<!-- 					</select>						 -->
<!-- 					<font class="items">试卷</font><select class="items"> -->
<!-- 						<option>1</option> -->
<!-- 						<option>2</option> -->
<!-- 						<option>3</option> -->
<!-- 					</select>			 -->
					<font class="items">时长</font><font class="items"><select >
						<option>1</option>
						<option>1.5</option>
						<option>2</option>
						<option>2.5</option>
					</select>小时</font>
					<font class="items">开始</font><input class="items" id="meeting" type="datetime-local" value="2019-01-13" />		
<!-- 					<font class="items">结束</font><input class="items" id="meeting" type="datetime-local" value="2019-01-13" />		 -->
					<font class="items">自动开始</font><input class="items" type="checkbox" />			
					<a class="items"></a><input class="items btn" style="margin-top: 0px;height:30px;" type="submit" value="OK" />		
	</form>
	
</div>
	

</body>
</html>