<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>messager消息提示控件实体</title>
<!-- 引入easyui相关资源文件 -->
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">	
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		//屏幕右下角的提示框	
		window.setTimeout(function() {
			$.messager.show({
				title: "My messager" ,
				msg: "Message will be closed 5 seconds" ,
				timeout: 5000 ,
				//动态弹出效果（滑出效果）
				showType: "slide"			
			});
		},3000);
		
		//普通提示框
		//info ， error ，warnning ， question
//			$.messager.alert("提示信息","提示内容正文","info");
		
		//确认框
/* 			$.messager.confirm("确定信息","您确认删除当前数据吗?",function(boolConfirm){
			alert(boolConfirm);
		}); */
		
		//带有输入功能的确认框
/*  		$.messager.prompt("确认信息","您确认删除当前数据吗",function(data) {
 			alert(data);
 		});  */
 		
 		//进度条
  		$.messager.progress();
  		window.setTimeout(function() {
  			$.messager.progress('close');
  		}, 3000);
		
	});
</script>
</head>
<body >
</body>
</html>