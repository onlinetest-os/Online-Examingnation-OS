<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="" %>
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

	<c:if test="${allNum!=null}">
		<div class="bar">
		<h3>在线比例</h3>
		<div class="progress">
			<div
				class="progress-bar progress-bar-info progress-bar-striped active"
				role="progressbar" aria-valuenow="${submitNum}" aria-valuemin="0"
				aria-valuemax="${allNum}" style="width: ${onlineNum }%">
			</div>
		</div>

	在线：${onlineNum }人 &nbsp; 离线：${allNum-onlineNum }人
	</div>

	<div class="bar">
		<h3>提交比例</h3>
		<div class="progress">
			<div
				class="progress-bar progress-bar-info progress-bar-striped active"
				role="progressbar" aria-valuenow="${submitNum}" aria-valuemin="0"
				aria-valuemax="${allNum}" style="width: ${submitNum}%">
				
			</div>
		</div>
	已提交：${submitNum}人 &nbsp; 未提交：${allNum-submitNum }人
	</div>


	<div class="container">
		<p class="items btn">
			<a style="color: white;"  id="startBtn">开始考试</a>
		</p>
		<p class="items btn">
			<a style="color: white;"  id="endBtn">结束考试</a>
		</p>
		<p class="items btn">
			<a style="color: white;" href="teacher_showLoginOrder?eId=${eId}" 
			id="loginOrderBtn">登录名单</a>
		</p>
		<p class="items btn">
			<a style="color: white;" href="teacher_showSubmitOrder?eId=${eId}" 
			id="submitOrderBtn">提交名单</a>
		</p>
	</div>
	</c:if> 
	<script type="text/javascript">
		//开始考试
		$("#startBtn").click(function() {
			//alert("start");
			$.ajax({
				url:"${APP_PATH}/teacher_start_exam?eId=${eId}",
				type:"GET",
				success:function(result){
					alert(result.msg);
				}
			});
		});
		//结束考试
		$("#endBtn").click(function() {
			//alert("end");
			$.ajax({
				url:"${APP_PATH}/teacher_end_exam?eId=${eId}",
				type:"GET",
				success:function(result){
					alert(result.msg);
				}
			});
		});
		//登录名单
		$("#loginOrderBtn").click(function() {
			//alert("loginOrder");
		});
		//提交名单
		$("#submitOrderBtn").click(function() {
			//alert("submitOrder");
		});
	</script>
	
</body>
</html>