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
	<form id="inputForm" action="${ctx }/admin/preference/sys/save" method="post"
		class="form-horizontal">
		<div class="panel panel panel-info">
			<div class="panel-body">
				<input type="hidden" name="preferenceCategoryId" value="${preferenceCategoryId}">
				<c:forEach items="${preferenceDefinitions}"
					var="preferenceDefinition">
					<div class="form-group">
						<label for="${preferenceDefinition.code}" class="col-md-2 control-label">${preferenceDefinition.name}</label>
						<div class="col-md-4">
						<c:choose>
							<c:when test="${preferenceDefinition.editorType == 'radio' }">
								<tags:dict itemCode="${preferenceValueMap[preferenceDefinition.code]}" dictCode="${preferenceDefinition.metaMap['code']}" type="radio"
									id="${preferenceDefinition.code}" name="pref_${preferenceDefinition.code}" cssClass="" />
							</c:when>
							<c:when test="${preferenceDefinition.editorType  == 'checkbox' }">
								<tags:dict itemCode= "${preferenceValueMap[preferenceDefinition.code]}"  dictCode="${preferenceDefinition.metaMap['code']}" type="checkbox"
							id="${preferenceDefinition.code}" name="pref_${preferenceDefinition.code}" cssClass="" />
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
								</textarea>
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>
										
							<%-- controltype text
								dropdowntype dict <tags:dict/>
							<input type="text" id="${preferenceDefinition.code}" name="pref_${preferenceDefinition.code}"
								value="${preferenceValueMap[preferenceDefinition.code]}"  class="form-control input-sm"/>
							controltype duoxuan
								dropdowntype dict <tags:dict dictCode="" type="checkbox"/>
							controltype danxuan
								dropdowntype dict <tags:dict type="radio"/>--%>
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