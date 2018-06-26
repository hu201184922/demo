<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html>
<head>
<title>用户管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript">
		$(function(){
			
			$("#exportBtn").click(function(){
				var orgCode = $('#orgCode').val();
				if(stringUtils.isEmpty(orgCode)){
					$.alert("请点击选择左边需要查询的组织机构！");
					return;
				}
				window.location.href = request.getContextPath() + "/admin/sec/user/reportExport?search_EQ_orgCode="+$('#orgCode').val();
			});
			
			$("#pager").pager(request.getContextPath()+'/admin/sec/user/list',15,$('#userView').view().on('add',function(temp){
				(function(zhis){
					var deleteConfirm = new Ace.awt.ConfirmBox({
						widget : $('<div style="background-color:#FFFFFF;display:none;"><span class="yesorno"><a href="javascript:void(0)" class="confirm">确定</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="cancel">取消</a></span></div>'),
						trigger : zhis.target.find('.removed'),
						positionAdjust : [ 6, -45 ]
					});	
					deleteConfirm.on('confirm',function(w, t) {
						$.getJSON('${pageContext.request.contextPath}/admin/user/delete',{id:zhis.getData().id},function(bit){
							if(bit==true){
								$.msgbox({time: 2000,msg: "删除成功!",icon:"success"});
							}
							zhis.target.remove();
						});
					});
					if(temp.enabled){
						zhis.target.find("#disable").show();
					}else{
						zhis.target.find("#enable").show();
					}
// 					var resetPsw = new Ace.awt.ConfirmBox({
// 						widget : $('<div style="background-color:#FFFFFF;display:none;"><span class="yesorno"><a href="javascript:void(0)" class="confirm">确定</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="cancel">取消</a></span></div>'),
// 						trigger : zhis.target.find('.resetPsw'),
// 						positionAdjust : [ 6, -45 ]
// 					});	
// 					resetPsw.on('confirm',function(w, t) {
// 						$.getJSON('${pageContext.request.contextPath}/admin/user/resetPsw',{id:zhis.getData().id},function(bit){
// 							$.dialog("<html><head><title>密码重置消息</title></head><body><span>密码重置成功！新密码为："+bit+"</span></body></html>");
// 						});
// 					});
					zhis.target.find('.switch').click(function(){
						$.post('${pageContext.request.contextPath}/admin/sec/user/add',zhis.getData(),function(data){
								$.dialog(data);
						});
					});
				})(this);
			}));
			$('#orgTree').zTree({url:'${pageContext.request.contextPath}/admin/user/tree',dataFilter:function(treeId, parentNode, childNodes){
				childNodes?childNodes.each(function(){
					this.id=this.orgid;
					this.pid=this.porgId;
					this.name=this.orgName+(this.arg1 == 0 ? "" : "("+this.arg1+"/"+this.arg2+")");
					this.isParent= (this.levels == 1 ? true : (this.levels == 3 ? false : (this.childsnum > 0 ? true : false)));
				}):null;
				return childNodes;
			},
			edit:{
				enable:true,
				showRemoveBtn : false,
				showRenameBtn : false
			},
			callback:{
				add:function(treeId, treeNode){
					$.post("${pageContext.request.contextPath}/admin/sec/user/add",{orgCode:treeNode.orgCode},function(data){
						$.dialog({content:data,lock:true});
					});
				},
				onClick : function(e, treeId, treeNode) {
					$.getJSON(request.getContextPath()+'/admin/sec/user/list', {
						search_EQ_orgCode : treeNode.orgCode
					}, function(data) {
						$("#orgCode").val(treeNode.orgCode);
						$("#dtitle").template(treeNode);
						$("#dtitle").show();
						$("#pager").pager().options.postData.search_EQ_orgCode=treeNode.orgCode;
						$("#pager").pager().setJSON(data);
					});
				}

			}
		});
		});
		var actions = {'add':'数据字典添加'};
		var dictDialog = function(pid,type){
			$('#saveDictForm').template({
				pid : pid
			});
			$('#saveDictForm').data('type',type);
			$('#saveDictForm').data('dialog', $.dialog( {
				title : actions[type],
				content : $('#saveDictForm')[0]
			}));
		};
		function resetPsw(id){
			$.dialog("<html><head><title>确认重置提示</title></head><body><a id='diaClosePsw' class='close' style='display: none;'></a><span>确认重置密码？</span><br/><br/><div class='clear'></div><input type='button' onclick='comfirmResetPsw("+id+")' value='确认'/></body></html>");
		}
		function comfirmResetPsw(id){
			$.getJSON('${pageContext.request.contextPath}/admin/user/resetPsw',{id:id},function(bit){
				$('#diaClosePsw').click();
				$.dialog("<html><head><title>密码重置消息</title></head><body><span>密码重置成功！新密码为："+bit+"</span></body></html>");
			});
		}
		function enableUser(account,enable){
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/admin/user/changeEnable",
				data : {account : account,enable:enable},
				success : function(msg) {
					$("#pager").pager().reload();
				}
			});
		}
	</script>
</head>
<body>
<div class="business_title">用户管理</div>
<div  style="float:right;width:928px">

<div class="col_lg_04" style="width:923px">
<div class="business_search_list_warp" style="width:95%">
	   <h1 id="dtitle" style="font-size: 150%;display:none">{name}</h1>
	   <input type="hidden" id="orgCode" />
       <table cellspacing="0" cellpadding="0" class="t-list table" id="userView">
	   <tbody>
	   <tr>
		  <th width="40px">序号</th>
		  <th>账号</th>
		  <th>姓名</th>
		  <th>性别</th>
		  <th>电话</th>
		  <th>角色</th>
		  <th>邮箱</th>
		  <th>描述</th>
		  <th style="border-right:1px solid #ccc; width:100px">操作</th>
	   </tr>
		<tr class="tb_tr_content template" name="default">
			<td>{index:seq()}</td>
			<td>{account}</td>
			<td>{userName}</td>
			<td>{sex:dict({0:'男',1:'女'})}</td>
			<td>{phone}</td>
			<td>{roleName}</td>
			<td>{email}</td>
			<td>{description}</td>
			<td style="border-right:1px solid #ccc" width="18%">
				<span class="span_edit">
					<a href="javascript:void(0)" class="switch">
						<img style="float:left;" src="${pageContext.request.contextPath}/static/images/xiugai.png" title="修改"/>
					</a>
					<a ajax="{type:'dialog'}" href="${pageContext.request.contextPath}/admin/sec/user/auth?id={id}">
					  		<img src="${pageContext.request.contextPath}/static/images/shouquan.png" title="授权重置"/>
					  	</a>&nbsp;&nbsp;
					<a href="javascript:void(0)" class="removed">
						<img style="float:left;" src="${pageContext.request.contextPath}/static/images/del_icon.png" title="删除">
					</a>
					<a href="javascript:resetPsw({id})">
						<img style="float:left;" src="${pageContext.request.contextPath}/static/images/shouquan.png" title="重置密码"/>
					</a>
					<a id="enable" style="display: none;" href="javascript:enableUser('{account}',true)">启用</a>
					<a id="disable" style="display: none;" href="javascript:enableUser('{account}',false)">禁用</a>
				</span> 
			</td>
		</tr>
	</tbody>
	</table>
	<div id="pager"></div>
   </div>
   <div class="clear"></div>
   </div>
</div>
<div style="float:left;width:257px;overflow: auto;height: 300px">
	<ul id="orgTree" class="ztree"></ul>
</div>  
</body>
</html>