<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>知识库目录维护</title>
<script type="text/javascript">
	var setting = {
		data : {
			simpleData : {
				enable : true
			}
		},
		view : {
			showIcon : true,
			showLine : true
		},
		callback : {
			onClick : onClick
		}
	};

	var tree;
	$(function() {
		var zNodes = ${treeJson};
		tree = $("#catalogTree").zTree(setting, zNodes); 
		var list = Ace.decode("${u:serialize(list)}");
		$('#treePager').pager(list, 5, $('#catalogTable').view());

	});

	function onClick(e, treeId, treeNode) {
		$("#fatherId").val(treeNode.id);
		$("#fatherName").val(treeNode.name);
		$("#catalogUrl").val(treeNode.curl);
		$.getJSON(request.getContextPath() + '/admin/catalog/getCatalogList', {
			pId : treeNode.id,
			pName : treeNode.name,
			ajaxType : true
		}, function(data) {
			$("#addButton").show();
			$('#treePager').pager().setJSON(data);
		});
	}

	var dia = null;

	function add() {
		$("#sortName").val("");
		$("#remark").val("");
		$("#id").val("");
		dia = $.dialog({
			title : "新增分类",
			content : $('#addcontent')[0]
		});
	}

	function modifySort(o1, o2, o3) {
		$("#id").val(o1);
		$("#sortName").val(o2);
		$("#remark").val(o3);
		var updateSortNode = tree.getNodeByParam("id", o1, null);
		if (updateSortNode.level == 1) {
			$.msgbox({
				time : 2000,
				msg : "系统数据,不能编辑!",
				icon : "success"
			});
			return;
		}

		dia = $.dialog({
			title : "修改分类",
			content : $('#addcontent')[0]
		});
	}

	function save(o) {
		var id = $("#id").val();
		var fatherId = $("#fatherId").val();
		var sortName = $("#sortName").val();
		var fatherName = $("#fatherName").val();
		var catalogUrl=$("#catalogUrl").val();
		var remark = $("#remark").val();
		var title = "";
		var op = "";
		if (sortName.trim() == "") {
			$.alert("名称必填！");
			return;
		}
		//编辑
		if (id != "") {
			op = "modify";
			title = "编辑成功！";
		} else {
			op = "add";
			title = "新增成功！";
		}
		$.post(request.getContextPath() + '/admin/catalog/opCatalog', {
			id : id,
			fatherId : fatherId,
			fatherName : fatherName,
			sortName : sortName,
			remark : remark,
			catalogUrl : catalogUrl,
			ajaxType : true
		}, function(data) {
			dia.close();
			var node;
			node = tree.getNodeByParam("id", fatherId, null);
			if (op == "modify") {
				var updateSortNode = tree.getNodeByParam("id", id, null);
				updateSortNode.name = sortName;
				tree.updateNode(updateSortNode, false);
			} else {
				var newNode = {
					id : data.catalog.catalogId,
					pId : data.catalog.pCatalogId,
					name : data.catalog.catalogName,
					
				};
				tree.addNodes(node, newNode, false);
			}
			$('#treePager').pager().setJSON(data.list);
			$.msgbox({
				time : 1000,
				msg : title,
				icon : "success"
			});
		});
		return;
	}

	function deleted(o) {
		var fatherId = $("#fatherId").val();
		var fatherName = $("#fatherName").val();
		var deleteSortNode = tree.getNodeByParam("id", o, null);
		if (deleteSortNode.children != null) {
			$.msgbox({
				time : 2000,
				msg : "不是子节点,不能删除!",
				icon : "success"
			});
			return;
		}
		if (deleteSortNode.level == 1) {
			$.msgbox({
				time : 2000,
				msg : "系统数据,不能删除!",
				icon : "success"
			});
			return;
		}
		$.confirm("确定要删除吗?", function() {
			$.post(request.getContextPath() + '/admin/catalog/deleteCatalog', {
				id : o,
				fatherId : fatherId,
				fatherName : fatherName,
				ajaxType : true
			}, function(data) {
				$("#id").val("");
				if (data.sortMessage == "ok") {
					tree.removeNode(deleteSortNode, false);
					$('#treePager').pager().setJSON(data.list);
					$.msgbox({
						time : 2000,
						msg : "删除成功!",
						icon : "success"
					});
				} else {
					$.msgbox({
						time : 2000,
						msg : "不能删除!",
						icon : "success"
					});
				}
			});
			return;
		});
	}
</script>
</head>

<body>
	<div class="business_title">
		目录维护
	</div>

	<div id="indexLists" class="indexManageLists" style="width: 100%;">
		<div id="left" class="indexManagelist_l">
			<ul id="catalogTree" class="ztree"
				style="height: 200px; border: 0px; overflow: scroll">
			</ul>
		</div>
		<div class="library_right_warp"
			style="width: 74%; margin: 15px 0 0 13px; float: left; clear: none;">
			<div class="library_right_warp_table">
				<div class="knowledgeform">
					<input type="button" value="新增" id="addButton"
						onclick="javascript:add();" class="searchBtn"
						style="display: none;" /> <input type="hidden" name="fatherId"
						id="fatherId" value="1" />
						<input type="hidden" name="fatherUrl"
						id="fatherUrl" value="" />
					<!-- 编辑节点的ID -->
					<input type="hidden" name="nodeId" id="id" value="" />
				</div>
				<table id="catalogTable" cellpadding="0" cellspacing="0"
					id="knowledgeLists" class="t-list table">
					<tr>
						<th width="20%">目录名称</th>
						<th width="20%">父目录名称</th>
						 <th>目URL录</th>
						<th width="20%">备注</th>
						<th width="10%">操作</th>
					</tr>
					<tr class="tb_tr_content template" name="default">
						<td>{catalogName}</td>
						<td>{parentName}</td>
						 <td>{catalogUrl}</td>
						<td>{memo}</td>
						<td><a
							href="javascript:modifySort({catalogId},'{catalogName}','{memo}');">编辑</a>
							<a href="javascript:deleted({catalogId});">删除</a></td>
					</tr>
				</table>
				<div id="treePager"></div>
			</div>
		</div>
	</div>

	<!-- 新增弹出框 -->
	<div id="addcontent" style="display: none; line-height: 30px;">
		<table>
			<tr>
				<td class="text_right">分类名称：</td>
				<td><input type="text" class="text" name="sortName"
					id="sortName" />
				</td>
			</tr>
			<tr>
				<td class="text_right">父节点名称：</td>
				<td><input type="text" class="text" name="fatherName"
					id="fatherName" readonly="readonly" value="总目录" />
				</td>
			</tr>
			<tr>
				<td class="text_right">路径：</td>
				<td><input type="text" class="text" name="catalogUrl"
					id="catalogUrl"  value="" />
				</td>
			</tr>
			<tr>
				<td class="text_right">备注：</td>
				<td><input type="text" style="width: 200px; height: 40px;"
					name="remark" id="remark" />
				</td>
			</tr>
			<tr>
				<td colspan="2" height="10px"></td>
			</tr>
			<tr align="center">
				<td colspan="2"><input type="button" value="保 存"
					onclick="javascript:save();" />&nbsp;&nbsp;<input type="button"
					class=" close" value="取 消" />
				</td>
			</tr>
		</table>
	</div>

</body>
</html>
