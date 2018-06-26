<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Redis</title>
<script type="text/javascript">
	function showValue(key){
		$.get("${pageContext.request.contextPath}/admin/redis/getVal?key="+encodeURIComponent(key),function(data){
			alert(data);
		});
	}
	function deleteKey(key,obj){
		$.confirm("是否确定删除？", 
				function() {
					$.post("${pageContext.request.contextPath}/admin/redis/delete?key="+encodeURIComponent(key),function(data){
						//alert(data);
						$(obj).parent().parent().remove();
					});
				}
		);
		
	}
	function selectAll(){
		var checked = $("#ckSelectAll").attr("checked");
		if(checked=='checked'){
			$(".keys").attr("checked","checked");
		}else{
			$(".keys").removeAttr("checked");
		}
	}
	function multiDelete(){
		$.confirm("是否确定删除？", 
				function() {
					$('#deleteSelForm').submit();
				}
		);
	}
	$(function(){
		
		$('#deleteSelForm').ajaxForm(function(data) {
			//alert(data);
			//location.reload();
			$('input:checkbox[name=keys]:checked').each(function(i){
				$(this).parent().parent().remove();
			});
		});
		$("#setRedisBtn").click(function() {
			$("#setRedisForm")[0].reset();
			$('#setRedisForm').data("dialog", $.dialog({
				title : "新增数据",
				content : $('#setRedisForm')[0]
			}));
		});
		$('#setRedisForm').ajaxForm(function(data) {
			$('#setRedisForm').data("dialog").close();
			location.reload();
		});
	})
</script>
</head>
<body>
<div class="business_title">Redis数据管理</div>
	<div class="col_lg_04" style="width: 1203px">
		<div class="business_search_list_warp" style="width: 95%">
			<input type="button" id="setRedisBtn" value="新增数据" style="width: 80px;height: 30px; border-radius: 5px;border:1px solid #90C1E6;" >
		 	<div class="business_search">
				<div class="business_search_left" style="margin-left: -20px;">
					<form id="templetAjaxForm"
						action="${pageContext.request.contextPath}/admin/redis/index"
						method="get">
						<table>
							<tbody>
								<tr>
									<td style="text-align: left;">Key Pattern：</td>
									<td><input style="width: 200px;height: 30px;border-radius: 5px;border:1px solid #90C1E6;" type="text" name="pattern" id="pattern"  class="text" value="${pattern }" placeholder="区分大小写，可以使用通配符 *">&nbsp;&nbsp;&nbsp;</td>
									<td><input style="width: 60px;height: 25px;border-radius: 5px;border:1px solid #90C1E6;" type="submit" value="查&#12288;询"style="font-size: 14px;" id="btnQuery"></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
			<form id="deleteSelForm"
						action="${pageContext.request.contextPath}/admin/redis/multiDelete"
						method="post">
				<input type="button" onclick="multiDelete()" style="width: 60px;height: 25px;border-radius: 5px;border:1px solid #90C1E6;" value="删除所选"/>
				<table cellspacing="0" cellpadding="0" class="t-list table"id="templetView">
					<tbody>
						<tr>
							<th width="20px"><input type="checkbox" id="ckSelectAll" onclick="selectAll()" autocomplete="off">选择</th>
							<th width="20px">序号</th>
						    <th width="100px">Key</th>
							<th style="border-right: 1px solid #ccc; width: 20px">操作</th>
						</tr>
						<c:forEach items="${list}" var="key" varStatus="idx">
							<tr class="tb_tr_content template" name="default">
								<td><input type="checkbox" name="keys" value="${key }" class="keys" autocomplete="off"></td>
							    <td>${idx.index }</td>
							    <td>${key}</td>
							    <td>
									<a href="javascript:void(0)" class="switch" onclick="showValue('${key}')"> 
									<img style="float: left;"
										src="${pageContext.request.contextPath}/static/images/xiugai.png"
											title="查看Value" /></a> 
									<a href="javascript:void(0)" class="removed" onclick="deleteKey('${key}',this)"> <img
											style="float: left;"
											src="${pageContext.request.contextPath}/static/images/del_icon.png"
											title="删除">
									</a>
								</td>
						    </tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
		</div>
		<div class="clear"></div>
	</div>
	<form id="setRedisForm" style="display: none;"
		  action="${pageContext.request.contextPath}/admin/redis/setRedis"
		  method="post">
		<div id="tagContent2" class="tagContent tagContent_div"
			style="display: block; background: #F7F7F7">
			<table style="line-height: 30px">
				<tbody>
					<tr>
						<td class="text_right">Key：</td>
						<td class="text_left"><input type="text" name="key" style="border-radius:5px; width:200px;height: 30px;border:1px solid #90C1E6;"/></td>
					</tr>
					<tr><td height="30px" colspan="2"></td></tr>
					<tr>
						<td class="text_right">Value：</td>
						<td class="text_left"><input type="text" name="value" style="border-radius:5px; width:200px;height: 30px;border:1px solid #90C1E6;"/></td>
					</tr>
					<tr><td height="30px" colspan="2"></td></tr>
					<tr>
						<td></td>
						<td class="text_left"><input type="submit" value="提 交"
							class="search_btn">&nbsp;&nbsp;<input type="button"
							value="取 消" class="search_btn close"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</body>
</html>