<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<%@page import="com.huatai.web.utils.Constants"%>
<html>
<head>
<title>关联指标管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/help/js/jscolor.js" type="text/javascript"></script>
<style type="text/css">
	.forminput{
		width: 200px;
		height: 30px;
		border-radius: 5px;
		border:1px solid #90C1E6;
	}
	.formtable{
		line-height: 50px;
	}
	.formtextarea{
		border-radius: 5px;
		border:1px solid #90C1E6;
	}
	.formbutton{
		width: 80px;
		height: 30px; 
		border-radius: 5px;
		border:1px solid #90C1E6;
	}
	.formhref{
		color:#3399FF;
	}
	.formCheck{
		vertical-align:middle;
		margin:-4px 2px 0px 2px;
	}
	.sql{
		width:240px;
		text-overflow: ellipsis; /* for IE */  
    	-moz-text-overflow: ellipsis; /* for Firefox,mozilla */  
    	overflow: hidden;  
    	white-space: nowrap;  
	}
</style>
<script type="text/javascript">
	function back(){
		window.history.back(-1);
	};
	$(function() {
		$('#pager').pager('${pageContext.request.contextPath}/admin/tarReg/listTarget',15,$('#parameterView').view().on('add',function(data){
			(function(zhis){
				if(data.checked){
					zhis.target.find('.ckTarget').each(function(){
						$(this).attr("checked",true);
					});
				}
			})(this);
		}));
		$('#pager1').pager('${pageContext.request.contextPath}/admin/tarReg/listTarRegSql',15,$('#parameterView1').view().on('add',function(data){
			(function(zhis){
				var deleteConfirm = new Ace.awt.ConfirmBox({
					widget : $('<div style="background-color:#FFFFFF; text-align:center; display:none; line-height:50px;width: 100px;height: 50px; border-radius: 5px;border:1px solid #90C1E6;"><span class="yesorno"><a href="javascript:void(0)" class="confirm">确定</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="cancel">取消</a></span></div>'),
					trigger : zhis.target.find('.removed'),
					positionAdjust : [ 6, -45 ]
				});	
				deleteConfirm.on('confirm',function(w, t) {
					
					$.post('${pageContext.request.contextPath}/admin/tarReg/deleteTarRegSql',{trsId:data.trsId},function(bit){
						if(bit==true){
							$.msgbox({time: 2000,msg: "删除成功!",icon:"success"});
						}
						$('#pager1').pager().reload(function(){
							zhis.target.remove();
						});
					});
				});
				zhis.target.find('.edit').click(function(){
					clearSqlForm();
					if(data.dateType!=null && data.dateType!=""){
						/* $("#dateType").val(data.dateType); */
						//var dateTypes = data.dateType.split("_");
						//var day = dateTypes[0];
						//var month = dateTypes[1];
						//var year = dateTypes[2];
						/* if(data.dateType=="DAY"){
							$("#day").attr("selected",true);
						}
						if(data.dateType=="MONTH"){
							$("#month").attr("selected",true);
						}
						if(data.dateType=="YEAR"){
							$("#year").attr("selected",true);
						} */
						$("select[name='dateType']").val(data.dateType);
					}
					
					$("#trsId").val(data.trsId);
					listGroDim(data.groupCode,data.groupDetailCode);
					$("#sqlCode").val(data.sqlCode);
					queryTableAlias();
					$('#sqlDiv').data("dialog", $.dialog({
						title : "编辑SQL",
						content : $('#sqlDiv')[0]
					}));
				});	
			})(this);
		}));
		//$('#pager').pager().reload();
		$('#parameterAjaxForm').ajaxForm(function(data) {
			$('#pager').pager().setPostData(Ace.parseQuery($('#parameterAjaxForm').serialize()));
			$('#pager').pager().setJSON(data);
		});
		
		$("#createParameter").click(function() {
			$('#saveParameterForm').data("dialog", $.dialog({
				title : "添加",
				content : $('#saveParameterForm')[0]
			}));
		});
		$('#saveParameterForm').ajaxForm(function(data) {
			$('#saveParameterForm').data("dialog").close();
			$('#pager').pager().reload();
		});
		$("#btnQuery").click(function(){
			query();
		});
		
		$("#graphType").change(function(){
			graphTypeChange();
		})
		$("#createTableHead").click(function(){
			$("#arrIndex").val(null);
			$("#txtTableHead").val(null);
			$("#txtIndex").val(null);
			$('#tableHeadDiv').data("dialog", $.dialog({
				title : "新增表头字段",
				content : $('#tableHeadDiv')[0]
			}));
		});
		$("#createTableAlias").click(function(){
			$("#tableAliasEditIndex").val(null);
			$("#txtTableName").val(null);
			$("#txtAliasName").val(null);
			$('#tableAliasDiv').data("dialog", $.dialog({
				title : "新增表别名",
				content : $('#tableAliasDiv')[0]
			}));
			$(".search_btn").focus();
		});
		//底部保存按钮逻辑
		$('#dimForm').submit(function() {
			var flag = validateDimForm();
			if(flag){
				var sqlIndex = $("#trId").val();
				//alert(sqlIndex);
				//var sqlCode = $("#sqlCode").val();
				if(sqlIndex==null || sqlIndex==""){
					var options = {
				        success:       dimFormSuccess,
				        url:	'${pageContext.request.contextPath}/admin/tarReg/create',
				        dataType:  'json',
				        clearForm: true
				    }; 
					$(this).ajaxSubmit(options);
				}else{
					var options = {
				        success:       dimFormSuccess,
				        url:	'${pageContext.request.contextPath}/admin/tarReg/update',
				        dataType:  'json',
				        clearForm: true
				    }; 
					$(this).ajaxSubmit(options);
				}
			}
	        
	        return false; 
	    });
		
	    //右侧SQL对话框确认按钮逻辑
	    $('#sqlForm').submit(function() {
			var options = {
				beforeSubmit:  sqlFormBefore,
		        success:       sqlFormSuccess,
		        dataType:  'json',
		        clearForm: true
		    }; 
			$(this).ajaxSubmit(options);
	        
	        return false; 
	    });
		query();
		
});	
	function validateDimForm(){
		return true;
	}
	function sqlFormBefore(){
		/* var dayChecked = $("#day").attr("checked")=="checked"?"1":"0";
		var monthChecked = $("#month").attr("checked")=="checked"?"1":"0";
		var yearChecked = $("#year").attr("checked")=="checked"?"1":"0";
		var dateType=dayChecked+"_"+monthChecked+"_"+yearChecked;
		$("#dateType").val(dateType); */
	}
	function sqlFormSuccess(){
		$('#sqlDiv').data("dialog").close();
		$('#pager1').pager().reload();
	}
	function graphTypeChange(){
		var graphType = $("#graphType").val();
		$(".prop").hide();
		if(graphType=="<%=Constants.GraphType3%>"){
			$(".s3").show();
		}else if(graphType=="<%=Constants.GraphType7%>"){
			reloadTableHead();
			$(".s2").show();
		}else{
			$(".s1").show();
		}
		
		$('#color').css("background-color","white").val("");
	}
	//保存完毕后关闭下方界面
	function dimFormSuccess(data){
		closeDim();
	}
	
	function query(){
		closeDim();
		var postData ={
				"regId":"${temReg.regId}",
				"targetCode":$("#targetCodeQuery").val(),
				"targetName":$("#targetNameQuery").val()
		}
		$('#pager').pager().setPostData(postData);
		$('#pager').pager().reload();
	}
	//TableHead操作 start
	//tableHead暂存于js变量中，与TarReg一起提交
	var tableHeadArr = new Array();
	function sortNumber(a, b)
	{
		return a.index - b.index
	}
	function addTableHead(colName,index){
		var obj = new Object();
		obj.colName=colName;
		obj.index=index;
		tableHeadArr.push(obj);
		tableHeadArr.sort(sortNumber);
	}
	function editTableHead(i){
		var obj = tableHeadArr[i];
		$("#txtTableHead").val(obj.colName);
		$("#txtIndex").val(obj.index);
		$("#arrIndex").val(i);
		$('#tableHeadDiv').data("dialog", $.dialog({
			title : "修改表头字段",
			content : $('#tableHeadDiv')[0]
		}));
	}
	function deleteTableHead(i){
		tableHeadArr.splice(i,1);
		reloadTableHead();
	}
	function reloadTableHead(){
		var tables = $(".tTableHead");
		var div = $("#tableHeadHidden");
		for(var i=0;i<tables.length;i++){
			var table = tables[i];
			$(table).empty();
			div.empty();
			for(var j=0;j<tableHeadArr.length;j++){
				var tableHead = tableHeadArr[j];
				$(table).append("<tr class='tb_tr_content'><td>"+tableHead.index+"</td><td>"+tableHead.colName+"</td><td><a href='javascript:editTableHead("+j+")'>修改</a>|<a href='javascript:deleteTableHead("+j+")'>删除</a></td></tr>")
				div.append("<input type='hidden' name='tabHeadName' value='"+tableHead.colName+"'/><input type='hidden' name='tabHeadIndex' value='"+tableHead.index+"'/>")
			}
		}
	}
	function saveTableHead(){
		var arrIndex = $("#arrIndex").val();
		var tableHead = $("#txtTableHead").val();
		var index = $("#txtIndex").val();
		if(arrIndex==null || arrIndex==""){
			addTableHead(tableHead,index);
		}else{
			deleteTableHead(arrIndex);
			addTableHead(tableHead,index);
		}
		reloadTableHead();
		$('#tableHeadDiv').data("dialog").close();
	}
	function queryTabHead(){
		var regId = '${temReg.regId}';
		var targetCode = $("#targetCodeSelected").val();
		$.getJSON('${pageContext.request.contextPath}/admin/tarReg/listTarRegTabHead',{regId:regId,targetCode:targetCode},function(data){
			tableHeadArr = new Array();
			$.each( data, function(index, content)
			  {
				addTableHead(content.colName,content.sort);
			  });
			reloadTableHead();
		});	
	}
	//TableHead操作 end
	
	
	//TableAlias操作 start
	//TableAlias暂存于js变量中，与TarRegSql一起提交
	var tableAliasArr = new Array();
	function addTableAlias(tableName,aliasName){
		var obj = new Object();
		obj.tableName=tableName;
		obj.aliasName=aliasName;
		tableAliasArr.push(obj);
	}
	function editTableAlias(i){
		var obj = tableAliasArr[i];
		$('.relaTab').eq(i).html("<td>"+(i+1)+"</td><td><input type='text' name='tableName' value='"+obj.tableName+"'/></td><td><input type='text' name='ualiasName' value='"+obj.aliasName+"'/></td><td><a href='javascript:saveTempHeadTab("+i+")'>保存</a>|<a href='javascript:saveTempHeadTab("+i+")'>删除</a></td>");
	}
	function saveTempHeadTab(i){
		var tableName = $("input[name='tableName']").val();
		var aliasName = $("input[name='ualiasName']").val();
		$("#txtTableName").val(tableName);
		$("#txtAliasName").val(aliasName);
		$('.relaTab').eq(i).html("<td>"+(i+1)+"</td><td>"+tableName+"</td><td>"+aliasName+"</td><td><a href='javascript:editTableAlias("+i+")'>修改</a>|<a href='javascript:deleteTableHead("+i+")'>删除</a></td>");
		var editObj = tableAliasArr[i];
		editObj.tableName=tableName;
		editObj.aliasName=aliasName;
		reloadTableAlias();
	}
	function deleteTableAlias(i){
		tableAliasArr.splice(i,1);
		reloadTableAlias();
	}
	function reloadTableAlias(){
		var div = $("#tableAliasHidden");
		var table = $("#aliasTable");
		$(table).empty();
		div.empty();
		for(var j=0;j<tableAliasArr.length;j++){
			var tableAlias = tableAliasArr[j];
			$(table).append("<tr class='tb_tr_content relaTab'><td>"+(j+1)+"</td><td>"+tableAlias.tableName+"</td><td>"+tableAlias.aliasName+"</td><td><a href='javascript:editTableAlias("+j+")'>修改</a>|<a href='javascript:deleteTableAlias("+j+")'>删除</a></td></tr>")
			div.append("<input type='hidden' name='aliasTableName' value='"+tableAlias.tableName+"'/><input type='hidden' name='aliasName' value='"+tableAlias.aliasName+"'/>")
		}
	}
	function saveTableAlias(){
		var tableAliasEditIndex = $("#tableAliasEditIndex").val();
		var tableName = $("#txtTableName").val();
		var aliasName = $("#txtAliasName").val();
		if ($.trim(tableName) == '') {
			$.dialog({content: '请输入关系表名！', time: 2000});
			return;
		}
		if ($.trim(aliasName) == '') {
			$.dialog({content: '请输入别名！', time: 2000});
			return;
		}
		if(tableAliasEditIndex==null || tableAliasEditIndex==""){
			addTableAlias(tableName,aliasName);
		}else{
			deleteTableAlias(tableAliasEditIndex);
			addTableAlias(tableName,aliasName);
		}
		reloadTableAlias();
		$('#tableAliasDiv').data("dialog").close();
	}
	function queryGroDim(groupCode,groupDetailCode){
		var trsId = $("#trsId").val();
		
	}
	function queryTableAlias(){
		var trsId = $("#trsId").val();
		$.getJSON('${pageContext.request.contextPath}/admin/tarReg/listSqlAlias',{trsId:trsId},function(data){
			tableHeadArr = new Array();
			$.each( data, function(index, content)
			  {
				addTableAlias(content.tableName,content.aliasName);
			  });
			reloadTableAlias();
		});	
	}
	function clearTableAlias(){
		tableAliasArr = new Array();
		reloadTableAlias();
	}
	function clearSqlForm(){
		$("#groDimDiv").empty();
		$("#groDimDetailDiv").empty();
		$("#trsId").val(null);
		$("#sqlCode").val(null);
		$("select[name='dateType']").each(function(){
			$(this).attr("selected",false);
		});
		$('#sqlForm')[0].reset();
		clearTableAlias();
	}
	function clearDimForm(){
		/* $(':input','#dimForm') 
		.not(':button, :submit, :reset, .regId') 
		.removeAttr('checked') 
		.removeAttr('selected'); */
		$(':radio,:checkbox','#dimForm') 
		.removeAttr('checked') 
		.removeAttr('selected');
		
		$(':input','#dimForm') 
		.not(':button, :submit, :reset, .regId, .tempId,:radio,:checkbox') 
		.val('');
		$('#color').css("background-color","white")
	}
	//TableAlias操作 end
	//展开与收起下方界面
	var dimShowed = false;
	function showDim(targetCode){
		if(!dimShowed){
			clearDimForm();
			//console.log($("#trId").val());
			graphTypeChange();
			$("input[name='ckTarget'][value="+targetCode+"]").attr("checked",true);
			var targetCodes = $(".targetCode");
			for(var i=0;i<targetCodes.length;i++){
				$(targetCodes[i]).val(targetCode);
			}
			querySql(targetCode);
			queryTarReg();
			queryTabHead();
			queryTarRegQue(targetCode);
			$("#dimDiv").show();
			disableCkTargets();
			dimShowed = true;
			$("#submitBtn").focus();
		}else{
			
		}
	}
	function querySql(targetCode){
		var postData ={
				"regId":"${temReg.regId}",
				"targetCode":targetCode,
		}
		$('#pager1').pager().setPostData(postData);
		$('#pager1').pager().reload();
	}
	function showAddSql(){
		//var targetCode = $("#targetCodeSelected").val();
		listGroDim();
		clearSqlForm();
		$('#sqlDiv').data("dialog", $.dialog({
			title : "新增SQL",
			content : $('#sqlDiv')[0]
		}));
	}
	function listGroDim(groupCode,groupDetailCode){
		var targetCode = $("#targetCodeSelected").val();
		var gdId = null;
		$.getJSON('${pageContext.request.contextPath}/admin/tarReg/listGroDim',{targetCode:targetCode},function(data){
			var div = $("#groDimDiv");
			div.append('<option value="">--请选择--</option>');
			$.each( data, function(index, content)
			  {
				var flag = false;
				if(groupCode!=null && groupCode==content.groDimTypeCode){
					flag = true;
					gdId = content.gdId;
				}
					
				div.append('<option gdId='+content.gdId+' value="'+content.groDimTypeCode+'" class="formCheck" '+(flag?"selected":"")+'>'+content.groDimTypeName+"</option>")
			 });
			if(groupDetailCode!=null){
				selectGroDim(gdId,groupDetailCode);
			}
		});	
	}
	function selectGroDim(gdId,groupDetailCode){
		$("#groDimDetailDiv").empty();
		var targetCode = $("#targetCodeSelected").val();
		$.getJSON('${pageContext.request.contextPath}/admin/tarReg/listGroDimDetail',{targetCode:targetCode,gdId:$("#groDimDiv").find("option:selected").attr ("gdId")},function(data){
			var div = $("#groDimDetailDiv");
			div.append('<option value="">--请选择--</option>')
			$.each( data, function(index, content)
			  {
				var flag = false;
				if(groupDetailCode!=null && groupDetailCode==content.groDimCode){
					flag = true;
				}
				div.append('<option  value="'+content.groDimCode+'" class="formCheck" '+(flag?"selected":"")+'>'+content.groDimName+"</option>")
			});
		});	
	}
	function saveSql(){
		var sqlIndex = $("#trsId").val();
		var sqlCode = $("#sqlCode").val();
		if(sqlIndex==null){
			addSql(sqlCode);
		}else{
			editSql(sqlCode,i);
		}
	}
	//查询下方TarReg信息并展示
	function queryTarReg(){
		var regId = '${temReg.regId}';
		var targetCode = $("#targetCodeSelected").val();
		$.ajax({
			url : "${pageContext.request.contextPath}/admin/tarReg/findTarReg",
			type:"get",
			dataType : "json",
			data:{regId:regId,targetCode:targetCode},
			success : function(data) {
				if(data){
					var color = null;
					if(data.color != null){
						color = data.color.substring(1,7);
					}
					$("#trId").val(data.trId);
					$("#graphType").val(data.graphType);
					graphTypeChange();
					$("#graphTitle").val(data.graphTitle);
					//$("#tableName").val(data.graphTitle);
					$("#xName").val(data.xName);
					$("#yName").val(data.yName);
					$("#unit").val(data.unit);
					$("#color").val(color);
					$("#sort").val(data.sort);
					$("#pwd").val(data.pwd);
					$("input[name='isDisplay'][value="+data.isDisplay+"]").attr("checked",true);
					$("input[name='dimType'][value="+data.dimType+"]").attr("checked",true);
					$("#roleOrgType").val(data.roleOrgType);
					if(data.isRank=="1")
						$("#isRank").attr("checked",true);
					$("#rankOrgType").val(data.rankOrgType);
					isRankChange();
				}
			}
		});
	}
	function selectTarget(obj){
		var targetCode = obj.value;
		var checked = $(obj).attr("checked");
		if(checked=="checked"){
			showDim(targetCode);
		}else{
			$.confirm("是否确定删除？", 
			function() {
				$.post('${pageContext.request.contextPath}/admin/tarReg/delete',{targetCode:targetCode,regId:"${temReg.regId}"},function(bit){
					$.msgbox({time: 2000,msg: "删除成功!",icon:"success"});
					$('#pager').pager().reload();
				});
			},
			function(){
				$(obj).attr("checked",true);
			});
		}
	}
	function disableCkTargets(){
		$(".ckTarget").each(function(){
			$(this).attr("disabled","disabled");
		})
		$(".aShowDim").each(function(){
			$(this).attr("disabled","disabled");
		})
		
	}
	function enableCkTargets(){
		$(".ckTarget").each(function(){
			$(this).removeAttr("disabled");
		})
		$(".aShowDim").each(function(){
			$(this).removeAttr("disabled");
		})
	}
	function cancel(){
		closeDim();
		$('#pager').pager().reload();
	}
	function closeDim(){
		$("#dimDiv").hide();
		dimShowed = false;
		enableCkTargets();
	}
	function queryTarRegQue(targetCode){
		var root = $("#tarRegQueDiv");
		root.empty();
		$.getJSON('${pageContext.request.contextPath}/admin/tarReg/listQueryDim',{targetCode:targetCode},function(data){		
			if(data.length!=0){
				$.each( data, function(index, content){
					if(content.checked==true){
							root.append("<input type='checkbox' style='margin-left: 20px;' name='qdId' checked value='"+content.qdId+"' class='formCheck'>"+content.queryDimName)
					}else{
							root.append("<input type='checkbox' style='margin-left: 20px;' name='qdId' value='"+content.qdId+"' class='formCheck'>"+content.queryDimName)
					}
			    });
				return true;
			}
			//root.append("<span><input type='checkbox' value='' class='formCheck'>无</span>");
			root.append("<span>无</span>");			  
		});	
	}
	function isRankChange(){
		var isRank = $("#isRank").attr("checked");
		if(isRank=="checked"){
			$("#rankOrgTypeDiv").show();
		}else{
			$("#rankOrgTypeDiv").hide();
		}
	}	
	$("#saveSqlBtn").live("click",function(){
		var sqlCode = $("#sqlCode").val();
		if($.trim(sqlCode) == ''){
			$.dialog({time: 2000,content:  "请输入SQL代码！"});
			return;
		}
		$("#sqlCode").val(encodeURIComponent($("#sqlCode").val()));
		$("#sqlForm").submit();
	})
	
