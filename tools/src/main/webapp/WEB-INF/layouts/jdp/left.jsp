<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<div class="panel-group" id="accordion">
	<%-- 一级菜单 --%>
	<c:forEach items="${menuItems}" var="menu" varStatus="idx">
		<shiro:hasPermission name="${menu.permString}">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-toggle="collapse"
							data-parent="#accordion" href="#collapse${idx.index}">
							${menu.name}</a>
					</h4>
				</div>
				<div id="collapse${idx.index}"
					class="panel-second panel-collapse collapse <c:if test="${param.parentId==menu.id || param.parentId==null && idx.index==0}">in</c:if>">
					<div class="panel-body">
						<div class="list-group">
							<%-- 二级菜单 --%>
							<c:forEach items="${menu.children}" var="submenu">
								<c:if test="${submenu.visible}">
									<shiro:hasPermission name="${submenu.permString}">
										<a href="${ctx}/${submenu.url}?parentId=${menu.id}"
											class="list-group-item <c:if test="${fn:startsWith (currentPath, submenu.url)}">active</c:if>">
											${submenu.name} </a>
									</shiro:hasPermission>
								</c:if>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</shiro:hasPermission>
	</c:forEach>
	
	<c:if test="${param.parentId==null}">
		<script>
			if($(".active").get(0)){
				$(".panel-second").removeClass("in");
				$(".active").parents(".panel-second").addClass("in");
			}
		</script>
	</c:if>
	
</div>

