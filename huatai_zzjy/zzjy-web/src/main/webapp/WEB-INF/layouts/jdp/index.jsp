<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<title>复深蓝Java基础开发平台:<sitemesh:title /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link type="image/x-icon" href="${ctx}/static/images/favicon.ico"
	rel="shortcut icon">
<!-- 设置网站收藏夹图标 -->
<link href="${ctx}/static/lib/jdp/bootstrap/3.1.1/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/lib/jdp/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
<link href='${ctx}/static/lib/jdp/date/css/datetimepicker.css' rel="stylesheet">
<link href="${ctx}/static/lib/jdp/ztree/3.5/css/zTreeStyle/zTreeStyle.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/lib/jdp/fancyBox/2.1.5/jquery.fancybox.css?v=2.1.5" media="screen" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/lib/jdp/fancyBox/2.1.5/helpers/jquery.fancybox-buttons.css?v=1.0.5" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/lib/jdp/fancyBox/2.1.5/helpers/jquery.fancybox-thumbs.css?v=1.0.7" />
<link href="${ctx}/static/css/default.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/lib/jdp/jquery/jquery-1.11.1.min.js" type="text/javascript"></script>

<!--[if lt IE 9]>
<script src="${ctx}/static/lib/jdp/html5shiv/html5shiv.js"></script>
<script src="${ctx}/static/lib/jdp/bootstrap/respond.min.js"></script>
<![endif]-->
<sitemesh:head />
</head>

<body style="padding-top: 70px;padding-bottom: 0px;">
	
		<%@ include file="/WEB-INF/layouts/jdp/header.jsp"%>
		<div class="row">
			<div class="col-md-2">
				<%@ include file="/WEB-INF/layouts/jdp/left.jsp"%>
			</div>
			<div class="col-md-10">
				<sitemesh:body />
			</div>
		</div>
		<%@ include file="/WEB-INF/layouts/jdp/footer.jsp"%>
	
	<script src="${ctx}/static/js/jdp-framework/general.js"></script>
<script src="${ctx}/static/lib/jdp/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/lib/jdp/jquery-validation/1.11.1/messages_<sp:message code="i18n.locale"/>.js" type="text/javascript"></script>
<script src="${ctx}/static/lib/jdp/bootstrap/3.1.1/js/bootstrap.min.js" type="text/javascript"></script>
<script	src="${ctx}/static/lib/jdp/ztree/3.5/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
<script src="${ctx}/static/lib/jdp/date/js/bootstrap-datetimepicker.js"></script>
<!-- Add fancyBox -->
<script type="text/javascript"	src="${ctx}/static/lib/jdp/fancyBox/2.1.5/jquery.mousewheel.pack.js?v=3.1.3"></script>
<script type="text/javascript"	src="${ctx}/static/lib/jdp/fancyBox/2.1.5/jquery.fancybox.js?v=2.1.5"></script>
<script type="text/javascript"	src="${ctx}/static/lib/jdp/fancyBox/2.1.5/helpers/jquery.fancybox-buttons.js?v=1.0.5"></script>
<script type="text/javascript"	src="${ctx}/static/lib/jdp/fancyBox/2.1.5/helpers/jquery.fancybox-thumbs.js?v=1.0.7"></script>
<script type="text/javascript"	src="${ctx}/static/lib/jdp/fancyBox/2.1.5/helpers/jquery.fancybox-media.js?v=1.0.6"></script>
</body>
</html>