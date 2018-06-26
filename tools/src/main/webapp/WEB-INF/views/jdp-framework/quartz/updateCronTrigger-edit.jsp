<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!-- <meta name="decorator" content="admin-popup"> -->

<html>
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
	
	function openEditExp(){
		$("#generateExp").show();
	}
	
	function cancel(){
		$("#generateExp").hide();
	}
	
	function generateExp(){
		var cronExp;
		
		var secondField = $("#secondField").val();
		var secondReg = /^([*]{1}|[0-5]{0,1}[0-9]{1}|[0-5]{0,1}[0-9]{1}([,]{1}[0-5]{0,1}[0-9]{1})+|[0-5]{0,1}[0-9]{1}[-\/]{1}[0-5]{0,1}[0-9]{1})$/;	
		var secondFlag = secondReg.test(secondField);
		if(!secondFlag){
			alert("秒格式不正确");
			return;
		}
		
		var minutesField = $("#minutesField").val();
		//分的正则表达式与秒相同
		var minutesFlag = secondReg.test(minutesField);
		if(!minutesFlag){
			alert("分格式不正确");
			return;
		}
		
		var hourField = $("#hourField").val();
		var hourReg = /^([*]{1}|[0-1]{0,1}[0-9]{1}|[2]{1}[0-3]{1}|([0-1]{0,1}[0-9]{1}|[2]{1}[0-3]{1})([,]{1}[0-1]{0,1}[0-9]{1}|[2]{1}[0-3]{1})+|([0-1]{0,1}[0-9]{1}|[2]{1}[0-3]{1})[-\/]([0-1]{0,1}[0-9]{1}|[2]{1}[0-3]{1}))$/;
		var hourFlag = hourReg.test(hourField);
		if(!hourFlag){
			alert("时格式不正确");
			return;
		}
		
		var dayField = $("#dayField").val();
		var dayReg = /^([*?L]{1}|[0-2]{0,1}[0-9]{1}|[3]{1}[0-1]{1}|([0-2]{0,1}[0-9]{1}|[3]{1}[0-1]{1})([,]{1}[0-2]{0,1}[0-9]{1}|[3]{1}[0-1]{1})+|([0-2]{0,1}[0-9]{1}|[3]{1}[0-1]{1})[-\/]([0-2]{0,1}[0-9]{1}|[3]{1}[0-1]{1})|([0-2]{0,1}[0-9]{1}|[3]{1}[0-1]{1})[W])$/;
		var dayFalg = dayReg.test(dayField);
		if(!dayFalg){
			alert("日格式不正确");
			return;
		}
		
		var monthField = $("#monthField").val();
		var monthReg = /^([*?]{1}|[0]{0,1}[1-9]{1}|[1]{0-2}|JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC|([0]{0,1}[1-9]{1}|[1]{0-2}|JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)([,][0]{0,1}[1-9]{1}|[1]{0-2}|JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)+|([0]{0,1}[1-9]{1}|[1]{0-2}|JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)[-\/]([0]{0,1}[1-9]{1}|[1]{0-2}|JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC))$/;
		var monthFlag = monthReg.test(monthField);
		if(!monthFlag){
			alert("月格式不正确");
			return;
		}
		
		var weekField = $("#weekField").val();
		var weekReg = /^([*?]{1}|[1-7]|SUN|MON|TUE|WED|THU|FRI|SAT|([1-7]|SUN|MON|TUE|WED|THU|FRI|SAT)([,]([1-7]|SUN|MON|TUE|WED|THU|FRI|SAT))+|([1-7]|SUN|MON|TUE|WED|THU|FRI|SAT)[-\/]([1-7]|SUN|MON|TUE|WED|THU|FRI|SAT))$/;
		var weekFlag = weekReg.test(weekField);
		if(!weekFlag){
			alert("周格式不正确");
			return;
		}
		
		var yearField = $("#yearField").val();
		var yearReg = /^([*?]{1}|19[7-9][0-9]|20[0-9]{2}|(19[7-9][0-9]|20[0-9]{2})([,]19[7-9][0-9]|20[0-9]{2})+|(19[7-9][0-9]|20[0-9]{2})[-\/](19[7-9][0-9]|20[0-9]{2}))$/;
		var yearFlag = yearReg.test(yearField);
		if(!yearFlag){
			alert("年格式不正确");
			return;
		}
		
		cronExp = secondField + " " + minutesField + " " + hourField + " " + dayField + " " + monthField;
		if(weekField == ""){
			cronExp = cronExp + " " + yearField;
		}else if(yearField == ""){
			cronExp = cronExp + " " + weekField;
		}else{
			cronExp = cronExp + " " + weekField + " " + yearField;
		}
		
		$("#cronExp").val(cronExp);
		$("#generateExp").hide();
	}
	
