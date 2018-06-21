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
			<button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
 <form id="form" action="${ctx}/admin/party/person/update-person" method="post" class="form-horizontal">
 <input type="hidden" name="id" value="${person.id}"/>
 <input type="hidden" name="initialcode" id="initialcode"   value="${person.code}"/>
 
	<div class="panel panel-info">
  <div class="panel-heading">编辑</div>
  <div class="panel-body">
 	<div class="form-group">
				<label for="inputMenuUrl" class="col-md-2 control-label">姓名：</label>
					<div class="col-sm-3">
						<input type="text" class="form-control required" id="inputName" name="name"
								value="${person.name}">
					</div>
				<label for="inputMenuUrl" class="col-md-2 control-label">性別：</label>
					<div class="col-sm-3">					
							<c:choose>
							 <c:when test="${person.sex=='man'}">
							 <input type="radio" name="sex" value="man" checked/>男
							 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							 <input type="radio" name="sex" value="woman"/>女
							 </c:when>
							 <c:otherwise>
							 <input type="radio" name="sex" value="man"/>男
							  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							 <input type="radio" name="sex" value="woman"  checked/>女
							 </c:otherwise>
							 </c:choose>					
					</div>	
	</div>	
	<div class="form-group">
				<label for="inputMenuUrl" class="col-md-2 control-label">编号：</label>
					<div class="col-sm-3">
						<input type="text" class="form-control required" id="inputCode" name="code"
								value="${person.code}">
					</div>
				<label for="inputMenuUrl" class="col-md-2 control-label">出生日期：</label>
					<div class="col-sm-3">				
								<input  data-format="yyyy-MM-dd" type="text" class="form-control required search-query form_datetime"  name="birthday" value="<fmt:formatDate value="${person.birthday}" pattern="yyyy-MM-dd"/>"> 
								<span class="add-on">
     								 <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                                     </i>
                                 </span>		
					</div>	
	</div>
			
			<div class="form-group">
				<label for="inputMenuUrl" class="col-md-2 control-label">学历：</label>
					<div class="col-sm-3">
							<tags:dict itemCode="${person.degree}" dictCode="xl" type="select"
							id="Inputdegree" name="degree" cssClass="form-control required" />	
					</div>
				<label for="postName" class="col-md-2 control-label">岗位：</label>
					<div class="col-sm-3">	
						<input type="hidden" name="postId"  id="post"
							value="${person.post.id}">
						<input type="text" class="form-control input-sm"  id="postName" name="v_postId""
							value="${person.post.name}">
					</div>
					<input type="button" class="post btn btn-default"
							value="选择">
					<span id="postTip"></span>
			</div>        
			<div class="form-group">
			<label for="inputPhone" class="col-md-2 control-label">电话：</label>
					<div class="col-sm-3">		
								<input type="text" class="form-control input-sm" id="inputPhone" name="phone"
								value="${person.phone}">					
					</div>	
				<label for="orgName" class="col-md-2 control-label">部门：</label>
					<div class="col-sm-3">					
						<input type="hidden" name="organization"  id="org"
							value="${person.org.id}">
						<input type="text" class="form-control input-sm"  id="orgName" name="v_organization"
							value="${person.org.name}">
					</div>
					<input type="button" class="org btn btn-default"
							value="选择">
					<span id="orgTip"></span>
				
			</div>
			<div class="form-group">
				<label for="inputMenuUrl" class="col-md-2 control-label">邮箱：</label>
					<div class="col-sm-3">
						
								<input type="text" class="form-control" id="inputEmail" name="email"
								value="${person.email}">
							
					</div>
				<label for="inputMenuUrl" class="col-md-2 control-label">入职时间：</label>
					<div class="col-sm-3">	
								<input type="text" class="form-control require search-query form_datetime" name="entryDate" value="<fmt:formatDate value="${person.entryDate}" pattern="yyyy-MM-dd"/>" format="yyyy-mm-dd"> <span
									class="add-on"><i class="icon-th"></i></span>						
					</div>	
			</div>
			<div class="form-group">
				<label for="inputMenuUrl" class="col-md-2 control-label">手机：</label>
					<div class="col-sm-3">			
								<input type="text" class="form-control" id="inputMobile" name="mobile" 
								value="${person.mobile}">
					</div>
			</div>
			<div class="form-group">
				<label for="inputMenuUrl" class="col-md-2 control-label">备注：</label>
					<div class="col-sm-8">
						
								<textarea rows="3" class="form-control" id="inputDescipt" name="descript">${person.descript}</textarea>
							
					</div>
			</div>	
  		</div>
  		<div class="panel-footer"><div class=" col-md-offset-4">
  		<input id="submit_btn" class="btn btn-default" type="submit"
							value="保存并新增" />
		<input id="submit_btn" class="btn btn-default" type="button" onclick="saveFieldSet()"
							value="保存并关闭" />
						<input id="cancel_btn" class="btn btn-default" type="button" value="关闭" onclick="parent.location.reload()"/></div>
		</div>
	</div>
     </form>
	<script type="text/javascript">
	
	$(document).ready(function(){
		jQuery.validator.addMethod("ismobile", function(value, element) {   
		    var tel =/^0?(13[0-9]|15[012356789]|18[012356789]|14[57])[0-9]{8}$/;
		    return this.optional(element) || (tel.test(value));
		}, "请正确填写手机号码"); 
		jQuery.validator.addMethod("isTel", function(value, element) {           
		    var tel = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/ ;       
		    return this.optional(element) || (tel.test(value));           
		}, "请正确填写您的电话号码");  
		jdp.selectSinglePost("post", function(data){
			$("#post").val(data.id);
			$("#postName").val(data.name);
		});
		jdp.selectSingleOrg("org", function(data){
			$("#org").val(data.id);
			$("#orgName").val(data.name);
		});
		$(".form_datetime").datetimepicker({
			minView: "month",
			format : "yyyy-mm-dd",
			pickerPosition: "bottom-left",
			autoclose : true,
			todayBtn : true,
			pickTime: false
		});
		var sex = $("#sexinital").val();
		if (sex == 'man') {
			var check = document.getElementById("man");
			check.checked = true;
		} else if (sex == 'woman') {
			var check = document.getElementById("woman");
			check.checked = true;
		}
		$("#inputName").focus();
		$("#form").validate({
			rules : {
				name : {
					required : true,
					maxlength : 20,
// 					remote:{
// 						url:"${ctx}/admin/party/person/check",
// 						type:"post",
// 						dataType: "json", 
// 						data:{
// 							name:function(){
// 								return $("#inputName").val();
// 							},
// 							initalname:"${person.name}"
// 						}
// 					}
				},
				sex : "required",
				code : {
					required : true,
					number : true,
					maxlength : 10,
					remote:{
						url:"${ctx}/admin/party/person/checkCode",
						type:"post",
						datatype:"json",
						data:{
							name:function(){
								return $("#inputCode").val();
							},
							initialcode:function(){
 								return $("#initialcode").val();
 							}	
						}
					}
				},
				email : {
					required : true,
					email : true
				},
				mobile : {
					 required: true,  
					 ismobile: true  
				},
				v_organization :{
					 required: true,
				},
				v_postId:{
					required: true,
				},
				phone:{
					required: true,
					isTel:true
					
				}
			}
		});
	});
	function saveFieldSet() {
		var postname = $("#post").val();
		var orgname=  $("#org").val();
		var validator = $("#form").validate();
		if(validator.form()){
			var values = $("#form").serialize();
			$.ajax({
				type : "post",
				data : values,
				url : "${ctx}/admin/party/person/update-person/" ,
				success : function(msg) {
					parent.location.reload();
				}
			});	
		}else $("#form").submit();
			
		
		
		
	};
	function saveadd(){		
			var postname = $("#post").val();
			var orgname=  $("#org").val();
			if(postname.trim() == ""||orgname.trim() == ""){
				if (postname.trim() == "") {
					$("#postTip").html(
							"<font color=\"red\">岗位不能为空</font>");
				}
				if (orgname.trim() == "") {
					$("#orgTip").html(
							"<font color=\"red\">部门不能为空</font>");
				}
			}else {			
				var values = $("#form").serialize();
				$.ajax({
					type : "post",
					data : values,
					url : "${ctx}/admin/party/person/create" ,	
					success : function(msg) {
						window.location.reload();
					}
				});	
			}
	};
	</script>
</body>
</html>