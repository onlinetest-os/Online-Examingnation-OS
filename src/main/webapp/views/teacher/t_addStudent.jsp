<%@page import="org.apache.tomcat.jni.Directory"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta charset="utf-8">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <meta name="description" content="A fully featured admin theme which can be used to build CRM, CMS, etc.">
        <meta name="author" content="Coderthemes">
        <title>Moltran - Responsive Admin Dashboard Template</title>
        <link rel="shortcut icon" href="${APP_PATH }/static/assets/images/favicon_1.ico">
        
        <link href="${APP_PATH }/static/assets/plugins/dropzone/dist/dropzone.css" rel="stylesheet" type="text/css">
        <link href="${APP_PATH }/static/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="${APP_PATH }/static/assets/css/core.css" rel="stylesheet" type="text/css">
        <link href="${APP_PATH }/static/assets/css/icons.css" rel="stylesheet" type="text/css">
        <link href="${APP_PATH }/static/assets/css/components.css" rel="stylesheet" type="text/css">
        <link href="${APP_PATH }/static/assets/css/pages.css" rel="stylesheet" type="text/css">
        <link href="${APP_PATH }/static/assets/css/menu.css" rel="stylesheet" type="text/css">
        <link href="${APP_PATH }/static/assets/css/responsive.css" rel="stylesheet" type="text/css">
        
        <script src="${APP_PATH }/static/assets/js/modernizr.min.js"></script>
        <script src="${APP_PATH }/static/assets/js/jquery.min.js"></script>
		<script src="${APP_PATH }/static/assets/js/bootstrap.min.js"></script>
		<script src="${APP_PATH }/static/assets/js/detect.js"></script>
        <script src="${APP_PATH }/static/assets/js/fastclick.js"></script>
        <script src="${APP_PATH }/static/assets/js/jquery.slimscroll.js"></script>
        <script src="${APP_PATH }/static/assets/js/jquery.blockUI.js"></script>
        <script src="${APP_PATH }/static/assets/js/waves.js"></script>
        <script src="${APP_PATH }/static/assets/js/wow.min.js"></script>
        <script src="${APP_PATH }/static/assets/js/jquery.nicescroll.js"></script>
        <script src="${APP_PATH }/static/assets/js/jquery.scrollTo.min.js"></script>
        <script src="${APP_PATH }/static/assets/js/jquery.app.js"></script>
        <script src="${APP_PATH }/static/assets/plugins/dropzone/dist/dropzone.js"></script>
        
<style type="text/css">
.btn {
	color: white;
	text-decoration: none;
	cusor: pointer;
	background: #0066ff;
	border-radius: 4px;
	padding: 5px;
}
.sc {
	cursor:pointer; 
	width: 100px;
	height: 36px;
	background: white;
	border: #2178FC 1px solid;
	border-radius: 15px;
}
</style>
</head>
<body>
	
	<form  style="margin-left: 250px;">
		姓名<input type="text" /> 学号<input type="text" /> <input class="btn"
			type="submit" value="OK" />
	</form>
	<h3 align="center">批量导入</h3>
	<br />
	<div style="margin: 0px, auto;">
		<div class="row">
			<div class="col-md-12 portlets">
				<!-- Your awesome content goes here -->
				<div class="m-b-30">
					<form action="#.jsp" class="dropzone" id="dropzone" method="post" enctype="multipart/form-data">
						<div class="fallback">
							<input name="file" type="file" multiple="multiple">
						</div>
						<input class="sc" type="submit" name="upload" value="开始上传">
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>