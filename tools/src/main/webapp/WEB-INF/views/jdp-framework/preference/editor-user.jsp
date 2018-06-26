<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="decorator" content="nodecorate" />
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<title>系统首选项设置</title>

<script type="text/javascript">
	
</script>

</head>
<body>
	<form id="inputForm" action="${ctx }/admin/preference/user/save" method="post"
		class="form-horizontal">
		<div class="panel panel panel-info">
			<div class="panel-body">
				<input type="hidden" name="preferenceCategoryId" value="${preferenceCategoryId}">
				<c:forEach items="${preferenceDefinitions}"
					var="preferenceDefinition">
					<div class="form-group">
						<label for="pref_${preferenceDefinition.code}" class="col-md-2 control-label">${preferenceDefinition.name}</label>
						<div class="col-md-4">
						<c:choose>
							<c:when test="${preferenceDefinition.editorType == 'radio' }">
								<tags:dict itemCode="${preferenceValueMap[preferenceDefinition.code]}" dictCode="${preferenceDefinition.metaMap['code']}" type="radio"
									id="${preferenceDefinition.code}" name="pref_${preferenceDefinition.code}" cssClass="" />
							</c:when>
							<c:when test="${preferenceDefinition.editorType  == 'checkbox' }">
							<%-- <c:forEach item="${fn:split(preferenceValueMap[preferenceDefinition.code],',')" var="perDefCode">--%>
								<tags:dict itemCode= "${preferenceValueMap[preferenceDefinition.code]}"  dictCode="${preferenceDefinition.metaMap['code']}" type="checkbox"
							id="${preferenceDefinition.code}" name="pref_${preferenceDefinition.code}" cssClass="" />
							<%--</c:forEach>--%>
							</c:when>
							<c:when test="${preferenceDefinition.editorType  == 'text' }">
								<c:choose>
									<c:when test="${preferenceDefinition.dropDownType =='dictionary'}">
										<tags:dict itemCode="${preferenceValueMap[preferenceDefinition.code]}" dictCode="${preferenceDefinition.metaMap['code']}" type="select"
											id="${preferenceDefinition.code}" name="pref_${preferenceDefinition.code}" cssClass="" />
									</c:when>
									<c:otherwise>
										<input type="text" id="${preferenceDefinition.code}" name="pref_${preferenceDefinition.code}"
											value="${preferenceValueMap[preferenceDefinition.code]}"  class="form-control input-sm"/>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:when test="${preferenceDefinition.editorType  == 'textarea' }">
								<textarea id="${preferenceDefinition.code}" name="pref_${preferenceDefinition.code}" rows="6"
									class="form-control input-sm">
								${preferenceValueMap[preferenceDefinition.code]}
								</textarea>	
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>
							<%-- <input type="text" id="pref_${preferenceDefinition.code}" name="pref_${preferenceDefinition.code}"
								value="${preferenceValueMap[preferenceDefinition.code]}"
								<c:if test="${empty preferenceDefinition.overwritable or preferenceDefinition.overwritable == false}">disabled</c:if>
								class="form-control input-sm" />
								--%>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="panel-footer">
				<div class=" col-md-offset-2">
					<input class="btn btn-default" type="submit" value="保存" />
				</div>
			</div>
		</div>
	</form>
 
 <div>
<%--    <h3><a href="${ctx}/admin/exception/dao">dao正常错误</a></h3>  
 <h3><a href="${ctx}/admin/exception/service">service正常错误</a></h3>  
 <h3><a href="${ctx}/admin/exception/controller">controller正常错误</a></h3>   --%>
 </div> 
</body>
<script type="text/javascript">
$(document).ready(function(){
	$("#inputForm").validate({
		rules:{
			<c:forEach items="${preferenceDefinitions}" var="preferenceDefinition">
				'pref_${preferenceDefinition.code}' : { 
					<c:choose>
						<c:when test="${preferenceDefinition.dataType == 'Integer'||preferenceDefinition.dataType == 'Long'}">
							digits : true,
						</c:when >
						<c:when test="${preferenceDefinition.dataType == 'Double'||preferenceDefinition.dataType == 'Float'}">
							number:true,
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>
				},
			</c:forEach>
		}
	});
});
</script>
</html>