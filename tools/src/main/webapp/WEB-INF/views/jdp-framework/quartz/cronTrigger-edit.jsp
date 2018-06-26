<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!-- <meta name="decorator" content="admin-popup"> -->

<html>
<script type="text/javascript" src="check.js"></script>
<head>
<style>
.formCheck{vertical-align:middle;margin:-4px 2px 0px 2px;}
</style>
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
				name : {
					required : true,
					remote:{
						url:"${ctx}/admin/qrtz/cron/checkName",
						type:"post",
						datatype:"json",
						data:{
							name:function(){
								return $("#inputName").val();
							},
							initialName:"${cronTrigger.name}"
						}
					}
				},
				code : {
					required : true,
					remote:{
						url:"${ctx}/admin/qrtz/cron/checkCode",
						type:"post",
						datatype:"json",
						data:{
							name:function(){
								return $("#inputCode").val();
							},
							initialCode:"${cronTrigger.code}"
						}
					}
				},
				jobClassName : {
					required : true,
					isJobClassName : true
				}
				/* ,
				cronExp : {
					required : true,
					isCronExp : true
				} */
			}
		});
		
	});
	
	function saveCronTrigger(){
		save();
		var validator = $("#cronTriggerForm").validate();
		if(validator.form()){
			$.ajax({
				type : "post",
				data : $("#cronTriggerForm").serialize(),
				url : "${ctx}/admin/qrtz/cron/save",
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

<form id="cronTriggerForm" class="form-horizontal"  action="${ctx}/admin/qrtz/cron/saveAndCreate" method="post">

<div class="panel panel-info">
	<div class="panel-heading">任务定义信息</div>
	<div class="panel-body">
	<div class="form-group">
		<label for="name" class="col-sm-2 col-md-2 col-lg-2 control-label">名称(Name)：</label>
			<div class="col-sm-4 col-md-4 col-lg-4">
				<input type="text" class="form-control input-sm required" name="name" value="${cronTrigger.name}"/>
			</div>
		<label for="code"  class="col-sm-2 col-md-2 col-lg-2 control-label">Code：</label>
			<div class="col-sm-4 col-md-4 col-lg-4">
				<input type="text" class="form-control input-sm required" id="inputCode" name="code" value="${cronTrigger.code}"/>
			</div>
	</div>
	<div class="form-group">
		<label for="jobClassName"  class="col-sm-2 col-md-2 col-lg-2 control-label">Job：</label>
			<div class="col-sm-4 col-md-4 col-lg-4">
				<select name="jobClassName" class="form-control input-sm required">
					<option>请选择</option>
					<c:forEach items="${jobs }" var="job">
						<option value="${job.value }" <c:if test="${cronTrigger.jobClassName == job.value}">selected="selected"</c:if>>${job.key }</option>
					</c:forEach>
				</select>
			</div>
		<label for="jobDataMap"  class="col-sm-2 col-md-2 col-lg-2 control-label">任务参数：</label>
			<input type="hidden" name="jobDataMap" id="jobDataMap" value="${cronTrigger.jobDataMap}"/>
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
				<c:forEach items="${cronTrigger.jobDataMetaMap }" var="jobDataMeta">
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
	<div class="form-group">
		<label for="name" class="col-sm-2 col-md-2 col-lg-2 control-label">关联表：</label>
		<div class="col-sm-4 col-md-4 col-lg-4">
			<c:forEach items="${inters}" var="inter">
				<input type="checkbox" name="tableCode" value="${inter.interName}" <c:if test="${inter.checked == true}">checked</c:if>/>&nbsp;${inter.interName}</br>
			</c:forEach>
		</div>
	</div>
	</div>
	
	<div class="panel-heading">CronTrigger编辑</div>
	<div class="panel-body">
		<input type="hidden" name="id" value="${cronTrigger.id}"/>
		<input type="hidden" name="qrtzGroup.id" value="${cronTrigger.qrtzGroup.id }"/>
		
		<div class="form-group">
			<label for="startTime" class="col-sm-2 col-md-2 col-lg-2 control-label">开始时间(S)：</label>
					<div class="col-sm-4 col-md-4 col-lg-4">				
						<input  data-format="yyyy-MM-dd HH:mm:ss" readonly="readonly" type="text" class="form-control search-query form_Date_time" id="startTime"  name="startTime" value="<fmt:formatDate value="${cronTrigger.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"> 
						<span class="add-on">
     						<i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
                         </span>
					</div>
				<label for="endJobTime" class="col-sm-2 col-md-2 col-lg-2 control-label">结束时间(N)：</label>
					<div class="col-sm-4 col-md-4 col-lg-4">				
						<input  data-format="yyyy-MM-dd HH:mm:ss" readonly="readonly" type="text" class="form-control search-query form_Date_time" id="endTime"  name="endTime" value="<fmt:formatDate value="${cronTrigger.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"> 
						<span class="add-on">
     						<i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
                        </span>		
					</div>
			
		</div>
		
		<div class="form-group">
			<label for="cronExp" class="col-sm-2 col-md-2 col-lg-2 control-label">CronExpress：</label>
				<div class="col-sm-4 col-md-4 col-lg-4">
					<input type="text" class="form-control input-sm required" id="cronExp" name="cronExp" value="${cronTrigger.cronExp}"/>
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
			<br/>
			<br/>
		</div>
		
		<div class="form-group">
			<label for="description" class="col-sm-2 col-md-2 col-lg-2 control-label">描述：</label>
			<div class="col-sm-6">
				<textarea name="description" class="form-control" rows="3">${cronTrigger.description }</textarea>
			</div>
		</div>
	
	</div>
	
	<div class="panel-footer">
		<div class=" col-md-offset-2">
			<!-- <input id="submit_btn" class="btn btn-default" type="submit" onclick="save()" value="保存并新增" /> -->
			<input id="submit_btn" class="btn btn-default" type="button" value="保存并关闭" onclick="saveCronTrigger()" /> 
			<input id="cancel_btn" type="button" class="btn btn-default" value="关闭" onclick="closeChange()" />
		</div>
	</div>
	
</div>	

</form>

</body>

</html>