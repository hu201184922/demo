<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<meta name="decorator" content="admin-popup">
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<html>

<head>
<title>首选项定义</title>
<script type="text/javascript" >
$(document).ready(function(){
	//聚焦第一个输入框
	$("#name").focus();
	//为inputForm注册validate函数
	$("#definitionForm").validate({
		rules :{
			code:{
				remote:"${ctx}/admin/preference/def/definition/checkItem"
			},
			sortIndex : {
				digits : true
			}
		},
		messages :{
			code:{
				remote:"代码已存在"
			}
		}
	});
	 $("#dataTypes").hide();
	 $("#dropDownTypes").hide();
	 $("#dictCodes").hide();
});
function saveAndClose(){
	var validator = $("#definitionForm").validate();
    if (validator.form()) {//判断加入所有校验都通过后再做ajax提交；
    	$.ajax({
			type : "post",
			data : $("#definitionForm").serialize(),
			url : "${ctx}/admin/preference/def/definition/save" ,
			success : function(msg) {
				parent.$.fancybox.close();
			}
		});
    }
    else $("#definitionForm").submit();
};
function closeChange(){
	parent.$.fancybox.close();
};
</script>
</head>
<body>
	<form id="definitionForm" action="${ctx}/admin/preference/def/definition/save" method="post" 
	 class="form-horizontal">
		<div class="hiddenFields">
			<input type="hidden" id="preferenceDefinitionId" name="id" value="${preferenceDefinition.id }"/>
			<input type="hidden" name="preferenceCategory.id" value="${preferenceCategoryId }"/>
		</div>
		<div class="panel panel panel-info">
			<div class="panel-heading">首选项管理</div>
			<div class="panel-body">
				<div class="form-group">
					<label for="name" class="col-md-2 control-label">名称</label>
					<div class="col-md-4">
						<input id="name" type="text"name="name" class="form-control input-sm required" value="${preferenceDefinition.name}">
					</div>
				</div>
				<div class="form-group">
					<label for="code" class="col-md-2 control-label">代码</label>
					<div class="col-md-4">
						<input id="code" type="text"name="code" class="form-control input-sm required" value="${preferenceDefinition.code}">
						<span id="codeTip"></span>
					</div>
				</div>
			<%--	<div class="form-group">
					<label for="dataType" class="col-md-2 control-label">数据类型</label>
					<div class="col-md-4">
						<tags:dict itemCode="${preferenceDefinition.dataType}" dictCode="dataType" type="select"
							id="dataType" name="dataType" cssClass="form-control input-sm required" />
					</div>
				</div>
				--%>
				<div class="form-group">
					<label for="editorType" class="col-md-2 control-label">控件类型</label>
					<div class="col-md-4">
						<tags:dict itemCode="${preferenceDefinition.editorType}" dictCode="editorType" type="select"
							id="editorType" name="editorType" cssClass="form-control input-sm required" />
					</div>
				</div>
				<div class="form-group" id="dataTypes">
					 <label for="dataType" class="col-md-2 control-label">数据类型</label>
					 <div class="col-md-4">
						<tags:dict itemCode="${preferenceDefinition.dataType}" dictCode="dataType" type="select"
							id="dataType" name="dataType" cssClass="form-control input-sm required" />
					</div>
				</div>
					
				<div class="form-group" id="dropDownTypes">						
					<label for="dropDownTypeLabel" class="col-md-2 control-label">选择类型</label>
					<div class="col-md-4">
					 	<select name="dropDownType" id="dropDownType" class="form-control input-sm">
					 		<option id="Pchoose" value="">请选择</option>
					 		<option value="dictionary">数据字典类型</option>					 	
					 	</select>
					</div>
				</div>
				
				<div class="form-group" id="dictCodes">						
					<label for="dictCode" class="col-md-2 control-label">字典code</label>
					 <div class="col-md-4">
					  <input type="hidden" name="dropDownMeta"  id="dropDownMeta" value='${preferenceDefinition.dropDownMeta}'/>
					 <input type="text" class="form-control input-sm required code" id="DownMeta" value="${preferenceDefinition.metaMap['name']}"/>
					 
					</div>
					<input type="button" id="choose" class="code btn btn-default" value="选择"/>	
				</div>
				<div class="form-group">
					<label for="sortIndex" class="col-md-2 control-label">排序号</label>
					<div class="col-md-4">
						<input id="sortIndex" type="text"name="sortIndex" class="form-control input-sm" value="${preferenceDefinition.sortIndex}">
						<span id="sortIndexTip"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">个性化 </label>
					<div class="col-md-3">
						<c:choose>
							<c:when test="${preferenceDefinition.overwritable}">
							<input type="radio" id="isYes" name="overwritable" value="true" checked/><label for="isYes">是</label>
							<input type="radio" id="isNo" name="overwritable" value="false" /><label for="isNo">否</label>
							</c:when>
							<c:otherwise>
							<input type="radio" id="Yes" name="overwritable" value="true"/><label for="Yes">是</label>
							<input type="radio" id="No" name="overwritable" value="false"  checked/><label for="No">否</label>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="form-group">
					<label for="descript" class="col-md-2 control-label">备注</label>
					<div class="col-md-4">
						<textarea name="descript" id="descript" class="form-control input-sm">${preferenceDefinition.descript }</textarea>
					</div>
				</div>
				
				<!-- footer -->
				<div class="panel-footer">
					<div class=" col-md-offset-2">
						<input class="btn btn-default" type="submit"
							value="保存并新增" /> 
						<input class="btn btn-default" type="button"
							value="保存并关闭" onclick="saveAndClose()"/> 
						<input type="button" class="btn btn-default"
							value="关闭" onclick="closeChange()" />
					</div>
				</div>
			</div>
		</div>
	</form>
	
<script type="text/javascript">
$("#editorType").change(function() {
	if(this.value == "text"||this.value=="radio"||this.value=="checkbox") {
		  $("#dataTypes").show();
		  $("#dropDownTypes").show();
	  }else{
		  $("#dataTypes").hide();
		  $("#dropDownTypes").hide();
		  $("#dictCodes").hide();
	  }
	if(this.value=="radio"||this.value=="checkbox"){
		$("#Pchoose").hide();
		$("#dropDownType").val("dictionary");
	}else{
		$("#Pchoose").show();
		$("#dropDownType").val("");
	}

	if($("#dropDownType").val() == "dictionary") {
		  $("#dictCodes").show();
	 }else{
		  $("#dictCodes").hide();
	 }
});
$("#dropDownTypes").change(function() {
	  if($("#dropDownType").val() == "dictionary") {
		  $("#dictCodes").show();
	  }else{
		  $("#dictCodes").hide();
	  }
	});
<%--$("#choose").click(function() {
alert(111);
$.ajax({
	type : "post",
	url : "${ ctx }/admin/dicts/",
});
});--%>
$(document).ready(function(){
	jdp.selectSingleDict("code", function(data){
		var diction = new Object();
		diction.id = data.id;
		diction.name =data.name;
		diction.code =data.code;
		var json = JSON.stringify(diction); 
		//alert(json);
	$("#dropDownMeta").val(json);
	$("#DownMeta").val(data.name);
	});
});

</script>	
</body>
</html>