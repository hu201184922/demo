<%@ page contentType="text/html;charset=UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
	<title>字段集管理</title>
<meta name="decorator" content="admin-popup"/>
	<SCRIPT type="text/javascript">
<!--
	var setting = {
		view: {
			selectedMulti: false
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onClick:zTreeOnClick
		}
	};

	function zTreeOnClick(event, treeId, treeNode) {
	 	 var values=document.getElementById("selectedValue").options;
	 	 for(var i=0;i<values.length;i++){
		 	if(values[i].value==treeNode.id){
				return false;
		 	}
	 	 }
		 $("#selectedValue").append('<option value="'+treeNode.id+'">'+treeNode.name+'</option>');
	};

	var data;
	function complete() {
		var options=document.getElementById("selectedValue").options;
		 /* var values = [];
		 var treeObj = $.fn.zTree.getZTreeObj("orgtreemaintain");
		 
		 for(var i=0;i<options.length;i++){
		 	 var node = treeObj.getNodeByTId("orgtreemaintain_"+options[i].value);
			 values.push(node);
		 }
		 data = values; */
		  data = [];
		 
		 for(var i=0;i<options.length;i++){
		 	var item = {
		 		id	 :	options[i].value,
		 		name :  options[i].text
		 	};
			 data.push({
		 		id	 :	options[i].value,
		 		name :  options[i].text
		 	});
		 }
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
	
</head>

<body>

<div class="panel panel panel-info">
	<div class="panel-heading">组织机构——多选</div>
	<div class="panel-body">
	<div class="col-md-6">
		<ul id="orgtreemaintain" class="ztree"></ul>
	</div>
	<div class="col-md-6">
		<select class="col-md-6" multiple="multiple" size="8" id="selectedValue"
				ondblclick="this.removeChild(this.options[this.selectedIndex])">			
			</select>
	</div>
	</div>
	<!-- footer -->
			<div class="panel-footer">
				<div class=" col-md-offset-6">
					<input class="btn btn-primary" type="button"
						value="确定" onclick="complete()"/>
				</div>
			</div>
</div>

</body>

</html>
