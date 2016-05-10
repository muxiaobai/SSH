<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/public/head.jspf"%>
</head>
<body>
<table id="dg"></table>
<div id="win"></div>  
<script type="text/javascript">
	$(function(){
		//
		$('#dg').datagrid({
			url:'${basePath}/person_query.action',
			//width:200,
			frozenColumns:[[
				  {field:'checkbox',checkbox:true},//复选框
			     {field:'id',title:'用户id',width:200}//冻结列
			     	]],
			//queryParams:{type:""}, //第一查询的时候发出额外参数
			//idField:id,
			autoRowHeight:false,   // 固定高度
			striped:true,//是否显示斑马线效果。
			nowrap:true,  //如果为true，则在同一行中显示数据
			loadMsg:"正在加载......",
			singleSelect:false, // 支持单行显示
			rownumbers:true,  //显示行号
			fitColumns:true,//真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
			pagination:true, //是否支持分页显示
			pageNumber:1,  //初始页码
			pageSize:10,   //每页显示的记录数
			pageList:[5,10,15,20],   // 初始化页面大小选择列表
			toolbar:[{
				iconCls: 'icon-add',
				text:'添加用户',
				handler: function(){
					 //弹出添加窗体
					$("#win").window({
						title:'添加用户',
						width:520,
						height:380,
						collapsible:false,
						minimizable:false,
						maximizable:false,
						closable:true,
						modal:true,
						content:"<iframe src='${basePath}/send_person_save.action' scrolling='no' width='100%' height='100%' frameborder='0' />",
					});	
					}
			},'-',{
				iconCls: 'icon-edit',
				text:'更新用户',
				handler: function(){
					var rows=$("#dg").datagrid("getSelections");
					if(rows.length!=1){
						$.messager.show({
							title:'更新错误提示!',
							msg:'只能更新单行数据或者没有择需要更新的行',
							timeout:2000,
							showType:'slide'
						});
					}else{
						$("#win").window({
							title:'更新类别操作',
							width:520,
							height:380,
							collapsible:false,
							minimizable:false,
							maximizable:false,
							closable:true,
							modal:true,
							content:"<iframe src='${basePath}/send_person_update.action' scrolling='no' width='100%' height='100%' frameborder='0' />",
						});	
					}
				}
			},'-',{
				iconCls: 'icon-remove',
				text:'删除用户',
				handler: function(){
					var rows = $("#dg").datagrid("getSelections");
					if (rows.length == 0) {
						// 错误提示至少选中一行
						$.messager.show({
							title : '删除错误提示!',
							msg : '要删除至少选中一行数据',
							timeout : 2000,
							showType : 'slide'
						});
					} else {
						// 删除提示
						$.messager.confirm('确认对话框','确认要删除选中的行吗?',
										function(r) {
											if (r) {
												var ids = "";
												for ( var i = 0; i < rows.length; i++) {
													ids += rows[i].id + ",";
												}
												ids = ids.substring(0,ids.length - 1);
												$.post("${basePath}/person_delete.action",
													{ids : ids});
												$("#dg").datagrid("reload");
											}
										});
					} //if结束
				} //方法结束
			},'-',{
				text:"<input id='ss' name='type' />"
			}],//linkButton
		    columns:[[
		        {field:'name',title:'用户名',width:200},
		        {field:'password',title:'密码',width:200},
		        {field:'test',title:'测试',width:200},
		        {field:'date',title:'注册日期',width:200},
		        {field:'listprice',title:'价格',width:200,align:'right',
		        	// rowStyler: 设置行的样式的, 返回样式对于当前正行,返回数据是交给  style 
		        	// formatter: 仅仅给当前列设置数据的格式,返回数据是会显示当前列中
					rowStyler:function(value,row,index){
						//console.info(typeof(true));
						console.info("当前行索引:" + index +",当前行的对象:" + row + ",单元格值:" + value);
		        		return "background-color:#629388,color:#ff0000";
					},
					formatter: function(value,row,index){
		        		//console.info(value);
		        			return "2016-04-22 21:40:19";
		        	}
		        }
		    ]]

		});
		//搜索事件
		$("#ss").searchbox({
			 // 响应搜索的事件
			searcher:function(value,name){
				//获取当前查询参数
				
				$("#dg").datagrid("load",{type:name});
			//	alert(value + "," + name);
			}, 
			prompt:'Please Input Value' 
		});
		

	});
</script>
</body>
</html>