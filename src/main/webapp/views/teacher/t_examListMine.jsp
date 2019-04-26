<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>我的考试</title>

<script src="${APP_PATH }/static/assets/js/jquery.min.js"></script>

</head>
<style type="text/css">
.container {
	display: grid;
	grid-template-columns: 1fr 1fr 1fr;
	grid-template-rows: auto;
}

.items {
	padding: 10px; background : #ccccff;
	border-radius: 5px;
	margin: 10px;
	background: #ccccff;
}

.btn {
	color:white;
	text-decoration: none;
	cusor: pointer;
	background: #0066ff;
	border-radius: 4px;
	padding: 5px;
	border:0px;
}

.font {
	color: white;
}
</style>
<body>
	<div class="container">
		
			<c:if test="${exams==null}">
				<div>今天暂无考试！</div>
			</c:if>
			<c:if test="${exams!=null}" >
				<c:forEach items="${examsInfos}" var="exam" varStatus="st">
					<div class="items">
						<form>
						
						<h3>${exam.eName}</h3>
						<p>
							${exam.startTime}到${exam.endTime}
						</p>
						<p>
								
							<a class="btn" href="teacher_t_examEdit"><font class="font">编辑</font></a>
							<a class="btn" href="teacher_t_stuList?eId=${exam.eId }"><font class="font">学生列表</font></a>
							<input  class="deleteBtn btn" type="button" id-attr="${exam.eId }" name-attr="${exam.eName }" value="删除">
							<input  class="clearBtn btn" type="button" id-attr="${exam.eId }" name-attr="${exam.eName }" value="清理">
					
						</p>
						</form>
					</div>
				</c:forEach>
			</c:if>
	</div>
	<script>
	//$(document).on("click",".edit_btn",function(){
	//清理考试	
	$(".clearBtn").click(function(){
		var eName =  $(this).attr("name-attr");
		var eId =  $(this).attr("id-attr");
		var r = confirm("确认清理考试 "+eName+"-"+eId+" ？确认将清理本场考试所有信息，且此操作不可逆转！");
		if (r==true){
			//发送ajax请求后台清理考试
			$.ajax({
				url:"${APP_PATH}/teacher_clear_exam?eId="+eId,
				data:"",
				type:"GET",
				success:function(result){
					alert("清理成功！");
				}
			});
			
		}
		
	});
	
	//删除考试
	$(".deleteBtn").click(function(){
		var eName =  $(this).attr("name-attr");
		var eId =  $(this).attr("id-attr");
		var r = confirm("确认删除考试 "+eName+"-"+eId+" ？确认将删除本场考试所有信息，且此操作不可逆转！");
		if (r==true){
			//发送ajax请求后台删除考试
			/* $.ajax({
				url:,
				data:,
				type:,
				success:function(result){
					
				}
			}); */
			alert("删除成功！");
		}
	});
	</script>
</body>
</html>