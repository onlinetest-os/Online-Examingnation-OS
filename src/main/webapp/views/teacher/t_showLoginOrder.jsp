<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link href="${APP_PATH}/static/assets/css/core.css" rel="stylesheet" type="text/css">
<link href="${APP_PATH}/static/assets/css/icons.css" rel="stylesheet" type="text/css">
<link href="${APP_PATH}/static/assets/css/components.css" rel="stylesheet" type="text/css">
<link href="${APP_PATH}/static/assets/css/pages.css" rel="stylesheet" type="text/css">
<link href="${APP_PATH}/static/assets/css/menu.css" rel="stylesheet" type="text/css">
<link href="${APP_PATH}/static/assets/css/responsive.css" rel="stylesheet" type="text/css">

<script src="${APP_PATH}/static/assets/js/modernizr.min.js"></script>

<link href="${APP_PATH }/static/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script src="${APP_PATH }/static/assets/js/jquery.min.js"></script>
<script src="${APP_PATH }/static/assets/js/bootstrap.min.js"></script>
</head>
<body>
<div class="col-md-6">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">提交名单</h3>
        </div>
        <div class="panel-body">
	        <!-- 标题 -->
			<!-- 按钮 -->
			<div class="row">
				<div class="col-md-4 col-md-offset-8">
					已登录<input type='checkbox' class="check_box" id="onlineCbx"/>
					未登录<input type='checkbox' class="check_box" id="outlineCbx"/>
				</div>
			</div>
			<!-- 显示表格数据 -->
            <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                    <table id="stus_table" class="table">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>姓名</th>
                                <th>学号</th>
                                <th>班级</th>
                                <th>状态</th>
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
    </div>
</div>
</body>
<script type="text/javascript">

		
		var totalRecord,currentPage;
		var type;
		//1、页面加载完成以后，直接去发送ajax请求,要到分页数据
		$(function(){
			//去首页
			to_page(1);
		});
		
		function to_page(pn){
			var online = $("#onlineCbx").prop("checked");
			var outline=$("#outlineCbx").prop("checked");
			
			if(online && !outline){
				type = "online";
			}else if(!online && outline){
				type = "outline";
			}else{
				type="all";
			}
			//alert(type);
			$.ajax({
				url:"${APP_PATH}/teacher_get_students?eId=${eId}&type="+type,
				data:"pn="+pn,
				type:"GET",
				success:function(result){
					//console.log(result);
					//1、解析并显示学生数据
					build_stus_table(result);
					//2、解析并显示分页信息
					build_page_info(result);
					//3、解析显示分页条数据
					build_page_nav(result);
				}
			});
		}
		
		function build_stus_table(result){
			//清空table表格
			$("#stus_table tbody").empty();
			var stus = result.extend.pageInfo.list;
			$.each(stus,function(index,item){
				var stuIdTd = $("<td></td>").append(item.stuId);
				var stuNameTd = $("<td></td>").append(item.stuName);
				var stuNumberTd = $("<td></td>").append(item.stuNumber);
				var stuClassTd = $("<td></td>").append(item.stuClass);
				var ipTd = $("<td></td>").append(item.ip==null?"未登录":item.ip);
//		 	<tr class="success">
//	 	         <td>9</td>
//	 	         <td>Column content</td>
//	 	         <td>Column content</td>
//	 	         <td>Column content</td>
//	 	         <td>提交</td>
//	       </tr>
				//append方法执行完成以后还是返回原来的元素
				//alert(item.ip);
				//alert(item.ip=="");
				if(item.ip==null){
					$("<tr></tr>")
					.append(stuIdTd)
					.append(stuNameTd)
					.append(stuNumberTd)
					.append(stuClassTd)
					.append(ipTd)
					.appendTo("#stus_table tbody");
				}else{
					$("<tr></tr>").addClass("success")
					.append(stuIdTd)
					.append(stuNameTd)
					.append(stuNumberTd)
					.append(stuClassTd)
					.append(ipTd)
					.appendTo("#stus_table tbody");
				}
				
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
		
		$(".check_box").change(function() {
			to_page(currentPage);
		});
		
</script>

</html>