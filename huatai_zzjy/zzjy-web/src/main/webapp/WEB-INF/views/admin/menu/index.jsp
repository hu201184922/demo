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
			$('#pager').pager('${pageContext.request.contextPath}/admin/menu/list',15,$('#menuView').view().on('add',function(){
				(function(zhis){
					var deleteConfirm = new Ace.awt.ConfirmBox({
						widget : $('<div style="background-color:#FFFFFF;display:none;"><span class="yesorno"><a href="javascript:void(0)" class="confirm">确定</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="cancel">取消</a></span></div>'),
						trigger : zhis.target.find('.removed'),
						positionAdjust : [ 6, -45 ]
					});	
					deleteConfirm.on('confirm',function(w, t) {
						$.getJSON('${pageContext.request.contextPath}/admin/menu/delete',{id:zhis.getData().id},function(bit){
							if(bit==true){
								$.msgbox({time: 2000,msg: "删除成功!",icon:"success"});
							}
							$('#pager').pager().reload(function(){
								zhis.target.remove();
							});
						});
					});
					zhis.target.find('.save').click(function(){
						$.getJSON('${pageContext.request.contextPath}/admin/menu/update',zhis.getData(),function(data){
							$.msgbox({time: 2000,msg: "修改成功!",icon:"success"});
							zhis.setData(data);
							zhis.setTemplate('default',true);
						});
					});
				})(this);
			}));
			$('#menuAjaxForm').ajaxForm(function(data){
				$('#pager').pager().setPostData(Ace.parseQuery($('#menuAjaxForm').serialize()));
				$('#pager').pager().setJSON(data);
			});
			$("#createMenu").click(function(){
				$('#saveMenuForm').data("dialog",$.dialog( {
					title : "添加菜单",
					content : $('#saveMenuForm')[0]
				}));
				$('input[name="name"]').val("")
				$('input[name="url"]').val("");
				$('input[name="visible"]').attr("checked",false);
				$('input[name="sortIndex"]').val("");
				$('textarea[name="description"]').val("");
				
			});
			$('#saveMenuForm').ajaxForm(function(data) {
				$('#saveMenuForm').data("dialog").close();
				$('#pager').pager().reload();
			});
		});
		function sub(){
			var name = $('input[name="name"]').val()
			var url = $('input[name="url"]').val();
			var sortIndex = $('input[name="sortIndex"]').val();
			if ($.trim(name) == '') {
				$.dialog({time: 2000,content:  "请输入菜单名称！"});
				return;
			}
			if ($.trim(url) == '') {
				$.dialog({time: 2000,content:  "请输入链接地址！"});
				return;
			}
			if ($.trim(sortIndex) == '') {
				$.dialog({time: 2000,content:  "请输入排序！"});
				return;
			}
			$('#saveMenuForm').submit();
		}
	</script>
	</head>
	<body>
		<div class="business_title">菜单管理</div>
		<div class="col_lg_04" style="width:1203px">
		<div class="business_search_list_warp" style="width:95%">
			<input type="button" id="createMenu" value="新建菜单">
			<div class="business_search">
			<div class="business_search_left">
			<form id="menuAjaxForm" action="${pageContext.request.contextPath}/admin/menu/list" method="post">
			<input type="hidden" name="pageSize" value="15"/>
			<table>
				<tbody>
				  <tr>
				      <td style="text-align:right">菜单名称：</td>
				      <td colspan="2"><input type="text" style="width:200px;" name="search_LIKE_name" class="text"></td>
				  </tr>
				  <tr>
				      <td style="text-align:right">链接地址：</td>
				      <td><input style="width:300px;" type="text" name="search_LIKE_url" class="text"></td>
					  <td><input type="submit" value="查&#12288;询" style="font-size: 14px;"></td>
				  </tr>
				</tbody>
			  </table>
			  </form>
			  </div>
			  </div>
	          <table cellspacing="0" cellpadding="0" class="t-list table" id="menuView">
			   <tbody>
			   <tr>
				  <th width="40px">序号</th>
				  <th class="sort ace" orderBy="name">菜单名称</th>
				  <th>链接地址</th>
				  <th>授权字符串</th>
				  <th>排序</th>
				  <th width="80px">是否显示</th>
				  <th>菜单描述</th>
				  <th style="border-right:1px solid #ccc; width:80px">操作</th>
			   </tr>
				<tr class="tb_tr_content template" name="default">
					<td>{index:seq()}</td>
					<td>{name}</td>
					<td>{url}</td>
					<td>{permString}</td>
					<td>{sortIndex}</td>
					<td>{enabled:dict({'true':'显示','false':'不显示'})}</td>
					<td>{description}</td>
					<td style="border-right:1px solid #ccc">
						<span class="span_edit">
							<a href="javascript:void(0)" class="switch" template="update">
								<img style="float:left;" src="${pageContext.request.contextPath}/static/images/xiugai.png" title="修改"/>
							</a>
							<a href="javascript:void(0)" class="removed">
								<img style="float:left;" src="${pageContext.request.contextPath}/static/images/del_icon.png" title="删除">
							</a>
						</span> 
					</td>
				</tr>
				<tr class="tb_tr_content template" name="update">
					<td>{index:seq()}</td>
					<td>
					<input type="hidden" name="type" value="url"/>
					<input style="width: 120px;" class="view-field" name="name" type="text" mapping="name"/></td>
					<td>
						<input style="width: 120px;" class="text_wd180 view-field" name="url" type="text" mapping="url"/>
					</td>
					<td>{permString}</td>
					<td>
						<input  class="view-field" name="sortIndex" type="text" mapping="sortIndex"/>
					</td>
					<td>
						<input class="view-field" name="enabled" type="radio" value="true" style="border:0px"/>启用/<input style="border:0px" name="enabled" type="radio" value="false"/>禁用
					</td>
					<td><input class="text_wd180 view-field" name="description" type="text" mapping="description"/></td>
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
		<form id="saveMenuForm" style="display:none;" action="${pageContext.request.contextPath}/admin/menu/create" method="post" >
			<div id="tagContent2" class="tagContent tagContent_div" style="display:block;background:#F7F7F7">
		            <table style="line-height:30px">
					    <tbody><tr>
						    <td class="text_right">菜单名称：</td>
							<td class="text_left"><input type="text" name="name"  class="text_wd200"/></td>
						</tr>
						<tr>
						    <td class="text_right">是否显示：</td>
							<td class="text_left">
							<input name="visible"  type="checkbox"/>
							</td>
						</tr>
						<tr>
						    <td class="text_right">链接地址：</td>
							<td class="text_left">
								<input name="url" type="text"  style="width:200px;"></input>
							</td>
						</tr>
						<tr>
						    <td class="text_right">排序：</td>
							<td class="text_left">
								<input name="sortIndex" type="text"  style="width:200px;"></input>
							</td>
						</tr>
						<tr>
						    <td class="text_right">菜单描述：</td>
							<td class="text_left">
								<textarea name="description" type="text"  style="width:205px;"></textarea>
							</td>
						</tr>
						<tr>
						    <td height="10px" colspan="2"></td>
						</tr>
						<tr>
						    <td></td>
							<td class="text_left">
							<input type="button" value="提 交" class="search_btn" onclick="sub()">&nbsp;&nbsp;<input type="button" value="取 消" class="search_btn close">
							</td>
						</tr>
					</tbody>
				</table>
		    </div>
		</form>
	</body>
</hmtl>