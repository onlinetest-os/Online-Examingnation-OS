<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
.container {
	display: grid;
	grid-template-columns: repeat(2, 170px);
	justify-items: stretch;
	height: 50px;
	margin: 50px;
}

.container::before {
	display: none;
}

.btn {
	color: white;
	text-decoration: none;
	cusor: pointer;
	background: #0066ff;
	border-radius: 4px;
}

.items {
	margin-top: 10px;
	height: 30px;
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

.a-upload  input {
	position: absolute;
	font-size: 100px;
	right: 0;
	top: 0;
	opacity: 0;
	filter: alpha(opacity = 0);
	cursor: pointer
}
</style>

<script type="text/javascript">
	function ipt1() {
		var ft1 = document.getElementById("ft1");
		var bt1 = document.getElementById("bt1");
		var arr = bt1.value.split('\\');
		var fileName = arr[arr.length - 1];
		ft1.innerHTML = "<font id='ft1'>" + fileName + "</font>";
	};
	function ipt2() {
		var ft2 = document.getElementById("ft2");
		var bt2 = document.getElementById("bt2");
		var arr = bt2.value.split('\\');
		var fileName = arr[arr.length - 1];
		ft2.innerHTML = "<font id='ft2'>" + fileName + "</font>";
	};
	function submitForm() {
		document.getElementById("form1").submit();
		document.getElementById2("form2").submit();
	}
</script>
</head>

<body>
	正在编辑"考试名称"<br/>
	<div>
		<form  action="UploadwithServlet" method="post" enctype="multipart/form-data" id="form1">
			<a href="javascript:;" class="a-upload" id="a1"> <input
				type="file" name="fileName1" id="bt1" onchange="ipt1()"><font
				id="ft1">点击选择试卷</font>
			</a>
			<a href="javascript:;" class="a-upload" id="a2">
				<input type="file" name="fileName2" id="bt2" onchange="ipt2()"><font
				id="ft2">点击选择名单</font>
			</a>
		</form>

		<form action="" class="container" method="get" id="form2"
			style="margin: 0px, auto;">
			<font class="items">考试名称</font><input class="items" type="text"
				name="exam_name" /> <font class="items">班级</font><select
				class="items">
				<option>1</option>
				<option>2</option>
				<option>3</option>
			</select> <font class="items">时长</font><font class="items"><select>
					<option>1</option>
					<option>1.5</option>
					<option>2</option>
			</select>小时</font> <font class="items">开始</font><input class="items" id="meeting"
				type="datetime-local" value="2019-01-13" />
			<font class="items">自动开始</font><input class="items" type="checkbox" />
			<a class="items"></a><input class="items btn"
				style="margin-top: 0px; height: 30px;" onclick="submitForm();"
				value="OK" />
		</form>

	</div>


</body>
</html>