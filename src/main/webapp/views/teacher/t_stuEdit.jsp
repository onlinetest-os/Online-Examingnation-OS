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
	grid-template-columns:1fr 1fr;
	grid-template-rows: auto;
	justify-items: center;
}
.btn {
	color: white;
	text-decoration: none;
	cusor: pointer;
	background: #0066ff;
	border-radius: 4px;
	padding: 5px;
}

ul.pagination {
	display: inline-block;
	padding: 0;
	margin: 0;
}

ul.pagination li {
	display: inline;
}

ul.pagination li a {
	color: black;
	float: left;
	padding: 8px 16px;
	text-decoration: none;
}
</style>

</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<table class="table">
					<thead>
						<tr>
							<th>姓名</th>
							<th>学号</th>
							<th>性别</th>
							<th>班级</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						
								<td>燕付龙</td>
								<td>123</td>
								<td>男</td>
								<td>2班</td>
								<td><a href="t_stuEditDetail.jsp" class="btn" style="height: 30px;" >编辑</a>
						</tr>
					</tbody>
				</table>
				<div class="container">
		<p class="items btn">
			<a style="color: white;" href="#">上一页</a>
		</p>
		<p class="items btn">
			<a style="color: white;" href="#">下一页</a>
		</p>
	</div>
			</div>
		</div>
	</div>
	</div>

</body>
</html>