<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>  
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<!-- Brand and toggle get grouped for better mobile display -->
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target="#bs-example-navbar-collapse-1">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="#">复深蓝Java Web开发框架</a>
	</div>
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

		<!--
    <form class="navbar-form navbar-left" role="search">
      <div class="form-group">
        <input type="text" class="form-control" placeholder="Search">
      </div>
      <button type="submit" class="btn btn-default">Submit</button>
    </form>
      -->
		<ul class="nav navbar-nav navbar-right">
			<li><a href="${ctx}/admin/desktop">桌面</a></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle" 
				data-toggle="dropdown"><sp:message code="i18n.language"/><b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="<%= request.getRequestURL() %>?locale=zh_CN">中文</a></li>
					<li><a href="<%= request.getRequestURL() %>?locale=en_US">English</a></li>
				</ul></li>
			<shiro:user>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><shiro:principal property="userName" /> <b
						class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="${ctx}/admin/sec/user">用户管理</a></li>
						<%-- <li><a class="b" data-fancybox-type="iframe" href="${ctx}/admin/profile/loadForm" 
						class="black-icons">账号设置</a></li> --%>

						<li><a class="change" data-fancybox-type="iframe" href="${ctx}/admin/profile/alterAdminSetting">账号设置</a></li>

 						<li><a  class="a" data-fancybox-type="iframe" href="${ctx}/admin/profile/alterAdminPassword">修改密码</a></li>
						<li class="divider"></li>
						<li><a href="${ctx}/admin/logout">注销</a></li>
					</ul></li>
			</shiro:user>
		</ul>
	</div>
</nav>
<script type="text/javascript">
$(document).ready(function() {
	$(".change").fancybox({
		fitToView	: false,
		width		: '80%',
		height		: '80%',
		autoSize	: false,
		closeClick	: false,
		openEffect	: 'none',
		closeEffect	: 'none',
		afterClose : function() {
			location.reload() ;
		}
		}
	);
	$(".a").fancybox({
		fitToView	: false,
		width		: '80%',
		height		: '80%',
		autoSize	: false,
		closeClick	: false,
		openEffect	: 'none',
		closeEffect	: 'none',
	
		}
	);
	
});
</script>
