<%@tag import="java.util.Map"%>
<%@tag import="java.util.HashMap"%>
<%@tag import="org.apache.commons.lang3.StringUtils"%>
<%@tag import="com.fairyland.jdp.framework.util.DictionaryUtils"%>
<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="itemCode" type="java.lang.String" required="false"%>
<%@ attribute name="name" type="java.lang.String" required="false"%>
<%@ attribute name="dictCode" type="java.lang.String" %>
<%@ attribute name="type" type="java.lang.String" required="false"%>
<%@ attribute name="cssClass" type="java.lang.String" required="false"%>
<%@ attribute name="id" type="java.lang.String" required="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%

String itemName = DictionaryUtils.codeToName(dictCode,itemCode);
request.setAttribute("itemName", itemName);
request.setAttribute("items", DictionaryUtils.getDictItems(dictCode));

if(StringUtils.isNotEmpty(itemCode)){
	String[]  itemCodes=itemCode.split(",");
	Map<String,String> map=new HashMap<String,String>();
	for(String itemCodeSub:itemCodes){
		map.put(itemCodeSub,itemCodeSub);
	}
	request.setAttribute("itemCodes", map);
}
%>

<%if(StringUtils.isNotEmpty(dictCode)){%>
	
	<c:choose>
		<c:when test="${type == 'radio' }">
			<c:forEach items = "${items }" var ="item">
				<input type="radio" value="${item.code }" name="${name }" class="${cssClass }" id="${id }"
					<c:if test="${item.code eq itemCode}">checked</c:if>/>${item.name }
			</c:forEach>
		</c:when>
		<c:when test="${type == 'select' }">
			<select name="${name }" class="${cssClass }" id="${id }">
				<option value="" >请选择</option>
			<c:forEach items = "${items }" var ="item">
				<option value="${item.code }" <c:if test="${item.code eq itemCode}">selected</c:if>>${item.name }</option>
			</c:forEach>	
			</select>
		</c:when>
		<c:when test="${type == 'label' }">
			${itemName }
		</c:when>
		<c:when test="${type == 'checkbox' }">
			
					<c:forEach items = "${items }" var ="item">
				<input type="checkbox" value="${item.code }" name="${name }" class="${cssClass }" id="${id }"
					<c:if test="${not empty itemCodes[item.code] }">checked</c:if>/>${item.name }
			</c:forEach>
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>
	
<%}else{%>
	${itemName }
<%}%>
