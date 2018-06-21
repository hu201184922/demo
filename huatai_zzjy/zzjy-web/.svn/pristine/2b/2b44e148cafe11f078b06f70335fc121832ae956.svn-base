<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html>
<head>
<title>Quartz管理</title>
<script type="text/javascript">
$(function(){
	var list = Ace.decode("${u:serialize(jobModels)}");
	$("[ref=submit]").click(function(){
		if(stringUtils.trim($("#name").val()) == ""){
			$.alert("任务名称不能为空!");
			return;
		}
		if(stringUtils.trim($("#triggerName").val()) == ""){
			$.alert("触发器名称不能为空!");
			return;
		}
		if(!validateJobNameExit($('#quartzJobForm'))){
			return;
		}
		if(!validateTriggerNameExit($('#quartzJobForm'))){
			return;
		}
		var flag = true;
		
		var type = $("[name=triggerType]:checked").val();
		if(type == '1'){
			if(stringUtils.trim($("#secondField").val()) == ""){
				$.alert("秒不能为空!");
				return;
			}
			if(stringUtils.trim($("#minutesField").val()) == ""){
				$.alert("分不能为空!");
				return;
			}
			if(stringUtils.trim($("#hourField").val()) == ""){
				$.alert("时不能为空!");
				return;
			}
			if(stringUtils.trim($("#dayField").val()) == ""){
				$.alert("日不能为空!");
				return;
			}
			if(stringUtils.trim($("#monthField").val()) == ""){
				$.alert("月不能为空!");
				return;
			}
			if(stringUtils.trim($("#weekField").val()) == "" && stringUtils.trim($("#yearField").val()) == ""){
				$.alert("周和年不能为同时为空!");
				return;
			}
			flag = validateCronExp($('#quartzJobForm'));
		}
		if(!flag){
			return;
		}
		$("#closequartzJobForm").click();
		$("#quartzJobForm").submit();
	});
	
	$('#quartzJobPager').pager(list, 10, $('#quartzJobView').view().on('add', function(data) {
		if(data.state == '停止' || data.state == '错误'){
			this.target.find("#resumeQuartzJob").show();
		}else if("正常运行"){
			this.target.find("#stopQuartzJob").show();
		}
		if(data.jobClassName == 'com.fairyland.jdp.framework.quartz.TaskJob'){
			this.target.find("#showPushTaskLog").show();
		}
	}));
	
	$("#createQuartzJob").click(function(){
		quartzJobDialog("add");
	});
	$("#repeatCount").each(function(){
		this.onblur = function(){
			validateNumOnkeyupAndOther(this,'-1');
		};
	});
	$("#repeatInterval").each(function(){
		this.onblur = function(){
			validateNumOnkeyup(this,1);
		};
	});
});