</script>
</head>
<body>
<!--上方区域 start -->
	<div class="business_title">模板区域管理</div>
	<div class="col_lg_04" style="width: 1203px">
		<div class="business_search_list_warp" style="width: 95%">
		 	<div class="business_search" style="padding:0px;">
		 	<table style="width:40%">
				<tbody>
					<tr>
						<td>模板名称：${templet.tempName }</td>
						<td style="text-align: left;">区域名称：${temReg.regName }</td>
						<td style="text-align: left;"><a style="position: absolute;margin-top: -15px; margin-left: 630px; line-height: 25px;padding: 0 11px;border: 1px #26bbdb solid;
            				border-radius: 3px; display: inline-block;" onclick="back()">返回</a>
            			</td>
				</tbody>
				</table>
				<div class="business_search_left">
				
					<form id="parameterAjaxForm"
						action="${pageContext.request.contextPath}/admin/temReg/list"
						method="post">
						<input type="hidden" name="pageSize" value="15" />
						<table>
							<tbody>
								<tr>
									<td style="text-align: right">指标代码：</td>
									<td><input style="width: 200px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" type="text"
										name="targetCodeQuery" id="targetCodeQuery" class="text">&nbsp;&nbsp;</td>
									<td style="text-align: right">指标名称：</td>
									<td><input style="width: 200px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" type="text"
										name="targetNameQuery" id="targetNameQuery"  class="text">&nbsp;&nbsp;</td>
									<td><input type="button" value="查&#12288;询"
										style="width: 60px;height: 25px;border-radius: 5px;border:1px solid #90C1E6;" id="btnQuery"></td>
								</tr>
							</tbody> 
							
						</table>
					</form>
				</div>
			</div>
			
			<table cellspacing="0" cellpadding="0" class="t-list table"id="parameterView">
				<tbody>
					<tr>
						<th width="20px">操作</th>
						<th width="100px">指标代码</th>
						<th width="150px">指标名称</th>
						<th width="100px">指标属性</th>
						<th width="80px">创建时间</th>
						<th width="80px">修改时间</th>
						<!-- <th width="80px">创建人</th>
						<th width="80px">修改人</th> -->
						<th style="border-right: 1px solid #ccc; width: 80px">操作</th>
					</tr>
					<tr class="tb_tr_content template" name="default">
						<td><input type="checkbox" class="ckTarget" name="ckTarget" value="{targetCode}" onclick="selectTarget(this)"></td>
						<td>{targetCode}</td>
						<td>{targetName}</td>
						<td>{targetType:dict({0:'主题或板块',1:'一级指标',2:'二级指标分组',3:'二级指标'})}</td>
						<td>{createTime}</td>
						<td>{modifyTime}</td>
						<!-- <td>{creatorName}</td>
						<td>{modifierName}</td> -->
						<td style="border-right: 1px solid #ccc"><span
							class="span_edit"> 
							<a href="javascript:showDim('{targetCode}')" style="float: left;margin-right:5px" class="aShowDim formhref">设置维度</a>
						</span></td>
					</tr>
				</tbody>
				<input type="hidden" name="tempId" value="${templet.tempId }" />
			</table>
			<div id="pager"></div>
		</div>
		
		<div class="clear"></div>
	</div>
