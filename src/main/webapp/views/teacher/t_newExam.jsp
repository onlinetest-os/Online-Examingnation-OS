<%@page import="phion.onlineexam.bean.StaticResources"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="${APP_PATH }/static/assets/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<script src="${APP_PATH }/static/assets/js/jquery.min.js"></script>
<script src="${APP_PATH }/static/assets/js/bootstrap.min.js"></script>
<style type="text/css">
.container {
	display: grid;
	grid-template-columns: repeat(2, 170px);
	justify-items: stretch;
	height: 50px;
	margin: 50px;
}

.container::before {
	display: none;
}

.btn {
	color: white;
	text-decoration: none;
	cusor: pointer;
	background: #0066ff;
	border-radius: 4px;
}

.items {
	margin-top: 10px;
	height: 30px;
}

.a-upload {
	padding: 4px 10px;
	height: 30px;
	line-height: 20px;
	position: relative;
	cursor: pointer;
	color: white;
	background: #0066ff;
	border: 1px solid #ddd;
	border-radius: 4px;
	overflow: hidden;
	display: inline-block;
	*display: inline;
	*zoom: 1
}

.a-upload  input {
	position: absolute;
	font-size: 100px;
	right: 0;
	top: 0;
	opacity: 0;
	filter: alpha(opacity = 0);
	cursor: pointer
}
</style>


</head>

<body>
	<div>

		<form action="teacher_check_exam" method="post"
			enctype="multipart/form-data" id="form1">
			<a href="javascript:;" class="a-upload" id="a1"> 
				<input type="file" name="paper" id="paperBtn" onchange="ipt1()">
				<font id="paper">点击选择试卷</font>
			</a> 
			<a href="javascript:;" class="a-upload" id="a2"> 
				<input type="file" name="studentOrder" id="studentOrderBtn" onchange="ipt2()">
				<font id="studentOrder">点击选择名单</font>
			</a>
		</form>


		<form action="" class="container" method="get" id="form2"
			style="margin: 0px, auto;">
			<font class="items">考试名称</font>
			<input class="items" id="eName" type="text" name="eName" /> 
			<font class="items">班级</font> 
			<input class="items" id="stuClass" type="text" name="stuClass" /> 
			
			<font class="items" >开始时间</font>
			<input class="items" id="startTime" 
			name="startTime" type="datetime-local" value="2019-04-13T08:00" /> 
			
			<font class="items" >结束时间</font>
			<input class="items" id="endTime" 
			name="endTime" type="datetime-local" value="2019-04-13T10:00" /> 
			
			<input type="button" class="items btn" id="save_as_exam_btn" value="发布考试" />
		</form>

	</div>
<script type="text/javascript">
	var formdata;
	//正式保存考试
	$("#save_as_exam_btn").click(function(){
		alert("save btn");
		//1、获取当前已有考试的值
		formdata = new FormData(document.getElementById("form1"));
		formdata.append("eName",$("#eName").val());
		formdata.append("stuClass",$("#stuClass").val());
		formdata.append("startTimeStr",$("#startTime").val());
		formdata.append("endTimeStr",$("#endTime").val());
		//formdata.append("studentOrder");
		//2、验证并保存考试
		$.ajax({
			url	 :"${APP_PATH}/teacher_validate_exam",
			type : "POST",
			data :formdata,
			processData:false,
            contentType:false,
			success : function(result) {
				if(result.msg.code="100"){
					//信息有效，保存考试
					saveExam();
				}else{
					showInfos(result);
				}
			}
		});
	});
	
	//保存考试
	function saveExam() {
		$.ajax({
			url	 :"${APP_PATH}/teacher_save_exam",
			type : "POST",
			data :formdata,
			processData:false,
            contentType:false,
			success : function(result) {
				alert(result.msg);
			}
		});
	}
	
	//提示信息
	function showInfos(result) {
		alert("showInfos");
	}
	
	//上传试卷与学生名单
	function ipt1() {
		var paper = document.getElementById("paper");
		var paperBtn = document.getElementById("paperBtn");
		var arr = paperBtn.value.split('\\');
		var fileName = arr[arr.length - 1];
		paper.innerHTML = "<font id='paper'>" + fileName + "</font>";
	};
	function ipt2() {
		var studentOrder = document.getElementById("studentOrder");
		var studentOrderBtn = document.getElementById("studentOrderBtn");
		var arr = studentOrderBtn.value.split('\\');
		var fileName = arr[arr.length - 1];
		studentOrder.innerHTML = "<font id='studentOrder'>" + fileName + "</font>";
	};
</script>

</body>
</html>