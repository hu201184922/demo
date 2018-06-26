<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<title>角色管理</title>
<script src="${ctx}/static/js/jdp/theme/js/util.js"></script> 
</head>
<body>
<input type="hidden" id="roleId" value="${roleId}" />
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success">
			<button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	<div class="row btn-group">
		<a class="btn" href="${ctx}/admin/sec/role/createPage">新增</a>
		<button onclick="del()" class="btn">删除</button>

		<div class="btn-group">
			<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
				操作<span class="caret"></span>
			</a>
			<ul class="dropdown-menu">
				<li><a>刷新缓存</a></li>
			</ul>
		</div>
	</div>
	<div class="row" style="margin-top: 20px">
		<div class="span8">
			<form class="form-search" action="${ctx}/admin/sec/role" method="get">
				<label for="code">角色标识：</label><input type="text" id="code" name="search_EQ_code"
					class="input-medium search-query" >
				<label for="name">名称：</label> <input type="text" id="name" name="search_EQ_name"
					class="input-medium search-query">
				<button type="submit" class="btn btn-primary" id="search_btn">查询</button>
			</form>
		</div>
		<div class="span2 offset2">
			<tags:sort />
		</div>
	</div>
	<div class="row">
		<table id="contentTable"
			class="table table-bordered table-condensed">
			<thead>
				<tr>
					<th><input type="checkbox" name="selectAll" id="selectAll" /></th>
					<th>角色标识</th>
					<th>名称</th>
					<th>备注</th>
				</tr>
			</thead>
			<tbody class="td">
				<c:forEach items="${roles.content}" var="role">
					<tr id="${role.id}">
						<td><input value="${role.id}" id="${role.id}" type="checkbox" /></td>
						<td onclick="findUsers(${role.id})"><a href="${ctx}/admin/sec/role/updatePage/${role.id}">${role.code}</a></td>
						<td>${role.name}</td>
						<td>${role.descript}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="row">
		<tags:pagination page="${roles}" paginationSize="5" />
	</div>
	

	<div class="row">
		<div class="span2 offset2" style="overflow-x: auto; overflow-y: auto; height: 500px; width:250px;">
			<table class="table table-bordered" border="0" align="center" width="250px">
				<thead>
					<tr>
						<th></th><th>未分配用户</th>
					</tr>
				</thead>
				<tbody id="unmatchUser">
					<c:forEach var="unmatchUser" items="${unmatchUser}"> 
			     <tr id="user${unmatchUser.id}"><td><input value="${unmatchUser.id}" type="checkbox"/></td><td>${unmatchUser.name}</td></tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div class="span2">
			<div style="margin-top: 30px; margin-left: 30%;">
				<button onclick="rightUser(this)" class="btn">&gt;&gt;</button>
				</br>
				<button onclick="leftUser(this)" style="margin-top: 10px;" class="btn">&lt;&lt;</button>
			</div>

		</div>

		<div class="span2 " style="overflow-x: auto; overflow-y: auto; height: 500px; width:250px;">
			<table class="table table-bordered" border="0" align="center" width="250px">
				<thead>
					<tr>
						<tr><th></th><th>已分配用户</th>
					</tr>
				</thead>
				<tbody id="matchUser">
					 <c:forEach var="matchUser" items="${matchUser}"> 
			     <tr id="user${matchUser.id}"><td><input value="${matchUser.id}" type="checkbox"/></td><td>${matchUser.name}</td></tr>
	   				 </c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script>
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
		function del() {
			var arr = "";
			$(".td :input").each(function(i) {
				if (this.checked == true) {
					var rs = this.value;
					arr = arr + rs + ",";
				}
			});
			if (arr.length == 0) {
				alert("您还没有选择要删除的对象");
			} else {
				arr = arr.substring(0, arr.length - 1);
				var rs = $("#rs").val();
				location.href = "${ctx}/admin/sec/role/delete/" + arr;
			}
		}
		function findUsers(roleid){
			location.href="${ctx}/admin/sec/role/findUsers/"+roleid;
		}
		
		
		function rightUser(btn){
		btn.disabled=true;
		var roleid=$("#roleId").val();
		var arr="";
		$("#unmatchUser :input[type=checkbox]").each(function(i){
			if(this.checked==true){
				var rs=this.value;
				arr=arr+rs+",";
			}
		});
		if(arr.length==0){
			alert("请至少选择一个用户");
			btn.disabled=false;
		}else{
			arr=arr.substring(0,arr.length-1);
			var url="${ctx}/admin/sec/role/rightToLeft/"+roleid+"/"+arr;
			$.get(url,function(data){
				   parentnode=document.getElementById("matchUser");	
				   var tr=arr.split(",");
				   for(var i=0;i<tr.length;i++){
						moveNode(parentnode,tr[i],"user");
						btn.disabled=false;
				   }
			});
		}
		
		}
		
		
		function leftUser(btn){
		btn.disabled=true;
		var roleid=$("#roleId").val();
		var arr="";
		$("#matchUser :input[type=checkbox]").each(function(i){
			if(this.checked==true){
				var rs=this.value;
				arr=arr+rs+",";
			}
		});
		if(arr.length==0){
			alert("请至少选择一个角色");
			btn.disabled=false;
		}
		else{
			arr=arr.substring(0,arr.length-1);
			var url="${ctx}/admin/sec/role/leftToRight/"+roleid+"/"+arr;
			$.get(url,function(data){
				   parentnode=document.getElementById("unmatchUser");	
				   var tr=arr.split(",");
				   for(var i=0;i<tr.length;i++){
						moveNode(parentnode,tr[i],"user");
						btn.disabled=false;
				   }
			});
		}
		
	}
		var trid=$("#roleId").val();
		if(trid!=""){
			document.getElementById(trid).style.backgroundColor="#FF6";
			document.getElementById(trid).style.background="#FF6"  ;
		}
	</script>
</body>




</html>