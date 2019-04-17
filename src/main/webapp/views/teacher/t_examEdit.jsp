<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="../assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script src="../assets/js/jquery.min.js"></script>
<script src="../assets/js/bootstrap.min.js"></script>
<style type="text/css">
div {
	margin-top: 5px;
	text-align: center;
}
.container {
	display: grid;
	grid-template-columns:repeat(2,170px);
	justify-items: stretch;
	height:50px;
	margin: 50px;
}
.items {
	margin-top:10px; 
}
.btn {
	color:white;
	text-decoration: none;
	cusor: pointer;
	background: #0066ff;
	border-radius: 4px;
}
</style>
</head>

<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="tabbable" id="tabs-802004">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#panel-261733" data-toggle="tab">更改考试</a></li>
						<li><a href="#panel-246276" data-toggle="tab">临时添加学生</a></li>
						<li><a href="#panel-261734" data-toggle="tab">考试提前开始</a></li>
					</ul>
					<div  class="tab-content">
						<div  class="active tab-pane" id="panel-261733">
							<form action="" method="get" class="container">
								<font class="items"></font>
								<font class="items">考试名称</font><input class="items" type="text" name="exam_name" />									
								<font class="items">班级</font><select class="items">
									<option>1</option>
									<option>2</option>
									<option>3</option>
								</select>										
								<font class="items">试卷</font><select class="items">
									<option>1</option>
									<option>2</option>
									<option>3</option>
								</select>									
								<font class="items">时长</font><font class="items"><select>
									<option>1</option>
									<option>1.5</option>
									<option>2</option>
									</select>小时</font>						
								<font class="items">开始</font><input class="items" id="meeting" type="datetime-local" value="2019-01-13" />										
								<font class="items">结束</font><input class="items" id="meeting" type="datetime-local" value="2019-01-13" />									
								<font class="items">自动开始</font><input class="items" type="checkbox" />										
								<font class="items"></font><input class="items btn" type="submit" value="OK" />
							</form>
						</div>
						<div class="tab-pane" id="panel-246276">
							<form>
								姓名：<input type="text" /><br /><br /> 学号：<input type="text" /><br /><br />
								<input class="btn" type="submit" value="OK">
							</form>
						</div>
						<div class="tab-pane" id="panel-261734">
							<input class="btn" type="button" value="开始考试" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>














</body>
</html>