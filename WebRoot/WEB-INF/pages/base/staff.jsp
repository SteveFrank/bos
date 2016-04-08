<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
	function doAdd(){
		///增加的方法
		$('#addStaffWindow').window("open");
	}
	
	function doView(){
		alert("查看...");
	}
	
	function doDelete(){
		//作废功能
		//1、首先给出提示，需要进行选择等，保证前端完成这一个步骤
		//获取当前数据表格选中的行
		var rows = $("#grid").datagrid("getSelections");
		if(rows.length == 0) {
			//没有选中
			$.messager.alert("提示信息","请您选择需要作为的取派员","warning");
		} else {
			//已经选中，可以执行批量作废
			//提示操作者是否确认
			$.messager.confirm("提示信息","您确定删除当前已经选中的取派员吗？",function(r){
				if(r) {
					//2、获取选中后获取的为标示数组
					var array = new Array();
					for(var i=0;i<rows.length;i++) {
						var id = rows[i].id;
						array.push(id);
					}
					var ids = array.join(",");
					window.location.href = "${pageContext.request.contextPath}/staff/staffAction_delete.action?ids="+ids;	
				}
			});
			
		}
	}
	
	function doRestore(){
		//还原功能
		//1、给出提示信息，如果用户没有选中，当前选中的条目数
		var rows = $("#grid").datagrid("getSelections");
		if(rows.length == 0) {
			//没有选中
			$.messager.alert("提示信息", "请您选择需要还原到正常工作状态的取派员", "warnning");
		} else {
			//若是已经被选中的员工
			$.messager.confirm("确认信息", "您确定需要还原这些取派员吗？", function(r) {
				if(r) {
					//获取选中后获取的为选中的数组
					var array = new Array();
					for(var j=0;j<rows.length;j++) {
						var id = rows[j].id;
						array.push(id);
					}
					var ids = array.join(",");
					window.location.href = "${pageContext.request.contextPath}/staff/staffAction_restore.action?ids="+ids;	
				}	
			});
		}
	}
	//工具栏
	var toolbar = [ {
		id : 'button-view',	
		text : '查询',
		iconCls : 'icon-search',
		handler : doView
	}, 
	//是否具有某个权限 
	//使用的是shiro这样的一套标签
	<shiro:hasPermission name="staff">
		{
			id : 'button-add',
			text : '增加',
			iconCls : 'icon-add',
			handler : doAdd
		} , 
	</shiro:hasPermission>
	{
		id : 'button-delete',
		text : '作废',
		iconCls : 'icon-cancel',
		handler : doDelete
	},{
		id : 'button-save',
		text : '还原',
		iconCls : 'icon-save',
		handler : doRestore
	}];
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	},{
		field : 'name',
		title : '姓名',
		width : 120,
		align : 'center'
	}, {
		field : 'telephone',
		title : '手机号',
		width : 120,
		align : 'center'
	}, {
		field : 'haspda',
		title : '是否有PDA',
		width : 120,
		align : 'center',
		//利用formatter进行转换
		formatter : function(data,row, index){
			if(data=="1"){
				return "有";
			}else{
				return "无";
			}
		}
	}, {
		field : 'deltag',
		title : '是否作废',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="0"){
				return "正常工作"
			}else{
				return "已经作废";
			}
		}
	}, {
		field : 'standard',
		title : '取派标准',
		width : 120,
		align : 'center'
	}, {
		field : 'station',
		title : '所谓单位',
		width : 200,
		align : 'center'
	} ] ];
	
	$(function(){
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});
		
		// 取派员信息表格
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : false,
			rownumbers : true,
			striped : true,
			pageList: [5,10,30,50,100],
			pagination : true,
			toolbar : toolbar,
			url : "${pageContext.request.contextPath }/staff/staffAction_pageQuery.action",
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow
		});
		
		// 添加取派员窗口
		$('#addStaffWindow').window({
	        title: '添加取派员',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
		// 修改取派员窗口
		$('#editStaffWindow').window({
	        title: '修改取派员',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
	});
		
	//双击用户条目
	function doDblClickRow(rowIndex, rowData){
		//打开修改取派员
		$('#editStaffWindow').window("open");
		//将双击行数据显示到修改窗口的表单中
		//通过key进行一一对应的操作
		$("#editStaffForm").form("load",rowData);
	}
</script>	
</head>

<script type="text/javascript">
	$(function() {
		var regex = /^1[3|4|5|7|8][0-9]{9}$/;
		$.extend($.fn.validatebox.defaults.rules, {
			//扩展对于手机号的校验规则
			phoneNumber: {
				validator: function(value, param) {
					return regex.test(value);
				} , 
				message: "手机号输入有误！"
			}
		});
	});
</script>

<body class="easyui-layout" style="visibility:hidden;">
	<div region="center" border="false">
    	<table id="grid"></table>
	</div>
	<div class="easyui-window" title="对收派员进行添加" id="addStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" 
					class="easyui-linkbutton" plain="true" >保存</a>
				<script type="text/javascript">
					//为保存按钮绑定事件
					$("#save").click(function() {
						//表单校验
						var form = $("#addStaffFrom").form("validate");
						alert("添加取派员成功");
						if(form) {
							//校验通过,提交表单
							$("#addStaffFrom").submit();
						}
					});
				</script>
			</div>
		</div>
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="addStaffFrom" action="${pageContext.request.contextPath }/staff/staffAction_add.action" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					<!-- 取派员编号由我们系统自己生成 
					<tr>
						<td>取派员编号</td>
						<td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
					</tr> 
					-->
					<tr>
						<td>姓名</td>
						<td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>手机</td>
						<!-- 按照校验规则进行校验 -->
						<td><input type="text" 
								data-options="validType:'phoneNumber'" 
								name="telephone" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td colspan="2">
						<input type="checkbox" name="haspda" value="1" />
						是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td>
							<input type="text" name="standard" class="easyui-validatebox" required="true"/>  
						</td>
					</tr>
					</table>
			</form>
		</div>
		<div class="easyui-window" title="对收派员进行修改" id="editStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="edit" icon="icon-save" 
					class="easyui-linkbutton" plain="true" >确认修改</a>
				<script type="text/javascript">
					//为保存按钮绑定事件
					$("#edit").click(function() {
						//表单校验
						var form = $("#editStaffForm").form("validate");
						alert("修改取派员成功");
						if(form) {
							//校验通过,提交表单
							$("#editStaffForm").submit();
						}
					});
				</script>
			</div>
		</div>
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="editStaffForm" action="${pageContext.request.contextPath }/staff/staffAction_edit.action" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					<!-- 取派员编号由我们系统自己生成 
					<tr>
						<td>取派员编号</td>
						<td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
					</tr> 
					-->
					<input type="hidden" name="id" >
					<tr>
						<td>姓名</td>
						<td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>手机</td>
						<!-- 按照校验规则进行校验 -->
						<td><input type="text" 
								data-options="validType:'phoneNumber'" 
								name="telephone" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td colspan="2">
						<input type="checkbox" name="haspda" value="1" />
						是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td>
							<input type="text" name="standard" class="easyui-validatebox" required="true"/>  
						</td>
					</tr>
					</table>
			</form>
		</div>
	</div>
</body>
</html>	