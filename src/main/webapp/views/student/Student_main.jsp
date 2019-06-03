<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="${APP_PATH}/static/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script src="${APP_PATH}/static/assets/js/jquery.min.js"></script>
<script src="${APP_PATH}/static/assets/js/bootstrap.min.js"></script>

<style type="text/css">
div.container::before{
	display:none;
}
</style>
</head>
<body>
		<%@include file="s_nav.jsp" %>
		<div class="row-fluid">
			<div class="span12">
				<div class="tabbable" id="tabs-951882">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#panel-97659" data-toggle="tab">我的考试</a>
						</li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="panel-97659">
							<%@include file="s_examList.jsp" %>
						</div>
					</div>
				</div>
			</div>
		</div>
		
	</div>
</body>
</html>