<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>menubutton菜单按钮</title>
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
<script
	src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"
	type="text/javascript"></script>
</head>
<body>
<script type="text/javascript">
	$(function() {
		//使用全局的index实现定向编辑与保存
		var index = 0;
		$("#grid").datagrid({
			columns :[ [ {
				field : 'id',
				checkbox : true,
			},{
				field : 'name',
				title : '姓名',
				width : 120,
				align : 'center',
				editor: {
					type: "validatebox",
					options:{
						//做非空校验
						required: true
					}
				} 
			}, {
				field : 'telephone',
				title : '手机号',
				width : 120,
				align : 'center',
				editor: {
					type: "validatebox",
					options:{
						//做非空校验
						required: true
					}
				} 
			} ] ],
			//事件：结束编辑状态时候进行触发
			onAfterEdit: function(rowIndex, rowData, changes) {
				//rowIndex可以获取到行索引
				//发送Ajax请求，将数据提交到服务端修改数据库中的数据
			} , 
			url :	 '${pageContext.request.contextPath}/json/staff.json',
			//工具栏
			toolbar: [
				{
					id: 'button-add',
					text: '编辑',
					iconCls: 'icon-add',
					handler: function() {
						//开启编辑状态
						//开启第一行的编辑状态
						$("#grid").datagrid("beginEdit", 0);
					}
				} , //增加按钮
				{
					id: 'button-add',
					text: '增加一行',
					iconCls: 'icon-add',
					handler: function() {
						//增加一行的操作
						$("#grid").datagrid("insertRow",{
								//插入一行的操作
								index : 0,
								row : {}
						});
						index = 0;
						//开启编辑状态
						$("#grid").datagrid("beginEdit", index);
					}
				} ,//增加一行按钮
				{
					id: 'button-save',
					text: '保存',
					iconCls: 'icon-save',
					handler: function() {
						//结束编辑状态
						$("#grid").datagrid("endEdit",index);
					}
				} ,//保存编辑信息按钮
				{
					id: 'button-edit',
					text: '编辑',
					iconCls: 'icon-edit',
					handler: function() {
						//结束编辑状态
						var rows = $("#grid").datagrid("getSelections");
						if(rows.length == 1) {
							//获得当前选中行的索引
							index = $("#grid").datagrid("getRowIndex",rows[0]);
							//开启选中行的编辑状态
							$("#grid").datagrid("beginEdit", index);
						}
					}
				}  //编辑选中行
			]
		});
	});
</script>
<table id="grid" class="easyui-datagrid"></table>
</body>
</html>