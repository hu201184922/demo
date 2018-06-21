<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<meta name="decorator" content="admin-popup">
<html>
<head>
<title>新增人员</title>
</head>
<body>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success">
			<button data-dismiss="alert" class="close">×</button>${message}
		</div>
			
	</c:if>
	 <form id="form" action="${ctx}/admin/party/org/${action}" method="post" class="form-horizontal">
	<div class="panel panel-info">
  		<div class="panel-heading">完善人员信息</div>
  		<div class="panel-body">		
		<div>
			<input type="hidden" name="id" value="${person.id}"> 
			<input type="hidden" name="sexinital" value="${person.sex}" id="sexinital">
		</div>
			<div class="form-group">
				<label for="inputName" class="col-md-2 control-label">姓名：</label>
					<div class="col-sm-3">
						<input type="text" class="required form-control input-sm " id="inputName" name="name"
								value="${person.name}">
					</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">性別：</label>
					<div class="col-sm-3">
						 <input type="radio" id="male"/>&nbsp;&nbsp;<label for="male">男</label> &nbsp;&nbsp;&nbsp;<input type="radio" id="female"/>&nbsp;&nbsp;<lable for="female">女</lable>
					</div>
			</div>
			<div class="form-group">
				<label for="inputCode" class="col-md-2 control-label">编号：</label>
					<div class="col-sm-3">
						<input type="text" class="form-control input-sm required" id="inputCode" name="code"
								value="${person.code}">
					</div>
			</div>
			<div class="form-group">
				<label for="inputBirthyDate" class="col-md-2 control-label">出生日期：</label>
					<div class="col-sm-3">
						<div class="input-append date form_datetime">
								<input type="text" class="form-control input-sm required" id="inputBirthyDate" readonly name="birthday" value="<fmt:formatDate value="${person.birthday}" pattern="yyyy-MM-dd"/>"> <span
									class="add-on"><i class="icon-th"></i></span>
						</div>
					</div>
			</div>
			<div class="form-group">
				<label for="inputName" class="col-md-2 control-label">职位：</label>
					<div class="col-sm-3">
						<div class="input-append date form_datetime">
								<input type="text"  class="form-control input-sm" id="inputName">
							</div>
					</div>
			</div>
			<div class="form-group">
				<label for="inputDegree" class="col-md-2 control-label">学历：</label>
					<div class="col-sm-3">
						<div class="input-append date form_datetime">
								<input type="text" class="form-control input-sm" id="inputDegree">
							</div>
					</div>
			</div>
			<div class="form-group">
				<label for="org" class="col-md-2 control-label">部门：</label>
					<div class="col-sm-3">
						<div class="input-append date form_datetime">
								<select name="organization" class="form-control input-sm required" id="org">
							<option></option>
							<c:forEach var="org" items="${orgs}">
							<option value="${org.id}">${org.name}</option>
							</c:forEach>
							</select>
							</div>
					</div>
			</div>
			<div class="form-group">
				<label for="inputPhone" class="col-md-2 control-label">电话：</label>
					<div class="col-sm-3">
						<div class="input-append date form_datetime">
								<input type="text" class="form-control input-sm" id="inputPhone" name="phone"
								value="${person.phone}">
							</div>
					</div>
			</div>
			<div class="form-group">
				<label for="inputEmail" class="col-md-2 control-label">邮箱：</label>
					<div class="col-sm-3">
						<div class="input-append date form_datetime">
								<input type="text" class="form-control input-sm" id="inputEmail" name="email"
								value="${person.email}">
							</div>
					</div>
			</div>
			<div class="form-group">
				<label for="inputEntryDate" class="col-md-2 control-label">入职时间：</label>
					<div class="col-sm-3">
						<div class="input-append date form_datetime">
								<input type="text" class="form-control input-sm" id="inputEntryDate" readonly name="entryDate" value="<fmt:formatDate value="${person.entryDate}" pattern="yyyy-MM-dd"/>" format="yyyy-mm-dd"> <span
									class="add-on"><i class="icon-th"></i></span>
							</div>
					</div>
			</div>
			<div class="form-group">
				<label for="inputMobile" class="col-md-2 control-label">手机：</label>
					<div class="col-sm-3">
						<div class="input-append date form_datetime">
								<input type="text" class="form-control input-sm" id="inputMobile" name="mobile" 
								value="${person.mobile}">
							</div>
					</div>
			</div>
			<div class="form-group">
				<label for="inputDescipt" class="col-md-2 control-label">备注：</label>
					<div class="col-sm-3">
						<div class="input-append date form_datetime">
								<textarea rows="3" class="form-control input-sm" id="inputDescipt" name="descript">${person.descript}</textarea>
							</div>
					</div>
			</div>	
  		</div>
  		<div class="panel-footer"><div class=" col-md-offset-2"><input id="submit_btn" class="btn btn-default" type="submit"
							value="提交" />
						<input id="cancel_btn" class="btn btn-default" type="button" value="返回" onclick="history.back()"/></div>
		</div>
	</div>
	</form>
	<script type="text/javascript">
	$(".form_datetime").datetimepicker({
		format : "yyyy-mm-dd",
		pickerPosition: "bottom-left",
		autoclose : true,
		todayBtn : true
		
	});
	jQuery.validator.addMethod("ismobile", function(value, element) {   
	    var tel = /^[0-9]{11}$/;
	    return this.optional(element) || (tel.test(value));
	}, "请正确填写手机号码");
	$(document).ready(function(){
		var sex = $("#sexinital").val();
		if (sex == 'man') {
			var check = document.getElementById("man");
			check.checked = true;
		} else if (sex == 'woman') {
			var check = document.getElementById("woman");
			check.checked = true;
		}
		var value="${person.org.id}";
		$("#org").val(value);
		$("#inputName").focus();
		$("#form").validate({
			rules : {
				name : {
					required : true,
					maxlength : 20,
					remote:{
						url:"${ctx}/admin/party/person/check",
						type:"post",
						dataType: "json", 
						data:{
							name:function(){
								return $("#inputName").val();
							},
							initalname:"${person.name}"
						}
					}
				},
				sex : "required",
				code : {
					required : true,
					number : true,
					maxlength : 10
				},
				email : {
					required : true,
					email : true
				},
				mobile : {
					required : true,
					ismobile:true
				}

			}
		});
	});
	
	</script>
</body>
</html>