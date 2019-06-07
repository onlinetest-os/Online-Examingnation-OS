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
				<table class="table">
				<c:if test="${exams==null}" >
					暂无可下载的考试
				</c:if>
					<thead>
						<tr>
							<th>考试名称</th>
							<th>下载答案</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${exams!=null}" >
							<c:forEach items="${exams}" var="exam" varStatus="st">
								<tr>
									<td>${exam.eName}</td>
									<td><a class="btn" href="teacher_t_getAnwsers?eId=${exam.eId}">下载</a></td>
								</tr>
							</c:forEach>
								
						</c:if>
					</tbody>
				</table>
			
			</div>
		</div>
	</div>


</body>
</html>