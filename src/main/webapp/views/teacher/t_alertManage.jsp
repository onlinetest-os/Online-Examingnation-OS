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
								<label>通知内容</label> 
								<input id="addMsg" type="text" placeholder="这里填写通知信息" />
								<button id="sentBtn" class="btn" type="submit" class="btn">发送</button>
							</fieldset>
						</form>
					</div>
					<br /> <br />
					<div class="col-md-6">
						<h3>通知内容</h3>
						<div id="msg">
						....
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	var version = -1;
	
	refresh();
	//开始执行自己
	refreshOnTime();
	//alert("script");
	function refreshOnTime(){
		//alert("refreshOnTime");
		 //每隔5秒访问一次服务器
		setInterval("refresh()",5000);	
	}
	function refresh(){
		//alert("refresh");
		$.ajax({
	    	url : "${APP_PATH}/message_getNewest?version="+version,
			type : "GET",
			success : function(result) {
				//获取通知
				if (result.code == 100) {
					version = result.extend.version;
					var msgQueue = result.extend.msgQueue;
					var len = result.extend.length;
					//alert(len);
					$("#msg").text("");
					for (var i = 0; i < len; i++) {
						//alert(i);
						var time = msgQueue[i].time;
						//alert(time);
						$("#msg").append(time.hour+":"+time.minute+":"+time.second);
						$("#msg").append("&nbsp;&nbsp;&nbsp;&nbsp;");
						$("#msg").append(msgQueue[i].msg);
						$("#msg").append("<br>");
					 }
					 
				}else{
					 //$("#msg").append("<b>插入文本</b>.");
				}
			}
	    });
	}
	$("#sentBtn").click(function(){
		var msg = $("#addMsg").val();
		
		alert("增加："+msg);
		$.ajax({
			url : "${APP_PATH}/teacher_t_alertManage_add",
			contentType: "application/x-www-form-urlencoded; charset=utf-8",
			type : "POSt",
			data: {"msg":msg},
			success : function(result) {
				alert(result.msg);
				if(result.code==100){
					alert("更新成功！");
					refresh();
				}else{
					alert("信息太短！");
				}
			}
		});
	});
	
</script>
</body>
</html>