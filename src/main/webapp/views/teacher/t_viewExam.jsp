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
.container::before{
display:none;
}
.container {
	display: grid;
	grid-template-columns: 1fr 1fr 1fr 1fr;
	grid-template-rows: auto;
	justify-items: center;
}

.bar {
	padding: 10px;
	background: #ccccff;
	border-radius: 5px;
	margin: 10px;
}

.items {
	width : 100px;
	height: 35px;
	padding: 10px;
	background: #3366ff;
	border-radius: 20px;
	margin: 10px;
	text-align: center;
	width: 100px;
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

	<div class="bar">
		<h3>在线比例</h3>
		<div class="progress">
			<div
				class="progress-bar progress-bar-info progress-bar-striped active"
				role="progressbar" aria-valuenow="60" aria-valuemin="0"
				aria-valuemax="100" style="width: 60%">
				50
			</div>
		</div>

	</div>

	<div class="bar">
		<h3>提交比例</h3>
		<div class="progress">
			<div
				class="progress-bar progress-bar-info progress-bar-striped active"
				role="progressbar" aria-valuenow="60" aria-valuemin="0"
				aria-valuemax="100" style="width: 60%">
				60
			</div>
		</div>

	</div>


	<div class="container">
		<p class="items btn">
			<a style="color: white;" href="#">开始考试</a>
		</p>
		<p class="items btn">
			<a style="color: white;" href="#">结束考试</a>
		</p>
		<p class="items btn">
			<a style="color: white;" href="#">登录名单</a>
		</p>
		<p class="items btn">
			<a style="color: white;" href="#">提交名单</a>
		</p>
	</div>
</body>
</html>