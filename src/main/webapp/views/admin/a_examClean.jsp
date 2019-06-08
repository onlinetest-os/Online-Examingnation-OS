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
<style>
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
	<%@include file="a_nav.jsp" %>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
			<c:if test="${examsInfos==null}">
				<div>暂无可清理的考试！</div>
			</c:if>
			
			<c:if test="${examsInfos!=null}" >
				<table class="table">
					<thead>
						<tr>
							<th>考试名称</th>
							<th>考试时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
				<c:forEach items="${examsInfos}" var="exam" varStatus="st">
					<div class="items">
						<tr>
							<td>${exam.eName}</td>
							<td>${exam.startTime}到${exam.endTime}</td>
							<td><a class="cleanBtn btn" name-attr="${exam.eName }" id-attr="${exam.eId }" class="btn">清理</a></td>
						</tr>
					</div>
				</c:forEach>
			</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>
<script type="text/javascript">
$(".cleanBtn").click(function(){
	var eName =  $(this).attr("name-attr");
	var eId =  $(this).attr("id-attr");
	var r = confirm("确认清理考试 "+eName+"-"+eId+" ？确认将清理本场考试所有信息，且此操作不可逆转！");
	if (r==true){
		//发送ajax请求后台清理考试
		$.ajax({
			url:"${APP_PATH}/admin_clean_exam?eId="+eId,
			data:"",
			type:"GET",
			success:function(result){
				if(result.code==100){
					alert("清理成功！");
				}else{
					alert(result.msg);
				}
			}
		});
	}
});
	
</script>

</body>
</html>