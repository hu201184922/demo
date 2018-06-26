<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!-- <meta name="decorator" content="admin-popup"> -->

<html>
<script type="text/javascript" src="check.js"></script>
<head>
<title>CronTrigger编辑</title>
<script type="text/javascript">
	$(document).ready(function(){
		
		$(".form_Date_time").datetimepicker({
			 format: "yyyy-mm-dd hh:ii:ss",
			 startView : "year",
			 autoclose: true,
			 todayBtn: true,
			 pickerPosition: "bottom-left"
		});
		
		
	});

	function add_line() {
		var jobDataMapKey = $('#jobDataMapKey').val();
		var jobDataMapValue = $('#jobDataMapValue').val();
		if(jobDataMapKey == ""){
			alert("请填写新增的参数名！！！");
			return;
		}
		if(jobDataMapValue == ""){
			alert("请填写新增的参数值！！！");
			return;
		}
		$('#jobDataMapTable').after(
				"<tr><td>参数名：</td><td><input type='text' name='jobDataMapKey' value='"+jobDataMapKey+"' style='width: 60px' disabled='disabled'/></td>"
					+"<td>参数值：</td><td><input type='text' name='jobDataMapValue' value='"+jobDataMapValue+"' style='width: 60px' disabled='disabled'/</td>"
					+"<td><a onclick='remove_line(this)' href='javascript:void(0)'>&nbsp;&nbsp;删除</a></td></tr>"
			);
		$('#jobDataMapKey').val("");
		$('#jobDataMapValue').val("");
	}
	
	function remove_line(obj) {
		$(obj).parent().parent().remove();
	}
	
	function closeChange() {
		parent.$.fancybox.close();
	};
	
	function save(){
		var jobData = {};
		var jobDataMapKeys = document.getElementsByName("jobDataMapKey");
		var jobDataMapValues = document.getElementsByName("jobDataMapValue");
		var size = jobDataMapKeys.length;
		for(var i=0; i<size; i++){
			var jobDataMapKey = jobDataMapKeys[i].value;
			var jobDataMapValue = jobDataMapValues[i].value;
			if(jobDataMapKey != "" && jobDataMapValue != ""){
				jobData[jobDataMapKey] = jobDataMapValue;
			}
		}
		var jobDataMapString = JSON.stringify(jobData);
		$("#jobDataMap").val(jobDataMapString);
	}
	
		
	$(document).ready(function(){
		
		jQuery.validator.addMethod("isJobClassName", function(value, element) {
			if(value == "请选择"){
				return false;
			}
			return true;
		}, "请选择Job！！！");
		
		$("#simpleTriggerForm").validate({
			rules : {
				name : {
					required : true,
					remote:{
						url:"${ctx}/admin/qrtz/simple/checkName",
						type:"post",
						datatype:"json",
						data:{
							name:function(){
								return $("#inputName").val();
							},
							initialName:"${simpleTrigger.name}"
						}
					}
				},
				code : {
					required : true,
					remote:{
						url:"${ctx}/admin/qrtz/simple/checkCode",
						type:"post",
						datatype:"json",
						data:{
							name:function(){
								return $("#inputCode").val();
							},
							initialCode:"${simpleTrigger.code}"
						}
					}
				},
				jobClassName : {
					required : true,
					isJobClassName : true
				},
				startTime : {
					required : true,
				},
				endTime : {
					required : true,
				},
				simpleExp : {
					required : true,
					isCronExp : true
				}
			}
		});
		
	});
	
	function saveSimpleTrigger(){
		save();
		var validator = $("#simpleTriggerForm").validate();
		if(validator.form()){
			$.ajax({
				type : "post",
				data : $("#simpleTriggerForm").serialize(),
				url : "${ctx}/admin/qrtz/simple/save",
				success : function(msg) {
					parent.$.fancybox.close();
				}
				
			});
		}
	}


</script>

</head>

<body>

<!-- 信息提示 -->
<c:if test="${not empty message}">
		<div id="message" class="alert alert-success">
			<button data-dismiss="alert" class="close">×</button>${message}</div>
</c:if>

<form id="simpleTriggerForm" class="form-horizontal"  action="${ctx}/admin/qrtz/simple/saveAndCreate" method="post">

