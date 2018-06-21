<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html>
<head>
<title>外勤用户管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript">
		$(function(){
			var list = Ace.decode("${u:serialize(list)}");
			$("#pager").pager(request.getContextPath()+'/admin/sales/list',15,$('#userView').view().on('add',function(temp){
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
					if(temp.accountstate == '01'){
						zhis.target.find("#accountstate1").html("在职");
					}else{
						zhis.target.find("#accountstate1").html("离职");
					} 
					if(temp.orgGrade == 'AM'){
						zhis.target.find("#upgradecode").html(temp.rmcode);
						zhis.target.find("#upgradename").html(temp.rmname);
					}else if(temp.orgGrade == 'SM'){
						zhis.target.find("#upgradecode").html(temp.amcode);
						zhis.target.find("#upgradename").html(temp.amname);
					}else if(temp.orgGrade == 'LP'){
						zhis.target.find("#upgradecode").html(temp.smcode);
						zhis.target.find("#upgradename").html(temp.smname);
					}
				})(this);
			}));
			$('#userAjaxForm').ajaxForm(function(data){
				$('#pager').pager().setPostData(Ace.parseQuery($('#userAjaxForm').serialize()));
				$('#pager').pager().setJSON(data);
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
		function enableUser(account,enable){
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/admin/user/changeEnable",
				data : {account : account,enable:enable},
				success : function(msg) {
					/* if(msg == "true"){
						$("#disable").show();
						$("#enable").hide();
					}else if(msg == "false"){
						$("#enable").show();
						$("#disable").hide();
					} */
					$("#pager").pager().reload();
				}
			});
		}
		function resetPsw(id){
			$.dialog("<html><head><title>确认重置提示</title></head><body><a id='diaClosePsw' class='close' style='display: none;'></a><span>确认重置密码？</span><br/><br/><div class='clear'></div><input type='button' onclick='comfirmResetPsw("+id+")' value='确认'/></body></html>");
		}
		function comfirmResetPsw(id){
			$.getJSON('${pageContext.request.contextPath}/admin/user/resetPsw',{id:id},function(bit){
				$('#diaClosePsw').click();
				$.dialog("<html><head><title>密码重置消息</title></head><body><span>密码重置成功！新密码为："+bit+"</span></body></html>");
			});
		}
	</script>
</head>
<body>

<div class="business_title">外勤用户管理</div>
<div  style="width:100%">

<div class="fenpeiCon">
	<form id="userAjaxForm" action="${pageContext.request.contextPath }/admin/sales/list" method="post">
		<input type="hidden" name="search_LIKE_isType" id="isType" value="02"/>
		<div class="fenpeiConLine">
				<p>姓名：</p>
				<input type="text" name="search_LIKE_userName" id="userName" class="fenpeiInput fenpeiInputWidth2"/>
				<p>账号：</p>
				<input type="text" name="search_LIKE_account" id="account" class="fenpeiInput fenpeiInputWidth2"/>
				<p>角色：</p>
				<select name="search_LIKE_orgGrade" id="orgGrade" class="fenpeiInput fenpeiInputWidth2">
					<option value="">全部</option>
					<c:forEach var="searchRole" items="${searchRoles }">
						<option value="${searchRole.code }">${searchRole.name }</option>
					</c:forEach>
				</select>
		</div>
		<div class="fenpeiConLine">
				<p>状态：</p>
				<select name="search_LIKE_accountstate" id="accountstate" class="fenpeiInput fenpeiInputWidth2">
					<option value="">全部</option>
		         	<option value="01">在职</option>
		         	<option value="03">离职</option>
	         	</select>
	         	<p>分公司：</p>
				<select name="search_LIKE_orgCode" id="orgCode" class="fenpeiInput fenpeiInputWidth2">
					<option value="">全部</option>
		         	<c:forEach var="org" items="${orglist }">
						<option value="${org.cityCode}">${org.shortName }</option>
					</c:forEach>
	         	</select>
	         	<input style="margin-left: 100px" type="submit" class="fenpeiIcon" id="searchUser" value="查 询"/>
		</div>
		<%--
		<div class="fenpeiConLine">
				<input style="margin-left: 1073px" type="button" class="fenpeiIcon" id="searchUser" value="查 询"/>
		</div>
		 --%>
	</form>
</div>
<div class="col_lg_04" style="width:100%">
<div class="business_search_list_warp" style="width:95%" style="overflow-x:auto;">
	   <h1 id="dtitle" style="font-size: 150%;display:none">{name}</h1>
       <table cellspacing="0" cellpadding="0" class="t-list table" id="userView">
	   <tbody>
	   <tr>
		  <th width="40px">序号</th>
		  <th>账号</th>
		  <th>姓名</th>
		  <th>上级账号</th>
		  <th>上级姓名</th>
		  <th>分公司编号</th>
		  <th>分公司名称</th>
		  <th>性别</th>
		  <th>电话</th>
		  <th>职级</th>
		  <th>角色</th>
		  <th>邮箱</th>
		  <th>状态</th>
		  <th style="border-right:1px solid #ccc; width:100px">操作</th>
	   </tr>
		<tr class="tb_tr_content template" name="default">
			<td>{index:seq()}</td>
			<td>{account}</td>
			<td>{userName}</td>
			<td><span id="upgradecode"></span></td>
			<td><span id="upgradename"></span></td>
			<td>{orgCode}</td>
			<td>{orgShortName}</td>
			<td>{sex:dict({M:'男',F:'女',0:'男',1:'女'})}</td>
			<td>{phone}</td>
			<td>{orgGrade}</td>
			<td>{roleName}</td>
			<td>{email}</td>
			<td><span id="accountstate1"></span></td>
			<td style="border-right:1px solid #ccc">
				<span class="span_edit">
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
</body>
</html>