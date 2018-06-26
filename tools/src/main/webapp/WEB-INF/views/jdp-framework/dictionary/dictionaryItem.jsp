
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<meta name="decorator" content="nodecorate" />
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<title>首选项定义</title>

<script type="text/javascript">
function deleteDictId(id) {
	var r = window.confirm("确定要删除吗？");
	if (r) {
		$.ajax({
			type : "post",
			url : "${ctx}/admin/dict/sys/deleteDictItemOne/" + id,
			success : function(msg) {
				var id = $("#dict_id").val();
				loadDictItem(id);
			}
		});
	}
	;
};
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
		<div class="panel-heading">字典项</div>
		<table id="contentTable"
			class="table table-condensed table-hover table-striped table-responsive">
			<thead>
				<tr>
					<th><input type="checkbox" name="selectAll" id="selectAll" /></th>
					<th>名称</th>
					<th>编码</th>
					<th>备注</th>
					<th>排序号</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody class="td">
				<c:forEach items="${dictionaryItem}" var="dictionaryItem">
					<tr>
						<td><input type="checkbox" value="${dictionaryItem.id}"
							name="box" /></td>
						<td>${dictionaryItem.name}</td>
						<td>${dictionaryItem.code}</td>
						<td>${dictionaryItem.descript}</td>
						<td>${dictionaryItem.sortIndex}</td>
						<td><a data-fancybox-type="iframe" class="a"
							href="${ctx}/admin/dict/sys/updateDictItempage/${dictionaryItem.id}">编辑</a>
							<a onclick="deleteDictId(${dictionaryItem.id})">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

</body>
</html>