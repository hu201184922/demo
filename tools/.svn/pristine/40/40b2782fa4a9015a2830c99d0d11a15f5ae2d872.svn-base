<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!-- <meta name="decorator" content="admin-popup"> -->

<html>
<script type="text/javascript" src="check.js"></script>
<head>
<title>调度编辑</title>
<script type="text/javascript">
	$(document).ready(function(){
		
		//日期格式组件
		$(".form_datetime").datetimepicker({
			minView: "month",
			format : "yyyy-mm-dd",
			pickerPosition: "bottom-left",
			autoclose : true,
			todayBtn : true,
			pickTime: false
		});
		$(".form_time").datetimepicker({
			startView : "day",
			format : "hh:ii:ss",
			language: 'en',
	        pickDate: false,
	        pickTime: true,
	        hourStep: 1,
	        minuteStep: 1,
	        secondStep: 1,
	        autoclose : true,
	        inputMask: true
		});
		
		
		// 定期模式类型初始值 
		var obj = document.getElementsByName("intervalType");
		var type = "${qrtzDefinition.intervalType}";
		for(var i = 0; i <obj.length; i++){
			if(obj[i].value == type){
				obj[i].checked = 1;
			}	
		}
		
		//为定期模式中Radio、checkbox的选择初始化
		change(type);
		
		var endTypeObj = document.getElementsByName("endType");
		if("${qrtzDefinition.endType}" == "无结束日期"){
			endTypeObj[0].checked = 1;
		}else if("${qrtzDefinition.endType}" == "重复"){
			endTypeObj[1].checked = 1;
		}else if("${qrtzDefinition.endType}" == "结束日期"){
			endTypeObj[2].checked = 1;
		}else{
			//
		}
		
		if("天" == type){
			var dayTypeObj = document.getElementsByName("dayType");
			if("${qrtzDefinition.metaMap['dayType']}" == "dayType1"){
				dayTypeObj[0].checked = 1;
			}else if("${qrtzDefinition.metaMap['dayType']}" == "dayType2"){
				dayTypeObj[1].checked = 1;
			}else{
				//按日保存类型的其他类型
			}
		}else if("周" == type){
			var weekTypeObj = document.getElementsByName("weekType");
// 			if("${qrtzDefinition.metaMap['weekType']}" == "weekType1"){
				weekTypeObj[0].checked = 1;
				var weekSpeNum = "${qrtzDefinition.metaMap['weekSpeNum']}";
				var arrs = weekSpeNum.split(",");
				var weekSpeNumObj = document.getElementsByName("weekSpeNum");
				for(var i = 0; i<arrs.length; i++){
					weekSpeNumObj[arrs[i]-1].checked = 1;
				}
// 			}else{
// 				//按周保存类型的其他类型
// 			}
		}else if("月" == type){
			var monthTypeObj = document.getElementsByName("monthType");
			if("${qrtzDefinition.metaMap['monthType']}" == "monthType1"){
				monthTypeObj[0].checked = 1;
				var monthNumObj = document.getElementsByName("monthNum");
				monthNumObj[0].value = "${qrtzDefinition.metaMap['monthNum'] }";
			}else if("${qrtzDefinition.metaMap['monthType']}" == "monthType2"){
				monthTypeObj[1].checked = 1;
				var monthWeekNumObj = document.getElementsByName("monthWeekNum");
				monthWeekNumObj[0].value = "${qrtzDefinition.metaMap['monthWeekNum']}";
				var monthNumObj = document.getElementsByName("monthNum");
				monthNumObj[1].value = "${qrtzDefinition.metaMap['monthNum'] }";
				var monthSpeWeekNum = document.getElementsByName("monthSpeWeekNum");
				monthSpeWeekNum[0].value = "${qrtzDefinition.metaMap['monthSpeWeekNum']}";
			}else{
				//按月保存类型的其他类型
			}
		}else if("年" == type){
			var yearTypeObj = document.getElementsByName("yearType");;
			if("${qrtzDefinition.metaMap['yearType']}" == "yearType1"){
				yearTypeObj[0].checked = 1;
				var yearSpeMonthObj = document.getElementsByName("yearSpeMonth");
				yearSpeMonthObj[0].value = "${qrtzDefinition.metaMap['yearSpeMonth']}";
			}else if("${qrtzDefinition.metaMap['yearType']}" == "yearType2"){
				yearTypeObj[1].checked = 1;
				var yearSpeMonthObj = document.getElementsByName("yearSpeMonth");
				yearSpeMonthObj[1].value = "${qrtzDefinition.metaMap['yearSpeMonth']}";
				var yearWeekNumObj = document.getElementsByName("yearWeekNum");
				yearWeekNumObj.value = "${qrtzDefinition.metaMap['yearWeekNum']}";
				var yearSpeWeekObj = document.getElementsByName("yearSpeWeek");
				yearSpeWeekObj.value = "${qrtzDefinition.metaMap['yearSpeWeek']}";
			}else{
				//按年保存类型的其他类型
			}
		}else{
			//intervalType的其他类型
		}
		
	});
	
	
	//为每个模式类型选定需要的存储方式
	function change(value){
		if("天" == value){
			$("#day").show();
			$("#week").hide();
			$("#month").hide();
			$("#year").hide();
		}else if("周" == value){
			$("#day").hide();
			$("#week").show();
			$("#month").hide();
			$("#year").hide();
		}else if("月" == value){
			$("#day").hide();
			$("#week").hide();
			$("#month").show();
			$("#year").hide();
		}else{
			$("#day").hide();
			$("#week").hide();
			$("#month").hide();
			$("#year").show();
		}
	}
	
	//jobDataMap的保存      定期模式的数据用Json方式保存	
	function save(){
		
		//jobDataMap的保存
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
		
		//定期模式的数据用Json方式保存
		var jsondata={};
		var intervalTypeObj = document.getElementsByName("intervalType");
		var intervalType = "";
		for(var i = 0; i<intervalTypeObj.length; i++){
			if(intervalTypeObj[i].checked == 1){
				intervalType = intervalTypeObj[i].value;
			}
		}
		
		if("天" == intervalType){
			var dayTypeObj = document.getElementsByName("dayType");
			for(var i = 0; i<dayTypeObj.length; i++){
				if(dayTypeObj[i].checked == 1){
					jsondata["dayType"] = dayTypeObj[i].value;
					if(dayTypeObj[i].value == "dayType1"){
						var numObj = document.getElementsByName("dayNum");
						jsondata["dayNum"] = numObj[0].value;
					}
				}
			}
		}else if("周" == intervalType){
			var weekTypeObj = document.getElementsByName("weekType");
			jsondata["weekType"] = weekTypeObj[0].value;
			var numObj = document.getElementsByName("weekNum");
			jsondata["weekNum"] = numObj[0].value;
			var weekSpeNumObj = document.getElementsByName("weekSpeNum");
			var weekSpeNum = "";
			for(var i = 0; i<weekSpeNumObj.length; i++){
				if(weekSpeNumObj[i].checked){
					var j = 1;
					if(j < $("input[name='weekSpeNum']:checked").length){
						weekSpeNum += weekSpeNumObj[i].value+",";
						j++;
					}else{
						weekSpeNum += weekSpeNumObj[i].value;
					}
				}
			}
			jsondata["weekSpeNum"] = weekSpeNum;
		}else if("月" == intervalType){
			var monthTypeObj = document.getElementsByName("monthType");
			for(var i = 0; i<monthTypeObj.length; i++){
				if(monthTypeObj[i].checked == 1){
					jsondata["monthType"] = monthTypeObj[i].value;
					if(monthTypeObj[i].value == "monthType1"){
						var numObj = document.getElementsByName("monthNum");
						jsondata["monthNum"] = numObj[0].value;
						var monthDayNumObj = document.getElementsByName("monthDayNum");
						jsondata["monthDayNum"] = monthDayNumObj[0].value;
					}else{
						var numObj = document.getElementsByName("monthNum");
						jsondata["monthNum"] = numObj[1].value;
						var monthWeekNumObj = document.getElementsByName("monthWeekNum");
						jsondata["monthWeekNum"] = monthWeekNumObj[0].value;
						var monthSpeWeekNumObj = document.getElementsByName("monthSpeWeekNum");
						jsondata["monthSpeWeekNum"] = monthSpeWeekNumObj[0].value;
					}
				}
			}
		}else if("年" == intervalType){
			var yearTypeObj = document.getElementsByName("yearType");
			for(var i = 0; i<yearTypeObj.length; i++){
				if(yearTypeObj[i].checked == 1){
					jsondata["yearType"] = yearTypeObj[i].value;
					var numObj = document.getElementsByName("yearNum");
					jsondata["yearNum"] = numObj[0].value;
					if(yearTypeObj[i].value == "yearType1"){
						var yearSpeMonthObj = document.getElementsByName("yearSpeMonth");
						jsondata["yearSpeMonth"] = yearSpeMonthObj[0].value;
						var yearSpeDayObj = document.getElementsByName("yearSpeDay");
						jsondata["yearSpeDay"] = yearSpeDayObj[0].value;
					}else{
						var yearSpeMonthObj = document.getElementsByName("yearSpeMonth");
						jsondata["yearSpeMonth"] = yearSpeMonthObj[1].value;
						var yearWeekNumObj = document.getElementsByName("yearWeekNum");
						jsondata["yearWeekNum"] = yearWeekNumObj[0].value;
						var yearSpeWeekObj = document.getElementsByName("yearSpeWeek");
						jsondata["yearSpeWeekNum"] = yearSpeWeekObj[0].value;
					}
				}
			}
		}else{
			//intervalType的其他类型
		}
		
		var jsonString = JSON.stringify(jsondata);
		$("#intervalMeta").val(jsonString);
		
		
	}
	</script>
