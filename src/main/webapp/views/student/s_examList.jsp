<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>今天的考试</title>

<style type="text/css">
.container {
	display: grid;
	grid-template-columns: 1fr 1fr 1fr;
	grid-template-rows: auto;
}

.items {
	padding: 10px;
	background: #ccccff;
	border-radius: 5px;
	margin: 10px;
	background: #ccccff;
}

.btn {
	color: white;
	text-decoration: none;
	cusor: pointer;
	background: #0066ff;
	border-radius: 4px;
	padding: 5px;
}

.font {
	color: white;
}
</style>
</head>
<body>
		<script type="text/javascript">
			//alert("${examsInfos}");
		</script>

	<div class="container">
		<c:if test="${exams==null}">
			<div>今天暂无考试！</div>
		</c:if>
		<c:if test="${exams!=null}" >
			<c:forEach items="${examsInfos}" var="exam" varStatus="st">
				<div class="items">
					
					<h3>${exam.eName}</h3>
					<p>
						状态：${exam.status }<br>
						考试时间：${exam.startTime}到
						${exam.endTime}
						
					</p>
					<p>
						<a class="btn" href="student_begin_exam?eId=${exam.eId }&stuId=${student.stuId}"><font class="font">参加考试</font></a>
					</p>
				</div>
			</c:forEach>
		</c:if>
	</div>
</body>
</html>


