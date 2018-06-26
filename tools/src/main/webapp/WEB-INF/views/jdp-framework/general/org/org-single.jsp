<%@ page contentType="text/html;charset=UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<meta name="decorator" content="admin-popup"/>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<SCRIPT type="text/javascript">
<!--
	var setting = {
		view : {
			selectedMulti : false
		},
		
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			beforeClick : beforeClick,
			onExpand : onExpand
		}
	};

	var log, className = "dark", curDragNodes, autoExpandNode;
	
	function onExpand(event, treeId, treeNode) {
		if (treeNode === autoExpandNode) {
			className = (className === "dark" ? "" : "dark");
			showLog("[ " + getTime() + " onExpand ]&nbsp;&nbsp;&nbsp;&nbsp;"
					+ treeNode.name);
		}
	}

	function showLog(str) {
		if (!log)
			log = $("#log");
		log.append("<li class='"+className+"'>" + str + "</li>");
		if (log.children("li").length > 8) {
			log.get(0).removeChild(log.children("li")[0]);
		}
	}
	function getTime() {
		var now = new Date(), h = now.getHours(), m = now.getMinutes(), s = now
				.getSeconds(), ms = now.getMilliseconds();
		return (h + ":" + m + ":" + s + " " + ms);
	}

	function setTrigger() {
		var zTree = $.fn.zTree.getZTreeObj("orgtreemaintain");
		zTree.setting.edit.drag.autoExpandTrigger = $("#callbackTrigger").attr(
				"checked");
	}

	var data;
	function beforeClick(treeId, treeNode, clickFlag) {
		data = treeNode;
		parent.$.fancybox.close();
		return false;
	}
	
	function getValue(){
		return data;
	}
	$(document).ready(function() {
		$.getJSON("${ctx}/admin/party/org/get-tree?" + new Date(), function(data) {
			var zNodes = data;
			$.fn.zTree.init($("#orgtreemaintain"), setting, zNodes);
			$("#callbackTrigger").bind("change", {}, setTrigger);

		});
	});
//-->
</SCRIPT>
<style type="text/css">
.ztree li span.button.add {
	margin-left: 2px;
	margin-right: -1px;
	background-position: -144px 0;
	vertical-align: top;
	*vertical-align: middle
}
</style>

<div>
	<div class="col-md-12">
		<ul id="orgtreemaintain" class="ztree"></ul>
	</div>
</div>
