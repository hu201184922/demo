<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link href="${ctx }/static/lib/jdp/jquery-easyui/css/easyui.css" rel="stylesheet">
<script type="text/javascript" src="${ctx }/static/lib/jdp/jquery-easyui/jquery.easyui.min.js"></script> 
<%-- 
<link href="${ctx }/static/lib/jdp/jquery-treegrid/css/jquery.treegrid.css" rel="stylesheet">
<script type="text/javascript" src="${ctx }/static/lib/jdp/jquery-treegrid/jquery.treegrid.js"></script>
<script type="text/javascript" src="${ctx }/static/lib/jdp/jquery-treegrid/jquery.treegrid.bootstrap3.js"></script> --%>
<div>
	<input type="hidden" id="initalResourceIds" value="${resourceIds}" /> 
	<input type="hidden" id="roleId" value="${roleId}" />
	<div class="row" style="margin-bottom: 10px">
		<form class="form-inline" action="#" role="form">
			<div class="form-group col-md-2">
				<input id="searchRole" type='text' class="form-control input-sm col-md-2" placeholder="角色名称"/>
			</div>
			<div class="col-md-3">
				<a class="btn btn-default" onclick="searchRole()">查找</a>
			</div>
		 </form>
	</div>
	<div>
		<div class="col-md-2">
			<div class="row" style="overflow-y:auto;height: 500px;">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-toggle="collapse"
								data-parent="#accordion" href="#">
								角色列表</a>
						</h4>
					</div>
					<div id="permission-roles" >
						<div class="list-group" id="role-list-group">
							<c:forEach items="${roles}" var="role">
								<a href="javascript:void(0)" id="list-group-item_${role.id}" onclick="bindRes(${role.id})"
									class="list-group-item "> ${role.name} </a>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	
		<div class="col-md-10">
			<div class="panel panel-info">
				<div class="">
					<!-- easy treegrid -->
					 <table id="contentTable" class="easyui-treegrid" url="${ctx }/admin/sec/permission/get-tree-grid"
			            rownumbers="false" idField="id" treeField="name" method="get">
						<thead>
								<tr>
									<th field="name" width="200" >资源分类</th>
									<th field="urlResources" width="350" align="left" formatter="processURLResources">url资源</th>
									<th width="340" data-options="field:'elementResources',formatter:processElementResources">元素资源</th>
								</tr>
							</thead>
					</table>
				</div>
				<div class="panel-footer">
					<div class="col-md-offset-2">
						<input onclick="save()" id="submit_btn" class="btn btn-default"
							type="submit" value="保存" /> 
					</div>
				</div>
			</div>
			
		</div>
	</div>
</div>

<SCRIPT type="text/javascript">
<!--
//begin tree grid
var initalResourceIds = '${initalResourceIds}'.split(",");
alert(initalResourceIds);
function processURLResources(value){

    if (value){
        return processResourcesByType(value,'U');
    } else {
        return '';
    }
}
function processElementResources(value){

    if (value){
        return processResourcesByType(value,'E');
    } else {
        return '';
    }
}
function processResourcesByType(value,type){
	var resources = '';
	for(var i=0; i< value.length;i++){
		if(value[i].resType == type){
			var checkedOrNot = (initalResourceIds.indexOf(value[i].id) >=0 )?"checked":"";
			resources +='<div class="col-md-3" style="overflow: hidden;text-overflow:ellipsis;padding:2px;" title="'+value[i].name+'"><input class="candidateResource" type="checkbox" value="' 
							+ value[i].id + '" '+ checkedOrNot +'/>' + value[i].name 
						+"</div>";
		}
	} 
	
	return resources;
}

//end tree grid

function searchRole(){
	var roleName = $("#searchRole").val();
	$.ajax({
		url			: "${ctx}/admin/sec/permission/search-role",
		data		: {search_LIKE_name : roleName },
		type		: 'get',
		dataType	: 'json',
		success 	: function(data){
			var roleList = '';
			for(var i=0;i< data.length;i++){
				roleList +='<a href="javascript:void(0)" id="list-group-item_'+data[i].id+
					'" onclick="bindRes('+data[i].id+')" class="list-group-item "> '+data[i].name+' </a>';
			}
			$("#role-list-group").html(roleList);
			$("#roleId").val('');
			$(".candidateResource").attr("checked",false);
		}
	});
}

function bindRes(roleId){
	$.ajax({
		url			: "${ctx}/admin/sec/permission/role-resource/"+roleId,
		type		: "get",
		dataType	: "json",
		success		: function(data){
			$("#roleId").val(roleId);
			$("#permission-roles .list-group-item").removeClass("active");
			$("#permission-roles #list-group-item_"+roleId).addClass("active");
			$(".candidateResource").each(function(){
				this.checked = data.indexOf(this.value) >= 0;
			});
		}
	});
}

	function save(){
		var roleId=$("#roleId").val();
		if(roleId==""){
			alert("请选择角色");
			return false;
		}
		var arr=[];
		$(".candidateResource:input[type='checkbox']:checked").each(function(i){
			if(this.checked==true){
				arr.push(this.value);
			}
		});
		
		if(arr.length == 0){
			if(!confirm("您已取消所有的资源，确定要保存吗")){
				return;
			}
		}
		
		$.ajax({
			url			: "${ctx}/admin/sec/permission/updateResource/"+roleId,
			type		: "post",
			data		: { resourceIds : arr.join(",") },
			success		: function(msg){
				alert("保存成功");
			}
		});
	}
//-->
</SCRIPT>