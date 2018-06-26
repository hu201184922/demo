<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<meta name="decorator" content="admin-popup">
<c:if test="${not empty message}">
	<div id="message" class="alert alert-success">
		<button data-dismiss="alert" class="close">×</button>
		${message}
	</div>
</c:if>
<div>
	<input type="hidden" id="initalname" name="initalname" /> <input
		type="hidden" id="dict_id" value="${id}">
	<form id="inputForm" action="${ctx}/admin/dict/user/createDictItem/${id}"
		method="post" class="form-horizontal">
		<div class="panel panel panel-info">
			<!-- head -->
			<div class="panel-heading">字典项信息</div>
			<div class="panel-body">
				<div class="form-group">
					<label for="dictionaryItem_name" class="col-md-2 control-label">名称</label>
					<div class="col-md-4">
						<input type="text" id="dictionaryItem_name" name="name"
							class="form-control input-sm required">
					</div>
				</div>
				<div class="form-group">
					<label for="dictionaryItem_code" class="col-md-2 control-label">编码</label>
					<div class="col-md-4">
						<input type="text" id="dictionaryItem_code" name="code"
							class="form-control input-sm required">
					</div>
				</div>
				<div class="form-group">
					<label for="dictionaryItem_sortindex"
						class="col-md-2 control-label">排序号</label>
					<div class="col-md-4">
						<input type="text" id="dictionaryItem_sortindex" name="sortIndex"
							class="form-control input-sm">
					</div>
				</div>

				<div class="form-group">
					<label for="dictionaryItem_descript" class="col-md-2 control-label">备注</label>
					<div class="col-md-4">
						<textarea id="dictionaryItem_descript" name="descript"
							class="form-control input-sm"></textarea>
					</div>
				</div>
			</div>
			<!-- footer -->
			<div class="panel-footer">
				<div class=" col-md-offset-2">
					<input id="submit_btn" class="btn btn-default" type="submit"
						value="保存并新增" /> <input id="submit_btn" class="btn btn-default"
						type="button" value="保存并关闭" onclick="saveDictionaryItem()" /> <input
						id="cancel_btn" type="button" class="btn btn-default" value="关闭"
						onclick="closeChange()" />
				</div>
			</div>

		</div>
	</form>
</div>
<script>
	$(document).ready(function() {
		//聚焦第一个输入框
		$("#dictionaryItem_name").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate({
			rules : {
				code : {
					required : true,
					remote : {
						url : "${ctx}/admin/dict/user/checkItem",
						type : "post",
						dataType : "json",
						data : {
							code : $("#code").val(),
							initalname : "${dictionaryItem.code}",
							dictId : "${id}"
						}
					}
				},
				sortIndex : {
					digits : true
				}
			}
		});
	});

	function saveDictionaryItem() {
		var validator = $("#inputForm").validate();
		if (validator.form()) {//判断加入所有校验都通过后再做ajax提交；
			$.ajax({
				type : "post",
				data : $("#inputForm").serialize(),
				url : "${ctx}/admin/dict/user/createDictItem/${id}",
				success : function(msg) {
					parent.$.fancybox.close();
				}
			});
		}
	};

	function closeChange() {
		parent.$.fancybox.close();
	};
</script>
