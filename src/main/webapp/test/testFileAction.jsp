<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>testFileDownLoadOrUpload</title>
</head>
<body>
<h1>测试文件上传或下载</h1>
	<form action="http://localhost:8090/OnlineExam/file/test/upload" method="post" enctype="multipart/form-data">
		选择文件:<input type="file" name="file" width="120px">
		<input type="submit" value="上传">
	</form>
	<hr>
	<form action="http://localhost:8090/OnlineExam/file/test/download" method="get">
		<input type="submit" value="下载">
	</form>

</body>
</html>