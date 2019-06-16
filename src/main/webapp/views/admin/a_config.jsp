<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

</head>
<body>
	<%@include file="a_nav.jsp" %>
	<div class="container">
		<form id="dataForm" class="main">
		
			 <div class="form-group">
			    <label for="spiltPageCount">分页记录数（页）</label>
			    <input name="spiltPageCount" type="text" class="form-control" id="spiltPageCount"
			     value="${spiltPageCount}">
		  	</div>
		  	
		  	<div class="form-group">
			    <label for="manualStartExamRange">手动开启考试时间阈值（分钟）</label>
			    <input name="manualStartExamRange" type="text" class="form-control" id="manualStartExamRange" 
			    value="${manualStartExamRange}">
		  	</div>
		  	
		  	<div class="checkbox">
			    <label>
			      <input name="haveDeletePower" id="haveDeletePower" type="checkbox">允许主考教师清理删除 
			    </label>
		 	 </div>
		  	
		  	<div class="form-group">
			    <label for=maxUploadSize>最大上传文件大小（字节）</label>
			    <input name="maxUploadSize" type="text" class="form-control" id="maxUploadSize" 
			    value="${maxUploadSize}">
		  	</div>
		  	
		  	<div class="checkbox">
			    <label>
			    <input name="autoStartExam" id="autoStartExam" type="checkbox">  自动开启考试
			    </label>
		 	 </div>
		 <button id="submit" type="submit" class="btn btn-default">提交修改</button>
		</form>
	</div>
	<script type="text/javascript">
		//初始化
		$("#haveDeletePower").attr('checked',${haveDeletePower});
		$("#autoStartExam").attr('checked',${autoStartExam});
		//alert("click");
		$("#submit").click(function(){
			$.ajax({
				url:"${APP_PATH}/admin_save_configs",
				type:"POST",
				data:$("#dataForm").serialize(),
				success:function(result){
					//lert(result.msg);
					//alert($("#dataForm").serialize());
					if(result.code == 100){
						alert(result.msg);
					}else{
						alert(result.msg);
					}
				}
			});
		});
	</script>
	
	
</body>
</html>