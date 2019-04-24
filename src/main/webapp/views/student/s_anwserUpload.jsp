<%@page import="org.apache.tomcat.jni.Directory"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" errorPage="upload_error.jsp"%>
<!DOCTYPE html>
<html>
<head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <meta name="description" content="A fully featured admin theme which can be used to build CRM, CMS, etc.">
        <meta name="author" content="Coderthemes">
        <link rel="shortcut icon" href="${APP_PATH}/static/assets/images/favicon_1.ico">
        <title>Moltran - Responsive Admin Dashboard Template</title>

        <link href="${APP_PATH}/static/assets/plugins/dropzone/dist/dropzone.css" rel="stylesheet" type="text/css">        
        <link href="${APP_PATH}/static/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="${APP_PATH}/static/assets/css/core.css" rel="stylesheet" type="text/css">
        <link href="${APP_PATH}/static/assets/css/icons.css" rel="stylesheet" type="text/css">
        <link href="${APP_PATH}/static/assets/css/components.css" rel="stylesheet" type="text/css">
        <link href="${APP_PATH}/static/assets/css/pages.css" rel="stylesheet" type="text/css">
        <link href="${APP_PATH}/static/assets/css/menu.css" rel="stylesheet" type="text/css">
        <link href="${APP_PATH}/static/assets/css/responsive.css" rel="stylesheet" type="text/css">

        <script src="${APP_PATH}/static/assets/js/modernizr.min.js"></script>
        <script src="${APP_PATH}/static/assets/js/jquery.min.js"></script>
        <script src="${APP_PATH}/static/assets/js/bootstrap.min.js"></script>
        <script src="${APP_PATH}/static/assets/js/detect.js"></script>
        <script src="${APP_PATH}/static/assets/js/fastclick.js"></script>
        <script src="${APP_PATH}/static/assets/js/jquery.slimscroll.js"></script>
        <script src="${APP_PATH}/static/assets/js/jquery.blockUI.js"></script>
        <script src="${APP_PATH}/static/assets/js/waves.js"></script>
        <script src="${APP_PATH}/static/assets/js/wow.min.js"></script>
        <script src="${APP_PATH}/static/assets/js/jquery.nicescroll.js"></script>
        <script src="${APP_PATH}/static/assets/js/jquery.scrollTo.min.js"></script>
        <script src="${APP_PATH}/static/assets/js/jquery.app.js"></script>
        <script src="${APP_PATH}/static/assets/plugins/dropzone/dist/dropzone.js"></script>
<style>
.sc {
	cursor:pointer; 
	width: 100px;
	height: 36px;
	background: white;
	border: #2178FC 1px solid;
	border-radius: 15px;
}

.a-upload {
	padding: 4px 10px;
	height: 30px;
	line-height: 20px;
	position: relative;
	cursor: pointer;
	color: white;
	background: #0066ff;
	border: 1px solid #ddd;
	border-radius: 4px;
	overflow: hidden;
	display: inline-block;
	*display: inline;
	*zoom: 1
}
</style>

</head>

<body>
	<%@include file="s_nav.jsp"%>
	<h3 align="center">答案上传</h3>
	<br />
	<div style="margin: 0px, auto;">
		 <div class="row">
              <div class="col-md-12 portlets">
                  <!-- Your awesome content goes here -->
                  <div class="m-b-30">
                      <form action="#" enctype="multipart/form-data" id="uploadForm">
                        <div class="fallback">
                          <input name="file" type="file" multiple="multiple">
                        </div>
                        <br>
                        <img src="${APP_PATH }/static/assets/images/small/upload.png"  
                        alt="立即上传">
                        <button id="uploadBtn" type="button" class="btn btn-primary">立即上传</button>
                      </form>
                  </div>
              </div>
          </div>
	</div>
</body>
<script type="text/javascript">
	$("#uploadBtn").click(function () {
		var formdata = new FormData(document.getElementById("uploadForm"));
		formdata.append("stuNumber","${student.stuNumber}");
		formdata.append("stuName","${student.stuName}");
		formdata.append("stuClass","${student.stuClass}");
		formdata.append("stuName","${student.stuName}");
		formdata.append("eId","${eId}");
		alert("上传");
		$.ajax({
			url:"${APP_PATH }/student_uploadAnwser",
			data:formdata,
			type:"POST",
			processData:false,
            contentType:false,
			success:function(result){
				alert(result.msg);
			}
		});
	});
</script>

</html>