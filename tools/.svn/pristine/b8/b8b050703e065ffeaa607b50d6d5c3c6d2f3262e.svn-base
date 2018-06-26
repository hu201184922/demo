<%@ page contentType="text/html;charset=UTF-8"%>


<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<meta name="decorator" content="admin-popup"/>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<SCRIPT type="text/javascript">
<!--
	var data;
	function selectOne(id, name) {
		data = {id:id,name:name};
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

<div>
	<div class="col-md-12">
		<div class="panel panel-info">
		<div class="panel-heading">人员信息</div>
		<table id="contentTable"
			class="table table-condensed table-hover table-striped table-responsive">
			<thead>
				<tr>
					<th>名称</th>
					<th>性别</th>
					<th>所属部门</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${persons.content}" var="person">
					<tr>
						<td>${person.name}</td>
						<td><tags:dict itemCode="${person.sex}" dictCode="gender" type="label"/></td>
						<td>${person.org.name}</td>
						<td><a class="candidate" onclick="selectOne('${person.id }','${person.name }')" >选中</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	</div>
</div>
