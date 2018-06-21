<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title>复深蓝Java基础开发平台:<sitemesh:title/></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<link type="image/x-icon" href="${ctx}/static/images/favicon.ico" rel="shortcut icon"><!-- 设置网站收藏夹图标 -->
<link href="${ctx}/static/css/bootstrap.css" rel="stylesheet">
<link href="${ctx}/static/css/jquery-ui-1.8.16.custom.css" rel="stylesheet">
<link href="${ctx}/static/css/jquery.jqplot.css" rel="stylesheet">
<link href="${ctx}/static/css/prettify.css" rel="stylesheet">
<link href="${ctx}/static/css/elfinder.min.css" rel="stylesheet">
<link href="${ctx}/static/css/elfinder.theme.css" rel="stylesheet">
<link href="${ctx}/static/css/fullcalendar.css" rel="stylesheet">
<link href="${ctx}/static/js/plupupload/jquery.plupload.queue/css/jquery.plupload.queue.css" rel="stylesheet">
<link href="${ctx}/static/css/styles.css" rel="stylesheet">
<link href="${ctx}/static/css/bootstrap-responsive.css" rel="stylesheet">
<link href="${ctx}/static/css/icons-sprite.css" rel="stylesheet">
<link id="themes" href="${ctx}/static/css/themes.css" rel="stylesheet">
<!--[if IE 7]>
<link rel="stylesheet" type="text/css" href="css/ie/ie7.css" />
<![endif]-->
<!--[if IE 8]>
<link rel="stylesheet" type="text/css" href="css/ie/ie8.css" />
<![endif]-->
<!--[if IE 9]>
<link rel="stylesheet" type="text/css" href="css/ie/ie9.css" />
<![endif]-->
<sitemesh:head/>
</head>

<body>
	<shiro:user>
		<%@ include file="/WEB-INF/layouts/header.jsp"%>
	</shiro:user>

	<shiro:user>
		<%@ include file="/WEB-INF/layouts/left.jsp"%>
	</shiro:user>
	
   	<div id="main-content">
        	<sitemesh:body/>
     </div>

	<%@ include file="/WEB-INF/layouts/footer.jsp"%>

 
    
    <script src="${ctx}/static/js/jquery.js"></script> 
	<script src="${ctx}/static/js/jquery-ui-1.8.16.custom.min.js"></script> 
	<script src="${ctx}/static/js/jquery.ui.touch-punch.js"></script> 
	<script src="${ctx}/static/js/bootstrap.js"></script> 
	<script src="${ctx}/static/js/prettify.js"></script> 
	<script src="${ctx}/static/js/jquery.sparkline.min.js"></script> 
	<script src="${ctx}/static/js/jquery.nicescroll.min.js"></script> 
	<script src="${ctx}/static/js/accordion.jquery.js"></script> 
	<script src="${ctx}/static/js/smart-wizard.jquery.js"></script> 
	<script src="${ctx}/static/js/vaidation.jquery.js"></script> 
	<script src="${ctx}/static/js/jquery-dynamic-form.js"></script> 
	<script src="${ctx}/static/js/fullcalendar.js"></script> 
	<script src="${ctx}/static/js/raty.jquery.js"></script> 
	<script src="${ctx}/static/js/jquery.noty.js"></script> 
	<script src="${ctx}/static/js/jquery.cleditor.min.js"></script> 
	<script src="${ctx}/static/js/data-table.jquery.js"></script> 
	<script src="${ctx}/static/js/TableTools.min.js"></script> 
	<script src="${ctx}/static/js/ColVis.min.js"></script> 
	<script src="${ctx}/static/js/plupload.full.js"></script> 
	<script src="${ctx}/static/js/elfinder/elfinder.min.js"></script> 
	<script src="${ctx}/static/js/chosen.jquery.js"></script> 
	<script src="${ctx}/static/js/uniform.jquery.js"></script> 
	<script src="${ctx}/static/js/jquery.tagsinput.js"></script> 
	<script src="${ctx}/static/js/jquery.colorbox-min.js"></script> 
	<script src="${ctx}/static/js/check-all.jquery.js"></script> 
	<script src="${ctx}/static/js/inputmask.jquery.js"></script> 
	<script src="${ctx}/static/js/browserplus-min.js"></script> 
	<script src="${ctx}/static/js/plupupload/jquery.plupload.queue/jquery.plupload.queue.js"></script> 
	<script src="${ctx}/static/js/excanvas.min.js"></script> 
	<script src="${ctx}/static/js/jquery.jqplot.min.js"></script> 
	<script src="${ctx}/static/js/chart/jqplot.highlighter.min.js"></script> 
	<script src="${ctx}/static/js/chart/jqplot.cursor.min.js"></script> 
	<script src="${ctx}/static/js/chart/jqplot.dateAxisRenderer.min.js"></script> 
	<script src="${ctx}/static/js/FusionCharts.js"></script>
	<script src="${ctx}/static/js/custom-script.js"></script> 
	<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
	      <script src="${ctx}/static/lib/jdp/html5shiv/html5shiv.js"></script>
	<![endif]-->
	<script>
		$(function() {
			var date = new Date();
			var d = date.getDate();
			var m = date.getMonth();
			var y = date.getFullYear();
			$('#calendar-widget').fullCalendar({
				header: {
					left: 'prev,next today',
					center: 'title',
					right: 'month,agendaWeek,agendaDay'
				},
				buttonText: {
				prev: 'Prev',
				next: 'Next',
				today: 'Today',
				month: 'Month',
				week: 'Week',
				day: 'Day'
			},
				editable: true,
				events: [
					{
						title: 'All Day Event',
						start: new Date(y, m, 1)
					},
					{
						title: 'Long Event',
						start: new Date(y, m, d-5),
						end: new Date(y, m, d-2)
					},
					{
						id: 999,
						title: 'Repeating Event',
						start: new Date(y, m, d-3, 16, 0),
						allDay: false
					},
					{
						id: 999,
						title: 'Repeating Event',
						start: new Date(y, m, d+4, 16, 0),
						allDay: false
					},
					{
						title: 'Meeting',
						start: new Date(y, m, d, 10, 30),
						allDay: false
					},
					{
						title: 'Lunch',
						start: new Date(y, m, d, 12, 0),
						end: new Date(y, m, d, 14, 0),
						allDay: false
					},
					{
						title: 'Birthday Party',
						start: new Date(y, m, d+1, 19, 0),
						end: new Date(y, m, d+1, 22, 30),
						allDay: false
					},
					{
						title: 'Click for Google',
						start: new Date(y, m, 28),
						end: new Date(y, m, 29),
						url: 'http://google.com/'
					}
				]
			});
		});
	</script> 
	<script src="${ctx}/static/js/respond.min.js"></script>
	<script src="${ctx}/static/js/ios-orientationchange-fix.js"></script>
	<script type="text/javascript">
	</script>
</body>
</html>