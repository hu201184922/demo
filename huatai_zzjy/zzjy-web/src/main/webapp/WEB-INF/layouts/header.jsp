<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="navbar navbar-fixed-top">
  <div class="navbar-inner top-nav">
    <div class="container-fluid">
      <div class="branding">
        <div class="logo"> <a href="index.html"><img src="${ctx}/static/img/logo.png" width="168" height="40" alt="Logo"></a> </div>
      </div>
      <ul class="nav pull-right">
        <li class="dropdown search-responsive"><a data-toggle="dropdown" class="dropdown-toggle" href="#"><i class="nav-icon magnifying_glass"></i><b class="caret"></b></a>
          <ul class="dropdown-menu">
            <li class="top-search">
              <form action="#" method="get">
                <div class="input-prepend"> <span class="add-on"><i class="icon-search"></i></span>
                  <input type="text" id="searchIcon">
                </div>
              </form>
            </li>
          </ul>
        </li>
        <li class="dropdown"><a data-toggle="dropdown" class="dropdown-toggle" href="#">Anthony <span class="alert-noty">25</span><i class="white-icons admin_user"></i><b class="caret"></b></a>
          <ul class="dropdown-menu">
            <li><a href="#"><i class="icon-inbox"></i> 邮件 <span class="alert-noty">10</span></a></li>
            <li><a href="#"><i class="icon-envelope"></i> 通知 <span class="alert-noty">15</span></a></li>
            <li><a href="#"><i class="icon-briefcase"></i> 我的帐号</a></li>
            <li><a href="${ctx}/jdp/admin/user"><i class="icon-file"></i> 用户管理</a></li>
            <li><a href="#"><i class="icon-pencil"></i> 个性设置</a></li>
            <li><a href="#"><i class="icon-cog"></i> 帐号设置</a></li>
            <li class="divider"></li>
            <li><a href="${ctx}/biz/logout"><i class="icon-off"></i><strong> 注销</strong></a></li>
          </ul>
        </li>
      </ul>
      <button data-target=".nav-collapse" data-toggle="collapse" class="btn btn-navbar" type="button"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
      <div class="nav-collapse collapse">
        <ul class="nav">
         <c:forEach items="${menuItems}" var="menu">
						     <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="${menu.icon}"></i> ${menu.name}</a>
						</li>
				</c:forEach>
          <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="nav-icon cog_3"></i> 主题设置<b class="caret"></b></a>
            <ul class="dropdown-menu">
              <li class="nav-header hidden-phone hidden-tablet">侧边栏</li>
              <li class="theme-settings clearfix hidden-phone hidden-tablet">
                <div class="btn-group">
                  <button id="sidebar-on" disabled="disabled" class="btn btn-success">开启</button>
                  <button id="sidebar-off" class="btn btn-inverse">关闭</button>
                </div>
              </li>
              <li class=" divider"></li>
              <li class="nav-header hidden-phone hidden-tablet">侧边栏位置</li>
              <li class="theme-settings clearfix hidden-phone hidden-tablet">
                <div class="btn-group">
                  <button disabled="disabled" id="left-sidebar" class="btn btn-inverse">左侧</button>
                  <button id="right-sidebar" class="btn btn-info">右侧</button>
                </div>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </div>
  </div>
</div>
