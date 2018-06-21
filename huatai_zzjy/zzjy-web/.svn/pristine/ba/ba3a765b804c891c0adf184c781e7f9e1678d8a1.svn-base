<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<hmtl>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript">
		$(function(){
			var page = Ace.decode("${u:serialize(pager)}");
			$('#pager').pager('${pageContext.request.contextPath}/admin/sec/role/list',15,$('#roleView').view().on('add',function(){
				(function(zhis){
					var deleteConfirm = new Ace.awt.ConfirmBox({
						widget : $('<div style="background-color:#FFFFFF;display:none;"><span class="yesorno"><a href="javascript:void(0)" class="confirm">确定</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="cancel">取消</a></span></div>'),
						trigger : zhis.target.find('.removed'),
						positionAdjust : [ 6, -45 ]
					});	
					deleteConfirm.on('confirm',function(w, t) {
						$.getJSON('${pageContext.request.contextPath}/admin/sec/role/delete',{id:zhis.getData().id},function(bit){
							if(bit==true){
								$.msgbox({time: 2000,msg: "删除成功!",icon:"success"});
							}
							$('#pager').pager().reload(function(){
								zhis.target.remove();
							});
						});
					});
					
					if(zhis.getData().id >= 1 && zhis.getData().id <= 7){
						zhis.target.find('.removed').remove();
					}
					
					zhis.target.find('.save').click(function(){
						$.getJSON('${pageContext.request.contextPath}/admin/sec/role/update',zhis.getData(),function(data){
							$.msgbox({time: 2000,msg: "修改成功!",icon:"success"});
							zhis.setData(data);
							zhis.setTemplate('default',true);
						});
					});
				})(this);
			})).setJSON(page);
			
			$("#createRole").click(function(){
				$('#saveRoleForm').data("dialog",$.dialog( {
					title : "添加角色",
					content : $('#saveRoleForm')[0]
				}));
			});
			$('#saveRoleForm').ajaxForm(function(data) {
				$('#saveRoleForm').data("dialog").close();
				$('#pager').pager().reload();
				$('#saveRoleForm').resetForm();
			});
		});
		function sub(){
			var name = $('input[name="name"]').val();
			var code = $('input[name="code"]').val();
			if($.trim(name) == ''){
				$.dialog({content: '请输入角色名!', time: 2000});return;
			}else if($.trim(name)==null || $.trim(name)=='NULL'){
				$.dialog({content: '请输入角色名!', time: 2000});return;
			}
			if($.trim(code)=='' || $.trim(code)==null){
				$.dialog({content: '请输入角色编号!', time: 2000});return;
			}
			
			$("#saveRoleForm").submit();
		}
	</script>
	</head>
	<body>
	<div class="tagContentList">
		<div class="business_title">角色管理</div>
		<div class="col_lg_04" style="width:1203px">
		<div class="business_search_list_warp" style="width:95%">
			<input type="button" id="createRole" value="新建角色">
	          <table cellspacing="0" cellpadding="0" class="t-list table" id="roleView">
			   <tbody>
			   <tr>
				  <th width="40px">序号</th>
				  <th width="250px">角色名称</th>
				  <th width="100px">角色编码</th>
				  <th width="80px">角色类型</th>
				  <th>角色描述</th>
				  <th style="border-right:1px solid #ccc; width:120px">操作</th>
			   </tr>
				<tr class="tb_tr_content template" name="default">
					<td>{index:seq()}</td>
					<td>{name}</td>
					<td>{code}</td>
					<td>{type:dict({00:'管理员',01:'内勤',02:'外勤'})}</td>
					<td>{descript}</td>
					<td style="border-right:1px solid #ccc">
						<span class="span_edit">
							<a href="javascript:void(0)" class="switch" template="update">
								<img style="float:left;" src="${pageContext.request.contextPath}/static/images/xiugai.png" title="修改"/>
							</a>
					  		<a ajax="{target:'.tagContentList:last'}" class="new_a"  href="${pageContext.request.contextPath}/admin/sec/role/editauthority?id={id}">
					  			<img src="${pageContext.request.contextPath}/static/images/shouquan.png" title="权限配置"/>
					  		</a>&nbsp;&nbsp;
							<a href="javascript:void(0)" class="removed">
								<img style="float:left;" src="${pageContext.request.contextPath}/static/images/del_icon.png" title="删除">
							</a>
							<a ajax="{type:'dialog'}" href="${pageContext.request.contextPath }/admin/sec/role/addHref?id={id}">
								<img style="float:left;" src="${pageContext.request.contextPath}/static/images/xiugai.png" title="编辑链接"/>
							</a>
						</span> 
					</td>
				</tr>
				<tr class="tb_tr_content template" name="update">
					<td>{index:seq()}</td>
					<td>
					<input style="width: 120px;" class="text_wd180 view-field" name="name" type="text" mapping="name"/></td>
					<td><input style="width: 120px;" class="view-field" name="code" type="text" mapping="code"/></td>
					<td>
					<select name="type" mapping="type" class="view-field">
						<c:forEach items="${roleType}" var="item">
							<option value="${item.code}">${item.name}</option>
						</c:forEach>
					</select>
					</td>
					<td><input class="text_wd180 view-field" name="descript" type="text" mapping="descript"/></td>
					<td style="border-right:1px solid #ccc">
						<span class="span_edit">
					      <a href="javascript:void(0)" class="save"><img style="float:left;" title="保存" src="${pageContext.request.contextPath}/static/images/save.png"></a>&nbsp;
					      <a href="javascript:void(0)" class="switch back" template="default"><img style="float:left;" title="返回" src="${pageContext.request.contextPath}/static/images/fanhui.png"></a>
					   </span>
					</td>
				</tr>
			</tbody>
			</table>
			<div id="pager"></div>
	    </div>
	    <div class="clear"></div>
	    </div>
		<form id="saveRoleForm" style="display:none;" action="${pageContext.request.contextPath}/admin/sec/role/create" method="post" >
			<div id="tagContent2" class="tagContent tagContent_div" style="display:block;background:#F7F7F7">
		            <table style="line-height:30px">
					  <tbody><tr>
						    <td class="text_right">角色名称：</td>
							<td class="text_left"><input type="text" name="name"  class="text_wd200"/></td>
						</tr>
						<tr>
						    <td class="text_right">角色编码：</td>
							<td class="text_left"><input type="text" name="code"  class="text_wd200"/></td>
						</tr>
						<tr>
						    <td class="text_right">角色类型：</td>
							<td class="text_left">
								<select name="type">
									<c:forEach items="${roleType}" var="item">
									<option value="${item.code}">${item.name}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
						    <td class="text_right">角色描述：</td>
							<td class="text_left">
								<textarea name="descript" type="text"  style="width:205px;"></textarea>
							</td>
						</tr>
						<tr>
						    <td height="10px" colspan="2"></td>
						</tr>
						<tr>
						    <td></td>
							<td class="text_left">
							<input type="button" value="提 交" onclick="sub(this)" class="search_btn">&nbsp;&nbsp;<input type="button" value="取 消" class="search_btn close">
							</td>
						</tr>
					</tbody>
				</table>
		    </div>
		</form>
		</div>
	</body>
</hmtl>