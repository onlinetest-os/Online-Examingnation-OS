<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
.btn {
	color: white;
	text-decoration: none;
	cusor: pointer;
	background: #0066ff;
	border-radius: 4px;
	padding: 3 6;
}

ul.pagination {
	display: inline-block;
	padding: 0;
	margin: 0;
}

ul.pagination li {
	display: inline;
}

ul.pagination li a {
	color: black;
	float: left;
	padding: 8px 16px;
	text-decoration: none;
}
</style>
</head>
<body>
	<%@include file="a_nav.jsp"%>
	
	<!-- 教师修改的模态框 -->
	<div class="modal fade" id="teaUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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
			      <input type="text"  name="teaName" class="form-control" id="teaName_update_static" placeholder="teaName">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">工号</label>
			    <div class="col-sm-10">
			      <input type="text" name="teaNumber" class="form-control" id="teaNumber_update_input" placeholder="teaNumber">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">密码</label>
			    <div class="col-sm-10">
			   	<span>不可修改</span>
			      <input type="hidden" name="teaPassword" class="form-control" id="teaPassword_update_input" placeholder="teaPassword">
			      <span class="help-block"></span>
			    </div>
			  </div>
			<div class="form-group">
		    <label class="col-sm-2 control-label">类型</label>
	    		<div class="col-sm-10">
		     	 	<label class="radio-inline">
				  		<input type="radio" name="isAdmin" id="isAdmin1_update_input" value="1" > 管理员
					</label>
					<label class="radio-inline">
					  <input type="radio" name="isAdmin" id="isAdmin2_update_input" value="0" checked="checked"> 教师
					</label>
	   		   </div>
		  </div>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" id="tea_update_btn">更新</button>
	      </div>
	    </div>
	  </div>
	</div>



	<!-- 教师添加的模态框 -->
	<div class="modal fade" id="teaAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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
			      <input type="text" name="teaName" class="form-control" id="teaName_add_input" placeholder="teaName">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">工号</label>
			    <div class="col-sm-10">
			      <input type="text" name="teaNumber" class="form-control" id="teaNumber_add_input" placeholder="teaNumber">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">密码</label>
			    <div class="col-sm-10">
			      <input type="text" name="teaPassword" class="form-control" id="teaPassword_add_input" placeholder="teaPassword">
			      <span class="help-block"></span>
			    </div>
			  </div>
			<div class="form-group">
		    <label class="col-sm-2 control-label">类型</label>
	    		<div class="col-sm-10">
		     	 	<label class="radio-inline">
				  		<input type="radio" name="isAdmin" id="isAdmin1_add_input" value="1" > 管理员
					</label>
					<label class="radio-inline">
					  <input type="radio" name="isAdmin" id="isAdmin_add_input" value="0" checked="checked"> 教师
					</label>
	   		   </div>
		  </div>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" id="tea_save_btn">保存</button>
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
				<button class="btn btn-primary" id="tea_add_modal_btn">新增</button>
				<button class="btn btn-danger" id="tea_delete_all_btn">批量删除</button>
			</div>
		</div>
		<!-- 显示表格数据 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" id="teas_table">
					<thead>
						<tr>
							<th><input type="checkbox" id="check_all" /></th>
							<th>#</th>
							<th>姓名</th>
							<th>工号</th>
							<th>密码(不可修改)</th>
							<th>类型</th>
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

	<script type="text/javascript">
	var totalRecord,currentPage;
	//1、页面加载完成以后，直接去发送ajax请求,要到分页数据
	$(function(){
		//去首页
		to_page(1);
	});
	
	function to_page(pn){
		$.ajax({
			url:"${APP_PATH}/admin_get_teachers",
			data:"pn="+pn,
			type:"GET",
			success:function(result){
				//console.log(result);
				//1、解析并显示教师数据
				build_teas_table(result);
				//2、解析并显示分页信息
				build_page_info(result);
				//3、解析显示分页条数据
				build_page_nav(result);
			}
		});
	}
	
	function build_teas_table(result){
		//清空table表格
		$("#teas_table tbody").empty();
		var teas = result.extend.pageInfo.list;
		$.each(teas,function(index,item){
			var checkBoxTd = $("<td><input type='checkbox' class='check_item'/></td>");
			var teaIdTd = $("<td></td>").append(item.teaId);
			var teaNameTd = $("<td></td>").append(item.teaName);
			var teaNumber = $("<td></td>").append(item.teaNumber);
			var teaPasswordTd = $("<td></td>").append(item.teaPassword);
			var type = $("<td></td>").append(item.isAdmin==1?"管理员":"教师");
			/**
			<button class="">
			<span class="" aria-hidden="true"></span>
								编辑
							</button>
			*/
			var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
							.append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
			//为编辑按钮添加一个自定义的属性，来表示当前教师id
			editBtn.attr("edit-id",item.teaId);
			var delBtn =  $("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
							.append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
			//为删除按钮添加一个自定义的属性来表示当前删除的教师id
			delBtn.attr("del-id",item.teaId);
			var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
			//var delBtn = 
			//append方法执行完成以后还是返回原来的元素
			$("<tr></tr>").append(checkBoxTd)
				.append(teaIdTd)
				.append(teaNameTd)
				.append(teaNumber)
				.append(teaPasswordTd)
				.append(type)
				.append(btnTd)
				.appendTo("#teas_table tbody");
		});
	}
	
	//解析显示分页信息
	function build_page_info(result){
		$("#page_info_area").empty();
		$("#page_info_area").append("当前"+result.extend.pageInfo.pageNum+"页,总"+
				result.extend.pageInfo.pages+"页,总"+
				result.extend.pageInfo.total+"条记录");
		totalRecord = result.extend.pageInfo.total;
		currentPage = result.extend.pageInfo.pageNum;
	}
	
	//解析显示分页条，点击分页要能去下一页....
	function build_page_nav(result){
		//page_nav_area
		$("#page_nav_area").empty();
		var ul = $("<ul></ul>").addClass("pagination");
		
		//构建元素
		var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
		var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
		if(result.extend.pageInfo.hasPreviousPage == false){
			firstPageLi.addClass("disabled");
			prePageLi.addClass("disabled");
		}else{
			//为元素添加点击翻页的事件
			firstPageLi.click(function(){
				to_page(1);
			});
			prePageLi.click(function(){
				to_page(result.extend.pageInfo.pageNum -1);
			});
		}
		
		
		
		var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
		var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href","#"));
		if(result.extend.pageInfo.hasNextPage == false){
			nextPageLi.addClass("disabled");
			lastPageLi.addClass("disabled");
		}else{
			nextPageLi.click(function(){
				to_page(result.extend.pageInfo.pageNum +1);
			});
			lastPageLi.click(function(){
				to_page(result.extend.pageInfo.pages);
			});
		}
		
		
		
		//添加首页和前一页 的提示
		ul.append(firstPageLi).append(prePageLi);
		//1,2，3遍历给ul中添加页码提示
		$.each(result.extend.pageInfo.navigatepageNums,function(index,item){
			
			var numLi = $("<li></li>").append($("<a></a>").append(item));
			if(result.extend.pageInfo.pageNum == item){
				numLi.addClass("active");
			}
			numLi.click(function(){
				to_page(item);
			});
			ul.append(numLi);
		});
		//添加下一页和末页 的提示
		ul.append(nextPageLi).append(lastPageLi);
		
		//把ul加入到nav
		var navEle = $("<nav></nav>").append(ul);
		navEle.appendTo("#page_nav_area");
	}
	//清空表单样式及内容
	function reset_form(ele){
		$(ele)[0].reset();
		//清空表单样式
		$(ele).find("*").removeClass("has-error has-success");
		$(ele).find(".help-block").text("");
	}
	
	//点击新增按钮弹出模态框。
	$("#tea_add_modal_btn").click(function(){
		//清除表单数据（表单完整重置（表单的数据，表单的样式））
		reset_form("#teaAddModal form");
		//s$("")[0].reset();
		//弹出模态框
		$("#teaAddModal").modal({
			backdrop:"static"
		});
	});
	function validate_add_form(){
		//1、拿到要校验的数据，使用正则表达式
		var teaName = $("#teaName_add_input").val();
		var regName = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
		if(!regName.test(teaName)){
			//alert("用户名可以是2-5位中文或者6-16位英文和数字的组合");
			show_validate_msg("#teaName_add_input", "error", "用户名可以是2-5位中文或者6-16位英文和数字的组合");
			return false;
		}else{
			show_validate_msg("#teaName_add_input", "success", "");
		};
		
		//2、校验密码
		
		//3、校验工号
		return true;
	}
	
	
	//显示校验结果的提示信息
	function show_validate_msg(ele,status,msg){
		//清除当前元素的校验状态
		$(ele).parent().removeClass("has-success has-error");
		$(ele).next("span").text("");
		if("success"==status){
			$(ele).parent().addClass("has-success");
			$(ele).next("span").text(msg);
		}else if("error" == status){
			$(ele).parent().addClass("has-error");
			$(ele).next("span").text(msg);
		}
	}
	
	//校验工号是否可用
	$("#teaNumber_add_input").change(function(){
		//发送ajax请求校验工号是否可用
		var teaNumber = this.value;
		$.ajax({
			url:"${APP_PATH}/admin_check_teacher",
			data:"teaNumber="+teaNumber,
			type:"POST",
			success:function(result){
				if(result.code==100){
					show_validate_msg("#teaNumber_add_input","success","工号可用");
					$("#tea_save_btn").attr("ajax-va","success");
				}else{
					show_validate_msg("#teaNumber_add_input","error",result.extend.va_msg);
					$("#tea_save_btn").attr("ajax-va","error");
				}
			}
		});
	});
	
	//点击保存，保存教师。
	$("#tea_save_btn").click(function(){
		//alert("click save");
		//1、模态框中填写的表单数据提交给服务器进行保存
		//1、先对要提交给服务器的数据进行校验
		if(!validate_add_form()){
			//alert(" validate false");
			return false;
		};
		//1、判断之前的ajax工号校验是否成功。如果成功。
		if($(this).attr("ajax-va")=="error"){
			//alert(" validate error");
			return false;
		}
		
		//2、发送ajax请求保存教师
		//serialize() 方法通过序列化表单值，创建 URL 编码文本字符串。
		$.ajax({
			url:"${APP_PATH}/admin_save_teacher",
			type:"POST",
			data:$("#teaAddModal form").serialize(),
			success:function(result){
				//alert(result.msg);
				//alert($("#teaAddModal form").serialize());
				if(result.code == 100){
					//教师保存成功；
					//1、关闭模态框
					$("#teaAddModal").modal('hide');
					
					//2、来到最后一页，显示刚才保存的数据
					//发送ajax请求显示最后一页数据即可
					to_page(totalRecord);
				}else{
					//显示失败信息
					//console.log(result);
					//有哪个字段的错误信息就显示哪个字段的；
					//alert(" ajax fail");
				}
			}
		});
	});
	
	//1、我们是按钮创建之前就绑定了click，所以绑定不上。
	//1）、可以在创建按钮的时候绑定。    2）、绑定点击.live()
	//jquery新版没有live，使用on进行替代
	$(document).on("click",".edit_btn",function(){
		//alert("edit");
		
		
		//1、查出教师信息，显示教师信息
		getTea($(this).attr("edit-id"));
		
		//2、把教师的id传递给模态框的更新按钮
		$("#tea_update_btn").attr("edit-id",$(this).attr("edit-id"));
		$("#teaUpdateModal").modal({
			backdrop:"static"
		});
	});
	
	function getTea(id){
		$.ajax({
			url:"${APP_PATH}/admin_get_teacher?teaId="+id,
			type:"GET",
			success:function(result){
				console.log(result);
				var teaData = result.extend.teacher;
				$("#teaName_update_static").val(teaData.teaName);
				$("#teaNumber_update_input").val(teaData.teaNumber);
				$("#teaPassword_update_input").val(teaData.teaPassword);
				$("#teaUpdateModal input[name=isAdmin]").val([teaData.isAdmin]);
			}
		});
	}
	
	//点击更新，更新教师信息
	$("#tea_update_btn").click(function(){
		
		//2、发送ajax请求保存更新的教师数据
		$.ajax({
			url:"${APP_PATH}/admin_update_teacher?teaId="+$(this).attr("edit-id"),
			type:"POST",
			data:$("#teaUpdateModal form").serialize(),
			success:function(result){
				//alert(result.msg);
				//1、关闭对话框
				$("#teaUpdateModal").modal("hide");
				//2、回到本页面
				to_page(currentPage);
			}
		});
	});
	
	//单个删除
	$(document).on("click",".delete_btn",function(){
		//1、弹出是否确认删除对话框
		var teaName = $(this).parents("tr").find("td:eq(2)").text();
		var teaId = $(this).attr("del-id");
		//alert($(this).parents("tr").find("td:eq(1)").text());
		if(confirm("确认删除【"+teaName+"】吗？")){
			//确认，发送ajax请求删除即可
			$.ajax({
				url:"${APP_PATH}/admin_delete_teacher?teaId="+teaId,
				type:"GET",
				success:function(result){
					alert(result.msg);
					//回到本页
					to_page(currentPage);
				}
			});
		}
	});
	
	//完成全选/全不选功能
	$("#check_all").click(function(){
		//attr获取checked是undefined;
		//我们这些dom原生的属性；attr获取自定义属性的值；
		//prop修改和读取dom原生属性的值
		$(".check_item").prop("checked",$(this).prop("checked"));
	});
	
	//check_item
	$(document).on("click",".check_item",function(){
		//判断当前选择中的元素是否5个
		var flag = $(".check_item:checked").length==$(".check_item").length;
		$("#check_all").prop("checked",flag);
	});
	
	//批量删除
	$("#tea_delete_all_btn").click(function(){
		
		var teaNames = "";
		var del_idstr = "";
		$.each($(".check_item:checked"),function(){
			//this
			teaNames += $(this).parents("tr").find("td:eq(2)").text()+",";
			//组装员工id字符串
			del_idstr += $(this).parents("tr").find("td:eq(1)").text()+"-";
		});
		alert(teaNames+"  "+del_idstr);
		//去除teaNames多余的,
		teaNames = teaNames.substring(0, teaNames.length-1);
		//去除删除的id多余的-
		del_idstr = del_idstr.substring(0, del_idstr.length-1);
		if(confirm("确认删除【"+teaNames+"】吗？")){
			//发送ajax请求删除
			$.ajax({
				url:"${APP_PATH}/admin_delete_teachers?del_idstr="+del_idstr,
				type:"GET",
				success:function(result){
					alert(result.msg);
					//回到当前页面
					to_page(currentPage);
				}
			});
		}
	});
	</script>

		

</body>
</html>