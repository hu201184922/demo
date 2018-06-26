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
function deletedef(id) {
	var r = window.confirm("确定要删除吗？");
	if (r) {
		$.ajax({
			type : "post",
			url : "${ctx}/admin/qrtz/def/delete2/" + id,
			success : function(msg) {
				var id = $("#qrtzGroup_id").val();
				loadQrtzDefinition(id);
			}
		});
	}
};

function release(id){
	var r = window.confirm("确定要发布吗？");
	if (r) {
		$.ajax({
			type : "get",
			url : "${ctx}/admin/qrtz/def/release/" + id,
			success : function(msg) {
				var id = $("#qrtzGroup_id").val();
				loadQrtzDefinition(id);
			}
		});
// 		location.href="${ctx}/admin/qrtz/def/release/" + id;
	}
}

function generate(id){
	var r = window.confirm("确定要立即生成吗？");
	if (r) {
		$.ajax({
			type : "get",
			url : "${ctx}/admin/qrtz/def/generate/" + id,
			success : function(msg) {
				var id = $("#qrtzGroup_id").val();
				loadQrtzDefinition(id);
			}
		});
// 		location.href="${ctx}/admin/qrtz/def/generate/" + id;
	}
}

function stop(id){
	var r = window.confirm("确定要终止吗？");
	if (r) {
		$.ajax({
			type : "get",
			url : "${ctx}/admin/qrtz/def/stop/" + id,
			success : function(msg) {
				var id = $("#qrtzGroup_id").val();
				loadQrtzDefinition(id);
			}
		});
// 		location.href="${ctx}/admin/qrtz/def/stop/" + id;
	}
}

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

</script>
</head>
<body>

		<table id="contentTable" class="table table-condensed table-hover table-striped table-responsive">
			<thead>
				<tr>
					<th><input type="checkbox" name="selectAll" id="selectAll" /></th>
					<th>名称</th>
					<th>code</th>
					<th>权限定类名</th>
					<th>任务参数</th>
					<th>开始日期</th>
					<th>结束日期</th>
					<th>开始时间</th>
					<th>结束时间</th>
					<th>重复次数</th>
					<th>结束类型</th>
					<th>定期模式类型</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody class="td" id="tbody">
				<c:if test="${qrtzDefinitions != null }">
				<c:forEach items="${qrtzDefinitions}" var="qrtzDefinition">
					<tr>
						<td><input type="checkbox" value="${qrtzDefinition.id}" name="box" /></td>
						<td>${qrtzDefinition.name }</td>
						<td>${qrtzDefinition.code }</td>
						<td style="word-wrap:break-word;word-break:break-all;overflow:hidden;">${qrtzDefinition.jobClassName }</td>
						<td>${qrtzDefinition.jobDataMap }</td>
						<td>${qrtzDefinition.startJobDate }</td>
						<td>${qrtzDefinition.endJobDate }</td>
						<td>${qrtzDefinition.startJobTime }</td>
						<td>${qrtzDefinition.endJobTime }</td>
						<td>${qrtzDefinition.repeat }</td>
						<td>${qrtzDefinition.endType }</td>
						<td>${qrtzDefinition.intervalType }</td>
						<td>${qrtzDefinition.state }</td>
						<td>
								<c:choose>
									<c:when test="${qrtzDefinition.state == '草稿' }" >
										<div><a class="btn" onclick="release(${qrtzDefinition.id})"  href="javascript:void(0)">发布</a></div>
										<div><a class="btn ase" data-fancybox-type="iframe" href="${ctx}/admin/qrtz/def/edit/${qrtzDefinition.id}">编辑</a></div>
										<div><a class="btn" onclick="deletedef(${qrtzDefinition.id})" href="javascript:void(0)">删除</a></div>
									</c:when>
									<c:when test="${qrtzDefinition.state == '发布' }" >
										<div><a class="btn" onclick="generate(${qrtzDefinition.id})" href="javascript:void(0)">立即生成</a></div>
										<div><a class="btn" onclick="deletedef(${qrtzDefinition.id})" href="javascript:void(0)">删除</a></div>
									</c:when>
									<c:when test="${qrtzDefinition.state == '已生成' }" >
										<div><a class="btn" onclick="stop(${qrtzDefinition.id})" href="javascript:void(0)">终止</a></div>
										<div><a class="btn" onclick="deletedef(${qrtzDefinition.id})" href="javascript:void(0)">删除</a></div>
									</c:when>
									<c:when test="${qrtzDefinition.state == '终止' }" >
										<div><a class="btn" onclick="deletedef(${qrtzDefinition.id})" href="javascript:void(0)">删除</a></div>
									</c:when>
									<c:otherwise>  
										<div><a class="btn" onclick="deletedef(${qrtzDefinition.id})" href="javascript:void(0)">删除</a></div>
									</c:otherwise> 
								</c:choose>
						</td>
					</tr>
				</c:forEach>
				</c:if>
			</tbody>
		</table>
</body>
</html>