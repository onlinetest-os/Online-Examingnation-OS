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


</head>
<body>
<h5>${eName}</h5>

<%-- <div>${students }</div>  --%>

	<!-- 教师修改的模态框 -->
	<div class="modal fade" id="stuUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">教师修改</h4>
	      </div>
	      <div class="modal-body">
	        <form class="form-horizontal">
			  <div class="form-group">
			    <label class="col-sm-2 control-label">姓名</label>
			    <div class="col-sm-10">
			      <input type="text"  name="stuName" class="form-control" id="stuName_update_static" placeholder="stuName">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">学号</label>
			    <div class="col-sm-10">
			      <input type="text" name="stuNumber" class="form-control" id="stuNumber_update_input" placeholder="stuNumber">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">班级</label>
			    <div class="col-sm-10">
			      <input type="text" name="stuClass" class="form-control" id="stuPassword_update_input" placeholder="stuClass">
			      <span class="help-block"></span>
			    </div>
			  </div>
			
		  </div>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" id="stu_update_btn">更新</button>
	      </div>
	    </div>
	  </div>
	</div>



	<!-- 教师添加的模态框 -->
	<div class="modal fade" id="stuAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">教师添加</h4>
	      </div>
	      <div class="modal-body">
	        <form class="form-horizontal">
			  			  <div class="form-group">
			    <label class="col-sm-2 control-label">姓名</label>
			    <div class="col-sm-10">
			      <input type="text"  name="stuName" class="form-control" id="stuName_update_static" placeholder="stuName">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">学号</label>
			    <div class="col-sm-10">
			      <input type="text" name="stuNumber" class="form-control" id="stuNumber_update_input" placeholder="stuNumber">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">班级</label>
			    <div class="col-sm-10">
			      <input type="text" name="stuClass" class="form-control" id="stuPassword_update_input" placeholder="stuClass">
			      <span class="help-block"></span>
			    </div>
			  </div>
		  </div>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" id="stu_save_btn">保存</button>
	      </div>
	    </div>
	  </div>
	</div>



	<!-- 搭建显示页面 -->
	<div class="container">
		<!-- 标题 -->
		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-primary" id="stu_add_modal_btn">新增</button>
				<button class="btn btn-danger" id="stu_delete_all_btn">批量删除</button>
			</div>
		</div>
		<!-- 显示表格数据 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" id="stus_table">
					<thead>
						<tr>
							<th><input type="checkbox" id="check_all" /></th>
							<th>#</th>
							<th>姓名</th>
							<th>学号</th>
							<th>班级</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>

					</tbody>
				</table>
			</div>
		</div>

		<!-- 显示分页信息 -->
		<div class="row">
			<!--分页文字信息  -->
			<div class="col-md-6" id="page_info_area"></div>
			<!-- 分页条信息 -->
			<div class="col-md-6" id="page_nav_area"></div>
		</div>
		
		

	</div>
</body>
</html>