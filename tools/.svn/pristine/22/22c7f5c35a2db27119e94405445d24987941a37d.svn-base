<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<title>资源管理管理</title>
<style type="text/css">
.text-right{
	text-align: right;
}
</style>
<script>
	function disableOrNot(){
		var arr=[];
		$(".td :input[type=checkbox]").each(function(i){
			if(this.checked==true){
				arr.push(this.value);
			}
		});
		if(arr.length==0){
			alert("您还没有选择对象");
		}else if(confirm("您确定要禁用/启用选中对象吗")){
			location.href="${ctx}/admin/sec/resource/dis-en-abled/"+arr.join(",");
			
		}
	}
	function refreshFilterChains(){
		if(confirm("您确定要刷新系统资源权限吗")){
			location.href="${ctx}/admin/sec/resource/refresh/filters";
		}
	}
	function scannerResource(){
		location.href="${ctx}/admin/sec/resource/scanner";
	}
</script>
</head>
<body>
   <c:if test="${not empty message}">
   		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
   </c:if>
   <div class="row btn-group">
		<button class="addResource btn btn-default" data-fancybox-type="iframe" href="${ctx}/admin/sec/resource/create">新增</button>
		<button  id="remove" class="btn btn-default">删除</button>
		<button class="btn btn-default" onclick="javascript:disableOrNot()">禁用/启用</button>
		<button class="btn btn-default" onclick="javascript:refreshFilterChains()">刷新系统权限</button>
		<div class="btn-group">
         <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
       	  操作
         <span class="caret"></span>
        </a>
        <ul class="dropdown-menu">
    		<li><a href="javascript:scannerResource()">全局资源扫描</a></li>
    		<li><a>刷新缓存</a></li>
        </ul>
    	</div>
	</div>
	<div class="row" style="margin-top: 20px">
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="row">
					<div class="col-md-10">
						<form class="form-inline" action="#" role="form">
							<div class="form-group col-md-12">
								<div class="form-group col-md-5">
									<label for="name" class="col-md-5 text-right control-label">名称：</label> 
									<input type="text" name="search_LIKE_name" class="form-control input-sm"
											id="name" value="${param.search_LIKE_name}">
								</div>
								<div class="form-group col-md-5">
									<label for="resType"" class="col-md-5 text-right">类型：</label> 
									<select name="search_EQ_resType" id="resType"" class="form-control input-sm">
										<option></option>
										<option value="U" <c:if test="${param.search_EQ_resType == 'U' }">selected</c:if>>URL</option>
										<option value="E" <c:if test="${param.search_EQ_resType == 'E' }">selected</c:if>>ELEMENT</option>
										<option value="M" <c:if test="${param.search_EQ_resType == 'M' }">selected</c:if>>METHOD</option>
									</select>
								</div>
							</div>
							<div class="form-group col-md-12" style="margin-top: 5px">
								<div class="form-group col-md-5">
									<label for="resString" class="col-md-5 text-right">唯一字符串：</label> 
									<input type="text" name="search_LIKE_resString" class="form-control input-sm"
											id="resString" value="${param.search_LIKE_resString}">
								</div>
								<div class="form-group col-md-5">
									<label for="perString" class="col-md-5 text-right">授权字符串：</label> 
									<input type="text" name="search_LIKE_perString" class="form-control input-sm"
											id="perString" value="${param.search_LIKE_perString}">
								</div>
								<div class="col-md-2">
									<button type="submit" class="btn btn-default" id="search_btn">查询</button>
								</div>
							</div>
							
						</form>
					</div>
					<div class="col-md-2">
							<tags:sort />
					</div>
				</div>
			</div>
			
		</div>
	</div>
	<div class="row">
		<div class="panel panel-info">
		<div class="panel-heading">资源列表</div>
			<table id="contentTable"
				class="table table-condensed table-hover table-striped table-responsive">
				<thead>
					<tr>
						<th><input type="checkbox" name="selectAll" id="selectAll" /></th>
						<th>名称</th>
						<th>类型</th>
						<th>唯一字符串</th>
						<th>授权字符串</th>
						<th>是否可用</th>
						<th>备注</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody class="td">
				<c:forEach var="resource" items="${resources.content}"> 
					<tr id="${resource.id}">
						<td><input type="checkbox" value="${resource.id}" name="box" /></td>
						<td>${resource.name}</td>
						<td><tags:dict dictCode="resourceType" itemCode="${resource.resType}" type="label"/></td>
						<td>${resource.resString}</td>
						<td>${resource.perString}</td>
						<td><c:if test="${resource.enabled  eq true}">可用</c:if>
							<c:if test="${resource.enabled  eq false}">不可用</c:if></td>
						<td>${resource.descript}</td>
						<td><a class="editResource" data-fancybox-type="iframe" href="${ctx}/admin/sec/resource/update/${resource.id}">编辑</a>
						<a class="resource2Role" data-fancybox-type="iframe" href="${ctx}/admin/sec/rescategory/resource2RoleView/${resource.id}">分配给角色</a>
						<a href="javascript:void()" onclick="deleteResource('${resource.id}')">删除</a></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div class="collapse navbar-collapse">
		<div class="nav navbar-nav navbar-right">
			<tags:pagination page="${resources}" paginationSize="5" />
		</div>
	</div>
	<script>
	$(document).ready(function(){
		$(".addResource,.editResource").fancybox({
			fitToView	: false,
			width		: '80%',
			height		: '80%',
			autoSize	: false,
			closeClick	: false,
			openEffect	: 'none',
			closeEffect : 'none',
			beforeClose : function(){
				location.reload();
			}
		});
		$(".resource2Role").fancybox({
			fitToView	: false,
			width		: '80%',
			height		: '80%',
			autoSize	: false,
			closeClick	: false,
			openEffect	: 'none',
			closeEffect : 'none'
		});
	});
	
	$("#selectAll").click( function () { 
		var value = this.checked;
		if(value==true){
			$(".td :input").each(function(i){
				 this.checked=true;
				 
			});
			
		}else{
			$(".td :input").each(function(i){
				 this.checked=false;
			});
		}	
	});
	
	$("#remove").click(function(){
		var arr="";
		$(".td :input[type=checkbox]").each(function(i){
			if(this.checked==true){
				var rs=this.value;
				arr=arr+rs+",";
			}
			
		});
		if(arr.length==0){
			alert("您还没有选择要删除的对象");
		}else if(confirm("您确定要删除选中对象吗")){
			arr=arr.substring(0,arr.length-1);
			location.href="${ctx}/admin/sec/resource/delete/"+arr;
			
		}
	});
	function deleteResource(id){
		if(confirm("确定要删除吗")){
			location.href="${ctx}/admin/sec/resource/delete/"+id;
		}
	}
	</script>
</body>
</html>