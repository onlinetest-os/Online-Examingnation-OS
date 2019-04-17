<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="${APP_PATH }/static/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script src="${APP_PATH }/static/assets/js/jquery.min.js"></script>
<script src="${APP_PATH }/static/assets/js/bootstrap.min.js"></script>
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

#img {
	border-radius: 50%;
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
<script type="text/javascript">
	function change() {
		var photo = document.getElementById("se");
		var img = document.getElementById("img");
		img.src = photo.value;
	}
</script>
</head>
<body>
	<%@include file="a_nav.jsp"%>
	<div class="container">
		<div class="row">
			<div class="span12">
				<div class="tabbable" id="tabs-24917">
					<ul class="nav nav-tabs">
						<li><a href="admin_get_teachers" >教师管理</a></li>
						<li><a href="#panel-666666" data-toggle="tab">添加账户</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane" id="panel-705759">
							<form>
								<input style="width: 300px; border-radius: 20px;" type="text"
									placeholder="输入工号搜索" />&nbsp;
								<button class="btn">搜索</button>
							</form>
							<table class="table">
								<thead>
									<tr>
										<th>选择</th>
										<th>姓名</th>
										<th>工号</th>
										<th>账户</th>
										<th>密码</th>
										<th>身份</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<form action="" method="get">
										<tr>
											<td><input type="checkbox" /></td>
											<td>1</td>
											<td>TB - Monthly</td>
											<td>123456789</td>
											<td>127.0.0.1</td>
											<td>教师</td>
											<td><button class="btn" type="submit">编辑</button>&nbsp;&nbsp;&nbsp;<button class="btn" type="submit">删除</button></td>
										</tr>
									</form>
									<form action="" method="get">
										<tr>
											<td><input type="checkbox" /></td>
											<td>1</td>
											<td>TB - Monthly</td>
											<td>123456789</td>
											<td>127.0.0.1</td>
											<td>教师</td>
											<td><button class="btn" type="submit">编辑</button>&nbsp;&nbsp;&nbsp;<button class="btn" type="submit">删除</button></td>
										</tr>
									</form>
								</tbody>
							</table>
							<div class="span12" style="text-align: center;">
								<ul class="pagination">
									<li><a href="#">«</a></li>
									<li><a href="#">1</a></li>
									<li><a class="active" href="#">2</a></li>
									<li><a href="#">3</a></li>
									<li><a href="#">4</a></li>
									<li><a href="#">5</a></li>
									<li><a href="#">6</a></li>
									<li><a href="#">7</a></li>
									<li><a href="#">»</a></li>
								</ul>
							</div>

						</div>
						<div class="tab-pane" id="panel-666666">
							<div class="container">
								<form calss="main">
									<br />
									账户&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
										type="text" /> <br /> <br />
									姓名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
										type="text" /><br /> <br />
									身份&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select
										id="shenfen"">
										<option value="admin" selected>管理员</option>
										<option value="teach">教师</option>
										<option value="stu">学生</option>
									</select><br /> <br /> 设置密码&nbsp;&nbsp;<input type="password" /><br />
									<br /> 确认密码&nbsp;&nbsp;<input type="password" /><br /> <br />
									照片&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select
										id="se" name="picture" onchange="change();">
										<option value="${APP_PATH }/static/assets/images/users/avatar-1.jpg" selected>管理员</option>
										<option value="${APP_PATH }/static/assets/images/users/avatar-2.jpg">教师</option>
										<option value="${APP_PATH }/static/assets/images/users/avatar-3.jpg">学生</option>
									</select><br /> <br /> 预览
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img
										id="img" src="${APP_PATH }/static/assets/images/users/avatar-1.jpg"
										style="width: 170px; height: 170px" /><br /> <br />
									<button class="btn" style="width: 250px;" type="submit">OK</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>