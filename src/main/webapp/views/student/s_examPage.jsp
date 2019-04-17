<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta name="description"
	content="A fully featured admin theme which can be used to build CRM, CMS, etc.">
<meta name="author" content="Coderthemes">

<link rel="shortcut icon" href="assets/images/favicon_1.ico">

<title>Moltran - Responsive Admin Dashboard Template</title>

<link href="assets/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
<link href="${APP_PATH}/static/assets/css/core.css" rel="stylesheet" type="text/css">
<link href="${APP_PATH}/static/assets/css/icons.css" rel="stylesheet" type="text/css">
<link href="${APP_PATH}/static/assets/css/components.css" rel="stylesheet" type="text/css">
<link href="${APP_PATH}/static/assets/css/pages.css" rel="stylesheet" type="text/css">
<link href="${APP_PATH}/static/assets/css/menu.css" rel="stylesheet" type="text/css">
<link href="${APP_PATH}/static/assets/css/responsive.css" rel="stylesheet" type="text/css">

<script src="${APP_PATH}/static/assets/js/modernizr.min.js"></script>
<script src="${APP_PATH}/static/assets/js/jquery.min.js"></script>
<script src="${APP_PATH}/static/assets/js/bootstrap.min.js"></script>


</head>

<body>
	<%@include file="s_nav.jsp"%>


	<div style="float: left;margin-left: 50px;">
		<div class="hero-unit">
			<h1>操作说明</h1>
			<p>这是简要说明</p>
		</div>
		<dl>
			<dt>考试名称</dt>
			<dd>XXXX考试</dd>
			<dt>主考人</dt>
			<dd>张磊</dd>
			<dt>考试时间</dt>
			<dd>八点到十点</dd>
		</dl>
	</div>
	<div style="float: right;margin-right: 50px;">
		<div class="hero-unit">
			<h1>通知</h1>
			<p>这是通知内容</p>
		</div>
	</div>


	<div class="btn-group btn-group-justified m-b-10" style="float: right;">
		<a class="btn btn-primary waves-effect waves-light" role="button"
			href="s_paperDownload.jsp">下载试卷 »</a> <a 
			class="btn btn-warning waves-effect waves-light" role="button"
			href="s_anwserUpload.jsp">上传答案 »</a> <a
			class="btn btn-danger waves-effect waves-light" role="button"
			href="s_anwserRecord.jsp">我的答案 »</a>
	</div>

</body>
</html>