$(document).ready(function(){
		
		jQuery.validator.addMethod("isCronExp", function(value, element) {
			regex=/^(([*]{1}|[0-5]{0,1}[0-9]{1}|[0-5]{0,1}[0-9]{1}([,]{1}[0-5]{0,1}[0-9]{1})+|[0-5]{0,1}[0-9]{1}[-\/]{1}[0-5]{0,1}[0-9]{1})\s([*]{1}|[0-5]{0,1}[0-9]{1}|[0-5]{0,1}[0-9]{1}([,]{1}[0-5]{0,1}[0-9]{1})+|[0-5]{0,1}[0-9]{1}[-\/]{1}[0-5]{0,1}[0-9]{1})\s([*]{1}|[0-1]{0,1}[0-9]{1}|[2]{1}[0-3]{1}|([0-1]{0,1}[0-9]{1}|[2]{1}[0-3]{1})([,]{1}[0-1]{0,1}[0-9]{1}|[2]{1}[0-3]{1})+|([0-1]{0,1}[0-9]{1}|[2]{1}[0-3]{1})[-\/]([0-1]{0,1}[0-9]{1}|[2]{1}[0-3]{1}))\s([*?L]{1}|[0-2]{0,1}[0-9]{1}|[3]{1}[0-1]{1}|([0-2]{0,1}[0-9]{1}|[3]{1}[0-1]{1})([,]{1}[0-2]{0,1}[0-9]{1}|[3]{1}[0-1]{1})+|([0-2]{0,1}[0-9]{1}|[3]{1}[0-1]{1})[-\/]([0-2]{0,1}[0-9]{1}|[3]{1}[0-1]{1})|([0-2]{0,1}[0-9]{1}|[3]{1}[0-1]{1})[W])\s([*?]{1}|[0]{0,1}[1-9]{1}|[1]{0-2}|JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC|([0]{0,1}[1-9]{1}|[1]{0-2}|JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)([,][0]{0,1}[1-9]{1}|[1]{0-2}|JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)+|([0]{0,1}[1-9]{1}|[1]{0-2}|JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)[-\/]([0]{0,1}[1-9]{1}|[1]{0-2}|JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC))\s((([*?]{1}|[1-7]|SUN|MON|TUE|WED|THU|FRI|SAT|([1-7]|SUN|MON|TUE|WED|THU|FRI|SAT)([,]([1-7]|SUN|MON|TUE|WED|THU|FRI|SAT))+|([1-7]|SUN|MON|TUE|WED|THU|FRI|SAT)[-\/]([1-7]|SUN|MON|TUE|WED|THU|FRI|SAT))\s([*?]{1}|19[7-9][0-9]|20[0-9]{2}|(19[7-9][0-9]|20[0-9]{2})([,]19[7-9][0-9]|20[0-9]{2})+|(19[7-9][0-9]|20[0-9]{2})[-\/](19[7-9][0-9]|20[0-9]{2})))|(([*?]{1}|[1-7]|SUN|MON|TUE|WED|THU|FRI|SAT|([1-7]|SUN|MON|TUE|WED|THU|FRI|SAT)([,]([1-7]|SUN|MON|TUE|WED|THU|FRI|SAT))+|([1-7]|SUN|MON|TUE|WED|THU|FRI|SAT)[-\/]([1-7]|SUN|MON|TUE|WED|THU|FRI|SAT))|([*?]{1}|19[7-9][0-9]|20[0-9]{2}|(19[7-9][0-9]|20[0-9]{2})([,]19[7-9][0-9]|20[0-9]{2})+|(19[7-9][0-9]|20[0-9]{2})[-\/](19[7-9][0-9]|20[0-9]{2})))))$/;
			return this.optional(element) || (regex.test(value));
		}, "请书写正确的CronExpress");
		
		jQuery.validator.addMethod("isJobClassName", function(value, element) {
			if(value == "请选择"){
				return false;
			}
			return true;
		}, "请选择Job！！！");
		
		$("#cronTriggerForm").validate({
			rules : {
				startTime : {
					required : true,
				},
// 				endTime : {
// 					required : true,
// 				},
				cronExp : {
					required : true,
					isCronExp : true
				},
				nextFireTime : {
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

<form id="cronTriggerForm" class="form-horizontal" target="_parent"  action="${ctx}/admin/qrtz/trigger/saveCron" method="post">

<div class="panel panel-info">
	<div class="panel-heading">Trigger编辑</div>
	<input type="hidden" name="jobIdString" value="${jobIdString }"/>
	<div class="panel-body">
		<div class="form-group">
			<label for="key.name" class="col-md-2 control-label">名称：</label>
				<div class="col-sm-3">
					<input type="input" readonly="readonly"  class="form-control input-sm required" name="key.name" value="${trigger.key.name}"/>
				</div>
			<label for="key.group" class="col-md-2 control-label">所属组：</label>
				<div class="col-sm-3">
					<input type="input" readonly="readonly"  class="form-control input-sm required" name="key.group" value="${trigger.key.group}"/>
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
			<label for="cronExp" class="col-md-2 control-label">CronExpress：</label>
				<div class="col-sm-3">
					<input type="text" class="form-control input-sm required" id="cronExp" name="cronExp" value="${trigger.cronExpression}"/>
				</div>
				<div class="col-md-2 ">
					<input type="button" class="btn btn-default" onclick="openEditExp()" value="CronExp表达式帮助"/>
				</div>		
		</div>
		
		<div id="generateExp" class="from-group" style="display: none;">
			<table id="cronTriggerTable" align="center">
				<tr>
					<td style="color: blue;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;输入时注意不要有空格！！！</td>
				</tr>
				<tr>
					<td>秒：<input type="text" id="secondField"
					name="secondField" value="0" /> &nbsp;&nbsp;允许输入字符：0-59 , - * /</td>
				</tr>
				<tr>
					<td>分：<input type="text" id="minutesField"
					name="minutesField" value="0" /> &nbsp;&nbsp;允许输入字符：0-59 , - * /</td>
				</tr>
				<tr>
					<td>时：<input type="text" id="hourField"
					name="hourField" value="0" /> &nbsp;&nbsp;允许输入字符： 0-23 , - * /</td>
				</tr>
				<tr>
					<td>日：<input type="text" id="dayField"
					name="dayField" value="*" /> &nbsp;&nbsp;允许输入字符： 1-31 , - * ? /</td>
				</tr>
				<tr>
					<td>月：<input type="text" id="monthField"
					name="monthField" value="*" /> &nbsp;&nbsp;允许输入字符： 1-12 or JAN-DEC , - * ? /</td>
				</tr>
				<tr>
					<td>周：<input type="text" id="weekField" name="weekField"
					class="text" value="?" /> &nbsp;&nbsp;允许输入字符： 1-7 or SUN-SAT , - * ? /</td>
				</tr>
				<tr>
					<td>年：<input type="text" id="yearField" name="yearField"
					class="text" value="*" /> &nbsp;&nbsp;允许输入字符： empty, 1970-2099 , - * ? /</td>
				</tr>
				<tr>
					<td align="center">
						<input type="button" onclick="generateExp()" value="生成CronExp" class="btn btn-default" />&nbsp;&nbsp;
						<input type="button" onclick="cancel()" value="取消" class="btn btn-default" />
					</td>
				</tr>
			</table>
	</div>
	
	<div class="panel-footer">
		<div class=" col-md-offset-2">
			<input id="submit_btn" class="btn btn-default" type="submit" value="保存" />
			<input id="cancel_btn" type="button" class="btn btn-default" value="关闭" onclick="closeChange()" />
		</div>
	</div>
	
</div>	

</form>

</body>

</html>