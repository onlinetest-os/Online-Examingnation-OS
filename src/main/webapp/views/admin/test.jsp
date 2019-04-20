<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>测试查询</title>
</head>
<body>
${pageInfo.list}
<c:forEach items="${pageInfo.list}" var="item" varStatus="st">
	${ item.teaName}<br>
</c:forEach>
</body>
</html>