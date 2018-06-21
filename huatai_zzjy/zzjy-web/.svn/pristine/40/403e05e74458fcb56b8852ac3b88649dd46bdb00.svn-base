<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u" %>
<script type="text/javascript">
$(function(){
	var allResource = Ace.decode("${u:serialize(treeResources)}");
	allResource = allResource ? allResource : [] ;
	window.groupResources = Ace.decode("${u:serialize(role.roleResources)}");
	groupResources = groupResources ? groupResources : [] ;
	$('#resourceBoxTree').zTree({
		dataFilter:function(resources,call){
		resources.each(function(){
			var _this = this;
			if(groupResources.each((function(id){
				return function(){
					if(this.resource.id == id)
						return true;
				};
			})(this.id))){
				this.checked = true;
				this.checked = getChecked(this);
			}else{
				this.checked = false;
			}
			if(this.children && this.children.length > 0){
				this.children.each(function(){
					this.parentResourceChecked = _this.checked;
				});
				call(this.children);
			}
		});
		return resources;
	},check: {
		enable: true
	},view:{addDiyDom:function(treeId, treeNode){
		var checkbox = $('<input style="display:none;" id="'+treeNode.tId+'_checkbox" type="checkbox" name="resourceids" value="'+treeNode.id+'"/>');
		$("#" + treeNode.tId + "_check").after(checkbox);
	}}},allResource);
	function getChecked(node){
		if(node.checked && !(node.parentResourceChecked == false)){
			return true;
		}else{
			return false;
		}
	}
});
</script>
<ul id="resourceBoxTree" class="ztree"></ul>