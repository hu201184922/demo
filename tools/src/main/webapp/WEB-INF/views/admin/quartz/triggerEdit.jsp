<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/ace.tld" prefix="ace"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html decorator="null">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑触发器</title>
</head>
<script type="text/javascript">
	var triggerType = '${triggerType}';
	var repeatCount = -1;
	var repeatInterval = 1;
	<c:if test="${triggerType == 0}">
		repeatCount = '${triggerModel.repeatCount}';
		repeatInterval = '${triggerModel.repeatInterval}';
	</c:if>
	$(function() {
		$("[ref=submit]").click(function(){
			if(stringUtils.trim($("#triggerName").val()) == ""){
				$.alert("触发器名称不能为空!");
				return;
			}
			if(!validateTriggerNameExit($('#triggerForm'))){
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
				if(stringUtils.trim($("#weekField").val()) == "" ){
					$.alert("周不能为同时为空!");
					return;
				}
				flag = validateCronExp($('#triggerForm'));
			}
			if(!flag){
				return;
			}
			$("#triggerForm").submit();
		});
		$("#repeatCount").each(function(){
			this.onblur = function(){
				validateNumOnkeyupAndOther(this,repeatCount);
			};
		});
		$("#repeatInterval").each(function(){
			this.onblur = function(){
				validateNumOnkeyup(this,repeatInterval);
			};
		});
		changeTriggerType(triggerType);
	});
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
	function validateTriggerNameExit(fromObj){
		var flag = true;
		$.ajax({
			type : "POST",
			async : false,
			url : "${pageContext.request.contextPath}/admin/quartz/validateTriggerNameExit?initTriggerName=${triggerModel.triggerName}",
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
<body>
	<a id="diaClose" class="close" style="display: none;"></a>
	<form id="triggerForm" action="${pageContext.request.contextPath}/admin/quartz/updateTrigger" method="post">
		<input type="hidden" name="schedName" value="${triggerModel.schedName }"/>
		<input type="hidden" name="triggerGroup" value="${triggerModel.triggerGroup }"/>
		<input type="hidden" name="oldTriggerName" value="${triggerModel.triggerName }"/>
		<div id="tagContent2" class="tagContent tagContent_div" style="display:block;">
            <table style="line-height:30px">
			    <tbody>
			    <tr>
				    <td class="text_right">触发器名称：</td>
					<td class="text_left"><input type="text" id="triggerName" name="triggerName" value="${triggerModel.triggerName}" class="text_wd200"/></td>
				</tr>
				<tr>
		   			<td class="text_right">触发器类型：</td><td>
		   			<input type="checkbox" id="triggerType_0" value="0" name="triggerType" onclick="changeTriggerType(0)" <c:if test='${triggerType == 0 }'>checked="checked"</c:if> /><label>SimpleTrigger</label>
		   			<input type="checkbox" id="triggerType_1" value="1" name="triggerType" onclick="changeTriggerType(1)" <c:if test='${triggerType == 1 }'>checked="checked"</c:if> /><label>CronTrigger</label>
		   			</td>
		   		</tr>
				</tbody>
			</table>
		</div>
		<div id="triggerType_0_content" class="tagContent tagContent_div" style="display:block;">
			 <table style="line-height:30px">
			 	<tbody>
				<tr>
				    <td class="text_right">重复次数：</td>
					<td class="text_left">
						<input type="text" id="repeatCount" name="repeatCount" <c:if test='${triggerType == 0 }'>value="${triggerModel.repeatCount}"</c:if> class="text_wd200"/>
						<font style="color: green;"> *值为-1表示不设置执行次数</font>
					</td>
				</tr>
				<tr>
				    <td class="text_right">间隔时间：</td>
					<td class="text_left">
						<input type="text" id="repeatInterval" name="repeatInterval" <c:if test='${triggerType == 0 }'>value="${triggerModel.repeatInterval}"</c:if> class="text_wd200"/>
						<font style="color: green;">&nbsp;秒</font>
					</td>
				</tr>
			</table>
		</div>
		<div id="triggerType_1_content" class="tagContent tagContent_div">
			<table style="line-height:30px">
				<tbody>
				<tr>
					<td></td>
					<td><font style="color: black;">请输入新的CronTrigger表达式：</font></td>
				</tr>
				<tr>
						<td>秒：</td>
						<td><input type="text" id="secondField" name="secondField" <c:if test='${triggerType != 1 }'>value="0"</c:if><c:if test='${triggerType == 1 }'>value="${triggerModel.secondField }"</c:if> /> <font style="color: blue;">*允许输入字符：0-59 , - * /</font></td>
					</tr>
					<tr>
						<td>分：</td>
						<td><input type="text" id="minutesField" name="minutesField" <c:if test='${triggerType != 1 }'>value="0"</c:if><c:if test='${triggerType == 1 }'>value="${triggerModel.minutesField }"</c:if> /> <font style="color: blue;">*允许输入字符：0-59 , - * /</font></td>
					</tr>
					<tr>
						<td>时：</td>
						<td><input type="text" id="hourField" name="hourField" <c:if test='${triggerType != 1 }'>value="0"</c:if><c:if test='${triggerType == 1 }'>value="${triggerModel.hourField }"</c:if> /> <font style="color: blue;">*允许输入字符： 0-23 , - * /</font></td>
					</tr>
					<tr>
						<td>日：</td>
						<td><input type="text" id="dayField" name="dayField" <c:if test='${triggerType != 1 }'>value="*"</c:if><c:if test='${triggerType == 1 }'>value="${triggerModel.dayField }"</c:if> /> <font style="color: blue;">*允许输入字符： 1-31 , - * ? /</font></td>
					</tr>
					<tr>
						<td>月：</td>
						<td><input type="text" id="monthField" name="monthField" <c:if test='${triggerType != 1 }'>value="*"</c:if><c:if test='${triggerType == 1 }'>value="${triggerModel.monthField }"</c:if> /> <font style="color: blue;">*允许输入字符： 1-12 or JAN-DEC , - * ? /</font></td>
					</tr>
					<tr>
						<td>周：</td>
						<td><input type="text" id="weekField" name="weekField" class="text" <c:if test='${triggerType != 1 }'>value="?"</c:if><c:if test='${triggerType == 1 }'>value="${triggerModel.weekField }"</c:if> /> <font style="color: blue;">*允许输入字符： 1-7 or SUN-SAT , - * ? /</font></td>
					</tr>
					<tr>
						<td>年：</td>
						<td><input type="text" id="yearField" name="yearField" class="text" <c:if test='${triggerType != 1 }'>value="*"</c:if><c:if test='${triggerType == 1 }'>value="${triggerModel.yearField }"</c:if> /> <font style="color: blue;">*允许输入字符： empty, 1970-2099 , - * ? /</font></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div>
			<input type="button" ref="submit" value="提 交" class="search_btn">&nbsp;&nbsp;<input type="button" value="取 消" class="search_btn close">
		</div>
	</form>
</body>
</html>