</head>
<body>

<c:if test="${not empty message}">
		<div id="message" class="alert alert-success">
			<button data-dismiss="alert" class="close">×</button>${message}</div>
</c:if>
<form id="inputform" class="form-horizontal" action="${ctx}/admin/qrtz/def/updateAndCreate" method="post">
<input type="hidden" name="id" value="${qrtzDefinition.id}"/>
<input type="hidden" name="qrtzGroup.id" value="${qrtzDefinition.qrtzGroup.id }"/>
<div class="panel panel-info">

<div class="panel-heading">调度定义信息 </div>
<div class="panel-body">
	<div class="form-group">
		<label for="name" class="col-md-2 control-label">名称(Name)：</label>
			<div class="col-sm-3">
				<input type="text" class="form-control input-sm required" id="inputName" name="name" value="${qrtzDefinition.name}"/>
			</div>
		<label for="code"  class="col-md-2 control-label">Code：</label>
			<div class="col-sm-3">
				<input type="text" class="form-control input-sm required" id="inputCode" name="code" value="${qrtzDefinition.code}"/>
			</div>	
	</div>
	<div class="form-group">
		<label for="jobClassName"  class="col-md-2 control-label">Job：</label>
			<div class="col-sm-3">
<%-- 				<input type="text" class="form-control input-sm required" name="jobClassName" value="${qrtzDefinition.jobClassName}"/> --%>
				<select name="jobClassName" class="form-control input-sm required">
					<option>请选择</option>
					<c:forEach items="${jobs }" var="job">
						<option value="${job.value }" <c:if test="${qrtzDefinition.jobClassName == job.value}">selected="selected"</c:if>>${job.key }</option>
					</c:forEach>
				</select>
			</div>	
			
		<label for="jobDataMap"  class="col-md-2 control-label">任务参数：</label>
			<input type="hidden" name="jobDataMap" id="jobDataMap" value="${qrtzDefinition.jobDataMap}"/>
						<table border=0 cellpadding="0" cellspacing="0"
							style="border: 0; margin: 0 0;">
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
							<c:forEach items="${qrtzDefinition.jobDataMetaMap }" var="jobDataMeta">
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

