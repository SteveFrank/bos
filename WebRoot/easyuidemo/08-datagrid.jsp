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
<script type="text/javascript">
	
</script>
</head>
<body>
	<!-- 普通静态的数据 -->
	<h2>方式一：将静态HTML代码渲染为datagrid</h2>
	<table class="easyui-datagrid">
		<thead>
			<tr>
				<th data-options="field:'id'">id</th>
				<th data-options="field:'name'">name</th>
				<th data-options="field:'age'">age</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>1</td>
				<td>admin</td>
				<td>12</td>
			</tr>
			<tr>
				<td>2</td>
				<td>admin</td>
				<td>14</td>
			</tr>
		</tbody>
	</table>
	
	<!-- 普通动态数据 -->
	<!-- 利用发起的url请求，获取json数据 -->
	<h2>方式二：利用JSON文件动态创建datagrid</h2>
	<table class="easyui-datagrid" data-options="url: '${pageContext.request.contextPath }/json/08-datagrid.json'">
		<thead>
			<tr>
				<th data-options="field:'id'">id</th>
				<th data-options="field:'name'">name</th>
				<th data-options="field:'age'">age</th>
			</tr>
		</thead>
	</table>
	
	<h2>方式三：使用js代码渲染为datagrid</h2>
	<script type="text/javascript">
		$(function() {
			//将页面上的table元素展示位datagrid
			$("#grid").datagrid({
				//设置数据表格的属性
				columns: [[
						{field:'id',id:'编号',checkbox: true},
						{field:'name',title:'姓名'},
						{field:'age',title:'年龄'}
						]],
				url:'${pageContext.request.contextPath}/json/08-datagrid.json',
				rownumbers: true ,
				//至允许单选
				singleSelect: false ,
				//显示分页条（主要部分功能）
				//是已经完成的分页功能可以直接使用
				pagination: true ,
				//工具栏
				toolbar: [
					{
						text: "添加" ,
						iconCls: 'icon-add' ,
						//点击事件的设置
						handler: function() {
							alert('add');
						}
					}
				] 
			});
		});
	</script>
	<table id="grid"></table>
</body>
</html>