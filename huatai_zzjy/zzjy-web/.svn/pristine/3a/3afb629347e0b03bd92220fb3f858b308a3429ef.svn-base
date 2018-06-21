<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!-- <meta name="decorator" content="nodecorate" /> -->
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>调度定义</title>
<script type="text/javascript">

$("#selectAll").click(function() {
	var obj = document.getElementById("selectAll");
	var value = obj.checked;
	if (value == true) {
		$(".td :input").each(function(i) {
			this.checked = true;

		});

	} else {
		$(".td :input").each(function(i) {
			this.checked = false;
		});
	}
});

function deleteCron(id){
// 	var r = window.confirm("确定要删除吗？");
// 	if (r) {
	$.confirm("是否确定删除？", 
		function() {
		$.ajax({
			type : "post",
			url : "${ctx}/admin/qrtz/cron/delete/" + id,
			success : function(msg) {
				var id = $("#qrtzGroup_id").val();
				loadCronTrigger(id);
			}
		});
	})
}

function release(id){
// 	var r = window.confirm("确定要发布吗？");
// 	if (r) {
	$.confirm("确定要发布吗？",
		function(){
		$.ajax({
			type : "get",
			url : "${ctx}/admin/qrtz/cron/release/" + id,
			success : function(msg) {
				var id = $("#qrtzGroup_id").val();
				loadCronTrigger(id);
			}
		});
// 		location.href="${ctx}/admin/qrtz/cron/release/" + id;
	})
}

function generate(id){
// 	var r = window.confirm("确定要立即生成吗？");
// 	if (r) {
	$.confirm("确定要立即生成吗？",function(){
		$.ajax({
			type : "get",
			url : "${ctx}/admin/qrtz/cron/generate/" + id,
			success : function(msg) {
				var id = $("#qrtzGroup_id").val();
				loadCronTrigger(id);
			}
		});
// 		location.href="${ctx}/admin/qrtz/cron/generate/" + id;
	})
}
function run(id){
// 	var r = window.confirm("确定要立即执行吗？");
// 	if (r) {
	$.confirm("确定要立即执行吗？",function(){
		$.ajax({
			type : "get",
			url : "${ctx}/admin/qrtz/cron/run/" + id,
			success : function(msg) {
				var id = $("#qrtzGroup_id").val();
				loadCronTrigger(id);
			}
		});
	})
}
function getState(id,obj){
	//var r = window.confirm("确定要立即执行吗？");
	//if (r) {
		$.ajax({
			type : "get",
			url : "${ctx}/admin/qrtz/cron/getJobState/" + id,
			success : function(msg) {
				var p1 = $(obj).parent();
				var p2 = p1.parent();
				var c1 = p2.children(".runState");
				c1.each(function(){
					$(this).text(msg);
				});
				//alert(c1);
				//$(obj).parent().parent().children(".runState")[0].text(msg);
				//$(obj).text(msg);
			}
		});
	//}
}
function stop(id){
// 	var r = window.confirm("确定要终止吗？");
// 	if (r) {
	$.confirm("确定要终止吗？",function(){
		$.ajax({
			type : "get",
			url : "${ctx}/admin/qrtz/cron/stop/" + id,
			success : function(msg) {
				var id = $("#qrtzGroup_id").val();
				loadCronTrigger(id);
			}
		});
// 		location.href="${ctx}/admin/qrtz/cron/stop/" + id;
	})
}

