<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <script src="${pageContext.request.contextPath}/static/js/common/artDialog/artDialog.js"> --%>
<title>组织架构管理</title>
<style type="text/css">
 #tree-legend { font-size:12px;padding:5px;line-height:12px; }
 #tree-legend span { padding-left: 8px; display: inline-block; }
 #tree-legend i { display: inline-block;width: 16px;height: 16px;border-radius: 2px;border:1px solid #000; }
 .ztree li span { color:inherit; }
 .col_lg_02 { height: inherit; }
</style>
</head>
<body>
	 <!-- <div class="business_title">组织架构管理</div> -->
	 <div class="row">
	 	<!-- tagContent tagContent_div selectTag -->
		<div id="tagContent0" class="col_lg_02" style="width: 200px;">
			<%-- <input id="addOrg" type="button" value="添加"/>  --%>
			<%-- <ul id="orgTree" class="ztree"></ul> --%>
			<ul class="ztree">
			<c:forEach items="${citys}" var="item">
				<li><c:if test="${item.lv eq 2 }">&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
					<span data-code="${item.cityCode }">${item.shortName }[${item.cityCode }]</span></li>
			</c:forEach>
			</ul>
		</div>
		<div id="treeDiv" class="col_lg_02" style="display: none;">
			<div>最新职级关系：
			<!-- <a id="btn-tree-hide" href="javascript:;">[隐藏]</a> -->
			</div>
            <div class="input-append row-fluid" style="margin-bottom: 0px;">
		    	<input id="search_tree" type="text" placeholder="请输入代理人姓名" class="span8" style="font-size:12px"/>
		    	<button type="button" class="btn btn-info btn-sm" onclick="orgTree.search_ztree('testTree', 'search_tree')">搜索</button>
		    </div>
           	<div id="tree-legend"></div>
            <ul id="testTree" class="ztree" >
            </ul>
		</div>
		<div id="showUserDiv" class="col_lg_01" style="display: none;">
			<table class="table">
				<thead>
					<th>用户明细：</th>
				</thead>
				<tr>
					<td>账号：</td>
					<td id="tdCode"></td>
				</tr>
				<tr>
					<td>姓名：</td>
					<td id="tdName"></td>
				</tr>
				<tr>
					<td>职级：</td>
					<td id="tdLv"></td>
				</tr>
				<tr>
					<td>状态：</td>
					<td id="tdStatus"></td>
				</tr>
			</table>
		</div>
	</div>
	<form id="saveOrgForm" style="display:none;" action="${pageContext.request.contextPath}/admin/org/save" method="post" >
	<input type="hidden" name="orgid" value="{orgid}" />
	<input type="hidden" name="porgId" value="{porgId}"/>
	<input type="hidden" name="levels" value="{levels}"/>
	<div id="tagContent2" class="tagContent tagContent_div" style="display:block;">
            <table style="line-height:30px">
			    <tbody><tr>
				    <td class="text_right">机构名称：</td>
					<td class="text_left"><input type="text" name="orgName" value="{orgName}" class="text_wd200"/></td>
				</tr>
				<tr>
				    <td class="text_right">机构编号：</td>
					<td class="text_left"><input type="text" name="orgCode" value="{orgCode}" class="text_wd200"/></td>
				</tr>
				<tr>
				    <td class="text_right">状态：</td>
					<td class="text_left">
					<input name="enabled" checked="checked" type="radio" value="true"/>启用/<input name="enabled" type="radio" value="false"/>禁用
					</td>
				</tr>

				<tr>
				    <td class="text_right">描述：</td>
					<td class="text_left">
						<textarea name="description" rows="" cols="" style="width:200px;height:50px;">{description}</textarea>
					</td>
				</tr>
				<tr>
				    <td height="10px" colspan="2"></td>
				</tr>
				<tr>
				    <td></td>
					<td class="text_left">
					<input type="submit" value="提 交" class="search_btn">&nbsp;&nbsp;<input type="button" value="取 消" class="search_btn close">
					</td>
				</tr>
			</tbody>
		</table>
    </div>
