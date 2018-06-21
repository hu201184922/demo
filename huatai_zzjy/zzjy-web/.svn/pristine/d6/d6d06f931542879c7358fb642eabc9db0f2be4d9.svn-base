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
	function closeChange(){
		parent.$.fancybox.close();
	}
	
	$(document).ready(function(){
		$(".form_Date_time").datetimepicker({
			 format: "yyyy-mm-dd hh:ii:ss",
			 startView : "year",
			 autoclose: true,
			 todayBtn: true,
			 pickerPosition: "bottom-left"
		});
	});
	
$(document).ready(function(){
		
		$("#simpleTriggerForm").validate({
			rules : {
				startTime : {
					required : true,
				},
// 				endTime : {
// 					required : true,
// 				},
				nextFireTime : {
					required : true,
				},
				repeatInterval : {
					required : true,
				},
				repeatCount : {
					required : true,
				}
			}
		});
		
	});
	
	

</script>

</head>

<body>

<!-- 信息提示 -->
<c:if test="${not empty message}">
		<div id="message" class="alert alert-success">
			<button data-dismiss="alert" class="close">×</button>${message}</div>
</c:if>

<form id="simpleTriggerForm" class="form-horizontal" target="_parent"  action="${ctx}/admin/qrtz/trigger/saveSimple" method="post">

<div class="panel panel-info">
	<div class="panel-heading">Trigger编辑</div>
	<input type="hidden" name="jobIdString" value="${jobIdString }"/>
	<div class="panel-body">
		<div class="form-group">
			<label for="key.name" class="col-md-2 control-label">名称：</label>
				<div class="col-sm-3">
					<input type="input" readonly="readonly" class="form-control input-sm required" name="key.name" value="${trigger.key.name}"/>
				</div>
			<label for="key.group" readonly="readonly" class="col-md-2 control-label">所属组：</label>
				<div class="col-sm-3">
					<input type="input" class="form-control input-sm required" name="key.group" value="${trigger.key.group}"/>
				</div>
		</div>	
		
		<div class="form-group">
			<label for="startTime" class="col-md-2 control-label">开始时间(S)：</label>
					<div class="col-sm-3">				
						<input  data-format="yyyy-MM-dd HH:mm:ss" readonly="readonly" type="text" class="form-control required search-query form_Date_time" id="startTime"  name="startTime" value="<fmt:formatDate value="${trigger.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"> 
						<span class="add-on">
     						<i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
                         </span>
					</div>
				<label for="endTime" class="col-md-2 control-label">结束时间(N)：</label>
					<div class="col-sm-3">				
						<input  data-format="yyyy-MM-dd HH:mm:ss" readonly="readonly" type="text" class="form-control search-query form_Date_time" id="endTime"  name="endTime" value="<fmt:formatDate value="${trigger.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"> 
						<span class="add-on">
     						<i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
                        </span>		
					</div>
		</div>
		
		<div class="form-group">
			<label for="nextFireTime" class="col-md-2 control-label">下一次执行时间(N)：</label>
					<div class="col-sm-3">				
						<input  data-format="yyyy-MM-dd HH:mm:ss" readonly="readonly" type="text" class="form-control required search-query form_Date_time" id="nextFireTime"  name="nextFireTime" value="<fmt:formatDate value="${trigger.nextFireTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"> 
						<span class="add-on">
     						<i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
                         </span>
					</div>
			<label for="priority" class="col-md-2 control-label">优先级：</label>
				<div class="col-sm-3">
					<input type="input" class="form-control input-sm required" name="priority" value="${trigger.priority}"/>
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
	</div>
	
	<div class="panel-footer">
		<div class=" col-md-offset-2">
			<input id="submit_btn" class="btn btn-default" type="submit"  value="保存" />
			<input id="cancel_btn" type="button" class="btn btn-default" value="关闭" onclick="closeChange()" />
		</div>
	</div>
	
</div>	

</form>

</body>

</html>