</script>
</head>
<body>

		<table id="contentTable" class="table table-condensed table-hover table-striped table-responsive">
			<thead>
				<tr>
					<th style="width:5%;text-align:center"><input type="checkbox" name="selectAll" id="selectAll" /></th>
					<th style="width:10%;text-align:center">名称</th>
					<th style="width:10%;text-align:center">开始时间</th>
					<th style="width:10%;text-align:center">结束时间</th>
					<th style="width:10%;text-align:center">cron表达式</th>
					<th style="width:10%;text-align:center">任务参数</th>
					<th style="width:10%;text-align:center">状态</th>
					<th style="width:10%;text-align:center">执行状态</th>
					<th style="width:35%;text-align:center">操作</th>
				</tr>
			</thead>
			<tbody class="td" id="tbody">
				<c:if test="${cronTriggers != null }">
				<c:forEach items="${cronTriggers}" var="cronTirgger">
					<tr>
						<td style="width:5%"><input type="checkbox" value="${cronTirgger.id}" name="box" /></td>
						<td style="width:10%">${cronTirgger.name }</td>
						<td style="width:10%">${cronTirgger.startTime }</td>
						<td style="width:10%">${cronTirgger.endTime }</td>
						<td style="width:10%">${cronTirgger.cronExp }</td>
						<td style="width:10%">${cronTirgger.jobDataMap }</td>
						<td style="width:10%">${cronTirgger.state }</td>
						<td style="width:10%" class="runState"></td>
						<td style="width:25%">
							<c:choose>
									<c:when test="${cronTirgger.state == '草稿' }" >
										<a class="btn" onclick="release(${cronTirgger.id})"  href="javascript:void(0)">发布</a>
										<a class="btn cron" data-fancybox-type="iframe" href="${ctx}/admin/qrtz/cron/edit/${cronTirgger.id}">编辑</a>
										<a class="btn" onclick="deleteCron(${cronTirgger.id})" href="javascript:void(0)">删除</a>
									</c:when>
									<c:when test="${cronTirgger.state == '发布' }" >
										<a class="btn" onclick="generate(${cronTirgger.id})" href="javascript:void(0)">生成</a>
										<a class="btn cron" data-fancybox-type="iframe" href="${ctx}/admin/qrtz/cron/edit/${cronTirgger.id}">编辑</a>
										<a class="btn" onclick="deleteCron(${cronTirgger.id})" href="javascript:void(0)">删除</a>
									</c:when>
									<c:when test="${cronTirgger.state == '已生成' }" >
										<a class="btn" onclick="run(${cronTirgger.id})" href="javascript:void(0)">立即执行</a>
										<a class="btn" onclick="getState(${cronTirgger.id},this)" href="javascript:void(0)">刷新状态</a>
										<a class="btn" onclick="stop(${cronTirgger.id})" href="javascript:void(0)">终止</a>
									</c:when>
									<c:when test="${cronTirgger.state == '终止' }" >
										<a class="btn" onclick="generate(${cronTirgger.id})" href="javascript:void(0)">生成</a>
										<a class="btn cron" data-fancybox-type="iframe" href="${ctx}/admin/qrtz/cron/edit/${cronTirgger.id}">编辑</a>
										<a class="btn" onclick="deleteCron(${cronTirgger.id})" href="javascript:void(0)">删除</a>
									</c:when>
									<c:otherwise>  
										<a class="btn" onclick="deleteCron(${cronTirgger.id})" href="javascript:void(0)">删除</a>
									</c:otherwise> 
							</c:choose>
<%-- 							<a class="btn cron" data-fancybox-type="iframe" href="${ctx}/admin/qrtz/cron/edit/${cronTirgger.id}">编辑</a></div> --%>
<%-- 							<a class="btn" onclick="deleteCron(${cronTirgger.id})" href="javascript:void(0)">删除</a></div>  --%>
						</td>
					</tr>
				</c:forEach>
				</c:if>
			</tbody>
		</table>
	
<script type="text/javascript">
$(document).ready(function(){
	
	$(".cron").fancybox({
		fitToView	: false,
		width		: '80%',
		height		: '80%',
		autoSize	: false,
		closeClick	: false,
		openEffect	: 'none',
		closeEffect	: 'none',
		afterClose:function(){
			var id = $("#qrtzGroup_id").val();
			loadCronTrigger(id);
		}
	});
	
});
</script>
	
</body>
</html>