var actions = {'add':'新建Quartz任务'};
var quartzJobDialog = function(type){
	$('#quartzJobForm').data('type',type);
	$('#quartzJobForm').data('dialog', $.dialog( {
		title : actions[type],
		content : $('#quartzJobForm')[0]
	}));
	changeTriggerType(0);
};
function changeTriggerType(num){
	$("input[id^=triggerType_]").each(function(index,e){
		if(index!=num){
			e.checked = false;
		}else{
			e.checked = true;
		}
	});
	if(num == 1){
		$("#triggerType_1_content").show();
		$("#triggerType_0_content").hide();
	}else{
		$("#triggerType_0_content").show();
		$("#triggerType_1_content").hide();
	}
}
function deleteQuartzJob(jobKeyString,zhis){
	//console.debug(zhis);
	$.dialog(
		{
			title : "删除QuartzJob",
			content : $('#delete')[0]
		}
	);
	$("input","#delete").unbind('click');
	$("input","#delete").click(function(){
		$.post('${pageContext.request.contextPath}/admin/quartz/deleteQuartzJob',{jobKeyString:jobKeyString},function(bit){
			$('#diaClosedeleteQuartzJob').click();
			$(zhis).parents('tr').remove();
			$.msgbox({time: 2000,msg: "删除成功!",icon:"success"});
		});
	});
}
function stopQuartzJob(jobKeyString){
	$.ajax({
		type : "POST",
		url : "${pageContext.request.contextPath}/admin/quartz/stopQuartzJob",
		data : {jobKeyString : jobKeyString},
		success : function(msg) {
			window.location.reload(true);
			$.msgbox({time: 2000,msg: "终止成功!",icon:"success"});
		}
	});
}
function resumeQuartzJob(jobKeyString){
	$.ajax({
		type : "POST",
		url : "${pageContext.request.contextPath}/admin/quartz/resumeQuartzJob",
		data : {jobKeyString : jobKeyString},
		success : function(msg) {
			window.location.reload(true);
			$.msgbox({time: 2000,msg: "启动成功!",icon:"success"});
		}
	});
}
function validateNumOnkeyup(inputObj,defaultVal){
	var r = /^\+?[1-9][0-9]*$/;//正整数
	
	var num = inputObj.value.trim();
	if(!r.test(num)){
		inputObj.value=defaultVal;
	}else{
		inputObj.value = num;
	}
}
function validateNumOnkeyupAndOther(inputObj,defaultVal){
	var r1 = /^\+?[1-9][0-9]*$/;//正整数
	var r2 = /^(-1)$/;
	var num = inputObj.value.trim();
	if(!r1.test(num) || !r2.test(num)){
		inputObj.value=defaultVal;
	}else{
		inputObj.value = num;
	}
}
function validateCronExp(fromObj){
	var flag = true;
	$.ajax({
		type : "POST",
		async : false,
		url : "${pageContext.request.contextPath}/admin/quartz/validateCronExp",
		data : fromObj.serialize(),
		success : function(msg) {
			if(msg == "success"){
			}else{
				$.alert(msg);
				flag = false;
			}
		}
	});
	return flag;
}
function validateJobNameExit(fromObj){
	var flag = true;
	$.ajax({
		type : "POST",
		async : false,
		url : "${pageContext.request.contextPath}/admin/quartz/validateJobNameExit",
		data : fromObj.serialize(),
		success : function(msg) {
			flag = msg;
			if(!msg){
				$.alert("任务名称已存在");
			}
		}
	});
	return flag;
}
function validateTriggerNameExit(fromObj){
	var flag = true;
	$.ajax({
		type : "POST",
		async : false,
		url : "${pageContext.request.contextPath}/admin/quartz/validateTriggerNameExit",
		data : fromObj.serialize(),
		success : function(msg) {
			flag = msg;
			if(!msg){
				$.alert("触发器名称已存在");
			}
		}
	});
	return flag;
}
</script>
</head>
<body>
	<div class="tagContentList">
	
		<div class="business_title">Quartz管理</div>
		
		<div class="col_lg_04" style="width:1203px">
			<div class="business_search_list_warp" style="width:95%">
				<input type="button" id="createQuartzJob" value="新建Quartz任务">
				<table cellspacing="0" cellpadding="0" class="t-list table" id="quartzJobView">
					<tr>
						<th>任务名称</th>
						<th>触发器名</th>
						<th>上一次触发时间</th>
						<th>下一次触发时间</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
					<tr class="tb_tr_content template" name="default">
						<td>{name}</td>
						<td>{triggerName}</td>
						<td>{preFireTimeStr}</td>
						<td>{nextFireTimeStr}</td>
						<td>{state}</td>
						<td>
							<a ajax="{type:'dialog'}" href="${pageContext.request.contextPath }/admin/quartz/triggerEdit?jobKeyString={jobKeyString}">
								<img style="float: none;display: inline;" src="${pageContext.request.contextPath}/static/images/edit.png" title="编辑触发器"/>
							</a>
							<a href="javascript:void(0)" id="stopQuartzJob" style="display: none;" onclick="stopQuartzJob('{jobKeyString}')">终止</a>
							<a href="javascript:void(0)" id="resumeQuartzJob" style="display: none;" onclick="resumeQuartzJob('{jobKeyString}')">启动</a>
							<a href="javascript:void(0)" id="deleteQuartzJob" onclick="deleteQuartzJob('{jobKeyString}',this)">删除</a>
							<a ajax="{type:'dialog'}" id="showPushTaskLog" href="${pageContext.request.contextPath }/admin/quartz/getTaskJobLog?jobKeyString={jobKeyString}" style="display: none;">
								显示Log
							</a>
						</td>
					</tr>
				</table>
				<div id="quartzJobPager"></div>
			</div>
		</div>
		
	</div>
	<form id="quartzJobForm" style="display:none;" action="${pageContext.request.contextPath}/admin/quartz/createQuartzJob" method="post" >
		<div id="tagContent2" class="tagContent tagContent_div" style="display:block;">
            <table style="line-height:30px">
			   <tbody>
			   		<tr>
			   			<td class="text_right">任务名称: </td>
			   			<td class="text_left"><input type="text" id="name" name="name" class="text_wd200"/></td>
			   		</tr>
			   		<tr>
			   			<td class="text_right">JobClass: </td>
			   			<td class="text_left">
			   				<select name="jobClassName" style="width: 130px">
			   					<c:forEach items="${jobClassMap}" var="jobClass">
				   					<option value="${jobClass.key }">${jobClass.value }</option>
			   					</c:forEach>
			   				</select>
			   			</td>
			   		</tr>
			   		<tr>
			   			<td class="text_right">触发器名称: </td>
			   			<td class="text_left"><input type="text" id="triggerName" name="triggerName" class="text_wd200"/></td>
			   		</tr>
			   		<tr>
			   			<td class="text_right">触发器类型：</td><td>
			   			<input type="checkbox" id="triggerType_0" value="0" name="triggerType" onclick="changeTriggerType(0)" checked="checked"/><label>SimpleTrigger</label>
			   			<input type="checkbox" id="triggerType_1" value="1" name="triggerType" onclick="changeTriggerType(1)"/><label>CronTrigger</label>
			   			</td>
			   		</tr>
			   	</tbody>
			 </table>
		</div>
		<div id="triggerType_0_content" class="tagContent tagContent_div" style="display:block;">
			 <table style="line-height:30px">
			 	<tbody>
			   		<tr>
			   			<td class="text_right">重复次数: </td>
			   			<td class="text_left"><input type="text" id="repeatCount" name="repeatCount" class="text_wd200" value="-1"/>
			   			<font style="color: blue;"> *值为-1表示不设置执行次数</font>
			   			</td>
			   		</tr>
			   		<tr>
			   			<td class="text_right">间隔时间: </td>
			   			<td class="text_left"><input type="text" id="repeatInterval" name="repeatInterval" class="text_wd200" value="1"/>
			   			<font style="color: blue;">&nbsp;秒</font>
			   			</td>
			   		</tr>
			   	</tbody>
			  </table>
		</div>
		<div id="triggerType_1_content" class="tagContent tagContent_div">
			<table style="line-height:30px">
				<tbody>
					<tr>
						<td>秒：</td>
						<td><input type="text" id="secondField" name="secondField" value="0" /> <font style="color: blue;">*允许输入字符：0-59 , - * /</font></td>
					</tr>
					<tr>
						<td>分：</td>
						<td><input type="text" id="minutesField" name="minutesField" value="0" /> <font style="color: blue;">*允许输入字符：0-59 , - * /</font></td>
					</tr>
					<tr>
						<td>时：</td>
						<td><input type="text" id="hourField" name="hourField" value="0" /> <font style="color: blue;">*允许输入字符： 0-23 , - * /</font></td>
					</tr>
					<tr>
						<td>日：</td>
						<td><input type="text" id="dayField" name="dayField" value="*" /> <font style="color: blue;">*允许输入字符： 1-31 , - * ? /</font></td>
					</tr>
					<tr>
						<td>月：</td>
						<td><input type="text" id="monthField" name="monthField" value="*" /> <font style="color: blue;">*允许输入字符： 1-12 or JAN-DEC , - * ? /</font></td>
					</tr>
					<tr>
						<td>周：</td>
						<td><input type="text" id="weekField" name="weekField" class="text" value="?" /> <font style="color: blue;">*允许输入字符： 1-7 or SUN-SAT , - * ? /</font></td>
					</tr>
					<tr>
						<td>年：</td>
						<td><input type="text" id="yearField" name="yearField" class="text" value="*" /> <font style="color: blue;">*允许输入字符： empty, 1970-2099 , - * ? /</font></td>
					</tr>
			   </tbody>
			</table>
	    </div>
		<div>
			<input type="button" ref="submit" value="提 交" class="search_btn">&nbsp;&nbsp;<input type="button" value="取 消" id="closequartzJobForm" class="search_btn close">
		</div>
	</form>
	<div id="delete" style='display: none;'>
	<a id='diaClosedeleteQuartzJob' class='close' style='display: none;'></a>
	<span>确认删除此Quartz任务？</span>
	<br/><br/>
	<div class='clear'></div>
	<input type='button'  value='确认'/>
	</div>
</body>
</html>