<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="${APP_PATH }/static/assets/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
<script src="${APP_PATH }/static/assets/js/jquery.min.js"></script>
<script src="${APP_PATH }/static/assets/js/bootstrap.min.js"></script>
<style type="text/css">
.btn {
	color: white;
	text-decoration: none;
	cusor: pointer;
	background: #0066ff;
	border-radius: 4px;
	padding: 5px;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="row-fluid">
					<div class="col-md-6">
						<form>
							<fieldset>
								<legend>通知</legend>
								<label>通知内容</label> <input type="text" placeholder="这里填写通知信息" />
								<button class="btn" type="submit" class="btn">发送</button>
							</fieldset>
						</form>
					</div>
					<br /> <br />
					<div class="col-md-6">
						<h3>通知内容</h3>
						<p>这里是通知内容</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>