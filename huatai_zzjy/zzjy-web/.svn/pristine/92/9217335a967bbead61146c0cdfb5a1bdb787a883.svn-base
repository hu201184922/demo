<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>大都会E展通系统-<sitemesh:write property="title" default="模块名" /></title>
<link href="${pageContext.request.contextPath}/static/ezt/web/css/bootstrap.css" type="text/css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/static/ezt/web/css/common.css" type="text/css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/static/ezt/web/css/index.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ezt/web/js/jquery-1.9.1.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ezt/web/js/bootstrap.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ezt/web/js/html5shiv.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ezt/web/js/respond.min.js" charset="utf-8"></script>

<!-- Include all compiled plugins (below), or include individual files as needed --> 
<sitemesh:write property="head" />
</head>

<body>
<div class="container" style="width:1200px;">
    <aside class="aside" style="WIDTH: 220px; BACKGROUND: #0076C0; OVERFLOW: hidden">
        <div class="bg-color">
        </div>
        <div class="navbar-brand-logo" style="padding-top:38px;">
            <img src="${pageContext.request.contextPath}/static/ezt/web/images/logo.png">
        </div>
        <div class="man-info">
            <div class="postcard">
                <img src="${pageContext.request.contextPath}/static/ezt/web/images/man.png">
            </div>
            <h4>王俊杰</h4>
            <p>寿险顾问（上海分公司）</p>
        </div>
        <ul class="nav">
            <li class="active">
                <a href="javascript:void(0);"><span class="icons01" aria-hidden="true"></span>新建快捷</a>
            </li>
            <li>
                <a href="javascript:void(0);"><span class="fastcreate icons02" id="fastcreate1" aria-hidden="true"></span>准客户</a>
            </li>
            <li>
                <a href="javascript:void(0);"><span class="fastcreate icons03" aria-hidden="true"></span>客户群组</a>
            </li>
            <li>
                <a href="javascript:void(0);"><span class="fastcreate icons04" aria-hidden="true"></span>客户计划</a>
            </li>
            <li>
                <a href="javascript:void(0);"><span class="fastcreate icons05" aria-hidden="true"></span>个人计划</a>
            </li>
            <li>
                <a href="javascript:void(0);"><span class="fastcreate icons06" aria-hidden="true"></span>目标设置</a>
            </li>
            <li class="active">
                <a href="javascript:void(0);"><span class="icons07" aria-hidden="true"></span>个人中心</a>
            </li>
            <li>
                <a href="javascript:void(0);"><span class="fastcreate icons08" aria-hidden="true"></span>个人名片</a>
            </li>
        </ul>
    </aside>
    <main class="main">
        <div class="main-top">
            <span>2016年6月15日 星期四</span>
            <ul class="nav navbar-nav pull-right">
                <li>
                    <a href="#"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span><span class="badge bg-danger">4</span></a>
                </li>
                <li>
                    <a href="#"><span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span><span class="badge bg-danger">2</span></a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/logout" class="text-info">退出系统</a>
                </li>
            </ul>
        </div>
        <div class="main-nav">
            <ul class="nav navbar-nav">
                <c:forEach items="${menuItems}" var="menu" varStatus="idx">
                	<shiro:hasPermission name="${menu.permString}">
							 <li><a href="${pageContext.request.contextPath}/${menu.url}">${menu.name}</a></li> 
					</shiro:hasPermission>
				</c:forEach>
                <!-- <li class="active"><a href="#">首页</a></li>-->
            </ul>
        </div>
        <div class="main-content">
          <sitemesh:write property="body" />
        </div>
    </main>
</div>
<script>

$(function(){
	$("#fastcreate1").click(function(){
		alert("1");
		$(".fastcreate").removeClass("active");
		$(this).addClass("active");
	});
});
</script>

</body>

</html>