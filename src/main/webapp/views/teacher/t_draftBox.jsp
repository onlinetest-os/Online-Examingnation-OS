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
</head>
<body>
	
	<div class="container-fluid">
		<c:if test="${exams==null}">
				<div>暂无考试</div>
		</c:if>
		<c:if test="${exams!=null}" >
			<c:forEach items="${examsInfos}" var="exam" varStatus="st">
				<div class="items">
					<form>
					
					<h3>${exam.eName}</h3>
					状态：${exam.status}
					<p>
					${exam.startTime}到${exam.endTime}
					<p>
						<a class="btn" href="teacher_t_newExam?type=edit&eId=${exam.eId }"><font class="font">编辑</font></a>
						<a class="btn" href="teacher_t_stuList?eId=${exam.eId }"><font class="font">学生列表</font></a>
						<input  class="deleteBtn btn" type="button" id-attr="${exam.eId }" name-attr="${exam.eName }" value="删除">			
					</p>
					</form>
				</div>
			</c:forEach>
		</c:if>
	</div>
<script type="text/javascript">
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