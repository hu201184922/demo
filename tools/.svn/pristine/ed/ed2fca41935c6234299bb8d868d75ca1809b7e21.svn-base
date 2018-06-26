<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>用户管理</title>

</head>
<body>
	<input type="hidden" id="userId" value="${userId}" />
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success">
			<button data-dismiss="alert" class="close">×</button>
			${message}
		</div>
	</c:if>
	<div class="btn-group">
		<button id="btn" class="btn btn-default addframe"
			data-fancybox-type="iframe" href="${ctx}/admin/sec/user/create">新增</button>
		<button id="remove" class="btn btn-default">删除</button>
		<button id="setExpired" class="btn btn-default">设置过期</button>
		<button id="cancelExpired" class="btn btn-default">取消过期</button>
		<button id="ifLocked" class="btn btn-default">锁定/解锁</button>
		<button class="btn fancybox1 btn-default" onclick="updatepw()">重置密码</button>
		<div class="btn-group">
			<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
				操作 <span class="caret"></span>
			</a>
			<ul class="dropdown-menu">
				<!--     		<li><a class="fancybox2" href="#inline2" title="" onclick="modifypw()">修改密码</a></li> -->
				<!--     		<li><a>设置过期</a></li> -->
				<!--     		<li><a>取消过期</a></li> -->
				<!--     		<li><a>锁定/解锁</a></li> -->
				<!--     		<li><a class="fancybox1" href="#inline1" title="" onclick="updatepw()">重置密码</a></li> -->
				<li><a>刷新缓存</a></li>
			</ul>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body">
			<div class="row">
				<div class="col-md-10">
					<form class="form-inline" action="${ctx}/admin/sec/user">
						<div class="form-group">
							<label for="loginName">登录名:</label> <input type="text"
								name="search_LIKE_loginName" id="loginName"
								class="input-medium search-query form-control input-sm"
								value="${param.search_LIKE_loginName }">
						</div>
						<div class="form-group">
							<label for="name">用户名：</label> <input type="text" name="search_LIKE_name"
								id="name" class="input-medium search-query form-control input-sm"
								value="${param.search_LIKE_name }">
						</div>
						<div class="form-group">
							<label for="expired">是否过期：</label> <select name="search_EQ_expired"
								id="expired" class="input-medium search-query form-control input-sm">
								<c:choose>
									<c:when test="${param.search_EQ_expired eq 0 }">
										<option></option>
										<option value=0 selected>否</option>
										<option value=1>是</option>
									</c:when>
									<c:when test="${param.search_EQ_expired eq 1 }">
										<option></option>
										<option value=0>否</option>
										<option value=1 selected>是</option>
									</c:when>
									<c:otherwise>
										<option></option>
										<option value=0>否</option>
										<option value=1>是</option>
									</c:otherwise>
								</c:choose>
							</select>
						</div>

						<div class="form-group">
							<label for="locked">是否锁定：</label> <select name="search_EQ_locked"
								id="locked" class="input-medium search-query form-control input-sm">
								<c:choose>
									<c:when test="${param.search_EQ_locked eq 0 }">
										<option></option>
										<option value=0 selected>否</option>
										<option value=1>是</option>
									</c:when>
									<c:when test="${param.search_EQ_locked eq 1 }">
										<option></option>
										<option value=0>否</option>
										<option value=1 selected>是</option>
									</c:when>
									<c:otherwise>
										<option></option>
										<option value=0>否</option>
										<option value=1>是</option>
									</c:otherwise>
								</c:choose>
							</select>
						</div>

						<div class="form-group">
							<label>最近登录时间从</label><input type="text"
								name="search_GT_lastLoginedTime"
								class="input-medium search-query form_datetime form-control input-sm"
								value="${param.search_GT_lastLoginedTime }"> <label>到</label><input
								type="text" name="search_LT_lastLoginedTime"
								class="input-medium search-query form_datetime form-control input-sm"
								value="${param.search_LT_lastLoginedTime }">
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
		<div class="panel-heading">用户信息</div>
		<table id="contentTable"
			class="table table-condensed table-hover table-striped table-responsive">
			<thead>
				<tr>
					<th><input type="checkbox" name="selectAll" id="selectAll" /></th>
					<th>登录名</th>
					<th>用户名</th>
					<th>注册时间</th>
					<th>是否锁定</th>
					<th>是否过期</th>
					<th>最近登录时间</th>
					<th>锁定时间</th>
					<th>过期时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody class="td">
				<c:forEach var="user" items="${users.content}">
					<tr>
						<td><input type="checkbox" value="${user.id}" name="box"
							class="checkId" /></td>
						<td>${user.account}</td>
						<td>${user.userName}</td>
						<td><fmt:formatDate value="${user.registerDate}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><c:if test="${user.locked eq false}">否</c:if> <c:if
								test="${user.locked eq true}">是</c:if></td>
						<td><c:if test="${user.expired eq false}">否</c:if> <c:if
								test="${user.expired eq true}">是</c:if></td>
						<td><fmt:formatDate value="${user.lastLoginedTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><fmt:formatDate value="${user.lockedTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><fmt:formatDate value="${user.expiredTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><a class="btn nframe" data-fancybox-type="iframe"
							href="${ctx}/admin/sec/user/update/${user.id}">编辑</a> <a
							type="button" onclick='deleteUser(${user.id});'>删除</a> <a
							class="btn nframe" data-fancybox-type="iframe"
							href="${ctx}/admin/sec/user/modifypwd/${user.id}">修改密码</a> <a
							class="btn nframe" data-fancybox-type="iframe"
							href="${ctx}/admin/sec/user/selectRole/${user.id}">角色分配</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="collapse navbar-collapse">
		<div class="nav navbar-nav navbar-right">
			<tags:pagination page="${users}" paginationSize="5" />
		</div>
	</div>
	</div>


	<script>
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
		$(".td :input[type='checkbox']").each(function(i){
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
				location.href="${ctx}/admin/sec/user/delete/"+arr;
			}
		}
	}
	);
	
	function loadRole(userid){
		location.href="${ctx}/admin/sec/user/distributedRole/"+userid;
	};
	
	function rightRole(btn){
		btn.disabled=true;
		var userid=$("#userId").val();
		var arr="";
		$("#unmatchRole :input[type='checkbox']").each(function(i){
			if(this.checked==true){
				var rs=this.value;
				arr=arr+rs+",";
			}
		});
		if(arr.length==0){
			alert("请至少选择一个角色");
			  btn.disabled=false;
		}else{
			arr=arr.substring(0,arr.length-1);
			var url="${ctx}/admin/sec/user/rightToLeft/"+userid+"/"+arr;
			$.get(url,function(data){
				   parentnode=document.getElementById("matchRole");	
				   var tr=arr.split(",");
				   for(var i=0;i<tr.length;i++){
						moveNode(parentnode,tr[i],"role");
						btn.disabled=false;
				   }
			});
		}
		 	
	}
	
	function leftRole(btn){
		btn.disabled=true;
		var userid=$("#userId").val();
		var arr="";
		$("#matchRole :input[type='checkbox']").each(function(i){
			if(this.checked==true){
				var rs=this.value;
				arr=arr+rs+",";
			}
		});
		if(arr.length==0){
			btn.disabled=false;
			alert("请至少选择一个角色");
		}
		else{
			arr=arr.substring(0,arr.length-1);
			var url="${ctx}/admin/sec/user/leftToRight/"+userid+"/"+arr;
			$.get(url,function(data){
				   parentnode=document.getElementById("unmatchRole");	
				   var tr=arr.split(",");
				   for(var i=0;i<tr.length;i++){
						moveNode(parentnode,tr[i],"role");
				   }
				   btn.disabled=false;
			});
		}
		
		
	}
	var trid=$("#userId").val();
	if(trid!=""){
		document.getElementById(trid).style.backgroundColor="#FF6";
		document.getElementById(trid).style.background="#FF6"  ;
	}

	function updatepw(){
		var arr="";
		$(".td :input[type='checkbox']").each(function(i){
			if(this.checked==true){
				var rs=this.value;
				arr=arr+rs+",";
			}
			
		});
		if(arr.length==0){
			alert("您还没有选择要操作的对象");
		}else{
			var r=window.confirm("确定要重置密码？")
			if(r){
				arr=arr.substring(0,arr.length-1);
				location.href="${ctx}/admin/sec/user/repassword/"+arr;
			}
		}
	}

	$(document).ready(function() {
		$(".nframe").fancybox({
			fitToView	: false,
			width		: '80%',
			height		: '80%',
			autoSize	: false,
			closeClick	: false,
			openEffect	: 'none',
			closeEffect	: 'none'
		});
		$(".addframe").fancybox({
			fitToView	: false,
			width		: '80%',
			height		: '80%',
			autoSize	: false,
			closeClick	: false,
			openEffect	: 'none',
			closeEffect	: 'none',
			afterClose	: function(){
				parent.location.reload();
			}
		});
	});
	
	function deleteUser(id) {
		var r = window.confirm("确定要删除吗？");
		if(r){
			window.location.href="${ctx}/admin/sec/user/delete/" + id;
		}
	}
	
	$(document).ready(function(){
		$(".form_datetime").datetimepicker({
			format : "yyyy-mm-dd hh:ii:ss",
			pickerPosition: "bottom-left",
			autoclose : true,
			todayBtn : true
		});
	});
	
	$("#setExpired").click(function(){
		var arr="";
		$(".td :input[type='checkbox']").each(function(i){
			if(this.checked==true){
				var rs=this.value;
				arr=arr+rs+",";
			}
			
		});
		if(arr.length==0){
			alert("您还没有选择要操作的对象");
		}else{
			var r=window.confirm("确定要设置过期？")
			if(r){
				arr=arr.substring(0,arr.length-1);
				location.href="${ctx}/admin/sec/user/setExpired/"+arr;
			}
		}
	});
	
	$("#cancelExpired").click(function(){
		var arr="";
		$(".td :input[type='checkbox']").each(function(i){
			if(this.checked==true){
				var rs=this.value;
				arr=arr+rs+",";
			}
			
		});
		if(arr.length==0){
			alert("您还没有选择要操作的对象");
		}else{
			var r=window.confirm("确定要取消过期？")
			if(r){
				arr=arr.substring(0,arr.length-1);
				location.href="${ctx}/admin/sec/user/cancelExpired/"+arr;
			}
		}
	});
	
	$("#ifLocked").click(function(){
		var arr="";
		$(".td :input[type='checkbox']").each(function(i){
			if(this.checked==true){
				var rs=this.value;
				arr=arr+rs+",";
			}
			
		});
		if(arr.length==0){
			alert("您还没有选择要操作的对象");
		}else{
			var r=window.confirm("确定要执行本次操作？")
			if(r){
				arr=arr.substring(0,arr.length-1);
				location.href="${ctx}/admin/sec/user/ifLocked/"+arr;
			}
		}
	});
	</script>
</body>
</html>
