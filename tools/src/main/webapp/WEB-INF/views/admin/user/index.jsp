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
			$("#pager").pager(request.getContextPath()+'/admin/user/list',15,$('#userView').view().on('add',function(temp){
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
					zhis.target.find('.switch').click(function(){
						$.post('${pageContext.request.contextPath}/admin/user/add',zhis.getData(),function(data){
								$.dialog(data);
						});
					});
				})(this);
			}));
			$('#userAjaxForm').ajaxForm(function(data){
				$('#pager').pager().setPostData(Ace.parseQuery($('#userAjaxForm').serialize()));
				$('#pager').pager().setJSON(data);
			});
			$("#createUser").click(function(){
				$.post("${pageContext.request.contextPath}/admin/user/add",function(data){
					$.dialog({content:data,lock:true});
				});
			});
		});
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
<div >
	<div class="fenpeiCon" style="padding-bottom:0px;">
			<div class="fenpeiCon" style="border:0px;">
				<form id="userAjaxForm" action="${pageContext.request.contextPath}/admin/user/list" method="post">
				    
					<input type="hidden" name="pageSize" value="15"/>
					<div class="fenpeiConLine">
						<p style="broder:0px;">账号：</p>
						<input type="text" name="search_LIKE_account" id="account" class="fenpeiInput fenpeiInputWidth2"/>
						<p style="broder:0px;">姓名：</p>
						<input type="text" name="search_LIKE_userName" id="userName" class="fenpeiInput fenpeiInputWidth2"/>
						<!-- <p>职级：</p>
						<select name="search_EQ_orgGrade" id="orgGrade" class="fenpeiInput fenpeiInputWidth2">
							<option value="">全部</option>
							<option value="SA">管理员</option>
							<option value="CAO">CAO</option>
							<option value="GM">GM</option>
							<option value="AH">AH</option>
						</select> -->
				    </div>
				    <div class="fenpeiConLine">
						<input style="margin-left: 40%" type="button" class="fenpeiIcon" id="createUser" value="新建内勤用户"/>
						<input style="margin-left: 80px" type="submit" class="fenpeiIcon" id="searchUser" value="查 询"/>
				    </div>
				</form>
			 </div>
	</div>	
	<div class="col_lg_04" style="width:100%">
		<div class="business_search_list_warp" style="width:95%" style="overflow-x:auto;">
		       <table cellspacing="0" cellpadding="0" class="t-list table" id="userView">
			   <tbody>
			   <tr>
				  <th width="5%" >序号</th>
				  <th width="8%" >账号</th>
				  <th width="8%" >姓名</th>
				  <th width="8%" >分公司编号</th>
		          <th width="8%" nowrap="nowrap" style="overflow:hidden;">分公司名称</th>
				  <th width="5%" >性别</th>
				  <th width="10%" >电话</th>
				  <th width="8%" >职级</th>
				  <th width="8%" >角色</th>
				  <th width="10%" >邮箱</th>
				  <th width="8%" >描述</th>
				  <th style="border-right:1px solid #ccc; width:12%">操作</th>
			   </tr>
				<tr class="tb_tr_content template" name="default">
					<td>{index:seq()}</td>
					<td>{account}</td>
					<td>{userName}</td>
					<td>{orgCode}</td>
		            <td nowrap="nowrap" style="overflow:hidden;" title="{orgName}">{orgShortName}</td>
					<td>{sex:dict({0:'男',1:'女'})}</td>
					<td>{phone}</td>
					<td>{orgGrade}</td>
					<td>{roleName}</td>
					<td>{email}</td>
					<td>{description}</td>
					<td style="border-right:1px solid #ccc" width="18%">
						<span class="span_edit">
							<a href="javascript:void(0)" class="switch">
								<img style="float:left;" src="${pageContext.request.contextPath}/static/images/xiugai.png" title="修改"/>
							</a>
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
	  </div>
</div>
</body>
</html>