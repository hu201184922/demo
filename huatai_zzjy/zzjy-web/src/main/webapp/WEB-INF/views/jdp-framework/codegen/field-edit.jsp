<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>字段集管理</title>
<meta name="decorator" content="admin-popup">
</head>

<body>
	<form id="inputForm" action="${ctx}/admin/codegen/fieldset/${fieldSetId}/field/${action}"
		method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${field.id}" />
		<input type="hidden" name="fieldSet.id" value="${field.fieldSet.id}" />
		<div class="panel panel panel-info">
		<!-- head -->
			<div class="panel-heading">字段集管理</div>
			<div class="panel-body">
				<div class="form-group">
					<label for="fieldname" class="col-md-2 control-label ">名称:</label>
					<div class="col-md-3">
						<input type="text" id="fieldname" name="name" class="form-control input-sm required" value="${field.name}">
					</div>
					<label for="label" class="col-md-2 control-label">标签:</label>
					<div class="col-md-3">
						<input type="text" id="label" name="label" class="form-control input-sm required" value="${field.label}">
					</div>
				</div>
				<div class="form-group">
					<label for="control" class="col-md-2 control-label">控件类型:</label>
					<div class="col-md-3">
						<tags:dict itemCode="${field.control}" dictCode="control" type="select"
							id="control" name="control" cssClass="form-control input-sm required" />
					</div>
					<label for="dataType" class="col-md-2 control-label">数据类型:</label>
					<div class="col-md-3">
						<tags:dict itemCode="${field.dataType}" dictCode="dataType" type="select"
							id="dataType" name="dataType" cssClass="form-control input-sm required" />
					</div>
				</div>
				<div class="form-group">
					<label for="sortable" class="col-md-2 control-label">支持排序:</label>
					<div class="col-md-3">
						<c:choose>
							<c:when test="${field.sortable}">
							<input type="radio" id="issortable" name="sortable" value="1" checked/><label for="issortable">是</label>
							<input type="radio" id="nosortable" name="sortable" value="0" /><label for="nosortable">否</label>
							</c:when>
							<c:otherwise>
							<input type="radio" id="isSort" name="sortable" value="1"/><label for="isSort">是</label>
							<input type="radio" id="noSort" name="sortable" value="0"  checked/><label for="noSort">否</label>
							</c:otherwise>
						</c:choose>
					</div>
					<label for="sortType" class="col-md-2 control-label">排序类型:</label>
					<div class="col-md-3">
						
						<tags:dict itemCode="${field.sortType}" dictCode="sortType" type="select"
							id="sortType" name="sortType" cssClass="form-control input-sm" />
					</div>
				</div>
				<div class="form-group">
					<label for="visible" class="col-md-2 control-label">是否可见:</label>
					<div class="col-md-3">
						<c:choose>
							<c:when test="${field.visible}">
							<input type="radio" id="isvisible" name="visible" value="1" checked/><label for="isvisible">是</label>
							<input type="radio" id="novisible" name="visible" value="0" /><label for="novisible">否</label>
							</c:when>
							<c:otherwise>
							<input type="radio" id="Isvisible" name="visible" value="1"/><label for="Isvisible">是</label>
							<input type="radio" id="Novisible" name="visible" value="0"  checked/><label for="Novisible">否</label>
							</c:otherwise>
						</c:choose>
					</div>
					<label for="readOnly" class="col-md-2 control-label">只读:</label>
					<div class="col-md-3">
						<c:choose>
							<c:when test="${field.readOnly}">
							<input type="radio" id="IsreadOnly"" name="readOnly" value="1" checked/><label for="IsreadOnly">是</label>
							<input type="radio" id="Noreadonly" name="readOnly" value="0" /><label for="Noreadonly">否</label>
							</c:when>
							<c:otherwise>
							<input type="radio" id="isreadOnly" name="readOnly" value="1"/><label for="isreadOnly">是</label>
							<input type="radio" id="noreadonly" name="readOnly" value="0"  checked/><label for="noreadonly">否</label>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="form-group">
					<label for="required" class="col-md-2 control-label">必填:</label>
					<div class="col-md-3">
						<c:choose>
							<c:when test="${field.required}">
							<input type="radio" id="Isrequired" name="required" value="1" checked/><label for="Isrequired">是</label>
							<input type="radio" id="Norequired" name="required" value="0" /><label for="Norequired">否</label>
							</c:when>
							<c:otherwise>
							<input type="radio" id="isrequired" name="required" value="1"/><label for="isrequired">是</label>
							<input type="radio" id="norequired" name="required" value="0"  checked/><label for="norequired">否</label>
							</c:otherwise>
						</c:choose>
					</div>
					<label for="persistent" class="col-md-2 control-label">持久化:</label>
					<div class="col-md-3">
						<c:choose>
							<c:when test="${field.persistent}">
							<input type="radio" id="ispersistent" name="persistent" value="1" checked/><label for="ispersistent">是</label>
							<input type="radio" id="nopersistent" name="persistent" value="0" /><label for="nopersistent">否</label>
							</c:when>
							<c:otherwise>
							<input type="radio" id="Ispersistent" name="persistent" value="1"/><label for="Ispersistent">是</label>
							<input type="radio" id="Nopersistent" name="persistent" value="0"  checked/><label for="Nopersistent">否</label>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="form-group">
					<label for="jsonIgnore" class="col-md-2 control-label">JsonIgnore:</label>
					<div class="col-md-3">
						<c:choose>
							<c:when test="${field.jsonIgnore}">
							<input type="radio" id="isjsonIgnore" name="jsonIgnore" value="1" checked/><label for="isjsonIgnore">是</label>
							<input type="radio" id="nojsonIgnore" name="jsonIgnore" value="0" /><label for="nojsonIgnore">否</label>
							</c:when>
							<c:otherwise>
							<input type="radio" id="IsjsonIgnore" name="jsonIgnore" value="1"/><label for="IsjsonIgnore">是</label>
							<input type="radio" id="NojsonIgnore" name="jsonIgnore" value="0"  checked/><label for="NojsonIgnore">否</label>
							</c:otherwise>
						</c:choose>
					</div>
					<label for="future" class="col-md-2 control-label">future:</label>
					<div class="col-md-3">
						<input type="text" id="future" name="future" class="form-control input-sm" value="${field.future}">
					</div>
				</div>
				<div class="form-group">
					<label for="maxLength" class="col-md-2 control-label">最大长度:</label>
					<div class="col-md-3">
						<input type="text" id="maxLength" name="maxLength" class="form-control input-sm" value="${field.maxLength}">
					</div>
					<label for="minLength" class="col-md-2 control-label">最小长度:</label>
					<div class="col-md-3">
						<input type="text" id="minLength" name="minLength" class="form-control input-sm" value="${field.minLength}">
					</div>
				</div>
				<div class="form-group">
					<label for="maxValue" class="col-md-2 control-label">最大值:</label>
					<div class="col-md-3">
						<input type="text" id="maxValue" name="maxValue" class="form-control input-sm" value="${field.maxValue}">
					</div>
					<label for="pattern" class="col-md-2 control-label">正则表达式:</label>
					<div class="col-md-3">
						<input type="text" id="pattern" name="pattern" class="form-control input-sm" value="${field.pattern}">
					</div>
				</div>
				<div class="form-group">
					<label for="sortIndex" class="col-md-2 control-label">排序号:</label>
					<div class="col-md-3">
						<input type="text" id="sortIndex" name="sortIndex" class="form-control input-sm" value="${field.sortIndex}">
						<span id="sortIndexTip"></span>
					</div>
					<label for="dropDownType" class="col-md-2 control-label">下拉选择类型:</label>
					<div class="col-md-3">
						<input type="text" id="dropDownType" name="dropDownType" class="form-control input-sm" value="${field.dropDownType}">
					</div>
				</div>
				<div class="form-group">
					<label for="dropDownMeta" class="col-md-2 control-label">下拉选择数据:</label>
					<div class="col-md-3">
						<input type="text" id="dropDownMeta" name="dropDownMeta" class="form-control input-sm" value="${field.dropDownMeta}">
					</div>
					<label for="dropDownScript" class="col-md-2 control-label">下拉选择脚本:</label>
					<div class="col-md-3">
						<input type="text" id="dropDownScript" name="dropDownScript" class="form-control input-sm" value="${field.dropDownScript}">
					</div>
				</div>
				<div class="form-group">
					<label for="comment" class="col-md-2 control-label">注释:</label>
					<div class="col-md-3">
						<textarea id="comment" name="comment" class="form-control input-sm">${field.comment}</textarea>
					</div>
				</div>
			</div>
				<!-- footer -->
				<div class="panel-footer">
					<div class=" col-md-offset-2">
						<input id="submit_btn" class="btn btn-default" type="button" value="保存并新增" onclick="savemore()"/>
						<input id="submit_btn" class="btn btn-default" type="submit" value="保存并返回" />
						<input id="cancel_btn" type="button" class="btn btn-default" value="返回" onclick="window.location.href='${ctx}/admin/codegen/fieldset/${fieldSetId}/field'"/>
					</div>
				</div>
		</div>
	</form>
	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#name").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules:{
					name:{
						remote : {
							url : "${ctx}/admin/codegen/fieldset/checkItem",
							type : "post",
							dataType : "json",
							data : {
								name  : document.getElementsByName("name").value,
								initalname : "${field.name}",
								fieldSetId : "${fieldSetId}"
								}
							}
					},
					sortIndex : {
						digits : true
					},
					maxValue: {
						digits : true
					},
					maxLength: {
						digits : true
					},
					minLength: {
						digits : true
					},
					future: {
						range:[0,9]
					},
				}
			});
		});
	
		function savemore(){
			var validator = $("#inputForm").validate();
			if(validator.form()) {//判断加入所有校验都通过后再做ajax提交；
				inputForm.action="${ctx}/admin/codegen/fieldset/${fieldSetId}/field/${action}/more" ;
				inputForm.submit();
			}
		};
		
	</script>
</body>
</html>
