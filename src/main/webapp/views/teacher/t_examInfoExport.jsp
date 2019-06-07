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
</head>
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
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
			<c:if test="${examsInfos==null}">
				<div>暂无可导出的考试！</div>
			</c:if>
			
			<c:if test="${examsInfos!=null}" >
				<table class="table">
					<thead>
						<tr>
							<th>考试名称</th>
							<th>考试时间</th>
							<th>导出信息</th>
						</tr>
					</thead>
					<tbody>
				<c:forEach items="${examsInfos}" var="exam" varStatus="st">
					<div class="items">
						<tr>
							<td>${exam.eName}</td>
							<td>${exam.startTime}到${exam.endTime}</td>
							<td><a class="btn" href="#">导出</a></td>
						</tr>
					</div>
				</c:forEach>
			</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>


</body>
</html>