<div class="panel-heading">约会时间 </div>
<div class="panel-body">
	<div class="form-group">
				<label for="startJobTime" class="col-md-2 control-label">开始时间(T)：</label>
					<div class="col-sm-3">				
						<input  data-format="HH:mm:ss" readonly="readonly" type="text" class="form-control required search-query form_time" id="startJobTime"  name="startJobTime" value="<fmt:formatDate value="${qrtzDefinition.startJobTime}" pattern="HH:mm:ss"/>"> 
						<span class="add-on">
     						<i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
                         </span>
					</div>
				<label for="endJobTime" class="col-md-2 control-label">结束时间(N)：</label>
					<div class="col-sm-3">				
						<input  data-format="HH:mm:ss" readonly="readonly" type="text" class="form-control required search-query form_time" id="endJobTime"  name="endJobTime" value="<fmt:formatDate value="${qrtzDefinition.endJobTime }" pattern="HH:mm:ss"/>"> 
						<span class="add-on">
     						<i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
                        </span>		
					</div>
	</div>
</div>

<div class="panel-heading">定期模式</div>
<div class="panel-body">
<!-- 左边 -->
	<div class="col-md-2">
		<div class="panel panel-default">
			<div id="permission-roles" >
				<div class="list-group" id="role-list-group">
					<input type="radio" name="intervalType" value="天" onclick="change(this.value)" >&nbsp;&nbsp;&nbsp;按天<br/>
					<input type="radio" name="intervalType" value="周" onclick="change(this.value)" >&nbsp;&nbsp;&nbsp;按周<br/>
					<input type="radio" name="intervalType" value="月" onclick="change(this.value)" >&nbsp;&nbsp;&nbsp;按月<br/>
					<input type="radio" name="intervalType" value="年" onclick="change(this.value)" >&nbsp;&nbsp;&nbsp;按年<br/>
				</div>
			</div>
	</div>
	</div>
	<!-- 右边 -->
	<div class="col-md-6">
	
		<!-- 隐藏域用来保存intervalMeta的值 -->
		<input type="hidden" name="intervalMeta" id="intervalMeta" value="${qrtzDefinition.intervalMeta }"/>
		
		<div id="day">
			<div class="form-group">
				<input type="radio" name="dayType" value="dayType1"/>&nbsp;&nbsp;每(V)&nbsp;&nbsp;<input type="text" name="dayNum" value="${qrtzDefinition.metaMap['dayNum'] }"/>&nbsp;&nbsp;天
			</div>
			<div class="form-group">
				<input type="radio" name="dayType" value="dayType2"/>&nbsp;&nbsp;每个工作日
			</div>
		</div>
		
		<div id="week" style="display: none;">
			<div class="form-group">
				<input type="radio" name="weekType" id="weekType" value="weekType1"/>&nbsp;&nbsp;重复间隔为(C)&nbsp;&nbsp;<input type="week" name="weekNum" value="${qrtzDefinition.metaMap['weekNum'] }"/>&nbsp;&nbsp;周后
			</div>
			<div class="form-group">
				<input type="checkbox" name="weekSpeNum" value="1"/>星期一&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox" name="weekSpeNum" value="2"/>星期二&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox" name="weekSpeNum" value="3"/>星期三&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox" name="weekSpeNum" value="4"/>星期四&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox" name="weekSpeNum" value="5"/>星期五&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox" name="weekSpeNum" value="6"/>星期六<br>
				<input type="checkbox" name="weekSpeNum" value="7"/>星期日&nbsp;&nbsp;&nbsp;&nbsp;
			</div>
		</div>
		
		<div id="month" style="display: none;">
			<div class="form-group">
				<input type="radio" name="monthType" value="monthType1"/>&nbsp;&nbsp;每(A)<input type="text" name="monthNum" />&nbsp;&nbsp;个月的第&nbsp;&nbsp;<input type="number" name="monthDayNum" value="${qrtzDefinition.metaMap['monthDayNum'] }"/>&nbsp;&nbsp;天
			</div>
			<div class="form-group">
				<input type="radio" name="monthType" value="monthType2"/>&nbsp;&nbsp;每(E)<input type="text" name="monthNum" />&nbsp;&nbsp;个月的&nbsp;&nbsp;
				<select name="monthWeekNum">
					<option value="1">第一个</option>
					<option value="2">第二个</option>
					<option value="3">第三个</option>
					<option value="4">第四个</option>
					<option value="5">第五个</option>
				</select>&nbsp;
				<select name="monthSpeWeekNum">
					<option value="1">星期一</option>
					<option value="2">星期二</option>
					<option value="3">星期三</option>
					<option value="4">星期四</option>
					<option value="5">星期五</option>
					<option value="6">星期六</option>
					<option value="7">星期日</option>
				</select>&nbsp;&nbsp;
			</div>
		</div>
		<div id="year" style="display: none;">
			<div class="form-group">
				重复间隔为(C)&nbsp;&nbsp;<input type="text" name="yearNum" value="${qrtzDefinition.metaMap['yearNum'] }" />&nbsp;&nbsp;年后
			</div>
			<div class="form-group">
				<input type="radio" name="yearType" value="yearType1"/>&nbsp;&nbsp;时间：<select  name="yearSpeMonth">
														<option value="1">一月</option>
														<option value="2">二月</option>
														<option value="3">三月</option>
														<option value="4">四月</option>
														<option value="5">五月</option>
														<option value="6">六月</option>
														<option value="7">七月</option>
														<option value="8">八月</option>
														<option value="9">九月</option>
														<option value="10">十月</option>
														<option value="11">十一月</option>
														<option value="12">十二月</option>
													</select>&nbsp;&nbsp;<input type="text" name="yearSpeDay" value="${qrtzDefinition.metaMap['yearSpeDay'] }"/>日
			</div>
			<div class="form-group">
				<input type="radio" name="yearType" value="yearType2"/>&nbsp;&nbsp;时间：<select  name="yearSpeMonth">
														<option value="1">一月</option>
														<option value="2">二月</option>
														<option value="3">三月</option>
														<option value="4">四月</option>
														<option value="5">五月</option>
														<option value="6">六月</option>
														<option value="7">七月</option>
														<option value="8">八月</option>
														<option value="9">九月</option>
														<option value="10">十月</option>
														<option value="11">十一月</option>
														<option value="12">十二月</option>
													</select>&nbsp;&nbsp;<select name="yearWeekNum">
																			<option value="1">第一个</option>
																			<option value="2">第二个</option>
																			<option value="3">第三个</option>
																			<option value="4">第四个</option>
																			<option value="5">第五个</option>
																		</select>&nbsp;<select name="yearSpeWeek">
																							<option value="1">星期一</option>
																							<option value="2">星期二</option>
																							<option value="3">星期三</option>
																							<option value="4">星期四</option>
																							<option value="5">星期五</option>
																							<option value="6">星期六</option>
																							<option value="7">星期日</option>
																						</select>&nbsp;&nbsp;
			</div>
	</div>
	</div>
	
