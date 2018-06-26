<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<title>人员管理</title>
</head>
<body>
	<div><input id="rs" type="hidden" value="${ctx}" /></div>
	<div class="row-fluid">
	 <c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	</div>
		<div class="btn-group">
			<a button type="button" class="btn btn-default input-medium search-query ase"  data-fancybox-type="iframe" href="${ctx}/admin/party/person/create">新增</a>
			<a button id="remove" type="button" class="btn btn-default"  href="#">删除</a>
		</div>
		<div class="panel panel-default">
		<div class="panel-body">
			<div class="row">
				<div class="col-md-10">
					<form class="form-inline" action="${ctx}/admin/party/person" method="get">
					<div class="form-group">
						<label for="name">姓名：</label><input type="text" id="name" name="search_LIKE_name"
						class="input-sm search-query form-control" value="${param.search_LIKE_name}">
					</div>
					<div class="form-group">
						<label for="code">编号：</label> <input type="text" id="code" name="search_EQ_code"
						class="input-sm search-query form-control" value="${param.search_EQ_code}">
					</div>	
					<div class="form-group">	
						<label for="orgName">部门：</label><input type="text" id="orgName" name="search_EQ_org.name"
						class="input-sm search-query form-control" value="${param.search_EQ_org.name}">
					</div>	
					<div class="form-group">	
						<label for="postName">岗位：</label><input type="text" id="postName" name="search_EQ_post.name"
						class="input-sm search-query form-control" value="${param.search_EQ_post.name}">
					</div>
					<div class="form-group">
						<label>入职时间从</label><input type="text" name="search_GT_entryDate" class="input-sm search-query form_datetime form-control" value="${param.search_GT_entryDate}">
						<label>到</label><input type="text" name="search_LT_entryDate" class="input-sm search-query form_datetime form-control">
						<button type="submit" class="btn btn-default" id="search_btn" value="${param.search_LT_lastLoginedTime}">查询</button>
					</div>			
					</form>
				</div>
				<div class="col-md-2">
					<tags:sort />
				</div>
			</div>
		</div>
	</div>
    <div id="manager" class="row btn-group" style="display: none">
		<button class="btn btn-primary">全选</button>
		<button class="btn btn-primary">删除</button>
	</div>
<div class="panel panel-info">
  <div class="panel-heading">人员基础信息</div>
		<table id="contentTable"
			class="table table-condensed table-hover table-striped table-responsive">
			<thead>
				<tr>
					<th><input type="checkbox" name="selectAll" id="selectAll" /></th>
					<th>姓名</th>
					<th>性别</th>
					<th>编号</th>
					<th>部门</th>
					<th>岗位</th>
					<th>入职时间</th>
					<th>邮箱</th>
					<th>手机号</th>
					<th>电话</th>
					<th>出生日期</th>
					<th>学历</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody class="td">
				<c:forEach var="person" items="${personset.content}">
					<tr id="${person.id}">
						<td><input type="checkbox" value="${person.id}" name="box" /></td>
						<td>${person.name}</td>
						<td>
						<tags:dict itemCode="${person.sex}" dictCode="gender" type="label"/>
						</td>
						<td>${person.code}</td>
						<td>${person.org.name}</td>
						<td>${person.post.name}</td>
						<td><fmt:formatDate value="${person.entryDate}" pattern="yyyy-MM-dd"/></td>
						<td>${person.email}</td>
						<td>${person.mobile}</td>
						<td>${person.phone}</td>
						<td><fmt:formatDate value="${person.birthday}" pattern="yyyy-MM-dd"/></td>
						<td><tags:dict itemCode="${person.degree}" dictCode="xl" type="label"/></td>
						<td>
							<a class="btn nframe search-query ase" data-fancybox-type="iframe" href="${ctx}/admin/party/person/update/${person.id}">编辑</a>
							<a type="button"  onclick='deletePerson(${person.id});'>删除</a>
			  		   </td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
</div>
	<div class="collapse navbar-collapse">
  		<div class="nav navbar-nav navbar-right">	 
				<tags:pagination page="${personset}" paginationSize="5" />
 	    </div>
	</div>
	<script type="text/javascript">
	$("#selectAll").click( function () { 
		var obj = document.getElementById("selectAll");  
		var value = obj.checked;
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
		$(".td :input").each(function(i){
			if(this.checked==true){
				var rs=this.value;
				arr=arr+rs+",";
			}		
		});
		if(arr.length==0){
			alert("您还没有选择要删除的对象");
		}else{
			var r = window.confirm("确定要删除吗？");
			if(r){
				arr=arr.substring(0,arr.length-1);
				location.href="${ctx}/admin/party/person/delete/"+arr;
			}		
			<!--var rs=$("#rs").val();-->			
		}	
	});
	$(document).ready(function(){
		$(".form_datetime").datetimepicker({
			minView: "month",
			format : "yyyy-mm-dd",
			pickerPosition: "bottom-left",
			autoclose : true,
			todayBtn : true,
			pickTime: false
		});
		$(".ase").fancybox({
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
	function deletePerson(id){
		var v=window.confirm("确定要删除吗？");
		if(v){
			window.location.href="${ctx}/admin/party/person/delete/"+id;
		}
	}
	</script>
</body>
</html>