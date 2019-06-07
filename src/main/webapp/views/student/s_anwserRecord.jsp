<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<%@include file="s_nav.jsp"%>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<table class="table table-condensed">
					<thead>
						<tr>
							<th>最后一次提交时间</th>
							<th>文件名</th>
						</tr>
					</thead>
					<tbody>
					<c:if test="${time==null}">
						<tr><td>还未提交过！</td></tr>
					</c:if>
						<c:if test="${time!=null}">
							<tr>
								<td>${time}</td>
								<td>${fileName}</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>