</div>

<div class="panel-heading">重复范围</div>
<div class="panel-body">
	<!-- 左边 -->
	<div class="col-md-6">
		<div class="form-group">
			<label for="startJobDate" class="col-md-3 control-label">开始日期：</label>
					<div class="col-sm-4">				
						<input  data-format="yyyy-MM-dd" type="text" readonly="readonly" class="form-control required search-query form_datetime" id="startJobDate"  name="startJobDate" value="<fmt:formatDate value="${qrtzDefinition.startJobDate}" pattern="yyyy-MM-dd"/>"> 
						<span class="add-on">
     						<i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
                         </span>
					</div>
		</div>
	</div>
	
	<!-- 右边 -->
	<div class="col-md-4">
		<div class="form-group">
			<label class="col-md-6 control-label"><input type="radio" name="endType" value="无结束日期"/>无结束日期</label>
		</div>
		<div class="form-group">
			<label class="col-md-12 control-label">
				<input type="radio" name="endType" value="重复"/>重复
				<input type="number" name="repeat" value="${qrtzDefinition.repeat }" step="1" min="0"/>次后结束
			</label>
		</div>
		<div class="form-group">
			<label for="endJobDate" class="col-md-6 control-label"><input type="radio" name="endType" value="结束日期"/>结束日期：</label>
				<div class="col-sm-6">				
					<input  data-format="yyyy-MM-dd" type="text" readonly="readonly" class="form-control search-query form_datetime" id="endJobDate"  name="endJobDate" value="<fmt:formatDate value="${qrtzDefinition.endJobDate}" pattern="yyyy-MM-dd"/>"> 
					<span class="add-on">
     					<i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
                    </span>
				</div>
        </div>
		
		<!--  
        -->
	</div>	