<div class="panel panel-info">
	<div class="panel-heading" style="background-color: #EBEFF2;">任务定义信息</div>
	<div class="panel-body">
	<div class="form-group">
		<label for="name" class="col-md-2 control-label">名称(Name)：</label>
			<div class="col-sm-3">
				<input type="text" class="form-control input-sm required" name="name" value="${simpleTrigger.name}"/>
			</div>
		<label for="code"  class="col-md-2 control-label">Code：</label>
			<div class="col-sm-3">
				<input type="text" class="form-control input-sm required" id="inputCode" name="code" value="${simpleTrigger.code}"/>
			</div>
	</div>
	<div class="form-group">
		<label for="jobClassName"  class="col-md-2 control-label">Job：</label>
			<div class="col-sm-3">
				<select name="jobClassName" class="form-control input-sm required">
					<option>请选择</option>
					<c:forEach items="${jobs }" var="job">
						<option value="${job.value }" <c:if test="${simpleTrigger.jobClassName == job.value}">selected="selected"</c:if>>${job.key }</option>
					</c:forEach>
				</select>
			</div>
		<label for="jobDataMap"  class="col-md-2 control-label">任务参数：</label>
			<input type="hidden" name="jobDataMap" id="jobDataMap" value="${simpleTrigger.jobDataMap}"/>
			<table border=0 cellpadding="0" cellspacing="0" style="border: 0; margin: 0 0;">
				<tr id="jobDataMapTable">
					<td>参数名：</td>
					<td>
						<input id="jobDataMapKey" type="text" name="jobDataMapKey" style="width: 60px"/>&nbsp;&nbsp;
					</td>
					<td>参数值：</td>
					<td>
						<input id="jobDataMapValue" type="text" name="jobDataMapValue" style="width: 60px"/>
					</td>
					<td>
						&nbsp;&nbsp;<input type="button" onclick="add_line()" value="新增"/>
					</td>
				</tr>
				<c:forEach items="${simpleTrigger.jobDataMetaMap }" var="jobDataMeta">
					<tr>
						<td>参数名：</td>
						<td>
							<input type='text' name='jobDataMapKey' value="${jobDataMeta.key }" style='width: 60px' disabled='disabled'/>
						</td>
						<td>参数值：</td>
						<td>
							<input type='text' name='jobDataMapValue' value="${jobDataMeta.value }" style='width: 60px' disabled='disabled'/>
						</td>
						<td>
							<a onclick='remove_line(this)' href='javascript:void(0)'>&nbsp;&nbsp;删除</a>
						</td>
					</tr>
				</c:forEach>
			</table>
	</div>
	</div>
	
	<div class="panel-heading" style="background-color: #EBEFF2 !important;">SimpleTrigger编辑</div>
	<div class="panel-body">
		<input type="hidden" name="id" value="${simpleTrigger.id}"/>
		<input type="hidden" name="qrtzGroup.id" value="${simpleTrigger.qrtzGroup.id }"/>
		
		<div class="form-group">
			<label for="startTime" class="col-md-2 control-label">开始时间(S)：</label>
					<div class="col-sm-3">				
						<input  data-format="yyyy-MM-dd HH:mm:ss" readonly="readonly" type="text" class="form-control required search-query form_Date_time" id="startTime"  name="startTime" value="<fmt:formatDate value="${simpleTrigger.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"> 
						<span class="add-on">
     						<i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
                         </span>
					</div>
				<label for="endJobTime" class="col-md-2 control-label">结束时间(N)：</label>
					<div class="col-sm-3">				
						<input  data-format="yyyy-MM-dd HH:mm:ss" readonly="readonly" type="text" class="form-control required search-query form_Date_time" id="endTime"  name="endTime" value="<fmt:formatDate value="${simpleTrigger.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"> 
						<span class="add-on">
     						<i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
                        </span>		
					</div>
			
		</div>
		
		<div class="form-group">
			<label for="repeatInterval" class="col-md-2 control-label">重复间隔(毫秒)：</label>
				<div class="col-sm-3">
					<input name="repeatInterval" class="form-control" value="${simpleTrigger.repeatInterval }"/>
				</div>
			<label for="repeatCount" class="col-md-2 control-label">重复次数：</label>
				<div class="col-sm-3">
					<input name="repeatCount" class="form-control" value="${simpleTrigger.repeatCount }"/>
				</div>
		</div>
		
		<div class="form-group">
			<label for="description" class="col-md-2 control-label">描述：</label>
			<div class="col-sm-6">
				<textarea name="description" class="form-control" rows="3">${simpleTrigger.description }</textarea>
			</div>
		</div>
	
	</div>
	
	<div class="panel-footer">
		<div class=" col-md-offset-2">
			<input id="submit_btn" class="btn btn-default" type="submit" onclick="save()" value="保存并新增" />
			<input id="submit_btn" class="btn btn-default" type="button" value="保存并关闭" onclick="saveSimpleTrigger()" /> 
			<input id="cancel_btn" type="button" class="btn btn-default" value="关闭" onclick="closeChange()" />
		</div>
	</div>
	
</div>	

</form>

</body>

</html>