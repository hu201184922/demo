<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>CRM－<sitemesh:write property="title" default="模块名" /></title>
<link href="${pageContext.request.contextPath}/static/css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common.js" charset="utf-8"></script>
<sitemesh:write property="head" />
<script type="text/javascript">

</script>
</head>
<body>
	<!--整体框架结构开始 -->
	<div id="mainwrapper">
		<!--头部结构开始 -->
	    <div id="header">
	        <div class="logo"></div>
	        <div class="topHeadeRight"> 
	            <div class="topNav">
	                <ul>
	                    <li><img src="${pageContext.request.contextPath}/static/images/index_name.png" /></li>
	                    <li><span class="post_job"><shiro:principal property="userName" /></span><br />
	                        <span>2014年11月25&nbsp;&nbsp;日星期二</span></li>
	                    <li><a  class="bow_out" href="${pageContext.request.contextPath}/logout"  >退出</a></li>
	                    <li class="dividing_line" ></li>
	                    <li>
	                        <div class="mailbox" >
	                            <span class="digital">8</span>
	                        </div>
	                    </li>
	                    <li>
	                        <div class="message_1">
	                            <span class="digital">3</span>
	                        </div>
	                    </li>
	                </ul>
	            </div>
	            <div class="navigation">
	                <ul>                                      
						<c:forEach items="${menuItems}" var="menu" varStatus="idx">
							<shiro:hasPermission name="${menu.permString}">
								 <li><a href="${menu.url}" title="${menu.name}">${menu.name}</a></li> 
							</shiro:hasPermission>
						</c:forEach>
	                </ul>
	            </div>
	        </div>
	    </div>
	    <div class="clear"></div>
	    <!--内页主体结构开始 -->
		<div id="content">
			<div id="mainContent" style="width:100%">
			<sitemesh:write property="body" />
			</div>
		</div>
		<!--内页主体结构结束 -->
	    <!--整体框架结构结束-->
	    <div class="clear"></div>
	    <div id="footer">
	    </div>
	</div>
</body>
</html>