</div>


<!-- footer -->	
	<div class="panel-footer">
		<div class=" col-md-offset-2">
			<input id="submit_btn" class="btn btn-default" type="submit" onclick="save()" value="保存并新增" />
			<input id="submit_btn" class="btn btn-default" type="button" value="保存并关闭" onclick="saveDefinitionItem()" /> 
			<input id="cancel_btn" type="button" class="btn btn-default" value="关闭" onclick="closeChange()" />
		</div>
	</div>
	
<!-- 	<div class="panel-footer"><div class=" col-md-offset-4"> -->
<!-- 	  	<input id="submit_btn" class="btn btn-default" type="submit" onclick="save()" value="确定" /> -->
<!-- 		<input id="cancel_btn" class="btn btn-default" type="button" value="取消" onclick="parent.location.reload();"/></div> -->
<!-- 	</div> -->

	
</div>	
			
</form>

<script type="text/javascript">

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
	
	function getNum(value){
		var dayNum;
		if(value == 1){
			dayNum = /^([1-9]|0[1-9]|[12][0-9]|3[0-1])$/;
		}else if(value == 2){
			dayNum = /^([1-9]|0[1-9]|1[0-9]|2[0-8])$/;
		}else if(value == 3){
			dayNum = /^([1-9]|0[1-9]|[12][0-9]|3[0-1])$/;
		}else if(value == 4){
			dayNum = /^([1-9]|0[1-9]|[12][0-9]|3[0])$/;
		}else if(value == 5){
			dayNum = /^([1-9]|0[1-9]|[12][0-9]|3[0-1])$/;
		}else if(value == 6){
			dayNum = /^([1-9]|0[1-9]|[12][0-9]|3[0])$/;
		}else if(value == 7){
			dayNum = /^([1-9]|0[1-9]|[12][0-9]|3[0-1])$/;
		}else if(value == 8){
			dayNum = /^([1-9]|0[1-9]|[12][0-9]|3[0-1]$)/;
		}else if(value == 9){
			dayNum = /^([1-9]|0[1-9]|[12][0-9]|3[0]$)/;
		}else if(value == 10){
			dayNum = /^([1-9]|0[1-9]|[12][0-9]|3[0-1])$/;
		}else if(value == 11){
			dayNum = /^([1-9]|0[1-9]|[12][0-9]|3[0]$)/;
		}else if(value == 12){
			dayNum = /^([1-9]|0[1-9]|[12][0-9]|3[0-1])$/;
		}
		return dayNum;
	}

	$(document).ready(function(){
		jQuery.validator.addMethod("isMonthDay", function(value, element) {
			var obj = document.getElementsByName("monthNum");
			var temp = obj[0].value;
			var dayNum = getNum(temp);
			return this.optional(element) || (dayNum.test(value));
		}, "请书写正确的天数");
		
		jQuery.validator.addMethod("isYearDay", function(value, element) {
			var obj = document.getElementsByName("yearSpeMonth");
			var temp = obj[0].value;
			var dayNum = getNum(temp);
			return this.optional(element) || (dayNum.test(value));
		}, "请书写正确的天数");
		
		jQuery.validator.addMethod("isJobClassName", function(value, element) {
			if(value == "请选择"){
				return false;
			}
			return true;
		}, "请选择Job！！！");
		
		$("#inputform").validate({
			rules : {
				jobClassName : {
					isJobClassName : true
				},
				monthDayNum : {
					isMonthDay : true
				},
				yearSpeDay : {
					isYearDay : true
				},
				name : {
					required : true,
					maxlength : 20,
					remote:{
						url:"${ctx}/admin/qrtz/def/checkName",
						type:"post",
						datatype:"json",
						data:{
							name:function(){
								return $("#inputName").val();
							},
							initialname:"${qrtzDefinition.name}"
						}
					}
				},
				code : {
					required : true,
					maxlength : 10,
					remote:{
						url:"${ctx}/admin/qrtz/def/checkCode",
						type:"post",
						datatype:"json",
						data:{
							name:function(){
								return $("#inputCode").val();
							},
							initialcode:"${qrtzDefinition.code}"
						}
					}
				},
				
			}
		});
	});
	
	function saveDefinitionItem() {
		save();
		var validator = $("#inputform").validate();
		if (validator.form()) {//判断加入所有校验都通过后再做ajax提交；
			$.ajax({
				type : "post",
				data : $("#inputform").serialize(),
				url : "${ctx}/admin/qrtz/def/save",
				success : function(msg) {
					parent.$.fancybox.close();
				}
			});
		}
	};
	
	function closeChange() {
		parent.$.fancybox.close();
	};

</script>

</body>
</html>