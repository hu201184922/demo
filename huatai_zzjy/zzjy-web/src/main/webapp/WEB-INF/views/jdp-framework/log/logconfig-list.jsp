<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<title>日志配置管理</title>
</head>

<body>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success">
			<button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
		<div class="btn-group">
			<button id="btn" class="btn btn-default nframe"
				data-fancybox-type="iframe" href="${ctx}/admin/logcfg/create">新增</button>
		</div>
	<div class="panel panel-default">
		<div class="panel-body">
			<div class="row">
				<div class="col-md-10">
					<form class="form-inline" action="#" role="form">
						<div class="form-group">
							<label for="name">名称：</label> <input type="text"
								id="name" name="search_LIKE_name" class="form-control input-sm"
								value="${param.search_LIKE_name}">
						</div>
						<div class="form-group">
							<label for="resString">资源字符串：</label> <input type="text" id="resString" name="search_LIKE_resString"
								class="form-control input-sm" value="${param.search_LIKE_resString}">
						</div>
						<button type="submit" class="btn btn-default" id="search_btn">查询</button>
					</form>
				</div>
				<div class="col-md-2">
					<tags:sort />
				</div>
			</div>
		</div>
	</div>
	<div class="panel panel-info">
		<div class="panel-heading">日志配置信息</div>
		<table id="contentTable"
			class="table table-condensed table-hover table-striped table-responsive">
			<thead>
				<tr>
					<th>资源字符串</th>
					<th>HTTP Method</th>
					<th>名称</th>
					<th>启用日志</th>
					<th>备注</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${hyLogConfigs.content}" var="hyLogConfig">
					<tr>
						<td>${hyLogConfig.resString}</td>
						<td>${hyLogConfig.method}</td>
						<td>${hyLogConfig.name}</td>
						<td>${hyLogConfig.enable}</td>
						<td>${hyLogConfig.descript}</td>
						<td>
							<a class="btn btn-default nframe" data-fancybox-type="iframe" href="${ctx}/admin/logcfg/update/${hyLogConfig.id}">编辑</a>
							<!--  <a class="btn" href="${ctx}/admin/logcfg/delete/${hyLogConfig.id}">删除</a>-->
							<a class="btn" onclick='deleteLog(${hyLogConfig.id})'>删除</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="collapse navbar-collapse">
		<div class="nav navbar-nav navbar-right">
			<tags:pagination page="${hyLogConfigs}" paginationSize="5" />
		</div>
	</div>
	
	<script type="text/javascript">
	$(document).ready(function() {
		$(".nframe").fancybox({
			fitToView	: false,
			width		: '80%',
			height		: '80%',
			autoSize	: false,
			closeClick	: false,
			openEffect	: 'none',
			closeEffect	: 'none',
			afterClose:function(){
				location.reload(true);
			}
		});
	});
	
	function deleteLog(id){
		var v=window.confirm("确定要删除吗？");
		if(v){
			window.location.href="${ctx}/admin/logcfg/delete/"+id;
		}
	}
	</script>
</body>
</html>
