<%@ page contentType="text/html;charset=UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<meta name="decorator" content="admin-popup"/>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
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
		 fetchPersons(treeNode.id);
	 	 
	};
	
	function fetchPersons(orgId){
		$.ajax({
			type : "POST",
			url : "${ctx}/admin/party/person/fetch/persons/"+orgId,
			success : function(html) {
				$("#personsDiv").html(html);
				$(".candidate").bind("click",function(){
					var values=document.getElementById("selectedValue").options;
	 	 			var id = $(this).attr("pid");
					var name = $(this).attr("pname");
	 	 			for(var i=0;i<values.length;i++){
		 				if(values[i].value==id){
							return false;
		 				}
	 	 			}
		 			$("#selectedValue").append('<option value="'+id+'">'+name+'</option>');
				});
			}
		});
	}
	var data;
	function complete() {
		var options=document.getElementById("selectedValue").options;
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
		
		fetchPersons("-1");
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

<div class="panel panel panel-info">
	<div class="panel-heading">人员选择</div>
	<div class="panel-body">
	<div class="col-md-3">
		<ul id="orgtreemaintain" class="ztree"></ul>
	</div>
	<div class="col-md-6">
		<div id="personsDiv">
			
		</div>
	</div>
	<div class="col-md-3">
		<select class="col-md-12" multiple="multiple" size="8" id="selectedValue"
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
