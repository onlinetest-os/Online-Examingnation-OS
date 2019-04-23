	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	    pageEncoding="ISO-8859-1"%>
	<!DOCTYPE html>
	<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	</head>
	<body>
	<script type="text/javascript">
	
	//清空表单样式及内容
	function reset_form(ele){
		$(ele)[0].reset();
		//清空表单样式
		$(ele).find("*").removeClass("has-error has-success");
		$(ele).find(".help-block").text("");
	}
	
	//点击新增按钮弹出模态框。
	$("#stu_add_modal_btn").click(function(){
		//清除表单数据（表单完整重置（表单的数据，表单的样式））
		reset_form("#stuAddModal form");
		//s$("")[0].reset();
		//弹出模态框
		$("#stuAddModal").modal({
			backdrop:"static"
		});
	});
	function validate_add_form(){
		//1、拿到要校验的数据，使用正则表达式
		var stuName = $("#stuName_add_input").val();
		var regName = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
		if(!regName.test(stuName)){
			//alert("用户名可以是2-5位中文或者6-16位英文和数字的组合");
			show_validate_msg("#stuName_add_input", "error", "用户名可以是2-5位中文或者6-16位英文和数字的组合");
			return false;
		}else{
			show_validate_msg("#stuName_add_input", "success", "");
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
	$("#stuNumber_add_input").change(function(){
		//发送ajax请求校验工号是否可用
		var stuNumber = this.value;
		$.ajax({
			url:"${APP_PATH}/admin_check_stucher",
			data:"stuNumber="+stuNumber,
			type:"POST",
			success:function(result){
				if(result.code==100){
					show_validate_msg("#stuNumber_add_input","success","工号可用");
					$("#stu_save_btn").attr("ajax-va","success");
				}else{
					show_validate_msg("#stuNumber_add_input","error",result.extend.va_msg);
					$("#stu_save_btn").attr("ajax-va","error");
				}
			}
		});
	});
	
	//点击保存，保存学生。
	$("#stu_save_btn").click(function(){
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
		
		//2、发送ajax请求保存学生
		//serialize() 方法通过序列化表单值，创建 URL 编码文本字符串。
		$.ajax({
			url:"${APP_PATH}/admin_save_stucher",
			type:"POST",
			data:$("#stuAddModal form").serialize(),
			success:function(result){
				//alert(result.msg);
				//alert($("#stuAddModal form").serialize());
				if(result.code == 100){
					//学生保存成功；
					//1、关闭模态框
					$("#stuAddModal").modal('hide');
					
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
		
		
		//1、查出学生信息，显示学生信息
		getstu($(this).attr("edit-id"));
		
		//2、把学生的id传递给模态框的更新按钮
		$("#stu_update_btn").attr("edit-id",$(this).attr("edit-id"));
		$("#stuUpdateModal").modal({
			backdrop:"static"
		});
	});
	
	function getstu(id){
		$.ajax({
			url:"${APP_PATH}/admin_get_stucher?stuId="+id,
			type:"GET",
			success:function(result){
				console.log(result);
				var stuData = result.extend.stucher;
				$("#stuName_update_static").val(stuData.stuName);
				$("#stuNumber_update_input").val(stuData.stuNumber);
				$("#stuPassword_update_input").val(stuData.stuPassword);
				$("#stuUpdateModal input[name=isAdmin]").val([stuData.isAdmin]);
			}
		});
	}
	
	//点击更新，更新学生信息
	$("#stu_update_btn").click(function(){
		
		//2、发送ajax请求保存更新的学生数据
		$.ajax({
			url:"${APP_PATH}/admin_update_stucher?stuId="+$(this).attr("edit-id"),
			type:"POST",
			data:$("#stuUpdateModal form").serialize(),
			success:function(result){
				//alert(result.msg);
				//1、关闭对话框
				$("#stuUpdateModal").modal("hide");
				//2、回到本页面
				to_page(currentPage);
			}
		});
	});
	
	//单个删除
	$(document).on("click",".delete_btn",function(){
		//1、弹出是否确认删除对话框
		var stuName = $(this).parents("tr").find("td:eq(2)").text();
		var stuId = $(this).attr("del-id");
		//alert($(this).parents("tr").find("td:eq(1)").text());
		if(confirm("确认删除【"+stuName+"】吗？")){
			//确认，发送ajax请求删除即可
			$.ajax({
				url:"${APP_PATH}/admin_delete_stucher?stuId="+stuId,
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
	$("#stu_delete_all_btn").click(function(){
		
		var stuNames = "";
		var del_idstr = "";
		$.each($(".check_item:checked"),function(){
			//this
			stuNames += $(this).parents("tr").find("td:eq(2)").text()+",";
			//组装员工id字符串
			del_idstr += $(this).parents("tr").find("td:eq(1)").text()+"-";
		});
		alert(stuNames+"  "+del_idstr);
		//去除stuNames多余的,
		stuNames = stuNames.substring(0, stuNames.length-1);
		//去除删除的id多余的-
		del_idstr = del_idstr.substring(0, del_idstr.length-1);
		if(confirm("确认删除【"+stuNames+"】吗？")){
			//发送ajax请求删除
			$.ajax({
				url:"${APP_PATH}/admin_delete_stuchers?del_idstr="+del_idstr,
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