<!--上方区域 end -->

<!--新增与修改表TableHead字段对话框 start -->
	<div id="tableHeadDiv" style="display: none;">
		<div id="tagContent2" class="tagContent tagContent_div"
			style="display: block; background: #F7F7F7">
			<input type="hidden" id="arrIndex"/>
			<table style="line-height: 30px">
				<tbody>
					<tr>
						<td class="text_right">表头字段：</td>
						<td class="text_left"><input type="text" id="txtTableHead"
							style="border-radius:5px; width:200px;height: 30px;border:1px solid #90C1E6;"></input></td>
					</tr>
					<tr><td height="20px" colspan="2"></td></tr>
					<tr>
						<td class="text_right">排序：</td>
						<td class="text_left"><input type="text" id="txtIndex"
							style="border-radius:5px; width:200px;height: 30px;border:1px solid #90C1E6;"></input></td>
					</tr>
					
					<tr><td height="20px" colspan="2"></td></tr>
					<tr>
						<td></td>
						<td class="text_left">
							<input type="button" value="确 定" onclick="javascript:saveTableHead()"
								class="search_btn">&nbsp;&nbsp;<input type="button"
								value="取 消" class="search_btn close">
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
<!--新增与修改TableHead字段对话框 end -->

<!--新增与修改SQL代码对话框 start -->
	<div id="sqlDiv" style="display: none;">
		<form id="sqlForm"
		action="${pageContext.request.contextPath}/admin/tarReg/saveTarRegSql"
		method="post" >
			<input type="hidden" name="targetCode" class="targetCode" value="" />
			<!-- <input type="hidden" id="dateType" name="dateType"/> -->
			<div id="tagContent2" class="tagContent tagContent_div"
				style="display: block; background: #F7F7F7">
				<input type="hidden" name="regId" class="regId" value="${temReg.regId }" />
				<input type="hidden" id="trsId" name="trsId" value=""/>
				<table style="line-height: 30px">
					<tbody>
						<tr>
							<td class="text_right">SQL代码：</td>
							<td class="text_left">
								<textarea rows="6" cols="100" class="formtextarea" id="sqlCode" name="sqlCode"></textarea>
							</td>
						</tr>
						<tr><td height="20px" colspan="2"></td></tr>
						<tr>
							<td class="text_right">分组维度：</td>
							<td class="text_left" >
								<select name="groupCode" id="groDimDiv" onchange="selectGroDim(this)" style="width: 200px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;">
									<option value=''>--请选择--</option>
								</select>
							</td>
						</tr>
						<tr><td height="20px" colspan="2"></td></tr>
						<tr>
							<td class="text_right">分组维度详细：</td>
							<td class="text_left" >
								<select id="groDimDetailDiv" name="groupDetailCode" style="width: 200px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;">
									<option value=''>--请选择--</option>
								</select>
							</td>
						</tr>
						<tr><td height="20px" colspan="2"></td></tr>
						<tr>
							<td class="text_right">日期查询类型：</td>
							<td class="text_left">
								<select name="dateType" class="formCheck" style="width: 200px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;">
									<option value="">--请选择--</option>
									<option value="DAY">日</option>
									<option value="MONTH">月</option>
									<option value="YEAR">年</option>
								</select>
							</td>
						</tr>
						<tr><td height="20px" colspan="2"></td></tr>
					</tbody>
				</table>
				<div><span style="width:55px">关联表：</span><a href="#" class="formhref" id="createTableAlias">新增</a></div>
				<!-- TableAlias显示区 start -->
				<div id="tableAliasHidden"></div>
				<table style="line-height: 30px;width:681px" id="parameterView2" class="t-list table  "> 
					<tbody>
						<tr>
							<th width="60px">序号</th>
							<th>关联表名</th>
							<th>别名</th>
							<th width="100px">操作</th>
						</tr>
					</tbody>
					<tbody id="aliasTable">
							
					</tbody>
					
				</table>
				<!-- TableAlias显示区 end -->	
				<div><input type="button" id="saveSqlBtn" style="margin-left: 300px;" value="确 定" 
					class="search_btn">&nbsp;&nbsp;<input type="button"
					value="取 消" class="search_btn close"></div>
				</div>
		</form>
	</div>
