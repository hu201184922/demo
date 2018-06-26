<%@ page contentType="text/html;charset=UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<meta name="decorator" content="admin-popup"/>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<SCRIPT type="text/javascript">
<!--

	function selectOne(id,name) {
	 	 var values=document.getElementById("selectedValue").options;
	 	 for(var i=0;i<values.length;i++){
		 	if(values[i].value==id){
				return false;
		 	}
	 	 }
		 $("#selectedValue").append('<option value="'+id+'">'+name+'</option>');
	};

	var data;
	function complete() {
		var options=document.getElementById("selectedValue").options;
		  data = [];
		 
		 for(var i=0;i<options.length;i++){
		 	var item = {
		 		id	 :	options[i].value,
		 		name :  options[i].text
		 	};
			 data.push({
		 		id	 :	options[i].value,
		 		name :  options[i].text
		 	});
		 }
		parent.$.fancybox.close();
		return false;
	}
	
	function getValue(){
		return data;
	}

	$(document).ready(function() {
	});
//-->
</SCRIPT>

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
							<label for="org.name">所属部门：</label> <input type="text"
								id="org.name" name="search_LIKE_org.name" class="form-control input-sm"
								value="${param.search_LIKE_org.name}">
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

<div class="panel panel panel-info">
	<div class="panel-heading">人员选择</div>
	<div class="panel-body">
	<div class="col-md-8">
		<table id="contentTable"
			class="table table-condensed table-hover table-striped table-responsive">
			<thead>
				<tr>
					<th>名称</th>
					<th>性别</th>
					<th>所属部门</th>
					<th>所属岗位</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${persons.content}" var="person">
					<tr>
						<td>${person.name}</td>
						<td><tags:dict itemCode="${person.sex}" dictCode="gender" type="label"/></td>
						<td>${person.org.name}</td>
						<td>${person.post.name}</td>
						<td><a class="candidate" onclick="selectOne('${person.id }','${person.name }')" >选中</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class=" col-md-offset-6">
			<tags:pagination page="${persons}" paginationSize="5" />
		</div>
	</div>
	<div class="col-md-4">
		<div class="row">
		 	<select class="col-md-6" multiple="multiple" size="8" id="selectedValue"
				ondblclick="this.removeChild(this.options[this.selectedIndex])">			
			</select>
		</div>
	</div>
	</div>
	<!-- footer -->
			<div class="panel-footer">
				<div class=" col-md-offset-6">
					<input class="btn btn-default" type="button" value="确定" onclick="complete()"/>
				</div>
			</div>
</div>