</form>

<script type="text/javascript">
var orgTree={ load:true }; /**默认不加载组织树状 */
$(function(){
	/*
	$('#orgTree').zTree({url:'${pageContext.request.contextPath}/admin/org/tree',dataFilter:function(treeId, parentNode, childNodes){
		childNodes?childNodes.each(function(){
			this.pid = this.porgId;
			this.id = this.orgid;
			this.name=this.orgName+(this.arg1 == 0 ? "" : "("+this.arg1+"/"+this.arg2+")");
			this.isParent= (this.levels == 1 ? true : (this.levels == 3 ? false : (this.childsnum > 0 ? true : false)));
		}):null;
		return childNodes;
	},
	edit:false,
	callback:{
		onAsyncSuccess:function(event, treeId, treeNode, msg){
			var newNodes = $('#saveOrgForm').data('newNodes');
			if(!!newNodes){
				newNodes.each(function(){
					var nodes = $('#orgTree').zTree().getNodesByParam("orgid", this.orgid, treeNode);
					nodes.each(function(){
						$('#orgTree').zTree().removeNode(this, false);
					});
				});
			}
			$('#saveOrgForm').data('newNodes',null);
		},
		add:function(treeId, treeNode){
			$('#saveOrgForm').data('node',treeNode);
			$.dialog({title:"资源组添加",content:"<h3>选择资源的添加方式!</h3>",
				button : [{
					value : '添加新的机构',
					callback : function() {
						orgDialog(treeNode.id,'add',parseInt(treeNode.levels)+1);
					}
				}]
			});
		},
		beforeEditName : function(treeId, treeNode) {
			$.confirm('是否确定编辑机构:' + treeNode.name + '?', function() {
				$('#saveOrgForm').data('type', 'update');
				$('#saveOrgForm').data('node', treeNode);
				$('#saveOrgForm').template(treeNode);
				$('#saveOrgForm').find('[name=enabled]').val(treeNode.enabled);
				$('#saveOrgForm').data('dialog', $.dialog({
					title : '资源组编辑',
					content : $('#saveOrgForm')[0]
				}));
			});
			return false;
		},
		beforeRemove : function(treeId, treeNode) {
			var title = '是否确定删除资源'+(treeNode.type == 'url'?'':'组')+':' + treeNode.name + '?';
			$.dialog({title : title,content:"<h3>选择删除资源的方式!</h3>",
				button:[{
					value : "删除所有子节点",
					callback : function(){
						$.getJSON('${pageContext.request.contextPath}/admin/org/delete', {
							orgid : treeNode.orgid
						}, function(data) {
							$('#orgTree').zTree().removeNode(treeNode, false);
							$.msgbox({time: 2000,msg: "删除成功!",icon:"success"});
						});
					}
				},
				{
					value : "取消",
					callback : function(){
						
					}
				}]
			
			});
			return false;
		}
	}
	});
	
	$('#addOrg').click(function() {
		$('#saveOrgForm').data('node',null);
		orgDialog(null,'add',1);
	});
	var actions = {'add':'添加'};
	var orgDialog = function(pid,type,levels){
		$('#saveOrgForm').template({
			porgId : pid,
			levels : levels
		});
		$('#saveOrgForm').data('type',type);
		$('#saveOrgForm').data('dialog', $.dialog( {
			title : actions[type],
			content : $('#saveOrgForm')[0]
		}));
	};
	$('#saveOrgForm').ajaxForm(function(data) {
		var treeNode = $('#saveOrgForm').data('node'); //menuTree.getSelectedNodes();
		$('#saveOrgForm').data('dialog').close();
		switch($('#saveOrgForm').data('type')){
			case "add":
				$.msgbox({time: 2000,msg: "资源组添加成功!",icon:"success"});
				data.id = data.orgid;
				data.name = data.orgName;
				data.isParent = true;
				if(!!treeNode)
					data.pid = treeNode.id;
				$('#saveOrgForm').data('newNodes',[data]);
				$('#orgTree').zTree().addNodes(treeNode, data);
				break;
			case "update":
				$.msgbox({time: 2000,msg: "资源组编辑成功!",icon:"success"});
				var nodes = $('#orgTree').zTree().getNodesByParam('orgid',data.orgid, null);
				nodes.each(function(){
					$('#orgTree').zTree().updateNode(Ace.copy(this,data),false);
				});
				break;
		}
	});
	*/
	
	/** 颜色参数 */
	orgTree.colors={"CAO":"#F00","GM":"#0F0123","AH":"#008A00","DM":"#828200","AM":"#009C9C","SM":"#F0F","LP":"#000","01":"#FFF","03":"#CCC"};

	/**
	 * ztree设置文本颜色
	 */
	orgTree._getFont = function(treeId, node) {
		var font={};
		font["background-color"] = orgTree.colors[node.state];
		font.color = orgTree.colors[node.grade];
		return font;
	}
	
	
	/**
	 * 创建树状结构
	 */
	orgTree._createTree = function() {
		$("#testTree").empty();
		$.ajax({
			type : "POST",
			async : false,
			url : "${ctx}/admin/org/getOrsNodes",
			//data : {},
			dataType : "json",
			success : function(data) {
				var zNodes=data;
				//console.log(data);
				orgTree.treeObj=$("#testTree").zTree({
					check: {
						enable: true
					},
					data: {
						simpleData: {
							enable: true
						}
					},
					view: {
						fontCss: orgTree._getFont,
						showIcon: false
					},
					showTitle:true,
					key:{
						title:"title"
					},
					callback:{
						onClick:function(event,treeId,treeNode){
							//console.log(treeNode);
							if("CAO"!=treeNode.grade && "GM"!=treeNode.grade && "AH"!=treeNode.grade){
								$('#tdCode').html(treeNode.id);
								$('#tdName').html(treeNode.name);
								$('#tdLv').html(treeNode.grade);
								$('#tdStatus').html("03"==treeNode.state?"离职":"在职");
							}
						}
					}
				}, zNodes);
			}
		});

	}
	orgTree._createLegend=function(){
		var _h="";
		for(var n in orgTree.colors){
			if(n=='01'){
				_h+="<span>在职(背景色): </span><i style=\"background:"+orgTree.colors[n]+"\">&nbsp</i>";
			}else if(n=='03'){
				_h+="<span>离职(背景色): </span><i style=\"background:"+orgTree.colors[n]+"\">&nbsp</i>";
			}else{
				_h+="<span>"+n+": </span><i style=\"background:"+orgTree.colors[n]+"\">&nbsp</i>";
				if(n=='LP'){
					_h+="<br>";
				}
			}
			//console.log(n+","+colors[n]);
		}
		$('#tree-legend').html(_h);
	}
	orgTree.show=function(){
		$('#treeDiv').show();
	}
	
	orgTree.hide=function(){
		$('#treeDiv').hide();
	}
	
	//$('#btn-tree-hide').click(orgTree.hide);
	
	orgTree.init=function(){
		if(orgTree.load){
			orgTree._createLegend();  //加载图例
			orgTree._createTree();    //加载树结构
			$('#treeDiv').show();
			$('#showUserDiv').show();
		}
	};
	
	
	/**
     * 展开树
     * @param treeId  
     */
    function expand_ztree(treeId){
        var treeObj = orgTree.treeObj; //$.fn.zTree.getZTreeObj(treeId);
        treeObj.expandAll(true);
    }
     
    /**
     * 收起树：只展开根节点下的一级节点
     * @param treeId
     */
    function close_ztree(treeId){
        var treeObj = orgTree.treeObj; //$.fn.zTree.getZTreeObj(treeId);
        var nodes = treeObj.transformToArray(treeObj.getNodes());
        var nodeLength = nodes.length;
        for (var i = 0; i < nodeLength; i++) {
            if (nodes[i].id == '0') {
                //根节点：展开
                treeObj.expandNode(nodes[i], true, true, false);
            } else {
                //非根节点：收起
                treeObj.expandNode(nodes[i], false, true, false);
            }
        }
    }
     
    /**
     * 搜索树，高亮显示并展示【模糊匹配搜索条件的节点s】
     * @param treeId
     * @param searchConditionId 文本框的id
     */
     orgTree.search_ztree =function(treeId, searchConditionId){
        searchByFlag_ztree(treeId, searchConditionId, "");
    };
     
    /**
     * 搜索树，高亮显示并展示【模糊匹配搜索条件的节点s】
     * @param treeId
     * @param searchConditionId     搜索条件Id
     * @param flag                  需要高亮显示的节点标识
     */
    function searchByFlag_ztree(treeId, searchConditionId, flag){
        //<1>.搜索条件
        var searchCondition = $('#' + searchConditionId).val();
        //<2>.得到模糊匹配搜索条件的节点数组集合
        var highlightNodes = new Array();
        if (searchCondition != "") {
            var treeObj = orgTree.treeObj; //$.fn.zTree.getZTreeObj(treeId);
            highlightNodes = treeObj.getNodesByParamFuzzy("name", searchCondition, null);
        }
        //<3>.高亮显示并展示【指定节点s】
        highlightAndExpand_ztree(treeId, highlightNodes, flag);
    }
     
    /**
     * 高亮显示并展示【指定节点s】
     * @param treeId
     * @param highlightNodes 需要高亮显示的节点数组
     * @param flag           需要高亮显示的节点标识
     */
    function highlightAndExpand_ztree(treeId, highlightNodes, flag){
        var treeObj = orgTree.treeObj; //$.fn.zTree.getZTreeObj(treeId);
        //<1>. 先把全部节点更新为普通样式
        var treeNodes = treeObj.transformToArray(treeObj.getNodes());
        for (var i = 0; i < treeNodes.length; i++) {
            treeNodes[i].highlight = false;
            treeObj.updateNode(treeNodes[i]);
        }
        //<2>.收起树, 只展开根节点下的一级节点
        close_ztree(treeId);
        //<3>.把指定节点的样式更新为高亮显示，并展开
        if (highlightNodes != null) {
            for (var i = 0; i < highlightNodes.length; i++) {
                if (flag != null && flag != "") {
                    if (highlightNodes[i].flag == flag) {
                        //高亮显示节点，并展开
                        highlightNodes[i].highlight = true;
                        treeObj.updateNode(highlightNodes[i]);
                        //高亮显示节点的父节点的父节点....直到根节点，并展示
                        var parentNode = highlightNodes[i].getParentNode();
                        var parentNodes = getParentNodes_ztree(treeId, parentNode);
                        treeObj.expandNode(parentNodes, true, false, true);
                        treeObj.expandNode(parentNode, true, false, true);
                    }
                } else {
                    //高亮显示节点，并展开
                    highlightNodes[i].highlight = true;
                    treeObj.updateNode(highlightNodes[i]);
                    //高亮显示节点的父节点的父节点....直到根节点，并展示
                    var parentNode = highlightNodes[i].getParentNode();
                    var parentNodes = getParentNodes_ztree(treeId, parentNode);
                    treeObj.expandNode(parentNodes, true, false, true);
                    treeObj.expandNode(parentNode, true, false, true);
                }
            }
        }
    }
     
    /**
     * 递归得到指定节点的父节点的父节点....直到根节点
     */
    function getParentNodes_ztree(treeId, node){
        if (node != null) {
            var treeObj = orgTree.treeObj; //$.fn.zTree.getZTreeObj(treeId);
            var parentNode = node.getParentNode();
            return getParentNodes_ztree(treeId, parentNode);
        } else {
            return node;
        }
    }
    
	//orgTree.init(); //加载当前职级关系
	
});

</script>
</body>
</html>