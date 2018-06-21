<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<meta name="decorator" content="admin-popup">
<html>
<head>
<title>资源管理</title>
<script> 
	function saveAndClose(){
		var validator = $("#inputForm").validate();
        if (validator.form()) {//判断加入所有校验都通过后再做ajax提交；
        	$.ajax({
                url: "${ctx}/admin/sec/rescategory/save-close/${resourceCategory.id}",
                data: $("#inputForm").serialize(),
                type: "post",
                success: function (data) {
                	if(data=='success'){
                		parent.$.fancybox.close();
                	}
                }
                
        	
            });
        }else
        	$("#inputForm").submit();
	};	
</script>
</head>
<body>
<form id="inputForm" action="${ctx}/admin/sec/rescategory/save-add/${resourceCategory.id}" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${resource.id}"/>
		<input type="hidden" name="initalname" id="initalname" value="${resource.name}"/>
		<input type="hidden" name="initalresString" id="initalresString" value="${resource.resString}"/>
		<input type="hidden" name="initalperString" id="initalperString" value="${resource.perString}"/>
		<div class="panel panel-info">
			<div class="panel-heading">填写资源信息</div>
			<div class="panel-body">
				<div class="form-group">
					<label for="name" class="col-md-2 control-label">名称:</label>
					<div class="col-md-4">
						<input type="text" value="${resource.name}" class="form-control input-sm required" name="name" id="name"  placeholder="名称"/>
					</div>
				</div>
				<div class="form-group">
					<label for="resType" class="col-md-2 control-label">类型:</label>
					<div class="col-md-4">
						<select name="resType" id="resType" class="form-control input-sm required" >
						  <option value="">请选择</option>
	                      <option value="U">URL</option>
	                      <option value="E">ELEMENT</option>
	                      <option value="M">METHOD</option>
	                    </select>
					</div>
				</div>
				<div class="form-group">
					<label for="resString" class="col-md-2 control-label">唯一字符串:</label>
					<div class="col-md-4">
						<input type="text" id="resString" name="resString" class="form-control input-sm required" value="${resource.resString}"  placeholder="唯一字符串 "/>
					</div>
				</div>
				<div class="form-group">
					<label for="perString" class="col-md-2 control-label">permission字符串:</label>
					<div class="col-md-4">
						<input type="text" id="perString" name="perString" class="form-control input-sm required" value="${resource.perString}" placeholder="授权字符串"/>
					</div>
				</div>
				<div class="form-group">
					<label for="res_enabled" class="col-md-2 control-label">可用:</label>
						<div class="col-md-4">
						<c:choose>
							<c:when test="${resource.enabled  eq true}">
								<input type="radio" id="res_enabledYes" name="enabled" value="true" checked/><label for="res_enabledYes">是</label>
								<input type="radio" id="res_enabledNo" name="enabled" value="false" /><label for="res_enabledNo">否</label>
							</c:when>
							<c:otherwise>
								<input type="radio" id="res_enabledYes" name="enabled" value="true" /><label for="res_enabledYes">是</label>
								<input type="radio" id="res_enabledNo" name="enabled" value="false" checked/><label for="res_enabledNo">否</label>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="form-group">
					<label for="descript" class="col-md-2 control-label">描述:</label>
					<div class="col-md-4">
						<textarea rows="3" id="descript" name="descript" class="form-control input-sm">${resource.descript}</textarea>
					</div>
				</div>
			</div>
			<div class="panel-footer">
				<div class="col-md-offset-2">
					<input class="btn btn-default" type="submit" value="保存并新增"/>
					<input class="btn btn-default" type="button" value="保存并关闭" onclick="saveAndClose()"/>
					<input class="btn btn-default" type="button" value="关闭" onclick="javascript:parent.$.fancybox.close();"/>
				</div>
			</div>
		</div>
	</form>
   <script>
   	
	$(document).ready(function() {
		    var value="${resource.resType}";
		    $("select").val(value);
		    $("#res_enabled").val();
			$("#name").focus();
			$("#inputForm").validate({
				rules:{
					name:{
						remote:{
							url:"${ctx}/admin/sec/resource/check",
							type:"post",
							dataType: "json", 
							data:{
								name:function(){
									return $("#name").val();
								},
								initalname:function(){
									return $("#initalname").val();
								}
							}
						}
					},
					resString:{
						remote:{
							url:"${ctx}/admin/sec/resource/checkRes",
							type:"post",
							dataType: "json", 
							data:{
								resString:function(){
									return $("#resString").val();
								},
								initalRestring:function(){
									return $("#initalresString").val();
								}
							}
						}
					},
					perString:{
						remote:{
							url:"${ctx}/admin/sec/resource/checkPer",
							type:"post",
							dataType: "json", 
							data:{
								perString:function(){
									return $("#perString").val();
								},
								initalPerstring:function(){
									return $("#initalperString").val();
								}
							}
						}
					}
					
					
				}
			});
			
		});
	</script>

</body>
</html>