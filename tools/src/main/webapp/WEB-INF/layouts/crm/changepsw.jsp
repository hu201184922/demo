<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>CRM－后台管理系统-<sitemesh:write property="title" default="模块名" /></title>
<link href="${pageContext.request.contextPath}/static/css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common.js" charset="utf-8"></script>
<sitemesh:write property="head" />
</head>

<body>
    <div id="mainwrapper">
    <div id="header">
        <div class="logo"></div>
        <div class="topHeadeRight"> 
            <div class="topNav">
                <ul>
                    <li><img src="${pageContext.request.contextPath}/static/images/index_name.png" /></li>
                    <li><span class="post_job"><shiro:principal property="userName" /></span><br />
                        <span>${headDateStr }&nbsp;&nbsp;${headWeek }</span></li>
                    <c:choose>
                    	<c:when test="${firstStage eq '0' }">
	                    	<li><a  class="bow_out" href="${pageContext.request.contextPath}/admin/logout" >退出</a></li>
                    	</c:when>
                    	<c:otherwise>
                    		<li><a  class="bow_out" href="${pageContext.request.contextPath}/logout" >退出</a></li>
                    	</c:otherwise>
                    </c:choose>
                    <li class="dividing_line" ></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="clear"></div>
	<div id="content">
		<div id="mainContent" style="width:100%;margin-left:0px">
		<sitemesh:write property="body" />
		</div>
	</div>
    <div class="clear"></div>
    <div id="footer">
    </div>
	</div>
</body>
</html>