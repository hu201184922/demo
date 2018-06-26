	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/util.tld" prefix="u"%>
<html>
<head>
<title>用户行为分析</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(function() {
		$('#pager').pager('${pageContext.request.contextPath}/admin/userBehavior/list',15,$('#userBehView').view().on('add',function(data){
			(function(zhis){
				var deleteConfirm = new Ace.awt.ConfirmBox({
					widget : $('<div style="background-color:#FFFFFF; display:none; text-align:center;  line-height:50px;width: 100px;height: 50px; border-radius: 5px;border:1px solid #90C1E6;"><span class="yesorno"><a href="javascript:void(0)" class="confirm">确定</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="cancel">取消</a></span></div>'),
					trigger : zhis.target.find('.removed'),
					positionAdjust : [ 6, -45 ]
				});	
			})(this);
		}));
		$('#targetAjaxForm').ajaxForm(function(data) {
					$('#pager').pager().setPostData(Ace.parseQuery($('#targetAjaxForm').serialize()));
					$('#pager').pager().setJSON(data);
				});
		$('#pager').pager().reload();
		//获取机构
		$.getJSON('${pageContext.request.contextPath}/admin/userBehavior/orgList',function(datas){
			var div = $("#orgs");
			$.each(datas, function(index, data){
				div.append('<option value='+data["SHORTCODE"]+'>'+data["SHORTNAME"]+"</option>")
			});
		});	
		//模块
		$.getJSON('${pageContext.request.contextPath}/admin/userBehavior/moduleList',function(data){
			var div = $("#module");
			$.each( data, function(index, module)
			  {
				div.append('<option values="'+module+'">'+module+"</option>")
			  });
		});	
		//子节点
		$.getJSON('${pageContext.request.contextPath}/admin/userBehavior/nodeList',function(data){
			var div = $("#isNode");
			$.each( data, function(index, module)
			  {
				div.append('<option values="'+module+'">'+module+"</option>")
			  });
		});
		
});	
	function orgChang(){
		var datas=$("#module").val();
		$("#isNode").empty();
		//子节点
		$.getJSON('${pageContext.request.contextPath}/admin/userBehavior/nodeList',{ data : datas},function(data){
			var div = $("#isNode");
			div.append('<option value="">--请选择--</option>')
			$.each( data, function(index, module)
			  {
				div.append('<option values="'+module+'">'+module+"</option>")
			  });
		});	
	}
	//下载
	var Folder="";
	function Download(){
		var orgs=$('#orgs :selected').val();
		var userId=$('#userId').val();
		var module=$('#module :selected').val();
		var isNode=$('#isNode :selected').val();
		var datetime1=$('#datetime1').val();
		var datetime2=$('#datetime2').val();
		window.location.href = '${pageContext.request.contextPath}/admin/userBehavior/download?orgs='+orgs+'&userId='+userId+'&module='+module+'&isNode='+isNode+'&datetime1='+datetime1+'&datetime2='+datetime2;
	}
	
	//刷新页面
	function reload(){
		location.reload();
	}
	
</script>
</head>
<body>
	<div class="tagContentList">
	<div class="business_title">用户行为分析</div>
	<div class="col_lg_04" style="width: 1203px">
		<div class="business_search_list_warp" style="width: 95%">
		 	<div class="business_search" align="center">
				<div class="business_search_left" style="margin-left:20px;" align="center">
					<form id="targetAjaxForm" 
						action="${pageContext.request.contextPath}/admin/userBehavior/list"
						method="post" style="width:900px">
						<input type="hidden" name="pageSize" value="15" />
						<table>
							<tbody>
								<tr>
									<td style="text-align: right">机构：</td>
									<td><select style="width: 301px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" name="orgs" id="orgs" class="view-field">
											<option value="" selected="selected">--请选择--</option>
										</select>&nbsp;&nbsp;</td>
							 		<td style="text-align: right;border-left: 100px">工号：</td>
									<td><input style="border-radius:5px; width:300px;height: 30px;border:1px solid #90C1E6;" class="text_wd180 view-field" name="userId" id="userId" type="text"
							 			/>&nbsp;&nbsp;</td>
								</tr>
								<tr>
							 		<td style="text-align: right">模块：</td>
									<td><select onchange="orgChang(this)" style="width: 301px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" name="module" id="module" class="view-field">
											<option value="" selected="selected">--请选择--</option>
										</select>&nbsp;&nbsp;</td>
									<td style="text-align: right">节点：</td>
									<td><select style="width: 301px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" name="isNode"  id="isNode" class="view-field">
										<option value="" selected="selected">--请选择--</option>
									</select>&nbsp;&nbsp;</td>
								</tr>
								<td style="text-align: center">时间区间：</td>
									<td><input style="border-radius:5px; width:300px;height: 30px;border:1px solid #90C1E6;"  name="datetime1" id="datetime1" type="date" 
							 			/></td>
							 		<td style="text-align: left">～</td>
									<td><input style="border-radius:5px; width:300px;height: 30px;border:1px solid #90C1E6;"  name="datetime2" id="datetime2" type="date"
							 			/>&nbsp;&nbsp;</td>
								<tr height="50px">
							 		<td></td>
									<td>
										<input type="submit" value="查&#12288;询" 
											style="float: right;margin-right: 50px;width: 60px;height: 25px;border-radius: 5px;border:1px solid #90C1E6;">
									</td>
									<td>
									</td>
									<td>
										<input type="button" value="重&#12288;置"  onclick="reload()"
											style="float: left;margin-right: 50px;width: 60px;height: 25px;border-radius: 5px;border:1px solid #90C1E6;">
									</td>
								</tr>
							</tbody>
						</table>
					</form>
					<div style="float: right; height: 30px;">
						<input type="button" value="下&#12288;载" onclick="Download()"
						style="margin-right: -545px;width: 60px;height: 25px;border-radius: 5px;border:1px solid #90C1E6;">
					</div>
				</div>
			</div>			
			<table cellspacing="0" cellpadding="0" class="t-list table"id="userBehView">
				<tbody>
					<tr>
					      <th width="">序号</th>
					      <th width="">工号</th>	
					      <th width="">姓名</th>			      				      
						  <th width="">模块</th>		 								 
						  <th width="">请求节点</th>		 								 
						  <th width="">节点名称</th>		 								 
						  <th width="">请求路径</th>		 								 
						  <th width="">停留时长</th>		 								 
						  <th width="">响应时长</th>		 								 
					</tr>
					<tr class="tb_tr_content template" name="default">	 
						<td>{index:seq()}</td>
						<td>{userId}</td>
						<td>{userName}</td>
						<td>{fmName}</td>
						<td>{accessId}</td>	
						<td>{accessName}</td>	
						<td>{accessUrl}</td>	
						<td>{stayTime}</td>	
						<td>{resTime} ms</td>	
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