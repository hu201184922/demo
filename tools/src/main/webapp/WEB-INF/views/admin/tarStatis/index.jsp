<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html>
<head>
<title>区间统计</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
	.formbutton{
		width: 80px;
		height: 30px; 
		border-radius: 5px;
		border:1px solid #90C1E6;
	}
	.formhref{
		color:#3399FF;
	}
</style>
<script type="text/javascript">
	$(function() {
		$('#pager').pager('${pageContext.request.contextPath}/admin/tarStatis/listStatisTarget',15,$('#templetView').view().on('add',function(data){
			(function(zhis){
				
			})(this);
		}));
		
		$('#parameterAjaxForm').ajaxForm(function(data) {
			$('#pager').pager().setPostData(Ace.parseQuery($('#parameterAjaxForm').serialize()));
			$('#pager').pager().setJSON(data);
		});
		/* $('#pager1').pager('${pageContext.request.contextPath}/admin/tarStatis/listRefTargets',15,$('#templetView1').view().on('add',function(data){
			(function(zhis){
				
			})(this);
		})); */
		$("#createTemplet").click(function() {
			$('#saveTempletForm').data("dialog", $.dialog({
				title : "添加模板",
				content : $('#saveTempletForm')[0]
			}));
		});
		
		$('#refTarForm').ajaxForm(function(data) {
			$('#refTarForm').data("dialog").close();
			//$('#pager').pager().reload();
		});
		
		/* $("#btnQuery").click(function(){
			query();
		}); */
		query();
		$("#btnQuery1").click(function(){
			query1();
		});
    });	
	function showRefTarForm(targetCode){
		clearRefTarForm();
		$("#targetCodeSelected").val(targetCode);
		//query1();
		
		openTarget(targetCode);
	}
	function clearRefTarForm(){
		$("#templetView1 :checkbox").each(function(){
			$(this).attr("checked",false);
		});
	}
	function query(){
		var postData ={
				"targetName":$("#targetNameQuery").val()
		}
		$('#pager').pager().setPostData(postData);
		$('#pager').pager().reload();
	}
	
	function cancel(){
		$('#refTarForm').data("dialog").close();
	}
	
	var selectedList=[];
	function openTarget(subCode){
		var targetTree = $('#targetTree').data('zTree');
		if(targetTree) $('#targetTree').data('zTree','')
		var size=0;
		var setting = {
				treeId: "targetTree",
				data : {
					simpleData : {
						enable : true,
						idKey: 'id',
						pIdKey: 'pId'
					}
				}
				,view:{
					showIcon:false,
					showLine: true
				},check:{enable:true},
				callback:{
					onCheck:function(event,treeId,treeNode){
						var nodes = targetTree.getCheckedNodes(true);
						var nodes1 = targetTree.getCheckedNodes(false);
						selectedList=[];
						$.each(nodes, function (n, value) {
							selectedList.push(this.id);
						})
						$.each(nodes1, function (n, value) {
							size-=1;
						})
					}
				}
		};
		
		//关联指标	
		var selected;
	 	$.ajax({
			url:'${pageContext.request.contextPath}/admin/tarStatis/tree',
			data:{subCode:subCode},
			success:function(data){		
				selected=data
				$('#targetTree').html('');
				for(var i=0;i<data.length;i++){
					selected[i].id = data[i].id;//添加节点id属性
					selected[i].pId = data[i].pId;//添加节点pid属性
					selected[i].name = data[i].name;//添加name属性
					size=data.length;
					//selected[i].isParent = true;//把所有节点默认为父节点	
					selected[i].checked = data[i].checked;//把所有节点默认为父节点					
				}		
		
				targetTree=$('#targetTree').zTree(setting,selected);
				
				$('#refTarForm').data("dialog", $.dialog({
					title : "关联指标",
					content : $('#refTarForm')[0]
				}));
			}
			
		})
		$("#addTarget").off('click')
	 	$("#addTarget").click(function(){
	 		$.getJSON('${pageContext.request.contextPath}/admin/tarStatis/create',{targetCodes:selectedList,subCode:subCode,size:size},function(result){
				if(result==true){
					$.msgbox({time: 2000,msg: "关联成功!",icon:"success"});
					$('#refTarForm').data("dialog").close();
				}
		 	})
	 	});
	 	
	} 