<!--新增与修改SQL代码对话框 end -->

<!--下方设置维度区域 start -->
	<div id="dimDiv" style="display: none;width:1160px;border:1px solid #dee1e2;padding:20px;background:#fff">
		<form id="dimForm"
		action="${pageContext.request.contextPath}/admin/tarReg/create"
		method="post" >
		<div id="tableHeadHidden" style="display:none"></div>
		<input type="hidden" name="trId" id="trId" value="" />
		<input type="hidden" name="regId" id="regId" class="regId" value="${temReg.regId }" />
		<input type="hidden" name="tempId" id="tempId" class="tempId" value="${templet.tempId }" />
		<input type="hidden" name="targetCode" class="targetCode" id="targetCodeSelected" value="" />
		<div id="tagContent3" class="" style="display: block;">
			<div>属性设置：</div>
			<div>
				<!--左侧，维度设置相关 start -->
				<div class="left" style="width:550px; float:left; display:inline;padding-right:20px">
					<div>
						<table class="formtable">
							<tr>
								<td>图形类型：</td>
								<td>
									<select name="graphType" id="graphType" class="forminput" autocomplete="off">
										<option value="">请选择</option>
										<c:forEach items="${picTypes}" var="picType" varStatus="idx">
											<option value="${picType.code }">${picType.name }</option>
										</c:forEach>
									</select>
								</td>
							</tr>
						</table>
						<!--根据图形类型选择，显示相应表单 -->
						<table class="formtable graphTypeOp" id="s1">
							<tr class="prop s2">
								<td colspan="2"><div>表格：<a href="javascript:void(0)" id="createTableHead" style="color:#3399FF">新增</a></div></td>
							</tr>
							<tr>
								<td  colspan="2">
									<table class="t-list table prop s2 " cellspacing="0" cellpadding="0">
										<tbody>
											<tr>
												<th width="50px">排序号</th>
												<th width="100px">表头字段</th>
												<th style="border-right: 1px solid #ccc; width: 80px">操作</th>
											</tr>
										</tbody>
										<tbody class="tTableHead">
										
										</tbody>
									</table>
								</td>
							</tr>
							<tr class="prop s1 s2 s3">
								<td>标题：</td>
								<td>
									<input type="text" name="graphTitle" id="graphTitle" class="forminput">
								</td>
							</tr>
							<tr class="prop s1">
								<td>X轴名称：</td>
								<td>
									<input type="text" name="xName" id="xName" class="forminput">
								</td>
							</tr>
							<tr class="prop s1">
								<td>Y轴名称：</td>
								<td>
									<input type="text" name="yName" id="yName" class="forminput">
								</td>
							</tr>
							<tr class="prop s1">
								<td>单位：</td>
								<td>
									<select name="unit" id="unit" class="forminput" autocomplete="off">
										<option value="">请选择</option>
										<c:forEach items="${unitTypes}" var="item">
											<option value="${item.code}">${item.name}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<!-- <tr>
								<td>维度类型：</td>
								<td>
									机构<input type="radio" name="dimType" value="0" id="dimType0" checked autocomplete="off">&nbsp;&nbsp;
									日期<input type="radio" name="dimType" value="1" id="dimType1" autocomplete="off">
								</td>
							</tr> -->
							<tr class="prop s1">
								<td>颜色：</td>
								<td>
										<input class="jscolor" name="color" id="color"
										style="width: 200px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" 
										value="" type="text">
								</td>
							</tr>
							<tr class="prop s1">
								<td>合并标识：</td>
								<td>
									<input type="text" name="pwd" id="pwd" class="forminput">
								</td>
							</tr>
							<tr><!-- class="prop s1" -->
								<td>是否显示：</td>
								<td>
									是<input type="radio" name="isDisplay" value="1" id="isDisplay1" checked autocomplete="off">&nbsp;&nbsp;
									否<input type="radio" name="isDisplay" value="0" id="isDisplay0" autocomplete="off">
									
								</td>
							</tr>
							
						</table>
						<br/>
						<div>
							排序：<input type="text" name="sort" id="sort" class="forminput">
						</div>
						<br/>
						<div>
							最低权限机构类型：
							<select name="roleOrgType" id="roleOrgType" class="forminput">
								<option>无</option>
								<c:forEach items="${groDimDetails}" var="item">
									<option value="${item.groDimCode}">${item.groDimName}</option>
								</c:forEach>
							</select>
						</div>
						<br/>
						<div>
							是否同级排名：
							<input type="checkbox" id="isRank" name="isRank" value="1" class="formCheck" onclick="isRankChange()">
						</div>
						<br/>
						<div id="rankOrgTypeDiv" style="display:none">
							开始同级排名的最高机构类型：
							<select name="rankOrgType" id="rankOrgType" class="forminput">
								<c:forEach items="${groDimDetails}" var="item">
									<option value="${item.groDimCode}">${item.groDimName}</option>
								</c:forEach>
							</select>
						</div>
						<br/>
						<div>
							关联的查询维度：
							<span id="tarRegQueDiv">
								Code1<input type="checkbox" name="qdId" value="1">
								Code2<input type="checkbox" name="qdId" value="2">
							</span>
						</div>
					</div>
				</div>
				
				<!--左侧，维度设置相关 end -->
				<!--右侧，SQL设置相关 start -->
				<div class="" style="width:550px; float:left; display:inline;margin-top:10px;padding-left:20px">
					<div>SQL：<a href="javascript:showAddSql()" style="color:#3399FF">新增</a></div>
					<table cellspacing="0" cellpadding="0" class="t-list table"id="parameterView1">
						<tbody>
							<tr>
								<th width="100px">SQL代码</th>
								<th width="80px">创建时间</th>
								<th width="80px">修改时间</th>
								<th style="border-right: 1px solid #ccc; width: 80px">操作</th>
							</tr>
							<tr class="tb_tr_content template" name="default">
								<td><div class="sql">{sqlCode}</div></td>
								<td style="width:100px">{createTime}</td>
								<td style="width:100px">{modifyTime}</td>
								<td style="border-right: 1px solid #ccc;width:100px"><span
									class="span_edit"> 
									<a href="javascript:void(0)" style="float: left;margin-right:5px" class="edit">编辑</a>
									<a href="javascript:void(0)" style="float: left;margin-right:5px" class="removed">删除</a>
								</span></td>
							</tr>
						</tbody>
					</table>
					<div id="pager1"></div>
					
				</div>
				<!--右侧，SQL设置相关 start -->
			</div>
			
			<div style="clear:both;" align="center" >
				<input type="submit" value="保存" class="formbutton" style="margin:20px" id="submitBtn"/>
				<input type="button" value="取消" class="formbutton" style="margin:20px" onclick="cancel()"/>
			</div>
		</div>
	</form>
	</div>
<!--下方设置维度区域 end -->	

<!--新增与修改表TableAlias字段对话框 start -->
	<div id="tableAliasDiv" style="display: none;">
		<div class="tagContent tagContent_div" style="display: block; background: #F7F7F7">
			<input type="hidden" id="tableAliasEditIndex"/>
			<table style="line-height: 30px">
				<tbody>
					<tr>
						<td class="text_right">关系表名：</td>
						<td class="text_left">
							<input type="text" id="txtTableName" class="forminput"></input>
						</td>
					</tr>
					<tr><td height="20px" colspan="2"></td></tr>
					<tr>
						<td class="text_right">别名：</td>
						<td class="text_left">
							<input type="text" id="txtAliasName" class="forminput"></input>
						</td>
					</tr>
					
					<tr><td height="20px" colspan="2"></td></tr>
					<tr>
						<td></td>
						<td class="" style="">
							<input type="button" value="确 定" onclick="javascript:saveTableAlias()"
								class="search_btn">&nbsp;&nbsp;<input type="button"
								value="取 消" class="search_btn close">
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
<!--新增与修改TableHead字段对话框 end -->
</body>
</html>