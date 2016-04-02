<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>tabs选项卡面板使用</title>
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
</head>
<body class="easyui-layout">
	<!-- 每个div是一个区域 -->
	<div title="XXX系统" data-options="region:'north'" style="height: 100px">北部区域</div>
	<div title="系统菜单" data-options="region:'west'" style="width: 200px">
		<!-- 折叠面板 -->
		<div class="easyui-accordion" data-options="fit:true">
			<!-- 每个子div是一个面板 -->
			<div data-options="iconCls:'icon-add'" title="面板一">
				<!-- 每一个子div是一个选项卡 -->
				<div data-options="closable:true,iconCls:'icon-edit'" title="用户管理">
					内容一
				</div>
				<div title="角色管理"></div>
				<a class="easyui-linkbutton" id="myLink">点击打开选项卡</a>
				<script type="text/javascript">
					//为按钮绑定事件
					$(function(){
						$("#myLink").click(function(){
							//判断当前选项卡是否已经打开
							var e = $("#tabs").tabs("exists", "选型卡");
							if(e) {
								//选中
								$("#tabs").tabs(select, "选型卡");
							} else {
								//添加一个选项卡
								$("#tabs").tabs("add",{
									title: "选型卡"  ,
									closable: true ,
									iconCls: "icon-help" ,
									content: '<iframe src="page_common_index" frameborder="0" height="100%" width="100%"></iframe>'
								});
							}						
						});
					});
				</script>
			</div>
			
			<div data-options="iconCls:'icon-help'" title="面板二">面板二</div>
		</div>
	</div>
	<div data-options="region:'center'" >
		<!-- 选项卡面板 -->
		<div id="tabs" class="easyui-tabs" data-options="fit:true">
			<!-- 每个子div是一个选项卡 -->
			<div data-options="closable:true,iconCls:'icon-edit'" title="用户管理">
				内容一
			</div>
			<div title="角色管理">
				内容二
			</div>
		</div>
	</div>
	<div data-options="region:'east'" style="width: 100px">东部区域</div>
	<div data-options="region:'south'" style="height: 50px">南部区域</div>
</body>
</html>