</script>
</head>
<body>
	<div class="business_title">区间统计管理</div>
	<div class="col_lg_04" style="width: 1203px">
		<div class="business_search_list_warp" style="width: 95%">
		 	<div class="business_search">
				<div class="business_search_left" style="margin-left: -20px;">
					<form id="parameterAjaxForm"
							action="${pageContext.request.contextPath}/admin/tarStatis/listStatisTarget"
							method="post">
							<input type="hidden" name="pageSize" value="15" />
						<table>
							<tbody>
								<tr>
									<td style="text-align: left;">主题/板块名称：</td>
									<td><input style="width: 200px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" type="text" name="targetName" id="targetName"  class="text">&nbsp;&nbsp;&nbsp;</td>
									<td><input style="width: 60px;height: 25px;border-radius: 5px;border:1px solid #90C1E6;" type="submit" value="查&#12288;询"style="font-size: 14px;" id="btnQuery"></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
			
			<table cellspacing="0" cellpadding="0" class="t-list table"id="templetView">
				<tbody>
					<tr>
						<th>序号</th>
					    <th>指标编码</th>
					    <th>主题/板块</th>
						<th style="border-right: 1px solid #ccc; width: 120px">操作</th>
					</tr>
					<tr class="tb_tr_content template" name="default">
					    <td>{index:seq()}</td>
					    <td>{targetCode}</td>
					    <td>{targetName}</td>
						<td style="border-right: 1px solid #ccc">
							<a href="javascript:showRefTarForm('{targetCode}')" class="formhref"><img title="关联指标" style="width:17px;margin-left: 45%;" src="${pageContext.request.contextPath}/static/images/huatai_target.png" /></a>
						</td>
					 </tr>	
				</tbody>
			</table>
			<div id="pager"></div>
		</div>
		<div class="clear"></div>
	</div>
	
	<form id="refTarForm" style="display: none;width: 300px;"
		  action="${pageContext.request.contextPath}/admin/tarStatis/saveTarStatis"
		  method="post">
		  <input type="hidden" name="targetCodeSelected" id="targetCodeSelected"/>
		  
		  <div id="tagContent2" class="tagContent tagContent_div"
				style="display: block; background: #F2F2F2 ;width: 96.5%;border: 1px solid #dee1e2;">
				<div>
					<ul id="targetTree" class="ztree"></ul>
				</div>

			</div>
			<div style="height: 50px; colspan: 2"></div>
			<div class="text_left" style="height: 50px; colspan: 3">
				 <input type="button"
					style="float: right; width: 80px; height: 35px; border: 1px solid #90C1E6;margin-right: 50px;"
					onclick="" value="取 消" class="search_btn close">&nbsp;&nbsp;<input type="button" id="addTarget"
					style="width: 80px; float: right; height: 35px; border: 1px solid #90C1E6;"
					value="提 交" class="search_btn" />
			</div>
	<!-- <div id="tagContent2" class="tagContent tagContent_div"
			style="display: block; background: #F7F7F7">
			<table>
				<tbody>
					<tr>
						<td style="text-align: left;">指标代码：</td>
						<td><input style="width: 200px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" type="text" name="targetCodeQuery1" id="targetCodeQuery1"  class="text">&nbsp;&nbsp;&nbsp;</td>
						<td style="text-align: left;">指标名称：</td>
						<td><input style="width: 200px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" type="text" name="targetNameQuery1" id="targetNameQuery1" class="text">&nbsp;&nbsp;&nbsp;</td>
						<td><input style="width: 60px;height: 25px;border-radius: 5px;border:1px solid #90C1E6;" type="button" value="查&#12288;询"style="font-size: 14px;" id="btnQuery1"></td>
					</tr>
				</tbody>
			</table>
			<table cellspacing="0" cellpadding="0" class="t-list table"id="templetView1">
				<tbody>
					<tr>
						<th width="40px">选择</th>
						<th width="100px">指标名称</th>
						<th width="100px">指标代码</th>
					    <th width="100px">指标属性</th>
					    <th width="100px">部门</th>
					    <th width="100px">适用渠道</th>
					</tr>
					<tr class="tb_tr_content template" name="default">
						<td><input type="checkbox" value="{targetCode}" name="targetCode" {checked:dict({true:'checked',false:''})}/></td>
					    <td>{targetName}</td>
					    <td>{targetCode}</td>
					    <td>{targetType:dict({0:'主题或板块',1:'一级指标',2:'二级指标分组',3:'二级指标'})}</td>
					    <td>{deptCode}</td>
					    <td>{channel}</td>
					 </tr>	
				</tbody>
			</table>
			<div style="clear:both;" align="center" >
				<input type="submit" value="确定" class="formbutton" style="margin:20px"/>
				<input type="button" value="取消" class="formbutton" style="margin:20px" onclick="cancel()"/>
			</div>
			<div id="pager1"></div>
		</div> -->
	</form>
</body>
</html>